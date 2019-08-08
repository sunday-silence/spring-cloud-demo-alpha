package cn.speedyframework.demo.common.core.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description 日期时间工具包
 * @name chenguangxue
 * @date 2019-08-08 11:21
 */
public class DateTimeUtils {

    // 获取当前的时间戳
    public static long timestamp() {
        return System.currentTimeMillis();
    }

    // DateTimeFormatter 缓存
    private static Map<String, DateTimeFormatter> formatters = new HashMap<>();

    // 读写锁
    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    private static DateTimeFormatter getFormatter(String pattern) {
        if (formatters.containsKey(pattern)) {
            lock.readLock().lock();
            DateTimeFormatter formatter = formatters.get(pattern);
            lock.readLock().unlock();
            return formatter;
        } else {
            lock.writeLock().lock();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            formatters.put(pattern, formatter);
            lock.writeLock().unlock();
            return formatter;
        }
    }

    private enum TemporalFormatStrategy {
        DATE_TIME(LocalDateTime.class, "yyyy-MM-dd HH:mm:ss"),
        TIME(LocalTime.class, "HH:mm:ss"),
        DATE(LocalDate.class, "yyyy-MM-dd"),
        ;

        private String format(Temporal temporal, String pattern) {
            DateTimeFormatter formatter = getFormatter(pattern);
            return formatter.format(temporal);
        }

        private String format(Temporal temporal) {
            DateTimeFormatter formatter = getFormatter(this.defaultPattern);
            return formatter.format(temporal);
        }

        private final Class temporalClass;
        private final String defaultPattern;

        TemporalFormatStrategy(Class temporalClass, String defaultPattern) {
            this.temporalClass = temporalClass;
            this.defaultPattern = defaultPattern;
        }
    }

    private static EnumSet<TemporalFormatStrategy> strategies = EnumSet.allOf(TemporalFormatStrategy.class);

    public static String format(Temporal temporal, String pattern) {
        Optional<TemporalFormatStrategy> formatStrategy = strategies.stream()
                .filter(strategy -> strategy.temporalClass == temporal.getClass()).findAny();
        return formatStrategy.map(strategy -> strategy.format(temporal, pattern)).orElse(null);
    }

    public static String format(Temporal temporal) {
        Optional<TemporalFormatStrategy> formatStrategy = strategies.stream()
                .filter(strategy -> strategy.temporalClass == temporal.getClass()).findAny();
        return formatStrategy.map(strategy -> strategy.format(temporal)).orElse(null);
    }
}
