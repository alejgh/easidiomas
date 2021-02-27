package com.easidiomas.statisticsservice.service;

import com.easidiomas.statisticsservice.model.SystemStatistics;
import com.easidiomas.statisticsservice.model.UserStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@MTOM
@WebService(endpointInterface = "com.easidiomas.statisticsservice.service.IStatisticsService", serviceName = "statisticsService")
public class StatisticsService implements IStatisticsService {

    public static final Logger LOGGER = LoggerFactory.getLogger(StatisticsService.class);

    // Redis configuration.
    private static final String REDIS_ADDR = System.getenv("REDIS_ADDR")!=null ? System.getenv("REDIS_ADDR"): "10.0.1.1";
    private static final int REDIS_PORT = Integer.parseInt(System.getenv("REDIS_PORT")!=null ? System.getenv("REDIS_PORT"): "6379");

    private final JedisPool pool = new JedisPool(new JedisPoolConfig(), REDIS_ADDR, REDIS_PORT);

    @Override
    public void registerUserCreatedEvent(String[] learning, String speaks) {
        LOGGER.debug("Registering a new user registered event");
        Jedis jedis = pool.getResource();

        // Update the system total registered
        Set<String> keys = jedis.hkeys("system");
        if(keys == null || !keys.contains("totalRegistered")) {
            jedis.hset("system", "totalRegistered", "1");
        } else {
            String prevVal = jedis.hget("system", "totalRegistered");
            int counter = Integer.parseInt(prevVal);
            counter++;
            jedis.hset("system", "totalRegistered", Integer.toString(counter));
        }

        // Update each the learning counters.
        keys = jedis.hkeys("system:learning");
        for(String lang: learning) {
            if(keys == null || !keys.contains(lang)) {
                jedis.hset("system:learning", lang, "1");
            } else {
                String prevVal = jedis.hget("system:learning", lang);
                int counter = Integer.parseInt(prevVal);
                counter++;
                jedis.hset("system:learning", lang, Integer.toString(counter));
            }
        }

        // Update each the native counters.
        keys = jedis.hkeys("system:native");
        if(keys == null || !keys.contains(speaks)) {
            jedis.hset("system:native", speaks, "1");
        } else {
            String prevVal = jedis.hget("system:native", speaks);
            int counter = Integer.parseInt(prevVal);
            counter++;
            jedis.hset("system:native", speaks, Integer.toString(counter));
        }
        jedis.close();
    }

    @Override
    public void registerPostCreatedEvent(String userId, String language) {
        LOGGER.debug("Registering a new post created event");
        Jedis jedis = pool.getResource();

        // Update the system total registered
        Set<String> keys = jedis.hkeys("system");
        if(keys == null || !keys.contains("totalPosts")) {
            jedis.hset("system", "totalPosts", "1");
        } else {
            String prevVal = jedis.hget("system", "totalPosts");
            int counter = Integer.parseInt(prevVal);
            counter++;
            jedis.hset("system", "totalPosts", Integer.toString(counter));
        }

        // Update by post language system counters.
        keys = jedis.hkeys("system:posts");
        if(keys == null || !keys.contains(language)) {
            jedis.hset("system:posts", language, "1");
        } else {
            String prevVal = jedis.hget("system:posts", language);
            int counter = Integer.parseInt(prevVal);
            counter++;
            jedis.hset("system:posts", language, Integer.toString(counter));
        }

        // Update user posts counters.
        keys = jedis.hkeys("user:" + userId);
        if(keys == null || !keys.contains("totalPosts")) {
            jedis.hset("user:" + userId, "totalPosts", "1");
        } else {
            String prevVal = jedis.hget("user:" + userId, "totalPosts");
            int counter = Integer.parseInt(prevVal);
            counter++;
            jedis.hset("user:" + userId, "totalPosts", Integer.toString(counter));
        }

        // Update the user posts counters by language.
        keys = jedis.hkeys("user:" + userId + ":posts");
        if(keys == null || !keys.contains(language)) {
            jedis.hset("user:" + userId + ":posts", language, "1");
        } else {
            String prevVal = jedis.hget("user:" + userId + ":posts", language);
            int counter = Integer.parseInt(prevVal);
            counter++;
            jedis.hset("user:" + userId + ":posts", language, Integer.toString(counter));
        }
        jedis.close();
    }

    @Override
    public void registerChatCreatedEvent() {
        final String chatsKey = "chats";
        LOGGER.debug("Registering a new chat created event");
        Jedis jedis = pool.getResource();
        Set<String> keys = jedis.hkeys("system");
        if(keys == null || !keys.contains(chatsKey)) {
            jedis.hset("system", chatsKey, "1");
        } else {
            String prevVal = jedis.hget("system", chatsKey);
            int counter = Integer.parseInt(prevVal);
            counter++;
            jedis.hset("system", chatsKey, Integer.toString(counter));
        }
        jedis.close();
    }

