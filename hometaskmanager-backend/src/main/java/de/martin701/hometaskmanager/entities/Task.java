package de.martin701.hometaskmanager.entities;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Task {
    private int id;
    private Integer listId;
    private Integer projectId;
    private String name;
    private String description;
    private String priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}