package com.hz.smsgate.business.mybatis.mapper;

import com.hz.smsgate.business.pojo.SmppUser;
import com.hz.smsgate.business.pojo.SmppUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmppUserMapper {
    long countByExample(SmppUserExample example);

    int deleteByExample(SmppUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmppUser record);

    int insertSelective(SmppUser record);

    List<SmppUser> selectByExample(SmppUserExample example);

    SmppUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SmppUser record, @Param("example") SmppUserExample example);

    int updateByExample(@Param("record") SmppUser record, @Param("example") SmppUserExample example);

    int updateByPrimaryKeySelective(SmppUser record);

    int updateByPrimaryKey(SmppUser record);
}