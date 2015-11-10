package controllers.resource.play;

import models.services.project.ProjectService;
import play.Logger;

import javax.inject.Inject;

public class ProjectsEndpoint extends ResourceEndpoint<ProjectService>
{
    private static final Logger.ALogger logger = Logger.of(ProjectsEndpoint.class);

    @Inject public ProjectsEndpoint(ProjectService projectService)
    {
        super(projectService);
    }
}
