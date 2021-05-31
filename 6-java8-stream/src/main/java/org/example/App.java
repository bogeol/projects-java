package org.example;

import org.example.model.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class App {

    /**
     * 1. Stream流创建
     * 2. Stream流使用
     *     - 终止操作
     *     - 中间操作
     */
    public static void main(String[] args) {
        v1();
        v2();
    }

    /**
     * Stream创建:
     * - java.util.Collection.stream()
     * - java.util.Arrays.stream(T[] array)
     * - Stream: of() / iterate() / generate()
     */
    public static void v1() {
        // Collection
        System.out.println("----------------------------");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Stream<Integer> integerStream = list.stream();
        integerStream.forEach(System.out::println);

        // Arrays
        System.out.println("----------------------------");
        String[] strings = new String[]{
                "abcjk",
                "dsjflkd",
                "werwer",
                "xcvxc",
                "weccvb"
        };
        Stream<String> stringStream = Arrays.stream(strings);
        stringStream.forEach(System.out::println);

        // Stream of / iterate / generate
        System.out.println("----------------------------");
        Stream<Integer> streamOf = Stream.of(1, 2, 3, 4, 5);
        streamOf.forEach(System.out::println);

        Stream<Double> streamIterate = Stream.iterate(1D, x -> x + Math.random()).limit(5);
        System.out.println(streamIterate.collect(Collectors.toList()));

        Stream<Double> streamGenerate = Stream.generate(Math::random).limit(5);
        streamGenerate.forEach(System.out::println);
    }

    /**
     * Stream使用：
     * - 终止操作【END => RETURN】
     * - 中间操作【RETURN STREAM => CONTINUE】
     */
    public static void v2() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(User.builder()
                    .uuid(UUID.randomUUID())
                    .username(UUID.randomUUID().toString().replaceAll("\\d|-", "").toUpperCase(Locale.ROOT))
                    .password(UUID.randomUUID().toString().split("-")[0])
                    .hit(Math.random())
                    .sex(Math.random() > 0.5 ? "man" : "woman")
                    .status(Math.random() > 0.5)
                    .build());
        }

        System.out.println("-----------------------------------------------------终止操作");
        System.out.println("-----------------------------------------------------终止操作");
        System.out.println("-----------------------------------------------------终止操作");

        System.out.println("------forEach");
        userList.stream().forEach(System.out::println);

        System.out.println("------findFirst");
        Optional<User> first = userList.stream().filter(item -> item.getHit() > 0.9).findFirst();
        System.out.println(first.isPresent());
        System.out.println(first);

        System.out.println("------findAny");
        Optional<User> any = userList.parallelStream().filter(item -> item.getHit() > 0.5).findAny();
        System.out.println(any.isPresent());
        System.out.println(any);

        System.out.println("------anyMatch");
        boolean anyMatch = userList.stream().anyMatch(item -> item.getHit() < 0.2);
        System.out.println(anyMatch);

        System.out.println("------count / max / min");
        long count = userList.stream().filter(item -> item.getHit() < 0.7).count();
        System.out.println(count);
        Optional<User> minHitUserOptional = userList.stream().min(Comparator.comparing(User::getHit));
        Optional<User> maxHitUserOptional = userList.stream().max(Comparator.comparing(User::getHit));
        System.out.println(minHitUserOptional);
        System.out.println(maxHitUserOptional);

        System.out.println("-----------------------------------------------------中间操作");
        System.out.println("-----------------------------------------------------中间操作");
        System.out.println("-----------------------------------------------------中间操作");

        System.out.println("------filter");
        userList.stream().filter(item -> item.getHit() > 0.5).forEach(System.out::println);

        System.out.println("------map");
        System.out.println("------flatMap");
        List<String> usernameList = userList.stream().map(User::getUsername).collect(Collectors.toList());
        System.out.println(usernameList);

        System.out.println("------sorted");
        userList.stream().sorted(Comparator.comparing(User::getHit).reversed()).forEach(System.out::println);

        System.out.println("------reduce");
        Optional<Double> sumHitOptional = userList.stream().map(User::getHit).reduce(Double::sum);
        System.out.println(sumHitOptional);

        System.out.println("------collect");
        // collect: 统计
        Double sumHit = userList.stream().collect(Collectors.summingDouble(User::getHit));
        System.out.println(sumHit);
        // collect: 分组 / 分割
        Map<Boolean, List<User>> partitionUserList = userList.stream().collect(Collectors.partitioningBy(item -> item.getHit() > 0.9));
        System.out.println(partitionUserList);
        Map<String, List<User>> groupingUserList = userList.stream().collect(Collectors.groupingBy(User::getSex));
        System.out.println(groupingUserList);
        System.out.println(userList.stream().collect(Collectors.groupingBy(User::getUuid)));
        // collect: 连接
        String join = userList.stream().map(item -> {
            return String.valueOf(item.getHit());
        }).collect(Collectors.joining("-"));
        System.out.println(join);
        // collect: ...

        System.out.println("------other features");
        // concat【合并】
        Stream<String> stream1 = Arrays.stream(new String[]{"a", "b", "c", "d"});
        Stream<String> stream2 = Arrays.stream(new String[]{"1", "2", "3", "4"});
        System.out.println(Stream.concat(stream1, stream2).collect(Collectors.toList()));

        // distinct【去重】
        System.out.println(Stream.of(new String[]{"1", "2", "3", "4", "Hello", "world", "hello", "world"})
                .distinct()
                .collect(Collectors.joining("-")));
        // limit【限制从流中获取前limit个数据】
        System.out.println(Stream.iterate(1, x -> x * 3).limit(10).collect(Collectors.toList()));

        // skip【跳过流中的前skip个数据】
        System.out.println(Stream.iterate(1, x -> x * 3).skip(3).limit(10).collect(Collectors.toList()));
    }
}
