var express = require('express');
var path = require('path');
var app = express();
var server = require('http').Server(app);

var PORT = 8000;

app.use(express.static(path.join(__dirname, "public")));
app.listen(PORT);
console.log('Running on http://localhost:' + PORT);
