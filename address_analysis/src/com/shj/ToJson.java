package com.shj;

class ToJson {
	public static String tojson(District d) {
		if(d.code.equals("")) {
			return "{}";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{").append(keyValue("code", d.code)).append(",")
		.append(keyValue("name", d.name));
		if(d.districtList != null) {
			sb.append(",").append("\"children\":[{");
			for (int i = 0 ,j = d.districtList.size(); i < j; i++) {
				District district = d.districtList.get(i);
				sb.append(keyValue("code", district.code)).append(",")
				.append(keyValue("name", district.name));
				if(i+1==j) {
					sb.append("}]");
				}else {
					sb.append("},{");
				}
			}
		}
		sb.append("}");
		
		return sb.toString();
	}
	private static String keyValue(String key,String value) {
		return "\""+key+"\":\""+value+"\"";
	}
}
