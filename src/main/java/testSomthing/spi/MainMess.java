package testSomthing.spi;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MainMess {
    public static final String WEBHOOK_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=5d197d34-5e1d-4c9d-9dc5-107794ed8d31";

    public String sendText(String content) throws IOException {
        content = "{\n" +
                "  \"msgtype\": \"text\",\n" +
                "  \"text\": {\n" +
                "    \"content\": \"" + content + "\",\n" +
                "    \"mentioned_list\":[\"@all\"]" +
                "  }\n" +
                "}";
        System.out.println("content" + content);
        return send(content);
    }

    public String send(String textMsg) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(WEBHOOK_TOKEN);
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httpPost.setEntity(se);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("发送微信机器人消息成功 " + result);
            return result;
        } else {
            System.out.println("发送微信机器人消息失败");
        }
        // 关闭
        httpClient.close();
        response.close();
        return "发送微信机器人消息失败";
    }

    public static void main(String[] args) throws IOException {
        MainMess mm = new MainMess();
        mm.sendText("123456");
    }
}
