package com.entity.jpa;

public class DataOutput {

	
	private String YYMMDD_HHMM;
	private String SLAT;
	private String SLON;
	private String TMPF;
	private String state;
	
	public String getYYMMDD_HHMM() {
		return YYMMDD_HHMM;
	}
	public void setYYMMDD_HHMM(String yYMMDD_HHMM) {
		YYMMDD_HHMM = yYMMDD_HHMM;
	}
	public String getSLAT() {
		return SLAT;
	}
	public void setSLAT(String sLAT) {
		SLAT = sLAT;
	}
	public String getSLON() {
		return SLON;
	}
	public void setSLON(String sLON) {
		SLON = sLON;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTMPF() {
		return TMPF;
	}
	public void setTMPF(String tMPF) {
		TMPF = tMPF;
	}
	
}
