package guice.basic;

import com.google.inject.*;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-12
 * Time: 0:15
 * Guice demo.
 */
public class HelloWorldTest {
    @Test
    public void testSayHello() {
        Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(HelloWorld.class).to(HelloWorldImpl.class);
            }
        });

        HelloWorld hw = injector.getInstance(HelloWorld.class);
        assertThat(hw.sayHello(), equalTo("Hello, World"));

        // different instance
        HelloWorld hw2 = injector.getInstance(HelloWorld.class);
        assertThat(hw, not(hw2));
    }

    @Test
    public void testSayHelloProvider() {
        Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(HelloWorld.class).toProvider(new Provider<HelloWorld>() {
                    @Override
                    public HelloWorld get() {
                        return new HelloWorldImpl();
                    }
                });
            }
        });

        HelloWorld hw = injector.getInstance(HelloWorld.class);
        assertThat(hw.sayHello(), equalTo("Hello, World"));

        // different instance
        HelloWorld hw2 = injector.getInstance(HelloWorld.class);
        assertThat(hw, not(hw2));
    }

    @Test
    public void testSayHelloSingleton() {
        Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(HelloWorld.class).toInstance(new HelloWorldImpl());
            }
        });

        HelloWorld hw = injector.getInstance(HelloWorld.class);
        assertThat(hw.sayHello(), equalTo("Hello, World"));

        // same instance
        HelloWorld hw2 = injector.getInstance(HelloWorld.class);
        assertThat(hw, is(hw2));
    }

    @Test
    public void testSayHelloSingleton2() {
        Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(HelloWorld.class).to(HelloWorldImpl.class).in(Scopes.SINGLETON);
            }
        });

        HelloWorld hw = injector.getInstance(HelloWorld.class);
        assertThat(hw.sayHello(), equalTo("Hello, World"));

        // same instance
        HelloWorld hw2 = injector.getInstance(HelloWorld.class);
        assertThat(hw, is(hw2));
    }

    @Test
    public void testSayHelloProviderSingleton() {
        Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(HelloWorld.class).toProvider(new Provider<HelloWorld>() {
                    private final HelloWorld instance = new HelloWorldImpl();
                    @Override
                    public HelloWorld get() {
                        return instance;
                    }
                });
            }
        });

        HelloWorld hw = injector.getInstance(HelloWorld.class);
        assertThat(hw.sayHello(), equalTo("Hello, World"));

        // same instance
        HelloWorld hw2 = injector.getInstance(HelloWorld.class);
        assertThat(hw, is(hw2));
    }

    @Test
    public void guiceAutoFind() {
        Injector injector = Guice.createInjector();
        HelloWorld hw = injector.getInstance(HelloWorldImpl.class);
        assertThat(hw.sayHello(), equalTo("Hello, World"));
    }

    @Test
    public void guiceAnnotationConfig() {
        Injector injector = Guice.createInjector();
        HelloWorld hw = injector.getInstance(HelloWorld.class);
        assertThat(hw.sayHello(), equalTo("Hello, World"));

        // same instance if mark HelloWorldImpl to @Singleton
        HelloWorld hw2 = injector.getInstance(HelloWorld.class);
        assertThat(hw, not(hw2));
    }
}
