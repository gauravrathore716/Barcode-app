package patrika.barcode.com.model;

import java.util.List;

public class DepthReason {


    /**
     * response : {"isSuccess":"true","error":null,"data":[{"Reason_Code":"TC0001","Reason_Name":"SIDE CUT"},{"Reason_Code":"TC0002","Reason_Name":"CRUSH BY OTHER REEL"},{"Reason_Code":"TA0001","Reason_Name":"ANGLE CUTT"},{"Reason_Code":"TC0003","Reason_Name":"CRUSH BY TRUCK BODY"},{"Reason_Code":"TD0001","Reason_Name":"DALA CUTT"},{"Reason_Code":"TW0001","Reason_Name":"WATER SOAKED"},{"Reason_Code":"TD0002","Reason_Name":"DAMAGE BY FORKLIFT"},{"Reason_Code":"TS0001","Reason_Name":"SABAL CUT"},{"Reason_Code":"TF0001","Reason_Name":"FLOOR BOLT/ OTHER DAMAGE"},{"Reason_Code":"TF0002","Reason_Name":"FALLEN REEL"},{"Reason_Code":"TF0003","Reason_Name":"FLOOR DAMAGED"},{"Reason_Code":"TT0001","Reason_Name":"TERMITED REEL"},{"Reason_Code":"TD0003","Reason_Name":"DAMAGE BY CONTAINER"}]}
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
         * data : [{"Reason_Code":"TC0001","Reason_Name":"SIDE CUT"},{"Reason_Code":"TC0002","Reason_Name":"CRUSH BY OTHER REEL"},{"Reason_Code":"TA0001","Reason_Name":"ANGLE CUTT"},{"Reason_Code":"TC0003","Reason_Name":"CRUSH BY TRUCK BODY"},{"Reason_Code":"TD0001","Reason_Name":"DALA CUTT"},{"Reason_Code":"TW0001","Reason_Name":"WATER SOAKED"},{"Reason_Code":"TD0002","Reason_Name":"DAMAGE BY FORKLIFT"},{"Reason_Code":"TS0001","Reason_Name":"SABAL CUT"},{"Reason_Code":"TF0001","Reason_Name":"FLOOR BOLT/ OTHER DAMAGE"},{"Reason_Code":"TF0002","Reason_Name":"FALLEN REEL"},{"Reason_Code":"TF0003","Reason_Name":"FLOOR DAMAGED"},{"Reason_Code":"TT0001","Reason_Name":"TERMITED REEL"},{"Reason_Code":"TD0003","Reason_Name":"DAMAGE BY CONTAINER"}]
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
             * Reason_Code : TC0001
             * Reason_Name : SIDE CUT
             */

            private String Reason_Code;
            private String Reason_Name;
            public String Depth_Reason;

            public String getReason_Code() {
                return Reason_Code;
            }

            public void setReason_Code(String Reason_Code) {
                this.Reason_Code = Reason_Code;
            }

            public String getReason_Name() {
                return Reason_Name;
            }

            public void setReason_Name(String Reason_Name) {
                this.Reason_Name = Reason_Name;
            }


//            public void setDepth_Reason(String Depth_Reason) {
//                this.Depth_Reason = Depth_Reason;
//
//            }
        }
    }
}
