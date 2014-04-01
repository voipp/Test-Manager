package processCallbackWrapper.intf;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 25.02.14
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
public interface ICallbackProcessBuilder {

    public List<String> command();

    public ICallbackProcess start() throws IOException;

    public ProcessBuilder directory(File file);
}
