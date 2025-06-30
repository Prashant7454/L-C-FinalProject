package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewsAggrigationServer.service.ExternalNewsSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/external")
public class ExternalNewsSourceController {

    @Autowired
    private ExternalNewsSourceService externalNewsSourceService;

    @GetMapping("/{id}")
    public ExternalNewsSourceDTO getExternalSourceById(@PathVariable Integer id) {
        return externalNewsSourceService.getExternalSourceById(id);
    }

    @GetMapping("/source")
    public List<ExternalNewsSourceDTO> getAll() {
        return externalNewsSourceService.getAll();
    }

    @PutMapping("/updatesource")
    public ExternalNewsSourceDTO getExternalSourceById(@RequestBody ExternalNewsSourceDTO dto){
        return externalNewsSourceService.updateExternalNewsSource(dto.getId(),dto);
    }
}
