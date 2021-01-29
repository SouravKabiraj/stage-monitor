package com.opensource.stage_monitor.resume.contact_details;

import com.opensource.stage_monitor.resume.ResumeStageFactory;
import com.opensource.stage_monitor.resume.education_details.EducationDetailsStageManager;
import com.opensource.stage_monitor.resume.personal_detrails.PersonalDetails;
import com.opensource.stage_monitor.stage_manager.Stage;
import com.opensource.stage_monitor.stage_manager.StageManager;
import com.opensource.stage_monitor.stage_manager.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class ContactDetailsStageManager implements StageManager<ContactDetails> {
    @Autowired
    StageRepository<ContactDetails> stageRepository;

    @Autowired
    EducationDetailsStageManager educationDetailsManager;

    @Override
    public Stage<ContactDetails> initiate() {
        return stageRepository.save(new Stage<ContactDetails>("contact_details", null));
    }

    @Override
    public Optional<Stage<ContactDetails>> getStage(String id) {
        return stageRepository.findById(id);
    }

    @Override
    public Stage<ContactDetails> submit(Stage<ContactDetails> contactDetailsStage) {
        if (contactDetailsStage.getNextStageId() == null) {
            Stage educationDetailsStage = educationDetailsManager.initiate();
            contactDetailsStage.setNextStageId(educationDetailsStage.getId());
        }
        Stage<ContactDetails> savedPersonalDetailsStage = stageRepository.save(contactDetailsStage);
        return savedPersonalDetailsStage;
    }

    @Override
    public Stage<ContactDetails> execute(String cmd, Stage<ContactDetails> stage) {
        return null;
    }
}
