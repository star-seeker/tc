package com.ysu.zyw.tc.components.ali.support.mail;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Nonnull;

@Slf4j
public class TcMailService implements InitializingBean {

    @Getter
    @Setter
    private String regionId;

    @Getter
    @Setter
    private String accessKey;

    @Getter
    @Setter
    private String accessSecret;

    @Getter
    @Setter
    private String accountName;

    @Getter
    @Setter
    private String fromAlias;

    @Getter
    @Setter
    private String tagName;

    private IAcsClient client;

    @SneakyThrows
    public void send(@Nonnull String toAddress,
                     @Nonnull String subject,
                     @Nonnull String htmlBody) {
        SingleSendMailRequest request = new SingleSendMailRequest();
        request.setAccountName(accountName);
        request.setFromAlias(fromAlias);
        request.setAddressType(1);
        request.setTagName(tagName);
        request.setReplyToAddress(true);
        request.setToAddress(toAddress);
        request.setSubject(subject);
        request.setHtmlBody(htmlBody);
        if (log.isInfoEnabled()) {
            log.info("start send mail to [{}] [{}] [{}] [{}]", toAddress, tagName, subject, htmlBody);
        }
        SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        if (log.isInfoEnabled()) {
            log.info("finish send mail to [{}] [{}]", toAddress, httpResponse);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKey, accessSecret);
        client = new DefaultAcsClient(profile);
        log.info("successful initialize aliyun email client ...");
    }

}
