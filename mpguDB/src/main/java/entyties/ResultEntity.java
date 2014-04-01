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
@javax.persistence.Table(name = "result")
@Entity
public class ResultEntity {
    private int             resId;
    private String          logPath;
    private Integer         result;
    private TemplatesEntity templatesEntity;
    private LaunchesEntity  launchesEntity;

    @OneToOne
    @JoinColumn(name = "launch_id")
    public LaunchesEntity getLaunchesEntity() {
        return launchesEntity;
    }

    public void setLaunchesEntity(LaunchesEntity launchesEntity) {
        this.launchesEntity = launchesEntity;
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
    @javax.persistence.Column(name = "res_id")
    @Id
    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @javax.persistence.Column(name = "log_path")
    @Basic
    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    @javax.persistence.Column(name = "result")
    @Basic
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

}
