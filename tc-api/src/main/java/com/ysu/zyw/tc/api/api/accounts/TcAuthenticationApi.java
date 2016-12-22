package com.ysu.zyw.tc.api.api.accounts;

import com.ysu.zyw.tc.model.api.o.accounts.ToAccount;
import com.ysu.zyw.tc.model.api.o.accounts.auth.ToMenu;
import com.ysu.zyw.tc.model.api.o.accounts.auth.ToPermission;
import com.ysu.zyw.tc.model.api.o.accounts.auth.ToRole;
import com.ysu.zyw.tc.model.mw.TcP;
import com.ysu.zyw.tc.model.mw.TcR;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(value = "/auths")
public interface TcAuthenticationApi {

    /**
     * @code code == 0 => 成功, body = 包含可登陆账号;
     * @code code == 1 => 账号不存在;
     * @code code == 2 => 账号被锁定;
     * @code code == 9999 => 系统异常;
     */
    @POST
    @Path(value = "/signup")
    @Consumes(value = {MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(value = {MediaType.APPLICATION_JSON})
    TcR<ToAccount> signup(
            @FormParam(value = "username") String username,
            @FormParam(value = "canEmailLogin") @DefaultValue(value = "true") Boolean canEmailLogin,
            @FormParam(value = "canMobileLogin") @DefaultValue(value = "true") Boolean canMobileLogin
    );

    /**
     * @code code == 0 => 成功, body = 包含可登陆账号;
     * @code code == 9999 => 系统异常;
     */
    @GET
    @Path(value = "/find_menus/{id}")
    @Consumes(value = {MediaType.WILDCARD})
    @Produces(value = {MediaType.APPLICATION_JSON})
    TcP<List<ToMenu>> findMenus(
            @PathParam(value = "id") String accountId
    );

    @GET
    @Path(value = "/find_roles/{id}")
    @Consumes(value = {MediaType.WILDCARD})
    @Produces(value = {MediaType.APPLICATION_JSON})
    TcP<List<ToRole>> findRoles(
            @PathParam(value = "id") String accountId
    );

    @GET
    @Path(value = "/find_pms/{id}")
    @Consumes(value = {MediaType.WILDCARD})
    @Produces(value = {MediaType.APPLICATION_JSON})
    TcP<List<ToPermission>> findPermissions(
            @PathParam(value = "id") String accountId
    );

}
