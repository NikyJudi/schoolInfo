//生成菜单
var menuItem = Vue.extend({
    name: 'menu-item',
    props: {item: {}},
    template: [
        '<li>',
        '<a v-if="item.type === 0" href="javascript:;">',
        '<i v-if="item.icon != null" :class="item.icon"></i>',
        '<span>{{item.name}}</span>',
        '<i class="fa fa-angle-left pull-right"></i>',
        '</a>',
        '<ul v-if="item.type === 0" class="treeview-menu">',
        '<menu-item :item="item" v-for="item in item.list"></menu-item>',
        '</ul>',
        '<a v-if="item.type === 1" :href="\'#\'+item.url"><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i> {{item.name}}</a>',
        '</li>'
    ].join('')
});
 function height(hei) {
        //document.body.offsetHeight
     var min = document.documentElement.clientHeight - 210;
     if(min > hei){
         hei = min;
     }

    $("body").find('iframe').each(function () {
        $(this).height(hei +50);
    });
    var $content = $('.content');
    $content.height(hei  +50);

}


//注册菜单组件
Vue.component('menuItem', menuItem);

var vm = new Vue({
    el: '#rrapp',
    data: {
        user: {},
        menuList: {},
        main: "sys/main.html",
        password: '',
        newPassword: '',
        navTitle: "控制台"
    },
    methods: {
        getMenuList: function (event) {
            $.getJSON("sys/menu/user?_" + $.now(), function (r) {
                vm.menuList = r.menuList;
            });
        },
        getUser: function () {
            $.getJSON("sys/user/info?_" + $.now(), function (r) {
                vm.user = r.user;
            });
        },
        updatePassword: function () {
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "修改密码",
                area: ['450px', '370px'],
                shadeClose: false,
                content: jQuery("#passwordLayer"),
                btn: ['修改', '取消'],
                btn1: function (index) {
                    var data = "password=" + vm.password + "&newPassword=" + vm.newPassword;
                    $.ajax({
                        type: "POST",
                        url: "sys/user/password",
                        data: data,
                        dataType: "json",
                        success: function (result) {
                            if (result.code == 0) {
                                layer.close(index);
                                layer.alert('修改成功', function (index) {
                                    location.reload();
                                });
                            } else {
                                layer.alert(result.msg);
                            }
                        }
                    });
                }
            });
        }, uploadInfo: function () {
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "个人信息",
                area: ['450px', '370px'],
                shadeClose: false,
                content: jQuery("#infoLayer"),
                btn: ['修改', '取消'],
                btn1: function (index) {
                    
                    $.ajax({
                        type: "POST",
                        url: "sys/user/updateInfo",
                         data: JSON.stringify(vm.user),
                        contentType: "application/json",
                        dataType: "json",
                        success: function (result) {
                            if (result.code == 0) {
                                layer.close(index);
                                layer.alert('修改成功' );
                            } else {
                                layer.alert(result.msg);
                            }
                        }
                    });
                }
            });
        }
    },
    created: function () {
        this.getMenuList();
        this.getUser();
    },
    updated: function () {
        //路由
        var router = new Router();
        routerList(router, vm.menuList);
        router.start();
    }
});


function routerList(router, menuList) {
    for (var key in menuList) {
        var menu = menuList[key];
        if (menu.type == 0) {
            routerList(router, menu.list);
        } else if (menu.type == 1) {
            router.add('#' + menu.url, function () {
                var url = window.location.hash;

                //替换iframe的url
                vm.main = url.replace('#', '');

                //导航菜单展开
                $(".sidebar-trans li").removeClass("active");
                $("a[href='" + url + "']").parents("li").addClass("active");

                $("body").removeClass("sidebar-open");
                vm.navTitle = $("a[href='" + url + "']").text();
            });
        }
    }
}
