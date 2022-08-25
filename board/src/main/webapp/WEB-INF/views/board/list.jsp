<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 헤더에 이미 정보가 있기에 페이지 정보들 날림 -->

<%@ include file="../includes/header.jsp"%>

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Board</h1>
<p class="mb-4">
	여기는 설명글인데요<a target="_blank" href="https://datatables.net">누르면
		이동해요!</a>.
</p>

<!-- DataTales Example -->
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary"
			style="line-height: 2rem">
			Board List
			<button id="writeBtn" type="button"
				class="btn btn-primary btn-small float-right">글 작성</button>

		</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"
				cellspacing="0">
				<thead>
					<tr>
						<th>#글 고유번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${list}">
						<tr>
							<td>${board.bno }</td>
							<td><a class="move" href="${board.bno }" target="_blank">${board.title }</a></td>
							<td>${board.writer }</td>
							<td><fmt:formatDate value="${board.reg }"
									pattern="YY/MM/dd HH:mm" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- 검색기능 추가 -->
			<!-- 검색박스 -->
			<div class="row">
				<div class="col-lg-6">
					<form id="searchForm" action="/board/list" method="get">
						<select name="sel" class="form-control">
							<option value="">------</option>
							<option value="T">제목</option>
							<option value="C">내용</option>
							<option value="W">작성자</option>
							<option value="TC">제목+내용</option>
							<option value="TW">제목+작성자</option>
							<option value="TCW">제목+내용+작성자</option>
						</select> <input type="text" name="keyword" class="form-control">
						<button class="btn btn-secondary">검색</button>

						<input type="hidden" name="pageNum" value="${pager.cri.pageNum }" />
						<input type="hidden" name="listQty" value="${pager.cri.listQty }" />

					</form>
				</div>
			</div>

			<!-- 버튼 추가 -->
			<!-- 페이지 번호 pagination -->
			<div class="row float-right">
				<ul class="pagination">
					<c:if test="${pager.prev }">
						<li class="page-item"><a class="page-link"
							href="${pager.startPage-1 }" tabindex="-1">앞으로..</a></li>
					</c:if>

					<c:forEach var="num" begin="${pager.startPage }"
						end="${pager.endPage }" step="1">
						<li class="page-item ${pager.cri.pageNum == num ? "active":""}">
							<a class="page-link" href="${num }">${num }</a>
						</li>
					</c:forEach>

					<c:if test="${pager.next }">
						<li class="page-item"><a class="page-link"
							href="${pager.endPage+1 }">뒤로..</a></li>
					</c:if>
				</ul>
			</div>

			<form id="pagingForm" action="/board/list">
				<input type="hidden" name="pageNum" value="${pager.cri.pageNum }" />
				<input type="hidden" name="listQty" value="${pager.cri.listQty }" />
				<input type="hidden" name="sel" value="${pager.cri.sel }" />
				<input
					type="hidden" name="keyword" value="${pager.cri.keyword }" />


			</form>

			<!-- modal 모달추가 -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="myModalLabel">Modal title</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">작성이 완료되었습니다.</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary">Save
								changes</button>
						</div>
					</div>
				</div>
			</div>
			<!-- end modal -->
		</div>
	</div>
	<!-- end cardbody -->
</div>
<!-- content 내용물 끝나는 부분 -->


<%@ include file="../includes/footer.jsp"%>

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						//만약 write에서 넘어오는경우 확인
						let result = "${result}";
						console.log(result); //정상작동

						//함수 모달 부르기
						modalCheck(result);

						function modalCheck(result) {
							if (result == '') {
								//result 가 null일 경우
								return;
							}
							if (parseInt(result > 0)) {
								//bno를 가져오기때문에 0보다 큼. 숫자로 체크해서 값이 있으면..
								$(".model-body").html(
										"게시글 " + parseInt(result)
												+ "번이 작성되었습니다.");
							}
							//없으면 이미 위에서 reutrn 으로 종료되었으므로 여기에작성
							//모달 띄우기
							$("#myModal").modal("show");

						}

						// 버튼 이벤트 등록
						// 글 작성 버튼 클릭시 글 등록 페이지로 이동
						$("#writeBtn").click(function() {
							self.location = "/board/write";
						});

						//페이지 번호 누르면 이동하는 처리

						let pagingForm = $("#pagingForm"); //숨김 폼태그 가져오기

						//pagination 클래스이며 a태그인 것들
						$(".pagination a").on(
								"click",
								function(e) {
									e.preventDefault(); //a 태그의 기본 이동 기능 취소

									console.log("click....");
									
									//move 클릭시 board/read 로 변경이 되는데
									// 뒤로가기 선택시 바뀌지 않음(board/read 로 남아있음)
									pagingForm.attr("action","/board/list");

									//form 태그의 pageNum value 속성값을
									//이벤트 발생시킨 a 태그의 href 속성값으로 변경
									pagingForm.find("input[name='pageNum']")
											.val($(this).attr("href"));
									pagingForm.submit(); //숨김 폼태그 submit 시키기
								})

						//제목 클릭시 read로 넘어가는 처리
						$(".move")
								.click(
										function(e) {
											e.preventDefault();
											console.log("move...");

											//pagingForm재활용하기
											// hidden input tag로 bno 추가하기(태그 동적 생성)
											//append는 arraylist 뒤에 붙는 add 같은 개념
											pagingForm
													.append("<input type='hidden' name='bno' value='"
															+ $(this).attr(
																	"href")
															+ "'/>");
											// pagingForm의 action속성값(board/list)에서 (board/read)로 변경하기
											pagingForm.attr("action",
													"/board/read");
											// 후 submit()
											pagingForm.submit();
										});

						//검색 폼 처리
						let searchForm = $("#searchForm");
						$("#searchForm button").on(
								"click",
								function(e) {
									if (!searchForm.find("option:selected")
											.val()) {
										
										//console.log("3333");
										
										//검색 옵션중에 ----- 를 선택한경우 실행
										alert("검색종류를 선택하세요!");
										//return false --> 버튼을 눌렀을때 submit 이동 막으면서 함수 종료
										return false;
									}
									if (!searchForm.find(
											"input[name='keyword']").val()) {
										//검색내용이 없을 경우 실행
										alert("검색 내용을 입력하세요!");
										return false;
									}
									e.preventDefault();

									//검색 진행할시 남아있는페이지가 아닌 1페이지로 바꾸기
									searchForm.find("input[name='pageNum']")
											.val("1");

									searchForm.submit();
								})

					});
</script>
