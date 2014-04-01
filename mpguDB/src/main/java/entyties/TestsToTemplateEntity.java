package entyties;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 28.02.14
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "tests_to_template", schema = "", catalog = "mpgu_new")
@Entity
public class TestsToTemplateEntity {
    private int             id;
    private TemplatesEntity templatesEntity;
    private TestsEntity     testsEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    public TestsEntity getTestsEntity() {
        return testsEntity;
    }

    public void setTestsEntity(TestsEntity testsEntity) {
        this.testsEntity = testsEntity;
    }

    @ManyToOne
    @JoinColumn(name = "template_id")
    public TemplatesEntity getTemplatesEntity() {
        return templatesEntity;
    }

    public void setTemplatesEntity(TemplatesEntity templatesEntity) {
        this.templatesEntity = templatesEntity;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestsToTemplateEntity)) return false;

        TestsToTemplateEntity that = (TestsToTemplateEntity) o;

        if (templatesEntity != null ? !templatesEntity.equals(that.templatesEntity) :
                that.templatesEntity != null) return false;
        if (testsEntity != null ? !testsEntity.equals(that.testsEntity) : that.testsEntity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = templatesEntity != null ? templatesEntity.hashCode() : 0;
        result = 31 * result + (testsEntity != null ? testsEntity.hashCode() : 0);
        return result;
    }
}
