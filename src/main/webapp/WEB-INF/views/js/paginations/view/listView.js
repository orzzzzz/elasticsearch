/**
 * Created by zhangmengwen on 2016/10/27.
 */
define(['hbs', 'jquery', 'pagination'], function (handlebars) {
    function init() {
        _initList(0);
        // _initPagination(total, 0);
    }

    var total = 10;
    
    
    function _initPagination(total, index) {
        $('#js-pagination').pagination(total, {
            items_per_page:2,
            num_display_entries:1,    //...前的页数
            current_page:index,
            num_edge_entries:1,         //...后的页数
            link_to:"#",
            first_text: '首页',
            last_text: '末页',
            ellipse_text:"...",
            prev_show_always:true,
            next_show_always:true,
            prev_text: '<i class="icon-triangle-left"></i>上一页',
            next_text: '下一页<i class="icon-triangle-right"></i>',
            callback:_pageSelectCallback
        });
    }

    var list1 = {
        "list": [
            {"name": "0"},
            {"name": "1"},
            {"name": "2"},
            {"name": "3"},
            {"name": "4"},
            {"name": "5"},
            {"name": "6"},
            {"name": "7"},
            {"name": "8"},
            {"name": "9"}
        ]
    };

    var list2={
        list:["00","10","20","30","40","50","60","70","80","90"]
    };
    function _initList(pageNum) {
        var template = handlebars.compile($('#template').html());
        var html = template(list2);
        $('#list').find('ul').html(html);
        _initPagination(list1.list.length, pageNum);
    }
    
    function _pageSelectCallback(pageNum) {
        _initList(pageNum);
    }

    return {
        init:init
    }
});