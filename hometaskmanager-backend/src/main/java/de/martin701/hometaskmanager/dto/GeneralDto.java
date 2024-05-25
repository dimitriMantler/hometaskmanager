package de.martin701.hometaskmanager.dto;

import de.martin701.hometaskmanager.entities.GeneralEntity;
import de.martin701.hometaskmanager.models.GeneralModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GeneralDto<E extends GeneralEntity, M extends GeneralModel> {

    public enum Action {
        CREATE, CHANGE
    }

    public static <E extends GeneralEntity, M extends GeneralModel> List<E> writeEntities(List<M> models, EntityConverter<E, M> converter) {
        return models.stream().map(converter::toEntity).collect(Collectors.toList());
    }

    public static <E extends GeneralEntity, M extends GeneralModel> List<M> writeModels(List<E> entities, Action action, EntityConverter<E, M> converter) {
        return entities.stream().map(entity -> writeModel(entity, action, converter)).collect(Collectors.toList());
    }

    public static <E extends GeneralEntity, M extends GeneralModel> E writeEntity(M model, EntityConverter<E, M> converter) {
        E entity = converter.toEntity(model);
        entity.setCreateDate(model.getCreateDate());
        entity.setCreateUserId(model.getCreateUserId());
        entity.setCreateUserName(model.getCreateUserName());
        entity.setChangeDate(model.getChangeDate());
        entity.setChangeUserId(model.getChangeUserId());
        entity.setChangeUserName(model.getChangeUserName());
        return entity;
    }

    //TODO set userId and UserName if available
    public static <E extends GeneralEntity, M extends GeneralModel> M writeModel(E entity, Action action, EntityConverter<E, M> converter) {
        M model = converter.toModel(entity);
        switch (action) {
            case CREATE -> {
                model.setCreateDate(LocalDateTime.now());
                model.setCreateUserId(0);
                model.setCreateUserName("DM");
            }
            case CHANGE -> {
                model.setChangeDate(LocalDateTime.now());
                model.setChangeUserId(0);
                model.setChangeUserName("DM");
            }
        }
        return model;
    }

    public interface EntityConverter<E, M> {
        E toEntity(M model);
        M toModel(E entity);
    }
}
