package com.opensource.stage_monitor.resume.personal_detrails;

import com.opensource.stage_monitor.resume.ResumeStageFactory;
import com.opensource.stage_monitor.resume.contact_details.ContactDetails;
import com.opensource.stage_monitor.resume.contact_details.ContactDetailsStageManager;
import com.opensource.stage_monitor.stage_manager.Stage;
import com.opensource.stage_monitor.stage_manager.StageManager;
import com.opensource.stage_monitor.stage_manager.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class PersonalDetailsStageManager implements StageManager<PersonalDetails> {
    @Autowired
    StageRepository<PersonalDetails> stageRepository;

    @Autowired
    ContactDetailsStageManager contactDetailsStageManager;

    @Override
    public Stage<PersonalDetails> initiate() {
        return stageRepository.save(new Stage<PersonalDetails>("personal_details", null));
    }

    @Override
    public Optional<Stage<PersonalDetails>> getStage(String id) {
        return stageRepository.findById(id);
    }

    @Override
    public Stage<PersonalDetails> submit(Stage<PersonalDetails> personalDetailsStage) {
        if (personalDetailsStage.getNextStageId() == null) {
            Stage contactDetailsStage = contactDetailsStageManager.initiate();
            personalDetailsStage.setNextStageId(contactDetailsStage.getId());
        }
        Stage<PersonalDetails> savedPersonalDetailsStage = stageRepository.save(personalDetailsStage);
        return savedPersonalDetailsStage;
    }

    @Override
    public Stage<PersonalDetails> execute(String cmd, Stage<PersonalDetails> stage) {
        return null;
    }
}
