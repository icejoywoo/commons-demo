package guava.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-4
 * Time: 0:13
 * DeadEvent demo.
 */
public class DeadEventListenerDemo {
    public static void main(String[] args) {

        // given
        EventBus eventBus = new EventBus("test");

        DeadEventListener deadEventListener = new DeadEventListener();
        eventBus.register(deadEventListener);

        // when
        eventBus.post(new OurTestEvent(200));

        System.out.println(deadEventListener.isNotDelivered());
    }
}

/**
 * Listener waiting for the event that any message was posted but not delivered to anyone
 */
class DeadEventListener {

    boolean notDelivered = false;

    @Subscribe
    public void listen(DeadEvent event) {
        notDelivered = true;
    }

    public boolean isNotDelivered() {
        return notDelivered;
    }
}