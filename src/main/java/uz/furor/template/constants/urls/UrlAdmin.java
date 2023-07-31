package uz.furor.template.constants.urls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface UrlAdmin {
    String ROOT = "api/admin";
    String AUTH = ROOT + "/auth";
    List<String> openPaths = Collections.unmodifiableList(Arrays.asList(
            UrlAdmin.AUTH + "/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/**"
    ));
}
