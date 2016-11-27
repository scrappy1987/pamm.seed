package domain.services.project;

import com.fasterxml.jackson.databind.JsonNode;
import domain.infrastructure.ServiceOperation;
import domain.services.project.businessobjects.Project;
import play.Logger;

import javax.inject.Inject;

public class UpdateProjectServiceOperation extends ServiceOperation
{
    private static final Logger.ALogger logger = Logger.of(UpdateProjectServiceOperation.class);

    private ProjectRepository repository;

    @Inject
    public UpdateProjectServiceOperation(ProjectRepository repository)
    {
        this.repository = repository;
    }

    @Override protected JsonNode doExecute(JsonNode jsonRequest)
    {
        Long id = Long.parseLong(jsonRequest.findPath("id").textValue());

        Project project = repository.get(id);

        project.setStatus(jsonRequest.findPath("status").textValue());

        project.setInfo(jsonRequest.findPath("info").textValue());

        project.setTitle(jsonRequest.findPath("title").textValue());

        project.setSummary(jsonRequest.findPath("summary").textValue());

        repository.set(project);

        return jsonRequest;
    }
}
