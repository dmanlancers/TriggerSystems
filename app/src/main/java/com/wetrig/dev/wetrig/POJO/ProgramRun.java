
package com.wetrig.dev.wetrig.POJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProgramRun {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("budget")
    @Expose
    private Integer budget;

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The budget
     */
    public Integer getBudget() {
        return budget;
    }

    /**
     * 
     * @param budget
     *     The budget
     */
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

}
