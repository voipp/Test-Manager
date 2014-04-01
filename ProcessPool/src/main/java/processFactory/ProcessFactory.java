package processFactory;

import processCallbackWrapper.impl.CallbackProcessBuilder;
import processCallbackWrapper.intf.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 21.02.14
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public class ProcessFactory {

    /*
    необходимо что бы выполняемый jar лежал в classpath. И состоял из скомпилированных классов

     */
    public static ProcessBuilder createJarProcess(String jarName, List<String> params,
                                                  String workingDir){
        ArrayList<String> procString = new ArrayList<String>(Arrays.asList(
                "cmd",
                "/c",
                "java",
                "-classpath", System.getProperty("java.class.path"),
                "-Dfile.encoding=UTF-8", "-jar", jarName));
        procString.addAll(params);
        ProcessBuilder procBuilder = new ProcessBuilder(procString);
        procBuilder.directory(new File(workingDir));
        return procBuilder;
    }

    public static CallbackProcessBuilder createJarProcess(String jarName, List<String> params,
                                                  String workingDir, ICallbackFunction callback){
        ArrayList<String> procString = new ArrayList<String>(Arrays.asList(
                "java",
                "-classpath", System.getProperty("java.class.path"),
                "-Dfile.encoding=UTF-8", "-jar", jarName));
        procString.addAll(params);
        CallbackProcessBuilder procBuilder = new CallbackProcessBuilder(procString, callback);
        procBuilder.directory(new File(workingDir));
        return procBuilder;
    }

    public static ProcessBuilder createCustomProcess(List arg){
        return new ProcessBuilder(arg);
    }

    public static CallbackProcessBuilder createCustomProcess(List arg, ICallbackFunction callback){
        return new CallbackProcessBuilder(arg, callback);
    }

}
