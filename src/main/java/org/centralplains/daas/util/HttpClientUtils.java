package org.centralplains.daas.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpClientUtils {

    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;


    static {
        sslsf = getSSLSocketFactory();
        Registry socketFactoryRegistry = RegistryBuilder.create().
                register("https", sslsf).register("http", PlainConnectionSocketFactory.INSTANCE).build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(2000);
        cm.setDefaultMaxPerRoute(2000);
    }

    private static RequestConfig defaultRequestConfig() {
        return RequestConfig.custom().setConnectTimeout(15000).setConnectionRequestTimeout(15000).setSocketTimeout(15000).build();
    }

    private static CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig()).setConnectionManager(cm).setConnectionManagerShared(true).build();
    }

    private static CloseableHttpClient getSSLClient() {
        return HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(defaultRequestConfig()).setConnectionManager(cm).setConnectionManagerShared(true).build();
    }

    private static SSLConnectionSocketFactory getSSLSocketFactory() {
        try {
            // 在调用SSL之前需要重写验证方法，取消检测SSL
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String str) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String str) {
                }
            };
            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[]{trustManager}, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
            return socketFactory;
//            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                    .register("http", PlainConnectionSocketFactory.INSTANCE)
//                    .register("https", socketFactory).build();
//            // 创建ConnectionManager，添加Connection配置信息
////            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//            CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(cm)
//                    .setDefaultRequestConfig(defaultRequestConfig()).build();
//            return closeableHttpClient;
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }


    public static String get(String url) {
        return get(url, (Map) null);
    }

    public static String get(String url, Map<String, Object> paramsMap) {
        return get(url, paramsMap, (Header[]) null, null);
    }

    public static String get(String url, Map<String, Object> paramsMap, Header[] headers) {
        return response(url, paramsMap, headers, "GET", null);
    }

    public static String get(String url, Map<String, Object> paramsMap, Header[] headers, RequestConfig config) {
        return response(url, paramsMap, headers, "GET", config);
    }

    public static String post(String url, Object params) {
        return post(url, params, (Header[]) null, null);
    }

    public static String post(String url, Object params, Header[] headers) {
        return response(url, params, headers, "POST", null);
    }

    public static String post(String url, Object params, Header[] headers, RequestConfig config) {
        return response(url, params, headers, "POST", config);
    }

    public static String put(String url, Object params) {
        return put(url, params, (Header[]) null, null);
    }

    public static String put(String url, Object params, Header[] headers) {
        return response(url, params, headers, "PUT", null);
    }

    public static String put(String url, Object params, Header[] headers, RequestConfig config) {
        return response(url, params, headers, "PUT", config);
    }

    public static String delete(String url) {
        return put(url, (Header[]) null, null);
    }

    public static String delete(String url, Header[] headers) {
        return response(url, null, headers, "DELETE", null);
    }

    public static String delete(String url, Header[] headers, RequestConfig config) {
        return response(url, null, headers, "DELETE", config);
    }


    public static String response(String url, Object params, Header[] headers, String sendType, RequestConfig config) {
        if ("GET".equals(sendType)) {
            return getResponse(url, params, headers, config);
        } else if ("POST".equals(sendType)) {
            return postResponse(url, params, headers, config);
        } else if ("PUT".equals(sendType)) {
            return putResponse(url, params, headers, config);
        } else if ("DELETE".equals(sendType)) {
            return deleteResponse(url, headers, config);
        }
        return null;
    }

    public static String getResponse(String url, Object params, Header[] headers, RequestConfig config) {
        CloseableHttpClient httpClient = wrapClient(url);
        ;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            if (null != config) {
                httpGet.setConfig(config);
            }
            if (null != headers) {
                httpGet.setHeaders(headers);
            }
            if (params != null) {
                if (params instanceof Map) {
                    Map<String, Object> paramsMap = (Map) params;
                    if (paramsMap != null) {
                        List<NameValuePair> paramsList = initParams(paramsMap);
                        httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + EntityUtils.toString(new UrlEncodedFormEntity(paramsList, Charset.forName("UTF8")))));
                    }
                }
            }
            response = httpClient.execute(httpGet);// 执行提交
            int code = response.getStatusLine().getStatusCode();
            if (200 != code) {
                return null;
            }
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (URISyntaxException cpex) {
            cpex.printStackTrace();
            return null;
        } catch (IOException ioex) {
            ioex.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(response);
            IOUtils.closeQuietly(httpClient);
        }
        return result;
    }

    public static CloseableHttpClient wrapClient(String url) {
        if (url.startsWith("https://")) {
            return getSSLClient();
        }
        return getHttpClient();
    }


    public static String postResponse(String url, Object params, Header[] headers, RequestConfig config) {
        CloseableHttpClient httpClient = wrapClient(url);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            if (null != config) {
                httpPost.setConfig(config);
            }
            if (null != headers) {
                httpPost.setHeaders(headers);
            }
            if (params != null) {
                HttpEntity code = checkParams(params);
                if (code != null) {
                    httpPost.setEntity(code);
                }
            }
            response = httpClient.execute(httpPost);// 执行提交
            int code = response.getStatusLine().getStatusCode();
            if (200 != code) {
                return null;
            }
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (IOException ioex) {
            ioex.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(response);
            IOUtils.closeQuietly(httpClient);
        }
        return result;
    }


    public static String putResponse(String url, Object params, Header[] headers, RequestConfig config) {
        CloseableHttpClient httpClient = wrapClient(url);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            HttpPut httpPut = new HttpPut(url);
            if (null != config) {
                httpPut.setConfig(config);
            }
            if (null != headers) {
                httpPut.setHeaders(headers);
            }
            if (params != null) {
                HttpEntity code = checkParams(params);
                if (code != null) {
                    httpPut.setEntity(code);
                }
            }
            response = httpClient.execute(httpPut);// 执行提交
            int code = response.getStatusLine().getStatusCode();
            if (200 != code) {
                return null;
            }
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (ClientProtocolException cpex) {
            cpex.printStackTrace();
            return null;
        } catch (IOException ioex) {
            ioex.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(response);
            IOUtils.closeQuietly(httpClient);
        }
        return result;
    }

    public static String deleteResponse(String url, Header[] headers, RequestConfig config) {
        CloseableHttpClient httpClient = wrapClient(url);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            HttpDelete httpDelete = new HttpDelete(url);
            if (null != config) {
                httpDelete.setConfig(config);
            }
            if (null != headers) {
                httpDelete.setHeaders(headers);
            }
            response = httpClient.execute(httpDelete);// 执行提交
            int code = response.getStatusLine().getStatusCode();
            if (200 != code) {
                return null;
            }
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (ClientProtocolException cpex) {
            cpex.printStackTrace();
            return null;
        } catch (IOException ioex) {
            ioex.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(response);
            IOUtils.closeQuietly(httpClient);
        }
        return result;
    }

    public static String fileResponse(String url, MultipartFile file, Header[] headers, RequestConfig config) {
        List<MultipartFile> list = new ArrayList<>();
        list.add(file);
        return fileResponse(url, list, headers, config);
    }


    public static String fileResponse(String url, List<MultipartFile> files, Header[] headers, RequestConfig config) {
        CloseableHttpClient httpClient = wrapClient(url);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            if (null != config) {
                httpPost.setConfig(config);
            }
            if (null != headers) {
                httpPost.setHeaders(headers);
            }
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            files.forEach(file -> {
                String fileName = file.getOriginalFilename();
                try {
                    builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
                    builder.addTextBody("filename", fileName);// 类似浏览器表单提交，对应input的name和value
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);// 执行提交
            int code = response.getStatusLine().getStatusCode();
            if (200 != code) {
                return null;
            }
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (ClientProtocolException cpex) {
            cpex.printStackTrace();
            return null;
        } catch (IOException ioex) {
            ioex.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(response);
            IOUtils.closeQuietly(httpClient);
        }
        return result;
    }


    private static HttpEntity checkParams(Object params) {
        HttpEntity code = null;
        if (params instanceof Map) {
            Map mapResult = (Map) params;
            code = new UrlEncodedFormEntity(initParams(mapResult), Consts.UTF_8);
        } else if (params instanceof String) {
            String strResult = (String) params;
            code = new StringEntity(strResult, ContentType.APPLICATION_JSON);
        }
        return code;
    }


    private static List<NameValuePair> initParams(Map<String, Object> paramsMap) {
        ArrayList<NameValuePair> params = new ArrayList<>();
        if (paramsMap == null) {
            return params;
        }
        paramsMap.forEach((key, value) ->
                params.add(new BasicNameValuePair(key, Objects.toString(value))));
        return params;
    }
}
