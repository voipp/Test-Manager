package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 06.03.14
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement
public class TestResult {

    @XmlElement(name = "test_name")
    private String testName;

    @XmlElement(name = "result")
    private String result;

    @XmlElement(name = "log_path")
    private String logPath;

    public TestResult() {
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
}
