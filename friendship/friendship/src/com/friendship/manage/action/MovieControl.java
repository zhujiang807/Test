package com.friendship.manage.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendship.manage.service.IConfigService;
import com.friendship.manage.service.IMovieService;
import com.java.po.Movie;
import com.java.util.ActionBase;
import com.java.util.Constant;
import com.java.util.DateBase;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

@Controller
@RequestMapping(value="/movie")
@Scope
public class MovieControl extends ActionBase{
	private IMovieService movieService;
	private IConfigService configService;

	@Resource(name="movieService")
	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}
	@Resource(name="configService")
	public void setConfigService(IConfigService configService) {
		this.configService = configService;
	}
	
	/**
	 * 后台主页提供数据
	 * @throws Exception
	 */
	@RequestMapping(value="getMovieList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getMovieList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		writeString(response, movieService.getMovieList(pagingData));
	}
	
	/**
	 * @throws Exception
	 */
	@RequestMapping(value="addMovie.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void addMovie(HttpServletResponse response, Movie entity, @RequestParam MultipartFile fileA)throws Exception{
		String newPath = null;
		if(fileA.getSize() > 1500000){
			super.writeString(response, "图片小于1500Kb！");
			return ;
		}
		if(!fileA.isEmpty()){
			String[] path = configService.getConfig(Constant.MOVIE_PICTURE_PATH).getConfigValue().split(";");
			newPath = path[0]+fileA.getOriginalFilename();
			super.fileUploading(fileA, newPath);
			entity.setMoviePicture(path[1]+newPath);
		}
		if(entity.getFieldValue1() != null && entity.getFieldValue1().length() > 0)
			entity.setMovieShowTime(DateBase.strToTimestamp(entity.getFieldValue1()));
		entity.setMovieCreateTime(super.getDate());
		movieService.save(entity);
		writeString(response, "添加成功！");
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value="deleteMovie.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void deleteMovie(HttpServletResponse response, Movie entity)throws Exception{
		movieService.delete(entity);
		writeString(response, "添加成功！");
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value="updateMovie.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void updateMovie(HttpServletResponse response, Movie entity)throws Exception{
		if(entity.getFieldValue1() != null && entity.getFieldValue1().length() > 0)
			entity.setMovieShowTime(DateBase.strToTimestamp(entity.getFieldValue1()));
		movieService.update(entity);
		writeString(response, "修改成功！");
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value="movieListF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void movieListF(HttpServletResponse response, Integer page, Integer item, Integer movieType)throws Exception{
		AjaxPagingData pagingData = new AjaxPagingData();
		if(movieType == null)
			return;
		if(page == null) pagingData.setPage(1);
		else pagingData.setPage(page);
		if(page == null) pagingData.setItem(50);
		else pagingData.setItem(item);
		
		pagingData.setFieldsRank("total");
		pagingData.setFieldsRankType("desc");
		pagingData.setFieldsName("mvoie.movieShow");
		pagingData.setFieldsValue(movieType);
		
		super.writeStringNotNull(response, movieService.getMovieListF(pagingData));
	}
}
