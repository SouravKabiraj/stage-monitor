package com.opensource.stage_monitor.stage_manager;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@Document
public class Stage<T> {
    @Id
    private String id;
    private String name;
    private T data;
    private String previousStageId;
    private String nextStageId;
    private Date createdAt;
    private Date updatedAt;

    public Stage(String name, String previousStageId) {
        this.name = name;
        this.previousStageId = previousStageId;
    }
}
