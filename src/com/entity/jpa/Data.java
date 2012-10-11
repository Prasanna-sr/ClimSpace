package com.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="data")
public class Data{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column
private int i;
private String STN;
private String YYMMDD_HHMM;
private String MNET;
private String SLAT;
private String SLON;
private String SELV;
private String TMPF;
private String SKNT;
private String DRCT;
private String GUST;
private String PMSL;
private String ALTI;
private String DWPF;
private String RELH;
private String WTHR;
private String P24I;

public String getSTN() {
	return STN;
}
public void setSTN(String sTN) {
	STN = sTN;
}
public String getYYMMDD_HHMM() {
	return YYMMDD_HHMM;
}
public void setYYMMDD_HHMM(String yYMMDD_HHMM) {
	YYMMDD_HHMM = yYMMDD_HHMM;
}
public String getMNET() {
	return MNET;
}
public void setMNET(String mNET) {
	MNET = mNET;
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
public String getSELV() {
	return SELV;
}
public void setSELV(String sELV) {
	SELV = sELV;
}
public String getTMPF() {
	return TMPF;
}
public void setTMPF(String tMPF) {
	TMPF = tMPF;
}
public String getSKNT() {
	return SKNT;
}
public void setSKNT(String sKNT) {
	SKNT = sKNT;
}
public String getDRCT() {
	return DRCT;
}
public void setDRCT(String dRCT) {
	DRCT = dRCT;
}
public String getGUST() {
	return GUST;
}
public void setGUST(String gUST) {
	GUST = gUST;
}
public String getPMSL() {
	return PMSL;
}
public void setPMSL(String pMSL) {
	PMSL = pMSL;
}
public String getALTI() {
	return ALTI;
}
public void setALTI(String aLTI) {
	ALTI = aLTI;
}
public String getDWPF() {
	return DWPF;
}
public void setDWPF(String dWPF) {
	DWPF = dWPF;
}
public String getRELH() {
	return RELH;
}
public void setRELH(String rELH) {
	RELH = rELH;
}
public String getWTHR() {
	return WTHR;
}
public void setWTHR(String wTHR) {
	WTHR = wTHR;
}
public String getP24I() {
	return P24I;
}
public void setP24I(String p24I) {
	P24I = p24I;
}




}
