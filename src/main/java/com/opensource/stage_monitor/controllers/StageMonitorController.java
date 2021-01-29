package com.opensource.stage_monitor.controllers;

import com.opensource.stage_monitor.ApplicationFactory;
import com.opensource.stage_monitor.stage_manager.Stage;
import com.opensource.stage_monitor.stage_monitor_manager.StageMonitor;
import com.opensource.stage_monitor.stage_monitor_manager.StageMonitorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api")
public class StageMonitorController {
    @Autowired
    private ApplicationFactory applicationFactory;

    @GetMapping("/{appName}")
    public ResponseEntity<StageMonitor> getByUserIdAndAppName(@RequestHeader String userId, @PathVariable String appName) {
        StageMonitorManager stageMonitorManager = applicationFactory.getStageMonitorByName(appName);
        Optional<StageMonitor> optionalStageMonitor = stageMonitorManager.getByUserIdAndAppName(userId, appName);
        return new ResponseEntity<StageMonitor>(optionalStageMonitor.orElse(null), HttpStatus.OK);
    }

    @PostMapping("/{appName}/{stageName}/submit")
    public ResponseEntity<StageMonitor> submit(@RequestHeader String userId, @PathVariable String appName, @PathVariable String stageName, @RequestBody Stage stage) {
        StageMonitorManager stageMonitorManager = applicationFactory.getStageMonitorByName(appName);
        return new ResponseEntity<>(stageMonitorManager.proceed(userId, appName, stage), HttpStatus.OK);
    }

    @PostMapping("/{appName}")
    public ResponseEntity<StageMonitor> init(@RequestHeader String userId, @PathVariable String appName) {
        StageMonitorManager stageMonitorManager = applicationFactory.getStageMonitorByName(appName);
        return new ResponseEntity<>(stageMonitorManager.initiateFor(userId, appName), HttpStatus.OK);
    }
}
