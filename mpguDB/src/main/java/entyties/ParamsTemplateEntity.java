package entyties;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 28.02.14
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "params_template", schema = "", catalog = "mpgu_new")
@Entity
public class ParamsTemplateEntity {
    private int             id;
    private String          paramValue;
    private ParamsEntity    paramsEntity;
    private TemplatesEntity templatesEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParamsTemplateEntity)) return false;

        ParamsTemplateEntity that = (ParamsTemplateEntity) o;

        if (paramValue != null ? !paramValue.equals(that.paramValue) : that.paramValue != null)
            return false;
        if (paramsEntity != null ? !paramsEntity.equals(that.paramsEntity) :
                that.paramsEntity != null)
            return false;
        if (templatesEntity != null ? !templatesEntity.equals(that.templatesEntity) :
                that.templatesEntity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paramValue != null ? paramValue.hashCode() : 0;
        result = 31 * result + (paramsEntity != null ? paramsEntity.hashCode() : 0);
        result = 31 * result + (templatesEntity != null ? templatesEntity.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "template_id")
    public TemplatesEntity getTemplatesEntity() {

        return templatesEntity;
    }

    public void setTemplatesEntity(TemplatesEntity templatesEntity) {
        this.templatesEntity = templatesEntity;
    }

    @ManyToOne
    @JoinColumn(name = "params_id")
    public ParamsEntity getParamsEntity() {
        return paramsEntity;
    }

    public void setParamsEntity(ParamsEntity paramsEntity) {
        this.paramsEntity = paramsEntity;
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

    @javax.persistence.Column(name = "param_value", nullable = true, insertable = true, updatable = true, length = 5, precision = 0)
    @Basic
    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

}
