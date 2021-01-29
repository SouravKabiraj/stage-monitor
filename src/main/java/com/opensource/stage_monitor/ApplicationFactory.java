package com.opensource.stage_monitor;

import com.opensource.stage_monitor.resume.ResumeManager;
import com.opensource.stage_monitor.resume.ResumeStageFactory;
import com.opensource.stage_monitor.stage_monitor_manager.StageMonitorManager;
import com.opensource.stage_monitor.stage_monitor_manager.StageMonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ApplicationFactory {
    @Autowired
    ResumeManager resumeManager;

    Map<String, StageMonitorManager> nameStageMonitorManager;

    @PostConstruct
    public void init() {
        this.nameStageMonitorManager = new HashMap<>();
        nameStageMonitorManager.put("resume_application", resumeManager);
    }

    public StageMonitorManager getStageMonitorByName(String name) {
        return nameStageMonitorManager.get(name);
    }
}
