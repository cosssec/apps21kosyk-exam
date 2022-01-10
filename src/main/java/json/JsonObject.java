package json;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private HashMap<String, Json> myMap = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for(int i = 0; i<jsonPairs.length; i++){
            myMap.put(jsonPairs[i].key, jsonPairs[i].value);
        }
    }

    @Override
    public String toJson() {

        Set<String> keySetReal = myMap.keySet();
        StringBuilder line = new StringBuilder();
        int counter = 0;
        line.append("{");

        for (String key : keySetReal) {
            counter += 1;
            line.append("'");
            line.append(key);
            line.append("': ");
            Json val = myMap.get(key);
            line.append(val.toJson());
            if (counter != myMap.size()) {
                line.append(", ");
            }
        }
        line.append("}");
        String res = line.toString();
        return res;
    }

    public void add(JsonPair jsonPair) {
        myMap.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return myMap.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject res = new JsonObject();
        for (String name: names) {
            Json found = find(name);
            if (found == null){
                continue;}
            else{
                res.add(new JsonPair(name, found));
            }
        }
        return res;
    }
    }
