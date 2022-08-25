<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">BoardModifyPage</h6>
	</div>
	<div class="card-body">
		<form id="modifyForm" action="/board/modify" method="post">
			<div class="mr-3 ml-3 mb-4">
				<div class="form-group row">
					<label>Bno</label> <input type="text" class="form-control"
						name="bno" readonly="readonly" value="${board.bno }" />
				</div>
				<div class="form-group row">
					<label>Title</label> <input type="text" class="form-control"
						name="title" value="${board.title }" />
				</div>
				<div class="form-group row">
					<label>Content</label>
					<textarea class="form-control" rows="3" name="content">${board.content }</textarea>
				</div>
				<div class="form-group row">
					<label>Writer</label> <input type="text" class="form-control"
						name="writer" readonly="readonly" value="${board.writer }" />
				</div>
				<div class="form-group row">
					<label>Time</label> <input type="text" class="form-control"
						 readonly="readonly"
						value='<fmt:formatDate pattern="YY-MM-dd HH:MM" value="${board.reg }"/>' />
				</div>
			</div>
			<div class="mr-1 ml-2 mb-4">
				<button type="submit" data-service="delete" class="btn btn-danger">삭제하기</button>
				<button type="submit" data-service="modify" class="btn btn-warning">수정확인</button>
				<button type="submit" data-service="list" class="btn btn-info">돌아가기</button>
			</div>
			<!-- 이동시 들고갈 pageNum listQty 값 두개 히든으로 추가 -->
			<input type="hidden" name="pageNum" value="${cri.pageNum }"/>
			<input type="hidden" name="listQty" value="${cri.listQty }"/>
			<input type="hidden" name="sel" value="${cri.sel }"/>
			<input type="hidden" name="keyword" value="${cri.keyword }"/>
			
		</form>
	</div>
</div>

<!-- 버튼에 따른 이벤트 처리 스크립트 추가 -->
<script >
//헤더 하단부분에 제이쿼리 라이브러리 삽입되어있어서 사용할 수 있음

$(document).ready(function(){
	let formObject = $("#modifyForm");	//form 태그 가져오기
	
	$("button").on("click",function(e){	//e는 event
		e.preventDefault();		//기본 동작 취소(submit의 이동 기능 취소)
		//console.log(e);
		let service_data = $(this).data("service");
		//console.log(service_data); list, delete, modify
		
		if(service_data == 'delete'){
			formObject.attr("action","/board/delete");
			console.log("DELETE!!!");
		}else if(service_data == 'list'){
			console.log("LIST!!!");
			// 방법1
			//formObject.attr("action","/board/list");
			// 방법2
			//self.location="/board/list";
			//return;
			
			//0824 수정
			//폼 태그의 action과 method 속성을 변경
			formObject.attr("action","/board/list").attr("method","get");
			
			//list로 돌아갈때 다른 데이터들(form태그안에 있는 여러 데이터(BoardVO))은 필요하지 않기에
			//pageNum과 listQty 히든태그를 복사해놓고
			//내용물 전부 삭제하고 복사해둔 pageNum과 listQty 태그만 다시 추가해서
			//이동시킴(form submit 으로 이동)
			let pageNumTag = $("input[name='pageNum']").clone();
			let listQTyTag = $("input[name='listQTy']").clone();
			let selTag = $("input[name='sel']").clone();
			let keywordTag = $("input[name='keyword']").clone();
			
			formObject.empty();
			
			formObject.append(pageNumTag);
			formObject.append(listQTyTag);
			formObject.append(selTag);
			formObject.append(keywordTag);
		}
		
		//js로 폼의 submit 버튼 누른 효과와 동일
		formObject.submit();
	});
})
</script>

<%@ include file="../includes/footer.jsp"%>
