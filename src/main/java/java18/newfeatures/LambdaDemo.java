package java18.newfeatures;

public class LambdaDemo {
    interface MathOperation {
        int op(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    public static void main(String[] args) {
        MathOperation add = (int a, int b) -> a + b;
        MathOperation sub = (a, b) -> a - b;
        // 大括号中必须要return
        MathOperation mul = (a, b) -> { return a * b; };
        MathOperation div = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + add.op(10, 5));
        System.out.println("10 - 5 = " + sub.op(10, 5));
        System.out.println("10 x 5 = " + mul.op(10, 5));
        System.out.println("10 / 5 = " + div.op(10, 5));

        // 不用括号
        GreetingService greetService1 = message -> System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }
}
