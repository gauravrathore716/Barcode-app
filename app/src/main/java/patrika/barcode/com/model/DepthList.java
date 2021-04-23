package patrika.barcode.com.model;

import java.util.List;

public class DepthList {

    /**
     * response : {"isSuccess":"true","error":null,"data":[{"containerno":"MRKU5518742","supplierreelno":"4345921201781558","supp_gross_wt":178,"supp_net_wt":176,"supp_receipt_wt":176,"reel_gsm":42,"reel_mm":35,"reel_lenght":12035,"depthcut":0,"damagereason":""},{"containerno":"MRKU5518742","supplierreelno":"4345921301781558","supp_gross_wt":178,"supp_net_wt":176,"supp_receipt_wt":176,"reel_gsm":42,"reel_mm":35,"reel_lenght":12035,"depthcut":0,"damagereason":"ANGLE CUTT"}]}
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
         * data : [{"containerno":"MRKU5518742","supplierreelno":"4345921201781558","supp_gross_wt":178,"supp_net_wt":176,"supp_receipt_wt":176,"reel_gsm":42,"reel_mm":35,"reel_lenght":12035,"depthcut":0,"damagereason":""},{"containerno":"MRKU5518742","supplierreelno":"4345921301781558","supp_gross_wt":178,"supp_net_wt":176,"supp_receipt_wt":176,"reel_gsm":42,"reel_mm":35,"reel_lenght":12035,"depthcut":0,"damagereason":"ANGLE CUTT"}]
         */

        private String isSuccess;
        private Object error;
        private List<DataBean> data;

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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * containerno : MRKU5518742
             * supplierreelno : 4345921201781558
             * supp_gross_wt : 178
             * supp_net_wt : 176
             * supp_receipt_wt : 176
             * reel_gsm : 42
             * reel_mm : 35
             * reel_lenght : 12035
             * depthcut : 0
             * damagereason :
             */

            private String containerno;
            private String supplierreelno;
            private String pressreelno;
            private double supp_gross_wt;
            private double supp_net_wt;
            private double supp_receipt_wt;
            private double reel_gsm;
            private double reel_mm;
            private double reel_lenght;
            private double depthcut;
            private String damagereason;


            public String getContainerno()
            {
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
            public String getPressreelno() {
                return pressreelno;
            }

            public void setPressreelno(String pressreelno) {
                this.pressreelno = pressreelno;
            }

            public double getSupp_gross_wt() {
                return supp_gross_wt;
            }

            public void setSupp_gross_wt(int supp_gross_wt) {
                this.supp_gross_wt = supp_gross_wt;
            }

            public double getSupp_net_wt() {
                return supp_net_wt;
            }

            public void setSupp_net_wt(int supp_net_wt) {
                this.supp_net_wt = supp_net_wt;
            }

            public double getSupp_receipt_wt() {
                return supp_receipt_wt;
            }

            public void setSupp_receipt_wt(int supp_receipt_wt) {
                this.supp_receipt_wt = supp_receipt_wt;
            }

            public double getReel_gsm() {
                return reel_gsm;
            }

            public void setReel_gsm(int reel_gsm) {
                this.reel_gsm = reel_gsm;
            }

            public double getReel_mm() {
                return reel_mm;
            }

            public void setReel_mm(int reel_mm) {
                this.reel_mm = reel_mm;
            }

            public double getReel_lenght() {
                return reel_lenght;
            }

            public void setReel_lenght(int reel_lenght) {
                this.reel_lenght = reel_lenght;
            }

            public double getDepthcut() {
                return depthcut;
            }

            public void setDepthcut(int depthcut) {
                this.depthcut = depthcut;
            }

            public String getDamagereason() {
                return damagereason;
            }

            public void setDamagereason(String damagereason) {
                this.damagereason = damagereason;
            }
        }
    }
}
