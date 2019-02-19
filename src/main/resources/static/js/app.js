/*

$(document).ready(function)
$().ready(function)
$(function)
 */

$(function () {

    //设置对应菜单为激活状态
    var currentUrl = window.location.href.split('#')[0].split('?')[0];
    var treeviews = document.getElementsByClassName("sidebar-menu")[0].getElementsByClassName("treeview");

    for (var i=0; i< treeviews.length; i++){

        var menu = treeviews[i].getElementsByClassName('treeview-menu')[0];
        var menuItems = menu.getElementsByTagName('li');

        for(var j=0; j< menuItems.length; j++){
            var href = menuItems[j].getElementsByTagName('a')[0].href;
            console.log('href=' + href);
            if(href == currentUrl){
                menuItems[j].classList.add('active');
            }
        }
    }
});

