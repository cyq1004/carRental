<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>员工管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <%--<link rel="icon" href="favicon.ico">--%>
    <link rel="stylesheet" href="${yeqifu}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${yeqifu}/static/css/public.css" media="all"/>
    <link rel="stylesheet" href="${yeqifu}/static/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="${yeqifu}/static/layui_ext/dtree/font/dtreefont.css">
</head>
<body class="childrenBody" style="background: rgba(255,253,244,0.51)">

<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">员工姓名:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="staffName" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入员工名称" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">身份证号:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="identity" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入身份证号" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">家庭地址:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="address" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入员工家庭地址" style="height: 30px;border-radius: 10px">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">员工电话:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="phone" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入员工电话" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">员工岗位:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="career" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入员工岗位" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-inline">
                <input type="radio" name="sex" value="0" title="男">
                <input type="radio" name="sex" value="1" title="女">
            </div>
            <button type="button"
                    class="layui-btn layui-btn-normal layui-icon layui-icon-search layui-btn-radius layui-btn-sm"
                    id="doSearch" style="margin-top: 4px">查询
            </button>
            <button type="reset"
                    class="layui-btn layui-btn-warm layui-icon layui-icon-refresh layui-btn-radius layui-btn-sm"
                    style="margin-top: 4px">重置
            </button>
            <button type="button"
                    class="layui-btn layui-btn-green layui-icon layui-icon-download-circle layui-btn-radius layui-btn-sm"
                    id="exportExcel" style="margin-top: 4px">导出
            </button>
        </div>
    </div>

</form>

<!-- 数据表格开始 -->
<table class="layui-hide" id="staffTable" lay-filter="staffTable"></table>
<div id="staffToolBar" style="display: none;">
    <button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm layui-btn-radius" lay-event="deleteBatch">
        批量删除
    </button>
</div>
<div id="customerBar" style="display: none;">
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="leave">离职</a>
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
</div>

<!-- 修改员工 -->
<div style="display: none;padding: 20px" id="UpdateDiv">
    <form class="layui-form" lay-filter="updateDataFrm" id="updateDataFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">员工姓名:</label>
                <div class="layui-input-inline">
                    <input type="text" name="staffName" lay-verify="required" placeholder="请输入员工姓名" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">身份证号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="identity" readonly lay-verify="required" placeholder="请输入客户身份证号" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">员工家庭地址:</label>
                <div class="layui-input-inline">
                    <input type="text" name="address" placeholder="请输入员工家庭地址" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">岗位:</label>
                <div class="layui-input-inline">
                    <input type="text" name="career" placeholder="请输入岗位" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">薪资</label>
                <div class="layui-input-inline">
                    <input type="text" name="salary" placeholder="请输入薪资" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">员工电话:</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" lay-verify="required|phone" placeholder="请输入员工电话" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">员工性别:</label>
                <div class="layui-input-inline">
                    <input type="radio" name="sex" value="1" checked="checked" title="男">
                    <input type="radio" name="sex" value="0" title="女">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center;padding-right: 120px">
                <button type="button"
                        class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
                        lay-filter="doUpdateSubmit" lay-submit="">提交
                </button>
                <button type="reset"
                        class="layui-btn layui-btn-warm layui-btn-md layui-icon layui-icon-refresh layui-btn-radius">重置
                </button>
            </div>
        </div>
    </form>
</div>

<!--添加员工-->
<div style="display: none;padding: 20px" id="saveDiv">
    <form class="layui-form" lay-filter="saveDataFrm" id="saveDataFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">员工姓名:</label>
                <div class="layui-input-inline">
                    <input type="text" name="staffName" lay-verify="required" placeholder="请输入员工姓名" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">身份证号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="identity" lay-verify="required" placeholder="请输入员工身份证号" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">员工家庭地址:</label>
                <div class="layui-input-inline">
                    <input type="text" name="address" placeholder="请输入员工家庭地址" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">岗位:</label>
                <div class="layui-input-inline">
                    <input type="text" name="career" placeholder="请输入岗位" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">薪资:</label>
                <div class="layui-input-inline">
                    <input type="text" name="salary" placeholder="请输入薪资" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">员工电话:</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" lay-verify="required|phone" placeholder="请输入员工电话" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">性别:</label>
                <div class="layui-input-inline">
                    <input type="radio" name="sex" value="0" checked="checked" title="男">
                    <input type="radio" name="sex" value="1" title="女">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center;padding-right: 120px">
                <button type="button"
                        class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
                        lay-filter="doSaveSubmit" lay-submit="">提交
                </button>
                <button type="reset"
                        class="layui-btn layui-btn-warm layui-btn-md layui-icon layui-icon-refresh layui-btn-radius">重置
                </button>
            </div>
        </div>
    </form>
</div>

