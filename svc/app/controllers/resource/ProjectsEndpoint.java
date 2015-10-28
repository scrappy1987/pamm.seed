package controllers.resource;

import com.fasterxml.jackson.databind.JsonNode;
import models.services.project.ProjectService;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Result;

import javax.inject.Inject;

public class ProjectsEndpoint extends ResourceEndpoint
{
    private static final Logger.ALogger logger = Logger.of(ProjectsEndpoint.class);

    private ProjectService projectService;

    @Inject public ProjectsEndpoint(ProjectService projectService)
    {
         this.projectService = projectService;
    }

    // GET {path}/project
    @Override
    @Transactional public Result list()
    {
        logger.info("Getting list of projects ");

        JsonNode jsonResponse = projectService.list();

        return ok(jsonResponse);
    }

    // GET {path}/project/:id
    @Override
    @Transactional public Result find(Long id)
    {
        logger.info("Getting list of projects ");

        JsonNode jsonResponse = projectService.find(getIdAsJson(id));

        return ok(jsonResponse);
    }

    // POST {path}/project
    @Override
    @Transactional public Result create()
    {
        logger.info("In the create method of the ProjectController");

        JsonNode jsonResponse = projectService.create(request().body().asJson());

        return ok(jsonResponse);
    }

    // PUT {path}/project
    @Override
    @Transactional public Result update()
    {
        logger.info("In the create method of the ProjectController");

        JsonNode jsonResponse = projectService.update(request().body().asJson());

        return ok(jsonResponse);
    }

    // DELETE {path}/project/:id
    @Override
    @Transactional public Result delete(Long id)
    {
        logger.info("In the delete method of the ProjectController");

        JsonNode jsonResponse = projectService.delete(getIdAsJson(id));

        return ok(jsonResponse);
    }
}
