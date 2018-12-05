package co.inventorsoft.scripty.model.dto;

import java.util.Arrays;

public enum ImageTypes {
    JPEG("jpeg"),
    JPG("jpg"),
    PNG("png");

    private String type;

    ImageTypes(String type){
        this.type = type;
    }
    public static boolean contains(String str) {
        return Arrays.stream(values()).anyMatch(m -> m.type.equals(str.toLowerCase()));
    }
}
