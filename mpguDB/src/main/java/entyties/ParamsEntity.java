package entyties;


import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 28.02.14
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "params")
@Entity
public class ParamsEntity {
    private int                        paramsId;
    private String                     paramName;
    private List<ParamsTemplateEntity> paramsTemplateEntity;
    private String                     description;

    @OneToMany(mappedBy = "paramsEntity" ,cascade = CascadeType.ALL)
    public List<ParamsTemplateEntity> getParamsTemplateEntity() {
        return paramsTemplateEntity;
    }

    public void setParamsTemplateEntity(List<ParamsTemplateEntity> paramsTemplateEntity) {
        this.paramsTemplateEntity = paramsTemplateEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParamsEntity)) return false;

        ParamsEntity that = (ParamsEntity) o;

        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (paramName != null ? !paramName.equals(that.paramName) : that.paramName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paramName != null ? paramName.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "params_id")
    @Id
    public int getParamsId() {
        return paramsId;

    }

    public void setParamsId(int paramsId) {
        this.paramsId = paramsId;
    }

    @javax.persistence.Column(name = "param_name")
    @Basic
    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    @Column(name = "description")
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
