package com.example.demo.service;

import java.text.ParseException;
import java.util.List;

import com.example.demo.grid.BPIQueryGrid;
import com.example.demo.model.BPI;
import com.example.demo.model.CoinDeskMain;

public interface CoinDeskMainService {

	 String showCoinDeskAPI();
	
	 BPI updateCoinDeskMainAddBPI(BPI bpi) throws ParseException;
	 
	 CoinDeskMain handleOldData() throws ParseException;
	 
	 List<BPI> findLastBpi();

	 BPI updateCoinDeskMainUpdateBPI( BPI bpi) throws ParseException;
	 
	 void updateCoinDeskMainDeleteBPI( Integer id) throws ParseException;
	 
	 BPIQueryGrid inputCoinDeskAPIData() throws ParseException;
}
