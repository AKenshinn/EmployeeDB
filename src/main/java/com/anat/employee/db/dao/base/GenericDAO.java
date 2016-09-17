package com.anat.employee.db.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAO<Entity, ID extends Serializable> {

    Entity findById(ID id);

    List<Entity> findAll();

    List<Entity> search(Map<String, Object> parameterMap);

    ID insert(Entity entity);

    void update(Entity entity);

    void delete(Entity entity);

    void deleteById(ID id);
    
    void saveOrUpdate(Entity entity);

}