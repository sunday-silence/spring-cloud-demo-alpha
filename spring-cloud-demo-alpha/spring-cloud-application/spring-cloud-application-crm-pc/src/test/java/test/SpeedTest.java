package test;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @description
 * @name chenguangxue
 * @date 2019-08-15 17:50
 */
public class SpeedTest {

    // 这是以方法形式提供功能
    private void uppercase() {
        "Ascscasf".toUpperCase();
    }

    // 这是以lambda方式提供功能
    private Function<String, String> uppercase = String::toUpperCase;

    private static class TestResult {
        // 测试次数
        private long count;
        // 测试结果
        private Map<Type, Long> result = new HashMap<>(Type.values().length);

        private TestResult(long count) {
            this.count = count;
        }

        private void putResult(Type type, long interval) {
            result.put(type, interval);
        }

        @Override
        public String toString() {
            return "测试次数" + count + "\n"
                    + "\t" + Type.LAMBDA + ":" + result.get(Type.LAMBDA) + "\n"
                    + "\t" + Type.METHOD + ":" + result.get(Type.METHOD) + "\n";
        }
    }

    private enum Type {
        METHOD, LAMBDA,
        ;

        public static final EnumSet<Type> TYPES = EnumSet.allOf(Type.class);
    }

    @Test
    public void test() {
        Runnable action = () -> "3ascacaw".toUpperCase();

        // 指定次数的单次测试
        Function<Long, TestResult> singleTest = (count) -> {
            TestResult result = new TestResult(count);

            Type.TYPES.forEach(type -> {
                long start = System.currentTimeMillis();
                for (int i = 1; i <= count; i++) {
                    action.run();
                }
                long end = System.currentTimeMillis();
                long interval = end - start;

                result.putResult(type, interval);
            });

            System.out.println(result.toString());
            return result;
        };

        Function<Supplier<List<Long>>, List<TestResult>> fullTest = (supplier) ->
                supplier.get()
                        .stream()
                        .map(count -> count * 1000)
                        .map(singleTest)
                        .collect(Collectors.toList());

        fullTest.apply(() -> Arrays.asList(10L, 100L, 1000L, 10000L, 100000L, 1000000L));
    }
}
