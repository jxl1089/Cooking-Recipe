package com.myspring.cookpro.review.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.cookpro.member.dto.MemberDTO;
import com.myspring.cookpro.review.dto.ReviewDTO;
import com.myspring.cookpro.review.service.ReviewService;

@Controller
public class ReviewControllerImpl implements ReviewController{
	
	@Autowired
	ReviewService reviewService;
	@Autowired
	ReviewDTO reviewDTO;

	@Override
	@RequestMapping("/review/reviewList.do")
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String viewName = (String) request.getAttribute("viewName");
		List<ReviewDTO> articlesList = reviewService.listArticles();

		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);

		return mav;
	}

	@Override
	@RequestMapping(value="/review/addNewArticle.do", method=RequestMethod.POST)
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration<String> enu = multipartRequest.getParameterNames();
		
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}
		
		/*
		 * List<String> fileList = upload(multipartRequest); List<ImageDTO>
		 * imageFileList = new ArrayList<ImageDTO>();
		 * 
		 * if(fileList != null && fileList.size() != 0) { for(String fileName :
		 * fileList) { ImageDTO image = new ImageDTO();
		 * image.setImageFileName(fileName); imageFileList.add(image); }
		 * articleMap.put("imageFileList", imageFileList); }
		 */
		HttpSession session = multipartRequest.getSession();
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		
		String id = member.getId();
		articleMap.put("parentNo", 0);
		articleMap.put("id", id);
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");

		try {
			int articleNo = reviewService.addNewArticle(articleMap);
			
			/*
			 * if(imageFileList != null && imageFileList.size() != 0) { for(ImageDTO
			 * imageDTO : imageFileList) { File srcFile = new File(CURR_IMAGE_REPO_PATH+
			 * "\\temp\\" + imageDTO.getImageFileName()); File destDir = new
			 * File(CURR_IMAGE_REPO_PATH+"\\"+articleNo);
			 * FileUtils.moveFileToDirectory(srcFile, destDir, true); } }
			 */

			message = "<script>";
			message += "alert('새글을 추가했습니다.');";
			message += "location.href='" + multipartRequest.getContextPath()
				+"/review/reviewList.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			/*
			 * if(imageFileList != null && imageFileList.size() != 0) { for(ImageDTO
			 * imageDTO : imageFileList) { File srcFile = new
			 * File(CURR_IMAGE_REPO_PATH+"\\temp\\"+imageDTO.getImageFileName());
			 * srcFile.delete(); } }
			 */
			
			message = "<script>";
			message += "alert('오류가 발생했습니다. 다시 시도해 주세요.');";
			message += "location.href='" + multipartRequest.getContextPath()
				+"/review/reviewForm.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	@Override
	@RequestMapping(value="/review/*Form.do", method=RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String viewName = (String) request.getAttribute("viewName");
		
		ModelAndView mav = new ModelAndView(viewName);
		
		return mav;
	}

	/*
	 * private List<String> upload(MultipartHttpServletRequest multipartRequest)
	 * throws Exception{ List<String> fileList = new ArrayList<String>();
	 * 
	 * Map<String, String> articleMap = new HashMap<String, String>();
	 * Iterator<String> fileNames = multipartRequest.getFileNames();
	 * 
	 * while(fileNames.hasNext()) { String fileName = fileNames.next();
	 * MultipartFile mFile = multipartRequest.getFile(fileName);
	 * 
	 * String originalFileName = mFile.getOriginalFilename();
	 * fileList.add(originalFileName);
	 * 
	 * File file = new File(CURR_IMAGE_REPO_PATH + "\\" + fileName);
	 * 
	 * if(mFile.getSize() != 0) { if(!file.exists()) {
	 * if(file.getParentFile().mkdirs()) { file.createNewFile(); } }
	 * mFile.transferTo(new File(CURR_IMAGE_REPO_PATH + "\\temp\\" +
	 * originalFileName)); } } return fileList; }
	 */
	
	@Override
	@RequestMapping(value="/review/viewArticle.do", method=RequestMethod.GET)
	public ModelAndView viewArticle(int articleNo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String viewName = (String) request.getAttribute("viewName");
		Map articleMap = reviewService.viewArticle(articleNo);
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articleMap", articleMap);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/review/modArticle.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		
		String articleNo = multipartRequest.getParameter("articleNo");
		articleMap.put("articleNo", articleNo);
		
		String content = multipartRequest.getParameter("content");
		articleMap.put("content", content);
		
		String title = multipartRequest.getParameter("title");
		articleMap.put("title", title);
		
		/* List<String> fileList = upload(multipartRequest); */
		HttpSession session = multipartRequest.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		String id = member.getId();
		articleMap.put("id", id);
		
		/*
		 * List<ImageDTO> imageFileList = new ArrayList<ImageDTO>();
		 * 
		 * if(fileList != null && fileList.size() != 0) { for(String fileName :
		 * fileList) { ImageDTO image = new ImageDTO();
		 * image.setImageFileName(fileName); imageFileList.add(image); }
		 * articleMap.put("imageFileList", imageFileList); }
		 */

		String message = null;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		
		try {
			reviewService.modArticle(articleMap);
			
			/*
			 * if(imageFileList != null && imageFileList.size() != 0) { for(ImageDTO
			 * imageDTO : imageFileList) { File srcFile = new File(CURR_IMAGE_REPO_PATH +
			 * "\\temp\\" + imageDTO.getImageFileName()); File destDir = new
			 * File(CURR_IMAGE_REPO_PATH + "\\" + articleNo);
			 * FileUtils.moveFileToDirectory(srcFile, destDir, true);
			 * 
			 * String originalFileName = (String) articleMap.get("originalFileName"); File
			 * oldFile = new File(CURR_IMAGE_REPO_PATH + "\\" + articleNo + "\\" +
			 * originalFileName);
			 * 
			 * oldFile.delete(); } }
			 */
			
			message = "<script>";
			message += "alert('글이 수정되었습니다.');";
			message += "location.href='"+multipartRequest.getContextPath()+"/review/viewArticle.do?articleNo="+articleNo+"';";
			message += "</script>";
			
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			/*
			 * if(imageFileList != null && imageFileList.size() != 0) { for(ImageDTO
			 * imageDTO : imageFileList) { File srcFile = new
			 * File(CURR_IMAGE_REPO_PATH+"\\temp\\"+imageDTO.getImageFileName());
			 * srcFile.delete(); } }
			 */
			
			message = "<script>";
			message += "alert('글 수정 중 에러가 발생했습니다. 다시 시도하세요.');";
			message += "location.href='"+multipartRequest.getContextPath()+"/review/viewArticle.do?articleNo="+articleNo+"';";
			message += "</script>";
			
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
			e.printStackTrace();
		}
		return resEnt;
	}
	
	@Override
	@RequestMapping(value="/review/removeArticle.do", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity removeArticle(int articleNo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			reviewService.removeArticle(articleNo);
			/*
			 * File destDir = new File(CURR_IMAGE_REPO_PATH + "\\" + articleNo);
			 * FileUtils.deleteDirectory(destDir);
			 */
			
			message = "<script>";
			message += "alert('삭제가 완료 되었습니다.');";
			message += "location.href='"+request.getContextPath()+"/review/reviewList.do';";
			message += "</script>";
			
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += "alert('삭제에 실패 하었습니다. 다시 시도해 주세요.');";
			message += "location.href='"+request.getContextPath()+"/review/reviewList.do';";
			message += "</script>";
			
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	@RequestMapping(value="/review/addReply.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity replyArticle(@RequestParam("parentNo") int parentNo,
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {

		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration<String> enu = multipartRequest.getParameterNames();
		
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}
		
		/*
		 * List<String> fileList = upload(multipartRequest); List<ImageDTO>
		 * imageFileList = new ArrayList<ImageDTO>();
		 * 
		 * if(fileList != null && fileList.size() != 0) { for(String fileName :
		 * fileList) { ImageDTO image = new ImageDTO();
		 * image.setImageFileName(fileName); imageFileList.add(image); }
		 * articleMap.put("imageFileList", imageFileList); }
		 */
		HttpSession session = multipartRequest.getSession();
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		
		String id = member.getId();
		articleMap.put("parentNo", parentNo);
		articleMap.put("id", id);
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");

		try {
			int articleNo = reviewService.addNewArticle(articleMap);
			
			/*
			 * if(imageFileList != null && imageFileList.size() != 0) { for(ImageDTO
			 * imageDTO : imageFileList) { File srcFile = new File(CURR_IMAGE_REPO_PATH+
			 * "\\temp\\" + imageDTO.getImageFileName()); File destDir = new
			 * File(CURR_IMAGE_REPO_PATH+"\\"+articleNo);
			 * FileUtils.moveFileToDirectory(srcFile, destDir, true); } }
			 */

			message = "<script>";
			message += "alert('답글을 추가했습니다.');";
			message += "location.href='" + multipartRequest.getContextPath()
				+"/review/reviewList.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			/*
			 * if(imageFileList != null && imageFileList.size() != 0) { for(ImageDTO
			 * imageDTO : imageFileList) { File srcFile = new
			 * File(CURR_IMAGE_REPO_PATH+"\\temp\\"+imageDTO.getImageFileName());
			 * srcFile.delete(); } }
			 */
			
			message = "<script>";
			message += "alert('오류가 발생했습니다. 다시 시도해 주세요.');";
			message += "location.href='" + multipartRequest.getContextPath()
				+"/review/reviewList.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

}
