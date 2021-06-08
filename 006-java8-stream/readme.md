## Java8 Stream 简介

> Stream将要处理的元素集合（数组 / 集合）看作一种流，在流的过程中，借助Stream API对流中的元素进行操作，比如：筛选、排序、聚合等。

- 终止操作【termianl operations】
    - 每个流进行到终止操作即结束【返回值或者集合】
- 中间操作【intermediate operations】
    - 中间层，可以多次操作【返回流】

## 使用

```
Stream创建:
- java.util.Collection.stream()
- java.util.Arrays.stream(T[] array)
- Stream. + of() / iterate() / generate()

stream VS parallelStream：
- stream 串行流【主线程】
- parallelStream 并行流【多线程】

Optional：
可以为null的容器对象【isPresent判断是否存在】【get返回对象】

终止操作：【END => RETURN】【termianl operations】
- forEach【遍历】
- find / match【匹配】
- reduce【规约】（把一个流设置规约缩减成一个值）
- max / min / count【聚合】

中间操作：【RETURN STREAM => CONTINUE】【intermediate operations】
- filter【过滤】
- map【映射】
- sorted【排序】
- collect【收集】（把一个流收集成一个集合或者值）【★★★】
  - toList / toSet / toMap【归集】
  - counting / averaging / summarizing / ...【统计】
  - groupingBy / partitioningBy【分组 / 分割】
  - reduce【规约】
  - joining【连接】
- other【其他特性：合并 / 去重 / 限制 / 跳过 / ...】
  - concat
  - distinct
  - limit
  - skip
```

## 参考

- https://zhuanlan.zhihu.com/p/299064490?utm_source=ZHShareTargetIDMore&utm_medium=social&utm_oi=786501725082243072
