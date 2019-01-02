package org.centralplains.daas;

import org.assertj.core.util.DateUtil;
import org.centralplains.daas.beans.Product;
import org.centralplains.daas.dao.ProductRepository;
import org.centralplains.daas.service.ProductService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaasApplicationTests {
    private int k = 434;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @SuppressWarnings("all")
    @Test
    public void contextLoads() throws IOException {

    }

    @Test
    public void insert() {
        for (int j = this.k; j <= 434; j++) {
            String url = "http://nc.mofcom.gov.cn/channel/jghq2017/price_list.shtml?par_craft_index=13075&craft_index=13214&par_p_index=&p_index=&startTime=2018-09-14&endTime=2018-12-13&page=" + j;
            Connection connection = Jsoup.connect(url);
            Document document = null;
            try {
                document = connection.get();
            } catch (Exception e) {
                System.out.println("家在超时，休息10秒钟，继续加载...");
                this.k = j;
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                insert();
            }
            Element table = document.getElementsByClass("table-01").first();
            if (table == null) {
                this.k = j;
                System.out.println("数据抓取出现异常，休息60秒。");
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    System.out.println("----");
                }
                insert();
            }
            Elements trs = table.children().first().children();
            for (int i = 1; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Elements tds = tr.children();
                String date = tds.get(0).text();
                String name = tds.get(1).text();
                Double price = Double.valueOf(tds.get(2).getElementsByClass("c-orange").eq(0).text());
                String seller = tds.get(3).text();
                String uri = tds.get(4).getElementsByTag("a").eq(0).attr("href");
                //解析mark1
                String priceTrend = uri.substring(uri.indexOf("mark1=") + 6, uri.indexOf("&"));
                Product product = new Product();

                product.setDate(DateUtil.parse(date));
                product.setName(name);
                product.setPrice(price);
                product.setPriceTrend(productService.reqPriceTrend(priceTrend));
                product.setSeller(seller);
                product.setCreateDate(new Date());
                productRepository.save(product);
                System.out.println("持续采集中....");
            }
            System.out.println("第" + j + "页已经完成!");
            if (j % 30 == 0) {
                System.out.println("程序进入休息状态---------");
                System.out.println("持续休息30秒");
                try {
                    Thread.sleep(1000 * 30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

