package com.opensource.stage_monitor.stage_monitor_manager;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StageMonitorRepositoryTest {
    @Autowired
    StageMonitorRepository stageMonitorRepository;

    @Test
    void findByUserIdAndAppName() {
        Optional<StageMonitor> all = stageMonitorRepository.findByUserIdAndAppName("001", "resume_application");
        System.out.println(all);
    }
}