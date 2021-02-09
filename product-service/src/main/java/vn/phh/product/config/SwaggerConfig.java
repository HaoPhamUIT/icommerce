package vn.phh.product.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import vn.phh.commons.constants.CommonConstants;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author haopham
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        setSwaggerModel(docket);
        return docket
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("vn.phh.product.api"))
                .paths(PathSelectors.any())
                .build().securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Hao Pham REST API", "See the information.", "API", "Terms of service",
                new Contact("HaoPham", "https://www.facebook.com/HaoPhamXstorm", "haopham@gmail.com"),
                "License of Hao Pham", "API license URL", Collections.emptyList());
    }

    private TypeResolver typeResolver;

    @Autowired
    public SwaggerConfig(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }


    private void setSwaggerModel(Docket docket) {
        String[] packagesName = {"vn.mailinh.trip.response"};
        for (String packageName : packagesName) {
            URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(
                    CommonConstants.DOT, CommonConstants.SLASH));
            File[] files = root != null ? new File(root.getFile()).listFiles((dir, name) -> name.endsWith(".class")) : null;
            if (files != null && files.length > 0) {
                for (File file : files) {
                    String className = file.getName().replaceAll(".class$", CommonConstants.BLANK);
                    Class<?> cls;
                    try {
                        cls = Class.forName(packageName + "." + className);
                    } catch (Exception e) {
                        continue;
                    }
                    docket.additionalModels(typeResolver.resolve(cls));
                }
            }
        }
    }

    private ApiKey apiKey() {
        return new ApiKey(HttpHeaders.AUTHORIZATION, "Authorization", "header");
    }


    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
    }

}