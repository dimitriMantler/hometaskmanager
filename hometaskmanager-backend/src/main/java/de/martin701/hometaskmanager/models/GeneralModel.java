package de.martin701.hometaskmanager.models;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public abstract class GeneralModel {
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "create_user_name")
    private String createUserName;

    @Column(name = "create_user_id")
    private Long createUserId;

    @Column(name = "change_date")
    private LocalDateTime changeDate;

    @Column(name = "change_user_name")
    private String changeUserName;

    @Column(name = "change_user_id")
    private Long changeUserId;
}
