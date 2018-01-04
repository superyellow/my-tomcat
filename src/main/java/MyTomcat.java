import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author simple_huang@foxmail.com on 2018/1/4.
 */
public class MyTomcat {
    private int port = 8080;
    private Map<String, String> urlServletMap = new HashMap<String, String>();

    public MyTomcat(int port) {
        this.port = port;
    }

    public void start() {
        //初始化url与对应
        initServletMapping();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("my tomcat is starting");
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                MyRequest request = new MyRequest(inputStream);
                MyResponse response = new MyResponse(outputStream);
                dispatch(request, response);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initServletMapping() {
        for (ServletMapping servletMapping: ServletConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }

    private void dispatch(MyRequest request, MyResponse response) {
        String clazz = urlServletMap.get(request.getUrl());

        //反射
        Class<MyServlet> servletClass = null;
        try {
            servletClass = (Class<MyServlet>) Class.forName(clazz);
            MyServlet servlet = servletClass.newInstance();
            servlet.service(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new MyTomcat(8090).start();
    }
}
