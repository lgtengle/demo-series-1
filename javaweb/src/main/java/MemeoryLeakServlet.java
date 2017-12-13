import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/12/3 21:21
 *
 * @author leiguang
 */
public class MemeoryLeakServlet extends HttpServlet{

    @Override
    public void init() throws ServletException {

        System.out.println("开始喽...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("睡眠30s开始....");
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("启动内存测试....");
                List<String> strs = new ArrayList<>();
                int i = 1725852;

                while(true) {
                    strs.add(String.valueOf(i++).intern());
                }
            }
        }).start();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        resp.getWriter().print("hello world!");
    }
}
