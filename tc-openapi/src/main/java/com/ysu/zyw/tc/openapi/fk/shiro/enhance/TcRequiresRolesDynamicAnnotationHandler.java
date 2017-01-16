package com.ysu.zyw.tc.openapi.fk.shiro.enhance;

import com.ysu.zyw.tc.components.support.TcApplicationContextHolder;
import com.ysu.zyw.tc.openapi.fk.shiro.support.TcRequiresRolesDynamicSup;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;

import java.lang.annotation.Annotation;

import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
public class TcRequiresRolesDynamicAnnotationHandler extends AuthorizingAnnotationHandler {

    private TcRequiresRolesDynamicSup tcRequiresRolesDynamicSup;

    public TcRequiresRolesDynamicAnnotationHandler() {
        super(TcRequiresRolesDynamic.class);
        this.tcRequiresRolesDynamicSup =
                TcApplicationContextHolder.applicationContext.getBean(TcRequiresRolesDynamicSup.class);
        log.info("successful inject tc requires roles dynamic sup into handler ...");
    }

    @Override
    public void assertAuthorized(Annotation a) throws AuthorizationException {
        checkNotNull(tcRequiresRolesDynamicSup);

        if (!(a instanceof TcRequiresRolesDynamic)) {
            return;
        }

        TcRequiresRolesDynamic rrdAnnotation = (TcRequiresRolesDynamic) a;
        String[] roles = rrdAnnotation.value();

        if (!tcRequiresRolesDynamicSup.isAccessAllowed(roles, rrdAnnotation.logical())) {
            throw new AuthorizationException();
        }
    }

}
