package entyties;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 28.02.14
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
@javax.persistence.Table(name = "templates", schema = "", catalog = "mpgu_new")
@Entity
public class TemplatesEntity {
    private Integer                     tempId;
    private List<ParamsTemplateEntity>  paramsTemplateEntityList;
    @XmlElement(name = "template_name")
    private String                      templateName;
    private String                      description;
    private Set<TestsEntity>            testsEntities;

//    private LaunchesEntity launchesEntity;
//
//    @OneToOne(mappedBy = "templatesEntity")
//    public LaunchesEntity getLaunchesEntity() {
//        return launchesEntity;
//    }
//
//    public void setLaunchesEntity(LaunchesEntity launchesEntity) {
//        this.launchesEntity = launchesEntity;
//    }

    @Fetch(value = FetchMode.JOIN)
    @ManyToMany()
    @JoinTable(name = "tests_to_template",
            joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    public Set<TestsEntity> getTestsEntities() {
        return testsEntities;
    }

    public void setTestsEntities(Set<TestsEntity> testsEntities) {
        this.testsEntities = testsEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemplatesEntity)) return false;

        TemplatesEntity that = (TemplatesEntity) o;

        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (templateName != null ? !templateName.equals(that.templateName) :
                that.templateName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = templateName != null ? templateName.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Fetch(value = FetchMode.JOIN)
    @OneToMany(mappedBy = "templatesEntity", cascade = CascadeType.ALL)
    public List<ParamsTemplateEntity> getParamsTemplateEntityList() {
        return paramsTemplateEntityList;
    }

    public void setParamsTemplateEntityList(List<ParamsTemplateEntity> paramsTemplateEntityList) {
        this.paramsTemplateEntityList = paramsTemplateEntityList;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "temp_id", insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public Integer getTempId() {
        return tempId;
    }

    public void setTempId(Integer tempId) {
        this.tempId = tempId;
    }

    @javax.persistence.Column(name = "template_name", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @javax.persistence.Column(name = "description", nullable = true, insertable = true, updatable = true, length = 65535, precision = 0)
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
