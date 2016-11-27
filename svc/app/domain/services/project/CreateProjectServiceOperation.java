package domain.services.project;

import com.fasterxml.jackson.databind.JsonNode;
import domain.infrastructure.ServiceOperation;
import domain.services.project.businessobjects.Project;
import play.Logger;

import javax.inject.Inject;

public class CreateProjectServiceOperation extends ServiceOperation
{
    private static final Logger.ALogger logger = Logger.of(CreateProjectServiceOperation.class);

    private ProjectRepository repository;

    @Inject
    public CreateProjectServiceOperation(ProjectRepository repository)
    {
        this.repository = repository;
    }

    @Override protected JsonNode doExecute(JsonNode jsonRequest)
    {
        Project project = new Project();

        project.setStatus(jsonRequest.findPath("status").textValue());

        project.setInfo(jsonRequest.findPath("info").textValue());

        project.setTitle(jsonRequest.findPath("title").textValue());

        project.setSummary(jsonRequest.findPath("summary").textValue());

        repository.set(project);

        return jsonRequest;
    }
}
