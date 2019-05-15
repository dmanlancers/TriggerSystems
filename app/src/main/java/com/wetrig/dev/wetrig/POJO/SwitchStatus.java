
        package com.wetrig.dev.wetrig.POJO;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        public class  SwitchStatus {

            @SerializedName("d_digital_val")
            @Expose
            private String dDigitalVal;

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

        }
