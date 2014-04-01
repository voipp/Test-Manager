package dao.implementations;
import dao.interfaces.ILaunchDAO;
import util.*;
import models.LaunchData;
import entyties.*;
import org.hibernate.Session;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 03.03.14
 * Time: 13:24
 * To change this template use File | Settings | File Templates.
 */
public class LaunchDAO implements ILaunchDAO {


    private Session session;



    @Override
    public Collection<LaunchesEntity> getAllLAunches() {
        List<LaunchesEntity> launchesEntityList = null;
        try {

            //log.debug("begin");
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            //log.debug("getting launches");
            launchesEntityList = session.createQuery("from entyties.LaunchesEntity")
                    .list();
            //log.debug("committing");
            session.getTransaction().commit();
        } catch (RuntimeException re) {
            //log.error(re);
            session.getTransaction().rollback();
            session.close();
        }
        return launchesEntityList;
    }

    @Override
    public Map<String, String> getParamsValues(Integer launchId) {
        Map params = new HashMap<String, String>();


        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            LaunchesEntity launchesEntity = (LaunchesEntity) session
                    .get(LaunchesEntity.class, launchId);
            TemplatesEntity correspondingTemplate = launchesEntity.getTemplatesEntity();
            List<ParamsTemplateEntity> paramsValues = correspondingTemplate
                    .getParamsTemplateEntityList();
            for (ParamsTemplateEntity paramsValue : paramsValues) {
                String value = paramsValue.getParamValue();
                String name = paramsValue.getParamsEntity().getParamName();
                params.put(name, value);
            }
            session.getTransaction().commit();
        } catch (RuntimeException re) {
            log.warn(re);
            session.getTransaction().rollback();
            session.close();
        }
        return params;
    }

    @Override
    public String getName(Integer launchId) {
        Map params = new HashMap<String, String>();
        TemplatesEntity correspondingTemplate = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            LaunchesEntity launchesEntity = (LaunchesEntity) session
                    .get(LaunchesEntity.class, launchId);
            correspondingTemplate = launchesEntity.getTemplatesEntity();
            session.getTransaction().commit();
        } catch (RuntimeException re) {
            log.warn(re);
            session.getTransaction().rollback();
            session.close();
        }
        return correspondingTemplate.getTemplateName();
    }

    @Override
    public void saveLaunch(LaunchesEntity launchesEntity) {

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            session.saveOrUpdate(launchesEntity);
            session.getTransaction().commit();
        } catch (RuntimeException re) {
            log.warn(re);
            session.getTransaction().rollback();
            session.close();
        }

    }

    @Override
    public List<LaunchData> getAaData() {

        Collection<LaunchesEntity> AllLAunches = getAllLAunches();
        List<LaunchData> launchDataList = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();

            session.beginTransaction();


            launchDataList = new ArrayList<LaunchData>();


            for (LaunchesEntity launch : AllLAunches) {

                if (!session.contains(launch)) {
                    session.update(launch);
                }

                LaunchData launchData = new LaunchData();

                launchData.setEnd(launch.getDateEnd());
                launchData.setStart(launch.getDateStart());
                launchData.setStatus(launch.getStatus());
                launchData.setLaunch_name(launch.getTemplatesEntity().getTemplateName());
                List<ParamsTemplateEntity> paramsAndValues = launch.getTemplatesEntity()
                        .getParamsTemplateEntityList();
                launchData.setLaunch_id(launch.getLaunchId());
                Map params = new HashMap();


                for (ParamsTemplateEntity paramsAndValue : paramsAndValues) {
                    params.put(paramsAndValue.getParamsEntity().getParamName(),
                            paramsAndValue.getParamValue());
                }
                launchData.setParams(params);

                launchDataList.add(launchData);

            }
            session.getTransaction().commit();
        } catch (RuntimeException re) {
            log.warn(re);
            session.getTransaction().rollback();
            session.close();
        }
        return launchDataList;
    }
}
