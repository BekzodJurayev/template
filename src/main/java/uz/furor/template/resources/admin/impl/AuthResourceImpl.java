package uz.furor.template.resources.admin.impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.furor.template.constants.urls.UrlAdmin;
import uz.furor.template.db.beans.admin.UserBean;
import uz.furor.template.exceptions.RestException;
import uz.furor.template.resources.admin.AuthResource;
import uz.furor.template.service.admin.UserService;

@RestController
@RequestMapping(UrlAdmin.AUTH)
@RequiredArgsConstructor
public class AuthResourceImpl implements AuthResource {

    private final PasswordEncoder passwordEncoder;
    private static final Logger _logger = LogManager.getLogger(AuthResourceImpl.class);

    private final UserService userService;

    @Override
    @PostMapping("login")
    public ResponseEntity<?> login(@NonNull UserBean user) {
        UserBean userBean = userService.findByUsername(user.getUsername());
        if (!passwordEncoder.matches(user.getPassword(), userBean.getPassword())) {
            _logger.error("Login or password incorrect. user -> " + user);
            throw RestException.restThrow("Login or password incorrect", HttpStatus.UNAUTHORIZED);
        }
        UserBean user1 = (UserBean)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        _logger.info("Login success. user -> " + user);
        return ResponseEntity.ok(userBean);
    }
}
