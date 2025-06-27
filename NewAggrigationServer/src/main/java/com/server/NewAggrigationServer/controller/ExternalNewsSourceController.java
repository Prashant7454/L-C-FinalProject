package com.server.NewAggrigationServer.controller;

import com.server.NewAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewAggrigationServer.dto.NotificationConfigDTO;
import com.server.NewAggrigationServer.service.ExternalNewsSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/external")
public class ExternalNewsSourceController {

    @Autowired
    private ExternalNewsSourceService externalNewsSourceService;

    @GetMapping("/source")
    public List<ExternalNewsSourceDTO> getAll() {
        return externalNewsSourceService.getAll();
    }

    @PostMapping("/updatesource")
    public ExternalNewsSourceDTO getExternalSourceById(@RequestBody ExternalNewsSourceDTO dto){
        return externalNewsSourceService.updateExternalNewsSource(dto.getId(),dto);
    }
}
