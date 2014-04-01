package dao.interfaces;

import models.*;
import entyties.TemplatesEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 03.03.14
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public interface ITemplateDAO extends IDAO{
    public Integer saveTemplate(NewTemplateRequest newTemplateRequest);

    public HashMap<String, String> getParams(Integer tempId);

    public TemplatesEntity getTemplate(Integer templateId);

    public List<TemplatesEntity> getTemplates();

    public void executeLaunch(Integer templateId);

    public void completeLaunch(Integer templateId, Integer exitCode);
}
