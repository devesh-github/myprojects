package com.dev.stockmarket.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

@JsonInclude (Include.NON_EMPTY)
public class SssmAPIResponse<T> implements Serializable {

    private static final long serialVersionUID = -86595404654740956L;

    @JsonIgnore
    private Code result;

    private Integer code;
    private String message;
    private T data;

    public SssmAPIResponse() {
        result = ResultCode.SUCCESS;
    }

    public SssmAPIResponse(T value) {
        this();
        this.data = value;
    }

    public SssmAPIResponse(Code rc) {
        this.result = rc;
        setCode(rc.getCode());
        setMessage(rc.getMessage());
    }

    public SssmAPIResponse(Code rc, T value) {
        this(rc);
        this.data = value;
    }

    public Code getResult() {
        return result;
    }

    public void setResult(Code result) {
        this.result = result;
        setCode(result.getCode());
        setMessage(result.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SssmAPIResponse<?> other = (SssmAPIResponse<?>) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (result != other.result)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RestResponse [result=");
        builder.append(result);
        builder.append(", code=");
        builder.append(code);
        builder.append(", message=");
        builder.append(message);
        builder.append(", data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }

}
