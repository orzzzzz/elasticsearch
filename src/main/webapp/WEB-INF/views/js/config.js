/**
 * Created by zhangmengwen on 2016/10/25.
 */
require.config({
    baseUrl: '/js',
    paths: {
        'jquery': 'lib/jquery-1.12.3.min',
        'hbs': 'lib/handlebars-1.0.0',
        'pagination': 'lib/pagination/jquery.pagination'
    },
    shim: {
        'pagination': {
            deps: ['jquery','lib/css!lib/pagination/pagination.css']
        }
    }
});