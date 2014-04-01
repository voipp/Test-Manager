package dao.interfaces;

import models.TestData;
import models.TestResult;
import entyties.ParamsEntity;
import entyties.TestsEntity;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 05.03.14
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 */
public interface ITestsDAO extends IDAO{

    public Set<TestsEntity> getAllTests(Integer templateId);

    public List<ParamsEntity> getAllParams();

    public List<TestResult> getTestsResults(Integer launchId);
}
