
package com.wetrig.dev.wetrig.POJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Devices {

    @SerializedName("irri_d_id")
    @Expose
    private String irriDId;
    @SerializedName("s_id")
    @Expose
    private String sId;
    @SerializedName("d_id")
    @Expose
    private String dId;
    @SerializedName("d_name")
    @Expose
    private String dName;
    @SerializedName("d_type")
    @Expose
    private String dType;
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("d_digital_val")
    @Expose
    private String dDigitalVal;
    @SerializedName("channel_id")
    @Expose
    private String channelId;
    @SerializedName("d_gateway_id")
    @Expose
    private String dGatewayId;
    @SerializedName("d_io")
    @Expose
    private String dIo;
    @SerializedName("g_model_no")
    @Expose
    private String gModelNo;

    /**
     * 
     * @return
     *     The irriDId
     */
    public String getIrriDId() {
        return irriDId;
    }

    /**
     * 
     * @param irriDId
     *     The irri_d_id
     */
    public void setIrriDId(String irriDId) {
        this.irriDId = irriDId;
    }

    /**
     * 
     * @return
     *     The sId
     */
    public String getSId() {
        return sId;
    }

    /**
     * 
     * @param sId
     *     The s_id
     */
    public void setSId(String sId) {
        this.sId = sId;
    }

    /**
     * 
     * @return
     *     The dId
     */
    public String getDId() {
        return dId;
    }

    /**
     * 
     * @param dId
     *     The d_id
     */
    public void setDId(String dId) {
        this.dId = dId;
    }

    /**
     * 
     * @return
     *     The dName
     */
    public String getDName() {
        return dName;
    }

    /**
     * 
     * @param dName
     *     The d_name
     */
    public void setDName(String dName) {
        this.dName = dName;
    }

    /**
     * 
     * @return
     *     The dType
     */
    public String getDType() {
        return dType;
    }

    /**
     * 
     * @param dType
     *     The d_type
     */
    public void setDType(String dType) {
        this.dType = dType;
    }

    /**
     * 
     * @return
     *     The uId
     */
    public String getUId() {
        return uId;
    }

    /**
     * 
     * @param uId
     *     The u_id
     */
    public void setUId(String uId) {
        this.uId = uId;
    }

    /**
     * 
     * @return
     *     The dDigitalVal
     */
    public String getDDigitalVal() {
        return dDigitalVal;
    }

    /**
     * 
     * @param dDigitalVal
     *     The d_digital_val
     */
    public void setDDigitalVal(String dDigitalVal) {
        this.dDigitalVal = dDigitalVal;
    }

    /**
     * 
     * @return
     *     The channelId
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 
     * @param channelId
     *     The channel_id
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 
     * @return
     *     The dGatewayId
     */
    public String getDGatewayId() {
        return dGatewayId;
    }

    /**
     * 
     * @param dGatewayId
     *     The d_gateway_id
     */
    public void setDGatewayId(String dGatewayId) {
        this.dGatewayId = dGatewayId;
    }

    /**
     * 
     * @return
     *     The dIo
     */
    public String getDIo() {
        return dIo;
    }

    /**
     * 
     * @param dIo
     *     The d_io
     */
    public void setDIo(String dIo) {
        this.dIo = dIo;
    }

    /**
     * 
     * @return
     *     The gModelNo
     */
    public String getGModelNo() {
        return gModelNo;
    }

    /**
     * 
     * @param gModelNo
     *     The g_model_no
     */
    public void setGModelNo(String gModelNo) {
        this.gModelNo = gModelNo;
    }

}
