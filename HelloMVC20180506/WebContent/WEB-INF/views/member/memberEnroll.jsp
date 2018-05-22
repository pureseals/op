<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<script>
function fn_checkIdDuplicate(){
	var userId = $("#user_Id").val().trim();
	if(userId.length<4){
		alert("아이디는 4글자 이상 가능합니다.");
		return;
	}
	var url = "<%= request.getContextPath() %>/member/checkIdDuplicate";
	var title = "checkIdDuplicate";
	var status = "left=350px, top=100px, width=300px, height=200px";
	//window.open(url,title,status);
	var popup = window.open(url, title, status);
	
	var checkIdDuplicateFrm = document.checkIdDuplicateFrm;
	checkIdDuplicateFrm.userId.value=userId;
	
	//팝업과 form연결
	
	checkIdDuplicateFrm.target = title;
	checkIdDuplicateFrm.action = url;
	checkIdDuplicateFrm.submit();
}

function fn_enrollValidate(){
    
	$userId = $("#user_Id");
	$passWord = $("#pass_Word");
	$passWordChk = $("#pass_WordChk");
	$userName = $("#userName");
	$age = $("#age");
	$email = $("#email");
	$phone = $("#phone");
	$address =$("#address");
	$gender =$("input[name=gender]");
	$hobby = $("input[name=hobby]");
	//빈객체 체크
	
	
	if($userName.val() ==""){
		console.log("이름을 입력해주세요");
		$userName.focus();
		return false;		
	}
	
	if($phone.val() == ""){
		console.log("전화번호를 입력해주세요.")
		$phone.focus();
		return false;
	}
	
	if($address.val() == ""){
		console.log("주소를 입력해주세요.")
		$phone.focus();
		return false;
	}	
	
	
	
	
	
}

function regExpVaildation(regExp, el, msg) {
    if(regExp.test(el.val())){
        return;
    }else{
	    console.log(msg);
        console.log(el.val());
        console.log(regExp.test(el.val()));
    }
}



$(function(){
    var regType = /^[A-Za-z0-9]{6,12}$/g;
	$userId = $("#user_Id");
	$passWord = $("#pass_Word");
	$passWordChk = $("#pass_WordChk");
	$userName = $("#userName");
	$age = $("#age");
	$email = $("#email");
	$phone = $("#phone");
	$address =$("#address");
	$gender =$("input[name=gender]");
	$hobby = $("input[name=hobby]");
	
	
	
	$passWord.keyup(function(e){
		regExpVaildation(regType, $passWord, "영문 숫자만 허용 6~12자리");
	});
	//패스워드 체크
	$passWordChk.keyup(function(e){
		$pw_val = $("#pass_Word").val();
		$pwchk_val = $("#pass_WordChk").val();
		

		if(($pwchk_val=="")||($pw_val != $pwchk_val)){
			console.log("맞지않아");
		}else{
			console.log("맞아부렁");
		}
	});
 
})


</script>
<section id="enroll-container">
<h2>회원가입 정보입력</h2>

<form action="<%= request.getContextPath() %>/member/memberEnrollEnd" name="memberEnrollFrm" method="post" onsubmit="return fn_enrollValidate();">
<table>
	<tr>
		<th>아이디</th>
		<td><input type="text" name="userId" id="user_Id" required /><input type="button" value="중복검사" id="btn-idValid" onclick="fn_checkIdDuplicate()" /><input type="hidden" name="idValid" value="0"/></td>
		
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="password" name="password" id="pass_Word" /></td>
	</tr>
	<tr>
		<th>패스워드확인</th>
		<td><input type="password" name="password_chk" id="pass_WordChk" /></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input type="text" name="userName" id="userName" /></td>
	</tr>
	<tr>
		<th>나이</th>
		<td><input type="number" name="age" id="age" /></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><input type="email" name="email" id="email" /></td>
	</tr>
	<tr>
		<th>휴대폰</th>
		<td><input type="tel" id="phone" name="phone" placeholder="(-없이)01012345678" /></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input type="text" name="address" id="address" /></td>
	</tr>	
	<tr>
		<th>성별</th>
		<td>
			<input type="radio" name="gender" id="gender0" value="M" checked />
			<label for="gender0">남</label>
			<input type="radio" name="gender" id="gender1" value="F"/>
			<label for="gender1">여</label>
		</td>
	</tr>
	<tr>
		<th>취미</th>
		<td><input type="checkbox" name="hobby" id="hobby1"  value ="운동" />
		<label for="hobby1">운동</label>
		<input type="checkbox" name="hobby" id="hobby2"  value ="등산" />
		<label for="hobby2">등산</label>
		<input type="checkbox" name="hobby" id="hobby3"  value ="독서" />
		<label for="hobby3">독서</label>
		<input type="checkbox" name="hobby" id="hobby4"  value ="게임"/>
		<label for="hobby4">게임</label>
		<input type="checkbox" name="hobby" id="hobby5"  value ="여행"/>
		<label for="hobby5">여행</label>
		</td>
	</tr>

</table>
<input type="submit" value="가입" />
<input type="reset" value="초기화" />

