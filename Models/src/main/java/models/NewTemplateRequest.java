package models;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 11.03.14
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class NewTemplateRequest {

    @XmlElement(name = "sEcho")
    private String                         sEcho;
    @XmlElement(name = "aoData")
    private ArrayList<Integer>             testsIds;
    @XmlElement(name = "oLaunchData")
    private HashMap<String, String> templateData;

    public NewTemplateRequest() {
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public ArrayList<Integer> getTestsIds() {
        return testsIds;
    }

    public void setTestsIds(ArrayList<Integer> testsIds) {
        this.testsIds = testsIds;
    }

    public HashMap<String, String> getTemplateData() {
        return templateData;
    }

    public void setTemplateData(HashMap<String, String> templateData) {
        this.templateData = templateData;
    }
}
