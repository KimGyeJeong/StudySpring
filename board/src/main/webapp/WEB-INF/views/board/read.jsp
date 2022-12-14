<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ include file="../includes/header.jsp"%>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">BoardReadPage</h6>
	</div>
	<div class="card-body">
		<div class="mr-3 ml-3 mb-4">
			<div class="form-group row">
				<label>Bno</label> <input type="text" class="form-control"
					name="bno" readonly="readonly" value="${board.bno }" />
			</div>
			<div class="form-group row">
				<label>Title</label> <input type="text" class="form-control"
					name="title" readonly="readonly" value="${board.title }" />
			</div>
			<div class="form-group row">
				<label>Content</label>
				<textarea class="form-control" rows="3" readonly="readonly"
					name="content">${board.content }</textarea>
			</div>
			<div class="form-group row">
				<label>Writer</label> <input type="text" class="form-control"
					name="writer" readonly="readonly" value="${board.writer }" />
			</div>
			<div class="form-group row">
				<label>Time</label> <input type="text" class="form-control"
					name="reg" readonly="readonly" value="${board.reg }" />
			</div>
		</div>
		<div class="mr-1 ml-2 mb-4">
			<button class="btn btn-warning" data-service="modify">수정하기</button>
			<button class="btn btn-info" data-service="list">돌아가기</button>
		</div>

		<form id="btnForm" action="/board/modify" method="get">
			<input type="hidden" name="bno" value="${board.bno }" id="bno" /> <input
				type="hidden" name="pageNum" value="${cri.pageNum }" /> <input
				type="hidden" name="listQty" value="${cri.listQty }" /> <input
				type="hidden" name="sel" value="${cri.sel }" /> <input
				type="hidden" name="keyword" value="${cri.keyword }" />

		</form>
	</div>
	<!-- 본문 끝 -->


	<!-- 댓글 css 약식 -->
	<style>
.reply_container {
	margin: 0.5rem auto;
	display: flex;
	flex-direction: column;
}

.reply_li {
	margin: 1rem 0;
	display: flex;
	flex-direction: column;
	width: 100%;
}

.replyer_reg_ctn {
	margin: 0.4rem 0;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	width: 100%;
}

.reply_div {
	font-size: 1rem;
	width: 100%;
}

.replyer_div {
	font-weight: bold;
	font-size: 0.9rem;
}

.replyReg_div {
	font-size: 0.7rem;
}

.reply_inputbox {
	display: flex;
	flex-direction: column;
	font-size: 0.7rem;
	width: 100%;
}

