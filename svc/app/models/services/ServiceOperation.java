package models.services;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by a560832 on 14/10/2015.
 */
public abstract class ServiceOperation
{
    public abstract JsonNode execute(JsonNode jsonRequest);
}
