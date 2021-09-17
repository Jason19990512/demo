package com.example.demo.service;

import java.util.List;

import com.example.demo.model.BPI;

public interface BPIService {

	void addBPI(BPI bpi);
	
	List<BPI> findBPIS(Integer refID);
	
	BPI findBPI(Integer id);
	
	void updateBPI(BPI bpi);
	
	void deleteBPI(Integer id);
}
