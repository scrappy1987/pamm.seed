package models.services.project;

import com.fasterxml.jackson.databind.JsonNode;
import models.data.entities.ProjectEntity;
import models.data.repository.impl.ProjectDao;
import models.services.ServiceOperation;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;

/**
 * Created by a560832 on 16/10/2015.
 */
public class FindProjectServiceOperation extends ServiceOperation
{
    private static final Logger.ALogger logger = Logger.of(FindProjectServiceOperation.class);

    private ProjectDao projectDao;

    @Inject
    public FindProjectServiceOperation(ProjectDao projectDao)
    {
        this.projectDao = projectDao;
    }

    @Override protected JsonNode doExecute(JsonNode jsonRequest)
    {
        Long id = Long.parseLong(jsonRequest.findPath("id").textValue());

        logger.info("Deleting ProjectEntity with id " + id);

        ProjectEntity project = projectDao.find(id);

        return Json.toJson(project);
    }
}
