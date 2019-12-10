package com.hz.smsgate.business.service;


import com.hz.smsgate.business.pojo.Channel;
import com.hz.smsgate.business.pojo.OperatorVo;
import com.hz.smsgate.business.pojo.SmppUserVo;

import java.util.List;

public interface SmppService {



    List<Channel> getAllChannels();

    List<SmppUserVo> getAllSmppUser();

    List<OperatorVo> getAllOperator();

}
