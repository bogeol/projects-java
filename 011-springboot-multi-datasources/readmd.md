## springboot muti datasources【springboot多数据源配置】

- jdbc
- spring jdbc
- jpa
- mybatis

## 多数据源分包

分包：

- primary【主】
- secondary【次】

TIPS：

- 只要跟数据库访问有关的最好分数据源主次包，比如：dao/mapper/repository/model/...
- service只是提供服务层接口，并不关心你数据来源，只是单纯提供服务，所以就不用数据源主次包了

TIPS:

- 可能配置中会有model与数据访问层绑定，所以最好主次数据源配置最好分开【比如JPA的配置中需要指定model包】

## spring bean理解

```text
@Bean
public Xxx xxx(@Qualifier("object")Object object){
}

>>>

<bean id="" class="">
    <property name="" >
        <ref local=""/>
    </property>
</bean>

------------------------------------------------

@Component
public class ...{
    ...
    
    @Autowired
    public void setXxx(@Qualifier("object") Object object){
    }
}

>>>

<bean id="" class="">
    <property name="" >
        <ref local=""/>
    </property>
</bean>

TIPS：
- @Bean > @Qualifier
- @Autowired > @Qualifier【属性 / 方法】
```