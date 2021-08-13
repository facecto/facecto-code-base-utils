package com.facecto.code.base.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * The YAML factory
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.0 (2021/08/08)
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public org.springframework.core.env.PropertySource<?> createPropertySource(String s, EncodedResource encodedResource) throws IOException {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(encodedResource.getResource());
        factory.afterPropertiesSet();
        Properties ymlProperties = factory.getObject();
        String propertyName = s != null ? s : encodedResource.getResource().getFilename();
        return new PropertiesPropertySource(propertyName, ymlProperties);
    }
}