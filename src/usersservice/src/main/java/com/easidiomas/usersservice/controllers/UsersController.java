package com.easidiomas.usersservice.controllers;

import com.easidiomas.usersservice.clients.statisticsservice.IStatisticsService;
import com.easidiomas.usersservice.clients.statisticsservice.IStatisticsServiceService;
import com.easidiomas.usersservice.filters.*;
import com.easidiomas.usersservice.model.Links;
import com.easidiomas.usersservice.model.ResultPageWrapper;
import com.easidiomas.usersservice.model.User;
import com.easidiomas.usersservice.model.UserInfo;
import com.easidiomas.usersservice.persistence.DataGenerator;
import com.easidiomas.usersservice.persistence.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

@RestController
public class UsersController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    private static final String STATS_SERVICE_WDSL = System.getenv("STATS_SERVICE_WDSL")!=null ? System.getenv("STATS_SERVICE_WDSL"): "http://localhost:5000/soapws/statistics?wsdl";

    private Pipeline filters;

    @Autowired
    public UsersRepository repository;

    @GetMapping(value = "api/users:generateData")
    public ResponseEntity generateData() throws MalformedURLException {

        new DataGenerator().loadSomeData(repository, 100, 50);

        return ResponseEntity.ok().build();
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
        // Call images service HERE
        userToSave.setAvatarUrl("http://CALL_THE_IMAGES_SERVICE_ASHOLE/");
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
    public ResponseEntity update(@PathVariable String id, @RequestBody User newUser) {
        Long userId = null;
        try{
            userId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            // log the exception
            return ResponseEntity.badRequest().body("the id of the user should be a long.");
        }
        // Get passport from the request.
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
    public ResponseEntity remove(@PathVariable String id) {
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
