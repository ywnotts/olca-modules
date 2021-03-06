package org.openlca.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "tbl_locations")
public class Location extends RootEntity {

	@Column(name = "code")
	private String code;

	@Column(name = "latitude")
	private double latitude; 

	@Column(name = "longitude")
	private double longitude;

	@Lob
	@Column(name = "kmz")
	private byte[] kmz;

	public String getCode() {
		return code;
	}

	@Override
	public Location clone() {
		Location clone = new Location();
		clone.setCode(getCode());
		clone.setDescription(getDescription());
		clone.setLatitude(getLatitude());
		clone.setLongitude(getLongitude());
		clone.setName(getName());
		clone.setRefId(UUID.randomUUID().toString());
		return clone;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public byte[] getKmz() {
		return kmz;
	}

	public void setKmz(byte[] kmz) {
		this.kmz = kmz;
	}
}
