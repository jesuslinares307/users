package com.globallogic.users.dto;

public class PhoneDTO {

	private long number;
	private int cityCode;
	private String countryCode;

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
	 * @return the cityCode
	 */
	public int getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
