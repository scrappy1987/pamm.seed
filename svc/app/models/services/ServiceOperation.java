package models.services;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by a560832 on 14/10/2015.
 */
public abstract class ServiceOperation
{
    public JsonNode execute(JsonNode jsonRequest)
    {
        JsonNode jsonResponse = null;

        try
        {
            //Application Level Authentication / Authorization /

            //Audit processing

            jsonResponse = doExecute(jsonRequest);
        }
        catch (Exception e)
        {
            //Delegate to generic Exception Handling mechanism
        }

        return jsonResponse;
    }

    protected abstract JsonNode doExecute(JsonNode jsonRequest);
}
