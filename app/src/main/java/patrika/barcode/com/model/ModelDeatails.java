package patrika.barcode.com.model;

import java.util.List;

public class ModelDeatails {


    /**
     * response : {"isSuccess":"true","data":[{"pub_code":"P04","pub_name":"BALHANS"},{"pub_code":"P02","pub_name":"CHOTU MOTU"},{"pub_code":"P11","pub_name":"DAILY NEWS"},{"pub_code":"P09","pub_name":"NEWS TODAY"},{"pub_code":"P17","pub_name":"NEWS TODAY JAIPUR"},{"pub_code":"P15","pub_name":"PATRIKA"},{"pub_code":"P03","pub_name":"PUBLICATION BOOK"},{"pub_code":"P01","pub_name":"RAJASTHAN PATRIKA"},{"pub_code":"P14","pub_name":"RAJASTHAN PATRIKA CITY"},{"pub_code":"P12","pub_name":"SUPPLIMENT"},{"pub_code":"P10","pub_name":"YEAR BOOK ENGLSH"},{"pub_code":"P13","pub_name":"YEAR BOOK HINDI"}],"error":null}
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
         * data : [{"pub_code":"P04","pub_name":"BALHANS"},{"pub_code":"P02","pub_name":"CHOTU MOTU"},{"pub_code":"P11","pub_name":"DAILY NEWS"},{"pub_code":"P09","pub_name":"NEWS TODAY"},{"pub_code":"P17","pub_name":"NEWS TODAY JAIPUR"},{"pub_code":"P15","pub_name":"PATRIKA"},{"pub_code":"P03","pub_name":"PUBLICATION BOOK"},{"pub_code":"P01","pub_name":"RAJASTHAN PATRIKA"},{"pub_code":"P14","pub_name":"RAJASTHAN PATRIKA CITY"},{"pub_code":"P12","pub_name":"SUPPLIMENT"},{"pub_code":"P10","pub_name":"YEAR BOOK ENGLSH"},{"pub_code":"P13","pub_name":"YEAR BOOK HINDI"}]
         * error : null
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
             * pub_code : P04
             * pub_name : BALHANS
             */

            private String pub_code;
            private String pub_name;

            public String getPub_code() {
                return pub_code;
            }

            public void setPub_code(String pub_code) {
                this.pub_code = pub_code;
            }

            public String getPub_name() {
                return pub_name;
            }

            public void setPub_name(String pub_name) {
                this.pub_name = pub_name;
            }
        }
    }
}
