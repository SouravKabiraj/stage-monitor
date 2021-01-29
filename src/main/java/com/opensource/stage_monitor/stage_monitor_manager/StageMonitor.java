package com.opensource.stage_monitor.stage_monitor_manager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document
public class StageMonitor {
    @Id
    public String id;
    public String userId;
    public String appName;
    public String currentActiveStageId;
    public List<Stage> stages;

    public StageMonitor(String userId, String appName) {
        this.userId = userId;
        this.appName = appName;
        stages = new ArrayList<>();
    }

    public void updateStage(com.opensource.stage_monitor.stage_manager.Stage submittedStage) {
        for (Stage s : stages) {
            if (submittedStage.getId().equals(s.getId())) {
                s.setName(submittedStage.getName());
                s.setPreviousStageId(submittedStage.getPreviousStageId());
                s.setNextStageId(submittedStage.getNextStageId());
            }
        }
    }

    public void addStage(com.opensource.stage_monitor.stage_manager.Stage stage) {
        Stage newlyCreatedStage = new Stage(stage);
        stages.add(newlyCreatedStage);
    }

    public String getLastStageId() {
        int size = stages.size();
        return stages.get(size - 1).getId();
    }
}

@Getter
@Setter
@NoArgsConstructor
class Stage {
    private String id;
    private String name;
    private String previousStageId;
    private String nextStageId;

    public Stage(com.opensource.stage_monitor.stage_manager.Stage submittedStage) {
        id = submittedStage.getId();
        name = submittedStage.getName();
        previousStageId = submittedStage.getPreviousStageId();
        nextStageId = submittedStage.getNextStageId();
    }
}
