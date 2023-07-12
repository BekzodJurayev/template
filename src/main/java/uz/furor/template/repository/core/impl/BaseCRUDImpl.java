package uz.furor.template.repository.core.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import uz.furor.template.repository.core.BaseCRUD;
import uz.furor.template.utils.Utils;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BaseCRUDImpl implements BaseCRUD {

    private final SqlSessionFactory sessionFactory;

    @Override
    public <T> T getOne(String sql, Map<String, Object> params) throws PersistenceException {
        if (StringUtils.isBlank(sql))
            throw new PersistenceException();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            return sqlSession.selectOne(sql, params);
        }
    }

    @Override
    public <T> T getOne(String sql) throws PersistenceException {
        if (StringUtils.isBlank(sql))
            throw new PersistenceException();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            return sqlSession.selectOne(sql);
        }
    }

    @Override
    public <T> List<T> getList(String sql, Map<String, Object> params) throws PersistenceException {
        if (StringUtils.isBlank(sql))
            throw new PersistenceException();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            return sqlSession.selectList(sql, params, Utils.parseRowBounds(params));
        }
    }

    @Override
    public <T> List<T> getListAll(String sql, Map<String, Object> params) throws PersistenceException {
        if (StringUtils.isBlank(sql))
            throw new PersistenceException();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            return sqlSession.selectList(sql, params);
        }
    }

    @Override
    public <T> List<T> getListAll(String sql) throws PersistenceException {
        if (StringUtils.isBlank(sql))
            throw new PersistenceException();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            return sqlSession.selectList(sql);
        }
    }

    @Override
    public <T> int insert(String sqlInsert, T obj) throws PersistenceException {
        if (StringUtils.isBlank(sqlInsert))
            throw new PersistenceException();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            return sqlSession.insert(sqlInsert, obj);
        }
    }

    @Override
    public <T> int update(String sqlUpdate, T obj) throws PersistenceException {
        if (StringUtils.isBlank(sqlUpdate))
            throw new PersistenceException();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            return sqlSession.update(sqlUpdate, obj);
        }
    }

    @Override
    public <T> int delete(String sqlDelete, Integer id) throws PersistenceException {
        if (StringUtils.isBlank(sqlDelete))
            throw new PersistenceException();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            return sqlSession.delete(sqlDelete, id);
        }
    }


    @Override
    public void procedureCall(String sql, Map<String, Object> map) throws PersistenceException {
        if (StringUtils.isBlank(sql))
            throw new PersistenceException();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            sqlSession.update(sql, map);
        }
    }

}
