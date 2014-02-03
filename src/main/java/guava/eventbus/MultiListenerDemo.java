package guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-4
 * Time: 0:09
 * Guava EventBus demo.
 */
public class MultiListenerDemo {
    public static void main(String[] args) {
        // given
        EventBus eventBus = new EventBus("test");
        MultipleListener listener = new MultipleListener();

        eventBus.register(listener);

        // when
        eventBus.post(new Long(200));
        eventBus.post(new Integer(100));

        // then
        System.out.println(listener.getLastLong() + "\t" + listener.getLastInteger());
    }
}

class MultipleListener {

    public Integer lastInteger;
    public Long lastLong;

    @Subscribe
    public void listenInteger(Integer event) {
        lastInteger = event;
    }

    @Subscribe
    public void listenLong(Long event) {
        lastLong = event;
    }

    public Integer getLastInteger() {
        return lastInteger;
    }

    public Long getLastLong() {
        return lastLong;
    }
}


