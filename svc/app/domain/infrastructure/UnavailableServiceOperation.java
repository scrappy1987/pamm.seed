package domain.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import util.json.play.JSONHelper;

import javax.inject.Inject;

public class UnavailableServiceOperation extends ServiceOperation
{
    private JSONHelper jsonHelper;

    @Inject public UnavailableServiceOperation(JSONHelper jsonHelper)
    {
        this.jsonHelper = jsonHelper;
    }

    @Override protected JsonNode doExecute(JsonNode jsonRequest)
    {
        //Return JSON stating that this operation is unavailable
        return jsonHelper.toJson("{\"message\":\"Service Operation is unavailable\"}");
    }
}
