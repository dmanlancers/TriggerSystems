
    package com.wetrig.dev.wetrig.POJO;

    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

    public class Login {

        @SerializedName("ID")
        @Expose
        private String iD;
        @SerializedName("user_name")
        @Expose
        private String userName;
        @SerializedName("user_surname")
        @Expose
        private String userSurname;
        @SerializedName("user_email")
        @Expose
        private String userEmail;
        @SerializedName("user_password")
        @Expose
        private String userPassword;
        @SerializedName("activation_code")
        @Expose
        private String activationCode;
        @SerializedName("account_status")
        @Expose
        private String accountStatus;
        @SerializedName("user_photo")
        @Expose
        private String userPhoto;
        @SerializedName("user_birthdate")
        @Expose
        private String userBirthdate;
        @SerializedName("user_address")
        @Expose
        private String userAddress;
        @SerializedName("user_country")
        @Expose
        private String userCountry;
        @SerializedName("user_phone")
        @Expose
        private String userPhone;
        @SerializedName("user_gender")
        @Expose
        private String userGender;
        @SerializedName("user_bio")
        @Expose
        private String userBio;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("last_access")
        @Expose
        private String lastAccess;
        @SerializedName("t_u_id")
        @Expose
        private String tUId;
        @SerializedName("t_u_name")
        @Expose
        private String tUName;
        @SerializedName("t_u_pass")
        @Expose
        private String tUPass;
        @SerializedName("t_a_status")
        @Expose
        private String tAStatus;
        @SerializedName("company_name")
        @Expose
        private String companyName;
        @SerializedName("user_role")
        @Expose
        private String userRole;

        /**
         *
         * @return
         *     The iD
         */
        public String getID() {
            return iD;
        }

        /**
         *
         * @param iD
         *     The ID
         */
        public void setID(String iD) {
            this.iD = iD;
        }

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
         *     The userSurname
         */
        public String getUserSurname() {
            return userSurname;
        }

        /**
         *
         * @param userSurname
         *     The user_surname
         */
        public void setUserSurname(String userSurname) {
            this.userSurname = userSurname;
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
         *     The userPassword
         */
        public String getUserPassword() {
            return userPassword;
        }

        /**
         *
         * @param userPassword
         *     The user_password
         */
        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        /**
         *
         * @return
         *     The activationCode
         */
        public String getActivationCode() {
            return activationCode;
        }

        /**
         *
         * @param activationCode
         *     The activation_code
         */
        public void setActivationCode(String activationCode) {
            this.activationCode = activationCode;
        }

        /**
         *
         * @return
         *     The accountStatus
         */
        public String getAccountStatus() {
            return accountStatus;
        }

        /**
         *
         * @param accountStatus
         *     The account_status
         */
        public void setAccountStatus(String accountStatus) {
            this.accountStatus = accountStatus;
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
         *     The userBirthdate
         */
        public String getUserBirthdate() {
            return userBirthdate;
        }

        /**
         *
         * @param userBirthdate
         *     The user_birthdate
         */
        public void setUserBirthdate(String userBirthdate) {
            this.userBirthdate = userBirthdate;
        }

        /**
         *
         * @return
         *     The userAddress
         */
        public String getUserAddress() {
            return userAddress;
        }

        /**
         *
         * @param userAddress
         *     The user_address
         */
        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        /**
         *
         * @return
         *     The userCountry
         */
        public String getUserCountry() {
            return userCountry;
        }

        /**
         *
         * @param userCountry
         *     The user_country
         */
        public void setUserCountry(String userCountry) {
            this.userCountry = userCountry;
        }

        /**
         *
         * @return
         *     The userPhone
         */
        public String getUserPhone() {
            return userPhone;
        }

        /**
         *
         * @param userPhone
         *     The user_phone
         */
        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        /**
         *
         * @return
         *     The userGender
         */
        public String getUserGender() {
            return userGender;
        }

        /**
         *
         * @param userGender
         *     The user_gender
         */
        public void setUserGender(String userGender) {
            this.userGender = userGender;
        }

        /**
         *
         * @return
         *     The userBio
         */
        public String getUserBio() {
            return userBio;
        }

        /**
         *
         * @param userBio
         *     The user_bio
         */
        public void setUserBio(String userBio) {
            this.userBio = userBio;
        }

        /**
         *
         * @return
         *     The created
         */
        public String getCreated() {
            return created;
        }

        /**
         *
         * @param created
         *     The created
         */
        public void setCreated(String created) {
            this.created = created;
        }

        /**
         *
         * @return
         *     The lastAccess
         */
        public String getLastAccess() {
            return lastAccess;
        }

        /**
         *
         * @param lastAccess
         *     The last_access
         */
        public void setLastAccess(String lastAccess) {
            this.lastAccess = lastAccess;
        }

        /**
         *
         * @return
         *     The tUId
         */
        public String getTUId() {
            return tUId;
        }

        /**
         *
         * @param tUId
         *     The t_u_id
         */
        public void setTUId(String tUId) {
            this.tUId = tUId;
        }

        /**
         *
         * @return
         *     The tUName
         */
        public String getTUName() {
            return tUName;
        }

        /**
         *
         * @param tUName
         *     The t_u_name
         */
        public void setTUName(String tUName) {
            this.tUName = tUName;
        }

        /**
         *
         * @return
         *     The tUPass
         */
        public String getTUPass() {
            return tUPass;
        }

        /**
         *
         * @param tUPass
         *     The t_u_pass
         */
        public void setTUPass(String tUPass) {
            this.tUPass = tUPass;
        }

        /**
         *
         * @return
         *     The tAStatus
         */
        public String getTAStatus() {
            return tAStatus;
        }

        /**
         *
         * @param tAStatus
         *     The t_a_status
         */
        public void setTAStatus(String tAStatus) {
            this.tAStatus = tAStatus;
        }

        /**
         *
         * @return
         *     The companyName
         */
        public String getCompanyName() {
            return companyName;
        }

        /**
         *
         * @param companyName
         *     The company_name
         */
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        /**
         *
         * @return
         *     The userRole
         */
        public String getUserRole() {
            return userRole;
        }

        /**
         *
         * @param userRole
         *     The user_role
         */
        public void setUserRole(String userRole) {
            this.userRole = userRole;
        }

    }
