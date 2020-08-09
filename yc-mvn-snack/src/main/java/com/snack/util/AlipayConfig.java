package com.snack.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102600766507";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
	 public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCM9ok/ni67CF3tRvdStFBv4ICokUBMWt5zTpZnKGnRRAySMZBUaZdlXStf4OlmUvixNJd4+btF/u+4+zXlKFmw1QOnJcf+jwUY5lkc2JWwH9TTHiAPgOfWrR4Ialdjy646K5nZvTkGwu9T8IpVCoJFHE04JES2AjXd3V+ygL4HnFjlC5xjKXrPv2WxlHJcTR+MZUsxM8RGmQPNC5o8UWFjaVmGSVe5kfu8jRcS01M7UxYrwNQY854CJwEDsnVepXfBWR5jUGO1iUVpbzXazTh+ilaJAEsKhNEheRvzhcoQoUpwbY1/FzizJaBn8gcU93R0mS2a3xot10wxFJf8UeGDAgMBAAECggEASGClpAaiU+I96AESKGLxEiChruu1qf7WTTpCu4DipFJeagoM42q8E429IaOn86QSkVpBlBoV8CCwEbjauWj4dHVLRN1/TNM+eMR9aPREF4SgkC3dpFg5sd59lzCS4mjIcpHEkyo3R6N7owvU20lkDfw0IeltZk7IPbzW0B6Oc/Ur8MR45DNjo8xp9w6QDQYKvvrREtNdpf1Ph+3CXx++GwclNDF8Iwbd9iIaLD5hQSnbiM/4OEIdFxrQqsOTsfJLfPgJlc9CmUBJZJ2Y0aqL1NSGBSyFFcO2OvIBQcpgUZjdeerut4T34qorDdIzm388ZGMDiCZjFoN+MJfr4GNNEQKBgQDcPKfuDeNbHMZfX4ZfPYjwquRmFLcLHUq6jSc2qP4N7zDSSJMq72bBYK6BFPSKZn9Hs2T0su52HcrkQ3Qisrk0i6j6Thr4HN0wdRWPGYlRql50CKTniqXGUcj5ML0fRfsAEHitN4RzC2xzxA1zMCWYxpMPzlOA3YE5dNpDZCuS3QKBgQCj2m4LmUUHjvEwxpImz+donaF0pDDDnoiYa97FrZI/bLHijdS5nrx/Yj6IP97lEyBN9JD/Re2fWsCBb0+DTcUOfmgD9tUK4hCXGcHpdxB2SOq5yYI/OUP3ZDBmiDUH4UQU8ooDXOxKBGZ3HrPU7U22dzUGuTjooAfE5HEC3qoP3wKBgAE+Tmxno6r2BKCxN17+R/Gj5Vo2L7peVferqFcEqTqG6S2mvz300A2zcAjGh4LohTtwZdeD0xjTnjufrd4sSCNlznRrUHeqIg2N+6stJ7tQTOw2yEeOXtU5zPqrLcYpbPtZHHVN5O+JVvloV/MYiH3OUTI7pfkKRxp98GWFAUH5AoGAYTLpRu4kGei8wT96YZXBY7jml0fzOYgo5apP25osgH1dRNT4FSjKDxLbn32sHt3qsvPas+PqozZ1rhK262BN/lwUHg3lsXALgWchNfqQhGqa+51DK3gwDmiboac5NjR2a6q5HmeYMAxOMGnBKhWfnRc4GIX18WFFWpJdS+OEpKcCgYAvHiLuMibp+tUllD9jPkoEAdTNXpPg4CrEgNpB40hwYk0HsnoKjN9ZNyPrYApyhrUSBAVHBpWXTy5ePkucyedISul9LJjsv/O5NENelOVS9N/sJXXGHwdXElZkHFSvX1m2LGUk3x/wFzY6bG0/iWjdNj4DtF0COoMCeAO2Y4k0yg==";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	 public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjPaJP54uuwhd7Ub3UrRQb+CAqJFATFrec06WZyhp0UQMkjGQVGmXZV0rX+DpZlL4sTSXePm7Rf7vuPs15ShZsNUDpyXH/o8FGOZZHNiVsB/U0x4gD4Dn1q0eCGpXY8uuOiuZ2b05BsLvU/CKVQqCRRxNOCREtgI13d1fsoC+B5xY5QucYyl6z79lsZRyXE0fjGVLMTPERpkDzQuaPFFhY2lZhklXuZH7vI0XEtNTO1MWK8DUGPOeAicBA7J1XqV3wVkeY1BjtYlFaW812s04fopWiQBLCoTRIXkb84XKEKFKcG2Nfxc4syWgZ/IHFPd0dJktmt8aLddMMRSX/FHhgwIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:/com-yc-snack/order.html";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}

