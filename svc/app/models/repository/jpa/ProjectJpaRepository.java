package models.repository.jpa;

import models.repository.RepositoryObjectFactory;
import models.repository.jpa.persistence.dao.impl.ProjectDao;
import models.repository.jpa.persistence.entities.ProjectEntity;
import models.services.project.ProjectRepository;
import models.services.project.businessobjects.Project;
import play.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ProjectJpaRepository implements ProjectRepository
{
    private static final Logger.ALogger logger = Logger.of(ProjectJpaRepository.class);

    private RepositoryObjectFactory repositoryObjectFactory;

    private ProjectDao dao;

    @Inject public ProjectJpaRepository(RepositoryObjectFactory repositoryObjectFactory, ProjectDao dao)
    {
        this.repositoryObjectFactory = repositoryObjectFactory;

        this.dao = dao;
    }

    public Project set(Project businessObject)
    {
        ProjectEntity projectEntity = repositoryObjectFactory.createEntity(businessObject, ProjectEntity.class);

        dao.create(projectEntity);

        Project project = repositoryObjectFactory.createBusinessObject(projectEntity, Project.class);

        return project;
    }

    public Project get(Long businessObjectID)
    {
        ProjectEntity projectEntity = dao.find(businessObjectID);

        Project project = repositoryObjectFactory.createBusinessObject(projectEntity, Project.class);

        return project;
    }

    public List<Project> getAll()
    {
        List<ProjectEntity> projectEntities = dao.list();

        List<Project> projects = new ArrayList<>();

        for (ProjectEntity projectEntity : projectEntities)
        {
            projects.add(repositoryObjectFactory.createBusinessObject(projectEntity, Project.class));
        }

        return projects;
    }

    public void remove(Project businessObject)
    {
        Object key = businessObject.getId();

        dao.delete(key);
    }
}
