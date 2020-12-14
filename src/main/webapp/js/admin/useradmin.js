$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/user/list?type=2',
        datatype: "json",
        colModel: [
			{
				label: 'userId',
				name: 'userId',
				index: 'user_id',
				width: 50,
				key: true,
				hidden: true,
				formatter: function (v, a, r) {
					return "<a onclick='vm.info(\"" + r.id + "\")'>" + v + " </a>"
				}
			},
				{
					label: '用户名',
					name: 'username',
					index: 'username',
					width: 80
				},
				{
					label: '邮箱',
					name: 'email',
					index: 'email',
					width: 80
				},
				{
					label: '手机号',
					name: 'mobile',
					index: 'mobile',
					width: 80
				},
				{ label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
						return value === 0 ?
							'<span class="label label-danger">禁用</span>' :
							'<span class="label label-success">正常</span>';
					}},
				{
					label: '创建时间',
					name: 'createTime',
					index: 'create_time',
					width: 80
				},
				{
					label: '角色类型',
					name: 'type',
					index: 'type',
					width: 80, formatter: function(value, options, row){
						if (value === '1'){
							return '<span class="label label-danger">学生</span>';
						}
						if (value === '3'){
							return '<span class="label label-danger">图书管理员</span>';
						}
						if (value === '4'){
							return '<span class="label label-danger">食堂管理员</span>';
						}
						if (value === '5'){
							return '<span class="label label-danger">校医管理员</span>';
						}
						if (value === '6'){
							return '<span class="label label-danger">教师</span>';
						}
						if (value === '7'){
							return '<span class="label label-danger">公寓管理员</span>';
						}

					}
				},

				{
					label: '性别',
					name: 'xb',
					index: 'xb',
					width: 80
				},

			],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			username: null
		},
		showList: true,
		title:null,
		roleList:{},
		user:{
			status:1,
			roleIdList:[]
		},
		zys:[],
		bjs:[],

	},
	created:function () {
		$.getJSON("../sys/user/info",function(r){
			vm.user = r.user;
		})

		$.getJSON("../zy/list2",function(r){
			vm.zys = r.list;
		})
		$.getJSON("../bj/list2",function(r){
			vm.bjs = r.list;
		})

	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.roleList = {};
			vm.user = {status:1,bj:vm.bjs[0].id,zy:vm.zys[0].id,type:1,xb:"男"};
			
			//获取角色信息
			this.getRoleList();
		},
		update: function () {
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
			
			vm.getUser(userId);
			//获取角色信息
			this.getRoleList();
		},
		del: function () {
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/user/delete",
				    data: JSON.stringify(userIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
                                vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.user.userId == null ? "../sys/user/save" : "../sys/user/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.user),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		getUser: function(userId){
			$.get("../sys/user/info/"+userId, function(r){
				vm.user = r.user;
			});
		},
		getRoleList: function(){
			$.get("../sys/role/select", function(r){
				vm.roleList = r.list;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'username': vm.q.username},
                page:page
            }).trigger("reloadGrid");
		}
	}
});