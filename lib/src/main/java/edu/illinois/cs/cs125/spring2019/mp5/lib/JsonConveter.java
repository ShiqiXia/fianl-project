package edu.illinois.cs.cs125.spring2019.mp5.lib;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
/**
 * a class that change all Json documentation into string.
 */
public class JsonConveter {
    public JsonConveter() {}

    /**
     *
     * @param json a json object containing things we need
     * @return an array of defi
     */
    public static String[] converter(final String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonArray result = parser.parse(json).getAsJsonObject().get("list").getAsJsonArray();
        int size = result.size();
        String[] toReturn = new String[size];
        for (int i = 0; i < size; i++) {
            toReturn[i] = result.get(i).getAsJsonObject().get("defination").getAsString();
        }
        return toReturn;
    }
}
