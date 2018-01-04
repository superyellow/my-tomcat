/**
 * @author simple_huang@foxmail.com on 2018/1/4.
 */
public abstract class MyServlet {
    public abstract void doGet(MyRequest request, MyResponse response);
    public abstract void doPost(MyRequest request, MyResponse response);
    public void service(MyRequest request, MyResponse response) {
        if (request.getMethod().equals("POST")) {
            doPost(request, response);
        } else if (request.getMethod().equals("GET")) {
            doGet(request, response);
        }
    }
}
