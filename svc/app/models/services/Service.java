package models.services;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

/**
 * Created by a560832 on 16/10/2015.
 */
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
        return unavailableOperation.execute(Json.toJson(""));
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
