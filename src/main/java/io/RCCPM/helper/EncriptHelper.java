package io.RCCPM.helper;

import com.google.gson.Gson;
import io.RCCPM.client.ApiException;

import java.util.HashMap;

public class EncriptHelper {

	private String username;
	private String password;

	private CipherData cipherData;

	public EncriptHelper(String username, String password) {
		this.cipherData = CipherData.getInstance();
		this.username = username;
		this.password = password;
	}

	public String getCredentialJWT() throws ApiException {

		HashMap<String, String> credential = new HashMap<String, String>();
		credential.put("username", this.username);
		credential.put("password", this.password);
		Gson gson = new Gson();
		String json = gson.toJson(credential);

		return this.cipherData.cipherData(json);
	}

}