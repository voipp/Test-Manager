package models;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 05.03.14
 * Time: 19:04
 * To change this template use File | Settings | File Templates.
 */
public class TestData {
    private String test_name;
    private String test_path;
    private String id;

    public TestData() {
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getTest_path() {
        return test_path;
    }

    public void setTest_path(String test_path) {
        this.test_path = test_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
