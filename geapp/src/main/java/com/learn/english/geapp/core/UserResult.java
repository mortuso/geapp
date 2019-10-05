package com.learn.english.geapp.core;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the user_result database table.
 * 
 */
@Entity
@Table(name="user_result")
@NamedQuery(name="UserResult.findAll", query="SELECT u FROM UserResult u")
public class UserResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="OAUTH_USER_USERPK_GENERATOR", sequenceName="id_user_result", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OAUTH_USER_USERPK_GENERATOR")
	private Integer id;

	@Column(name="u_date")
	private Timestamp uDate;

	@Column(name="u_result")
	private BigDecimal uResult;

	//bi-directional many-to-one association to UUser
	@ManyToOne
	@JoinColumn(name="username")
	private UUser UUser;

	public UserResult() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getUDate() {
		return this.uDate;
	}

	public void setUDate(Timestamp uDate) {
		this.uDate = uDate;
	}

	public BigDecimal getUResult() {
		return this.uResult;
	}

	public void setUResult(BigDecimal uResult) {
		this.uResult = uResult;
	}

	public UUser getUUser() {
		return this.UUser;
	}

	public void setUUser(UUser UUser) {
		this.UUser = UUser;
	}

}