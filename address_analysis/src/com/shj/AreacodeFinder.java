package com.shj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/***
 * 地区编码查找器
 * @author Administrator
 *
 */
public class AreacodeFinder {

	private static AreacodeFinder areacodeFinder;
	private List<District> districtList = null;

	private AreacodeFinder() {
		super();
		districtList = new ArrayList<District>();
		init();
	}
	
	public AreacodeFinder(String p) {
		super();
		districtList = new ArrayList<District>();
		init2(p);
	}

	public static AreacodeFinder newInstance() {
		if(areacodeFinder==null) {
			areacodeFinder = new AreacodeFinder();
		}
		return areacodeFinder;
	}
	public static AreacodeFinder newInstance(String p) {
		if(areacodeFinder==null) {
			areacodeFinder = new AreacodeFinder(p);
		}
		return areacodeFinder;
	}
	private void initsheng(List<String> list) {
		// 加载省
		for (String shengList : list) {
			String[] sl = shengList.split(",");
			District admin = new District(sl[0], sl[1], sl[2]);
			districtList.add(admin);
		}
	}

	private void initshi(List<String> list) {
		for (String shiList : list) {
			String[] shil = shiList.split(",");
			District admin = new District(shil[0], shil[1], shil[2]);
			for (int i = 0; i < districtList.size(); i++) {
				// 省集合
				District sh = districtList.get(i);
				// 本市属于哪个省
				if (Strings.startWith(admin.code, sh.code)) {
					admin.top = sh;
					sh.districtList.add(admin);
				}
			}
		}
	}

	private void initquxian(List<String> list) {
		// 加载 区,县
		for (String quList : list) {
			String[] ql = quList.split(",");
			District admin = new District(ql[0], ql[1], ql[2]);
			for (int i = 0; i < districtList.size(); i++) {
				// 省集合,获取省
				District sh = districtList.get(i);
				// 获取市集合，遍历集合确定市
				if (Strings.startWith(admin.code, sh.code)) {
					for (int j = 0; j < sh.districtList.size(); j++) {
						District shi = sh.districtList.get(j);
						// 遍历市确定区县
						if (Strings.startWith(admin.code, shi.code)) {
							admin.top = shi;
							shi.districtList.add(admin);
						}
					}
				}
			}
		}
	}

	private void initjiedao(List<String> list) {
		// 加载 乡镇,街道
		for (String jieList : list) {
			String[] jl = jieList.split(",");
			District admin = new District(jl[0], jl[1], jl[2]);
			for (int i = 0; i < districtList.size(); i++) {
				// 省集合,获取省
				District sh = districtList.get(i);
				// 获取市集合，遍历集合确定市
				if (Strings.startWith(admin.code, sh.code)) {
					for (int j = 0; j < sh.districtList.size(); j++) {
						District shi = sh.districtList.get(j);
						if (Strings.startWith(admin.code, shi.code)) {
							// 遍历市确定区县
							for (int k = 0; k < shi.districtList.size(); k++) {
								District xian = shi.districtList.get(k);
								if (Strings.startWith(admin.code, xian.code)) {
									admin.top = xian;
									xian.districtList.add(admin);
								}
							}
						}
					}
				}
			}
		}
	}

