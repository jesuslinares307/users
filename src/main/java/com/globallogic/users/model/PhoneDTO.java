package com.globallogic.users.model;

public class PhoneDTO {

	private long number;
	private int citycode;
	private String contrycode;

	/**
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(long number) {
		this.number = number;
	}
	/**
	 * @return the citycode
	 */
	public int getCitycode() {
		return citycode;
	}
	/**
	 * @param citycode the citycode to set
	 */
	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}
	/**
	 * @return the contrycode
	 */
	public String getContrycode() {
		return contrycode;
	}
	/**
	 * @param contrycode the contrycode to set
	 */
	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}
}
