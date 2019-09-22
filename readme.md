一、新建父类项目
- 1.主要是项目依赖的管理
- 2.springcloud和springboot2个父类的依赖方式

二、新建公共模块
- 1.新建项目microservercloud-api
- 2.管理公共的接口以及Model等
- 3.业务功能：实现部门的增删改查

三、新建服务提供者
- 1.新建项目microservercloud-provider-dept-8001

四、新建服务消费者
- 1.新建项目
- 2.POM
- 3.application.yml
- 4.接口、接口实现类
- 5.启动类
- 6.测试验证

五、Eureka的环境搭建（Service服务端）（单机版）-典型的CS架构系统
- 1.新建项目microservercloud-eureka-7001
- 2.修改POM，引入eureka-server。
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka-server</artifactId>
</dependency>
- 3.配置YML
eureka:
  instance:
    hostname: localhost #eureka服务端的实例名称
  client:
    register-with-eureka: false # false标识不向注册中心注册自己
    fetch-registry: false # 自己端就是祖册中心，我的职责就是维护服务实例，并不需要去检索服务
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/ #设置Eureka Server交互的地址查询服务和注册服务检查都需要依赖这个地址
- 4.启动类配置
@EnableEurekaServer //启用Eureka注册中心的服务,接收其他微服务注册进来

六、将服务（部门服务）注册到Eureka（使用客户端依赖去链接服务端）
- 1.修改microservercloud-provider-dept-8001
- 2.pom文件修改 增加Eureke访问依赖，spring-cloud-starter-eureka
- 3.配置yml，指定Eureke的访问地址
- 4.修改启动类 @EnableEurekaClient

七、主机映射名自定义
instance:
    instance-id: microservercloud-dept8001 # 自定义主机映射名
    
八、鼠标悬停在服务链接上的，左下角显示真实的IP和端口
prefer-ip-address: true #鼠标放在访问链接的时候，左下角显示正式的IP和端口信息

九、点击服务链接的时候，可以显示服务的INFO信息（healcheck、autocofig、监控和度量 -- 需要依赖springboot的actuator包）
- 1.增加POM依赖
- 2.修改yml

十、Eureka的服务发现(待定)
- 1.修改microservercloud-consumer-dept-80
- 2.

十一、Eureka的服务集群
- 1.目的：都是实现服务的高可用（当一台服务不可用的时候，其他服务可以继续使用！）
- 2.

十二、改造服务消费者，通过注册中心去获取服务
- POM （Eureka、Ribbon、Config）
- YAML（配置了Eureke）
- 启动类（开启了Eureke）
- 修改代码（@LoadBalance注解，实现客户端的负载均衡--》通过调用Rinbbon实现的）
- 修改代码（Controller）
- 测试验证：1、注册中心 2、8001 3、80 4、访问验证

十三、Ribbon的负载均衡
- 改在服务提供者：
- 新建了8002、8003二个服务提供者（8001、8002、8003==》暴露同样的服务）
- 验证Ribbon的负载均衡
- 服务消费者，不需要做改造：只需要 将服务启动，然后访问，查看效果：
- 
