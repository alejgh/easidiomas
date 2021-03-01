package com.easidiomas.usersservice.controllers;

import com.easidiomas.auth.Authservice;
import com.easidiomas.usersservice.clients.imagesservice.IImageManager;
import com.easidiomas.usersservice.clients.imagesservice.IImageManagerService;
import com.easidiomas.usersservice.clients.statisticsservice.IStatisticsService;
import com.easidiomas.usersservice.clients.statisticsservice.IStatisticsServiceService;
import com.easidiomas.usersservice.filters.*;
import com.easidiomas.usersservice.model.Links;
import com.easidiomas.usersservice.model.ResultPageWrapper;
import com.easidiomas.usersservice.model.User;
import com.easidiomas.usersservice.model.UserInfo;
import com.easidiomas.usersservice.persistence.UsersRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.List;

@RestController
public class UsersController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    private static final String STATS_SERVICE_WDSL = System.getenv("STATS_SERVICE_WDSL")!=null ? System.getenv("STATS_SERVICE_WDSL"): "http://localhost:5000/soapws/statistics?wsdl";
    private static final String IMAGES_SERVICE_WDSL = System.getenv("IMAGES_SERVICE_WDSL")!=null ? System.getenv("IMAGES_SERVICE_WDSL"): "http://localhost:5000/soapws/images?wsdl";

    private Pipeline filters;

    public final UsersRepository repository;

    public UsersController(@Autowired UsersRepository repository) {
        this.repository = repository;

        // Generate a default admin
        User admin = new User();
        admin.setRole(1);
        admin.setName("Admin");
        admin.setSurname("1");
        admin.setUsername("admin1");
        admin.setPassword("admin1");
        admin.setBirthDate(new Date().toInstant().getEpochSecond());
        admin.setSpeaks("es");
        admin.setLearning(new String[] {"en"});
        admin.setAvatarUrl("https://png.pngtree.com/png-vector/20190710/ourmid/pngtree-user-vector-avatar-png-image_1541962.jpg");

        this.repository.save(admin);
    }

    // GET: /api/users
    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity index(@Nullable @RequestParam Integer limit,
                                @Nullable @RequestParam Integer offset,
                                @Nullable @RequestParam Integer minAge,
                                @Nullable @RequestParam Integer maxAge,
                                @Nullable @RequestParam String[] speaks,
                                @Nullable @RequestParam String[] wantsToLearn,
                                @Nullable @RequestParam String username,
                                @Nullable @RequestParam String password) {

        LOGGER.debug("Logging a debug message");
        LOGGER.info("Logging an info message");
        LOGGER.error("Logging an error message");

        this.filters = new Pipeline(repository.findAll());
        if(!Objects.isNull(minAge))
            this.filters.registerFilter(new MinAgeFilter(minAge));
        if(!Objects.isNull(maxAge))
            this.filters.registerFilter(new MaxAgeFilter(maxAge));
        if(!Objects.isNull(speaks))
            this.filters.registerFilter(new SpeaksLanguageFilter(new HashSet<>(Arrays.asList(speaks))));
        if(!Objects.isNull(wantsToLearn))
            this.filters.registerFilter(new WantsToLearnLanguageFilter(new HashSet<>(Arrays.asList(wantsToLearn))));
        if(!Objects.isNull(username))
            this.filters.registerFilter(new UsernameFilter((username)));
        if(!Objects.isNull(password))
            this.filters.registerFilter(new PasswordFilter((password)));

        List<User> hits = this.filters.executePipelineAndGetResult();
        hits.sort(Comparator.comparing(User::getId));
        if(!Objects.isNull(limit))
            if(limit < hits.size())
                hits = hits.subList(0, limit);

        ResultPageWrapper page = new ResultPageWrapper();
        page.setUsers(hits);
        page.setHits(hits.size());
        page.setTotal(repository.findAll().size());
        page.setLinks(new Links("", "", "", "", ""));

        return ResponseEntity.ok().body(page);
    }

    // POST: /api/users
    @PostMapping(value = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody UserInfo user) throws URISyntaxException, MalformedURLException {
        if(repository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");
        }
        User userToSave = new User();
        userToSave.setUsername(user.getUsername());
        userToSave.setPassword(user.getPassword());
        userToSave.setName(user.getName());
        userToSave.setSurname(user.getSurname());
        userToSave.setBirthDate(user.getBirthDate());
        userToSave.setLearning(user.getLearning());
        userToSave.setSpeaks(user.getSpeaks());
        userToSave.setRole(0);

        IImageManagerService imagesService = new IImageManagerService(new URL(IMAGES_SERVICE_WDSL));
        IImageManager imagesProcessor = imagesService.getIImageManagerPort();
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(user.getBase64Image());
        Image imgUpload = null;
        try {
            imgUpload = ImageIO.read(new ByteArrayInputStream(imageBytes));
        } catch (IOException e) {
            LOGGER.error("Error while processing the image bytes.");
            LOGGER.error(e.getMessage());
        }
        //enable MTOM in client
        BindingProvider bp = (BindingProvider) imagesProcessor;
        SOAPBinding binding = (SOAPBinding) bp.getBinding();
        binding.setMTOMEnabled(true);

        try {
            userToSave.setAvatarUrl(imagesProcessor.uploadImage(imgUpload));
        } catch (Exception e) {
            LOGGER.error("Error while calling the images service at address " + IMAGES_SERVICE_WDSL
                    + " and image: " + user.getBase64Image());
            LOGGER.error(e.getMessage());
            userToSave.setAvatarUrl("https://static2.elcomercio.es/www/pre2017/multimedia/RC/201501/12/media/cortadas/avatar--575x323.jpg");
        }

        User savedUser = repository.save(userToSave);

        // Register the created user in the statistics service
        IStatisticsServiceService service = new IStatisticsServiceService(new URL(STATS_SERVICE_WDSL));
        IStatisticsService statisticsService = service.getIStatisticsServicePort();
        statisticsService.registerUserCreatedEvent(Arrays.asList(savedUser.getLearning()), savedUser.getSpeaks());

        return ResponseEntity.created(new URI("http://easidiomas.com/users/" + savedUser.getId())).body(savedUser);
    }

    // GET: /api/users/{id}
    @GetMapping(value = "/api/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathVariable String id) {
        Long userId = null;
        try{
            userId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            // log the exception
            return ResponseEntity.badRequest().body("the id of the user should be a long.");
        }
        User user = repository.findById(userId).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    // PUT: /api/users/{id}
    @PutMapping(value = "api/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(HttpServletRequest request, @PathVariable String id, @RequestBody User newUser) {

        LOGGER.info("Request to update user " + id);

        // validate the passport.
        String passportHeader = request.getHeader("passport");
        if(passportHeader == null || passportHeader.isEmpty()) {
            LOGGER.error("The passport header is null or empty");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        LOGGER.error("The passport header is present: " + passportHeader);
        LOGGER.error("Deserializing the passport");
        Authservice.Passport passport = new Gson().fromJson(request.getHeader("passport"), Authservice.Passport.class);
        LOGGER.error("Passport deserialized: " + passport);

        // Validate the userId.
        Long userId = null;
        try{
            userId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            // log the exception
            return ResponseEntity.badRequest().body("the id of the user should be a long.");
        }

        // Validate that the user requesting the change is the one authenticated.
        if(!passport.getUserId().equals(Long.toString(userId))) {
            LOGGER.warn(String.format("The user requesting the update was [%s] and the passport belongs to [%s].", userId, passport.getUserId()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        LOGGER.info("The user has privileges to perform the update over user " + id);

        User userInRepo = repository.findById(userId)
                .map(user -> {
                    user.setLearning(newUser.getLearning());
                    user.setSpeaks(newUser.getSpeaks());
                    return repository.save(user);
                }).orElseGet(null);
        if(userInRepo == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userInRepo);
    }

    // DELETE: /pai/users/{id}
    @DeleteMapping(value = "api/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity remove(HttpServletRequest request, @PathVariable String id) {
        String passportHeader = request.getHeader("passport");
        if(passportHeader == null || passportHeader.isEmpty()) {
            LOGGER.error("The passport header is null or empty");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        LOGGER.error("The passport header is present: " + passportHeader);
        LOGGER.error("Deserializing the passport");
        Authservice.Passport passport = new Gson().fromJson(request.getHeader("passport"), Authservice.Passport.class);
        LOGGER.error("Passport deserialized: " + passport);

        // Un buen número mágico ahí to gocho. 1 es ADMIN.
        if(passport.getRole() != 1) {
            LOGGER.warn("The authenticated user is not an admin");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not an administrator user");
        }

        Long userId = null;
        try{
            userId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            // log the exception
            return ResponseEntity.badRequest().body("the id of the user should be a long.");
        }
        // Java 1.8 doees not allow .isPresent()...
        if(repository.findById(userId).get() == null)
            return ResponseEntity.notFound().build();

        repository.deleteById(userId);
        return ResponseEntity.ok().build();
    }
}
