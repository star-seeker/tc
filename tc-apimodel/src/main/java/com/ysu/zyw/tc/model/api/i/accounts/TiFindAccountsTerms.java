package com.ysu.zyw.tc.model.api.i.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.ws.rs.FormParam;
import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TiFindAccountsTerms implements Serializable {

    @FormParam(value = "ids")
    private List<String> ids;

    @FormParam(value = "name")
    private String name;

    @FormParam(value = "account")
    private String account;

    @FormParam(value = "mobile")
    private String mobile;

    @FormParam(value = "email")
    private String email;

    @FormParam(value = "mobileActivated")
    private Boolean mobileActivated;

    @FormParam(value = "emailActivated")
    private Boolean emailActivated;

    @FormParam(value = "locked")
    private Boolean locked;

}