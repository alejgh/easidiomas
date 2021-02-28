package com.easidiomas.usersservice.persistence;

import com.easidiomas.usersservice.clients.statisticsservice.IStatisticsService;
import com.easidiomas.usersservice.clients.statisticsservice.IStatisticsServiceService;
import com.easidiomas.usersservice.model.User;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;

public class DataGenerator {

    private static final String STATS_SERVICE_WDSL = System.getenv("STATS_SERVICE_WDSL")!=null ? System.getenv("STATS_SERVICE_WDSL"): "http://localhost:5000/soapws/statistics?wsdl";

    public void loadSomeData(UsersRepository repository, int numberOfUsers, int numberOfAdmins) throws MalformedURLException {

        IStatisticsServiceService service = new IStatisticsServiceService(new URL(STATS_SERVICE_WDSL));
        IStatisticsService statisticsService = service.getIStatisticsServicePort();

        for(int i = 0; i < numberOfUsers; i++) {
            if(repository.findByUsername(this.generateUser(i).getUsername()) == null) {
                repository.save(this.generateUser(i));
                statisticsService.registerUserCreatedEvent(Arrays.asList(this.generateUser(i).getLearning()), this.generateUser(i).getSpeaks());
            }
        }

        for(int i = 0; i < numberOfAdmins; i++) {
            if(repository.findByUsername(this.generateAdmin(i).getUsername()) == null) {
                repository.save(this.generateAdmin(i));
            }
        }
    }

    private User generateUser(int trailingNumber) {
        User user = new User();
        user.setRole(0);
        user.setName("User");
        user.setSurname(Integer.toString(trailingNumber));
        user.setUsername("user"+trailingNumber);
        user.setPassword("user"+trailingNumber);
        user.setAvatarUrl("https://www.avatars.com/"+trailingNumber);
        user.setLearning(new String[]{ "es", "en" });
        user.setSpeaks("pt");
        user.setBirthDate(new Date().toInstant().getEpochSecond());
        return user;
    }

    private User generateAdmin(int trailingNumber) {
        User admin = new User();
        admin.setRole(1);
        admin.setName("Admin");
        admin.setSurname(""+trailingNumber);
        admin.setUsername("admin"+trailingNumber);
        admin.setPassword("admin"+trailingNumber);
        return admin;
    }
}
