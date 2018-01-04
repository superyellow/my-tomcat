import java.util.ArrayList;
import java.util.List;

/**
 * @author simple_huang@foxmail.com on 2018/1/4.
 */
public class ServletConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<ServletMapping>();
    static {
        servletMappingList.add(new ServletMapping("helloWorld", "/girl", "HelloServlet"));
    }
}
