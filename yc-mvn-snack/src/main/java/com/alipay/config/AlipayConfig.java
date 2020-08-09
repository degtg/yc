package com.alipay.config;

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
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWnYSArU+wtju4Iz3/yPj2I3iAMfdbYNWoFj46MQ9a3YfB8SpBKsK9/n1+T2bTylD7ID5sIy3ZarYbr04yy+awXE7dnpM0vpGVlQen9z2Fcvyx1eESSU32nUnr69z6NJ12HQhCTkw82S9kIrJ/ZgEwGnFz4urKzFkAn1eni1xo2nYPpZK1RmQHvl631DEz5oYtoQKMukH28VAcYp4QkAdCLNFBVrvzq6EiOaYQxJcAk/bmBY4jqzFe6atwpBOckdfJ/XDxGgt9bMLPExg7P7O+PsqFKmYKZArLwv1GQ1PZmg6u8N148AMXz4w9m78MKd8ANItiXey6y5DeJsPaSbwnAgMBAAECggEAfFl4eDSewqryC0kteu0Wf0Exz8VxD8SXIbubjLfMkZys5PaTiFIhjnN6cLpljx5OVcd9QhKZXBO6XokR6uZlg93yTw8JQlbEI9dzBr4d0PvqnXiT5mEXOTELQvjs7LZb+pRlqTizwNkfQ3FSJdS1zl1GpgMHDoBASz9DQ1TP6tCOKjASR0XfWbd3ydOl5c/wwuVGiu/FJ9C0fx0iBHD37iDLEzLyFCj+YTMegNYh7q5VYqLEUDlgxC+G6sz9XG69GbFfn7E+0s5KVFA0UXbuCSjHzrRqJ2iX6/TVg2rMwXYF2ioVmJWF8BD33DQuETgXnFJ0vuwE015c2Jpf7k0KYQKBgQDQqQ5TjSoWoSO5PnzM+LlVnJCub1yFDqzzivo1CqUPEvclgpsnj/kXWdUzENZ+4anAzTB95XvQs+KL8AtSljsTI33c42ItIDsBDDip1ibrtLAhmWzW0WxRCxMaDbiVtXY1BJryF5L2fyVVF8Nfb3MGwj5mszhWZCr3E+pQMf+g5QKBgQC4yTfJRnc5oEFtILe9lrCOQGBbJ2KyJA/EaTQFKrGzSEgxYBQkpBDstoBFQghPszEflygcn+zevWJAcJ/Uy3ZigLF1bX5+o/zpn8EMtunjKBbNcuI2nbFsBk8Y/GV/Tv4Fry5FwQi51E2YpPvimuzQsF95tUs7AjmlqTJTBvx0GwKBgCrlZlWU4r8uN2rgxsVYqLlJ65CAwlN0VefnfVKkMpSrwHVMqVrzxTCgviy2XHJPMBe71yk6oT6hjIRaP+mlitV/aWOct1UxmA+6FHgN14iDZ+YwZWiwOwVaMn4brgPQ/1Ec9kGoyQS11NXugbK/9bTBABUa6Q0RoKeUx4nfDX9lAoGBAJWYUNFheqPsWQs/sYCKveRruZYSDJSrHRilErgcGEBMBCX04lnHeOwp0y4+hvv+1YX9mTuTPPAV4bTJeJuNHAoipYerXPypRdbcvizM6Aw+KDerCfetMjWB5gcUThcCnCN97rgJzkai2S76YfDR+2X3Y1PksULUSlcS4urUfQyBAoGAUqBXn2UQMrSFYoVn8F3yJmZ5fybVUlx91IDuRdWDfYcVlj3DjPoz4CxVmsL5UR3cow1uiU5sEWrHis3yCz0DeSfvBDPm1Oam+XMGmfcDr2jUcyW1uFLIMsC9Ymjdz/pJCLf5j3Znmh9XxmrCOLWN+S7F/AudbupLqdvn4Bx8cDI=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtLAyuZcBxkQ7fKy60ub0AR/G4+ybBUMOvHVkHOMAhSe+FEp7nOFYno509FVeKjQ0H7ejjJhwj+K69rp5HPqErK+BnSBDJSI+6V/r6kpv+NvfI0+VRpRLzhDiRquGSM3Jig6A9C7TkJqABzzt5As+yowt0x2sX9Evc+VeDVePT0N9wgJAhVf5yCkPvgR1nIWqUkHKCjVT2FfyAuaFV0/6B5BLQXU0/C747w4pDtCRqvj+ed4yYI302xAj31ZU7eL2BV/Mvt9PSVaffbZ28yRyjh/Wq86Kke7TMs4rQvwcaS4MzTzwx1yHirUliz2fDGdk5dYdipzrMBCR4FKObkckCwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://39.99.211.96:8080/com-yc-snack/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://39.99.211.96:8080/com-yc-snack/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:\\";


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

