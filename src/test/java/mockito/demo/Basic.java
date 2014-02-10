package mockito.demo;

import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by wujiabin on 14-2-10.
 */
public class Basic {
    @Test
    public void testMockitoBasic() {
        // 模拟创建
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();
        // 验证选择性和显式调用
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void testMockitoInvoke() {
        // 你不仅可以模拟接口,任何具体类都行
        LinkedList<String> mockedList = mock(LinkedList.class);
        // 执行前准备测试数据
        when(mockedList.get(0)).thenReturn("first");
        // 接着打印"first"
        assertThat(mockedList.get(0), equalTo("first"));
        // 因为get(999)未对准备数据,所以下面将打印"null".
        assertThat(mockedList.get(999), nullValue());
    }
}
