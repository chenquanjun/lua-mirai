package cn.qzwxsaedc.requests;

import cn.qzwxsaedc.requests.PreparedRequest;
import kotlin.Suppress;
import okhttp3.Headers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Response {
    private final okhttp3.Response response;
    // ��Ӧ����
    public final byte[] content;
    // ���������ص�Cookies
    public CookieJar cookies = null;
    // �ӷ��͵������ܹ����ѵ�ʱ��
    public final long elapsed;
    // Response.text���õı���
    public Charset encoding = StandardCharsets.UTF_8;
    // ��Ӧͷ
    public final Map<String, List<String>> headers;
    // �Ƿ�Ϊ�����ض���(302)
    public boolean is_permanent_redirect;
    // �Ƿ�Ϊ�ض���(301, 302)
    public final boolean is_redirect;
    // ������Ӧ�Ľ���ͷ����
    public final String links = null;
    // �����ض���������һ�������PreparedRequest
    public final PreparedRequest next = null;
    // status_code < 400 ʱΪtrue
    public final boolean ok;
    // ��Ӧ���ݵ�ԭʼ��
    public final InputStream raw;
    // ������ʽ��HTTP״̬������"Not Found"����"OK"
    public final String reason;
    // ��Ӧ��PreparedRequest����
    public final PreparedRequest request = null;
    // ������ʽ��HTTP״̬������"404"����"200"
    public final int status_code;
    // ��Ӧ���ݣ��ַ�����Ϊencoding
    public String text() {
        try{
            StringBuilder sb = new StringBuilder();
            String tmp = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(raw, encoding));
            while ((tmp = br.readLine()) != null)
                sb.append(tmp);
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    // ���յ�URL
    public final String url;
    public Response(okhttp3.Response response) throws IOException {
        this.response = response;
        raw = Objects.requireNonNull(response.body()).byteStream();
        content = raw.readAllBytes();

        elapsed = response.receivedResponseAtMillis() - response.sentRequestAtMillis();

        headers = response.headers().toMultimap();

        status_code = response.code();
        ok = status_code < 400;
        is_permanent_redirect = status_code == 302;
        is_redirect = response.isRedirect();

        url = response.request().url().toString();

        reason = response.message();
    }

    public void close(){
        response.close();
    }

    @Override
    public String toString() {
        return "Response:{code=" + status_code + "}";
    }
}
