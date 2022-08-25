<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">BoardReadPage</h6>
	</div>
	<div class="card-body">
		<div class="mr-3 ml-3 mb-4">
			<div class="form-group row">
				<label>Bno</label> <input type="text" class="form-control" name="bno"
					readonly="readonly" value="${board.bno }" />
			</div>
			<div class="form-group row">
				<label>Title</label> <input type="text" class="form-control" name="title"
					readonly="readonly" value="${board.title }" />
			</div>
			<div class="form-group row">
				<label>Content</label>
				<textarea class="form-control" rows="3" readonly="readonly" name="content">${board.content }</textarea>
			</div>
			<div class="form-group row">
				<label>Writer</label> <input type="text" class="form-control" name="writer"
					readonly="readonly" value="${board.writer }" />
			</div>
			<div class="form-group row">
				<label>Time</label> <input type="text" class="form-control" name="reg"
					readonly="readonly" value="${board.reg }" />
			</div>
		</div>
		<div class="mr-1 ml-2 mb-4">
			<button class="btn btn-warning" data-service="modify">수정하기</button>
			<button class="btn btn-info" data-service="list">돌아가기</button>
		</div>
		
		<form id="btnForm" action="/board/modify" method="get">
		<input type="hidden" name="bno" value="${board.bno }" id="bno"/>
		<input type="hidden" name="pageNum" value="${cri.pageNum }"/>
		<input type="hidden" name="listQty" value="${cri.listQty }"/>
		<input type="hidden" name="sel" value="${cri.sel }"/>
		<input type="hidden" name="keyword" value="${cri.keyword }"/>
		
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	let btnForm = $("#btnForm");
	
	//수정버튼 클릭
	$("button[data-service='modify']").click(function(){
		btnForm.attr("action","/board/modify").submit();
	})
	//목록으로 클릭
	$("button[data-service='list']").click(function(){
		btnForm.find("#bno").remove();	//bno 히든태그 삭제 (input자체를 삭제)
		btnForm.attr("action","/board/list");
		btnForm.submit();
	})
	
});
</script>



<%@ include file="../includes/footer.jsp"%>
