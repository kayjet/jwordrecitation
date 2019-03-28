package com.kayjet.word.recitation;

import com.opdar.platform.core.base.Context;
import com.opdar.platform.core.base.DispatcherServlet;
import com.opdar.platform.core.base.ServletEventListener;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Created by shiju on 2017/10/15.
 */
public class Application {
    public static void main(String[] args) {
        Context.putResourceMapping("/css","classpath:/static/css");
        Context.putResourceMapping("/fonts","classpath:/static/fonts");
        Context.putResourceMapping("/imgs","classpath:/static/imgs");
        Context.putResourceMapping("/js","classpath:/static/js");
        Server server = new Server(18886);
        ServletContextHandler context = new ServletContextHandler(1);
        context.addEventListener(new ServletEventListener());
        context.setContextPath("/");
        context.addServlet(DispatcherServlet.class, "/");
//        context.addFilter(CrossOriginFilter.class,"/*", EnumSet.of(DispatcherType.REQUEST));
        server.setHandler(context);
        try {
            server.start();
            server.join();
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }
}
