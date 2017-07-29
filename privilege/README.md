
# <center>SOFALite 工程模型</center>

## 一、关于本工程的介绍
 
### 1.1 本工程的模块划分

本工程主要划分为 6 个工程，自顶而下以及依赖关系分别是(参考项目根目录下主 pom 中的 modules 层次)：app/test 模块，app/web 模块，app/biz/service-impl 模块，app/core/service 模块，app/common/service/facade 模块和数据访问层 app/common/dal 模块，其中的具体依赖关系可以参考项目中依赖设置。其中 test 模块可以测试除了 web 模块中的所有经过spring容器创建和初始化的功能（未启动 web 容器，只启动了 spring 上下文），web模块功能可以通过运行web容器访问界面访问（如何本地直接运行请参考下文）。

### 1.2 本工程的注意事项
 
* 本工程中所有的应用名为：APPNAME，用户可以根据自己的需要进行设置，我这里只是示例。

* app/web 模块会构建 war 包并运行，其中加载其他模块配置文件的设置在：`app/web/src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml`,需要注意的是` <import resource="classpath*:META-INF/APPNAME/*.xml"/>`,之所以增加了一个`APPNAME`目录是因为只加载本工程中的 spring 配置文件避免加载其他工程的配置文件，并构造当前spring上下文，APPNAME作为关键加载路径上的区分，并且我们约定所有的工程配置文件均放在形如这样的目录下面:`META-INF/APPNAME/biz-service-impl.xml`。

* app/test 模块的配置文件路径为`test/APPNAME/test.xml`,之所以将`META-INF`换成了`test`是为了避免构造 spring 上下文时的重复加载。

* 工程中很多细节和配置，无法一一说明，用户参考即可，尤其针对 web 层中 Controller 的映射设计只提供参考，每个人考虑和实现不尽相同，请根据实际情况参考使用。


## 二、运行准备和教程

### 2.1 数据库准备

* 1.创建一个 DB

```java

create database user

```

* 2.创建一个表同时增加一个主键约束

```java

  CREATE TABLE `user_info` (
    `id` int(13) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `user_name` varchar(100) NOT NULL COMMENT '用户名称',
    `real_name` varchar(300) DEFAULT NULL COMMENT '真实姓名',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
    `ext` varchar(500) DEFAULT NULL COMMENT '扩展字段',
    `valid` tinyint(1) DEFAULT '1' COMMENT '1有效，0无效',
    UNIQUE (user_name) COMMENT '唯一键约束',
    PRIMARY KEY (`id`) COMMENT '主键'
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息表'; 
 
 ```
 
 * 3.数据库连接信息

 数据源的链接信息、用户信息以及密码信息配置在`META-INF/APPNAME/common-dal.xml`。
 
 url=jdbc:mysql://10.189.97.246:3306/user
 username=sofarouter
 password=sofarouter
 
 `dataSource` 这个 bean 中适配 mybatis 时,对应数据库链接、用户名和密码请修改成自己的数据库正确链接配置

 
 
### 2.2 运行方法:
 
 * 1.项目根目录下运行`mvn clean install`,将工程重新清理、编译、并打包（在app/web/target 目录下会生成一个 war包，当部署到开发服务器时，上传这一个 war 包即可）
 
 * 2.进入app/web 目录,然后运行`mvn jetty:run`,将整个工程运行起来,紧接着可以在浏览器采用如下方式访问：
	
	访问是否启动成功：`http://localhost:8080/`
	
	向数据库插入一条用户记录：`http://localhost:8080/user?userName=xiaoliang.xl&realName=wangxiaoliang`(注意：代码中采用的是post方法，默认浏览器是 GET 方法，可以通过修改测例com.xinmei.APPNAME.test.usercases.UserServiceTest#testUserService 先插入用户数据)或者用 chrome 浏览器插件 [Postman 构造](http://gold.xitu.io/entry/57597a62a341310061337885)。
	
	查询用户记录：`http://localhost:8080/user?userName=xiaoliang.xl`
 

### 2.3 测试方法

测试基类：`com.xinmei.APPNAME.test.base.AbstractTestBase`

运行测试类： `com.xinmei.APPNAME.test.usercases.UserServiceTest`

