package qnguyen.demo.lambda;

import java.awt.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by QuangNguyen on 19/06/2016.
 */
public class DecoratorPattern {

    public static void main(String[] args) {
        printSnap(new Camera());
        printSnap(new Camera(Color::darker));
        printSnap(new Camera(Color::brighter));
        printSnap(new Camera(Color::brighter, Color::darker));
    }

    public static void printSnap(Camera camera) {
        System.out.println(camera.snap(new Color(125, 125, 125)));
    }
}

class Camera {

    private Function<Color, Color> filter;

    public Camera(Function<Color, Color>... filters) {
        setFilters(filters);
    }

    private void setFilters(Function<Color, Color>... filters) {
        this.filter = Stream.of(filters)
//                            .reduce(color -> color, (totalFunction, function) -> totalFunction.andThen(function));
                            .reduce(Function.identity(), Function::andThen);
    }

    public Color snap(Color color) {
        return this.filter.apply(color);
    }
}
