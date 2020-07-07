package com.keepjoy.core.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class FileUtil {

	public static final String CHARSET="utf-8";

	//得到文件的byte数组流
	public static byte[] getByteArrayFromFile(File file) throws Exception{
		ByteArrayOutputStream baos=null;
		InputStream in=null;
		try {
			in=new FileInputStream(file);
			int ch = 0;
			baos = new ByteArrayOutputStream();
			while(in.available()!=0) {
				ch = in.read();
				baos.write(ch);
			}
			return baos.toByteArray();
		}finally{
			if(in!=null)in.close();
			if(baos!=null)baos.close();
		}
	}


	public static void copyFile(File inputFile, File outputFile)
			throws IOException {
		FileInputStream fis = new FileInputStream(inputFile);
		FileOutputStream fos = new FileOutputStream(outputFile);
		int temp = 0;
		try {
			while ((temp = fis.read()) != -1) {
				fos.write(temp);
			}
		}
		finally {
			if(null!=fis)fis.close();
			if(null!=fos)fos.close();
		}
	}

	public static void deleteFile(File file){
		if(null==file)return ;
		if(file.listFiles()!=null && file.listFiles().length>0){
			for(File subFile:file.listFiles()){
				if(subFile.isDirectory()){
					deleteFile(subFile);
				}else{
					subFile.delete();
				}
			}
		}
		file.delete();
	}

	/**
	 * 按全局请求参数编码
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static BufferedReader readBuffered(String filePath) throws IOException{
		return readBuffered(filePath,CHARSET);
	}
	public static BufferedReader readBuffered(File file) throws IOException{
		return readBuffered(file,CHARSET);
	}
	public static BufferedReader readBuffered(String filePath,String charsetName) throws IOException{
		return readBuffered(new File(filePath),charsetName);
	}
	public static BufferedReader readBuffered(File file,String charsetName) throws IOException{
		BufferedReader in=null;
		if(!file.exists()){
			return null;
		}
		in=new BufferedReader(new InputStreamReader(new FileInputStream(file),charsetName));
		return in;
	}

	public static String read(String filePath) throws IOException{
		return read(new File(filePath));
	}

	public static String read(File file) throws IOException{
		StringBuffer sb=new StringBuffer();
		BufferedReader in=null;
		try {
			in=readBuffered(file);
			if(null==in)return null;
			String s;
			while((s=in.readLine())!=null){
				sb.append(s);
			}
		} finally{
			if(null!=in)in.close();
		}
		return sb.toString();
	}

	public static String read(File file,String appendStr) throws IOException{
		StringBuffer sb=new StringBuffer();
		BufferedReader in=null;
		try {
			in=readBuffered(file);
			if(null==in)return null;
			String s;
			while((s=in.readLine())!=null){
				sb.append(s+" "+appendStr);
			}
		} finally{
			if(null!=in)in.close();
		}
		return sb.toString();
	}
	public static String read(String filePath,String appendStr) throws IOException{
		StringBuffer sb=new StringBuffer();
		BufferedReader in=null;
		try {
			in=readBuffered(filePath);
			if(null==in)return null;
			String s;
			while((s=in.readLine())!=null){
				sb.append(s+" "+appendStr);
			}
		} finally{
			if(null!=in)in.close();
		}
		return sb.toString();
	}

	public static void write(String filePath,String content) throws IOException{
		write(new File(filePath),content);
	}

	public static void write(File file,String content) throws IOException{
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(file);
			fos.write(content.getBytes(CHARSET));
		} finally{
			if(fos!=null){
				fos.flush();
				fos.close();
			}
		}
	}

	public static void write(InputStream is,File output) throws IOException{
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(output);
			byte[] buffer = new byte[1024];
			int n = 0;
			while (-1 != (n = is.read(buffer))){
				fos.write(buffer, 0, n);
			}
		} finally{
			if(null!=is)is.close();
			if(null!=fos){
				fos.flush();
				fos.close();
			}
		}
	}

	public static void write(File input,File output) throws IOException{
		write(new FileInputStream(input),output);
	}

	//得到文件的总行数
	public static int getFileLineNum(File file)throws IOException {
		RandomAccessFile raf = null;  
		int lineNum=0;
		try {  
			raf = new RandomAccessFile(file, "r");  
			long len = raf.length();
			if(len<=0)return 0;
			long start = raf.getFilePointer();
			long nextend = start + len - 1;//将初始读取文件的位置设置到最后

			// 将读文件的开始位置移到nextend位置。
			if(nextend>-1)raf.seek(nextend);

			//得到总行数
			int b=raf.read();
			int lineByteNum=0;//一行的字节数
			while(b!=-1){
				if(b == '\n' || b == '\r' || 0==nextend){//碰到回车或者换行符的时候,说明一行读掉了
					if(lineByteNum>1){
						lineNum++;
						lineByteNum=0;
					}else{
						lineByteNum++;
					}
				}else{
					lineByteNum++;
				}
				nextend--;
				if(nextend<0)break;
				raf.seek(nextend);
				b=raf.readByte();
			}

		} finally {  
			if (raf != null) raf.close();  
		}  
		return lineNum;
	}

	/**
	 * 从后往前读取begin到end行文件
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String readBeginToEndLineFromBack(File file,Integer beginLineNum,
			Integer endLineNum,String appendStr) throws IOException {  
		RandomAccessFile raf = null;  
		StringBuffer sb=new StringBuffer("");
		if(beginLineNum==null || endLineNum==null || endLineNum<beginLineNum)return null;
		try {  
			raf = new RandomAccessFile(file, "r");  
			long len = raf.length();
			long start = raf.getFilePointer();
			long nextend = start + len - 1;//将初始读取文件的位置设置到最后

			// 将读文件的开始位置移到nextend位置。
			if(nextend>-1)raf.seek(nextend);
			int count=0;//读取的行数
			//一个字节的读
			int c=raf.read();
			int lineByteNum=0;//一行的字节数
			while(c!=-1){
				if(count==endLineNum)break;
				if(c == '\n' || c == '\r' || nextend==0){//碰到回车或者换行符的时候,说明一行读掉了
					if(lineByteNum>1){//光是回车换行符号的时候不算,所以长度至少要大于1
						byte[] buffer = new byte[lineByteNum];
						raf.read(buffer,0,lineByteNum);
						String line=new String(buffer);
						sb.append(line+appendStr);

						count++;
						nextend=nextend-lineByteNum;
						lineByteNum=0;
					}else{
						nextend--;
						lineByteNum++;
					}
				}else{
					nextend--;
					lineByteNum++;
				}


				if(nextend<0)break;
				raf.seek(nextend);
				c=raf.readByte();
			}

		} finally {  
			if (raf != null) raf.close();  
		}  
		return sb.toString();
	}  


	// 计算文件的 MD5 值
	public static String getFileMD5(File inputFile) throws NoSuchAlgorithmException, IOException {
		// 缓冲区大小（这个可以抽出一个参数）
		int bufferSize = 256 * 1024;
		FileInputStream fileInputStream = null;
		DigestInputStream digestInputStream = null;
		try {
			// 拿到一个MD5转换器（同样，这里可以换成SHA1）
			MessageDigest messageDigest =MessageDigest.getInstance("MD5");
			// 使用DigestInputStream
			fileInputStream = new FileInputStream(inputFile);
			digestInputStream = new DigestInputStream(fileInputStream,messageDigest);
			// read的过程中进行MD5处理，直到读完文件
			byte[] buffer =new byte[bufferSize];
			while (digestInputStream.read(buffer) > 0);
			// 获取最终的MessageDigest
			messageDigest= digestInputStream.getMessageDigest();
			// 拿到结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();
			// 同样，把字节数组转换成字符串
			return ByteUtil.byteArrayToHexString(resultByteArray).toLowerCase();
		} finally {
			if(null!=digestInputStream)digestInputStream.close();
			if(null!=fileInputStream)fileInputStream.close();
		}

	}

}
