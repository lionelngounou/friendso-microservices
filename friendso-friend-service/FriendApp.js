var friends = [];
var seq = 1; 
var friendsService = {
	exists : function(from_user_id, to_user_id){
		for	(i = 0; i < friends.length; i++) {
			var fc = friends[i];
	    	if((fc.from_user_id==from_user_id && fc.to_user_id==to_user_id) || 
	    		(fc.from_user_id==to_user_id && fc.to_user_id==from_user_id)) 
	    		return true;
		}
		return false;
	},
	findConnection : function(id){
		for	(i = 0; i < friends.length; i++) {
			var fc = friends[i];
	    	if(fc.id==id)
	    		return fc;
		}
		return null;
	},
	listFriends : function (user_id){
		var myFriends = [];
		for	(i = 0; i < friends.length; i++) {
			var fc = friends[i];
	    	if((fc.from_user_id==user_id || fc.to_user_id==user_id) && fc.accepted==true)
	    		myFriends.push(fc);
		}
		return myFriends;
	},
	request : function(from_user_id, to_user_id){
		if(this.exists(from_user_id, to_user_id))
			return null;
		var newFc = {id:seq++, from_user_id:from_user_id, to_user_id:to_user_id, date_created:new Date(), accepted:false, deleted:false};
		friends.push(newFc);
		return newFc;
	},
	accept : function(id, to_user_id){
		var fc = this.findConnection(id);
		if(fc && fc.to_user_id==to_user_id){
			fc.accepted = true;
			fc.dateAccepted = new Date();
			return fc;
		}
		return null;
	}
};

var baseUrl = '/api/v1/friends';
var express = require('express');
var bodyParser = require('body-parser');
var app = express();
var router = express.Router();

app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());

app.get('/api', function (req, res) {
   res.send({alive:true, date:new Date()});
});

app.get('/api/v1', function (req, res) {
   res.send({alive:true, version:'v1', date:new Date()});
});

app.post(baseUrl + '/request', function (req, res) {
	var fc = friendsService.request(req.body.from_user_id, req.body.to_user_id);
   	res.send(fc);
});

app.put(baseUrl + '/accept/:id', function (req, res) {
	var fc = friendsService.accept(req.params.id, req.body.to_user_id);
   	res.send(fc);
});

app.get(baseUrl + '/:user_id', function (req, res) {
	var list = friendsService.listFriends(req.params.user_id);
   	res.send(list);
});

app.get(baseUrl, function (req, res) {
	res.send(friends);
});


var server = app.listen(8082, function () {

  var host = server.address().address;
  var port = server.address().port;

  console.log("Example app listening at http://%s:%s", host, port);
})
