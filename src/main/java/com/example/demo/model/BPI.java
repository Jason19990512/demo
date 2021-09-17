package com.example.demo.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BPI")
public class BPI {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "REF_ID")
	private Integer refID;
	@Column(name = "CODE")
	private String code ;
	@Column(name = "SYMBOL")
	private String symbol ;
	@Column(name = "RATE")
	private BigDecimal rate ;
	@Column(name = "DESCRIPTION")
	private String description ;
	@Column(name = "RATE_FLOAT")
	private BigDecimal rateFloat;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRefID() {
		return refID;
	}
	public void setRefID(Integer refID) {
		this.refID = refID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getRateFloat() {
		return rateFloat;
	}
	public void setRateFloat(BigDecimal rateFloat) {
		this.rateFloat = rateFloat;
	}
	@Override
	public String toString() {
		return "BPI [id=" + id + ", refID=" + refID + ", code=" + code + ", symbol=" + symbol + ", rate=" + rate
				+ ", description=" + description + ", rateFloat=" + rateFloat + "]";
	}
	
	
}
