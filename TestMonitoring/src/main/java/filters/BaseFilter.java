package filters;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 14.02.14
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */

@WebFilter(
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "Allow outer connections", value = "true"),
                @WebInitParam(name = "log4j-properties-location",
                        value = "WEB-INF\\log4j.xml")
        }

)
public class BaseFilter implements Filter {

    FilterConfig fc;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init the filter");
        this.fc = filterConfig;
        String log4jLocation = filterConfig.getInitParameter("log4j-properties-location");

        ServletContext sc = filterConfig.getServletContext();

        if (log4jLocation == null) {
            System.out.println("No log4j properites...");
            BasicConfigurator.configure();
        } else {
            String webAppPath = sc.getRealPath("/");
            String log4jProp = webAppPath + log4jLocation;
            File output = new File(log4jProp);

            if (output.exists()) {
                System.out.println("Initialising log4j with: " + log4jProp);
                DOMConfigurator.configure(log4jProp);
            } else {
                System.out.println("File not found (" + log4jProp + ").");
                BasicConfigurator.configure();
            }
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("hello from filter");

        if (Boolean.parseBoolean(fc.getInitParameter("Allow outer connections"))
               || (((HttpServletRequest)servletRequest).getRequestURI()
                .equals("/TestManager3/images/UnderConstructionPage.gif"))
                ) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (!servletRequest.getRemoteHost().equals("127.0.0.1")) {
                // происходит циклическая переадресация
                //((HttpServletResponse) servletResponse).sendRedirect("UnderConstruction.jsp");
                //return;
                servletRequest.getRequestDispatcher("UnderConstruction.html").forward
                        (servletRequest, servletResponse);

            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
