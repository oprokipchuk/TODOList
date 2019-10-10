package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entity.Group;
import json.container.TaskEdited;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.List;

public class JSONService {

    static final JsonNodeFactory factory = JsonNodeFactory.instance;
    private ObjectMapper mapper = new ObjectMapper();

    public String InputStreamToJSON(ServletInputStream inputStream) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String bodyMessageEncoded = "";

        String readStr;
        while ((readStr = bf.readLine()) != null) {
            bodyMessageEncoded += readStr;
        }
        String message = URLDecoder.decode(bodyMessageEncoded, "UTF-8");
        return message;
    }

    public JsonNode JSONStringToNode(String JSON) throws IOException {
        return mapper.readTree(JSON);

    }
    public TaskEdited JSONToUserEditedData(String JSON) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TaskEdited taskEditedData = mapper.readValue(JSON, TaskEdited.class);
        return taskEditedData;
    }

    public ObjectNode getBaseNode() {
        return factory.objectNode();
    }

    public void addString(ObjectNode parent, String name, String value) {
        parent.put(name, value);
    }

    public void addInteger(ObjectNode parent, String name, int value) {
        parent.put(name, value);
    }

    public void addArrayOfPOJOs(ObjectNode base, String name, List<?> list) throws JsonProcessingException {
        ArrayNode arrayNode = factory.arrayNode();
        for (int i = 0; i < list.size(); i++) {
            arrayNode.add(mapper.writeValueAsString(list.get(i)));
        }
        base.set(name, arrayNode);
    }
}
