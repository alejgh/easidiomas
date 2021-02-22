package com.easidiomas.auth;

import com.easidiomas.auth.encryption.AESDecryptor;
import com.easidiomas.auth.encryption.AESEncryptor;
import com.google.gson.Gson;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This implementation extends the one generated by gRPC. It overrides the two
 * declared proto service methods and implementes de desired functionality.
 */
public class AuthServiceImpl extends AuthenticationServiceGrpc.AuthenticationServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
    private static final long TOKEN_LIVE_TIME_MS = Long.parseLong(System.getProperty("TOKEN_LIVE_TIME_MS", "600000"));

    @Override
    public void generateToken(Authservice.LoginInfo request, StreamObserver<Authservice.Token> responseObserver) {
        final String username = request.getUsername();
        final String password = request.getPassword();
        LOGGER.debug(String.format("User [%s] request a new token.", username));

        // 1. Look for the combination of user and password in the users service.
        // TO-DO

        // 2. If the user exists, generate a passport for it with its data.
        String expirationDate = Long.toString(System.currentTimeMillis() + TOKEN_LIVE_TIME_MS);
        Authservice.Passport passport = Authservice.Passport.newBuilder().setUserId("0").setUsername(username)
                .setName("Willy").setSurname("Facundo Colunga")
                .setUserProfilePicUrl("https://content.easidiomas/static/willy.png").setExpirationDate(expirationDate)
                .build();

        LOGGER.debug(String.format("Passport generated for user [%s]: %s", username, passport));

        // 3. Convert the passport to JSON object.
        String jsonPassport = "";

        try {
            Gson gson = new Gson();
            jsonPassport = gson.toJson(passport);
        } catch (Exception e) {
            LOGGER.error("Error while serializing passport to JSON object: " + e.toString());
            responseObserver.onError(e);
            return;
        }

        // 4. Cypher the passport with AES.
        String tokenString = AESEncryptor.encrypt(jsonPassport);
        // 5. Create a token from the cyphered json.
        Authservice.Token token = Authservice.Token.newBuilder().setToken(tokenString).build();
        // 6. Return the created token.
        responseObserver.onNext(token);
        responseObserver.onCompleted();
    }

    @Override
    public void generatePassport(Authservice.Token request, StreamObserver<Authservice.Passport> responseObserver) {
        String tokenString = request.getToken();

        // 1. Decrypt the token.
        String jsonToken = AESDecryptor.decrypt(tokenString);

        // 2. Convert to passport.
        Authservice.Passport passport = null;
        try {
            Gson gson = new Gson();
            passport = gson.fromJson(jsonToken, Authservice.Passport.class);
        } catch (Exception e) {
            LOGGER.error("Error while deserializing the jsonToken: " + e.toString());
            responseObserver.onError(e);
            responseObserver.onCompleted();
            return;
        }

        // 3. Check the expirationDate.
        Long expirationDate = null;
        try {
            expirationDate = Long.parseLong(passport.getExpirationDate());
        } catch (Exception e) {
            LOGGER.error("Error while deserializing the passport date: " + e.getStackTrace());
            responseObserver.onError(e);
            responseObserver.onCompleted();
            return;
        }

        if (expirationDate < System.currentTimeMillis()) {
            // The passport is no longer valid.
            LOGGER.info("Invalid passport detected");
            responseObserver.onCompleted();
            return;
        }

        // 4. Return passport.
        responseObserver.onNext(passport);
        responseObserver.onCompleted();
    }
}
