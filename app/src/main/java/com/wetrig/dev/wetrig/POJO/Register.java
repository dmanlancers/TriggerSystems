package com.wetrig.dev.wetrig.POJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Register {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("exists")
    @Expose
    private Integer exists;

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The exists
     */
    public Integer getExists() {
        return exists;
    }

    /**
     *
     * @param exists
     * The exists
     */
    public void setExists(Integer exists) {
        this.exists = exists;
    }

}