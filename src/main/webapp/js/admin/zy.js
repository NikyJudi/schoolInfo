$(function () {
    $("#jqGrid").jqGrid({
        url: '../zy/list',
        datatype: "json",
        colModel: [
                                                {
                        label: 'id',
                        name: 'id',
                        index: 'id',
                        width: 50,
                        key: true,
                        hidden: true,
                        formatter: function (v, a, r) {
                            return "<a onclick='vm.info(\"" + r.id + "\")'>" + v + " </a>"
                        }
                    },
                                                                {
                        label: '专业',
                        name: 'name',
                        index: 'name',
                        width: 80
                    }
                            ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

                                                
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
name : ''
},
showAdd : false,
    showInfo : false,
    showList: true,
    title: null,
	 user:{},
    users:[],

        
zy: {
},
},
created:function () {
$.getJSON("../sys/user/info",function(r){
				vm.user = r.user;
			})

                            
},
methods: {
    query: function () {
        vm.reload();
    }
,
    add: function () {
        vm.showAdd = true;
        vm.showList = false;
        vm.title = "新增";
        vm.zy = {

                                                                        
        };
    }
,
    update: function (event) {
        var id =
        getSelectedRow();
        if (id== null
    )
        {
            return;
        }

        vm.showAdd = true;
        vm.showList = false;
        vm.title = "修改";

        vm.getInfo(id)
    }
,
    saveOrUpdate: function (event) {
        var url = vm
    .zy.id ==
        null ? "../zy/save" : "../zy/update";
        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(vm.zy),
            success: function (r) {
                if (r.code === 0) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                } else {
                    alert(r.msg);
                }
            }
        });
    }
,
    del: function (event) {
        var ids = getSelectedRows();
        if (ids == null) {
            return;
        }

        confirm('确定要删除选中的记录？', function () {
            $.ajax({
                type: "POST",
                url: "../zy/delete",
                data: JSON.stringify(ids),
                success: function (r) {
                    if (r.code == 0) {
                        alert('操作成功', function (index) {
                            $("#jqGrid").trigger("reloadGrid");
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        });
    }
,
    getInfo: function (id) {
        $.get("../zy/info/" +id, function (r) {
            vm.zy = r.zy;
        });
    }
,
    info : function (id) {
        if (isNaN(id)) {
            id
            = getSelectedRow();
            if (id== null
        )
            {
                return;
            }
        }
        vm.showAdd = false;
        vm.showList = false;
        vm.showInfo = true;
        vm.title = "详情";

        vm.getInfo(id)
    }
,
    reload: function (event) {
        vm.showList = true;
        vm.showInfo = false;
        vm.showAdd = false;
        var page = $("#jqGrid").jqGrid('getGridParam', 'page');
        $("#jqGrid").jqGrid('setGridParam', {
            postData: vm.q,
            page: page
        }).trigger("reloadGrid");
    }
}
});