package pers.allen.explore.io.ochat;

import java.util.HashMap;
import java.util.Map;

public class ClientsInfo {

	public static Map<String, Client> clients = new HashMap<>();

	public static final int SIGN_LENGTH = 2; // @:
	public static final int CID_LENGTH = 6; // clientId
	public static final int SC_LENGTH = SIGN_LENGTH + CID_LENGTH;

}
