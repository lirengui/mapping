# mapping

在对一个APP项目后台进行重构的过程中遇到了以下问题：重构系统的请求接口需按照新的设计要求进行开发，同时，还需要保证老版本的APP端能够通过旧的请求地址正常访问。

解决办法：将旧的请求地址与对应的新地址按照 oldUrl=newUrl 的格式添加到配置文件中，并使用过滤器对请求进行拦截，根据配置文件中的配置将对旧地址的请求重定向到新地址。

根据实际情况对需要处理的旧请求地址按照旧地址 =新地址 的格式在maps.properties中进行配置，配置示例如下。

```
# format: oldUrl = newUrl
/WXOPEND=/CSBM
```


- MapsApplication启动类中添加了一个自定义的MapsInitializeListener用来对映射地址配置文件进行加载。


- MapsInitializeListener初始化监听器负责对maps.properties中的地址映射配置进行加载。 

- MapFilter通过@WebFilter的urlPatterns属性来对过滤器需要过滤的请求进行配置，本例仅拦截匹配 /WXOPEND 的请求URL进行过滤。

- MappingHandler主要是负责自定义context-path，因为spring自身的context-path一但配置使用将没法修改，所以不使用他，我们自己定义一个。
  并在application.yal配置如下配置信息,本质上与spring的context-path大不一样，这个实际上是在RequestMappingInfo上面拼接一段path,效果一样。
```
context-path: /CSBM
```

- WebMvcConfiguration实现了WebMvcRegistrations接口。负责把MappingHandler注册到web

- 请求测试

启动应用，访问 http://localhost:8088/WXOPEND/bookShop/getAvPrice 和 http://localhost:8088/CSBM/bookShop/getAvPrice,
都能访问到getAvPrice接口。