    @Override
    public void registerTranslationCreatedEvent(String userId) {
        final String translationKey = "translations";
        LOGGER.debug("Registering a new translation created event for user " + userId);
        Jedis jedis = pool.getResource();
        Set<String> keys = jedis.hkeys("user:"+userId);
        if(keys == null || !keys.contains(translationKey)) {
            jedis.hset("user:" + userId, translationKey, "1");
        } else {
            String prevVal = jedis.hget("user:" + userId, translationKey);
            int counter = Integer.parseInt(prevVal);
            counter++;
            jedis.hset("user:" + userId, translationKey, Integer.toString(counter));
        }
        jedis.close();
    }

    @Override
    public void registerTextToSpeechCreatedEvent(String userId) {
        final String textToSpeech = "textToSpeech";
        LOGGER.debug("Registering a new text to speech created event for user " + userId);
        Jedis jedis = pool.getResource();
        Set<String> keys = jedis.hkeys("user:"+userId);
        if(keys == null || !keys.contains(textToSpeech)) {
            jedis.hset("user:" + userId, textToSpeech, "1");
        } else {
            String prevVal = jedis.hget("user:" + userId, textToSpeech);
            int counter = Integer.parseInt(prevVal);
            counter++;
            jedis.hset("user:" + userId, textToSpeech, Integer.toString(counter));
        }
        jedis.close();
    }

    @Override
    public SystemStatistics getSystemStatistics() {
        LOGGER.debug("Collecting system metrics.");
        Jedis jedis = pool.getResource();
        Set<String> systemMetrics = jedis.hkeys("system");
        if(systemMetrics == null || systemMetrics.isEmpty()) {
            jedis.close();
            return new SystemStatistics(new SystemStatistics.RegisteredUsersData(), Collections.singletonMap("total", 0),0);
        } else {
            SystemStatistics ss = new SystemStatistics();

            // Set the chats
            if(systemMetrics.contains("chats")) {
                ss.setCreatedChats(Integer.parseInt(jedis.hget("system", "chats")));
            } else {
                ss.setCreatedChats(0);
            }

            // Set the registeredUsers
            SystemStatistics.RegisteredUsersData rud = new SystemStatistics.RegisteredUsersData();
            if(systemMetrics.contains("totalRegistered")) {
                rud.setTotal(Integer.parseInt(jedis.hget("system", "totalRegistered")));
            } else {
                rud.setTotal(0);
            }

            // Set the learning map for users.
            Map<String, Integer> learning = new HashMap<String, Integer>();
            for(String langCode: jedis.hkeys("system:learning")) {
                learning.put(langCode, Integer.parseInt(jedis.hget("system:learning", langCode)));
            }
            rud.setLearning(learning);

            // Set the native map for users.
            Map<String, Integer> nnative = new HashMap<String, Integer>();
            for(String langCode: jedis.hkeys("system:native")) {
                nnative.put(langCode, Integer.parseInt(jedis.hget("system:native", langCode)));
            }
            rud.setNnative(nnative);

            ss.setRegisteredUsers(rud);

            // Set the created posts map.
            Map<String, Integer> posts = new HashMap<String, Integer>();
            for(String langCode: jedis.hkeys("system:posts")) {
                posts.put(langCode, Integer.parseInt(jedis.hget("system:posts", langCode)));
            }
            if(jedis.hkeys("system:posts") != null) {
                posts.put("total", jedis.hkeys("system:posts").size());
            } else {
                posts.put("total", 0);
            }
            ss.setCreatedPosts(posts);

            jedis.close();
            return ss;
        }
    }

    @Override
    public UserStatistics getUserStatistics(String userId) {
        LOGGER.debug("Collecting metrics for user " + userId);
        Jedis jedis = pool.getResource();

        UserStatistics us = new UserStatistics();

        // Set the userId
        us.setUserId(userId);

        // Set Text to Speech made.
        Set<String> keys = jedis.hkeys("user:"+userId);
        if(keys == null || !keys.contains("textToSpeech")) {
            us.setTextToSpeechMade(0);
        } else {
            us.setTextToSpeechMade(Integer.parseInt(jedis.hget("user:"+userId, "textToSpeech")));
        }

        // Set translations made.
        keys = jedis.hkeys("user:"+userId);
        if(keys == null || !keys.contains("translations")) {
            us.setTranslationsMade(0);
        } else {
            us.setTranslationsMade(Integer.parseInt(jedis.hget("user:"+userId, "translations")));
        }

        // Set posts Map
        Map<String, Integer> posts = new HashMap<String, Integer>();
        for(String langCode: jedis.hkeys("user:"+userId +":posts")) {
            posts.put(langCode, Integer.parseInt(jedis.hget("user:"+userId +":posts", langCode)));
        }
        if(jedis.hkeys("user:"+userId +":posts") != null) {
            posts.put("total", jedis.hkeys("user:"+userId +":posts").size());
        } else {
            posts.put("total", 0);
        }
        us.setCreatedPosts(posts);

        jedis.close();
        return us;
    }
}
