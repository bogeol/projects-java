## ssm流程

> 最基本的SSM项目构建》CRUD

### 一、IDEA新建maven webapp

```
生成项目目录：

- src
    - main
        - java
        - resources
        - webapp
            - WEB-INF
                - web.xml
            - index.jsp
- pom.xml
```

### 二、IDEA添加tomcat服务器

- 项目配置添加tomcat服务器
- deployment添加war exploded
- 更新时自动更新类和资源
- 然后启动访问index.jsp

### 三、SSM项目目录构建
```
## java目录
- com
    - example
        - controller
        - service
        - mapper
        - model
    
## resources目录
- config
    - spring
        - spring-beans.xml
        - spring-webmvc.xml
        - ...
    - db
        - jdbc.properties
    - mybatis
        - mybatis-config.xml
    - ...
- mapper
    - xxxMapper.xml
    - ...
- ...

## webapp目录
- WEB-INF
    - web.xml
- index.jsp
- jsp
    - xxx.jsp
    - ...
- ...
    
```

### 四、SSM依赖导入
```
- spring framework
    - spring core
    - spring beans
    - spring context
    - ...
- database
    - mysql driver
    - connection pool
    - mybatis
    - ...
- ...
```

### 五、SSM配置
```text
## webapp/WEB-INF/web.xml
- servlet[/]
    - 配置DispatcherServlet
    - 初始化spring

## resources/config/
- spring
    - spring-beans【spring相关配置】
    - spring-webmvc【spring mvc相关配置】
    - spring-mybatis【mybatis整合进spring相关配置】
- db【配置数据库相关信息】
- mybatis【配置mybatis相关信息】
- ...
```

### 六、CRUD启动
```text
- @Controller / @RestController
- @Service
- @Mapper + xml
- Model

启动tomcat就可以跑CRUD了
```

## TODO

- tomcat相关知识【中文乱码/热部署/...】
    - war / war_exploded
- ...