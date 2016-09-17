package com.anat.employee.db.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractDAOImpl<Entity, ID extends Serializable> implements GenericDAO<Entity, ID> {

    protected Class clazz;

    @Autowired
    protected SessionFactory sessionFactory;

    public AbstractDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        clazz = (Class) pt.getActualTypeArguments()[0];
    }

    public void setEntityClass(final Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Entity findById(ID id) {
        return (Entity) getCurrentSession().get(clazz, id);
    }

    @Override
    public List findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @Override
    public List search(Map<String, Object> parameterMap) {
        Criteria criteria = getCurrentSession().createCriteria(clazz);
        Set<String> fieldName = parameterMap.keySet();
        fieldName.stream().forEach((field) -> {
            criteria.add(Restrictions.ilike(field, parameterMap.get(field)));
        });
        return criteria.list();
    }

    @Override
    public ID insert(Entity entity) {
        return (ID) getCurrentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(Entity entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void update(Entity entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public void delete(Entity entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        delete(findById(id));
    }

    protected DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(clazz);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}

