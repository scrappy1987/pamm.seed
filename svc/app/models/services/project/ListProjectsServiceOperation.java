package models.services.project;

import com.fasterxml.jackson.databind.JsonNode;
import models.data.entities.ProjectEntity;
import models.data.repository.impl.ProjectDao;
import models.services.ServiceOperation;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by a560832 on 14/10/2015.
 */
public class ListProjectsServiceOperation extends ServiceOperation
{
    private static final Logger.ALogger logger = Logger.of(ListProjectsServiceOperation.class);

    private ProjectDao projectDao;

    @Inject
    public ListProjectsServiceOperation(ProjectDao projectDao)
    {
        this.projectDao = projectDao;
    }

    @Override public JsonNode execute(JsonNode jsonRequest)
    {
        List<ProjectEntity> projects = projectDao.list();;

        return Json.toJson(projects);
    }
}
