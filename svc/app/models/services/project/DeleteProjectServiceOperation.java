package models.services.project;

import com.fasterxml.jackson.databind.JsonNode;
import models.services.ServiceOperation;
import models.services.project.businessobjects.Project;
import play.Logger;
import util.json.play.JSONHelper;

import javax.inject.Inject;

public class DeleteProjectServiceOperation extends ServiceOperation
{
    private static final Logger.ALogger logger = Logger.of(CreateProjectServiceOperation.class);

    private ProjectRepository repository;

    private JSONHelper jsonHelper;

    @Inject
    public DeleteProjectServiceOperation(ProjectRepository repository, JSONHelper jsonHelper)
    {
        this.repository = repository;

        this.jsonHelper = jsonHelper;
    }

    @Override protected JsonNode doExecute(JsonNode jsonRequest)
    {
        Long id = Long.parseLong(jsonRequest.findPath("id").textValue());

        Project project = repository.get(id);

        repository.remove(project);

        return jsonHelper.toJson("{\"message\":\"Deleted Project with id" + id + "\"}");
    }
}
