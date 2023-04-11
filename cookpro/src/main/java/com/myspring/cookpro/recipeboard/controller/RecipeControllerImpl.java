package com.myspring.cookpro.recipeboard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.myspring.cookpro.member.dto.MemberDTO;
import com.myspring.cookpro.recipeboard.dto.ImageDTO;
import com.myspring.cookpro.recipeboard.dto.RecipeDTO;
import com.myspring.cookpro.recipeboard.service.RecipeService;


@Controller
public class RecipeControllerImpl implements RecipeController{
	
	private static final String CURR_IMAGE_REPO_PATH = "C:\\Users\\tmdwn\\git\\Cooking-Recipe\\cookpro\\recipe_imageFile";
	
	@Autowired
	RecipeService recipeService;

	@Override
	@RequestMapping("/recipeboard/recipeList.do")
	public ModelAndView listRecipe(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName= request.getParameter("viewName");
		List<RecipeDTO> recipesList = recipeService.recipesList();
		
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
	@RequestMapping(value="/recipeboard/addRecipe.do")
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
	
	
	


	@Override
	@RequestMapping(value="/recipeboard/removeRecipe", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removceArticle(int recipe_no, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Contetn-Type", "text/html; charset=utf-8");
		try {
				
			File destDir = new File(CURR_IMAGE_REPO_PATH + "\\" + recipe_no);
			FileUtils.deleteDirectory(destDir);
			
			message = "<script>";
			message += "alert('삭제가 완료 되었습니다.');";
			message += "location.href='"+request.getContextPath()+"/board/listArticles.do';";
			message += "</script>";
			
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			message = "<script>";
			message += "alert('삭제에 실패 하었습니다. 다시 시도해 주세요.');";
			message += "location.href='"+request.getContextPath()+"/board/listArticles.do';";
			message += "</script>";
			
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
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

//	@Override
//	@RequestMapping(value="/recipeboard/imageUpload.do", method=RequestMethod.POST)
//	public void imageUpload(MultipartHttpServletRequest multipartRequest, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		// TODO Auto-generated method stub
//		request.setCharacterEncoding("utf-8");
//		
//		JsonObject json = new JsonObject();
//		PrintWriter printWriter = null;
//		OutputStream out = null;
//		MultipartFile file = multipartRequest.getFile("upload");
//		if(file != null) {
//			if(file.getSize() > 0 && !StringUtils.isEmpty(file.getName())) {
//				if(file.getContentType().toLowerCase().startsWith("image/")) {
//					try {
//						String fileName = file.getName();
//						byte[] bytes;
//						bytes = file.getBytes();
//						String uploadPath = "C:\\Users\\tmdwn\\git\\Cooking-Recipe\\cookpro\\src\\main\\webapp\\resources\\image\\testimage";
//						File uploadFile = new File(uploadPath);
//						if (!uploadFile.exists()) {
//							uploadFile.mkdirs();
//						}
//						fileName = UUID.randomUUID().toString();
//						uploadPath = uploadPath + "/" + fileName;
//						out = new FileOutputStream(new File(uploadPath));
//						out.write(bytes);
//						printWriter = response.getWriter();
//						response.setContentType("text/html");
//						
//						String callback = request.getParameter("CKEditorFuncNum");
//				    	printWriter = response.getWriter();
//						
//						String fileUrl = request.getContextPath() + "/resources/image/testimage/" + fileName;
//						
//						json.addProperty("uploaded", 1);
//						json.addProperty("fileName", fileName);
//						json.addProperty("url", fileUrl);
//
//						printWriter.println(json);
//						
//					} catch (Exception e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}  finally {
//						if(out !=null) {
//							out.close();
//						}
//						if(printWriter != null) {
//							printWriter.close();
//						}
//					}
//				}
//			}
//		}
//		
//
//	}
	
}
