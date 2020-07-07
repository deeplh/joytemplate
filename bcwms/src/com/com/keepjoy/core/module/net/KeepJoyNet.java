package com.keepjoy.core.module.net;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public abstract class KeepJoyNet {
	public static final int NET_BUFFSIZE=1024*1024*5;//默认缓冲区5m
	public static final int NET_CONNECT_TIMEOUT=1000*30;//默认连接超时时间30s
	public static final int NET_GET_DATA_TIMEOUT=1000*60*10;//默认得到数据超时时间10分钟

	protected static final Log log = LogFactory.getLog(KeepJoyNet.class);

	protected String host;
	protected Integer port; 
	protected String username;
	protected String password;

	public KeepJoyNet(String host, Integer port, String username, String password){
		this.host=host;
		this.port=port;
		this.username=username;
		this.password=password;
	}

//	public abstract void connect()throws Exception;
//	public abstract void disconnect()throws Exception;

	/**
	 * 文件上传
	 * @param localFilePath -当为文件夹的时候必须以/结尾
	 * @param remoteFileFolder -必须以/结尾
	 * @param bufSize
	 * @return
	 */
//	public abstract boolean file(String localFilePath,String remoteFileFolder,Integer bufSize);

	/**
	 * 文件下载
	 * @param localFileFolder -必须以/结尾
	 * @param remoteFilePath -当为文件夹的时候必须以/结尾
	 * @param bufSize
	 * @return
	 */
//	public abstract boolean download(String localFileFolder,String remoteFilePath,Integer bufSize);

	/**
	 * 文件删除
	 * @param remoteFilePath -可以为文件夹或者文件
	 * @throws Exception
	 */
//	public abstract void remove(String remoteFilePath) throws Exception;



	
}
