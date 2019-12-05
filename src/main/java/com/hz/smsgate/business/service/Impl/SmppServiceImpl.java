package com.hz.smsgate.business.service.Impl;


import com.hz.smsgate.base.smpp.pojo.SessionKey;
import com.hz.smsgate.business.mybatis.mapper.ChannelMapper;

import com.hz.smsgate.business.mybatis.mapper.SmppMapper;
import com.hz.smsgate.business.pojo.Channel;
import com.hz.smsgate.business.pojo.ChannelExample;
import com.hz.smsgate.business.pojo.SmppUserVo;
import com.hz.smsgate.business.service.SmppService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class SmppServiceImpl implements SmppService {

	private static final Logger logger = LoggerFactory.getLogger(SmppServiceImpl.class);


	@Autowired
	private ChannelMapper channelMapper;
	@Autowired
	private SmppMapper smppMapper;


	@Override
	public List<Channel> getAllChannels() {
		ChannelExample example = new ChannelExample();
		example.createCriteria().andSenderidIsNotNull();
		List<Channel> channels = channelMapper.selectByExample(example);
		return channels;
	}

	@Override
	public List<SmppUserVo> getAllSmppUser() {

		List<SmppUserVo> smppUserVos = smppMapper.selectUser(null);


		if (smppUserVos != null && smppUserVos.size() > 0) {
			List<SmppUserVo> list;
			for (SmppUserVo smppUserVo : smppUserVos) {
				String userIds = smppUserVo.getUserIds();
				if (StringUtils.isNotBlank(userIds)) {
					List<Integer> listIds = Arrays.asList(userIds.split(",")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
					list = smppMapper.selectUser(listIds);
					smppUserVo.setList(list);
				}
			}
		}

		return smppUserVos;
	}

	//
//
//
//    @Override
//    public boolean checkNeedPopup(String channel) {
//        List<MkmRecommendPopupRecordsPo> mkmRecommendPopupRecordsPos = queryPopupList(channel);
//        return mkmRecommendPopupRecordsPos != null && mkmRecommendPopupRecordsPos.size() > 0 ? true : false;
//    }
//
//    @Override
//    public PointPosition getSomething(String direction, String len) {
//        PointPosition pointPosition = getLessThanOrEqualLen(direction, len);
//        PointPosition greaterThanOrEqualLen = getGreaterThanOrEqualLen(direction, len);
//        if (greaterThanOrEqualLen == null) {
//            pointPosition = new PointPosition();
//            pointPosition.setRemark("outRange");
//        }
//
//        return pointPosition;
//    }
//
//
//    @Override
//    public List<PointPosition> getSomethings(String direction, String len) {
//        PointPositionExample example = new PointPositionExample();
//        example.createCriteria().andDirectionEqualTo(direction).andLenLessThanOrEqualTo(Integer.valueOf(len));
//        example.setOrderByClause("len  asc");
//        List<PointPosition> pointPositions = pointPositionMapper.selectByExample(example);
//        return  pointPositions;
//    }
//
//    public PointPosition getLessThanOrEqualLen(String direction, String len) {
//        PointPositionExample example = new PointPositionExample();
//        example.createCriteria().andDirectionEqualTo(direction).andLenLessThanOrEqualTo(Integer.valueOf(len));
//        example.setOrderByClause("len desc");
//        List<PointPosition> pointPositions = pointPositionMapper.selectByExample(example);
//        return pointPositions != null && pointPositions.size() > 0 ? pointPositions.get(0) : new PointPosition();
//    }
//
//    public PointPosition getGreaterThanOrEqualLen(String direction, String len) {
//        PointPositionExample example = new PointPositionExample();
//        example.createCriteria().andDirectionEqualTo(direction).andLenGreaterThanOrEqualTo(Integer.valueOf(len));
//        example.setOrderByClause("len asc");
//        List<PointPosition> pointPositions = pointPositionMapper.selectByExample(example);
//        return pointPositions != null && pointPositions.size() > 0 ? pointPositions.get(0) : null;
//    }
//
//    public List<MkmRecommendPopupRecordsPo> queryPopupList(String channel) {
//        MkmRecommendPopupRecordsPoExample example = new MkmRecommendPopupRecordsPoExample();
//        String curTime = DateUtil.convertDateToString(new Date(), DateUtil.dataFormatHHmmss);
//        example.createCriteria().andEffectBeginTimeLessThanOrEqualTo(curTime).andEffectEndTimeGreaterThanOrEqualTo(curTime).andChannelEqualTo(channel);
//        List<MkmRecommendPopupRecordsPo> mkmRecommendPopupRecordsPos = mkmRecommendPopupRecordsPoMapper.selectByExample(example);
//        logger.info("数据条数为：{}", mkmRecommendPopupRecordsPos != null ? mkmRecommendPopupRecordsPos.size() : 0);
//        return mkmRecommendPopupRecordsPos;
//    }
//
//
//    @Override
//    public Map<String, Map<String,List<WechatSchedule>>> getSchedule(String userId) {
//
//        WechatScheduleExample example = new WechatScheduleExample();
//        example.createCriteria().andClassidEqualTo("174");
//
//        List<WechatSchedule> list = wechatScheduleMapper.selectByExample(example);
//        Map<String, Map<String,List<WechatSchedule>>> allMap =new TreeMap<>();
//        for (int i =0 ;i<list.size();i++) {
//            WechatSchedule schedule = list.get(i);
//            String name = schedule.getTimeperiod();
//            String curName = schedule.getTimeperiod() + "_"+ schedule.getDayofweek();
//            if (allMap.get(name)!=null){
//                Map<String,List<WechatSchedule>> curMap = allMap.get(name);
//
//                List<WechatSchedule> schedules = curMap.get(curName);
//                if (schedules== null){
//                    schedules = new ArrayList<>();
//                }
//
//                schedules.add(schedule);
//
//                curMap.put(curName,schedules);
//            }else{
//                List<WechatSchedule> curList = new ArrayList<>();
//                curList.add(schedule);
//                Map<String,List<WechatSchedule>> map =new HashMap<>();
//                map.put(curName,curList);
//                allMap.put(name,map);
//            }
//        }
//
//        return allMap;
//    }
//
//
//    @Override
//    public WechatInfo getWethatInfo(String id) {
//        WechatInfo wechatInfo = wechatInfoMapper.selectByPrimaryKey(Integer.valueOf(id));
//        return wechatInfo;
//    }
}
