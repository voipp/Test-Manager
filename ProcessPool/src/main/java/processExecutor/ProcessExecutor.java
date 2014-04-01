package processExecutor;

import processCallbackWrapper.intf.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 21.02.14
 * Time: 16:23
 * To change this template use File | Settings | File Templates.
 */
public class ProcessExecutor extends Thread {

    static Logger log = Logger.getLogger("ProcessPool");

    private BlockingQueue<ICallbackProcessBuilder> queue;

    public void shutdown(Boolean stop) {
        isStop = stop;
    }

    private Boolean isStop = false;



    public ProcessExecutor(BlockingQueue<ICallbackProcessBuilder> queue) {

        this.queue = queue;
        log.debug("constructor processExecutor() with queue size= " + queue.size());
        this.setName("Process executor");
        super.start();
    }


    //добавляет в среду выполнения каждого процесса его вызвавшую команду

    @Override
    public void run() {
        System.out.println("start processExecutor, with isStop= " + isStop);
        while (!(isStop && queue.isEmpty())) {
            try {

                log.debug("queue size: " + queue.size());
                log.debug("trying to take() ProcessBuilder from queue");

                ICallbackProcessBuilder processBuilder = queue.take();

                String command = new String("");
                for (String str : processBuilder.command()){
                    command += str + ";";
                }

                log.debug("taken ProcessBuilder, with command:");
                for (String s : processBuilder.command()) {
                    System.out.print(s + ",  ");
                }
                log.debug("starting processBuilder()");
                ICallbackProcess process = processBuilder.start();
                log.debug("waiting for process to quit");
                int result = process.waitFor();
                log.debug("process ended with code: " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    }
}
