package com.ysu.zyw.tc.model.mw;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 1. TcR.code == R.SUCCESS => success, have body (except TcR<Void>)
 * 2. TcR.code == ? => custom code
 * 3. TcR.code == R.SERVER_ERROR => server error, may happen in any apis.
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "响应包裹类型")
public class TcR<T> implements Serializable {

    private static final long serialVersionUID = -3742606203018432820L;

    @ApiModelProperty(value = "响应码")
    protected int code;

    @ApiModelProperty(value = "响应描述")
    protected String description;

    @ApiModelProperty(value = "响应体")
    protected T body;

    @ApiModelProperty(value = "附加信息")
    protected Object extra;

    public static <T> TcR<T> ok() {
        return new TcR<T>()
                .setCode(R.SUCCESS)
                .setDescription(R.SUCCESS_DESCRIPTION);
    }

    public static <T> TcR<T> ok(@Nonnull T body) {
        checkNotNull(body);
        return new TcR<T>()
                .setCode(R.SUCCESS)
                .setDescription(R.SUCCESS_DESCRIPTION)
                .setBody(body);
    }

    public static <T> TcR<T> code(int code, @Nonnull String description) {
        checkNotNull(description);
        return new TcR<T>()
                .setCode(code)
                .setDescription(description);
    }

    public static <T> TcR<T> error() {
        return new TcR<T>()
                .setCode(R.SERVER_ERROR)
                .setDescription(R.SERVER_ERROR_DESCRIPTION);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code == R.SUCCESS;
    }

    @JsonIgnore
    public boolean isPresent() {
        return this.isSuccess() && Objects.nonNull(body);
    }

    public void ifPresent(Consumer<T> consumer) {
        if (isPresent()) {
            consumer.accept(body);
        }
    }

    public T get() {
        return body;
    }

    public T orElse(T another) {
        return isPresent() ? body : another;
    }

    public T orElseGet(Supplier<T> supplier) {
        return isPresent() ? body : supplier.get();
    }

    public T orElseThrow(Supplier<? extends RuntimeException> supplier) {
        if (isPresent()) {
            return body;
        } else {
            throw supplier.get();
        }
    }

    public String orElseGetStringExtra() {
        return orElseGetStringExtra(Object::toString);
    }

    public String orElseGetStringExtra(Function<Object, String> extraProcessor) {
        if (Objects.isNull(extra)) {
            return null;
        } else {
            return extraProcessor.apply(this.extra);
        }
    }

}
