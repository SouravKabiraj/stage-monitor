package com.opensource.stage_monitor.stage_manager;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public interface StageManager<T> {
    Stage<T> initiate();

    Optional<Stage<T>> getStage(String id);

    Stage<T> submit(Stage<T> stage);

    Stage<T> execute(String cmd, Stage<T> stage);
}
