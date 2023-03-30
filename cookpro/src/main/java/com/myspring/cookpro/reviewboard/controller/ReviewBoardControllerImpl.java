package com.myspring.cookpro.reviewboard.controller;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.myspring.cookpro.reviewboard.dto.review_article_dto;
import com.myspring.cookpro.member.dto.MemberDTO;
import com.myspring.cookpro.reviewboard.service.ReviewService;
import com.myspring.cookpro.reviewboard.dto.review_image_dto;

@Controller
public class ReviewBoardControllerImpl implements ReviewBoardController{

	@Autowired
	ReviewService reviewService;
	@Autowired
	review_article_dto review_article_dto;
	
	@Override
	@RequestMapping("/reviewBoard/reviewList.do")
	public ModelAndView review_listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String viewName = request.getParameter("viewName");
		List<review_article_dto> articleList = reviewService.review_listArticles();
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articleList",articleList);
		return mav;
	}

	@Override
	public ResponseEntity review_addNewArticle(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView review_form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView review_viewArticle(int articleNo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity review_modArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity review_removeArticle(int articleNo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
=======
public class ReviewBoardControllerImpl {
>>>>>>> branch 'master' of https://github.com/jxl1089/Cooking-Recipe-java.git

}
