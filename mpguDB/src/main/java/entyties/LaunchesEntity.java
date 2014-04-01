package entyties;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;


/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 28.02.14
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "launches")
public class LaunchesEntity {
    private int             launchId;
    private String          dateStart;
    private String          dateEnd;
    private String          status;
    private String          dateQuery;
    private ExitCodesEntity exitCodesEntity;
    private ResultEntity    resultEntity;
    private TemplatesEntity templatesEntity;

    @OneToOne(mappedBy = "launchesEntity")
    public ResultEntity getResultEntity() {
        return resultEntity;
    }

    public void setResultEntity(ResultEntity resultEntity) {
        this.resultEntity = resultEntity;
    }
    @Cascade(value = {CascadeType.PERSIST})
    @OneToOne
    @JoinColumn(name = "template_id")
    public TemplatesEntity getTemplatesEntity() {
        return templatesEntity;
    }

    public void setTemplatesEntity(TemplatesEntity templatesEntity) {
        this.templatesEntity = templatesEntity;
    }

    @OneToOne(mappedBy = "launchesEntity")
    public ExitCodesEntity getExitCodesEntity() {
        return exitCodesEntity;
    }

    public void setExitCodesEntity(ExitCodesEntity exitCodesEntity) {
        this.exitCodesEntity = exitCodesEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LaunchesEntity)) return false;

        LaunchesEntity that = (LaunchesEntity) o;

        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (dateQuery != null ? !dateQuery.equals(that.dateQuery) : that.dateQuery != null)
            return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dateStart != null ? dateStart.hashCode() : 0;
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dateQuery != null ? dateQuery.hashCode() : 0);
        return result;
    }

    @Column(name = "date_query")
    public String getDateQuery() {
        return dateQuery;
    }

    public void setDateQuery(String dateQuery) {
        this.dateQuery = dateQuery;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "launch_id")
    @Id
    public int getLaunchId() {
        return launchId;
    }

    public void setLaunchId(int launchId) {
        this.launchId = launchId;
    }

    @javax.persistence.Column(name = "date_start")
    @Basic
    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    @javax.persistence.Column(name = "date_end")
    @Basic
    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    @javax.persistence.Column(name = "status")
    @Basic
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
