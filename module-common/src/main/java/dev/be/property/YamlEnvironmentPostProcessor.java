package dev.be.property;

import static dev.be.constant.Constant.BETA;
import static dev.be.constant.Constant.LOCAL;
import static dev.be.constant.Constant.REAL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Profiles;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import lombok.extern.slf4j.Slf4j;

/**
 * resources/META-INF/spring.factories에 YamlEnvironmentPostProcessor.class를 선언해줘야한다.
 */
@Slf4j
public class YamlEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String[] propertyUris = { "classpath*:config/custom/*.yml"};
    private static final String[] acceptsProfiles = { LOCAL, BETA, REAL };

    private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
    private final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        // Set Default Profile
        // ref : https://goodgid.github.io/Analyzing-the-Feign-Client-and-Use/#profile-%EC%84%A4%EC%A0%95
        boolean isNotValidProfileActive = !environment.acceptsProfiles(Profiles.of(acceptsProfiles));

        if (isNotValidProfileActive) {
            environment.setActiveProfiles(LOCAL);
        }

        // Load Custom *.yml
        // ref : https://goodgid.github.io/Analyzing-the-Feign-Client-and-Use/#custom-yml-%EC%82%AC%EC%9A%A9
        try {
            List<Resource> resourceList = new ArrayList<>();
            for (String propertyUri : propertyUris) {
                resourceList.addAll(List.of(resourcePatternResolver.getResources(propertyUri)));
            }

            resourceList.stream().map(this::loadYaml).forEach(them -> {
                if (them != null) {
                    for (PropertySource<?> it : them) {
                        environment.getPropertySources().addLast(it);
                    }
                }
            });
        } catch (Exception e) {
            throw new BeanCreationException(e.getMessage(), e);
        }
    }

    private List<PropertySource<?>> loadYaml(Resource resource) {
        if (!resource.exists()) {
            throw new IllegalArgumentException("Resource " + resource + " does not exist");
        }
        try {
            return loader.load(resource.getURL().toString(), resource);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to load yaml configuration from " + resource, ex);
        }
    }
}
