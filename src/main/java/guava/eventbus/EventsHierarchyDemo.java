package guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-4
 * Time: 0:21
 * Guava events hierarchy demo.
 */
public class EventsHierarchyDemo {
    public static void main(String[] args) {
        // given
        EventBus eventBus = new EventBus("test");
        IntegerListener integerListener = new IntegerListener();
        NumberListener numberListener = new NumberListener();
        eventBus.register(integerListener);
        eventBus.register(numberListener);

        // when
        eventBus.post(new Integer(100));

        // then
        System.out.println(integerListener.getLastMessage());
        System.out.println(numberListener.getLastMessage());

        //when
        eventBus.post(new Long(200L));

        // then
        // this one should has the old value as it listens only for Integers
        System.out.println(integerListener.getLastMessage());
        System.out.println(numberListener.getLastMessage());
    }
}

class NumberListener {

    private Number lastMessage;

    @Subscribe
    public void listen(Number integer) {
        lastMessage = integer;
    }

    public Number getLastMessage() {
        return lastMessage;
    }
}

class IntegerListener {

    private Integer lastMessage;

    @Subscribe
    public void listen(Integer integer) {
        lastMessage = integer;
    }

    public Integer getLastMessage() {
        return lastMessage;
    }
}
