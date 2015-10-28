package controllers.resource;

import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

public abstract class ResourceEndpoint extends Controller
{
    private static final Logger.ALogger logger = Logger.of(ResourceEndpoint.class);

    // GET {path}/resources?queryString
    public Result search(String searchCriteria)
    {
        return TODO;
    }

    // GET {path}/resources/
    public Result list()
    {
        return TODO;
    }

    // GET {path}/resources/:id
    public Result find(Long id)
    {
        return TODO;
    }

    // POST {path}/resources/ (json string sent as the data in the POST request)
    public Result create()
    {
        return TODO;
    }

    // PUT {path}/resources (json string sent as the data in the PUT request)
    public Result update()
    {
        return TODO;
    }

    // DELETE {path}/resources/:id
    public Result delete(Long id)
    {
        return TODO;
    }

    protected JsonNode getIdAsJson(Long id)
    {
        Map<String, String> idMap = new HashMap<String, String>();

        idMap.put("id", id.toString());

        return Json.toJson(idMap);
    }
}
