package com.hongcheng.fruitmall.ucenter.dao.mapper;

import com.hongcheng.fruitmall.ucenter.pojo.entity.UserCollectEntity;
import com.hongcheng.fruitmall.ucenter.pojo.vo.CollectVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCollectEntityMapper {

    UserCollectEntity selectByUserId(Integer userId);

    UserCollectEntity selectById(Integer id);

    List<CollectVO> getCollectList(@Param("userId") Integer userId);

    int insert(UserCollectEntity entity);

    int delete(Integer id);

}
