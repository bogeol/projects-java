## jpa hibernate mybatis

```
- JPA: 本身是一种ORM规范,不是ORM框架.由各大ORM框架提供实现
- Hibernate：是一个完整的ORM框架,常规CRUD我们不需要写一句SQL
- Mybatis：**data mapper framework**（半自动持久层框架） which is completely different framework compared to JPA. 
In JPA and ORM frameworks, you map Objects /Entities to the corresponding sql tables 
and you work on objects and not on tables directly unless you use their native queries. 
In mybatis , you play directly with sql data

```

## spring data jpa介绍

Spring Data JPA：This module deals with enhanced support for **JPA based data access layers**.

## spring data jpa CRUD操作

- find
- findById
- findAll
- save
- saveAll
- delete
- deleteById
- ...

## Repository

- JapRepository【Spring Data JPA】
- PagingAndSortingRepository【分页/排序】
- CrudRepository【CRUD】
- Repository

## TODO

- jpa yaml配置
- spring参数数据验证
- spring data jpa
    - Optional
    - Iterable
    - Pageable（PageRequest.of / Sort）
    - ...



