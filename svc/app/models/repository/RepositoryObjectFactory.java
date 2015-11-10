package models.repository;

import models.services.businessobjects.BusinessObject;
import util.json.play.JSONHelper;

import javax.inject.Inject;

public class RepositoryObjectFactory
{
    private JSONHelper jsonHelper;

    @Inject public RepositoryObjectFactory(JSONHelper jsonHelper)
    {
        this.jsonHelper = jsonHelper;
    }

    public <T extends PersistenceObject> T createEntity(BusinessObject businessObject, Class<T> poClass)
    {
        String jsonString = jsonHelper.getJSONForObject(businessObject);

        T entity = jsonHelper.getObjectForJSON(jsonString, poClass);

        return entity;
    }

    public <S extends BusinessObject> S createBusinessObject(PersistenceObject persistenceObject, Class<S> boClass)
    {
        String jsonString = jsonHelper.getJSONForObject(persistenceObject);

        S businessObject = jsonHelper.getObjectForJSON(jsonString, boClass);

        return businessObject;
    }
}
