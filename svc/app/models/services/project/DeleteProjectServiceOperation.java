package models.services.project;

import com.fasterxml.jackson.databind.JsonNode;
import models.data.repository.impl.ProjectDao;
import models.services.ServiceOperation;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;

public class DeleteProjectServiceOperation extends ServiceOperation
{
    private static final Logger.ALogger logger = Logger.of(CreateProjectServiceOperation.class);

    private ProjectDao projectDao;

    @Inject
    public DeleteProjectServiceOperation(ProjectDao projectDao)
    {
        this.projectDao = projectDao;
    }

    @Override public JsonNode execute(JsonNode jsonRequest)
    {
        Long id = Long.parseLong(jsonRequest.findPath("id").textValue());

        logger.info("Deleting ProjectEntity with id " + id);

        projectDao.delete(id);

        return Json.parse("{\"message\":\"Deleted Project with id" + id + "\"}");
    }
}
