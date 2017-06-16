/*
 *
 *  *
 *  *  Copyright (c) 2016, 重庆金窝窝网络科技有限公司.  All Rights Reserved.
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  *  use this file except in compliance with the License. You may obtain a copy of
 *  *  the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  *  License for the specific language governing permissions and limitations under
 *  *  the License.
 *  *
 *
 */

package com.jvv.gateway.integration.synOldSystem.utils;

import com.google.common.base.Preconditions;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

/**
 * Created by IntelliJ IDEA
 * 〈HttpClient连接池〉 <p>
 * 〈HttpClient采用统一调用方式〉
 *
 * @author linxm
 * @date 2017/1/16
 * @time 15:58
 */
public class HttpClient4Utils {

    private static final int MAX_TOTAL = 200;//最大连接数

    private static final int MAX_ROUTE = 20;//每个路由基础的连接数

    private static final int CONNECT_TIMEOUT = 60000;//连接超时毫秒

    private static final int SOCKET_TIMEOUT = 60000;//传输超时毫秒

    private static Logger logger = LoggerFactory.getLogger(HttpClient4Utils.class);

    private static Charset charset = Charset.forName("UTF-8");

    public static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = null;

    private static CloseableHttpClient httpClient = null;

    static {
        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        poolingHttpClientConnectionManager.setMaxTotal(MAX_TOTAL);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(MAX_ROUTE);
        // 可用空闲连接过期时间,重用空闲连接时会先检查是否空闲时间超过这个时间，如果超过，释放socket重新建立
        poolingHttpClientConnectionManager.setValidateAfterInactivity(30000);

        httpClient = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager)
                .setDefaultRequestConfig(setRequestConfig())
                .setRetryHandler(getRetryHandler()).build();
        if (poolingHttpClientConnectionManager != null && poolingHttpClientConnectionManager.getTotalStats() != null) {
            logger.info("now client pool " + poolingHttpClientConnectionManager.getTotalStats().toString());
        }
    }

    /**
     * 超时设置
     * @return
     */
    private static RequestConfig setRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();
    }

    /**
     * 重试机制
     * @return
     */
    private static HttpRequestRetryHandler getRetryHandler() {
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {

            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 3) {
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return true;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// ssl握手异常
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
        return httpRequestRetryHandler;
    }

    public static <T> T get(String url) {

        logger.info("GET请求地址：{}", url);
        Preconditions.checkNotNull(url, "get request url can not be null");

        CloseableHttpResponse response = null;
        InputStream in = null;
        try {

            HttpGet httpGet = new HttpGet(url);

            response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return (T) EntityUtils.toString(entity);
            }
        } catch (UnknownHostException e) {
            logger.error("请求地址{}不存在", url);
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.error("请求地址{}超时", url);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("请求地址{}错误", url);
            e.printStackTrace();
        } finally {
            colseResp(response,in);
        }
        return null;
    }

    public static <T> T post(String url, Map<String, String> params) {

        logger.info("POST请求地址：{}", url);
        Preconditions.checkNotNull(url, "post request url can not be null");

        List<NameValuePair> pairList = new ArrayList<NameValuePair>();

        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                pairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        CloseableHttpResponse response = null;
        InputStream in = null;
        try {

            HttpPost httppost = new HttpPost(url);
            httppost.setConfig(setRequestConfig());

            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(pairList, charset);
            httppost.setEntity(uefEntity);

            response = httpClient.execute(httppost);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                in = entity.getContent();
                return (T) IOUtils.toString(in, charset.name());
            }
        } catch (UnknownHostException e) {
            logger.error("请求地址{}不存在", url);
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            logger.error("请求地址{}超时", url);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("请求地址{}错误", url);
            e.printStackTrace();
        } finally {
            colseResp(response,in);
        }
        return null;
    }

    private static void colseResp(CloseableHttpResponse response,InputStream in) {
        try {
            if (response != null) {
                response.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(HttpClient4Utils.post("http://192.168.1.8:9999/m/login", null));
    }
}
