package com.friendship.manage.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendship.manage.service.IUserMovieService;
import com.java.po.UserMovie;
import com.java.util.ActionBase;
import com.java.util.DateBase;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

@Controller
@RequestMapping(value="/userMovie")
@Scope
public class UserMovieControl extends ActionBase{
	private IUserMovieService userMovieService;
	@Resource(name="userMovieService")
	public void setUserMovieService(IUserMovieService userMovieService) {
		this.userMovieService = userMovieService;
	}
	
	/**
	 * 后台主页提供数据
	 * @throws Exception
	 */
	@RequestMapping(value="getUserMovieList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getUserMovieList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		writeString(response, userMovieService.getUserMovieList(pagingData));
	}
	
	/**
	 * 电影评论
	 * @throws Exception
	 */
	@RequestMapping(value="addUserMovie.htm",method=RequestMethod.POST)
	@ResponseBody
	@UserVerifyAnnotation
	public void addUserMovie(HttpServletResponse response, String usersessionkey, String movieName)throws Exception{
		UserMovie entity = new UserMovie();
		entity.setCreateTime(DateBase.getDateYMD());
		entity.setUserId(getUserId(usersessionkey));
		super.writeString(response, userMovieService.add(entity, movieName));
	}
	
	/**
	 * 感悟
	 * @throws Exception
	 */
	@RequestMapping(value="updateUserMovieF.htm",method=RequestMethod.POST)
	@ResponseBody
	@UserVerifyAnnotation
	public void updateUserMovieF(HttpServletResponse response, String usersessionkey, String movieName,  String movieGnosis)throws Exception{
		UserMovie entity = new UserMovie();
		entity.setUserId(getUserId(usersessionkey));
		entity.setMovieGnosis(movieGnosis);
		super.writeString(response, userMovieService.updateGnosis(entity, movieName));
	}
	
	/**
	 * 前台显示感悟
	 * @throws Exception
	 */
	@RequestMapping(value="getUserMovieListF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void getUserMovieListF(HttpServletResponse response, Integer page, Integer item, String movieName)throws Exception{
		AjaxPagingData pagingData = new AjaxPagingData();
		if(movieName !=null){
			pagingData.setFieldsNameS("movie.movieName");
			pagingData.setFieldsValueS(movieName);
		}
		
		pagingData.setPage(page);
		pagingData.setItem(item);
		pagingData.setFieldsName("um.movieGnosis");
		pagingData.setFieldsValue(" <> '' ");
		pagingData.setFieldsRank("um.createTime");
		pagingData.setFieldsRankType("desc");
		writeStringNotNull(response, userMovieService.getUserMovieListF(pagingData));
	}
}
