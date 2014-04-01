package processCallbackWrapper.impl;

import processCallbackWrapper.intf.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 25.02.14
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
public class CallbackProcessBuilder implements ICallbackProcessBuilder {

    private ProcessBuilder pb = null;

    private ICallbackFunction callback = null;

    private CallbackProcessBuilder() {
    }

    public CallbackProcessBuilder(List<String> list, ICallbackFunction callback) {
        this.pb = new ProcessBuilder(list);
        this.callback = callback;
    }

    public CallbackProcessBuilder(ProcessBuilder pb) {
        this.pb = pb;
        this.pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        this.pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        this.callback = new ICallbackFunction() {

            @Override
            public void call(List<String> arg) {
                return;
            }
        };
    }

    @Override
    public List<String> command() {
        return pb.command();
    }

    @Override
    public ICallbackProcess start() throws IOException {
        Map<String, String> e = System.getenv();
        ICallbackProcess iCallbackProcess = new CallbackProcess(pb.start(), callback,
                pb.command());
        return iCallbackProcess;

    }

    @Override
    public ProcessBuilder directory(File file) {
        return pb.directory(file);
    }
}
