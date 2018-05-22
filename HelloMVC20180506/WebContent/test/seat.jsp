<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath() %>/js/jquery-3.3.1.js"></script>
<style>
	ul, li{outline:none; list-style: none; padding:0px; margin:0px; float:left;  border:1px solid #333;  width:40px; height:40px; text-align:center; font:10px; margin-right:2px; margin-bottom:2px; cursor:pointer; }
	.check{background:#333;color:#fff;}
	#adult_count{margin-top:50px; padding:50px; font-size:50px; text-align:center; }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> </title>
</head>

<div>
<select name="" id="adult_count">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
</select>
</div>

<div id="you">
	
</div>



<body>

<script>

var row = new Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I');
var column = new Array();
var setting = document.getElementById("you");
var select_col = document.getElementById("adult_count");
var sety = document.getElementsByTagName("li");
var select_tmp = 0;


var select_val = 1;


select_col.onclick= function(){
	select_val=this.value;
}


k=0;
for(i=0; i< row.length; i++){
	for(j=1; j<20; j++){
		k++;
		column[k] = row[i]+j;
		setting.innerHTML += "<li>"+ j +"</li>";
	}
	setting.innerHTML += "<div style='clear:both'></div>";
}


var temp ="";

for(i=1; i< column.length; i++){
	temp += column[i]+",";
}


var arr = Array.from(sety);
var temp_arr = [];

for(i=0; i<sety.length; i++){ 
	sety[i].onclick = function(){
		
		var checker = sety[parseInt(arr.indexOf(this))];	
		
		if(temp_arr[0]==checker){
			console.log("오 맞았어");
			
		}else{
			temp_arr =[];
		}
		

		var counter = document.getElementById("adult_count").value;
		
		this.classList.toggle("check");
	 	
		temp_arr.push(this);
		
	 	for(j=1; j<counter; j++){
	 		temp_arr.push(sety[parseInt(arr.indexOf(this))+parseInt(j)].classList.toggle("check"));
	 	}
	 	console.log(temp_arr.length);
	}
	
}




</script>


</body>
</html>