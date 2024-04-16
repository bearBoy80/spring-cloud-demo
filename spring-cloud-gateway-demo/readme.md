# Spring Cloud GetaWay

## 概念
### Route
 The basic building block of the gateway. It is defined by an ID, a destination URI, a collection of predicates, and a collection of filters. A route is matched if the aggregate predicate is true.
### Predicate
This is a Java 8 Function Predicate. The input type is a Spring Framework ServerWebExchange. This lets you match on anything from the HTTP request, such as headers or parameters.
### Filter
These are instances of Spring Framework GatewayFilter that have been constructed with a specific factory. Here, you can modify requests and responses before or after sending the downstream request.
## 关键类
### RoutePredicateFactory
### GatewayFilter
### GlobalFilter
### PredicateSpec
### RoutePredicateHandlerMapping
这个是关键入口，比如请求进来类了，需要哪个Route来处理
### Route
### RouteLocator
### RouteDefinitionRouteLocator
### RouteDefinition
### RouteDefinitionLocator
### PredicateDefinition
### FilterDefinition
### GatewayAutoConfiguration
### NettyRoutingFilter
这个类主要是将获取的完整url,通过httpclient调用下游服务获取对应的响应数据