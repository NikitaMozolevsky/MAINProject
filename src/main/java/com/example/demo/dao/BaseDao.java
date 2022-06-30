package com.example.demo.dao;

import com.example.demo.entity.AbstractEntity;
import com.example.demo.exception.DaoException;

import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T extends AbstractEntity> {

    public abstract boolean delete(T t) throws DaoException;
    public abstract List<Optional<T>> findAll() throws DaoException;
    public abstract T update(T t) throws DaoException;

}
