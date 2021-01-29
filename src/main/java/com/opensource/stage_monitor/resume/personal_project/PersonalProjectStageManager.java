package com.opensource.stage_monitor.resume.personal_project;

import com.opensource.stage_monitor.resume.ResumeStageFactory;
import com.opensource.stage_monitor.resume.contact_details.ContactDetails;
import com.opensource.stage_monitor.stage_manager.Stage;
import com.opensource.stage_monitor.stage_manager.StageManager;
import com.opensource.stage_monitor.stage_manager.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class PersonalProjectStageManager implements StageManager<PersonalProject> {
    @Autowired
    StageRepository<PersonalProject> stageRepository;

    @Override
    public Stage<PersonalProject> initiate() {
        return stageRepository.save(new Stage<PersonalProject>("personal_project", null));
    }

    @Override
    public Optional<Stage<PersonalProject>> getStage(String id) {
        return stageRepository.findById(id);
    }

    @Override
    public Stage<PersonalProject> submit(Stage<PersonalProject> personalProjectStage) {
        Stage<PersonalProject> savedPersonalDetailsStage = stageRepository.save(personalProjectStage);
        return savedPersonalDetailsStage;
    }

    @Override
    public Stage<PersonalProject> execute(String cmd, Stage<PersonalProject> stage) {
        return null;
    }
}
