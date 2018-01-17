package com.tuyue.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import static java.lang.System.out;

/**
 * 通过短信接口发送短信
 */
public class SmsUtil {
	
	
	public static void main(String [] args) {
		
		sendSms2("18629285275","您的验证码是：1111。请不要把验证码泄露给其他人。");
		//sendSmsAll(List<PageData> list)
		
		//sendSms1();
	}
	
	
	

	 //短信商 一  http://www.dxton.com/ =====================================================================================
	/**
	 * 给一个人发送单条短信
	 * @param mobile 手机号
	 * @param code  短信内容
	 */
 	public static void sendSms1(String mobile,String code){
 		
	    String account = "", password = "";
	    String strSMS1 = Tools.readTxtFile(Const.SMS1);			//读取短信1配置
		if(null != strSMS1 && !"".equals(strSMS1)){
			String strS1[] = strSMS1.split(",fh,");
			if(strS1.length == 2){
				account = strS1[0];
				password = strS1[1];
			}
		}
 		String PostData = "";
		try {
			PostData = "account="+account+"&password="+password+"&mobile="+mobile+"&content="+URLEncoder.encode(code,"utf-8");
		} catch (UnsupportedEncodingException e) {
			out.println("短信提交失败");
		}
		 //System.out.println(PostData);
 	     String ret = SMS(PostData, "http://sms.106jiekou.com/utf8/sms.aspx");
 	     out.println(ret);
 	   /*  
 	   100			发送成功
 	   101			验证失败
 	   102			手机号码格式不正确
 	   103			会员级别不够
 	   104			内容未审核
 	   105			内容过多
 	   106			账户余额不足
 	   107			Ip受限
 	   108			手机号码发送太频繁，请换号或隔天再发
 	   109			帐号被锁定
 	   110			发送通道不正确
 	   111			当前时间段禁止短信发送
 	   120			系统升级
		*/
 	     
	}
	
