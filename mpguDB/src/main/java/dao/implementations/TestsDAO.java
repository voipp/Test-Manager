package dao.implementations;

import dao.interfaces.ITestsDAO;
import util.HibernateUtil;
import entyties.*;
import models.TestResult;
import entyties.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 05.03.14
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 */
public class TestsDAO implements ITestsDAO {

    private Session session;

    @Override
    public Set<TestsEntity> getAllTests(Integer templateId) {

        if (templateId.equals(-1)) {

            List<TestsEntity> tests = null;
            try {
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                tests = session.createQuery("from TestsEntity").list();
                Hibernate.initialize(tests);
                session.getTransaction().commit();
            } catch (RuntimeException re) {
                log.warn(re);
                session.getTransaction().rollback();
                session.close();
            }
            return new HashSet<TestsEntity>(tests);
        } else {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Set<TestsEntity> tests = null;
            try {


                session.beginTransaction();

                TemplatesEntity templatesEntity = (TemplatesEntity) session
                        .get(TemplatesEntity.class, templateId);
                tests = templatesEntity.getTestsEntities();
                Hibernate.initialize(tests);
                session.getTransaction().commit();
            } catch (RuntimeException re) {
                log.warn(re);
                session.getTransaction().rollback();
                session.close();
            }
            return tests;
        }

    }

    @Override
    public List<ParamsEntity> getAllParams() {

        List<ParamsEntity> params = null;
        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            params = session.createQuery("from ParamsEntity ").list();
            Hibernate.initialize(params);
            session.getTransaction().commit();
        } catch (RuntimeException re) {
            session.getTransaction().rollback();
            session.close();
            log.warn(re);
        }
        return params;
    }

    @Override
    public List<TestResult> getTestsResults(Integer launchId) {

        List<TestResult> testResults = null;
        try {
            log.debug("begin " + " --> getTestsResults");
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            log.debug("begin transaction" + " --> getTestsResults");
            session.beginTransaction();

            testResults = new ArrayList<TestResult>();
            log.debug("getting launch " + " --> getTestsResults");
            LaunchesEntity launchesEntity = (LaunchesEntity) session.get(LaunchesEntity.class,
                    launchId);
            log.debug("getting result " + " --> getTestsResults");
            ResultEntity result = launchesEntity.getResultEntity();
            log.debug("getting tests " + " --> getTestsResults");
            Set<TestsEntity> allTestsWithLaunch = launchesEntity.getTemplatesEntity()
                    .getTestsEntities();
            log.debug("filling results " + " --> getTestsResults");
            for (TestsEntity test : allTestsWithLaunch) {
                TestResult testResult = new TestResult();
                testResult.setTestName(test.getName());
                testResult.setLogPath(test.getPath());
                if(result==null) {
                    testResult.setResult(null);
                }else {
                testResult.setResult(String.valueOf(result.getResult()));
                }
                testResults.add(testResult);
            }
            log.debug("commit " + " --> getTestsResults");
            session.getTransaction().commit();
        } catch (RuntimeException re) {
            session.getTransaction().rollback();
            session.close();
            log.warn(re + " --> getTestsResults");
        }

        return testResults;
    }
}
