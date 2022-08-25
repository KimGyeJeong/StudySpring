package com.board.controller;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/upload/*")
@Log4j
public class UploadController {

	@GetMapping("uploadForm")
	public void upload() {
		log.info("upload.....FORM!");
	}

	@PostMapping("uploadPro")
	public void uploadPro(@RequestParam("msg") String msg, MultipartHttpServletRequest request) {
		log.info(msg);
		// log.info(request.getContentType());
		// multipart/form-data; boundary=----WebKitFormBoundaryfJlGTS8KLWjfJnvV

		// log.info(request.getFile("img")); //파일 정보 꺼내기
		// org.springframework.web.multipart.commons.CommonsMultipartFile@13848b7

		try {
			// 전송한 파일 정보 꺼내기
			MultipartFile mf = request.getFile("img");
			log.info(mf);
			log.info("이름 : " + mf.getName());
			log.info("원본이름 : " + mf.getOriginalFilename());
			log.info("Size : " + mf.getSize());
			log.info("type : " + mf.getContentType());

			// 파일 저장 경로
			// resources 폴더 안 save 폴더
			String path = request.getRealPath("/resources/save"); // 서버상 save 폴더 위치
			log.info("PATH : " + path);

			// 새 파일명 생성
			String uuid = UUID.randomUUID().toString();
			log.info("uuid : " + uuid);
			String uuid_ = UUID.randomUUID().toString().replaceAll("-", "");
			log.info("uuid_ : " + uuid_);
			String uuid_upper = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			log.info("uuid_upper : " + uuid_upper);
			// 번호가 다르것이 맞음. 다 새로 생성중이기 때문

			// 업로드한 파일 확장자만 가져오기
			String orgName = mf.getOriginalFilename();
			log.info("orgName : " + orgName);
			// 파일명은 .jpeg 처럼 나오기때문에 뒤에서부터 .를 찾아서 그 이후 문자열 저장(.jpeg 저장)
			String ext = orgName.substring(orgName.lastIndexOf("."));
			log.info("ext : " + ext);

			// 저장할 파일명
			String newFileName = uuid + ext;
			log.info("newFileName : " + newFileName);

			// 저장할 파일 전체 경로
			String imgPath = path + "\\" + newFileName;
			log.info("imgPath : " + imgPath);

			// 파일 저장
			File copyFile = new File(imgPath);

			// 예외처리 발생
			mf.transferTo(copyFile);
			
			// 경로
			// C:\Users\pmwkd\Desktop\FrameWork\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\board\resources\save
			//에 파일이름
			//newFileName : 7a028217-d780-4458-8757-d7ae18e159d4.jpg
			//으로 저장됨

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//다운로드 요청 받는 페이지
	@GetMapping("helloDown")
	public void helloDown() {
		
	}
	
	//다운 요청 처리
	//리턴을 모델-뷰로 리턴해줌.
	@GetMapping("download")
	public ModelAndView download(int fileNum) {
		//다운시킬 파일 준비
		File f= null;	
		if(fileNum == 1) {
			f = new File("C:\\Users\\pmwkd\\Desktop\\img\\고양이9.jpg");
		}else if(fileNum==2) {
			f = new File("C:\\Users\\pmwkd\\Desktop\\img\\고양이8.jpg");
		}
		
		//생성자 매개변수
		//String viewName --> 보통 view이름이 들어감. 하지만 다운로드에서는 xml 지정한 DownloadView 빈의 id값을 줌
		//String modelName --> 파라미터명 지정 다운시킬 파일 이름?
		//Object modelObject --> 데이터 보내줌. (key - value)형식. (modelName - modelObject).. 다운시킬 파일
		
		//fileDown (루트에서)빈으로 등록한 자바 클래스
		ModelAndView mv = new ModelAndView("fileDown", "downloadFile", f);
		//호출한것...
		//	public ModelAndView(String viewName, String modelName, Object modelObject) {
		//this.view = viewName;
		//addObject(modelName, modelObject);
		//}
		
		
		return mv;
	}
	

}
