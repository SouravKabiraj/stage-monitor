package com.opensource.stage_monitor.stage_manager;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StageRepository<T> extends MongoRepository<Stage<T>, String> {
}
