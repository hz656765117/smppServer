package com.hz.smsgate.business.mybatis.mapper;

import com.hz.smsgate.business.pojo.OperatorVo;
import com.hz.smsgate.business.pojo.SmppUserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmppMapper {


    List<SmppUserVo> selectUser(@Param("list") List<Integer> ids,@Param("userType") Integer userType);

    List<OperatorVo> selectOperator();



}