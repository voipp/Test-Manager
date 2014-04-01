package tests;

import executorPool.ExecutorPool;
import org.apache.log4j.Logger;
import processFactory.ProcessFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 21.02.14
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */

/*
класс для тестирвоания ProcessPool
 */
public class Tests {

    static Logger log = Logger.getLogger("ProcessPool");
    static Object o   = new Object();

    public static void main(String[] args) throws IOException, InterruptedException {

        ExecuteMPGUJar();

//        executorPool ep = new executorPool(-1, 1);
//        ep.execute(processFactory.createCustomProcess(Arrays.asList("notepad")));
//        ep.execute(processFactory.createCustomProcess(Arrays.asList("cmd", "/c",
//                "start", "/wait")));
//        ep.shutdown();
//        System.out.println("meow");
//        Map<String, String> env = System.getenv();
//        System.out.println("environment params:");
//        for (Map.Entry<String, String> stringStringEntry : env.entrySet()) {
//            System.out.println("key, value: " + stringStringEntry.getKey() + ", " +
//                    "" + stringStringEntry.getValue());
//        }
//        System.out.println("environment properties: ");
//        Properties prop = System.getProperties();
//
//        for (Map.Entry<Object, Object> objectObjectEntry : prop.entrySet()) {
//            System.out.println("key, value: " + String.valueOf(objectObjectEntry.getKey()) + ", " +
//                    "" + String.valueOf(objectObjectEntry.getValue()));
//        }


    }

    private static void ExecuteMPGUJar() throws IOException, InterruptedException {


        ProcessBuilder procBuilder = ProcessFactory.createJarProcess
                ("D:\\subversion\\mpgu\\Gosuslugi\\runner.jar", Arrays.asList("-testId:1001",
                        "-serviceId:1001",
                        "-runner:D:\\subversion\\mpgu\\Gosuslugi\\epgu_result\\tests\\Run1.runner.xml"), "D:\\subversion\\mpgu\\Gosuslugi");

        Map<String, String> env2 = System.getenv();
        boolean env = System.getenv().get("Path").contains("C:\\Program Files\\Java\\jdk1.7.0_25\\bin");
        ExecutorPool ep = new ExecutorPool(-1, 1);
        ep.execute(procBuilder);
        ep.shutdown();
        ep.waitFor();

//        executorPool ep = new executorPool(-1, 1);
//        ep.execute(processFactory.createCustomProcess(Arrays.asList("cmd.exe",
//                "D:/subversion/mpgu/Gosuslugi/start_tests.bat", "/C")));
//        ep.shutdown();
    }

