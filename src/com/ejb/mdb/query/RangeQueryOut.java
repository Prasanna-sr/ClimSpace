package com.ejb.mdb.query;

import java.io.Serializable;

public interface RangeQueryOut extends Serializable {
	
	void setRangeData(String[] aRow);
}
