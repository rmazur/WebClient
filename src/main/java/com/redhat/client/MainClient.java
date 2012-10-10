package com.redhat.client;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;


public class MainClient {

	public static void main(String args[]) {
		executeHttpGet();
	}

	private static void executeHttpGet() {
		try {
			
			String url = "http://localhost:8080/SpringMVC/jobLauncher.htm";
		
			HttpHost httpHost = new HttpHost("localhost", 8080);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			UsernamePasswordCredentials userNamePassowrdCredentials = new UsernamePasswordCredentials("admin", "admin");
			AuthScope authScope = new AuthScope(httpHost);
			httpClient.getCredentialsProvider().setCredentials(authScope, userNamePassowrdCredentials);
			
			BasicAuthCache basicAuthCache = new BasicAuthCache();
			BasicScheme basicScheme = new BasicScheme();
			basicAuthCache.put(httpHost, basicScheme);
			
			BasicHttpContext basicHttpContext = new BasicHttpContext();
			basicHttpContext.setAttribute(ClientContext.AUTH_CACHE, basicAuthCache);
			
			HttpGet httpGet = new HttpGet(url);
			
			HttpResponse httpResponse = httpClient.execute(httpHost, httpGet, basicHttpContext);
			System.out.println("Status = "+httpResponse.getStatusLine());
			httpClient.getConnectionManager().shutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
