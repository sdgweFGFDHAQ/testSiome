package testSomthing.spi;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SendWeChatMessage {
    //发送消息的类型
    private final static String MSGTYPE = "text";
    //将消息发送给所有成员
    private final static String TOPARTY = "@all";
    //获取企业微信的企业号，根据不同企业更改
    private final static String CORPID = "wwb980da0adf5e8a44";
    //获取企业应用的密钥，根据不同应用更改
    private final static String CORPSECRET = "sDSJHl7UDYMeArh0swhNfjeZEpwztJeVM_GkDIpc2G8";
    //获取访问权限码URL
    private final static String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    //创建会话请求URL
    private final static String CREATE_SESSION_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";

    //获取access_token
    public static AccessToken getAccessToken() {

        AccessToken token = new AccessToken();

        //访问微信服务器
        String url = ACCESS_TOKEN_URL + "?corpid=" + CORPID + "&corpsecret=" + CORPSECRET;
        try {
            URL getUrl = new URL(url);
            //开启连接，并返回一个URLConnection对象
            HttpURLConnection http = (HttpURLConnection) getUrl.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            //将URL连接用于输入和输出，一般输入默认为true，输出默认为false
            http.setDoOutput(true);
            http.setDoInput(true);
            //进行连接，不返回对象
            http.connect();

            //获得输入内容,并将其存储在缓存区
            InputStream inputStream = http.getInputStream();
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            //将内容转化为JSON代码
            String message = new String(buffer, "UTF-8");
            JSONObject json = JSONObject.parseObject(message);
            System.out.println("--输入内容的json对象:" + json);

            //提取内容，放入对象
            token.setAccess_token(json.getString("access_token"));
            token.setExpires_in(new Integer(json.getString("expires_in")));
            System.out.println("--输入内容的token对象:" + token);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回access_token码
        return token;
    }

    /**
     * 企业接口向下属关注用户发送给微信消息
     *
     * @param toUser  成员ID列表
     * @param toParty 部门ID列表
     * @param toTag   标签ID列表
     * @param content 消息内容
     * @param safe    是否保密
     * @return
     */
    @SuppressWarnings("deprecation")
    public void sendWeChatMessage(String toUser, String toParty, String toTag, String content, String safe) {
        //从对象中提取凭证
        AccessToken accessToken = getAccessToken();
        String ACCESS_TOKEN = accessToken.getAccess_token();
        //请求串
        String url = CREATE_SESSION_URL + ACCESS_TOKEN;
        System.out.println("--请求的url:" + url);

        //封装发送消息请求JSON
        String json = "{" +
                "\"touser\":" + "\"" + toUser + "\"," +
                "\"toparty\":" + "\"" + toParty + "\"," +
                "\"totag\":" + "\"" + toTag + "\"," +
                "\"msgtype\":" + "\"" + MSGTYPE + "\"," +
                "\"text\":" + "{" +
                "\"content\":" + "\"" + content + "\"" +
                "}" +
                ",\"safe\":" + "\"" + safe + "\"," +
                "\"agentid\":" + "\"" + "1000002" + "\"," +
                "\"debug\":" + "\"" + "1" + "\"" +
                "}";
        System.out.println("--拼接的json内容:" + json);

        try {
            URL postUrl = new URL(url);
            HttpURLConnection http = (HttpURLConnection) postUrl.openConnection();
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            http.setDoOutput(true);
            http.setDoInput(true);
            // 连接超时30秒
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
            // 读取超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");
            http.connect();

            //写入内容
            OutputStream outputStream = http.getOutputStream();
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            InputStream inputStream = http.getInputStream();
            int size = inputStream.available();
            byte[] jsonBytes = new byte[size];
            inputStream.read(jsonBytes);
            String result = new String(jsonBytes, StandardCharsets.UTF_8);
            System.out.println("--请求返回的结果result:" + result);

            //清空输出流
            outputStream.flush();
            //关闭输出通道
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SendWeChatMessage weChat = new SendWeChatMessage();
        weChat.sendWeChatMessage("", TOPARTY, "", "内容文本", "0");
    }

}
