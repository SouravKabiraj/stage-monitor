package com.opensource.stage_monitor.resume.professional_details;

import com.opensource.stage_monitor.resume.ResumeStageFactory;
import com.opensource.stage_monitor.resume.contact_details.ContactDetails;
import com.opensource.stage_monitor.resume.personal_detrails.PersonalDetailsStageManager;
import com.opensource.stage_monitor.resume.personal_project.PersonalProjectStageManager;
import com.opensource.stage_monitor.stage_manager.Stage;
import com.opensource.stage_monitor.stage_manager.StageManager;
import com.opensource.stage_monitor.stage_manager.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class ProfessionalDetailsStageManager implements StageManager<ProfessionalDetails> {
    @Autowired
    StageRepository<ProfessionalDetails> stageRepository;

    @Autowired
    PersonalProjectStageManager personalProjectStageManager;

    @Override
    public Stage<ProfessionalDetails> initiate() {
        return stageRepository.save(new Stage<ProfessionalDetails>("professional_details", null));
    }

    @Override
    public Optional<Stage<ProfessionalDetails>> getStage(String id) {
        return stageRepository.findById(id);
    }

    @Override
    public Stage<ProfessionalDetails> submit(Stage<ProfessionalDetails> professionalDetailsStage) {
        if (professionalDetailsStage.getNextStageId()== null) {
            Stage personalProjectStage = personalProjectStageManager.initiate();
            professionalDetailsStage.setNextStageId(personalProjectStage.getId());
        }
        Stage<ProfessionalDetails> savedPersonalDetailsStage = stageRepository.save(professionalDetailsStage);
        return savedPersonalDetailsStage;
    }

    @Override
    public Stage<ProfessionalDetails> execute(String cmd, Stage<ProfessionalDetails> stage) {
        return null;
    }
}
