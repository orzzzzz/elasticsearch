/**
 * Created by zhangmengwen on 2016/10/25.
 */
define(['hbs', 'jquery'], function (hbs) {
    function init() {
        _change();
    }

    var name = {name: '李四'};

    var nameList = {
        nameList: [
            {name: "王五"},
            {name: "赵六"},
            {name: "刘七"}
        ]
    };

    var data = {
        list: ['HTML5', 'CSS3', "WebGL"],
        "error": "数据取出错误"
    };

    /**
     * 按钮点击事件
     * @private
     */
    function _change() {
        $('#change').click(function () {
            var template = hbs.compile($('#template').html());
            var html = template(data);
            $('.panel').find('table').html(html);
        });
    }

    return {
        init: init
    }
});