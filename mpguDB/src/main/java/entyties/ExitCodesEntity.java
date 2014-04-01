package entyties;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 28.02.14
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "exit_codes")
public class ExitCodesEntity {

    private Integer        exitId;
    private String         description;
    private LaunchesEntity launchesEntity;

//    @OneToOne(mappedBy = "exitCodesEntity")
@OneToOne(cascade = CascadeType.ALL)
@JoinTable(name = "launch_history",
        joinColumns = @JoinColumn(name = "exit_code"),
        inverseJoinColumns = @JoinColumn(name = "launch_id")
)
    public LaunchesEntity getLaunchesEntity() {
        return launchesEntity;
    }

    public void setLaunchesEntity(LaunchesEntity launchesEntity) {
        this.launchesEntity = launchesEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExitCodesEntity)) return false;

        ExitCodesEntity that = (ExitCodesEntity) o;

        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exit_id", unique = true)
    public Integer getExitId() {
        return exitId;
    }

    public void setExitId(Integer exitId) {
        this.exitId = exitId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
