package com.example.demo.grid;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.BPI;

public class BPIQueryGrid {
	private String updateTime ;
	private List<BPI> bpiList = new ArrayList<>();
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public List<BPI> getBpiList() {
		return bpiList;
	}
	public void setBpiList(List<BPI> bpiList) {
		this.bpiList = bpiList;
	}
	@Override
	public String toString() {
		return "BPIQueryGrid [updateTime=" + updateTime + ", bpiList=" + bpiList + "]";
	}
}