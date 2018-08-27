package com.friendship.teams.dao.impl;

import org.springframework.stereotype.Repository;
import com.friendship.teams.dao.ITeamsDelDao;
import com.java.po.LolTeamsDel;
import com.java.util.HibernateDaoBase;

@Repository("teamsDelDao")
public class TeamsDelDao extends HibernateDaoBase<LolTeamsDel> implements ITeamsDelDao{

}
