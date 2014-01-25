package guava.basic;

import com.google.common.base.Throwables;

import java.io.IOException;

public class ThrowablesDemo {
    public static void main(String[] args) throws IOException {
        try {
            throw new IOException();
        } catch (Throwable t) {
            Throwables.propagateIfInstanceOf(t, IOException.class);
            throw Throwables.propagate(t);
        }
    }
}