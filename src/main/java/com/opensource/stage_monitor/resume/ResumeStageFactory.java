package com.opensource.stage_monitor.resume;

import com.opensource.stage_monitor.resume.contact_details.ContactDetailsStageManager;
import com.opensource.stage_monitor.resume.education_details.EducationDetailsStageManager;
import com.opensource.stage_monitor.resume.personal_detrails.PersonalDetailsStageManager;
import com.opensource.stage_monitor.resume.personal_project.PersonalProjectStageManager;
import com.opensource.stage_monitor.resume.professional_details.ProfessionalDetailsStageManager;
import com.opensource.stage_monitor.stage_manager.StageManager;
import com.opensource.stage_monitor.stage_manager.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ResumeStageFactory {
    @Autowired
    StageRepository stageRepository;
    @Autowired
    PersonalDetailsStageManager personalDetailsStageManager;
    @Autowired
    ContactDetailsStageManager contactDetailsStageManager;
    @Autowired
    EducationDetailsStageManager educationDetailsStageManager;
    @Autowired
    ProfessionalDetailsStageManager professionalDetailsStageManager;
    @Autowired
    PersonalProjectStageManager personalProjectStageManager;

    Map<String, StageManager> nameStage;

    @PostConstruct
    public void init() {
        nameStage = new HashMap<>();
        nameStage.put("personal_details", personalDetailsStageManager);
        nameStage.put("contact_details", contactDetailsStageManager);
        nameStage.put("education_details", educationDetailsStageManager);
        nameStage.put("professional_details", professionalDetailsStageManager);
        nameStage.put("personal_project", personalProjectStageManager);
    }

    public StageManager getStageManagerByName(String name) {
        return nameStage.get(name);
    }
}
