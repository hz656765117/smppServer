package com.hz.smsgate.business.service;


import com.hz.smsgate.base.smpp.pojo.SessionKey;
import com.hz.smsgate.business.pojo.Channel;
import com.hz.smsgate.business.pojo.SmppUserVo;

import java.util.List;
import java.util.Map;

public interface SmppService {



    List<Channel> getAllChannels();

    List<SmppUserVo> getAllSmppUser();

}
