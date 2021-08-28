1.HTTP协议
    超文本传输协议,应用层协议，用于定义WEB浏览器与WEB服务器之间交换数据的过程
    HTTP1.0和HTTP1.1的区别
        ·HTTP1.0协议中，客户端与web服务器建立连接后，只能获得一个web资源。
        ·在HTTP1.1协议，允许客户端与web服务器建立连接后，在一个连接上获取多个web资源。
    HTTP请求内容：
        ·一个请求行：请求方式【POST、GET、HEAD、OPTIONS、DELETE、TRACE、PUT】
        ·若干消息头：accept【数据类型】、Accept-Charset【字符集】、Accept-Encoding【支持压缩格式】、If-Modified-Since【数据缓存时间】...
        ·以及实体内容
    HTTP响应：
        ·一个状态行：HTTP版本号　状态码　原因叙述<CRLF> 【HTTP/1.1 200 OK】
        ·若干消息头：Location【跳转到哪】、Server【服务器型号】、Content-Type【返回数据类型】、Refresh【定时刷新】...
        ·以及实体内

2.服务端设置响应头控制客户端浏览器行为
    ·设置Location响应头，实现请求重定向
    ·设置Content-Encoding响应头，告诉浏览器数据的压缩格式
    ·设置content-type响应头，指定回送数据类型
    ·设置refresh响应头，让浏览器定时刷新
    ·设置content-disposition响应头，让浏览器下载文件

3.Servlet创建的三种方式
    ·继承HttpServlet
    ·实现接口Servlet
    ·继承GenericServlet类

4.Servlet的两种配置方式
    ·注解方式：  @WebServlet(value = "/hello", loadOnStartup = 1)
    ·xml方式：web.xml配置 Servlet

5.请求方式
    ·GET请求  效率比POST高
        参数传递方式   GET请求在浏览器地址栏传参数，以?分割URL和传输数据，参数之间以&相连
        数据量限制  GET提交的数据大小有限制（因为浏览器对URL的长度有限制）
        安全问题  GET方式提交数据，会带来安全问题（地址栏显示出来）
        乱码处理方式  每个参数都要处理
    ·POST请求
        参数传递方式    POST方法是把提交的数据放在HTTP包的Body中（地址栏看不到）
        数据量限制      POST方法提交的数据没有限制 (文件上传必须是POST)
        安全问题        POST提交的数据相对安全
        乱码处理方式  统一处理所有

6.Servlet跳转和路径
    ·重定向
        response.sendRedirect("register.html");
        客户浏览器发送http请求---->web服务器接受后发送302状态码响应及对应新的location给客户浏览器--》客户浏览器发现是302响应，则自动再发送一个新的http请求，请求url是新的location地址----》服务器根据此请求寻找资源并发送给客户
        重定向是客户端行为
        重定向是浏览器做了至少两次的访问请求的
        重定向2次跳转之间传输的信息会丢失（request范围）
        重定向浏览器地址改变
    ·转发
        request.getRequestDispatcher("LoginServlet").forward(request, response);
        客户浏览器发送http请求----》web服务器接受此请求--》调用内部的一个方法在容器内部完成请求处理和转发动作----》将目标资源发送给客户
        转发是服务器行为
        转发是浏览器只做了一次访问请求
        转发浏览器地址不变
        转发2次跳转之间传输的信息不会丢失，所以可以通过request进行数据的传递

7.Servlet注解式文件上传
    jsp页面：表单一定是POST请求，表单设置属性：enctype="multipart/form-data"
        <form action="fileServlet" method="post" enctype="multipart/form-data">
    后台：Controller 层具体类上加上@MultipartConfig

8.Cookie和Session
    http协议是一个无状态协议，服务器端无法记录客户端浏览器身份信息
    WEB应用中的会话：指一个客户端浏览器与WEB服务器之间连续发生的一系列请求和响应过程
        ·客户端状态管理技术：将状态保存在客户端（浏览器）。代表性的是Cookie技术。
        ·服务器状态管理技术：将状态保存在服务器端（服务器内存中）。代表性的是session技术

