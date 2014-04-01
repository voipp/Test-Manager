package dao.implementations;

import dao.interfaces.ITemplateDAO;
import util.HibernateUtil;
import models.*;
import entyties.*;
import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 03.03.14
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
public class TemplateDAO implements ITemplateDAO {

    private Session session;

    @Override
    public Integer saveTemplate(NewTemplateRequest newTemplateRequest) {


        Integer tempId = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            TemplatesEntity templatesEntity = new TemplatesEntity();

            templatesEntity
                    .setTemplateName(newTemplateRequest.getTemplateData().get("template_name"));

            templatesEntity.setDescription(newTemplateRequest.getTemplateData().get("description"));

            Set<TestsEntity> testsEntities = new HashSet<TestsEntity>();

            for (Integer testId : newTemplateRequest.getTestsIds()) {
                testsEntities.add((TestsEntity) session.get(TestsEntity.class, testId));
            }

            templatesEntity.setTestsEntities(testsEntities);
            ArrayList<ParamsTemplateEntity> paramsTemplateEntities = new
                    ArrayList<ParamsTemplateEntity>();

            ArrayList<ParamsEntity> paramsEntities = (ArrayList<ParamsEntity>) session
                    .createQuery("from ParamsEntity fetch all properties").list();

            session.save(templatesEntity);

            tempId = templatesEntity.getTempId();

            for (ParamsEntity paramsEntity : paramsEntities) {
                ParamsTemplateEntity paramsTemplateEntity = new ParamsTemplateEntity();
                paramsTemplateEntity.setTemplatesEntity(templatesEntity);
                paramsTemplateEntity.setParamsEntity(paramsEntity);
                String paramValue = newTemplateRequest.getTemplateData().get(paramsEntity
                        .getParamName());
                paramsTemplateEntity.setParamValue(paramValue);
                paramsTemplateEntities.add(paramsTemplateEntity);
                session.save(paramsTemplateEntity);
            }

            session.getTransaction().commit();
        } catch (RuntimeException re) {
            log.warn(re);
            session.getTransaction().rollback();
            session.close();
        }

        return tempId;

    }

    @Override
    public HashMap<String, String> getParams(Integer tempId) {


        HashMap<String, String> params = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            params = new HashMap<String, String>();

            List<ParamsTemplateEntity> paramsTemplate = session
                    .createQuery("from ParamsTemplateEntity fetch all properties ").list();

            for (ParamsTemplateEntity paramsTemplateEntity : paramsTemplate) {
                params.put(paramsTemplateEntity.getParamsEntity().getParamName(),
                        paramsTemplateEntity.getParamValue());
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
    public TemplatesEntity getTemplate(Integer templateId) {

        TemplatesEntity templatesEntity = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            templatesEntity = (TemplatesEntity) session.get(TemplatesEntity.class, templateId);


            session.getTransaction().commit();
        } catch (RuntimeException re) {
            log.warn(re);
            session.getTransaction().rollback();
            session.close();
        }

        templatesEntity.setTestsEntities(null);

        templatesEntity.setParamsTemplateEntityList(null);


        return templatesEntity;
    }

    @Override
    public List<TemplatesEntity> getTemplates() {

        List<TemplatesEntity> templatesEntities = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            templatesEntities = session.createQuery("from TemplatesEntity fetch all properties ").list();


            session.getTransaction().commit();
        } catch (RuntimeException re) {
            session.getTransaction().rollback();
            session.close();
            //return templatesEntities;
        }


        for (TemplatesEntity templatesEntity : templatesEntities) {

            templatesEntity.setTestsEntities(null);

            templatesEntity.setParamsTemplateEntityList(null);

        }


        return templatesEntities;
    }

    @Override
    public void executeLaunch(Integer templateId) {

        LaunchesEntity launchesEntity = new LaunchesEntity();

        launchesEntity.setDateQuery(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));

        launchesEntity.setStatus("query");

        session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        ExitCodesEntity exitCodesEntity = (ExitCodesEntity) session.load(ExitCodesEntity.class, 1);

        TemplatesEntity templatesEntity = (TemplatesEntity) session.load(TemplatesEntity.class, templateId);

        launchesEntity.setTemplatesEntity(templatesEntity);

        LaunchesHistoryEntity launchesHistoryEntity = new LaunchesHistoryEntity();

        launchesHistoryEntity.setLaunchesEntity(launchesEntity);

        launchesHistoryEntity.setTemplatesEntity(templatesEntity);

        launchesHistoryEntity.setExitCodesEntity(exitCodesEntity);

        launchesEntity.setExitCodesEntity(exitCodesEntity);

        //session.save(launchesEntity);

        session.persist(launchesHistoryEntity);

        session.getTransaction().commit();


    }

    @Override
    public void completeLaunch(Integer templateId, Integer exitCode) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}
