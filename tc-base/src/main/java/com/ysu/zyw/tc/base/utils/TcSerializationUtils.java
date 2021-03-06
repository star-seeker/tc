package com.ysu.zyw.tc.base.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TcBeanUtils defines serialization related operations
 *
 * @author yaowu.zhang
 */
@Slf4j
@UtilityClass
public class TcSerializationUtils {

    public static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(TcDateUtils.FULL_DATE_FORMAT_VALUE));
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SneakyThrows
    public static byte[] serialize(@Nonnull Serializable value) {
        checkNotNull(value, "null writeJson value is not allowed");
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(value);
            return byteArrayOutputStream.toByteArray();
        }
    }

    @SneakyThrows
    public static <T> T deserialize(@Nonnull byte[] bytes, @Nonnull Class<T> clazz) {
        checkNotNull(bytes, "null readJson bytes is not allowed");
        checkNotNull(clazz, "null readJson clazz is not allowed");
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            Object object = objectInputStream.readObject();
            return clazz.cast(object);
        }
    }

    @SneakyThrows
    public static byte[] serializeStr(@Nonnull String text) {
        checkNotNull(text, "null writeJson text is not allowed");
        return text.getBytes(StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static String deserializeStr(@Nonnull byte[] bytes) {
        checkNotNull(bytes, "null readJson bytes is not allowed");
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static String writeJson(@Nonnull Object object) {
        checkNotNull(object, "null writeJson object is not allowed");
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    @SneakyThrows
    public static <T> T readJson(@Nonnull String text, @Nonnull Class<T> clazz) {
        checkNotNull(text, "null readJson str is not allowed");
        checkNotNull(clazz, "null readJson clazz is not allowed");
        return OBJECT_MAPPER.readValue(text, clazz);
    }

    @SneakyThrows
    public static <T> T readJson(@Nonnull String text, @Nonnull TypeReference<T> typeReference) {
        checkNotNull(text, "null readJson str is not allowed");
        checkNotNull(typeReference, "null readJson type reference is not allowed");
        return OBJECT_MAPPER.readValue(text, typeReference);
    }

}
