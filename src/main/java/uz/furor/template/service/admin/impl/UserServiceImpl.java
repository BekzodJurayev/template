package uz.furor.template.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.furor.template.db.beans.admin.UserBean;
import uz.furor.template.repository.admin.UserRepository;
import uz.furor.template.service.admin.UserService;
import uz.furor.template.utils.Utils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserBean findById(Integer id) throws UsernameNotFoundException {
        return userRepository.selectOne(id);
    }

    @Override
    public UserBean findByUsername(String username) throws UsernameNotFoundException {
        List<UserBean> users = userRepository.selectList(Utils.generateMap("username", username));
        if (!Objects.equals(users.size(), 1))
            throw new UsernameNotFoundException("An user could not be found by this username");
        return users.get(0);
    }

    @Override
    public UserBean save(UserBean user) {
        userRepository.insert(user);
        return findByUsername(user.getUsername());
    }
}
