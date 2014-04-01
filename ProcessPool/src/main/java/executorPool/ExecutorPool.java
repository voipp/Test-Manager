package executorPool;

import org.apache.log4j.Logger;
import processCallbackWrapper.impl.CallbackProcessBuilder;
import processCallbackWrapper.intf.ICallbackProcessBuilder;
import processExecutor.ProcessExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 21.02.14
 * Time: 16:37
 * To change this template use File | Settings | File Templates.
 */

/*
ленивый пул процессов
 */
public class ExecutorPool {

    //private static ExecutorPool                           this     = null;
    private        Logger                                 log          = Logger
            .getLogger("ProcessPool");
    private        List<ProcessExecutor>                  procexecs    = new ArrayList<ProcessExecutor>(1);
    private        Boolean                                isStop       = false;
    private        Integer                                corePoolSize = 1;
    private        BlockingQueue<ICallbackProcessBuilder> queue        = null;


    public ExecutorPool(Integer maximumPoolSize, Integer corePoolSize) {
        log.debug("constructor executorPool()");
        this.corePoolSize = corePoolSize;
        if (maximumPoolSize.equals(-1)) {
            this.queue = new LinkedBlockingQueue<ICallbackProcessBuilder>();
        } else {
            this.queue = new LinkedBlockingQueue<ICallbackProcessBuilder>(maximumPoolSize);
        }
    }

    private ExecutorPool() {}

//    public static ExecutorPool getInstance() {
//        this = new ExecutorPool(-1, 1);
//        return this;
//    }

//    public static ExecutorPool getInstance(Integer maximumPoolSize, Integer corePoolSize) {
//        this = new ExecutorPool(maximumPoolSize, corePoolSize);
//        return this;
//    }

    public synchronized void execute(ProcessBuilder pb) {

        log.debug("execute() ProcessBuilder");
        if (this.isStop) throw new IllegalStateException("thread pool is stopped, " +
                "no executions allowed");


        Boolean isAllExecBusy;
        log.debug("checking if isAllExecBusy...");
        if (this.procexecs.size() > this.queue.size()) {
            isAllExecBusy = false;
        } else {
            isAllExecBusy = true;
        }
        log.debug("isAllExecBusy= " + isAllExecBusy);

        // все executor ы  заняты, проверяем можно ли создать еще 1 executor
        if (isAllExecBusy && this.procexecs.size() < this.corePoolSize) {
            log.debug("new processExecutor()");
            this.procexecs.add(new ProcessExecutor(queue));
        }

        log.debug("queue.add(pb) with command: " + pb.command().get(0));
        // коллбэка нет, так что ставим заглушку

        this.queue.add(new CallbackProcessBuilder(pb));

    }

    public synchronized void execute(CallbackProcessBuilder pb) {

        log.debug("execute() ProcessBuilder");
        if (this.isStop) throw new IllegalStateException("thread pool is stopped, " +
                "no executions allowed");


        Boolean isAllExecBusy;
        log.debug("checking if isAllExecBusy...");
        if (this.procexecs.size() > this.queue.size()) {
            isAllExecBusy = false;
        } else {
            isAllExecBusy = true;
        }
        log.debug("isAllExecBusy= " + isAllExecBusy);

        // все executor ы  заняты, проверяем можно ли создать еще 1 executor
        if (isAllExecBusy && this.procexecs.size() < this.corePoolSize) {
            log.debug("new processExecutor()");
            this.procexecs.add(new ProcessExecutor(queue));
        }

        log.debug("queue.add(pb) with command: " + pb.command().get(0));

        this.queue.add(pb);

    }

    public synchronized void shutdown() {
        this.isStop = true;
        for (ProcessExecutor procexec : this.procexecs) {
            procexec.shutdown(true);
        }
    }

    public synchronized void waitFor() throws InterruptedException {
        shutdown();
        for (ProcessExecutor procexec : this.procexecs) {
            procexec.join();
        }
    }

}
