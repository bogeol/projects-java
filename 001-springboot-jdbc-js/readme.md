# 1-springboot-jdbc-js

## 前言
- 本demo【原生】：复习**JDBC**和**JS原生操作**为主
- 下一个demo【框架】：复习**MyBatis**和**Vue.js**为主
- 目的：体会一下【**JDBC -> MyBatis | JS原生操作 -> MVVM**】的感觉

![](https://raw.githubusercontent.com/bogeol/1-springboot-jdbc-js/main/assets/20210307_1554.png)

## 总结
> 2021-03-07
- 前端：HTML - CSS - JS
    - 前端一些基础知识【JS位置：JS放在head中 VS JS放到body末尾（DOM加载完成调用JS）等】
    - JS的基本语法和数据操作等【JS基础、ES6】
    - JS的原生操作DOM、绑定事件【相比于**MVVM（Model-ViewModel-View）开发模式**，原生JS太繁琐了】【**Data Bindings + DOM Listeners**】
        - 原生操作DOM【1. 定位元素 2. DOM API操作 （会有大量重复代码）】
    - JS使用xhr（XMLHttpRequest）请求【GET POST】【urlencoded / json】【相比于**ajax / axios库**】
- 后端：Spring Boot - JDBC
    - springboot的通用包结构【application.yaml / config -> repository -> service(impl) -> controller】
    - springboot的配置不熟悉【Java配置 / .yaml】
    - cors配置不熟悉【WebMvcConfig...】
    - springboot的注解不熟悉【@...】
    - springboot的请求响应内部原理不熟悉【@RequestBody @ResponseBody JSON转换 ...】
    - spring的实际优化不熟悉【Bean】
    - java基础不熟悉
    - JDBC操作【Register -> Connection -> Statement -(execute)> ResultSet / int / boolean 】
    - 服务器部署 / 静态资源分配不熟悉
- 数据库：MySQL【MySQL语法记不清了，比如：insert / order by id ASC / ..】
    - CRUD语法记不清楚了
    - 其他操作【排序 / ...】

## TODO
- 针对上面的知识点进行复习、归纳
- 针对CRUD：Spring Boot + JDBC + JS -> Spring Boot + MyBatis + Vue.js

## 备注
- 代码有些地方写的不严谨【只是复习过一波流程】【基础知识还要继续复习】
