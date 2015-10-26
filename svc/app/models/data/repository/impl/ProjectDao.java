package models.data.repository.impl;

import models.data.entities.ProjectEntity;
import models.data.repository.EntityManagerProvider;
import models.data.repository.GenericDao;

import javax.inject.Inject;

public class ProjectDao extends GenericDao<ProjectEntity>
{
    @Inject
    public ProjectDao(EntityManagerProvider emp)
    {
        super(emp);
    }

    //Add methods for any Project Entity specific database accesses here.
}
