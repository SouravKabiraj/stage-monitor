package com.opensource.stage_monitor.resume;

import com.opensource.stage_monitor.stage_manager.Stage;
import com.opensource.stage_monitor.stage_manager.StageManager;
import com.opensource.stage_monitor.stage_monitor_manager.StageMonitor;
import com.opensource.stage_monitor.stage_monitor_manager.StageMonitorManager;
import com.opensource.stage_monitor.stage_monitor_manager.StageMonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ResumeManager implements StageMonitorManager {
    @Autowired
    StageMonitorRepository stageMonitorRepository;

    @Autowired
    ResumeStageFactory resumeStageFactory;

    @Override
    public StageMonitor initiateFor(String userId, String appName) {
        StageMonitor stageMonitor = new StageMonitor(userId, appName);
        StageManager personalDetailsStageManager = resumeStageFactory.getStageManagerByName("personal_details");
        Stage personalDetailsStage = personalDetailsStageManager.initiate();
        stageMonitor.setCurrentActiveStageId(personalDetailsStage.getId());
        stageMonitor.addStage(personalDetailsStage);
        return stageMonitorRepository.save(stageMonitor);
    }

    @Override
    public StageMonitor proceed(String userId, String appName, Stage stage) {
        StageManager stageManager = resumeStageFactory.getStageManagerByName(stage.getName());
        Stage submittedStage = stageManager.submit(stage);
        StageMonitor fetchedStageMonitor = stageMonitorRepository.findByUserIdAndAppName(userId, appName).get();
        fetchedStageMonitor.updateStage(submittedStage);
        if (submittedStage.getNextStageId() != null) {
            Optional<Stage> newlyCreatedStage = stageManager.getStage(submittedStage.getNextStageId());
            if (newlyCreatedStage.isPresent()) {
                Stage newStage = newlyCreatedStage.get();
                newStage.setPreviousStageId(fetchedStageMonitor.getLastStageId());
                fetchedStageMonitor.setCurrentActiveStageId(newStage.getId());
                fetchedStageMonitor.addStage(newStage);
            }
        }
        return stageMonitorRepository.save(fetchedStageMonitor);
    }

    @Override
    public Optional<StageMonitor> getByUserIdAndAppName(String userId, String appName) {
        Optional<StageMonitor> stageMonitorList = stageMonitorRepository.findByUserIdAndAppName(userId, appName);
        return stageMonitorList.isEmpty() ? Optional.empty() : Optional.of(stageMonitorList.get());
    }

    @Override
    public StageMonitor execute(StageMonitor stageMonitor, Stage stage, String cmd) {
        StageManager stageManager = resumeStageFactory.getStageManagerByName(stage.getName());
        stageManager.execute(cmd, stage);
        return stageMonitor;
    }
}
