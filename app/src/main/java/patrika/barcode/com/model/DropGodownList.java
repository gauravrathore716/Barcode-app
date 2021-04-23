
package patrika.barcode.com.model;

import java.util.List;

public class DropGodownList {


    /**
     * response : {"isSuccess":"true","error":null,"data":[{"godowncode":"G021","godownname":"M/H - BANGALORE ( GEETANJALI )"},{"godowncode":"G019","godownname":"M/H - BANGALORE ( PEENIYA)"},{"godowncode":"G020","godownname":"M/H - BANGALORE ( RAJAJINAGAR )"}]}
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
         * data : [{"godowncode":"G021","godownname":"M/H - BANGALORE ( GEETANJALI )"},{"godowncode":"G019","godownname":"M/H - BANGALORE ( PEENIYA)"},{"godowncode":"G020","godownname":"M/H - BANGALORE ( RAJAJINAGAR )"}]
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
             * godowncode : G021
             * godownname : M/H - BANGALORE ( GEETANJALI )
             */

            private String godowncode;
            private String godownname;

            public String getGodowncode() {
                return godowncode;
            }

            public void setGodowncode(String godowncode) {
                this.godowncode = godowncode;
            }

            public String getGodownname() {
                return godownname;
            }

            public void setGodownname(String godownname) {
                this.godownname = godownname;
            }
        }
    }
}
