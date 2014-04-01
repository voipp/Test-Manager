package dao.interfaces;

import models.LaunchData;
import entyties.LaunchesEntity;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 03.03.14
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */

/**
 * дао для работы с запусками
 */
public interface ILaunchDAO extends IDAO{

    Collection<LaunchesEntity> getAllLAunches();

    Map<String, String> getParamsValues(Integer launchId);

    String getName(Integer launchId);

    void saveLaunch(LaunchesEntity launchesEntity);

    List<LaunchData> getAaData();
}
