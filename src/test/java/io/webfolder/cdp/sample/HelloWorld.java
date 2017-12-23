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
package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class HelloWorld {

    private  static  int count=0;


    public static boolean go() {
        boolean result = false;
        try {
            Launcher launcher = new Launcher();
            try (SessionFactory factory = launcher.launch();
                 Session session = factory.create()) {
                session.navigate("http://jandan.net/ooxx/page-393");
    //            session.navigate("https://baidu.com");
                session.waitDocumentReady();
                String content = session.getContent();
                if(content!=null && content.indexOf("html")>-1){
                    System.out.println(content);
                    return true;//说明返回数据了
                }else{
                    System.out.println("1111");
                }
            }
        } catch (Exception e) {
            //出错了,就返回false
            result=false;
        }
        return result;
    }

    public static void main(String[] args) {
        while (true){
            boolean result = go();
            if(result){
                break;
            }else{
                count++;
            }
        }
        System.out.println(count);
    }
}
