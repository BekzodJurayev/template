package uz.furor.template.repository.core;

import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;
import java.util.Map;

public interface BaseRepository<T> {
    T selectOne(Integer id) throws PersistenceException;
    List<T> selectList(Map<String, Object> params) throws PersistenceException;
    List<T> selectListAll(Map<String, Object> params) throws PersistenceException;
    void insert(T bean)  throws PersistenceException;
    void update(T bean)  throws PersistenceException;
    void delete(Integer id)  throws PersistenceException;
}
