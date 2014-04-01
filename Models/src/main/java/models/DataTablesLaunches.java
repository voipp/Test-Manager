package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 03.03.14
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement
public class DataTablesLaunches {

    private int     iTotalDisplayRecords;
    private int     iTotalRecords;
    @XmlElement(name = "aaData")
    private List<?> aaData;
    private String  sEcho;
    @XmlElement(name = "params_desc")
    private List<?> paramsAndDescriptions = null;

    public List<?> getParams_desc() {
        return paramsAndDescriptions;
    }

    public void setParams_desc(List<? extends Object> paramsAndDescriptions) {
        this.paramsAndDescriptions = paramsAndDescriptions;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public List<?> getAaData() {
        return aaData;
    }

    public void setAaData(List<? extends Object> aaData) {
        this.aaData = aaData;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }
}
