package models.services.project;

import com.fasterxml.jackson.databind.JsonNode;
import models.services.Service;
import models.services.UnavailableServiceOperation;
import play.libs.Json;

import javax.inject.Inject;

/**
 * Created by a560832 on 16/10/2015.
 */
public class ProjectService extends Service
{
    private CreateProjectServiceOperation createProjectServiceOperation;

    private ListProjectsServiceOperation listProjectsServiceOperation;

    private UpdateProjectServiceOperation updateProjectServiceOperation;

    private DeleteProjectServiceOperation deleteProjectServiceOperation;

    private FindProjectServiceOperation findProjectServiceOperation;

    @Inject public ProjectService(UnavailableServiceOperation unavailableServiceOperation,
            CreateProjectServiceOperation createProjectServiceOperation,
            ListProjectsServiceOperation listProjectsServiceOperation,
            UpdateProjectServiceOperation updateProjectServiceOperation,
            DeleteProjectServiceOperation deleteProjectServiceOperation,
            FindProjectServiceOperation findProjectServiceOperation)
    {
        super(unavailableServiceOperation);

        this.createProjectServiceOperation = createProjectServiceOperation;

        this.listProjectsServiceOperation = listProjectsServiceOperation;

        this.updateProjectServiceOperation = updateProjectServiceOperation;

        this.deleteProjectServiceOperation = deleteProjectServiceOperation;

        this.findProjectServiceOperation = findProjectServiceOperation;
    }

    @Override public JsonNode list()
    {
        return listProjectsServiceOperation.execute(Json.toJson(""));
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
