## 设置springmvc使用jsp视图引擎【需要手动配置】

- TODO
    1. JSP需要引入JSP编译库和JSTL标签库
    2. jsp默认去webapp目录寻找，需要新建一个指定目录【坑】
    3. springboot设置jsp视图
- 总体
    - 总体来说太麻烦了，适合servlet的webapp中使用

```text
### springboot jsp配置 ###

# By default, JSP pages are looked up inside /webapp directory.
# So under the src/main directory, create a new directory named webapp.
# ...................
# src/main
#    java
#    resources
#    webapp/WEB-INF/jsp
# ...................

# springmvc view resolver
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
```

## 参考

- [springboot jsp](https://o7planning.org/11681/spring-boot-and-jsp)
- [springboot jsp thymeleaf](https://www.codejava.net/frameworks/spring-boot/how-to-create-a-spring-boot-web-application-spring-mvc-with-jsp-thymeleaf)
- [springboot jsp 404](https://stackoverflow.com/questions/29782915/spring-boot-jsp-404)