package entyties;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 28.02.14
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
@javax.persistence.Table(name = "tests", schema = "", catalog = "mpgu_new")
@Entity
public class TestsEntity {
    private int                         id;
    private String                      path;
    private String                      name;
    private List<TestsToTemplateEntity> testsToTemplateEntityList;

    @OneToMany(mappedBy = "testsEntity", cascade = CascadeType.ALL)
    public List<TestsToTemplateEntity> getTestsToTemplateEntityList() {
        return testsToTemplateEntityList;
    }

    public void setTestsToTemplateEntityList(List<TestsToTemplateEntity> testsToTemplateEntityList) {
        this.testsToTemplateEntityList = testsToTemplateEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestsEntity)) return false;

        TestsEntity that = (TestsEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "test_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @javax.persistence.Column(name = "path", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @javax.persistence.Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
