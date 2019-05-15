
package com.wetrig.dev.wetrig.POJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RunStopSchedule {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("result")
    @Expose
    private String result;

    /**
     * 
     * @return
     *     The success
     */
    public Integer getSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    public void setSuccess(Integer success) {
        this.success = success;
    }

    /**
     * 
     * @return
     *     The result
     */
    public String getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(String result) {
        this.result = result;
    }

}
