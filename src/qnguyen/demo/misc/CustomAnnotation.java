package qnguyen.demo.misc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    public String value() default "";
}

class Car {

    @JsonField("manufacturer")
    private final String make;

    @JsonField
    private final String model;

    @JsonField
    private final String year;

    public Car(String make, String model, String year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return year + " " + make + " " + model;
    }
}

class JsonSerializer {

    public String serialize(Object object) throws IllegalAccessException {
        Class<?> objectClass = Objects.requireNonNull(object).getClass();
        Map<String, String> jsonElements = new HashMap<>();
        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonField.class)) {
                jsonElements.put(getSerializedKey(field), (String) field.get(object));
            }
        }
        return toJsonString(jsonElements);
    }

    private String toJsonString(Map<String, String> jsonMap) {
        String elementsString = jsonMap.entrySet()
                                       .stream()
                                       .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                                       .collect(Collectors.joining(","));
        return "{" + elementsString + "}";
    }

    private static String getSerializedKey(Field field) {
        String annotationValue = field.getAnnotation(JsonField.class).value();
        if (annotationValue.isEmpty()) {
            return field.getName();
        } else {
            return annotationValue;
        }
    }
}

public class CustomAnnotation {
    public static void main(String[] args) {
        Car car = new Car("Ford", "F150", "2018");
        JsonSerializer serializer = new JsonSerializer();
        try {
            serializer.serialize(car);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
