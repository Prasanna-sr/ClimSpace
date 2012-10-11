package com.ejb.mdb.upload;

import java.io.Serializable;

public interface Upload extends Serializable {
	void echo(String message, String auth);
	
	void update(String[] aRow);
}
