package com.ysu.zyw.tc.components.commons.upload;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * TcUploadService provide a upload file interface.
 *
 * @author yaowu.zhang
 */
public interface TcUploadService {

    String upload(@Nonnull TcUploadResourceData metadata, @Nonnull InputStream inputStream);

    OutputStream download(@Nonnull TcUploadResourceData metadata);

    void delete(@Nonnull TcUploadResourceData metadata);

    boolean exists(@Nonnull TcUploadResourceData metadata);

}
