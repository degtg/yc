package com.yc.damai.dao;

import java.util.List;

import com.yc.damai.po.DmUser;

public interface DmUserMapper {
	List<DmUser> findByTrem(DmUser user);
}
