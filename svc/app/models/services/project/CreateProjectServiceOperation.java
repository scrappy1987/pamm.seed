package models.services.project;

import com.fasterxml.jackson.databind.JsonNode;
import models.data.entities.ProjectEntity;
import models.data.repository.impl.ProjectDao;
import models.services.ServiceOperation;
import play.Logger;

import javax.inject.Inject;

/**
 * Created by a560832 on 14/10/2015.
 */
public class CreateProjectServiceOperation extends ServiceOperation
{
    private static final Logger.ALogger logger = Logger.of(CreateProjectServiceOperation.class);

    private ProjectDao projectDao;

    @Inject
    public CreateProjectServiceOperation(ProjectDao projectDao)
    {
        this.projectDao = projectDao;
    }

    @Override public JsonNode execute(JsonNode jsonRequest)
    {
        ProjectEntity project = new ProjectEntity();

        project.setStatus(jsonRequest.findPath("status").textValue());

        project.setInfo(jsonRequest.findPath("info").textValue());

        project.setTitle(jsonRequest.findPath("title").textValue());

        project.setSummary(jsonRequest.findPath("summary").textValue());

        logger.info("Created ProjectEntity instance from JSON");

        projectDao.create(project);

        return jsonRequest;
    }
}
