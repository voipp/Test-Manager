package controller.Initializer;

import executorPool.ExecutorPool;
import processCallbackWrapper.impl.CallbackProcessBuilder;
import processCallbackWrapper.intf.ICallbackFunction;
import processFactory.ProcessFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 18.03.14
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */

public class ContextInitializer implements ServletContextListener {

    /*
    Приложение ожидает окончания выполнения всех запущенных им тестов.
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        ServletContext servletContext = arg0.getServletContext();
        ExecutorPool executorPool = (ExecutorPool) servletContext.getAttribute("ProcessExecutorPool");
        try {
            executorPool.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("ServletContextListener destroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        ServletContext servletContext = arg0.getServletContext();
        Enumeration<String> names = servletContext.getInitParameterNames();
        Integer maximumProcessPoolSize = Integer
                .valueOf(servletContext.getInitParameter("maximum processpool size"));
        Integer coreProcessPoolSize = Integer
                .valueOf(servletContext.getInitParameter("core processpool size"));

        ExecutorPool executorPool = new ExecutorPool(maximumProcessPoolSize, coreProcessPoolSize);

        servletContext.setAttribute("process executor pool", executorPool);



        String executableJar = servletContext.getInitParameter("executable jar");
        String jarParams = servletContext.getInitParameter("jar parameters");
        String workingDir = servletContext.getInitParameter("working dir");



        ProcessBuilder processBuilder = ProcessFactory.createJarProcess(executableJar,
                Arrays.asList(jarParams.split(",")), workingDir);

        servletContext.setAttribute("process builder", processBuilder);

        System.out.println("ServletContextListener started");
    }
}