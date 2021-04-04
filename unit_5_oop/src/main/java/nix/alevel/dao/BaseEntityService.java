package nix.alevel.dao;

import nix.alevel.entity.BaseEntity;

import java.util.List;

public interface BaseEntityService<T extends BaseEntity> {
    void create(T entity);
    List<T> readAll();
    void update(String id);
    void delete(String id);
    T getById(String id);
}
