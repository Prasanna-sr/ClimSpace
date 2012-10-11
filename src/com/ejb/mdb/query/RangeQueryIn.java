package com.ejb.mdb.query;

import java.io.Serializable;

public interface RangeQueryIn extends Serializable {
	
	void getRangeData( String[] aRow);
}
