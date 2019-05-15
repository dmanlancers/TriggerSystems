
package com.wetrig.dev.wetrig.POJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ratting {

    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_photo")
    @Expose
    private String userPhoto;
    @SerializedName("ratting")
    @Expose
    private String ratting;
    @SerializedName("review")
    @Expose
    private String review;

    /**
     * 
     * @return
     *     The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName
     *     The user_name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * @return
     *     The userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 
     * @param userEmail
     *     The user_email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 
     * @return
     *     The userPhoto
     */
    public String getUserPhoto() {
        return userPhoto;
    }

    /**
     * 
     * @param userPhoto
     *     The user_photo
     */
    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    /**
     * 
     * @return
     *     The ratting
     */
    public String getRatting() {
        return ratting;
    }

    /**
     * 
     * @param ratting
     *     The ratting
     */
    public void setRatting(String ratting) {
        this.ratting = ratting;
    }

    /**
     * 
     * @return
     *     The review
     */
    public String getReview() {
        return review;
    }

    /**
     * 
     * @param review
     *     The review
     */
    public void setReview(String review) {
        this.review = review;
    }

}
