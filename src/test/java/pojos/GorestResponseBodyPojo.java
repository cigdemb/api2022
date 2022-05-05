package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GorestResponseBodyPojo {
    private Object meta;
    private GoRestDataPojo data;

    public GorestResponseBodyPojo() {
    }

    public GorestResponseBodyPojo(Object meta, GoRestDataPojo gorestPojo) {
        this.meta = meta;
        this.data = gorestPojo;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GorestResponseBodyPojo{" +
                "meta=" + meta +
                ", gorestPojo=" + data +
                '}';
    }
}
