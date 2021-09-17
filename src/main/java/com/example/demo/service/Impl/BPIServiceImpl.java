package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BPI;
import com.example.demo.repository.BPIRepository;
import com.example.demo.service.BPIService;

@Service("bpiService")
public class BPIServiceImpl implements BPIService{

	@Autowired
	private BPIRepository bpiRepository;

	@Override
	public void addBPI(BPI bpi) {
		// TODO Auto-generated method stub
		bpiRepository.save(bpi);
	}

	@Override
	public List<BPI> findBPIS(Integer refID) {
		// TODO Auto-generated method stub
		return bpiRepository.findByRefID(refID);
	}

	@Override
	public BPI findBPI(Integer id) {
		// TODO Auto-generated method stub
		BPI bpi = bpiRepository.findById(id).orElse(null);
		
		return bpi;
	}

	@Override
	public void updateBPI(BPI bpi) {
		// TODO Auto-generated method stub
		BPI bpiExist = findBPI(bpi.getId());
		if(bpiExist != null) {
			bpiRepository.save(bpi);
		}
	}

	@Override
	public void deleteBPI(Integer id) {
		// TODO Auto-generated method stub
		BPI bpiExist = findBPI(id);
		if(bpiExist != null) {
			bpiRepository.deleteById(id);
		}
	}

}