<script src="${yeqifu}/static/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer', 'form', 'table'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var dtree = layui.dtree;
        //渲染数据表格
        tableIns = table.render({
            elem: '#staffTable'   //渲染的目标对象
            , url: '${yeqifu}/staff/loadAllStaff.action' //数据接口
            , method: 'post'
            , title: '员工数据表'//数据导出来的标题
            , toolbar: "#staffToolBar"   //表格的工具条
            , height: 'full-210'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                , {field: 'staffName', title: '员工姓名', align: 'center', width: '100'}
                , {field: 'identity', title: '身份证号', align: 'center', width: '185'}
                , {field: 'address', title: '家庭地址', align: 'center', width: '125'}
                , {field: 'career', title: '岗位', align: 'center', width: '125'}
                , {field: 'phone', title: '手机号码', align: 'center', width: '150'}

                , {
                    field: 'sex', title: '性别', align: 'center', width: '120', templet: function (d) {
                        return d.sex == '0' ? '<font color=blue>男</font>' : '<font color=red>女</font>';
                    }
                }
                , {field: 'joinTime', title: '入职时间', align: 'center', width: '200'}
                , {field: 'leaveTime', title: '离职时间', align: 'center', width: '200'}
                , {fixed: 'right', title: '操作', toolbar: '#customerBar', align: 'center', width: '250'}
            ]],
            done:function (data, curr, count) {
                //不是第一页时，如果当前返回的数据为0那么就返回上一页
                if(data.data.length==0&&curr!=1){
                    tableIns.reload({
                        page:{
                            curr:curr-1
                        }
                    })
                }
            }
        });

        //模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchFrm").serialize();
            tableIns.reload({
                url: "${yeqifu}/staff/loadAllStaff.action?" + params,
                page: {curr: 1}
            })
        });

        //导出
        $("#exportExcel").click(function () {
            var url = "${yeqifu}/customer/exportExcel.action"
            var params = $("#searchFrm").serialize();
            $.post(url, params, function(result) {
                if(200 == result.code){
                    alert("导出成功");
                }else{
                    alert("导出失败")
                }
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(staffTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddCustomer();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(staffTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除
                layer.confirm('真的删除【' + data.staffName + '】这个员工么？', function (index) {
                    //向服务端发送删除指令
                    $.post("${yeqifu}/staff/delete.action", {identity: data.identity}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent === 'edit') { //编辑
                //编辑，打开修改界面
                openUpdateCustomer(data);
            } else if (layEvent === 'leave') { //离职
                layer.confirm('真的辞退【' + data.staffName + '】这个员工么？', function (index) {
                    //向服务端发送删除指令
                    $.post("${yeqifu}/staff/updateLeave.action", {identity: data.identity}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            }
        });

        var url;
        var mainIndex;

        //打开添加页面
        function openAddCustomer() {
            mainIndex = layer.open({
                type: 1,
                title: '添加客户',
                content: $("#saveDiv"),
                area: ['700px', '320px'],
                success: function (index) {
                    //清空表单数据
                    $("#saveDataFrm")[0].reset();
                    url = "${yeqifu}/staff/add.action";
                }
            });
        }

        //打开修改页面
        function openUpdateCustomer(data) {
            mainIndex = layer.open({
                type: 1,
                title: '修改员工',
                content: $("#UpdateDiv"),
                area: ['700px', '320px'],
                success: function (index) {
                    form.val("updateDataFrm", data);
                    url = "${yeqifu}/staff/update.action";
                }
            });
        }

        //保存更新
        form.on("submit(doUpdateSubmit)", function (obj) {
            //序列化表单数据
            var params = $("#updateDataFrm").serialize();
            $.post(url, params, function (obj) {
                if(obj.code == 0) {
                    layer.msg(obj.msg);
                    //关闭弹出层
                    layer.close(mainIndex)
                    //刷新数据 表格
                    tableIns.reload();
                }else {
                    layer.msg(obj.msg);
                }
            })
        });

        //保存
        form.on("submit(doSaveSubmit)", function (obj) {
            //序列化表单数据
            var params = $("#saveDataFrm").serialize();
            $.post(url, params, function (obj) {
                if(obj.code == 0) {
                    layer.msg(obj.msg);
                    //关闭弹出层
                    layer.close(mainIndex)
                    //刷新数据 表格
                    tableIns.reload();
                }else {
                    layer.msg(obj.msg);
                }
            })
        });

        //批量删除
        function deleteBatch() {
            //得到选中的数据行
            var checkStatus = table.checkStatus('staffTable');
            var data = checkStatus.data;
            layer.alert(data.length);
            var params = "";
            $.each(data, function (i, item) {
                if (i == 0) {
                    params += "ids=" + item.identity;
                } else {
                    params += "&ids=" + item.identity;
                }
            });
            layer.confirm('真的要删除这些员工么？', function (index) {
                //向服务端发送删除指令
                $.post("${yeqifu}/staff/deleteBatch.action", params, function (res) {
                    layer.msg(res.msg);
                    //刷新数据表格
                    tableIns.reload();
                })
            });
        }

    });

</script>
</body>
</html>

