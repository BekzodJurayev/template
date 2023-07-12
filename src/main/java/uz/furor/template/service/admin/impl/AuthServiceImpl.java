package uz.furor.template.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.furor.template.config.JwtService;
import uz.furor.template.db.beans.admin.RoleBean;
import uz.furor.template.db.beans.admin.UserBean;
import uz.furor.template.exceptions.RestException;
import uz.furor.template.service.admin.AuthService;
import uz.furor.template.service.admin.RoleService;
import uz.furor.template.service.admin.UserService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Value("${user.default_password_expire_days}")
    private int default_password_expire_days;

    @Override
    public String register(UserBean userBean) throws RestException {
        List<RoleBean> roles = roleService.findDefaultRoles();

        var user = UserBean.builder()
                .username(userBean.getUsername())
                .password(passwordEncoder.encode(userBean.getPassword()))
                .password_expire_date(new Date(System.currentTimeMillis() + 1000L * 60 * 24 * default_password_expire_days))
                .enabled(true)
                .roles(null)
                .permissions(null)
                .build();
        user = userService.save(user);
        return jwtService.generateToken(user);
    }

    @Override
    public String login(UserBean userBean) throws UsernameNotFoundException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userBean.getUsername(),
                            userBean.getPassword()
                    )
            );
        } catch (DisabledException | LockedException | CredentialsExpiredException disabledException) {
            throw RestException.restThrow("USER_NOT_FOUND_OR_DISABLED", HttpStatus.FORBIDDEN);
        } catch (BadCredentialsException | UsernameNotFoundException badCredentialsException) {
            throw RestException.restThrow("LOGIN_OR_PASSWORD_ERROR", HttpStatus.UNAUTHORIZED);
        }
        var user = userService.findByUsername(userBean.getUsername());
        return jwtService.generateToken(user);
    }
}
