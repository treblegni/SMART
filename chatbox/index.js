var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
users = [];
connections = [];


console.log('Server running...');

app.get('/', function(req, res){
    res.sendFile(__dirname + '/index.html');
  });

io.on('connection', function(socket){
    connections.push(socket);
    console.log('Connected: %s sockets connected', connections.length);
    
    // Disconnect
    socket.on('disconnect', function(data){
        // if(!socket.username) return;
        users.splice(users.indexOf(socket.username), 1);
        updateUsernames();

        connections.splice(connections.indexOf(socket), 1);
        console.log('Disconnected: %s sockets connected', connections.length);
    });
    
    socket.on('send message', function(data){
        // console.log(data);
        io.sockets.emit('new message', {msg: data, user: socket.username});

    });
    
    // New User
    socket.on('new user', function(data, callback){
        callback(true);
        socket.username = data;
        users.push(socket.username);
        updateUsernames();
    });
    
    function updateUsernames(){
        io.sockets.emit('get users', users);
    }

  });

http.listen(3000, function(){
  // console.log('listening on *:3000');
});