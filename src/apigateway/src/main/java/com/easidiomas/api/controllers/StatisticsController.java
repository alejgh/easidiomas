package com.easidiomas.api.controllers;

import com.easidiomas.api.clients.statisticsservice.IStatisticsService;
import com.easidiomas.api.clients.statisticsservice.IStatisticsServiceService;
import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping()
public class StatisticsController extends EasidiomasAPIController {

    private static final String STATS_SERVICE_WDSL = System.getenv("STATS_SERVICE_WDSL")!=null ? System.getenv("STATS_SERVICE_WDSL"): "http://localhost:5000/soapws/statistics?wsdl";
    private static final int STATS_SERVICE_PORT = Integer.parseInt(System.getenv("STATS_SERVICE_HOST")!=null ? System.getenv("STATS_SERVICE_HOST"): "5000");

    @GetMapping(value = "api/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSystemStatistics(HttpServletRequest request) throws MalformedURLException {
        IStatisticsServiceService service = new IStatisticsServiceService(new URL(STATS_SERVICE_WDSL));
        IStatisticsService statisticsService = service.getIStatisticsServicePort();
        System.out.print(statisticsService.getSystemStatistics());
        return ResponseEntity.ok(new Gson().toJson(statisticsService.getSystemStatistics()));
    }

    @GetMapping(value = "api/users/{id}/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserStatistics(HttpServletRequest request, @PathVariable String id) throws MalformedURLException {
        IStatisticsServiceService service = new IStatisticsServiceService(new URL(STATS_SERVICE_WDSL));
        IStatisticsService statisticsService = service.getIStatisticsServicePort();
        System.out.print(statisticsService.getSystemStatistics());
        return ResponseEntity.ok(new Gson().toJson(statisticsService.getUserStatistics(id)));
    }
}
