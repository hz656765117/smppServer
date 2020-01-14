package com.hz.smsgate.business.mybatis.mapper;

import com.hz.smsgate.business.pojo.MtTask;
import com.hz.smsgate.business.pojo.MtTaskExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MtTaskMapper {
    long countByExample(MtTaskExample example);

    int deleteByExample(MtTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MtTask record);

    int insertSelective(MtTask record);

    List<MtTask> selectByExample(MtTaskExample example);

    MtTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MtTask record, @Param("example") MtTaskExample example);

    int updateByExample(@Param("record") MtTask record, @Param("example") MtTaskExample example);

    int updateByPrimaryKeySelective(MtTask record);

    int updateByPrimaryKey(MtTask record);
}