    private static void CompareStrings(String s1, String s2) {

        String classPathTestMonitoring;

        if (s1 == null) {

            classPathTestMonitoring = "C:\\Program Files\\Java\\jdk1.7" +
                    ".0_25\\jre\\lib\\charsets.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\deploy.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\javaws.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\jce.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\jfr.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\jfxrt.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\jsse.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\management-agent.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\plugin.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\resources.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\rt.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\access-bridge-64.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\dnsns.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\jaccess.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\localedata.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\sunec.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\sunjce_provider.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\sunmscapi.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\zipfs.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\fscontext.jar;D:\\subversion\\IDEA\\TestManager\\out\\production\\ProcessPool;D:\\subversion\\IDEA\\TestManager\\lib\\log4j-1.2.17.jar;D:\\subversion\\IDEA\\TestManager\\out\\production\\TestMonitoring;D:\\subversion\\IDEA\\TestManager\\lib\\javax.annotation.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.ejb.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.jms.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.persistence.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.transaction.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.resource.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.servlet.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.servlet.jsp.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.servlet.jsp.jstl.jar;D:\\subversion\\IDEA\\TestManager\\lib\\junit-4.10.jar;D:\\subversion\\IDEA\\TestManager\\lib\\json-simple-1.1.1.jar;D:\\subversion\\IDEA\\TestManager\\lib\\mysql-connector-java-5.1.26-bin.jar;D:\\subversion\\IDEA\\TestManager\\out\\production\\mpguDB;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\hibernate-core-4.2.2.Final.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\hibernate-jpa-2.0-api-1.0.1.Final.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\hibernate-commons-annotations-4.0.2.Final.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\antlr-2.7.7.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\dom4j-1.6.1.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\javassist-3.15.0-GA.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\jboss-logging-3.1.0.GA.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\jboss-transaction-api_1.1_spec-1.0.1.Final.jar;D:\\subversion\\IDEA\\TestManager\\lib\\slf4j-api-1.7.6.jar;D:\\subversion\\IDEA\\TestManager\\lib\\slf4j-log4j12-1.7.6.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\hibernate-c3p0-4.1.1.Final.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\mchange-commons-java-0.2.6.3.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\c3p0-0.9.5-pre6.jar;C:\\Program Files (x86)\\JetBrains\\IntelliJ IDEA 12.0\\lib\\idea_rt.jar\n";

        } else {
            classPathTestMonitoring = s1;
        }
        String classPathProcessPool;

        if (s2 == null) {
            classPathProcessPool = "C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\charsets" +
                    ".jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\deploy.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\javaws.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\jce.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\jfr.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\jfxrt.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\jsse.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\management-agent.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\plugin.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\resources.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\rt.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\access-bridge-64.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\dnsns.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\jaccess.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\localedata.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\sunec.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\sunjce_provider.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\sunmscapi.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\ext\\zipfs.jar;C:\\Program Files\\Java\\jdk1.7.0_25\\jre\\lib\\fscontext.jar;D:\\subversion\\IDEA\\TestManager\\out\\production\\TestMonitoring;D:\\subversion\\IDEA\\TestManager\\lib\\javax.annotation.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.ejb.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.jms.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.persistence.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.transaction.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.resource.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.servlet.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.servlet.jsp.jar;D:\\subversion\\IDEA\\TestManager\\lib\\javax.servlet.jsp.jstl.jar;D:\\subversion\\IDEA\\TestManager\\lib\\junit-4.10.jar;D:\\subversion\\IDEA\\TestManager\\lib\\json-simple-1.1.1.jar;D:\\subversion\\IDEA\\TestManager\\lib\\mysql-connector-java-5.1.26-bin.jar;D:\\subversion\\IDEA\\TestManager\\out\\production\\mpguDB;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\hibernate-core-4.2.2.Final.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\hibernate-jpa-2.0-api-1.0.1.Final.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\hibernate-commons-annotations-4.0.2.Final.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\antlr-2.7.7.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\dom4j-1.6.1.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\javassist-3.15.0-GA.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\jboss-logging-3.1.0.GA.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\jboss-transaction-api_1.1_spec-1.0.1.Final.jar;D:\\subversion\\IDEA\\TestManager\\lib\\slf4j-api-1.7.6.jar;D:\\subversion\\IDEA\\TestManager\\lib\\slf4j-log4j12-1.7.6.jar;D:\\subversion\\IDEA\\TestManager\\lib\\log4j-1.2.17.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\hibernate-c3p0-4.1.1.Final.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\mchange-commons-java-0.2.6.3.jar;D:\\subversion\\IDEA\\TestManager\\mpguDB\\lib\\c3p0-0.9.5-pre6.jar;C:\\Program Files (x86)\\JetBrains\\IntelliJ IDEA 12.0\\lib\\idea_rt.jar\n";

        } else {
            classPathProcessPool = s2;
        }


        List cpTestMonit = new ArrayList<String>(Arrays.asList(classPathTestMonitoring.split(";")
        ));

        List cpProcPool = new ArrayList<String>(Arrays.asList(classPathProcessPool.split(";")
        ));

        List<String> intersection = new ArrayList(cpProcPool);

        intersection.retainAll(cpTestMonit);

        List<String> InProcPoolNotInMonit = new ArrayList(cpProcPool);

        InProcPoolNotInMonit.removeAll(cpTestMonit);

        List<String> InMonitNotInProcPool = new ArrayList(cpTestMonit);

        InMonitNotInProcPool.removeAll(cpProcPool);

        for (String o1 : intersection) {
            System.out.println("intersection: " + o1);
        }

        System.out.println("InProcPoolNotInMonit: ");
        for (String o1 : InProcPoolNotInMonit) {
            System.out.println("difference: " + o1);
        }

        System.out.println("InMonitNotInProcPool: ");
        for (String o1 : InMonitNotInProcPool) {
            System.out.println("difference: " + o1);
        }
    }
}
