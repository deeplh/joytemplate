package com.keepjoy.core.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class ByteUtil {
	/**
	 * 将BCD码转成String
	 * @param b
	 * @return
	 */
	public static String bcdByteArrayToString(byte [] b)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0;i < b.length;i++ )
		{
			int h = ((b [i] & 0xff) >> 4) + 48;
			sb.append ((char) h);
			int l = (b [i] & 0x0f) + 48;
			sb.append ((char) l);
		}
		return sb.toString(); 
	}

	/**
	 * 将String转成BCD码
	 * 
	 * @param s
	 * @return
	 */
	public static byte [] stringToBcdByteArray(String s)
	{
		if(s.length () % 2 != 0)
		{
			s = "0" + s;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		char [] cs = s.toCharArray ();
		for (int i = 0;i < cs.length;i += 2)
		{
			int high = cs [i] - 48;
			int low = cs [i + 1] - 48;
			baos.write (high << 4 | low);
		}
		return baos.toByteArray();
	}

	/**
	 * byte数组的拼接
	 */
	public static byte[] join(byte[] data1,byte[] data2){
		byte[] data3 = new byte[data1.length+data2.length];
		System.arraycopy(data1,0,data3,0,data1.length);
		System.arraycopy(data2,0,data3,data1.length,data2.length); 
		return data3;

	}

	/**
	 * 对byte数组不够长度补零(右补0)
	 * @param fillByte 需要补零的byte数组
	 * @param len 需要补零的长度
	 * @return
	 */
	public static byte[] rightAddZero(byte[] fillByte,int len){
		if(fillByte == null){
			return null;
		}
		//补零
		byte [] zero = new byte[len]; 
		byte[] fillZero=join(fillByte,zero);
		return fillZero;
	}

	//左补0
	public static byte[] leftAddZero(byte[] fillByte,int len){
		if(fillByte == null){
			return null;
		}
		//补零
		byte [] zero = new byte[len]; 
		byte[] fillZero=join(zero,fillByte);
		return fillZero;
	}






	/**
	 * 字节数组转换为int(java中1个int,4个字节32位)
	 * @param b
	 * @return
	 */
	public static int byteArrayToInt(byte[] b) {
		int value= 0;
		for (int i = 0; i < 4; i++) {
			int shift= (4 - 1 - i) * 8;
			value +=(b[i] & 0x000000FF) << shift;
		}
		return value;
	}
	
	/**
	 * 字节数组转换为int
	 * @param b
	 * @param offset 位偏移
	 * @return
	 */
	public static int byteArrayToInt(byte[] b, int offset) {
		int value= 0;
		for (int i = 0; i < 4; i++) {
			int shift= (4 - 1 - i) * 8;
			value +=(b[i + offset] & 0x000000FF) << shift;
		}
		return value;
	}

	/**
	 * int转为字节
	 * @param i
	 * @return
	 */
	public static byte[] intToByteArray(int i){
		ByteArrayOutputStream buf = null;   
		DataOutputStream out = null;   
		byte[] b=null;
		try {
			buf = new ByteArrayOutputStream();  
			out = new DataOutputStream(buf);   
			out.writeInt(i);   
			b = buf.toByteArray();
		} catch (IOException e) {
			b = null;
		}finally{
			try {
				out.close();
				buf.close();
			} catch (IOException e) {
				b=null;
			}
		}
		return b;
	}

	/** byte数组转16进制表示的String
	 * @param bt
	 * @param String
	 * @return
	 */
	public static  boolean byteArrayToHexString(byte[] bt, StringBuffer str)
	{
		if (bt == null)
			return false;
		for (int i = 0; i < bt.length; i++)
		{
			//将byte 类型数据 转到 十六进制 字符串
			str.append(byteToHexString(bt[i]));
		}
		return true;
	}
	
	/**
	 * 将byte 转换成 对应的十六进制 字符串
	 * @param bt
	 * @return
	 */
	public static  String byteToHexString(byte bt)
	{
		String myIntStr = Integer.toHexString(bt & 0xFF);
		myIntStr=myIntStr.toUpperCase();
		if(myIntStr !=null && myIntStr.length() == 1){
			myIntStr = "0"+myIntStr;
		}
		return myIntStr;
	}
	
	/**
	 * byte 数组转16进制表示的String
	 * @param bt
	 * @return
	 */
	public static  String byteArrayToHexString(byte[] bt)
	{
		StringBuffer  str = new StringBuffer("");
		if (bt == null)
			return str.toString();
		for (int i = 0; i < bt.length; i++)
		{
			str.append(byteToHexString(bt[i]));
		}
		return str.toString();
	}

	/**
	 * 将十六进制字符串 转成 byte[] 数组 (十六进制字符串，每两位需要转换成byte[])
	 * @param hex
	 * @return
	 */
	public static byte[] hexStringToByteArray(String hex) {
		String tmp="0123456789ABCDEF";
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) ((byte) tmp.indexOf(achar[pos]) << 4 | (byte) tmp.indexOf(achar[pos + 1]));
		}
		return result;
	}


	/**
	 * byte数组转成ascii的字符串
	 * @param bt
	 * @return
	 */
	public static String byteArrayToString(byte[] bt)
	{
		StringBuffer  str = new StringBuffer("");
		if (bt == null)
			return "";
		for (int i = 0; i < bt.length; i++)
		{
			str.append(bt[i]);
		}
		return str.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*******************测试*************************/
	
	public static void main(String[] args){
		byte a[]=new byte[]{0x00,0x01,0x06,0x08};
		System.out.println(ByteUtil.byteArrayToInt(a));
		System.out.println(ByteUtil.byteArrayToString(ByteUtil.intToByteArray(ByteUtil.byteArrayToInt(a))));
		System.out.println("==========end=============");
	}
}
