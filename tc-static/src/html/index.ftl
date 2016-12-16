<!DOCTYPE html>
<html lang="en">
<head>
    <title>实验工程</title>
<#include "/WEB-INF/templates/commons/pre-lib.ftl"/>
    <link href="${staticBase}/styles/index.css"
          type="text/css"
          rel="stylesheet"/>
</head>
<body>
<!-- + wrapper -->
<div id="wrapper">
    <!-- + header -->
<#include "/WEB-INF/templates/commons/header.ftl"/>
    <!-- - header -->

    <!-- + body -->
    <div id="body"
         class="am-container">
        <!-- + signup form -->
        <div id="doc-signup-div"
             class="am-u-lg-12">
            <form id="doc-signup-form"
                  class="am-form">
                <fieldset>
                    <legend>登陆</legend>

                    <div class="am-form-group">
                        <label>账号</label>
                        <div class="am-input-group"
                             v-bind:class="usernameClass">
                            <span class="am-input-group-label"><i class="am-icon-user am-icon-fw"></i></span>
                            <input id="doc-signup-username"
                                   type="text"
                                   class="am-form-field"
                                   name="username"
                                   placeholder="请输入您的账号"
                                   v-model="uiElement.formElement.username">
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label>密码</label>
                        <div class="am-input-group"
                             v-bind:class="passwordClass">
                            <span class="am-input-group-label"><i class="am-icon-lock am-icon-fw"></i></span>
                            <input id="doc-signup-password"
                                   type="password"
                                   class="am-form-field"
                                   name="password"
                                   placeholder="请输入您的密码"
                                   v-model="uiElement.formElement.password">
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label>验证码</label>
                        <div class="am-input-group"
                             v-bind:class="verificationCodeClass">
                            <span class="am-input-group-label"><i class="am-icon-gg am-icon-fw"></i></span>
                            <input id="doc-signup-verification-code"
                                   type="text"
                                   class="am-form-field"
                                   name="verificationCode"
                                   placeholder="请输入右方的验证码"
                                   v-model="uiElement.formElement.verificationCode">
                            <span class="am-input-group-label doc-verification-code-span">
                                <span>{{ hiddenElement.verificationCode }}</span>
                                <i class="am-icon-refresh am-icon-fw"
                                   v-on:click="reloadVerificationCode"></i>
                            </span>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label>是否记住密码?</label>
                        <div class="am-input-group am-input-group-primary">
                            <select id="doc-signup-remeber-me"
                                    data-am-selected
                                    name="rememberMe"
                                    v-model="uiElement.formElement.rememberMe">
                                <option selected
                                        value="false">
                                    不记住密码
                                </option>
                                <option value="true">
                                    记住密码
                                </option>
                            </select>
                        </div>
                    </div>

                    <div>
                        <h6 class="am-text-danger"
                            v-if="showSignupErrorMsg">
                            <strong>
                                登陆失败，原因：({{ uiElement.signupErrorMsg }})
                            </strong>
                        </h6>
                        <button type="button"
                                class="am-btn am-btn-primary"
                                v-bind:class="formClass"
                                v-on:click="signup">
                            提交
                        </button>
                        <button type="button"
                                class="am-btn am-btn-default"
                                v-on:click="reset">
                            重置
                        </button>
                    </div>
                </fieldset>
            </form>
        </div>
        <!-- - signup form -->
    </div>
    <!-- - body -->

    <!-- + footer -->
<#include "/WEB-INF/templates/commons/footer.ftl"/>
    <!-- - footer -->
</div>
<!-- - wrapper -->
<#include "/WEB-INF/templates/commons/post-lib.ftl"/>
<@static.script src="/libs/jquery.md5.min.js"/>
<@static.script src="/scripts/index.js"/>
</body>
</html>