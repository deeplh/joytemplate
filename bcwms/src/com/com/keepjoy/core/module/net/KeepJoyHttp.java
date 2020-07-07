package com.keepjoy.core.module.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSONArray;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.core.util.JarResourceUtil;
import io.netty.channel.ConnectTimeoutException;
import org.apache.commons.lang.StringUtils;

public class KeepJoyHttp extends KeepJoyNet {
	public static final String NET_HTTP_KEY="http";


	private Integer connectTimeout;
	private Integer readTimeout;
	private int responseCode;
	private Boolean isHttps;

	public KeepJoyHttp(){
		super(null, 0, null, null);
		this.isHttps=false;
	}

	public KeepJoyHttp(Integer connectTimeout, Integer readTimeout){
		super(null, 0, null, null);
		this.connectTimeout=connectTimeout;
		this.readTimeout=readTimeout;
	}

	public KeepJoyHttp(Boolean isHttps){
		super(null, 0, null, null);
		this.isHttps=isHttps;
	}




	public String sendGet(String url,String param,String charset) throws IOException, KeyManagementException, NoSuchAlgorithmException{
		String result = "";
		HttpURLConnection conn=null;
		try{
			if(StringUtils.isNotEmpty(param)){
				url=url+ "?" + param;
			}
			URL realUrl = new URL(url);//打开和URL之间的连接
			
			if(isHttps){
				conn = getHttpsConnection(realUrl);
			}else{
				conn = (HttpURLConnection) realUrl.openConnection();//设置通用的请求属性
			}
			
			
			conn.setRequestProperty("accept", "*/*"); 
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("charset", "charset="+charset); 
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"); //建立实际的连接
			if(null!=connectTimeout)conn.setConnectTimeout(connectTimeout);
			if(null!=readTimeout)conn.setReadTimeout(readTimeout);
			conn.connect(); //获取所有响应头字段

		} catch (ConnectTimeoutException e) {
			throw new KeepJoyServiceException("http连接超时",504);
		}
		try{
			this.responseCode=conn.getResponseCode();

			//定义BufferedReader输入流来读取URL的响应
			result=JarResourceUtil.read(conn.getInputStream(), charset);
		}
		catch(SocketTimeoutException e){
			throw new KeepJoyServiceException("http读超时",504);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String sendPost(String url,Object paramObj,String charset) throws IOException, NoSuchAlgorithmException, KeyManagementException{

		OutputStream out = null;
		String result = null;
		//打开和URL之间的连接
		HttpURLConnection conn=null;
		try {
			log.debug("url:"+url+",param:"+JSONArray.toJSONString(paramObj));
			URL realUrl = new URL(url);

			if(isHttps){
				conn = getHttpsConnection(realUrl);
			}else{
				conn = (HttpURLConnection) realUrl.openConnection();
			}

			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			//设置通用的请求属性
			conn.setRequestProperty("accept", "*/*"); 
			conn.setRequestProperty("connection", "Keep-Alive"); 
			conn.setRequestProperty("charset", "charset="+charset); 
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"); 
			if(null!=connectTimeout)conn.setConnectTimeout(connectTimeout);
			if(null!=readTimeout)conn.setReadTimeout(readTimeout);
			conn.setDoOutput(true);
			conn.setDoInput(true);

			//获取URLConnection对象对应的输出流
			out = conn.getOutputStream();
			//发送请求参数
			if(null!=paramObj){
				String paramStr=null;
				if(paramObj instanceof Map){
					Map<String,Object> param=(Map<String, Object>) paramObj;
					StringBuffer strBuf=new StringBuffer("");
					for(String key:param.keySet()){
						if(null!=param.get(key)){
							strBuf.append(key+"="+param.get(key)+"&");
						}
					}
					paramStr=strBuf.toString();
					if (paramStr.length()> 0){
						paramStr = paramStr.substring(0, paramStr.length() - 1);
					}
				}else{
					paramStr=paramObj.toString();
				}
				if(StringUtils.isNotEmpty(charset)){
					out.write(paramStr.getBytes(charset));
				}else{
					out.write(paramStr.getBytes());
				}
			}

		} catch (ConnectTimeoutException e) {
			throw new KeepJoyServiceException("http连接超时",504);
		} finally{
//			log.debug("url:"+url+",param:"+JtJSONArray.toJSONString(paramObj));
			if(null!=out){
				out.flush();
				out.close();
			}
		}
		try {
			this.responseCode=conn.getResponseCode();

			//定义BufferedReader输入流来读取URL的响应
			result=JarResourceUtil.read(conn.getInputStream(), charset);
		} catch(SocketTimeoutException e){
			throw new KeepJoyServiceException("http读超时",504);
		} 
		return result;
	}





	public HttpsURLConnection getHttpsConnection(URL realUrl) throws IOException, NoSuchAlgorithmException, KeyManagementException{
		SSLContext ctx  = SSLContext.getInstance("TLS");
		ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
		SSLSocketFactory ssf = ctx.getSocketFactory();

		HttpsURLConnection httpsConn = (HttpsURLConnection) realUrl.openConnection();
		httpsConn.setSSLSocketFactory(ssf);
		httpsConn.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});
		return httpsConn;

	}


	private static class DefaultTrustManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

	public int getResponseCode() {
		return responseCode;
	}



	/**************test
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException ************/
//	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, IOException{
//		//https://ibsbjstar.ccb.com.cn/CCBIS/B2CMainPlat_09_EPAY?REMARK1=&REMARK2=&BRANCHID=530000000&TXCODE=530550&CCB_IBSVersion=V6&CURCODE=01&MERCHANTID=105910200321111&RETURNTYPE=3&ORDERID=10591020032111101&POSID=013327985&PAYMENT=0.01&MAC=424c6ccf811e09573d6779d125b1ea7a&TIMEOUT=&QRCODE=1&CHANNEL=1
//		KeepJoyHttp https=new KeepJoyHttp(true);
//		System.out.println(https.sendGet("https://ibsbjstar.ccb.com.cn/CCBIS/B2CMainPlat_09_EPAY?REMARK1=&REMARK2=&BRANCHID=530000000&TXCODE=530550&CCB_IBSVersion=V6&CURCODE=01&MERCHANTID=105910200321111&RETURNTYPE=3&ORDERID=10591020032111101&POSID=013327985&PAYMENT=0.01&MAC=424c6ccf811e09573d6779d125b1ea7a&TIMEOUT=&QRCODE=1&CHANNEL=1", null, "utf-8"));
//	}


}
