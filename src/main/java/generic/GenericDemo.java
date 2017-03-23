package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.zhihu.com/question/20400700
 */
public class GenericDemo {
    static class Shape {
        public String name = null;

        public String getName() {
            return name;
        }
    }

    static class Circle extends Shape {
        Circle() {
            name = "circle";
        }
    }

    static class Rectangle extends Shape {
        Rectangle() {
            name = "rectangle";
        }
    }

    static class Ellipse extends Circle {
        Ellipse() {
            name = "ellipse";
        }
    }

    static class Source<T extends Object> {
    }

    public static void main(String[] args) {
        List<Circle> circles = new ArrayList<>();
        circles.add(new Circle());
        // extends 和 super 会导致一些功能无法使用

        // ? 不是一个具体的类型，不可以写入数据，因为不知道具体类型
        List<? extends Shape> shapes = circles;
        System.out.println(shapes.get(0).getName());

        // 可以写入数据，get 到的数据只能是 Object
        List<? super Circle> s = new ArrayList<Shape>() {{
            add(new Circle());
            add(new Rectangle());
            add(new Ellipse());
        }};

        Shape a = (Shape) s.get(1);
        System.out.println(a.getName());

        Source<? extends Object> so = new Source<String>();
    }
}
