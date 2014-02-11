package guice.basic;

import com.google.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-12
 * Time: 0:12
 * interface implementation.
 */

//@Singleton
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String sayHello() {
        return "Hello, World";
    }
}
