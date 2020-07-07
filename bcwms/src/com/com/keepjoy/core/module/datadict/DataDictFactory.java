package com.keepjoy.core.module.datadict;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;
import com.keepjoy.core.context.KeepJoySpringContext;
import com.keepjoy.core.entity.BasDataDict;
import com.keepjoy.core.util.MyLogUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DataDictFactory {
	private static final Log log=LogFactory.getLog(DataDictFactory.class);
	public static final String KEY_JSON="_JSON";
	public static final String KEY_LIST="_LIST";

	private static Map<String,Object> dataDictMap;


	private DataDictFactory(){}

	public static void create() throws IOException{
		if(null==dataDictMap){
			MyLogUtil.printlnInfo("****************************begin init data dict****************************");
			dataDictMap=new HashMap<String, Object>();
			List<BasDataDict> typeList=(List<BasDataDict>) KeepJoySpringContext.getKeepJoyDao().find("from BasDataDict where parentId is null and (dataDictKey is null or dataDictKey='') ");

			if(null!=typeList && typeList.size()>0){
				for(BasDataDict type:typeList){
					createByDataDictType(type);
				}
			}
			log.debug("dataDictMap:"+JSONObject.toJSONString(dataDictMap));
			MyLogUtil.printlnInfo("****************************init data dict ok****************************");
		}
	}


	private static List<Map<String,Object>> getSubList(List<BasDataDict> subValueList){
		List<Map<String,Object>> subValueFinalList=new ArrayList<Map<String,Object>>();

		for(BasDataDict subValue:subValueList){
			Map<String,Object> subMap=new HashMap<String, Object>();
			subMap.put("key", subValue.getDataDictKey());

			List<BasDataDict> subSubValueList=(List<BasDataDict>)KeepJoySpringContext.getKeepJoyDao().find("from BasDataDict where parentId=? ",subValue.getId());

			if(null!=subSubValueList && subSubValueList.size()>0){
				subMap.put("value", subValue);
				subMap.put("sub",getSubList(subSubValueList));
			}
			else{
				subMap.put("value",subValue);
			}

			subValueFinalList.add(subMap);
		}
		return subValueFinalList;

	}

	/**
	 * 根据JtDataDictType 添加
	 * @param type
	 */

	public static void createByDataDictType(BasDataDict type){
		
		Map<String,String> valueMap=new TreeMap<String, String>();
		if(valueMap.containsKey(type.getDataDictType())){
			log.error("dataDictType["+type.getDataDictType()+"] has contained,pls check");
			return;
		}
		
		//得到单级别字典
		List<BasDataDict> allValueList=(List<BasDataDict>)KeepJoySpringContext.getKeepJoyDao().find("from BasDataDict where dataDictType=? and dataDictKey is not null and dataDictKey !='' ",type.getDataDictType());
		
		if(null!=allValueList && allValueList.size()>0){
			dataDictMap.put(type.getDataDictType()+KEY_LIST, allValueList);
			for(BasDataDict value:allValueList){
				if(type.getDataDictType().equals(value.getDataDictType())){
					valueMap.put(value.getDataDictKey(), value.getDataDictValue());
				}
			}
			dataDictMap.put(type.getDataDictType(), valueMap);
		}
		
		//前台用json
		List<BasDataDict> moreValueList=(List<BasDataDict>)KeepJoySpringContext.getKeepJoyDao().find(
				"from BasDataDict where dataDictType=? and (parentId is null or parentId='') and dataDictKey is not null and dataDictKey !='' ",type.getDataDictType());
		dataDictMap.put(type.getDataDictType()+KEY_JSON, getSubList(moreValueList));




	}

	public static Object getDataDictTypeValue(String type){
		return  dataDictMap.get(type);
	}

	public static void removeDataDictByType(String type){
		dataDictMap.remove(type);
		dataDictMap.remove(type+KEY_LIST);
		dataDictMap.remove(type+KEY_JSON);
	}

	/**
	 * 根据key得到value
	 */
	public static <T>T getDataDictTypeKeyValue(String type,Object key){
		if(null==key)return null;
		String keyStr=key.toString();
		if(StringUtils.isEmpty(type) || StringUtils.isEmpty(keyStr))return null;
		Map<String,String> valueMap=(Map<String,String>)dataDictMap.get(type);
		if(null==valueMap){
			log.error("["+type+"] map is null");
			return null;
		}
		String value=valueMap.get(key);
		if(StringUtils.isEmpty(value)){
			return (T) key;
		}else{
			return (T) valueMap.get(key);
		}
	}

	/**
	 * 根据value得到key
	 * @param type
	 * @return
	 */
	public static <T>T getDataDictTypeValueKey(String type,Object value){
		if(null==value)return null;
		String valueStr=value.toString();
		if(StringUtils.isEmpty(type) || StringUtils.isEmpty(valueStr))return null;
		Map<String,String> valueMap=(Map<String,String>)dataDictMap.get(type);
		if(null==valueMap){
			log.error("["+type+"] map is null");
			return null;
		}
		for(String key:valueMap.keySet()){
			if(value.equals(valueMap.get(key))){
				return (T) key;	
			}
		}
		return null;
	}


	public static void refreshAll() throws IOException{
		dataDictMap=null;
		create();
	}



}
