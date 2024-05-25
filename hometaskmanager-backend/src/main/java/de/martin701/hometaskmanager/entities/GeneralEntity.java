package de.martin701.hometaskmanager.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class GeneralEntity {
    private LocalDateTime createDate;
    private String createUserName;
    private Integer createUserId;
    private LocalDateTime changeDate;
    private String changeUserName;
    private Integer changeUserId;
}
