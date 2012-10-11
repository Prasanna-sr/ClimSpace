package com.entity.jpa;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="metadata")
public class Metadata{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column
private int i;
private String primaryid;
private String secondaryid;
private String station_name;
private String state;
private String country;
private String latitude;
private String longitude;
private String elevation;
private String mesowest_network_id;
private String network_name;
private String status;
private String primary_provider_id;
private String primary_provider;
private String secondary_provider;
private String tertiary_provider_id;
private String tertiary_provider;

public String getPrimaryid() {
	return primaryid;
}
public void setPrimaryid(String primaryid) {
	this.primaryid = primaryid;
}
public String getSecondaryid() {
	return secondaryid;
}
public void setSecondaryid(String secondaryid) {
	this.secondaryid = secondaryid;
}
public String getStation_name() {
	return station_name;
}
public void setStation_name(String station_name) {
	this.station_name = station_name;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}

public String getElevation() {
	return elevation;
}
public void setElevation(String elevation) {
	this.elevation = elevation;
}

public String getNetwork_name() {
	return network_name;
}
public void setNetwork_name(String network_name) {
	this.network_name = network_name;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getPrimary_provider_id() {
	return primary_provider_id;
}
public void setPrimary_provider_id(String primary_provider_id) {
	this.primary_provider_id = primary_provider_id;
}
public String getSecondary_provider() {
	return secondary_provider;
}
public void setSecondary_provider(String secondary_provider) {
	this.secondary_provider = secondary_provider;
}
public String getTertiary_provider_id() {
	return tertiary_provider_id;
}
public void setTertiary_provider_id(String tertiary_provider_id) {
	this.tertiary_provider_id = tertiary_provider_id;
}
public String getTertiary_provider() {
	return tertiary_provider;
}
public void setTertiary_provider(String tertiary_provider) {
	this.tertiary_provider = tertiary_provider;
}
public String getPrimary_provider() {
	return primary_provider;
}
public void setPrimary_provider(String primary_provider) {
	this.primary_provider = primary_provider;
}
public String getMesowest_network_id() {
	return mesowest_network_id;
}
public void setMesowest_networkid(String mesowest_network_id) {
	this.mesowest_network_id = mesowest_network_id;
}
public void setLatitude(String latitude) {
	this.latitude = latitude;
}
public void setLongitude(String longitude) {
	this.longitude = longitude;
}


}