.rbox_div {
	margin: 0.2rem 0;
	width: 100%;
}
</style>

	<!-- 댓글 DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Reply</h6>
		</div>
		<div class="card-body">
			<div class="mr-3 ml-3 mb-4">
				<div class="reply_container">
					<div class="reply_li">
						<div class="replyer_reg_ctn">
							<div class="replyer_div">replyer</div>
							<div class="replyReg_div">2022.08.31</div>
						</div>
						<div>
							<div class="reply_div">reply : 댓글내용.......................</div>
						</div>
					</div>

					<div class="reply_li">
						<div class="replyer_reg_ctn">
							<div class="replyer_div">replyer</div>
							<div class="replyReg_div">2022.08.31</div>
						</div>
						<div>
							<div class="reply_div">reply : 댓글내용.......................</div>
							<%-- TODO 0901 수정, 삭제 구현 --%>
							<button class="btn btn-info replyBtn" data-rno="result[i].rno로 채워주기" data-service="modify"> 수정 </button>
							<button class="btn btn-info replyBtn" data-rno="result[i].rno로 채워주기" data-service="delete"> 삭제 </button>
						</div>
					</div>
				</div>

				<%-- 로그인 한 상태일때만 댓글 작성 폼이 보임 --%>
				<%-- 로그인 한 상태 일때(권한체크) --%>				
				<sec:authorize access="isAuthenticated()">
				<hr />
				<div class="reply_inputbox form-group">
						<div class="rbox_div">
							<textarea class="form-control" rows="3" name="reply" id="reply" 
								placeholder="댓글작성.."></textarea>
						</div>
						<div class="rbox_div">
						<%-- 가지고있는 권한의 값을 꺼냄 --%>
							<input type="text" class="form-control" name="replyer" id="replyer"
								value='<sec:authentication property="principal.username"/>' readonly="readonly"/>
						</div>
						<div class="rbox_div">
							<button id="saveReplyBtn" type="button" class="btn btn-warning">Save
								Reply</button>
						</div>
				</div>
				</sec:authorize>
			</div>
		</div>
	</div>
	<!-- 댓글끝 -->

	<script type="text/javascript">
	//댓글 저장시 작동 스크립트
	$(document).ready(function(){
		
		// 본문글 고유번호 bno가져오기
		let bnoVal = "${board.bno}";
		console.log("bno : "+bnoVal);
		
		//댓글 가져오기 함수 호출
		showReplyList();
		//댓글 목록 가져와 뿌려주는 함수
		function showReplyList(){
			//페이징 처리 없음.
			
			//console.log("함수 실행!");	정상작동
			
			//전체 댓글 가져오기 요청
			//bno 보내서 가져와야함
			$.ajax({
				type : "GET",
//				url : "/reply/list/"+bnoVal,
				url : "/reply/list/"+bnoVal + ".json",	//json으로 요청
				data : {bno:bnoVal},
				success : function(result){
					console.log("ajax 요청 성공.");
					console.log(result);	//댓글들 들어있음
				//	console.log(result[0].reply);
				//	console.log(result[0].replyer);
				//	console.log(result[0].reg);
				
				makeList(result);
				
				},
				error : function(e){
					console.log("ajax 요청 실패.");
					console.log(e);
				}
			})
		}//showReplyList
		
		//댓글목록 담을 컨테이너 div
		let replyContainer = $(".reply_container");
		
		//댓글 목록 만들어서 화면에 부착해주는 함수
		function makeList(result){
			console.log("makeList ");
			console.log(result);
			console.log("result.length = "+result.length);
			
			if(result == null || result.length == 0){
				//댓글이 없을때 실행.
				//댓글 목록에 아래와 같이 태그 부착하고
				replyContainer.html("<div class='reply_li'>댓글이없습니다</div>")
				//makeList 함수 강제 종료
				return;
			}
			
			//부착할 html 목록 문자열로 만들기
			let str = "";
			//글이 있는만큼 실행
			for(let i=0; i<result.length; i++){
				/*
				<div class='reply_li'>
					<div class='replyer_reg_ctn'>
						<div class='replyer_div'>replyer</div>
						<div class='replyReg_div'>2022.08.31</div>
					</div>
					<div>
						<div class='reply_div'>reply : 댓글내용.......................</div>
					</div>
				</div>
				*/
				str+="<div class='reply_li'><div class='replyer_reg_ctn'>";
				str+="<div class='replyer_div'>"+result[i].replyer+"</div>";
				str+="<div class='replyReg_div'>"+timeFormat(result[i].reg)+"</div></div>"
				str+="<div><div class='reply_div'>"+result[i].reply+"</div>"
				
			//	<button class='btn btn-info replyBtn' data-rno='result[i].rno로 채워주기' data-service='modify'> 수정 </button>
			//	<button class='btn btn-info replyBtn' data-rno="result[i].rno로 채워주기' data-service='delete'> 삭제 </button>
				
				str+="<button class='btn btn-info replyBtn' data-rno='"+result[i].rno+"' data-service='modify'> 수정 </button>";
				str+="<button class='btn btn-info replyBtn' data-rno='"+result[i].rno+"' data-service='delete'> 삭제 </button>";
			
				str+="</div></div>"
				
			}
			//html 부착
			replyContainer.html(str);
		}
		
		//시간 함수 : 오늘 댓글은 시간, 이전 댓글은 날짜 출력
		function timeFormat(regVal){
            let today = new Date(); 
            let diff = today.getTime() - regVal; 
            let dateObj = new Date(regVal); 
            if(diff < (1000*60*60*24)) { // 24h 보다 작으면 
               let hh = dateObj.getHours(); 
               let mi = dateObj.getMinutes(); 
               let ss = dateObj.getSeconds(); 
               return (hh > 9 ? "" : "0") + hh + ":" 
                  + (mi > 9 ? "" : "0") + mi + ":"
                  + (ss > 9 ? "" : "0") + ss;
            }else {
               let yy = dateObj.getFullYear(); 
               let mm = dateObj.getMonth() + 1; 
               let dd = dateObj.getDate(); 
               return yy + "/" + (mm > 9 ? "" : "0") + mm + "/" + (dd > 9 ? "":"0") + dd;
            }
         }//시간함수종료
         
         //header에 있는 csrf 정보들 meta 태그에서 가져오기
         //xhr로 보내기 위해서는 나눠서 보내야함
         let token = $("meta[name='_csrf']").attr("content");
         let header = $("meta[name='_csrf_header']").attr("content");
		
         //xhr 설명
         //XMLHttpRequest : 서버와 상호작용하기 위한 객체
         //요청에 대한 정보를 가지고 있음
         //요청에 관한 상태값들을 가지고 있음.
         //ajax 통신시 xhr 객체를 이용함
		
		$("#saveReplyBtn").on("click",function(){
			
			//function(e) , e.preventDefault() 사용하지않고 버튼에 type="button" 줘도 괜찮음
			console.log("저장 버튼 클릭..");
			
			//id값 붙여주면 form이 필요하지 않음
			//let replyForm = $("#replyForm");
			//console.log(replyForm);
			
			//보낼 데이터 js 객체로 담아주기
			let replyVal = $("#reply").val();		//댓글 내용
			//let replyerVal = $("#replyer").val();	//댓글 작성자
			console.log("reply:"+replyVal);
			console.log("replyer:"+replyerVal);
			
			//객체 담아주기
			let reqData = {reply : replyVal, replyer : replyerVal, bno : bnoVal};
			console.log("requestData : "+reqData);
			console.log(reqData);
			
			$.ajax({
				type:"POST",
				url:"/reply/add",
				data : JSON.stringify(reqData),
				contentType : "application/json;charset=UTF-8",
				//0901 post 방식 시큐리티 위해 추가
				//요청을 날리기 전에.. 명령어 beforeSend
				beforeSend : function(xhr){
					xhr.setRequestHeader(header, token);
				},				
				success : function(result, status, xhr){
					console.log("ajax 요청 성공!");
					console.log(result);
					console.log(status);
					//xhr...?
							
					//작성 성공시 다시 보여주기 위해서
					showReplyList();
					
					//댓글 등록 후 input 공백으로 만들어주기
					$("#reply").val("");
					$("#replyer").val("");
				},
				error : function(e){
					console.log("ajax 요청 실패")
					console.log(e);
				}
				 
			});	//ajax
		});//btnclick
		
		//TODO 0901 구현
		//댓글 수정. 삭제
		//동적으로 생성된 버튼 요소에는 직접적으로 이벤트 등록이 안되서
		//html 문서가 미리 만들어져 있는 부모 태그에 이벤트 우회해서 걸기
		$(".replyBtn").on("click",function(){
			
		})
		//다음과 같은 방식으로 사용해야 함
		//on("click","button.replyBtn",function(){})
		//이벤트 걸어주는거는 container. 클릭이 되는건 button.replyBtn
		//replyBtn 눌리면 container의 이벤트 실행
		$(".reply_container").on("click","button.replyBtn",function(){
			//수정 누르면 modify, 삭제 누르면 delete 출력
			console.log($(this).data("service"));
			
			let service = $(this).data("service");
			if(service == 'modify'){
				//수정일때
				//수정가능한 폼으로 html 변경
				//reply_li 내용물 입력가능하게 변경
				let reply_li = $(this).parent().parent();
				console.log(reply_li);
				//TODO 0901 수정폼으로 태그 변경
				
				
			}else if(service == 'delete'){
				//삭제일때
				//ajax 로 삭제 요청
				let rno = $(this).data("rno");
				console.log("delete. rno : "+ rno);
				
				//TODO 0901 ajax 작성
				$.ajax({
					
				})
				
				
			}
		})
		
		
	})
	
	</script>


</div>
<script type="text/javascript">
	$(document).ready(function() {
		
		let btnForm = $("#btnForm");

		//수정버튼 클릭
		$("button[data-service='modify']").click(function() {
			btnForm.attr("action", "/board/modify").submit();
		})
		//목록으로 클릭
		$("button[data-service='list']").click(function() {
			btnForm.find("#bno").remove(); //bno 히든태그 삭제 (input자체를 삭제)
			btnForm.attr("action", "/board/list");
			btnForm.submit();
		})

	});
</script>



<%@ include file="../includes/footer.jsp"%>