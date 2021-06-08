## [springboot demo](https://spring.io/guides/gs/spring-boot/)

## Controller / RestController

- Controller：关注于**模型和视图**
    - Model + return "view"
    - Map + return "view"
    - HttpServletRequest + return "view"
    - ModelAndView("view")
- RestController：关注于**将JSON结果写入HTTP响应体**【RESTful API / RESTful Web Service】
    - JSON

## spring对视图引擎的支持【服务端渲染后提交给客户端】

- Thymeleaf【默认支持】
- JSP
- Freemarker

## 总结

- Controller: 服务端视图引擎渲染 【JSP / Thymeleaf / ...】
- RestController: RESTful Web Service 【前后端分离，通过JSON传递数据】

## 小技巧

- ApplicationRunner / CommandLineRunner 可以用于启动时初始化一些东西【比如遍历IoC Beans】

## TODO

- JSP没有跑起来【暂时不搞了，后面复习Servlet的时候搞】
- 
```
@RequestMapping("user")
public class UserController {

默认访问user的时候，调用什么东西？
```
