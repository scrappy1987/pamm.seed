package models.services;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class Service
{
    private UnavailableServiceOperation unavailableOperation;

    public Service(UnavailableServiceOperation unavailableOperation)
    {
        this.unavailableOperation = unavailableOperation;
    }

    public JsonNode search(JsonNode searchCriteria)
    {
        return unavailableOperation.execute(searchCriteria);
    }

    public JsonNode list()
    {
        return unavailableOperation.execute(null);
    }

    public JsonNode find(JsonNode identifier)
    {
        return unavailableOperation.execute(identifier);
    }

    public JsonNode create(JsonNode jsonResource)
    {
        return unavailableOperation.execute(jsonResource);
    }

    public JsonNode update(JsonNode jsonResource)
    {
        return unavailableOperation.execute(jsonResource);
    }

    public JsonNode delete(JsonNode identifier)
    {
        return unavailableOperation.execute(identifier);
    }
}
