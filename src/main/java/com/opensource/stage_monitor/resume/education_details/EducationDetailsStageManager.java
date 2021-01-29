package com.opensource.stage_monitor.resume.education_details;

import com.opensource.stage_monitor.resume.ResumeStageFactory;
import com.opensource.stage_monitor.resume.contact_details.ContactDetails;
import com.opensource.stage_monitor.resume.professional_details.ProfessionalDetailsStageManager;
import com.opensource.stage_monitor.stage_manager.Stage;
import com.opensource.stage_monitor.stage_manager.StageManager;
import com.opensource.stage_monitor.stage_manager.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class EducationDetailsStageManager implements StageManager<EducationDetails> {
    @Autowired
    StageRepository<EducationDetails> stageRepository;

    @Autowired
    ProfessionalDetailsStageManager professionalDetailsStageManager;

    @Override
    public Stage<EducationDetails> initiate() {
        return stageRepository.save(new Stage<EducationDetails>("education_details", null));
    }

    @Override
    public Optional<Stage<EducationDetails>> getStage(String id) {
        return stageRepository.findById(id);
    }

    @Override
    public Stage<EducationDetails> submit(Stage<EducationDetails> educationDetailsStage) {
        if (educationDetailsStage.getNextStageId()== null) {
            Stage professionalDetailsStage = professionalDetailsStageManager.initiate();
            educationDetailsStage.setNextStageId(professionalDetailsStage.getId());
        }
        Stage<EducationDetails> savedPersonalDetailsStage = stageRepository.save(educationDetailsStage);
        return savedPersonalDetailsStage;
    }

    @Override
    public Stage<EducationDetails> execute(String cmd, Stage<EducationDetails> stage) {
        return null;
    }
}
