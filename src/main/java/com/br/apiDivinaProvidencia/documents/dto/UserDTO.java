package com.br.apiDivinaProvidencia.documents.dto;

public class UserDTO {
	private String id;
	private String login;
	private boolean admin;

	public UserDTO(String id, String login, boolean admin) {
		this.id = id;
		this.login = login;
		this.admin = admin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
