console.log("js imported!!!");
var ws;
function openConnection(){
	const url = "ws://localhost:9011/customwebsocket?name=kavi"
	ws = new WebSocket(url);
	ws.onopen = function(){
		$("#clientMessage").append($("<a>").append("Connection opened<br>").html());
	}
	
	ws.onmessage = function(event){
		console.log("Socket connected", event);
		$("#serverMessage").append($("<a>").append(event.data+"<br>").html());
	}
	
	ws.onclose = function(event){
		console.log("Socket disconnected", event);
		$("#clientMessage").append($("<a>").append("Connection closed<br>").html())
	}
}

function greet(){
	ws.send("greet");
}

function printmessage(){
	ws.send("printmessage");
}

function closeConnection(){
	ws.close();
}