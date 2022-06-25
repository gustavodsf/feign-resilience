package my.tech.poc.demo;

import org.springframework.web.client.HttpServerErrorException;

public class FallBack {

    public static String fallback(HttpServerErrorException ex) {
        return "Recovered HttpServerErrorException: " + ex.getMessage();
    }

    private static String fallback(Exception ex) {
        return "Recovered: " + ex.toString();
    }

    private static String fallback(String str, Throwable ex) {
        return "Recovered: " + str + ex.toString();
    }

}
