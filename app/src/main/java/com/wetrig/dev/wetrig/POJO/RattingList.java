
package com.wetrig.dev.wetrig.POJO;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RattingList {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("data")
    @Expose
    private List<ratting> data = new ArrayList<ratting>();

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
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The data
     */
    public List<ratting> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(List<ratting> data) {
        this.data = data;
    }

}
