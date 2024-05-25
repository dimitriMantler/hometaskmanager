package de.martin701.hometaskmanager.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class GeneralModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "create_user_name")
    private String createUserName;

    @Column(name = "create_user_id")
    private Integer createUserId;

    @Column(name = "change_date")
    private LocalDateTime changeDate;

    @Column(name = "change_user_name")
    private String changeUserName;

    @Column(name = "change_user_id")
    private Integer changeUserId;
}
