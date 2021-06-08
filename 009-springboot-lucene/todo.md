## TODO

- java文件操作（File / Path）
    - File / Path都是干嘛的？
    - 为什么File也能是目录，为什么这么设计
- springboot当前运行的相对路径的问题
- java7 nio

## springboot运行路径的问题》获取资源

- Access a File from the Classpath in a Spring Application：https://www.baeldung.com/spring-classpath-file-access

基于classpath【用springboot提供的工具】

```
ResourceUtils.getFile("classpath:application.properties");
```

获取工作目录的路径

```
System.getProperty("user.dir")
```

基于.java出发

## java文件属性

- https://docs.oracle.com/javase/tutorial/essential/io/fileAttr.html

```
Path file = ...;
BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

System.out.println("creationTime: " + attr.creationTime());
System.out.println("lastAccessTime: " + attr.lastAccessTime());
System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

System.out.println("isDirectory: " + attr.isDirectory());
System.out.println("isOther: " + attr.isOther());
System.out.println("isRegularFile: " + attr.isRegularFile());
System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
System.out.println("size: " + attr.size());
```

## springboot classpath

```
- src
  - java
  - resources
- pom.xml
  
【java目录为源代码会被编译出来 + resources目录】全部放到target/classes => classpath
```

![](src/main/resources/static/classpath.png)

## maven编译会自动忽略空文件夹
- https://stackoverflow.com/questions/60469200/how-to-include-empty-directories-while-building-using-spring-maven-plugin
```text
<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-resources-plugin</artifactId>
<configuration>
<includeEmptyDirs>true</includeEmptyDirs>
</configuration>
</plugin>
```

## ResourceUtils找的是target中的路径，怎么弄到本地的resources路径中，要不然每次mvn clean，新建的索引就没了

## Builder
new PhraseQuery.Builder().build();
Model.Builder(); lombok中的不一样