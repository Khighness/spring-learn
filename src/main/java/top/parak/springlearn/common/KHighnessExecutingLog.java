package top.parak.springlearn.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author KHighness
 * @since 2021-05-06
 */

public class KHighnessExecutingLog {
    private final static AtomicLong COUNTER = new AtomicLong();
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static void info(String message) {
        System.out.println(FORMATTER.format(LocalDateTime.now()) + " [" + COUNTER.incrementAndGet() + "] => " + message);
    }
}
