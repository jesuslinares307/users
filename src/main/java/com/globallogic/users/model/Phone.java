package com.globallogic.users.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;



@Entity
@Table(name = "PHONE")
public class Phone {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@ColumnDefault("random_uuid()")
	@Type(type = "uuid-char")
	private UUID id;
	private long number;
	private int citycode;
	private String contrycode;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
	
	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	
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
