
package com.wetrig.dev.wetrig.POJO;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetSystemId {

    @SerializedName("lth")
    @Expose
    private Integer lth;
    @SerializedName("data")
    @Expose
    private List<systemId> data = new ArrayList<systemId>();

    /**
     *
     * @return
     *     The lth
     */
    public Integer getLth() {
        return lth;
    }

    /**
     *
     * @param lth
     *     The lth
     */
    public void setLth(Integer lth) {
        this.lth = lth;
    }

    /**
     *
     * @return
     *     The data
     */
    public List<systemId> getData() {
        return data;
    }

    /**
     *
     * @param data
     *     The data
     */
    public void setData(List<systemId> data) {
        this.data = data;
    }

}