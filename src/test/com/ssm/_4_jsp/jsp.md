1.JSP作用
    Java Server Pages, java服务器页面
    JSP = HTML+java代码+一系列JSP相关的标签
    JSP是动态资源，放的位置、编写和访问上很像静态资源
    html只能为用户提供静态数据，而Jsp技术允许在页面中嵌套java代码，为用户提供动态数据
    JSP专门负责页面展示数据 不要在JSP中嵌套Java代码！<%  %>  在这个标签中编写的java代码  局部的JAVA代码 JSP翻译之后代码是在 service方法中
    后期用EL和JSTL表达式进行改进！
    ${表达式}

2.JSP语法
    ·JSP指令
        <%@ 指令名称 属性名称1＝“属性值1” 属性名称2＝“属性值2” 。。。。。%>
        <%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    ·includ指令
        静态包含：在翻译时就把两个JSP文件进行合并一个JSP文件，白话把JSP内容直接合并
            <%@ include file="header.jsp" %>
        动态包含：翻译的时候翻译成2个JSP文件，暂时先不会合并JSP文件，当代码执行到include时，才包含另一个JSP的文件的内容包含进来
            <jsp:include page="header.jsp" ></jsp:include>
    ·taglib指令
        在JSP页面中导入JSTL标签库。替换jsp中的java代码片段
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %> 
    ·JSP内置对象
        out对象：JspWriter类型，负责页面数据输出
            <% out.write("bbb"); %>
        response对象：HttpServletResponse类型，负责响应浏览器的请求
        request对象：HttpServletRequest类型，负责接收浏览器发送过来的请求参数，例如表单的信息.......
        session对象：HttpSession类型，负责封装用户请求会话的相关信息，例如用户的登录状态...
        application对象：ServletContext类型，负责封装WEB应用相关应用
        pageContext对象：PageContext类型，封装网页属性相关信息
        page对象:  Object类型，封装当前JSP页面信息
        config对象:  ServletConfig类型，封装了JSP的配置
        exection对象: thowable类型，封装JSP的异常信息

3.JSP运行原理
    第一次访问JSP的时候，JSP会被翻译成.java的源文件，然后再被编译成.class的字节码文件，最后执行字节码文件，呈现运行结果。
    第二次访问该JSP的时候，先去检测这个JSP内容有没有发生改变，如果内容有改变，那么将会执行翻译→编译→执行过程。如果JSP没有发生改变，那么将直接运行字节码文件，返回结果。

4.EL与JSTL表达式
    ·EL
        EL不能遍历集合结构，EL只能一个一个取！！！  ${list[0].name == "aaa"}
        格式：${ EL表达式 }   等价于  findAttribute(name)
    ·JSTL
        JSTL支持通用的、结构化的任务，比如迭代，条件判断，XML文档操作，国际化标签，SQL标签
        还提供了一个框架（standard.jar和jstl.jar）来使用集成JSTL的自定义标签
        核心标签：<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        通用标签：set,out,remove
            <p:set var="k" value="${1+1}"/>
        条件标签：if,choose
            <c:if test = "${8>2}"/>
        迭代标签:for基础遍历、foreach遍历
            <c:forEach var="stu" items="${students}" varStatus="vs">
        格式化标签：
           日期： <fmt:formatDatevalue="${d}" pattern="yyyy-MM-dd"/>
        