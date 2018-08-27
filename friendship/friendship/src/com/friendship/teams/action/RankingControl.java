package com.friendship.teams.action;


import java.util.ArrayList;
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
import com.friendship.teams.service.IRankingService;
import com.java.po.TeamsRanking;
import com.java.util.ActionBase;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;
/**
 * 排名表action
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/ranking")
@Scope
public class RankingControl extends ActionBase{
	private IRankingService rankingService;
	@Resource(name="rankingService")
	public void setRankingService(IRankingService rankingService) {
		this.rankingService = rankingService;
	}
	
	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getRankingList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getRankingList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		super.writeString(response, rankingService.getRankingList(pagingData));
	}
	
	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="deleteRanking.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteRanking(HttpServletResponse response, TeamsRanking entity)throws Exception{
		try {
			rankingService.delete(entity);
			super.writeString(response, "成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="updateRanking.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void updateRanking(HttpServletResponse response, TeamsRanking entity)throws Exception{
		try {
			rankingService.update(entity);
			super.writeString(response, "成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 *前台战队排名
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="rankingDataF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void rankingDataF(HttpServletResponse response, Integer page, Integer item, String seek, String actName)throws Exception{
		AjaxPagingData pagingData = new AjaxPagingData();
		List<EntityFields> list = new ArrayList<EntityFields>();
		if(actName != null && actName.length() > 0){
			pagingData.setFieldsNameS("act.actName");
			pagingData.setFieldsValueS(actName);
		}
		if(seek != null && seek.length() > 0){
			list.add(new EntityFields("act.actName", seek));
			list.add(new EntityFields("teams.teamsName", seek));
		}
		
		pagingData.setFields(list);
		if(item == null) pagingData.setItem(10);
		else pagingData.setItem(item);
		pagingData.setPage(page);
		
		pagingData.setFieldsRank("ranking.ranNumber");
		pagingData.setFieldsRankType("asc");		
		pagingData.setFieldsRank2("ranking.ranCreateTime");
		pagingData.setFieldsRankType2("desc");
		
		super.writeStringNotNull(response, rankingService.getRankingListF(pagingData));
	}
	
	@RequestMapping(value="rankingPageF.htm",method=RequestMethod.POST)
	public String rankingPageF()throws Exception{
		return "foreground/page/ranking";
	}
}
