package guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-4
 * Time: 0:01
 * Guava EventBus demo.
 * refer: http://macrochen.iteye.com/blog/1393697
 *        http://tomaszdziurko.pl/2012/01/google-guava-eventbus-easy-elegant-publisher-subscriber-cases/
 */
public class SimpleDemo {
    public static void main(String[] args) {
        // given
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        // when
        eventBus.post(new OurTestEvent(100));

        // then
        System.out.println(listener.getLastMessage());
    }
}

class EventListener {

    public int lastMessage = 0;

    @Subscribe
    public void listen(OurTestEvent event) {
        lastMessage = event.getMessage();
    }

    public int getLastMessage() {
        return lastMessage;
    }
}

class OurTestEvent {

    private final int message;

    public OurTestEvent(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }
}