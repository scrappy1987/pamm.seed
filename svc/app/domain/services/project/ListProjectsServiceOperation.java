package domain.services.project;

import com.fasterxml.jackson.databind.JsonNode;
import domain.infrastructure.ServiceOperation;
import domain.services.project.businessobjects.Project;
import play.Logger;
import util.json.play.JSONHelper;

import javax.inject.Inject;
import java.util.List;

public class ListProjectsServiceOperation extends ServiceOperation
{
    private static final Logger.ALogger logger = Logger.of(ListProjectsServiceOperation.class);

    private ProjectRepository repository;

    private JSONHelper jsonHelper;

    @Inject
    public ListProjectsServiceOperation(ProjectRepository repository, JSONHelper jsonHelper)
    {
        this.repository = repository;

        this.jsonHelper = jsonHelper;
    }

    @Override protected JsonNode doExecute(JsonNode jsonRequest)
    {
        List<Project> projects = repository.getAll();

        return jsonHelper.toJson(projects);
    }
}
