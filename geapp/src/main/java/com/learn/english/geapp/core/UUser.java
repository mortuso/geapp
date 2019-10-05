package com.learn.english.geapp.core;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the u_user database table.
 * 
 */
@Entity
@Table(name="u_user")
@NamedQuery(name="UUser.findAll", query="SELECT u FROM UUser u")
public class UUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String answer;

	private String pass;

	private String question;

	//bi-directional many-to-one association to UserResult
	@OneToMany(mappedBy="UUser")
	private List<UserResult> userResults;

	public UUser() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<UserResult> getUserResults() {
		return this.userResults;
	}

	public void setUserResults(List<UserResult> userResults) {
		this.userResults = userResults;
	}

	public UserResult addUserResult(UserResult userResult) {
		getUserResults().add(userResult);
		userResult.setUUser(this);

		return userResult;
	}

	public UserResult removeUserResult(UserResult userResult) {
		getUserResults().remove(userResult);
		userResult.setUUser(null);

		return userResult;
	}

}