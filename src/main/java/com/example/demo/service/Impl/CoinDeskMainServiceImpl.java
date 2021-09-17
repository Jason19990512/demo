package com.example.demo.service.Impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.grid.BPIQueryGrid;
import com.example.demo.model.BPI;
import com.example.demo.model.CoinDeskMain;
import com.example.demo.repository.BPIRepository;
import com.example.demo.repository.CoinDeskMainRepository;
import com.example.demo.service.CoinDeskMainService;

@Service("coinDeskMainService")
public class CoinDeskMainServiceImpl implements CoinDeskMainService{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private CoinDeskMainRepository coinDeskMainRepository;
	
	@Autowired
	private BPIRepository bpiRepository;
	
	@Override
	public String showCoinDeskAPI() {
		Map<String, String> queryParams = new HashMap<>();
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		String coinDeskAPIData = restTemplate
	            .getForObject(url, String.class, queryParams);
		return coinDeskAPIData;
	}

	@Override
	public BPI updateCoinDeskMainAddBPI(BPI bpi) throws ParseException {
		List<BPI> bpiList = findLastBpi();
		CoinDeskMain coinDeskMain = handleOldData();
		bpi.setRefID(coinDeskMain.getId());
		System.out.println(coinDeskMain.getId());
		for(BPI vo : bpiList) {
			vo.setId(null);
			vo.setRefID(coinDeskMain.getId());
		}
		bpiList.add(bpi);
		bpiRepository.saveAll(bpiList);
		return bpi;
	}

	@Override
	public CoinDeskMain handleOldData() throws ParseException {
		Instant instant = Instant.now();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		OffsetDateTime odt = instant.atOffset( ZoneOffset.UTC );
		String s1 = odt.toString();
		Date date = dt.parse(s1.substring(0,s1.indexOf("T")) +" " + s1.substring(s1.indexOf("T") + 1 ,s1.indexOf(".")));
		
		CoinDeskMain coinDeskMain = coinDeskMainRepository.findLastOne();
		coinDeskMain.setTime(new Timestamp(date.getTime()));
		coinDeskMain.setId(null);
		coinDeskMainRepository.save(coinDeskMain);
		return coinDeskMain;
	}

	@Override
	public List<BPI> findLastBpi() {
		CoinDeskMain coinDeskMain = coinDeskMainRepository.findLastOne();
		if(coinDeskMain == null) {
			return new ArrayList<>();
		}else {
			return bpiRepository.findByRefID(coinDeskMain.getId());
		}
	}

	@Override
	public BPI updateCoinDeskMainUpdateBPI(BPI bpi) throws ParseException {
		List<BPI> bpiList =  findLastBpi();
		CoinDeskMain coinDeskMain = handleOldData();
		for(BPI vo : bpiList) {
			if(vo.getId() == bpi.getId()) {
				vo = bpi;
			}
			vo.setRefID(coinDeskMain.getId());
			vo.setId(null);
			bpiRepository.save(vo);
		}
		
		return bpi;
	}

	@Override
	public void updateCoinDeskMainDeleteBPI(Integer id) throws ParseException {
		List<BPI> bpiList =  findLastBpi();
		CoinDeskMain coinDeskMain = handleOldData();
		for(BPI vo : bpiList) {
			if(vo.getId() == id ) {
				continue;
			}
			vo.setRefID(coinDeskMain.getId());
			vo.setId(null);
			bpiRepository.save(vo);
		}
	}

	@Override
	public BPIQueryGrid inputCoinDeskAPIData() throws ParseException {
		List<BPI> bpiList = findLastBpi();
		bpiList.stream().forEach(b -> b.setId(null));
		String coinDeskAPIData = showCoinDeskAPI();
		
		CoinDeskMain coinDeskMain = new CoinDeskMain();
		JSONObject coinDeskAPIObj = JSON.parseObject(coinDeskAPIData);
		
		String time = coinDeskAPIObj.getObject("time", String.class);
		JSONObject timeObj = JSON.parseObject(time);
		String updatedISO = timeObj.getObject("updatedISO", String.class);
		
		String disclaimer = coinDeskAPIObj.getObject("disclaimer", String.class);
		coinDeskMain.setDisclaimer(disclaimer);
		
		String chartName = coinDeskAPIObj.getObject("chartName", String.class);
		coinDeskMain.setChartName(chartName);
		
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dt.parse(updatedISO.substring(0,updatedISO.indexOf("T")) +" " + updatedISO.substring(updatedISO.indexOf("T") + 1 ,updatedISO.indexOf("+")));
		coinDeskMain.setTime(new Timestamp(date.getTime()));
		
		coinDeskMain = coinDeskMainRepository.save(coinDeskMain);
		
		String bpi = coinDeskAPIObj.getObject("bpi", String.class);
		JSONObject bpiDataObj = JSON.parseObject(bpi);
		Map<String, Object> map = (Map<String, Object>)bpiDataObj;
		
		for(String s: map.keySet()) {
			JSONObject bpiObj = JSON.parseObject( bpiDataObj.getObject(s, String.class));
			BPI bpiObject = new BPI();
			String code = bpiObj.getObject("code", String.class);
			for(BPI vo: bpiList) {
				if(code.equals(vo.getCode())) {
					bpiList.remove(vo);
					break;
				}
			}
			bpiObject.setCode(code);
			bpiObject.setDescription(bpiObj.getObject("description", String.class));
			bpiObject.setRate(new BigDecimal(bpiObj.getObject("rate", String.class).replaceAll(",", "")));
			bpiObject.setRateFloat(new BigDecimal(bpiObj.getObject("rate_float", String.class).replaceAll(",", "")));
			bpiObject.setSymbol(bpiObj.getObject("symbol", String.class));
			bpiObject.setRefID(coinDeskMain.getId());
			bpiList.add(bpiObject);
		}
		bpiRepository.saveAll(bpiList);
		
		BPIQueryGrid bPIQueryGrid = new BPIQueryGrid();
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		bPIQueryGrid.setUpdateTime(dt1.format(date));
		bPIQueryGrid.setBpiList(bpiList);
		return bPIQueryGrid;
	}

	
	

}
