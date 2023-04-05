package com.myspring.cookpro.recipeboard.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.cookpro.member.dto.MemberDTO;
import com.myspring.cookpro.recipeboard.dto.ImageDTO;

@Controller
public class RecipeControllerImpl implements RecipeController{
	
	private static final String CURR_IMAGE_REPO_PATH = "C:\\Users\\tmdwn\\git\\Cooking-Recipe\\cookpro\\recipe_imageFile";

	@Override
	@RequestMapping("/recipeboard/recipeList.do")
	public ModelAndView listRecipe(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName= request.getParameter("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/recipeboard/*Form.do", method=RequestMethod.GET)
	public ModelAndView r_form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String viewName= request.getParameter("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/recipeboard/recipeView.do", method=RequestMethod.GET)
	public ModelAndView viewRecipe(int recipeNo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName= request.getParameter("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/recipeboard/addNewRecipe.do")
	public ResponseEntity addNewRecipe(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> recipeMap = new HashMap<String, Object>();
		Enumeration<String> enu = multipartRequest.getParameterNames();
		
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = multipartRequest.getParameter(name);
			recipeMap.put(name, value);
		}
		
		List<String> fileList = upload(multipartRequest);
		List<ImageDTO> imageFileList= new ArrayList<ImageDTO>();
		if(fileList != null && fileList.size() != 0) {
			for(String fileName : fileList) {
				ImageDTO image = new ImageDTO();
				image.setImageFileName(fileName);
				imageFileList.add(image);
			}
			recipeMap.put("imageFileList", imageFileList);
		}
		
		HttpSession session = multipartRequest.getSession();
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		
		String id = member.getId();
		recipeMap.put("parentNo", 0);
		recipeMap.put("id",id);
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.add("Content-Type", "text/html;charset=utf-8");
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resEnt;
	}
	
	
	
	private List<String> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		List<String> fileList = new ArrayList<String>();
		Map<String, String> recipeMap = new HashMap<String, String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			fileList.add(originalFileName);
			File file = new File(CURR_IMAGE_REPO_PATH+ "\\" + fileName);
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
	
}
