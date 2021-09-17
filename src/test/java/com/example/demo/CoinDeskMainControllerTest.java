package com.example.demo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.grid.BPIQueryGrid;
import com.example.demo.model.BPI;
import com.example.demo.model.CoinDeskMain;
import com.example.demo.repository.CoinDeskMainRepository;
import com.example.demo.service.Impl.CoinDeskMainServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoinDeskMainControllerTest {

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	CoinDeskMainServiceImpl coinDeskMainService;
	
	@Autowired
	private CoinDeskMainRepository coinDeskMainRepository;
	
//	@Test
	public void testFindLastBpi() {
		List<BPI> bpiList = coinDeskMainService.findLastBpi();//1. 測試呼叫查詢幣別對應表資料 API,並顯示其內容。
		for(BPI vo : bpiList) {
			logger.info(vo.toString());
		}
	}
	
//	@Test
	public void testUpdateCoinDeskMainAddBPI() throws ParseException {
		BPI bpi =  new BPI();
		bpi.setCode("TEST_CODE");
		bpi.setDescription("FOR TEST");
		bpi.setRate(new BigDecimal("123.34"));
		bpi.setRateFloat(new BigDecimal("123.34"));
		bpi.setSymbol("&test");
		bpi = coinDeskMainService.updateCoinDeskMainAddBPI(bpi);//2. 測試呼叫新增幣別對應表資料 API。
		logger.info(bpi.toString());
	}
	
	
	
//	@Test
	public void testupdateCoinDeskMainUpdateBPI() throws ParseException {
		BPI bpi =  new BPI();
		CoinDeskMain coinDeskMain = coinDeskMainRepository.findLastOne();
		bpi.setId(29);
		bpi.setRefID(14);
		bpi.setCode("TEST_CODE_1");
		bpi.setDescription("FOR TEST_1");
		bpi.setRate(new BigDecimal("12345.67"));
		bpi.setRateFloat(new BigDecimal("1234.56"));
		bpi.setSymbol("&test1");
		bpi = coinDeskMainService.updateCoinDeskMainUpdateBPI(bpi);//3. 測試呼叫更新幣別對應表資料 API,並顯示其內容。
		logger.info(bpi.toString());
	}
	
	
//	@Test
	public void testUpdateCoinDeskMainDeleteBPI() throws ParseException {
		coinDeskMainService.updateCoinDeskMainDeleteBPI(45); //4.  測試呼叫刪除幣別對應表資料 API。
	}
	
	@Test
	public void testShowCoinDeskAPI() {
		String coinDeskAPIData = coinDeskMainService.showCoinDeskAPI();//5. 測試呼叫 coindesk API 成功,並顯示其內容。
		logger.info(coinDeskAPIData);
	}
	
//	@Test
	public void testInputCoinDeskAPIData() throws ParseException {
		BPIQueryGrid bPIQueryGrid =coinDeskMainService.inputCoinDeskAPIData();//6. 測試呼叫資料轉換的 API,並顯示其內容。
		logger.info(bPIQueryGrid.toString());
	}
}
