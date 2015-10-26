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
public class UpdateProjectServiceOperation extends ServiceOperation
{
    private static final Logger.ALogger logger = Logger.of(UpdateProjectServiceOperation.class);

    private ProjectDao projectDao;

    @Inject
    public UpdateProjectServiceOperation(ProjectDao projectDao)
    {
        this.projectDao = projectDao;
    }

    @Override protected JsonNode doExecute(JsonNode jsonRequest)
    {
        Long id = Long.parseLong(jsonRequest.findPath("id").textValue());

        ProjectEntity project = projectDao.find(id);

        project.setStatus(jsonRequest.findPath("status").textValue());

        project.setInfo(jsonRequest.findPath("info").textValue());

        project.setTitle(jsonRequest.findPath("title").textValue());

        project.setSummary(jsonRequest.findPath("summary").textValue());

        logger.info("Created ProjectEntity instance from JSON");

        return jsonRequest;
    }
}
