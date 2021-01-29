package com.opensource.stage_monitor.stage_monitor_manager;

import com.opensource.stage_monitor.stage_manager.Stage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public interface StageMonitorManager {
    StageMonitor initiateFor(String userId, String appName);

    StageMonitor proceed(String userId, String appName, Stage stage);

    Optional<StageMonitor> getByUserIdAndAppName(String userId, String appName);

    StageMonitor execute(StageMonitor stageMonitor, Stage stage, String cmd);
}
