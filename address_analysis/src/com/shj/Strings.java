package com.shj;

public class Strings {
	
	//移除字符
	public static String remove(String str,char ch) {
		char[] chars = str.toCharArray();
		//统计需要移除的字符个数
		int count = 0;
		for (int i = 0; i < chars.length; i++) {
			if(chars[i]==ch) {
				count++;
			}
		}
		//创建新的字符数组  去掉相应的字符
		char[] newstr = new char[chars.length-count];
		for (int i = 0,index = 0; i < chars.length; i++) {
			if(chars[i]!=ch) {
				newstr[index]=chars[i];
				index++;
			}
		}
		return new String(newstr);
	}
	/***
	 * 是否包含字符串
	 * @param parent 父串
	 * @param child 子串
	 * @return
	 */
	public static boolean contains(String parent,String child) {
		char[] a = parent.toCharArray();
		char[] b = child.toCharArray();
		if(a.length<b.length) {
			return false;
		}
		for (int j = 0; j < a.length; j++) {
			if(b[0] == a[j]) {
				int i = 1;
				while(i < b.length) {
					++j;
					if(j>=a.length) 
						return false;
					
					if(b[i] != a[j]) 
						return false;
					
					i++;
				}
				return true;
			}
		}
		return false;
	}
	/***
	 * 是否以子字符串开头
	 * @param parent 父串
	 * @param child 子串
	 * @return
	 */
	public static boolean startWith(String parent,String child) {
		char[] fu = parent.toCharArray();
		char[] pre = child.toCharArray();
		if(fu.length<pre.length) {
			return false;
		}
		for (int i = 0; i < pre.length; i++) {
			if(pre[i] != fu[i]) {
				return false;
			}
		}
		return true;
	}
	
	/***
	 * 否以子字符串结尾
	 * @param parent 父串
	 * @param child 子串
	 * @return
	 */
	public static boolean endWith(String parent,String child) {
		char[] fu = parent.toCharArray();
		char[] pre = child.toCharArray();
		if(fu.length<pre.length) {
			return false;
		}
		for (int i = pre.length-1,j = fu.length-1; i >= 0; i--,j--) {
			if(pre[i] != fu[j]) {
				return false;
			}
		}
		return true;
	}
	public static boolean equals(String parent,String child) {
		char[] par = parent.toCharArray();
		char[] ch = child.toCharArray();
		for (int i = 0; i < par.length; i++) {
			if(par[i]!=ch[i])
				return false;
		}
		return true;
	}
	public static boolean isNum(String num) {
		char[] fu = num.toCharArray();
		for (int i = 0; i < fu.length; i++) {
			if(fu[i] < 48 || fu[i] > 57) {
				return false;
			}
		}
		return true;
	}
}
