package de.martin701.hometaskmanager.dto;

import de.martin701.hometaskmanager.models.GeneralModel;

public class GeneralDto<E, M extends GeneralModel> {

    public enum Action {
        CREATE, CHANGE
    }
}
