import java.io.IOException;

/**
 * @author simple_huang@foxmail.com on 2018/1/4.
 */
public class HelloServlet extends MyServlet{

    public void doGet(MyRequest request, MyResponse response) {
        try {
            response.write("get the hello world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(MyRequest request, MyResponse response) {
        try {
            response.write("post the hello world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
