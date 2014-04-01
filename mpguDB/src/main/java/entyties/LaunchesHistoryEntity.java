package entyties;


import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 28.02.14
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "launch_history")
public class LaunchesHistoryEntity {
    private Integer         Id;
    private ExitCodesEntity exitCodesEntity;
    private LaunchesEntity  launchesEntity;
    private TemplatesEntity templatesEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LaunchesHistoryEntity)) return false;

        LaunchesHistoryEntity that = (LaunchesHistoryEntity) o;

        if (Id != null ? !Id.equals(that.Id) : that.Id != null) return false;
        if (exitCodesEntity != null ? !exitCodesEntity.equals(that.exitCodesEntity) :
                that.exitCodesEntity != null) return false;
        if (launchesEntity != null ? !launchesEntity.equals(that.launchesEntity) :
                that.launchesEntity != null) return false;
        if (templatesEntity != null ? !templatesEntity.equals(that.templatesEntity) :
                that.templatesEntity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + (exitCodesEntity != null ? exitCodesEntity.hashCode() : 0);
        result = 31 * result + (launchesEntity != null ? launchesEntity.hashCode() : 0);
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

    @Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST})
    @ManyToOne
    @JoinColumn(name = "launch_id")
    public LaunchesEntity getLaunchesEntity() {

        return launchesEntity;
    }

    public void setLaunchesEntity(LaunchesEntity launchesEntity) {
        this.launchesEntity = launchesEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @ManyToOne
    @JoinColumn(name = "exit_code")
    public ExitCodesEntity getExitCodesEntity() {
        return exitCodesEntity;
    }

    public void setExitCodesEntity(ExitCodesEntity exitCodesEntity) {
        this.exitCodesEntity = exitCodesEntity;
    }
}
