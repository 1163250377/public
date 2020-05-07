package com.shj;

import java.util.ArrayList;
import java.util.List;

/***
 * 行政区,代表5级行政区的类
 * @author shj
 *
 */
class District {
	/***此行政区的上级区域*/
	public District top;
	/***行政编码 */
	public String code;
	/***城市全称*/
	public String name;
	/***城市简称*/
	public String omitName;
	/***此行政区的下属区域*/
	public List<District> districtList;
	public District(String code, String name, String omitName) {
		super();
		this.code = code;
		this.name = name;
		this.omitName = omitName;
		districtList = new ArrayList<District>();
	}
	
	public District(String code, String name, String omitName, List<District> districtList) {
		super();
		this.code = code;
		this.name = name;
		this.omitName = omitName;
		this.districtList = districtList;
	}

	public District() {
		super();
		districtList = new ArrayList<District>();
	}

	@Override
	public String toString() {
		return "AdminDistrict [code=" + code + ", name=" + name + ", omitName=" + omitName + "]";
	}

	
	
}
