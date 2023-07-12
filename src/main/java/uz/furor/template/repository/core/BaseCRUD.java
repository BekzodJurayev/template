package uz.furor.template.repository.core;

import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;
import java.util.Map;

public interface BaseCRUD {
    <T> T getOne(String sql, Map<String, Object> params) throws PersistenceException;
    <T> T getOne(String sql) throws PersistenceException;

    <T> List<T> getList(String sql, Map<String, Object> params) throws PersistenceException;

    <T> List<T> getListAll(String sql, Map<String, Object> params) throws PersistenceException;
    <T> List<T> getListAll(String sql) throws PersistenceException;

    <T> int insert(String sqlInsert, T obj) throws PersistenceException;

    <T> int update(String sqlUpdate, T obj) throws PersistenceException;

    <T> int delete(String sqlDelete, Integer id) throws PersistenceException;
    void procedureCall(String sql, Map<String, Object> map) throws PersistenceException;
}
