package com.keepjoy.core.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int countKeyOfString(String str,String key){
		Map map = new HashMap(); 
		for(int i = 0; i < str.length(); i++){ 
			String ch = str.charAt(i) + ""; 
			Object och = map.get(ch); 
			if(och == null){ 
				map.put(ch, new Integer(1)); 
			}else{ 
				map.put(ch, new Integer(((Integer)och).intValue()+ 1)); 
			} 
		} 
		Object obj=map.get(key);
		int count=obj==null?0:Integer.parseInt(obj.toString());
		return  count;
	}



	/*******************test*******************/
	public static void main(String[] args){
		String str="and p.assetNo =:blearParm or p.assetDesc =:blearParm or p.band =:blearParm or p.model =:blearParm or p.spec =:blearPram";

		System.out.println(StringUtil.countKeyOfString(str,":"));
	}
}
