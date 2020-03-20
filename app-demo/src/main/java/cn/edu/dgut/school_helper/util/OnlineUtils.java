package cn.edu.dgut.school_helper.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class OnlineUtils {

    public static InputStream getImageStream(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                return inputStream;
            }
        } catch (IOException e) {
            System.out.println("获取网络图片出现异常，图片路径为：" + url);
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getImageBytes(String url) {
        InputStream in = getImageStream(url);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = in.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static String sendGet(String url, Map<String, String> params) {
        url = constructUrl(url, params);
        HttpURLConnection conn = null;
        try {
            // 发起请求
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            // 读取返回字符串
            if (conn.getResponseCode() == 200) {
                return readResponseStr(conn);
            }
        } catch (IOException e) {
            System.out.println("GET请求异常:" + url);
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return null;
    }

    public static String sendPostImage(String url, Map<String, String> params, byte[] imgBytes) {

        url = constructUrl(url, params);
        HttpURLConnection conn = null;
        OutputStream os = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.connect();
            os = new DataOutputStream(conn.getOutputStream());
            os.write(imgBytes);
            os.flush();
            // 读取返回字符串
            return readResponseStr(conn);
        } catch (IOException e) {
            System.out.println("POST请求异常:" + url);
            e.printStackTrace();
        } finally {
            conn.disconnect();
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
/*
    public static String sendPostImage2(String url, Map<String, String> params, byte[] imgBytes) {
        String line = null;//接口返回的结果
        url = constructUrl(url, params);
        try {
            // 换行符
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            // 定义数据分隔线
            String BOUNDARY = "========7d4a6d158c9";
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            // 设置为POST情
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求头参数
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
            OutputStream out = new DataOutputStream(conn.getOutputStream());

            // 上传文件
            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix);
            sb.append(BOUNDARY);
            sb.append(newLine);
            // 文件参数,photo参数名可以随意修改
            sb.append("Content-Disposition: form-data;name=\"image\";filename=\""
                    + "media" + "\"" + newLine);
            sb.append("Content-Type:image/jpeg");
            // 参数头设置完以后需要两个换行，然后才是参数内容
            sb.append(newLine);
            sb.append(newLine);

            // 将参数头的数据写入到输出流中
            out.write(sb.toString().getBytes("utf-8"));

            // 读取文件数据
            out.write(imgBytes);
            // 最后添加换行
            out.write(newLine.getBytes("utf-8"));

            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] end_data = (newLine + boundaryPrefix + BOUNDARY
                    + boundaryPrefix + newLine).getBytes();
            // 写上结尾标识
            out.write(end_data);
            out.flush();
            out.close();
            // 定义BufferedReader输入流来读取URL的响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                return line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
        }
        return line;
    }
*/
/*
    public static String checkImgByInputStream(String url, Map<String, String> params, byte[] imgBytes) throws HttpException {
        url = constructUrl(url, params);
        HttpClient httpclient = HttpClients.createDefault();

        HttpPost request = new HttpPost(url);
        request.addHeader("Content-Type", "application/octet-stream");
        try {
            request.setEntity(new ByteArrayEntity(imgBytes, ContentType.create("image/jpg")));
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");// 转成string
            System.out.println("response: " + result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
*/

    public static String sendPostContent(String url, Map<String, String> params, String jsonContent) {
        url = constructUrl(url, params);
        System.out.println(url);
        HttpURLConnection conn = null;
        OutputStreamWriter osw = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.connect();
            osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            osw.write(jsonContent);
            osw.flush();
            // 读取返回字符串
            return readResponseStr(conn);
        } catch (IOException e) {
            System.out.println("POST请求异常:" + url);
            e.printStackTrace();
        } finally {
            conn.disconnect();
            try {
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private static String constructUrl(String url, Map<String, String> params) {
        int i = 1;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            url = url.concat((i == 1) ? "?" : "&");
            url = url.concat(k).concat("=").concat(v);
            i++;
        }
        return url;
    }

    private static String readResponseStr(HttpURLConnection conn) throws IOException {
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String realStr = "";
        String str = null;
        while ((str = br.readLine()) != null) {
            realStr += str;//解决中文乱码问题
        }
        close(is, br);
        return realStr;
    }

    private static void close(InputStream is, BufferedReader br) {
        // 关闭连接
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
