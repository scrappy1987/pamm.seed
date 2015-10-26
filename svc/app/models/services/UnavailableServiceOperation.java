package models.services;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

/**
 * Created by a560832 on 16/10/2015.
 */
public class UnavailableServiceOperation extends ServiceOperation
{
    @Override protected JsonNode doExecute(JsonNode jsonRequest)
    {
        //Return JSON stating that this operation is unavailable
        return Json.toJson("{\"message\":\"Service Operation is unavailable\"}");
    }
}
