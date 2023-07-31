package uz.furor.template.resources.admin.impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.furor.template.constants.urls.UrlAdmin;
import uz.furor.template.models.LoginBean;
import uz.furor.template.models.RegisterBean;
import uz.furor.template.resources.admin.AuthResource;
import uz.furor.template.service.admin.AuthService;

@RestController
@RequestMapping(UrlAdmin.AUTH)
@RequiredArgsConstructor
public class AuthResourceImpl implements AuthResource {
    private static final Logger _logger = LogManager.getLogger(AuthResourceImpl.class);
    private final AuthService authService;

    @Override
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginBean login) {
        String token = authService.login(login);
        _logger.info("Login success. user -> " + login);
        return ResponseEntity.ok(token);
    }

    @Override
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterBean register) {
        String token = authService.register(register);
        _logger.info("Register success. user -> " + register);
        return ResponseEntity.ok(token);
    }
}
