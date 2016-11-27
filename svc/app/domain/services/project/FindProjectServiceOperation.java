package domain.services.project;

import com.fasterxml.jackson.databind.JsonNode;
import domain.infrastructure.ServiceOperation;
import domain.services.project.businessobjects.Project;
import play.Logger;
import util.json.play.JSONHelper;

import javax.inject.Inject;

public class FindProjectServiceOperation extends ServiceOperation
{
    private static final Logger.ALogger logger = Logger.of(FindProjectServiceOperation.class);

    private ProjectRepository repository;

    private JSONHelper jsonHelper;

    @Inject
    public FindProjectServiceOperation(ProjectRepository repository, JSONHelper jsonHelper)
    {
        this.repository = repository;

        this.jsonHelper = jsonHelper;
    }

    @Override protected JsonNode doExecute(JsonNode jsonRequest)
    {
        Long id = Long.parseLong(jsonRequest.findPath("id").textValue());

        Project project = repository.get(id);

        return jsonHelper.toJson(project);
    }
}
