package tyrant.body;

import javax.servlet.http.Part;
import java.util.Map;

/**
 * Created by zhangli on 24/5/2017.
 */
public class WSDataVo {

    private String url;
    private String type;
    private Map<String,String> headers;
    private Map<String,String> parameters;
    private String json;
    private boolean noSign;
    Part filePart;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public boolean isNoSign() {
        return noSign;
    }

    public void setNoSign(boolean noSign) {
        this.noSign = noSign;
    }

    public Part getFilePart() {
        return filePart;
    }

    public void setFilePart(Part filePart) {
        this.filePart = filePart;
    }
}
