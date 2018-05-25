package com.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.Exposer;
import com.dto.SeckillExecution;
import com.dto.SeckillResult;
import com.exception.RepeatKillException;
import com.exception.SeckillCloseException;
import com.exception.SeckillException;
import com.pojo.Seckill;
import com.seckillStateEnum.SeckillStateEnum;
import com.service.SeckillService;

/**
 * 控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

	private Logger logger = Logger.getLogger(SeckillController.class);

	@Autowired
	private SeckillService seckillService;

	/**
	 * 获取列表 时间：2018年5月13日-下午4:50:53 作者：news
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		List<Seckill> seckillList = seckillService.getSeckillList();
		model.addAttribute("seckillList", seckillList);
		return "list";
	}

	/**
	 * 详情 时间：2018年5月13日-下午4:55:37 作者：news
	 */
	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
		if (seckillId == null) {
			return "redirect:/seckill/list";
		}

		Seckill seckill = seckillService.getSeckillById(seckillId);
		if (seckill == null) {
			return "forword:/seckill/list";
		}
		model.addAttribute("seckill", seckill);

		return "detail";
	}

	/**
	 * 暴露秒杀地址 时间：2018年5月13日-下午7:23:23 作者：news
	 */
	@ResponseBody
	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {
			"application/json,charset=utf-8" })
	public SeckillResult<Exposer> exposer(long seckillId) {
		SeckillResult<Exposer> seckillResult;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			seckillResult = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			seckillResult = new SeckillResult<>(false, e.getMessage());
		}
		return seckillResult;
	}

	/**
	 * 执行秒杀 时间：2018年5月13日-下午7:35:02 作者：news
	 */
	@RequestMapping(value = "/{seckillId}/{md5}/execute")
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5, @CookieValue(value = "killPhone", required = false) Long killPhone) {

		if (killPhone == null) {
			return new SeckillResult<SeckillExecution>(false, "未注册");
		}
		SeckillResult<SeckillExecution> result = null;

		try {
			// 存储过程执行秒杀
			SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, killPhone, md5);
			result = new SeckillResult<SeckillExecution>(true, execution);
		} catch (RepeatKillException re) {
			logger.error(">>:重复秒杀，" + re.getMessage());
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(false, execution);
		} catch (SeckillCloseException se) {
			logger.error(">>:秒杀关闭，" + se.getMessage());
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.END);
			return new SeckillResult<SeckillExecution>(false, execution);
		} catch (Exception e) {
			logger.error(">>:秒杀内部错误，" + e.getMessage());
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.INSERT_ERROR);
			return new SeckillResult<SeckillExecution>(false, execution);
		}

		return result;

	}

	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	public SeckillResult<Long> getTime() {
		Date time = new Date();
		return new SeckillResult<Long>(true, time.getTime());

	}

}
