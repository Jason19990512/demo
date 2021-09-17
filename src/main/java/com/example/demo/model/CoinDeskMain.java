package com.example.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="COINDESK_MAIN")
public class CoinDeskMain implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "TIME")
	private Timestamp time ;
	@Column(name = "CHART_NAME")
	private String chartName;
	@Column(name = "DISCLAIMER")
	private String disclaimer;
	
	
	@Transient
	private List<BPI> bpiList = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public List<BPI> getBpiList() {
		return bpiList;
	}
	public void setBpiList(List<BPI> bpiList) {
		this.bpiList = bpiList;
	}
	@Override
	public String toString() {
		return "CoinDeskMain [id=" + id + ", time=" + time + ", chartName=" + chartName + ", disclaimer=" + disclaimer
				+ ", bpiList=" + bpiList + "]";
	}
	
}
