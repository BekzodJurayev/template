package uz.furor.template.repository.admin;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Repository;
import uz.furor.template.db.beans.admin.UserBean;
import uz.furor.template.repository.core.BaseCRUD;
import uz.furor.template.repository.core.BaseRepository;
import uz.furor.template.utils.Utils;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRepository implements BaseRepository<UserBean>{
    private final BaseCRUD baseCRUD;
    private final String SELECT_ID = "selectUsers";

    @Override
    public UserBean selectOne(Integer id) throws PersistenceException {
        return baseCRUD.getOne(SELECT_ID, Utils.generateMap("id", id));
    }

    @Override
    public List<UserBean> selectList(Map<String, Object> params) throws PersistenceException {
        return baseCRUD.getList(SELECT_ID, params);
    }

    @Override
    public List<UserBean> selectListAll(Map<String, Object> params) throws PersistenceException {
        return baseCRUD.getListAll(SELECT_ID, params);
    }

    @Override
    public void insert(UserBean bean) throws PersistenceException {
        String INSERT_ID = "insertUser";
        baseCRUD.insert(INSERT_ID, bean);
    }

    @Override
    public void update(UserBean bean) throws PersistenceException {
        String UPDATE_ID = "updateUser";
        baseCRUD.update(UPDATE_ID, bean);
    }

    @Override
    public void delete(Integer id) throws PersistenceException {
        String DELETE_ID = "deleteUser";
        baseCRUD.delete(DELETE_ID, id);
    }
}
