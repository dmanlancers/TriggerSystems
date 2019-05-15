
package com.wetrig.dev.wetrig.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SwitchOnOff {

    @SerializedName("allowRun")
    @Expose
    private String allowRun;
    @SerializedName("runNow")
    @Expose
    private String runNow;
    @SerializedName("doProgram")
    @Expose
    private String doProgram;
    @SerializedName("controllerId")
    @Expose
    private String controllerId;
    @SerializedName("pgmNum")
    @Expose
    private String pgmNum;
    @SerializedName("minutes")
    @Expose
    private String minutes;
    @SerializedName("terminal")
    @Expose
    private String terminal;
    @SerializedName("z3Minutes")
    @Expose
    private String z3Minutes;
    @SerializedName("success")
    @Expose
    private Integer success;

    /**
     * 
     * @return
     *     The allowRun
     */
    public String getAllowRun() {
        return allowRun;
    }

    /**
     * 
     * @param allowRun
     *     The allowRun
     */
    public void setAllowRun(String allowRun) {
        this.allowRun = allowRun;
    }

    /**
     * 
     * @return
     *     The runNow
     */
    public String getRunNow() {
        return runNow;
    }

    /**
     * 
     * @param runNow
     *     The runNow
     */
    public void setRunNow(String runNow) {
        this.runNow = runNow;
    }

    /**
     * 
     * @return
     *     The doProgram
     */
    public String getDoProgram() {
        return doProgram;
    }

    /**
     * 
     * @param doProgram
     *     The doProgram
     */
    public void setDoProgram(String doProgram) {
        this.doProgram = doProgram;
    }

    /**
     * 
     * @return
     *     The controllerId
     */
    public String getControllerId() {
        return controllerId;
    }

    /**
     * 
     * @param controllerId
     *     The controllerId
     */
    public void setControllerId(String controllerId) {
        this.controllerId = controllerId;
    }

    /**
     * 
     * @return
     *     The pgmNum
     */
    public String getPgmNum() {
        return pgmNum;
    }

    /**
     * 
     * @param pgmNum
     *     The pgmNum
     */
    public void setPgmNum(String pgmNum) {
        this.pgmNum = pgmNum;
    }

    /**
     * 
     * @return
     *     The minutes
     */
    public String getMinutes() {
        return minutes;
    }

    /**
     * 
     * @param minutes
     *     The minutes
     */
    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    /**
     * 
     * @return
     *     The terminal
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * 
     * @param terminal
     *     The terminal
     */
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    /**
     * 
     * @return
     *     The z3Minutes
     */
    public String getZ3Minutes() {
        return z3Minutes;
    }

    /**
     * 
     * @param z3Minutes
     *     The z3Minutes
     */
    public void setZ3Minutes(String z3Minutes) {
        this.z3Minutes = z3Minutes;
    }

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

}
