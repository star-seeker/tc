package com.ysu.zyw.tc.openapi.fk.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
public class TcShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    @Override
    public void setFilterChainDefinitions(String definitionFile) {
        checkArgument(definitionFile.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX),
                "definition file must be a classpath file, and it must start with 'classpath:'");
        String realPath = definitionFile.split(ResourceUtils.CLASSPATH_URL_PREFIX)[1];
        checkNotNull(realPath, "empty real path is not allowed");
        Resource filterChainDefinitionResource = new ClassPathResource(realPath);
        String definitions;
        try {
            log.info("start load shiro filter definition [{}]", realPath);
            definitions = FileUtils.readFileToString(filterChainDefinitionResource.getFile(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("shiro filter initialize need an valid filter chain definition "
                    + "resource file, it's must start with [classpath:], and then is a valid file path, but get ["
                    + definitionFile + "]", e);
        }
        checkNotNull(definitions, "empty definitions is not allowed");
        super.setFilterChainDefinitions(definitions);
    }

}
