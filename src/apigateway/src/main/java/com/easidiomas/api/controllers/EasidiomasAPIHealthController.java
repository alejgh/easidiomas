package com.easidiomas.api.controllers;

import com.easidiomas.api.EasidiomasAPIGateway;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "api/health")
public class EasidiomasAPIHealthController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity index() {
        APIStatus status = new APIStatus(EasidiomasAPIGateway.PORT, "Up and running! Listening on port [" + EasidiomasAPIGateway.PORT + "].");
        return ResponseEntity.ok().body(new Gson().toJson(status));
    }

    private class APIStatus {
        private int port;
        private String status;

        public APIStatus() {
        }

        public APIStatus(int port, String status) {
            this.port = port;
            this.status = status;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            APIStatus apiStatus = (APIStatus) o;
            return port == apiStatus.port &&
                    Objects.equals(status, apiStatus.status);
        }

        @Override
        public int hashCode() {
            return Objects.hash(port, status);
        }

        @Override
        public String toString() {
            return "APIStatus{" +
                    "port=" + port +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}
