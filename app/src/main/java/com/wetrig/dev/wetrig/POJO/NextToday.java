
package com.wetrig.dev.wetrig.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NextToday {

    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("0")
    @Expose
    private com.wetrig.dev.wetrig.POJO.Today _0;

    /**
     *
     * @return
     *     The cnt
     */
    public Integer getCnt() {
        return cnt;
    }

    /**
     *
     * @param cnt
     *     The cnt
     */
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    /**
     *
     * @return
     *     The _0
     */
    public com.wetrig.dev.wetrig.POJO.Today get0() {
        return _0;
    }

    /**
     *
     * @param _0
     *     The 0
     */
    public void set0(com.wetrig.dev.wetrig.POJO.Today _0) {
        this._0 = _0;
    }

}
