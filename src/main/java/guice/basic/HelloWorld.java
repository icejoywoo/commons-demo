package guice.basic;

import com.google.inject.ImplementedBy;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-12
 * Time: 0:11
 * simple interface
 */

@ImplementedBy(HelloWorldImpl.class)
public interface HelloWorld {
    String sayHello();
}
