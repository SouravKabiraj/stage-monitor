package com.opensource.stage_monitor.stage_monitor_manager;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageMonitorRepository extends MongoRepository<StageMonitor, String> {
    Optional<StageMonitor> findByUserIdAndAppName(String userId, String appName);
}
