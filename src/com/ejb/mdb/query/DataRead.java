package com.ejb.mdb.query;

import java.io.Serializable;

public interface DataRead extends Serializable {
	void doDataRead(String loc);
}
