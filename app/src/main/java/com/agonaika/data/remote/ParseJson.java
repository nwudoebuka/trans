package com.agonaika.data.remote;

import com.agonaika.utils.AgoLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ParseJson {
    public static <T> T getJsonStringFromHttpResponse(String jsonResponse, Class<T> classOfT) {
        String jsonString = "";
        try {
            JSONObject jsonObject = (JSONObject) new JSONTokener(jsonResponse).nextValue();
            jsonString = jsonObject.getString("d");
        }
        catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.fromJson(jsonString, classOfT);
    }

    public static <T> T toObjectFromHttpResponse(String jsonString, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, classOfT);
    }

    private static Gson getGson(){
        return  new Gson();
    }

    /**
     * Converts the Arrays of JSON object string to Arrays of correstponding object list
     * based on passed in Type
     * */
    public static <T> ArrayList<T> toObjectListFromJsonString(String jsonString, Class<T> classType) {
        ArrayList<T> objectList = new ArrayList<T>();
        try {

            Gson gson = new Gson();
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int index = 0; index < jsonArray.length(); index++) {
                String nestedJsonString = jsonArray.get(index).toString();

                T nestedListOfObject = gson.fromJson(nestedJsonString, classType);

                objectList.add(nestedListOfObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectList;
    }

    /**
     * Generic method to convert the JSON string to a passed in class type.
     * */
    public static <T> T jsonStringToGenericType(String jsonString, Class<T> classType) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Boolean.class, booleanAsIntAdapter)
				.registerTypeAdapter(boolean.class, booleanAsIntAdapter)
				.create();

        return new Gson().fromJson(jsonString, classType);
    }

    public static JSONObject objectToJSONObject(Object object){
        JSONObject jsonObject = new JSONObject();

        Gson gson = new Gson();
        try {
            jsonObject = (JSONObject) new JSONTokener(gson.toJson(object)).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static JsonElement[] jsonArrayToJsonElements(JSONArray jsonArray) {
        JsonElement[] jsonElements = null;
        try {
            if (jsonArray != null) {
                jsonElements = new JsonElement[jsonArray.length()];
                for (int index = 0; index < jsonArray.length(); index++) {
                    String jsonString = jsonArray.get(index).toString();
                    if (jsonString.contains("::") && !jsonString.contains("Messages")) {
                        jsonElements[index] = new Gson().toJsonTree(jsonString);
                    }
                    else {
                        jsonElements[index] = getGson().fromJson(jsonString, JsonElement.class);
                    }
                }
            }
        } catch (JSONException e) {
            AgoLog.e("jsonArrayToJsonElements", e.toString());
        }
        return jsonElements;
    }

    public static String ObjectToJsonString(Object object) {
        return new Gson().toJson(object);
    }

    public static JsonElement stringToJsonElement(String stringValue) {
        return new JsonParser().parse(stringValue);
    }

    private static final TypeAdapter<Boolean> booleanAsIntAdapter = new TypeAdapter<Boolean>() {
        @Override
        public void write(JsonWriter out, Boolean value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value);
            }
        }
        @Override
        public Boolean read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            switch (peek) {
                case BOOLEAN:
                    return in.nextBoolean();
                case NULL:
                    in.nextNull();
                    return null;
                case NUMBER:
                    return in.nextInt() != 0;
                case STRING:
                    return Boolean.parseBoolean(in.nextString());
                default:
                    throw new IllegalStateException("Expected BOOLEAN or NUMBER but was " + peek);
            }
        }
    };
}
