package com.qubaopen.survey.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author mars 城市表
 */
@Entity
@Table(name = "city_code")
public class CityCode extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -4150449937995416451L;

	/**
	 * 城市代码
	 */
	@Column(unique = true)
	private String code;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 省份代码
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_code_id")
	private ProvinceCode provinceCode;

	/**
	 * 身份id
	 */
	private String IDCard;

	/**
	 * 是否显示
	 */
	private boolean isShow;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProvinceCode getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(ProvinceCode provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

}
