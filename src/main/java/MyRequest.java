import java.io.IOException;
import java.io.InputStream;

/**
 * @author simple_huang@foxmail.com on 2018/1/4.
 */
public class MyRequest {
    private String url;
    private String method;

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if ((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes, 0, length);
        }

        String httpHead = httpRequest.split("\n")[0];
        String[] httpHeadSplit = httpHead.split("\\s");
        url = httpHeadSplit[1];
        method = httpHeadSplit[0];
        System.out.println(httpRequest);


    }
}
