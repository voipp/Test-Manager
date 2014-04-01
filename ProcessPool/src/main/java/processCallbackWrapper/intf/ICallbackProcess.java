package processCallbackWrapper.intf;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 25.02.14
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public interface ICallbackProcess {

    ICallbackFunction callback = null;

    Process process = null;

    String arg = null;

    public int waitFor() throws Exception;
}
