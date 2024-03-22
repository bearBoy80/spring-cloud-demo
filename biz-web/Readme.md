# Eureka
## Eureka相关配置
## Eureka相关关键类
 - InstanceInfo
   持有服务发现和服务注册相关配置信息

   官方原话：
    `The class that holds information required for registration with Eureka Server and to be discovered by other components.`
 - Application
   持有多个InstanceInfo
 - Applications
   持有多个application
 - ApplicationInfoManager
 - InstanceInfoReplicator
    负责定时注册InstanceInfo数据的task
 - DiscoveryClient
    负责注册Instance到对应的eureka server，并提供服务发现功能
 - HeartbeatThread
    负责定时上报数据到eureka server
 - EurekaClientConfig
   eureka 客户端相关配置信息。spring cloud 默认实现是EurekaClientConfigBean，我们常用的配置属性都是eureka.client开头的
 
## Eureka 指标