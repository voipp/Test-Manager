package processCallbackWrapper.impl;

import org.apache.log4j.Logger;
import processCallbackWrapper.intf.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 25.02.14
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public class CallbackProcess implements ICallbackProcess {

    static Logger log = Logger.getLogger("ProcessPool");

    private ICallbackFunction callback;

    private Process process;

    List<String> arg;

    public CallbackProcess(Process process, ICallbackFunction callback, List<String> arg) {
        this.callback = callback;
        this.process = process;
        this.arg = arg;
    }

    @Override
    public int waitFor() throws Exception {
        log.debug("waitFor() process");
        int result = process.waitFor();
        ArrayList<String> args = (ArrayList<String>) arg;
        args.add("-result:" + result + ";");
        log.debug("call() callnack function with args");
        callback.call(args);
        return result;
    }
}
