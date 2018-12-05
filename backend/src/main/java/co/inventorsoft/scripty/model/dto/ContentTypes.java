package co.inventorsoft.scripty.model.dto;

import java.util.Arrays;

public enum ContentTypes {
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html");

    private String type;

    ContentTypes(String type){
        this.type = type;
    }
    public static boolean contains(String str) {
        return Arrays.stream(values()).anyMatch(m -> m.type.equals(str.toLowerCase()));
    }
}