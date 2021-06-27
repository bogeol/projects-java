## springboot ==> jdbc vs spring jdbc

- jdbc VS spring-jdbc
- spring web mvc参数

```
pom.xml:

- spring-boot-starter-web
- spring-jdbc
- mysql-connector-java
```

## 总结

- crud restful api
    - GET
    - POST
    - PUT
    - DELETE
- spring web mvc
    - @PathVariable ~ id ~ /model/xxx 【路径变量】
    - @RequsetParam ~ key ~ /model?key=xxx 【请求参数】
    - @RequestBody ~ data ~ /model 【请求体】
- jdbc vs spring jdbc
    - Class.forName ~ DriverManagerDataSource【注册驱动】
    - DriverManager ~ DriverManagerDataSource【连接管理】
    - Connection
    - Statement / PreparedStatement
    - ResultSet 【指针 / 返回值】

## jdbc VS spring-jdbc

```
对比【 Java - JDBC(jdbc) / Spring - JDBC(spring-jdbc) 】流程

### Java - JDBC流程：
- 注册数据库驱动 【Class.forName("com.mysql.jdbc.Driver");】
- 配置、获取数据库连接 【DriverManager.getConnection(url, username, password)】
- 配置语句 > 执行语句 > 返回值 【Statement > execute() > ResultSet】
- 关闭所有资源 【close()】

### Spring - JDBC流程：
- 配置（注册数据库驱动 + 配置数据库连接 ）【@Configuration - @Bean】
- 获取数据库连接 【DriverManagerDatasource.getConnection()】
- ...同上

```

## 报错

### spring jdbc报错

import org.springframework.jdbc.datasource.DriverManagerDataSource;找不到

解决：需要引入`spring-jdbc`包，才能使用DriverManagerDataSource配置类

## TODO

### 数据库连接池的问题

- 原生jdbc使用连接池怎么操作？
- spring-jdbc默认连接池？

### MySQL语句中``【可能是mysql语法】

字段key, value ...和mysql的关键词可能冲突了，所以在Java String中最好使用``把字段包围起来

### python requests库

> link: https://docs.python-requests.org/en/master/user/quickstart/

- params 【Passing Parameters In URLs】
- data 【RequestBody】
- json
- files
- ...

### python Generator语法 join操作

```python
def randomstr(num):
    x = "qwertyuiopasdfghjklzxcvbnm"
    x += x.upper()
    x += "0123456789"
    return "".join(i for i in random.choices(x, k=int(num)))
```