9.JAVAEE核心API和组件
    1、JDBC(Java Database Connectivity) 　　JDBC API为访问不同的数据库提供了一种统一的途径，象ODBC一样，JDBC对开发者屏蔽了一些细节问题，另外，JDCB对数据库的访问也具有平台无关性。
　　2、JNDI(Java Name and Directory Interface) 　　JNDI API被用于执行名字和目录服务。它提供了一致的模型来存取和操作企业级的资源如DNS和LDAP，本地文件系统，或应用服务器中的对象。
　　3、EJB(Enterprise JavaBean) 　　JAVAEE技术之所以赢得媒体广泛重视的原因之一就是EJB。它们提供了一个框架来开发和实施分布式商务逻辑，由此很显著地简化了具有可伸缩性和高度复杂的企业级应用的开发。EJB规范定义了EJB组件在何时如何与它们的容器进行交互作用。容器负责提供公用的服务，例如目录服务、事务管理、安全性、资源缓冲池以及容错性。但这里值得注意的是，EJB并不是实现JAVAEE的唯一途径。正是由于JAVAEE的开放性，使得有的厂商能够以一种和EJB平行的方式来达到同样的目的。
　　4、RMI(Remote Method Invoke) 　　正如其名字所表示的那样，RMI协议调用远程对象上方法。它使用了序列化方式在客户端和服务器端传递数据。RMI是一种被EJB使用的更底层的协议。
　　5、Java IDL/CORBA 　　在Java IDL的支持下，开发人员可以将Java和CORBA集成在一起。他们可以创建Java对象并使之可在CORBA ORB中展开, 或者他们还可以创建Java类并作为和其它ORB一起展开的CORBA对象的客户。后一种方法提供了另外一种途径，通过它Java可以被用于将你的新的应用和旧的系统相集成。
　　6、JSP(Java Server Pages) 　　JSP页面由HTML代码和嵌入其中的Java代码所组成。服务器在页面被客户端所请求以后对这些Java代码进行处理，然后将生成的HTML页面返回给客户端的浏览器。
　　7、Java Servlet 　　Servlet是一种小型的Java程序，它扩展了Web服务器的功能。作为一种服务器端的应用，当被请求时开始执行，这和CGI Perl脚本很相似。Servlet提供的功能大多与JSP类似，不过实现的方式不同。JSP通常是大多数HTML代码中嵌入少量的Java代码，而servlets全部由Java写成并且生成HTML。
　　8、XML(Extensible Markup Language) 　　XML是一种可以用来定义其它标记语言的语言。它被用来在不同的商务过程中共享数据。 XML的发展和Java是相互独立的，但是，它和Java具有的相同目标正是平台独立性。通过将Java和XML的组合，您可以得到一个完美的具有平台独立性的解决方案。
　　9、JMS(Java Message Service) 　　JMS是用于和面向消息的中间件相互通信的应用程序接口(API)。它既支持点对点的域，有支持发布/订阅(publish/subscribe)类型的域，并且提供对下列类型的支持：经认可的消息传递,事务型消息的传递，一致性消息和具有持久性的订阅者支持。JMS还提供了另 一种方式来对您的应用与旧的后台系统相集成。
　　10、JTA(Java Transaction Architecture) 　　JTA定义了一种标准的API，应用系统由此可以访问各种事务监控。
　　11、JTS(Java Transaction Service) 　　JTS是CORBA OTS事务监控的基本的实现。JTS规定了事务管理器的实现方式。该事务管理器是在高层支持Java Transaction API (JTA)规范，并且在较底层实现OMG OTS specification的Java映像。JTS事务管理器为应用服务器、资源管理器、独立的应用以及通信资源管理器提供了事务服务。
　　12、JavaMail 　　JavaMail是用于存取邮件服务器的API，它提供了一套邮件服务器的抽象类。不仅支持SMTP服务器，也支持IMAP服务器。
　　13、JAF(JavaBeans Activation Framework) 　　JavaMail利用JAF来处理MIME编码的邮件附件。MIME的字节流可以被转换成Java对象，或者转换自Java对象。大多数应用都可以不需要直接使用JAF