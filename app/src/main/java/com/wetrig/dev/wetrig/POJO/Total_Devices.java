
package com.wetrig.dev.wetrig.POJO;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Total_Devices {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("lnt")
    @Expose
    private Integer lnt;
    @SerializedName("data")
    @Expose
    private List<Devices> data = new ArrayList<Devices>();

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
     *     The lnt
     */
    public Integer getLnt() {
        return lnt;
    }

    /**
     * 
     * @param lnt
     *     The lnt
     */
    public void setLnt(Integer lnt) {
        this.lnt = lnt;
    }

    /**
     * 
     * @return
     *     The data
     */
    public List<Devices> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(List<Devices> data) {
        this.data = data;
    }

}