	 public static String SMS(String postData, String postUrl) {
	        try {
	            //发送POST请求
	            URL url = new URL(postUrl);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            conn.setRequestProperty("Connection", "Keep-Alive");
	            conn.setUseCaches(false);
	            conn.setDoOutput(true);

	            conn.setRequestProperty("Content-Length", "" + postData.length());
	            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	            out.write(postData);
	            out.flush();
	            out.close();

	            //获取响应状态
	            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	                return "";
	            }
	            //获取响应内容体
	            String line, result = "";
	            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	            while ((line = in.readLine()) != null) {
	                result += line + "\n";
	            }
	            in.close();
	            return result;
	        } catch (IOException e) {
	            e.printStackTrace(out);
	        }
	        return "";
	    }
	 //===================================================================================================================
	 
	 
	/**
	 * 
	 * 短信商 二  http://www.ihuyi.com/ =====================================================================================
	 * 
	 */
	private static String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";
	
	
	
	/**
	 * 给一个人发送单条短信
	 * @param mobile 手机号
	 * @param code  短信内容
	 */
	public static void sendSms3(String mobile,String code){
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);
			
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

	    String content = new String(code);  
	    
	    String account = "", password = "";
	    String strSMS2 = Tools.readTxtFile(Const.SMS2);			//读取短信2配置
		if(null != strSMS2 && !"".equals(strSMS2)){
			String strS2[] = strSMS2.split(",fh,");
			if(strS2.length == 2){
				account = strS2[0];
				password = strS2[1];
			}
		}
	    
		NameValuePair[] data = {//提交短信
		    new NameValuePair("account", account), 
		    new NameValuePair("password", password), 			//密码可以使用明文密码或使用32位MD5加密
		    new NameValuePair("mobile", mobile), 
		    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);
		
		try {
			client.executeMethod(method);
			
			String SubmitResult =method.getResponseBodyAsString();
					
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();


			code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");
			
			
			out.println(code);
			out.println(msg);
			out.println(smsid);
			
			if(code == "2"){
				out.println("短信提交成功");
			}
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}	
		
	}

	/**
	 * 给一个人发送单条短信
	 * @param mobile 手机号
	 * @param code  短信内容
	 */
	public static void sendSms2(String mobile,String code){
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);

		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

		String content = new String(code);

		String account = "", password = "";
		String strSMS2 = Tools.readTxtFile(Const.SMS2);			//读取短信2配置
		if(null != strSMS2 && !"".equals(strSMS2)){
			String strS2[] = strSMS2.split(",fh,");
			if(strS2.length == 2){
				account = strS2[0];
				password = strS2[1];
			}
		}

		NameValuePair[] data = {//提交短信
				new NameValuePair("account", account),
				new NameValuePair("password", password), 			//密码可以使用明文密码或使用32位MD5加密
				new NameValuePair("mobile", mobile),
				new NameValuePair("content", content),
		};

		method.setRequestBody(data);

		try {
			client.executeMethod(method);

			String SubmitResult =method.getResponseBodyAsString();

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();


			System.out.print(root.toString());

			String responseCode = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");


			out.println(code);
			out.println(msg);
			out.println(smsid);

			if(code == "2"){
				out.println("短信提交成功");
			}

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 给一个人发送单条短信验证码
	 * @param mobile 手机号
	 * @param session  前台session
     * 返回短信码
     * 0	提交失败
        2	提交成功
        400	非法ip访问
        401	帐号不能为空
        402	密码不能为空
        403	手机号码不能为空
        4030	手机号码已被列入黑名单
        404	语音通知内容不能为空
        405	用户名或密码不正确
        4050	账号被冻结
        4051	剩余条数不足
        4052	访问ip与备案ip不符
        406	手机格式不正确
        4071	没有提交备案模板
        4072	你提交过来语音内容必须与报备过的模板格式相匹配
        40722	变量内容超过指的的长度
        4073	短信内容超出长度限制
        408	您的帐户疑被恶意利用，已被自动冻结，如有疑问请与客服联系。
	 */
	public static String sendVerificationCode(String mobile,HttpSession session) {

		//随机生成验证码
		int randomNum = Tools.getRandomNum();
		String code = "您的验证码是："+String.valueOf(randomNum)+"。请不要把验证码泄露给其他人。";
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

        String content = new String(code);

        String account = "", password = "";
        String strSMS2 = Tools.readTxtFile(Const.SMS2);            //读取短信2配置
        if (null != strSMS2 && !"".equals(strSMS2)) {
            String strS2[] = strSMS2.split(",fh,");
            if (strS2.length == 2) {
                account = strS2[0];
                password = strS2[1];
            }
        }

        NameValuePair[] data = {//提交短信
                new NameValuePair("account", account),
                new NameValuePair("password", password),            //密码可以使用明文密码或使用32位MD5加密
                new NameValuePair("mobile", mobile),
                new NameValuePair("content", content),
        };

        method.setRequestBody(data);

        String responseCode = "0";
        try {
            client.executeMethod(method);

            String SubmitResult = method.getResponseBodyAsString();

            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();


            System.out.print(root.toString());

            responseCode = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");


            out.println(code);
            out.println(msg);
            out.println(smsid);

            if (code == "2") {
                out.println("短信提交成功");
            }

            //将验证码存储在session中
            session.setAttribute("'"+mobile+"'",randomNum);

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return responseCode;
    }
	/**
	 * @Author: 王晨
	 * @Description:验证验证码是否正确
	 * @param: session ,phone 手机号，code 输入的验证码。
	 * @return:1 验证成功 2 验证码过期 3 验证码不正确
	 * @Date: 13:50 2017/9/22
	 */
    public static String verificationCode(HttpSession session,String phone,String code){
		String checkcode = String.valueOf(session.getAttribute("'"+phone+"'"));
		if(checkcode!=null&&!"".equals(checkcode)){
			if(checkcode.equals(code)){
				//session.removeAttribute("'"+phone+"'");
				return "1";//验证成功
			}else{
				return  "3";//验证码不正确
			}
		}else{
			return "2";//验证码过期
		}
	}


	/**
	 * 给多个人发送单条短信
	 * @param list 手机号验证码
	 */
	public static void sendSmsAll(List<PageData> list){
		String code;
		String mobile;
		for(int i=0;i<list.size();i++){
			code=list.get(i).get("code").toString();
			mobile=list.get(i).get("mobile").toString();
			sendSms2(mobile,code);
		}
	}
	// =================================================================================================


	
}

