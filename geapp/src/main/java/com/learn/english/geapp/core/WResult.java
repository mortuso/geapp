package com.learn.english.geapp.core;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the w_result database table.
 * 
 */
@Entity
@Table(name="w_result")
@NamedQuery(name="WResult.findAll", query="SELECT w FROM WResult w")
public class WResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="w_name")
	private String wName;

	private Integer attempts;

	private Integer centered;

	private BigDecimal percent;

	public WResult() {
	}

	public String getWName() {
		return this.wName;
	}

	public void setWName(String wName) {
		this.wName = wName;
	}

	public Integer getAttempts() {
		return this.attempts;
	}

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public Integer getCentered() {
		return this.centered;
	}

	public void setCentered(Integer centered) {
		this.centered = centered;
	}

	public BigDecimal getPercent() {
		return this.percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

}