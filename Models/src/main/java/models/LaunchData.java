package models;


import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 03.03.14
 * Time: 18:35
 * To change this template use File | Settings | File Templates.
 */

public class LaunchData {

    private String status;
    private String start;
    private int    launch_id;
    private String end;
    private String launch_name;
    private Map    params;

    public String getLaunch_name() {
        return launch_name;
    }

    public void setLaunch_name(String launch_name) {
        this.launch_name = launch_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public int getLaunch_id() {
        return launch_id;
    }

    public void setLaunch_id(int launch_id) {
        this.launch_id = launch_id;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

}
