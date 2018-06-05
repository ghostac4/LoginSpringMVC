function validate(){
	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	
	if(username == ""){
		document.getElementById("usernameError").innerHTML="Username cannot be blank";
		return false;
	}else{
		document.getElementById("usernameError").innerHTML="";
	}
	if(password == ""){
		document.getElementById("passwordError").innerHTML="Password Cannot be blank";
		return false;
	}else{
		document.getElementById("passwordError").innerHTML="";
	}
	return true;
};