1.Ajax（Asynchronous Javascript And XML ）
    异步的javascript和xml。在后台与服务器进行数据交换，异步更新
    AJax本质就是和服务器进行交互，前提条件(不刷新网页！)
    Ajax的原理：通过XmlHttpRequest对象来向服务器发异步请求，从服务器获得数据，然后用javascript来操作DOM而更新页面
        $(function(){
            //请求参数
            var list = {};
            //
            $.ajax({
                //请求方式
                type : "POST",
                //请求的媒体类型
                contentType: "application/json;charset=UTF-8",
                //请求地址
                url : "http://127.0.0.1/admin/list/",
                //数据，json字符串
                data : JSON.stringify(list),
                //请求成功
                success : function(result) {
                    console.log(result);
                },
                //请求失败，包含具体的错误信息
                error : function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        });

    
    