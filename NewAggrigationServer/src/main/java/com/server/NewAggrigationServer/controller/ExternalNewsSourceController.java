package com.server.NewAggrigationServer.controller;

import com.server.NewAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewAggrigationServer.service.ExternalNewsSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/external")
public class ExternalNewsSourceController {

    @Autowired
    private ExternalNewsSourceService externalNewsSourceService;

    @GetMapping("/source/{sourceName}")
    public List<ExternalNewsSourceDTO> getConfigByUser(@PathVariable String sourceName) {
        return externalNewsSourceService.getNewsSourceBySourceName(sourceName);
    }
}
