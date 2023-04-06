package com.myspring.cookpro.reviewboard.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.cookpro.reviewboard.dto.Review_article_dto;
import com.myspring.cookpro.member.dto.MemberDTO;
import com.myspring.cookpro.reviewboard.service.ReviewService;
import com.myspring.cookpro.reviewboard.dto.Review_image_dto;

@Controller
public class ReviewBoardControllerImpl implements ReviewBoardController{
	private static final String CURR_IMAGE_REPO_PATH = ""; // 나중에 채워야함 경로
	@Autowired
	ReviewService reviewService;
	@Autowired
	Review_article_dto review_article_dto;
	
	@Override
	@RequestMapping("/reviewBoard/review_listArticles.do")
	public ModelAndView review_listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String viewName = request.getParameter("viewName");
		List<Review_article_dto> articleList = reviewService.review_listArticles();
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articleList",articleList);
		return mav;
	}

	@Override
	@RequestMapping(value="/reveiwBoard/review_addNewArticles.do",method=RequestMethod.POST)
	public ResponseEntity review_addNewArticle(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration<String> enu = multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}
		
		List<String> fileList = review_upload(multipartRequest);
		List<Review_image_dto> imageFileList = new ArrayList<Review_image_dto>();
		if(fileList != null && fileList.size() != 0) {
			for(String fileName : fileList) {
				Review_image_dto image = new Review_image_dto();
				image.setImageFileName(fileName);
				imageFileList.add(image);
			}
			articleMap.put("imageFileList", imageFileList);
		}
		
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
			int articleNo = reviewService.review_addNewArticle(articleMap);
			if(imageFileList != null && imageFileList.size() != 0) {
				for(Review_image_dto imageDTO : imageFileList) {
					File srcFile = new File(CURR_IMAGE_REPO_PATH+ "\\temp\\" 
							+ imageDTO.getImageFileName());
					File destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+articleNo);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
			}
			message = "<script>";
			message += "alert('새글을 추가했습니다.');";
			message += "location.href='" + multipartRequest.getContextPath()
				+"/board/listArticles.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			if(imageFileList != null && imageFileList.size() != 0) {
				for(Review_image_dto Review_image_dto : imageFileList) {
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\temp\\"
							+Review_image_dto.getImageFileName());
					srcFile.delete();
				}
			}
			message = "<script>";
			message += "alert('오류가 발생했습니다. 다시 시도해 주세요.');";
			message += "location.href='" + multipartRequest.getContextPath()
				+"/board/articleForm.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	private List<String> review_upload(MultipartHttpServletRequest multipartRequest) throws IOException {
		List<String> fileList = new ArrayList<String>();
		Map<String, String> articleMap = new HashMap<String, String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			fileList.add(originalFileName);
			File file = new File(CURR_IMAGE_REPO_PATH+"\\"+fileName);
			if(mFile.getSize()!=0) {
				if(!file.exists()) {
					if(file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH+"\\temp\\"+originalFileName));
			}
		}
		return fileList;
	}

	@Override
	@RequestMapping(value="/reviewBoard/*review_Form.do", method=RequestMethod.GET)
	public ModelAndView review_form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/review_board/review_viewArticle.do", method=RequestMethod.GET)
	public ModelAndView review_viewArticle(int articleNo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String viewName = (String) request.getAttribute("viewName");
		Map articleMap = reviewService.review_viewArticle(articleNo);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articleMap", articleMap);
		return mav;
	}

	@Override
	@RequestMapping(value="/reviewBoard/review_modArticle.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity review_modArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		
		String articleNo = multipartRequest.getParameter("articleNo");
		articleMap.put("articleNo", articleNo);
		
		System.out.println("articleNo : " + articleNo);
		String content = multipartRequest.getParameter("content");
		articleMap.put("content",content);
		
		String title = multipartRequest.getParameter("title");
		articleMap.put("title", title);
		
		List<String> fileList = review_upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberDTO member = (MemberDTO)session.getAttribute("member");

		
		String id = member.getId();
		articleMap.put("id", id);
		List<Review_image_dto> imageFileList = new ArrayList<Review_image_dto>();
		if(fileList != null && fileList.size() != 0) {
			for(String fileName : fileList) {
				Review_image_dto image = new Review_image_dto();
				image.setImageFileName(fileName);
				imageFileList.add(image);
			}
			articleMap.put("imageFileList", imageFileList);
		}
		 
		String message = null;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			reviewService.review_modArticle(articleMap);
			if(imageFileList != null && imageFileList.size() != 0) {
				for(Review_image_dto imageDTO : imageFileList) {
					File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\temp\\" + imageDTO.getImageFileName());
					File destDir = new File(CURR_IMAGE_REPO_PATH + "\\" + articleNo);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					
					String originalFileName = (String)articleMap.get("originalFileName");
					File oldFile = new File(CURR_IMAGE_REPO_PATH + "\\" + articleNo + "\\" + originalFileName);
					oldFile.delete();
				}
			}
			message = "<script>";
			message += "alert('글이 수정 되었습니다.');";
			message += "location.href='"+multipartRequest.getContextPath()
				+"/board/viewArticle.do?articleNo="+articleNo+"';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			if(imageFileList != null && imageFileList.size() != 0) {
				for(Review_image_dto imageDTO : imageFileList) {
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\temp\\"
							+imageDTO.getImageFileName());
					srcFile.delete();
				}
			}
			message = "<script>";
			message += "alert('글 수정 중 에러가 발생했습니다. 다시 시도 하세요.');";
			message += "location.href='"+multipartRequest.getContextPath()
				+"/board/viewArticle.do?articleNo="+articleNo+"';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	@Override
	@RequestMapping(value="/reviewBoard/review_removeArticle", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity review_removeArticle(int articleNo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			reviewService.review_removeArticle(articleNo);
			File destDir = new File(CURR_IMAGE_REPO_PATH + "\\" + articleNo);
			FileUtils.deleteDirectory(destDir);
			
			message = "<script>";
			message += "alert('삭제가 완료 되었습니다.');";
			message += "location.href='"+request.getContextPath()+"/board/listArticles.do';";
			message += "</script>";
			
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += "alert('삭제에 실패 하었습니다. 다시 시도해 주세요.');";
			message += "location.href='"+request.getContextPath()+"/board/listArticles.do';";
			message += "</script>";
			
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
}


