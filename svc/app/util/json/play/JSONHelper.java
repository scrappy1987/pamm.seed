package util.json.play;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.libs.Json;

import javax.inject.Named;
import java.io.StringWriter;

@Named public class JSONHelper
{
    public String getJSONForObject(Object obj)
    {
        ObjectMapper mapper = new ObjectMapper();

        StringWriter sw = new StringWriter();

        try
        {
            mapper.writeValue(sw, obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        return sw.toString();
    }

    public <T> T getObjectForJSON(String jsonString, Class<T> clazz)
    {
        ObjectMapper mapper = new ObjectMapper();

        T obj = null;

        try
        {
            obj = mapper.readValue(jsonString, clazz);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        return obj;
    }

    public JsonNode toJson(Object obj)
    {
        return Json.toJson(obj);
    }

    public String createJSONErrorMessage(String jsonString, int returnCode, String errorReason, String errorMessage)
    {
        String originalJSONString;

        originalJSONString = jsonString;
        int ind = originalJSONString.lastIndexOf("}");
        String aReturnCode = getStartString(jsonString) + "\"returnCode\": " + "\"" + returnCode + "\"";
        originalJSONString = new StringBuilder(originalJSONString)
                .replace(ind, ind + 1, aReturnCode + populateJSON(errorReason, errorMessage)).toString();
        return originalJSONString;
    }

    private String populateJSON(String errorReason, String errorMessage)
    {
        String aErrorReason = ", \"errorReason\": " + "\"" + errorReason + "\"";
        String aErrorMessage = ", \"errorMessage\": " + "\"" + errorMessage + "\"";
        String errorJson = aErrorReason + aErrorMessage + "}";
        return errorJson;
    }

    private String getStartString(String jsonString)
    {
        String firstChar = "";

        if (jsonString.indexOf(':') > -1)
        {
            firstChar = ", ";
        }

        return firstChar;
    }
}
