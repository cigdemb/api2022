package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyResponseBodyPojo {


        private String status;
        private DummyApiPojo data;
        private String message;


        public DummyResponseBodyPojo() {
        }


        public DummyResponseBodyPojo(String status, DummyApiPojo data, String message) {
            this.status = status;
            this.data = data;
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public DummyApiPojo getData() {
            return data;
        }

        public void setData(DummyApiPojo data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    @Override
    public String toString() {
        return "DummyResponseBodyPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
