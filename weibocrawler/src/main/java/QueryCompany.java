import org.apache.http.HttpVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class QueryCompany {
    private static String queryPrefix = "https://s.weibo.com/user?q=";
    private static String querySuffix = "&auth=org_vip&Refer=g";
    private String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String queryCompany(String company)throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(queryPrefix + company + querySuffix);

        CloseableHttpResponse response = httpClient.execute(httpGet);
        Document document = Jsoup.parse(EntityUtils.toString(response.getEntity()));
        //href 所在的class = info div
        Element divElement = document.body().getElementsByClass("info").first();
        //href 所在的a
        if (divElement == null)
            return null;
        Element aElement = divElement.getElementsByClass("name").first();
        //获取href
        String href = aElement.attr("href");

        //获取微博名称
        aElement = document.body().getElementsByClass("name").first();
        String companyWeiboName = aElement.text();
        if (href.matches(".*/u/.*")) {
            return null;
        }
        return href + "=" + companyWeiboName;
    }

    public String getConpanyRegisterDate(String company) {
        try {
            String targetUrlAndName = queryCompany(company);
            if (targetUrlAndName == null)
                return "0";
            else {
                System.out.println(Thread.currentThread().getName() + "获取到微博信息：" + targetUrlAndName + " 查询信息为：" + company);
                String[] strs = targetUrlAndName.split("=");
                CloseableHttpClient httpClient = HttpClients.createDefault();

                HttpGet httpGet = new HttpGet("http:" + strs[0]);
                httpGet.addHeader(new BasicHeader("Cookie", cookie));

                httpGet.setProtocolVersion(HttpVersion.HTTP_1_1);

                CloseableHttpResponse response = httpClient.execute(httpGet);
                Document document = Jsoup.parse(EntityUtils.toString(response.getEntity()));

                //定位date区域
                int beginindex = document.toString().indexOf("last");
                int endindex = document.toString().indexOf("firstweibo");
                String result = document.toString().substring(beginindex, endindex);

                //获取date
                String date = result.substring(result.indexOf("stat_date") + 10, result.indexOf("page") - 1);
                return date + "=" + strs[1];
            }
        } catch (Exception e) {
            System.out.println("爬取: " + company + " 数据失败");
            return "-1";
        }
    }
}
