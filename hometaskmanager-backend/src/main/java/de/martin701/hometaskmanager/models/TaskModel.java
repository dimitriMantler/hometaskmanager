package de.martin701.hometaskmanager.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "task")
public class TaskModel extends GeneralModel{

    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "priority")
    private String priority;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
}
