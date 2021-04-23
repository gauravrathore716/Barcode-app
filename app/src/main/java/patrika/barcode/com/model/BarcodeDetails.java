package patrika.barcode.com.model;



public class BarcodeDetails {
    /**
     * response : {"isSuccess":"true","error":null,"data":{"containerno":"TEMU8330241","supplierreelno":"4345921301781558","supp_gross_wt":178,"supp_net_wt":176,"reel_gsm":42,"reel_mm":35,"reel_lenght":12035}}
     */

    private ResponseBean response;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * isSuccess : true
         * error : null
         * data : {"containerno":"TEMU8330241","supplierreelno":"4345921301781558","supp_gross_wt":178,"supp_net_wt":176,"reel_gsm":42,"reel_mm":35,"reel_lenght":12035}
         */

        private String isSuccess;
        private Object error;
        private DataBean data;

        public String getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(String isSuccess) {
            this.isSuccess = isSuccess;
        }

        public Object getError() {
            return error;
        }

        public void setError(Object error) {
            this.error = error;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * containerno : TEMU8330241
             * supplierreelno : 4345921301781558
             * supp_gross_wt : 178
             * supp_net_wt : 176
             * reel_gsm : 42
             * reel_mm : 35
             * reel_lenght : 12035
             */
            private String containerno;
            private String supplierreelno;
            private String pressreelno;
            private double supp_gross_wt;
            private double supp_net_wt;
            private double reel_gsm;
            private double reel_mm;
            private double reel_lenght;


            public String getContainerno() {
                return containerno;
            }

            public void setContainerno(String containerno) {
                this.containerno = containerno;
            }

            public String getSupplierreelno() {
                return supplierreelno;
            }

            public void setSupplierreelno(String supplierreelno) {
                this.supplierreelno = supplierreelno;
            }

            public double getSupp_gross_wt() {
                return supp_gross_wt;
            }

            public void setSupp_gross_wt(double supp_gross_wt) {
                this.supp_gross_wt = supp_gross_wt;
            }

            public double getSupp_net_wt() {
                return supp_net_wt;
            }

            public void setSupp_net_wt(double supp_net_wt) {
                this.supp_net_wt = supp_net_wt;
            }

            public double getReel_gsm() {
                return reel_gsm;
            }

            public void setReel_gsm(double reel_gsm) {
                this.reel_gsm = reel_gsm;
            }

            public double getReel_mm() {
                return reel_mm;
            }

            public void setReel_mm(double reel_mm) {
                this.reel_mm = reel_mm;
            }

            public double getReel_lenght() {
                return reel_lenght;
            }

            public void setReel_lenght(double reel_lenght) {
                this.reel_lenght = reel_lenght;
            }
            public String getPressreelno() {
                return pressreelno;
            }

            public void setPressreelno(String pressreelno) {
                this.pressreelno = pressreelno;
            }
        }
    }


    /**
     * response : {"isSuccess":"true","error":null,"data":{"containerno":"TEMU8330241","supplierreelno":"4345921301781558","supp_gross_wt":178,"supp_net_wt":176,"reel_gsm":42,"reel_mm":35,"reel_lenght":12035}}
     */



}