	private void initcun(List<String> list) {
		// 加载村
		for (String cunList : list) {
			String[] jl = cunList.split(",");
			District admin = new District(jl[0], jl[1], jl[2], null);
			for (int i = 0; i < districtList.size(); i++) {
				// 省集合,获取省
				District sh = districtList.get(i);
				if (Strings.startWith(admin.code, sh.code)) {
					// 获取市集合，遍历集合确定市
					for (int j = 0; j < sh.districtList.size(); j++) {
						District shi = sh.districtList.get(j);
						if (Strings.startWith(admin.code, shi.code)) {
							// 遍历市确定区县
							for (int k = 0; k < shi.districtList.size(); k++) {
								District xian = shi.districtList.get(k);
								if (Strings.startWith(admin.code, xian.code)) {
									for (int l = 0; l < xian.districtList.size(); l++) {
										// 街道
										District jiedao = xian.districtList.get(l);
										if (Strings.startWith(admin.code, jiedao.code)) {
											admin.top = jiedao;
											jiedao.districtList.add(admin);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	private List<String> readFiles(String str) throws IOException {
		List<String> list = new ArrayList<String>();
		BufferedReader buff = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(str)));
		for (String line = buff.readLine();line!=null;line = buff.readLine()) {
			list.add(line);
		}
		return list;
	}
	private void init2(String p) {
		try {
			List<String> listSheng = Files.readAllLines(Paths.get(p + "sheng.csv"));
			List<String> listShi = Files.readAllLines(Paths.get(p + "shi.csv"));
			List<String> listQuxian = Files.readAllLines(Paths.get(p + "qu_xian.csv"));
			List<String> listJiedao = Files.readAllLines(Paths.get(p + "jiedao.csv"));
			List<String> listcun = Files.readAllLines(Paths.get(p + "cun.csv"));
			initsheng(listSheng);
			initshi(listShi);
			initquxian(listQuxian);
			initjiedao(listJiedao);
			initcun(listcun);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void init() {
		
		try {
			List<String> listSheng = readFiles("data/sheng.csv");
			List<String> listShi = readFiles("data/shi.csv");
			List<String> listQuxian = readFiles("data/qu_xian.csv");
			List<String> listJiedao = readFiles("data/jiedao.csv");
			List<String> listcun = readFiles("data/cun.csv");
			initsheng(listSheng);
			initshi(listShi);
			initquxian(listQuxian);
			initjiedao(listJiedao);
			initcun(listcun);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		AreacodeFinder a = AreacodeFinder.newInstance();
		System.out.println(a.getJSON("中1国"));
		System.out.println();
	}
	/***
	 * 根据 省，市名或者地区编码获取 下属的所有行政区 以json格式返回
	 * @param areaName
	 * @return
	 */
	public String getJSON(String areaName) {
		District a = null;
		if(areaName.equals("中国")) {			
			a = new District("000000000000", "中华人民共和国", "中国", districtList);
		}else {
			a = new District("", "", "", null);
		}
		if(Strings.isNum(areaName)) {
			areaName = searchByCode(areaName);
		}
		District b = findNode(a,districtList, areaName, 1);
		return ToJson.tojson(b);
	}
	/***
	 * 传入一个地址，返回这个地址的12位地区编码
	 * @param str
	 * @return
	 */
	public String searchByAddress(String str) {
		District a = new District("000000000000", "中华人民共和国", "中国", null);
		District b = findNode(a,districtList, str, 1);
		String string = b.code;
		int size = 12 - string.length();
		if(size > 0) {
			for (int i = 0; i < size; i++) {
				string +="0";
			}
		}
		return string;
	}
	/***
	 * 传入一个编码，返回这个编码的5级地址
	 * @param str
	 * @return
	 */
	public String searchByCode(String str) {
		return findCode(districtList,str,"");
	}
	private String findCode(List<District> nodelist,String str,String name) {
		for (int i = 0,size = nodelist.size(); i < size; i++) {
			District admin = nodelist.get(i);
			if(Strings.startWith(str, admin.code)) {
				if(admin.districtList!=null) {
					return findCode(admin.districtList, str,name+admin.name);
				}
				return name + admin.name;
			}
		}
		return name;
	}
	// 递归查找 从省开始查找
	/***
	 * 
	 * @param node
	 * @param address
	 * @param grade
	 * @return grade 递归等级
	 */
	private District findNode(District curr,List<District> nodelist, String address, Integer grade) {
		// 标记是否找到
		boolean iszhaodao = false;
		// 查找当前地址
		District currentNode = null;
		for (int i = 0, nodesize = nodelist.size(); i < nodesize; i++) {
			District ad = nodelist.get(i);
			if (Strings.contains(address, ad.omitName)) {
				iszhaodao = true;
				currentNode = ad;
				break;
			}
		}
		if (iszhaodao) {
			// 如果找到当前地址,且还有二级地址，则继续查找
			if (currentNode.districtList != null) {
				return findNode(currentNode,currentNode.districtList, replace(address.replace(currentNode.omitName, ""), grade),
						grade + 1);
			} else {
				return currentNode;
			}
		} else {
			// 如果没有找到当前地址，那么在下一级的地址中继续找
			return findNodeJump(curr,nodelist, address, grade + 2);
		}
	}
	//跳过下一级  到更下一级中去查找
	private District findNodeJump(District cur,List<District> nodelist, String address, Integer grade) {
		//这个for跳过一级去查
		for (int i = 0, nodesize = nodelist.size(); i < nodesize; i++) {
			District ad = nodelist.get(i);
			if (ad.districtList != null) {
				for (int j = 0, size = ad.districtList.size(); j < size; j++) {
					District admin = ad.districtList.get(j);
					if (Strings.contains(address, admin.omitName)) {
						if (admin.districtList != null) {
							return findNode(admin,admin.districtList, replace(address.replace(admin.omitName, ""), grade),grade + 2);
						} else {
							return admin;
						}
					}
				}
			}
		}
		//这个for跳过二级去查
		for (int i = 0, nodesize = nodelist.size(); i < nodesize; i++) {
			District ad = nodelist.get(i);
			if (ad.districtList != null) {
				for (int j = 0, size = ad.districtList.size(); j < size; j++) {
					District admin = ad.districtList.get(j);
					if(admin.districtList != null) {						
						for (int k = 0,si = admin.districtList.size(); k < si; k++) {
							District district = admin.districtList.get(k);
							if (Strings.contains(address, district.omitName)) {
								if (district.districtList != null) {
									return findNode(district,district.districtList, replace(address.replace(district.omitName, ""), grade),grade + 3);
								} else {
									return district;
								}
							}
						}
					}
				}
			}
		}
		return cur;
	}
	//减去查询地址中一些没必要的字符
	private String replace(String str, Integer grade) {
		switch (grade) {
		case 1:
			return str.replaceFirst("省", "").replaceFirst("市", "");
		case 2:
			return str.replaceFirst("市", "").replaceFirst("盟", "").replaceFirst("自治州", "");
		case 3:
			return str.replaceFirst("区", "").replaceFirst("自治县", "").replaceFirst("县", "");
		case 4:
			return str.replaceFirst("街道", "").replaceFirst("镇", "").replaceFirst("乡", "");
		default:
			// return str.replaceFirst("居委会", "").replaceFirst("村委会",
			// "").replaceFirst("委员会", "");
			return str;
		}
	}
}