</form>

<!-- 아이디중복체크폼 -->
<form name="checkIdDuplicateFrm" method="post">
	<input type="hidden" name="userId" />
</form>



</section>
<style>
#cinema_container *{box-sizing: border-box; outline:none;}
#cinema_container{background:#fff; padding:0px;}
#cinema_container table{border-collapse: collapse; padding:0p; border-top:3px solid #503396; width:100%}
#cinema_container table th, #cinema_container table td{padding:10px; border-bottom:1px solid #ccc; }
#cinema_container th{background:#ececec; text-align:left;}
#cinema_container table th{width:13%;}
#cinema_container input{padding:10px; width:100%;}
#cinema_container input[type='button']{background:#ddd; border:none;}

#cinema_container table .birth_tr td{padding:10px 0px;}
#cinema_container table .birth_tr td input{text-align:center;}
#cinema_container table .birth_tr .birth{width:80px; padding:10px;}
#cinema_container table .birth_tr td:first-of-type{padding-right:0px;}
#cinema_container table .birth_tr td:nth-of-type(3){padding:10px 0px }
#cinema_container table .birth_tr td:nth-of-type(5){padding-left: 0px}


#cinema_container table .phone_tr td{padding:10px 0px; text-align:center;}
#cinema_container table .phone_tr td input{text-align:center;;}
#cinema_container table .phone_tr .phone{width:80px; padding:10px;}
#cinema_container table .phone_tr td:first-of-type{padding-right:0px; width:90px;}
#cinema_container table .phone_tr td:nth-of-type(3){padding:10px 0px; }
#cinema_container table .phone_tr td:nth-of-type(5){padding-left: 0px}

.submit_tr button{ background :#503396; border:none; padding:20px; color:#fff; font-size:18px;}
.submit_tr .cancle_btn{background:#ddd; color:#898989;}
.submit_btn{ background :#503396; border:none; padding:20px; color:#fff; font-size:18px;}
</style>

<section id="cinema_container">
	<form action="<%= request.getContextPath() %>/subscriber/subscriberInsertEnd" method="post" onsubmit="return sValidation();">
	<input type="hidden" name="phone_number" />
	<input type="hidden" name="birth" />
		<table>
			<tr>
				<th>아이디</th>
				<td colspan="6"><input type="text" name="sId"/> </td>
				<td><input type="button" value="중복검사" /></td>
				<td><span>영문이나 숫자 혹은 그 조합 8~12자리</span></td>
			</tr>		
			<tr>
				<th>비밀번호</th>
				<td colspan="6"><input type="password" name="password" id="hpwd" /></td>
				<td></td>
				<td><span>영문, 숫자, 특수문자 중 2가지 이상 조합 10자리 이상</span></td>
			</tr>		
			<tr>
				<th>비밀번호 확인</th>
				<td colspan="6"><input type="password" name="password_chk" id="hpwd_chk" /></td>
				<td></td>
				<td></td>
			</tr>		
			<tr>
				<th>이름</th>
				<td colspan="6"><input type="text" name="sName" /> </td>
				<td></td>
				<td></td>
			</tr>	
			<tr class="birth_tr">
				<th>생년월일</th>
				<td class="birth"><input type="number"  min=0  placeholder="1960"/></td>
				<td>-</td>
				<td class="birth"><input type="number" min=0 placeholder="12"/></td>
				<td>-</td>
				<td class="birth"><input type="number" min=0 placeholder="01"/></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>			
			<tr class="phone_tr">
				<th>휴대폰</th>
				<td class="phone"><input type="number"  min=0 placeholder="010"/></td>
				<td>-</td>
				<td class="phone"><input type="number" min=0 placeholder="1234"/></td>
				<td>-</td>
				<td class="phone"><input type="number" min=0 placeholder="5678"/></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>	
			<tr>
				<th>이메일</th>
				<td colspan="6"><input type="email" name="email"/> </td>
				<td></td>
				<td></td>
			</tr>
			
			<tr class="submit_tr">
				<td colspan="9" style="text-align:center">
					<button class="cancle_btn">취소하기</button>
					<input type="submit" class="submit_btn" value="가입하기">
				</td>
			
			</tr>
		</table>
	</form>
	

</section>


<script>
$(function(){

});


function sValidation(){
	$year = $(".birth_tr input").eq(0).val();
	$month = $(".birth_tr input").eq(1).val();
	$date = $(".birth_tr input").eq(2).val();
	
	$birth = $("input[name=birth]");
	
	
	if($month.length < 2){
		$month = "0"+ $month; 
	}
	
	if($date.length < 2){
		$date = "0"+ $date;
	}
	
	
	$birth.val($year+$month+$date);
	
	$first = $(".phone_tr input").eq(0).val();
	$second = $(".phone_tr input").eq(1).val();
	$last= $(".phone_tr input").eq(2).val();
	
	$phone_number = $("input[name=phone_number]");
	
	$phone_number.val($first+$second+$last);
	console.log($phone_number.val());
};
</script>




<%@ include file="/WEB-INF/views/common/footer.jsp" %>