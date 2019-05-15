
package com.wetrig.dev.wetrig.POJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Today {

    @SerializedName("sno")
    @Expose
    private Integer sno;
    @SerializedName("program")
    @Expose
    private String program;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("device")
    @Expose
    private String device;
    @SerializedName("duration")
    @Expose
    private String duration;

    /**
     *
     * @return
     *     The sno
     */
    public Integer getSno() {
        return sno;
    }

    /**
     *
     * @param sno
     *     The sno
     */
    public void setSno(Integer sno) {
        this.sno = sno;
    }

    /**
     *
     * @return
     *     The program
     */
    public String getProgram() {
        return program;
    }

    /**
     *
     * @param program
     *     The program
     */
    public void setProgram(String program) {
        this.program = program;
    }

    /**
     *
     * @return
     *     The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     *     The start_date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return
     *     The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     *     The end_date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     *     The time
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @param time
     *     The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     *
     * @return
     *     The device
     */
    public String getDevice() {
        return device;
    }

    /**
     *
     * @param device
     *     The device
     */
    public void setDevice(String device) {
        this.device = device;
    }

    /**
     *
     * @return
     *     The duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     *     The duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

}
