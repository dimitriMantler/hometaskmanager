package de.martin701.hometaskmanager.dto;

import de.martin701.hometaskmanager.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import de.martin701.hometaskmanager.entities.GeneralEntity;
import de.martin701.hometaskmanager.interfaces.DtoInterface;
import de.martin701.hometaskmanager.models.GeneralModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GeneralDto<E extends GeneralEntity, M extends GeneralModel> implements DtoInterface<E, M> {

    public enum Action {
        CREATE, CHANGE
    }

    public <E extends GeneralEntity, M extends GeneralModel> List<E> writeEntities(List<M> models, EntityConverter<E, M> converter) {
        return models.stream().map(converter::toEntity).collect(Collectors.toList());
    }

    public <E extends GeneralEntity, M extends GeneralModel> List<M> writeModels(List<E> entities, Action action, EntityConverter<E, M> converter) {
        return entities.stream().map(entity -> writeModel(entity, action, converter)).collect(Collectors.toList());
    }

    public <E extends GeneralEntity, M extends GeneralModel> E writeEntity(M model, EntityConverter<E, M> converter) {
        E entity = converter.toEntity(model);
        return entity;
    }

    public <E extends GeneralEntity, M extends GeneralModel> M writeModel(E entity, Action action, EntityConverter<E, M> converter) {
        M model = converter.toModel(entity);
        switch (action) {
            case CREATE -> {
                model.setCreateDate(LocalDateTime.now());
                model.setCreateUserId(getCurrentUserId());
                model.setCreateUserName(getCurrentUserName());
            }
            case CHANGE -> {
                model.setChangeDate(LocalDateTime.now());
                model.setChangeUserId(getCurrentUserId());
                model.setChangeUserName(getCurrentUserName());
            }
        }
        return model;
    }

    public interface EntityConverter<E, M> {
        E toEntity(M model);
        M toModel(E entity);
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return "unknown";
    }

    private int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return ((CustomUserDetails) userDetails).getId();
        }
        return 0;
    }

}