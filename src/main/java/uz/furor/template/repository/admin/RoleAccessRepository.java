package uz.furor.template.repository.admin;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Repository;
import uz.furor.template.db.beans.admin.RoleAccessBean;
import uz.furor.template.repository.core.BaseCRUD;
import uz.furor.template.repository.core.BaseRepository;
import uz.furor.template.utils.Utils;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RoleAccessRepository implements BaseRepository<RoleAccessBean> {
    private final BaseCRUD baseCRUD;
    private final String SELECT_ID = "selectRoleAccesses";
    @Override
    public RoleAccessBean selectOne(Integer id) throws PersistenceException {
        return baseCRUD.getOne(SELECT_ID, Utils.generateMap("id", id));
    }

    @Override
    public List<RoleAccessBean> selectList(Map<String, Object> params) throws PersistenceException {
        return baseCRUD.getList(SELECT_ID, params);
    }

    @Override
    public List<RoleAccessBean> selectListAll(Map<String, Object> params) throws PersistenceException {
        return baseCRUD.getListAll(SELECT_ID, params);
    }

    @Override
    public void insert(RoleAccessBean bean) throws PersistenceException {
        String INSERT_ID = "insertRoleAccess";
        baseCRUD.insert(INSERT_ID, bean);
    }

    @Override
    public void update(RoleAccessBean bean) throws PersistenceException {
        String UPDATE_ID = "updateRoleAccess";
        baseCRUD.update(UPDATE_ID, bean);
    }

    @Override
    public void delete(Integer id) throws PersistenceException {
        String DELETE_ID = "deleteRoleAccess";
        baseCRUD.delete(DELETE_ID, id);
    }
}
