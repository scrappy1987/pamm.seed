package models.repository.jpa.persistence.dao.impl;

import models.repository.jpa.persistence.entities.ProjectEntity;
import models.repository.jpa.persistence.dao.play.EntityManagerProvider;
import models.repository.jpa.persistence.dao.GenericDao;

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
