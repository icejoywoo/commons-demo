package service.loader.demo;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<Search> loader = ServiceLoader.load(Search.class);
        for (Search search : loader) {
            search.searchDoc("hello world");
        }
    }
}
