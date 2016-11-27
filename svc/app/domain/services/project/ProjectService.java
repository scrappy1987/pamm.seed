package domain.services.project;

import com.fasterxml.jackson.databind.JsonNode;
import domain.infrastructure.Service;
import domain.infrastructure.UnavailableServiceOperation;
import util.json.play.JSONHelper;

import javax.inject.Inject;

public class ProjectService extends Service
{
    private CreateProjectServiceOperation createProjectServiceOperation;

    private ListProjectsServiceOperation listProjectsServiceOperation;

    private UpdateProjectServiceOperation updateProjectServiceOperation;

    private DeleteProjectServiceOperation deleteProjectServiceOperation;

    private FindProjectServiceOperation findProjectServiceOperation;

    private JSONHelper jsonHelper;

    @Inject public ProjectService(UnavailableServiceOperation unavailableServiceOperation,
            CreateProjectServiceOperation createProjectServiceOperation,
            ListProjectsServiceOperation listProjectsServiceOperation,
            UpdateProjectServiceOperation updateProjectServiceOperation,
            DeleteProjectServiceOperation deleteProjectServiceOperation,
            FindProjectServiceOperation findProjectServiceOperation,
            JSONHelper jsonHelper)
    {
        super(unavailableServiceOperation);

        this.createProjectServiceOperation = createProjectServiceOperation;

        this.listProjectsServiceOperation = listProjectsServiceOperation;

        this.updateProjectServiceOperation = updateProjectServiceOperation;

        this.deleteProjectServiceOperation = deleteProjectServiceOperation;

        this.findProjectServiceOperation = findProjectServiceOperation;

        this.jsonHelper = jsonHelper;
    }

    @Override public JsonNode list()
    {
        return listProjectsServiceOperation.execute(jsonHelper.toJson(""));
    }

    @Override public JsonNode create(JsonNode jsonResource)
    {
        return createProjectServiceOperation.execute(jsonResource);
    }

    @Override public JsonNode update(JsonNode jsonResource)
    {
        return updateProjectServiceOperation.execute(jsonResource);
    }

    @Override public JsonNode delete(JsonNode identifier)
    {
        return deleteProjectServiceOperation.execute(identifier);
    }

    @Override
    public JsonNode find(JsonNode identifier)
    {
        return findProjectServiceOperation.execute(identifier);
    }
}
