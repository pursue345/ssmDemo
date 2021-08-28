1.连接数据库步骤
    ·加载JDBC驱动程序  Class.forName("com.mysql.jdbc.Driver");
    ·创建数据库的连接 DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssm?useUnicode=true&characterEncoding=utf-8","root", "123456");
    ·创建一个准备好的声明
        1、执行SQL语句。通常通过Statement实例实现  con.createStatement() ;
        2、执行动态SQL语句。通常通过PreparedStatement实例实现 con.prepareStatement(sql) ;
        3、执行数据库存储过程。通常通过CallableStatement实例实现 con.prepareCall("{CALL demoSp(? , ?)}") ;
    ·执行SQL语句 ResultSet executeQuery(String sqlString)
    ·遍历结果集
    ·处理异常，关闭JDBC对象资源
2.PreparedStatement与Statement区别
    ·PreparedStatement是预编译的,对于批量处理可以大大提高效率. 也叫JDBC存储过程
    ·Statement不会初始化，没有预处理，每次都是从0开始执行SQL
    ·prepareStatement可以在SQL中用?替换变量；
    ·Statement不支持 ? 替换变量，只能在sql中拼接参数；