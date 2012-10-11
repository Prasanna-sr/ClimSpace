package com.ejb.mdb.query;

import java.io.Serializable;

public interface PointQuery extends Serializable {
	String query(String stn);
}
