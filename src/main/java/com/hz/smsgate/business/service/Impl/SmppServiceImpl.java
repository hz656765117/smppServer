package com.hz.smsgate.business.service.Impl;


import com.hz.smsgate.base.smpp.pdu.SubmitSm;
import com.hz.smsgate.base.utils.ChangeCharset;
import com.hz.smsgate.business.mybatis.mapper.ChannelMapper;
import com.hz.smsgate.business.mybatis.mapper.MtTaskMapper;
import com.hz.smsgate.business.mybatis.mapper.OperatorMapper;
import com.hz.smsgate.business.mybatis.mapper.SmppMapper;
import com.hz.smsgate.business.pojo.*;
import com.hz.smsgate.business.service.SmppService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SmppServiceImpl implements SmppService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmppServiceImpl.class);

	@Autowired
	private ChannelMapper channelMapper;


	@Autowired
	private SmppMapper smppMapper;

	@Autowired
	private MtTaskMapper mtTaskMapper;




	@Override
	public List<Channel> getAllChannels() {
		ChannelExample example = new ChannelExample();
		example.createCriteria().andSenderidIsNotNull();
		List<Channel> channels = channelMapper.selectByExample(example);
		return channels;
	}

	@Override
	public List<SmppUserVo> getAllSmppUser() {

		List<SmppUserVo> smppUserVos = smppMapper.selectUser(null, 0);


		if (smppUserVos != null && smppUserVos.size() > 0) {
			List<SmppUserVo> list;
			for (SmppUserVo smppUserVo : smppUserVos) {
				String userIds = smppUserVo.getUserIds();
				if (StringUtils.isNotBlank(userIds)) {
					List<Integer> listIds = Arrays.asList(userIds.split(",")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
					list = smppMapper.selectUser(listIds, 0);
					smppUserVo.setSenderid(smppUserVo.getDesc());
					smppUserVo.setChannel(smppUserVo.getDesc());
					smppUserVo.setSystemid(smppUserVo.getSmppUser());
					smppUserVo.setList(list);
				}
			}
		}

		return smppUserVos;
	}

	@Override
	public List<OperatorVo> getAllOperator() {
		List<OperatorVo> operatorVos = smppMapper.selectOperator();
		return operatorVos;
	}




}
