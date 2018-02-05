/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017 WebFolder OÜ (support@webfolder.io)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp;

import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

/***
 * cdp常用方法:
 * 使用CdpPubUtil.getInstance().getHtml("http://jandan.net/ooxx/page-393",10)
 */
public class CdpPubUtil {

    private static CdpPubUtil ourInstance = new CdpPubUtil();

    public static CdpPubUtil getInstance() {
        return ourInstance;
    }

    private CdpPubUtil() {
    }

    private String content;

    private  boolean go(String url,int waitTime) {
        boolean result = false;
        try {
            Launcher launcher = new Launcher();
            try (SessionFactory factory = launcher.launch();
                 Session session = factory.create()) {
                session.navigate(url);
                session.waitDocumentReady(waitTime);
                content = session.getContent();
                if(content!=null  && (content.indexOf("html")>-1|| content.indexOf("meta")>-1)){
                    return true;//说明返回数据了
                }
            }
        } catch (Exception e) {
            //出错了,就返回false
            result=false;
        }
        return result;
    }

    public  synchronized String getHtml(String url,int maxTryTimes,int waitTime) {
        int tryTimes =0;//尝试次数
        while (true){
            boolean result = go(url,waitTime);
            if(result || tryTimes >=maxTryTimes){
                //结果正确,或者超过了允许的尝试次数
                break;
            }else{
                tryTimes++;
            }
        }
        return this.content;
    }


    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        try (SessionFactory factory = launcher.launch();
             Session session = factory.create()) {
            session.navigate("https://www.instagram.com/p/BbrY5Dml_Jh/?taken-by=kanamizu_yu");
            session.waitDocumentReady();
            String content = session.getContent();
            System.out.println(content);
        }
    }


}
