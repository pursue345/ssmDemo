 1.架构
    C/S  Client/Server（客户端服务器）
        需要在机器安装一个客户端来访问服务器 
        比如：QQ，好处就是访问的速度快（服务端的压力小），安全性高。缺点就是升级困难，不便于维护
    B/S(Browser/Server)（浏览器服务器）
        用浏览器作为客户端来访问服务器端资源。
        优点就是不需要安装客户端缺点就是服务器端压力大，访问速度慢，安全性差。
        降低服务器压力：JS Ajax ..Jquery
2.WEB
    2.1 动态web资源开发技术统称为Javaweb
        静态web资源（如html 页面）：指web页面中供人们浏览的数据始终是不变。
            静态web资源开发技术：Html
        动态web资源：指web页面中供人们浏览的数据是由程序产生的，不同时间点访问web页面看到的内容各不相同。
            常用动态web资源开发技术：JSP/Servlet、ASP、PHP等
    2.2 WEB应用程序
        一个web应用由多个静态web资源和动态web资源组成，如:html、css、js文件，Jsp文件、java程序、支持jar包、配置文件等等
    2.3 WEB服务器
        作用：完成底层网络通迅。不管什么web资源，想被远程计算机访问，都必须有一个与之对应的网络通信程序，当用户来访问时，这个网络通信程序读取web资源数据，并把数据发送给来访者
        服务器种类：
            ·WebLogic：用于开发、集成、部署和管理大型分布式Web应用、网络应用和数据库应用的Java应用服务器
            ·WebSphere：基于 Java 的应用环境，用于建立、部署和管理 Internet 和 Intranet Web 应用程序
            ·Tomcat：实现了JAVA EE标准的最小的WEB服务器，是Apache 软件基金会的Jakarta 项目中的一个核心项目，由Apache、Sun 和其他一些公司及个人共同开发而成
            ·IIS（Internet Information Services）：Microsoft的Web服务器产品，允许在公共Intranet或Internet上发布信息的Web服务器
            
3.XML基本概念
    ·XML是一种数据的格式，可以用这种数据格式来存储和表表示一组数据。
    ·XML基于文本格式，具有简单性、可扩展性、交互性和灵活性
    ·xml格式一般在项目中做配置文件
    ·还可以不同站点之间作为一种通用的数据交换格式使用，例如webservice....
    ·另外也可以存储少量的数据，类似于数据库的功能
    
4.解析XML方式
    DOM解析 (SUN公司)
    SAX解析
    JDOM
    DOM4J解析

5.Tomcat
    客户端和服务器端之间的交互式通过Socket来实现的，它术语应用层的协议。
    Tomcat的所有配置都放在conf文件夹之中，里面的server.xml文件是配置的核心文件,端口在该文件中修改
    命令行将web应用打包为war包：jar -cvf xxx.war web工程名
