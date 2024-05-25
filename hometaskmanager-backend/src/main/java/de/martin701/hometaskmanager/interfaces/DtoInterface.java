package de.martin701.hometaskmanager.interfaces;

import de.martin701.hometaskmanager.dto.GeneralDto;

import java.util.List;

public interface DtoInterface<E, M> {
    List<E> writeEntities(List<M> models);
    List<M> writeModels(List<E> entities, GeneralDto.Action action);
    E writeEntity(M model);
    M writeModel(E entity, GeneralDto.Action action);
    E updateEntityValues(E entity, E newEntityValues);
}