<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="../includes/header.jsp"%>

<!-- 작성 -->

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">BoardWritePage</h6>
	</div>
	<div class="card-body">
		<form id="writeForm" action="/board/write" method="post">
			<div class="mr-3 ml-3 mb-4">
				<div class="form-group row">
					<label>Title</label> <input type="text" class="form-control"
						name="title"  />
				</div>
				<div class="form-group row">
					<label>Content</label>
					<textarea class="form-control" rows="3" name="content"></textarea>
				</div>
				<div class="form-group row">
					<label>Writer</label> <input type="text" class="form-control"
						name="writer"   />
				</div>
			</div>
			<div class="mr-1 ml-2 mb-4">
				<button type="submit" data-service="write" class="btn btn-primary">작성하기</button>
				<button type="submit" data-service="list" class="btn btn-info">돌아가기</button>
			</div>
		</form>
	</div>
</div>

<script >
//헤더 하단부분에 제이쿼리 라이브러리 삽입되어있어서 사용할 수 있음

$(document).ready(function(){
	let formObject = $("#writeForm");	//form 태그 가져오기
	
	$("button").on("click",function(e){	//e는 event
		e.preventDefault();		//기본 동작 취소(submit의 이동 기능 취소)
		//console.log(e);
		let service_data = $(this).data("service");
		//console.log(service_data); list, delete, modify
		
		if(service_data == 'list'){
			formObject.attr("action","/board/list");
		}
		
		//js로 폼의 submit 버튼 누른 효과와 동일
		formObject.submit();
	});
})
</script>

<!-- footer -->
<%@ include file="../includes/footer.jsp"%>