var ws=require('nodejs-websocket');

var server=ws.createServer(function (conn) {
    console.log('New connection.');

    conn.on('text',function (str) {
        console.log(str);
    });
}).listen(2333);

