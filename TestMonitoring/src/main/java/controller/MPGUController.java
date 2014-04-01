package controller;

import com.sun.jersey.api.core.ResourceConfig;

import dao.implementations.LaunchDAO;
import dao.implementations.TemplateDAO;
import dao.implementations.TestsDAO;
import org.apache.log4j.Logger;
import util.HibernateUtil;
import entyties.LaunchesEntity;
import entyties.ParamsEntity;
import entyties.TestsEntity;

import executorPool.ExecutorPool;

import models.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 03.03.14
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */
@Path("/services")
public class MPGUController {

    private static Logger log = Logger.getLogger("Services");

    /**
     * метод контроллера, принимает данные DataTables и возращает
     * все Launches. Запрос строго get
     *
     * @param
     * @return
     */


    @GET
    @Path("/launchesServ")
    @Produces(MediaType.APPLICATION_JSON)
    public DataTablesLaunches getAllLAunches(@QueryParam("sEcho") String sEcho) {
        // получили JSON запрос от DataTables, теперь его парсим


        LaunchDAO launchDAO = new LaunchDAO();

        Collection<LaunchesEntity> AllLAunches = launchDAO.getAllLAunches();


        //получаем данные по каждому запуску и помещаем в массив launchData
        List<LaunchData> launchData = launchDAO.getAaData();


        DataTablesLaunches dataTablesLaunches = new DataTablesLaunches();

        dataTablesLaunches.setAaData(launchData);

        dataTablesLaunches.setsEcho(sEcho);

        dataTablesLaunches.setiTotalRecords(launchData.size());

        dataTablesLaunches.setiTotalDisplayRecords(launchData.size());


        return dataTablesLaunches;

    }

    @GET
    @Path("/TestsServ")
    @Produces(MediaType.APPLICATION_JSON)
    public DataTablesLaunches getAllLTests(
            @DefaultValue(value = "-1") @QueryParam(value = "templateId") Integer templateId,
            @QueryParam("sEcho") String sEcho)
            throws IllegalAccessException {
        // получили JSON запрос от DataTables, теперь его парсим

        DataTablesLaunches dataTablesLaunches = new DataTablesLaunches();

        dataTablesLaunches.setsEcho(sEcho);

        TestsDAO testsDAO = new TestsDAO();

        Set<TestsEntity> allTests = testsDAO.getAllTests(templateId);

        HibernateUtil.unproxy(Arrays.asList(allTests.toArray()));

        dataTablesLaunches.setAaData(Arrays.asList(allTests.toArray()));

        dataTablesLaunches.setiTotalRecords(allTests.size());

        dataTablesLaunches.setiTotalDisplayRecords(dataTablesLaunches.getiTotalRecords());

        List<ParamsEntity> allParams = testsDAO.getAllParams();

        HibernateUtil.unproxy(allParams);

        dataTablesLaunches.setParams_desc(allParams);


        return dataTablesLaunches;

    }

    @GET
    @Path("/LaunchInfoServ")
    @Produces(MediaType.APPLICATION_JSON)
    public DataTablesLaunches getLaunchInfo(
            @QueryParam(value = "launchId") Integer
                    launchId,
            @QueryParam(value = "sEcho") String sEcho,
            @Context HttpServletRequest request)
            throws IllegalAccessException {
        log.debug("begin " + " --> getLaunchInfo");
        DataTablesLaunches dataTablesLaunches = new DataTablesLaunches();

        TestsDAO testsDAO = new TestsDAO();
        log.debug("getting results " + " --> getLaunchInfo");
        List<TestResult> testsResults = testsDAO
                .getTestsResults(launchId);

        log.debug("begin unproxy" + " --> getLaunchInfo");
        HibernateUtil.unproxy(testsResults);
        log.debug("setting aa data " + " --> getLaunchInfo");
        dataTablesLaunches.setAaData(testsResults);

        dataTablesLaunches.setiTotalRecords(testsResults.size());

        dataTablesLaunches.setiTotalDisplayRecords(testsResults.size());

        dataTablesLaunches.setsEcho(sEcho);
        log.debug("returning " + " --> getLaunchInfo");
        return dataTablesLaunches;
    }

    @GET
    @Path("/TemplatesServ")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getTemplates(
            @QueryParam(value = "sEcho") String sEcho,
            @DefaultValue(value = "-1") @QueryParam(value = "templateId") Integer templateId,
            @Context HttpServletRequest request)
            throws IllegalAccessException {
        SimpleWrapper response = new SimpleWrapper();
        response.setsEcho(sEcho);
        TemplateDAO templateDAO = new TemplateDAO();
        if (templateId.equals(-1)) {
            response.setData(templateDAO.getTemplates());
        } else {
            response.setData(templateDAO.getTemplate(templateId));
        }

        return response;

    }

    @POST
    @Path("/TemplatesServ")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleWrapper postTemplates(
            @Context HttpServletRequest request,
            NewTemplateRequest newTemplateRequest)
            throws IllegalAccessException, IOException {

        TemplateDAO templateDAO = new TemplateDAO();

        Integer tempId = templateDAO.saveTemplate(newTemplateRequest);

        return new SimpleWrapper<Integer>(tempId);
    }

    @GET
    @Path("/ParamsServ")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleWrapper<HashMap<String, String>> getParams(
            @QueryParam(value = "templateId") Integer templateId) {

        TemplateDAO templateDAO = new TemplateDAO();

        return new SimpleWrapper(templateDAO.getParams(templateId));
    }

    /**
     * запускаем Запуск с указанным идентификатором шаблона
     * @return Response - объект созданные по шаблону строитель.Оборачивает ответ в удобную форму
     */
    @POST
    @Path("/LaunchServ")
    public void startLaunch(
            @QueryParam(value = "templateId") Integer templateId,
            @Context ServletContext servletContext
            ) throws InterruptedException {

        ExecutorPool processPool = (ExecutorPool) servletContext.getAttribute("process executor pool");

        TemplateDAO templateDAO = new TemplateDAO();

        templateDAO.executeLaunch(templateId);

        ProcessBuilder processBuilder = (ProcessBuilder) servletContext.getAttribute("process " +
                "builder");

        processPool.execute(processBuilder);

        return;
    }


}


