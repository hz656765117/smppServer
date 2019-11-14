package com.hz.smsgate.base.utils;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author huangzhuo
 * @date 2019/7/2 15:50
 */
public class ThreadPoolHelper {

	private static final Logger logger = LoggerFactory.getLogger(ThreadPoolHelper.class);

	/**
	 * 线程池大小
	 */
	private static final int POOL_SIZE = 40;

	/**
	 * 线程池
	 */
	private static ThreadPoolExecutor comitTaskPool = new ScheduledThreadPoolExecutor(POOL_SIZE,
			new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());


	/**
	 * 执行任务
	 * 	 *
	 * 	 * @param comitTask
	 */
	public static void executeTask(Runnable comitTask) {
		comitTaskPool.execute(comitTask);
		logger.debug("【线程池任务】线程池中线程数：" + comitTaskPool.getPoolSize());
		logger.debug("【线程池任务】队列中等待执行的任务数：" + comitTaskPool.getQueue().size());
		logger.debug("【线程池任务】已执行完任务数：" + comitTaskPool.getCompletedTaskCount());
	}


	/**
	 * 关闭线程池
	 */
	public static void shutdown() {
		logger.debug("shutdown comitTaskPool...");
		comitTaskPool.shutdown();
		try {
			if (!comitTaskPool.isTerminated()) {
				logger.debug("直接关闭失败[" + comitTaskPool.toString() + "]");
				comitTaskPool.awaitTermination(3, TimeUnit.SECONDS);
				if (comitTaskPool.isTerminated()) {
					logger.debug("成功关闭[" + comitTaskPool.toString() + "]");
				} else {
					logger.debug("[" + comitTaskPool.toString() + "]关闭失败，执行shutdownNow...");
					if (comitTaskPool.shutdownNow().size() > 0) {
						logger.debug("[" + comitTaskPool.toString() + "]没有关闭成功");
					} else {
						logger.debug("shutdownNow执行完毕，成功关闭[" + comitTaskPool.toString() + "]");
					}
				}
			} else {
				logger.debug("成功关闭[" + comitTaskPool.toString() + "]");
			}
		} catch (InterruptedException e) {
			logger.warn("接收到中断请" + comitTaskPool.toString() + "停止操作");
		}
	}
}