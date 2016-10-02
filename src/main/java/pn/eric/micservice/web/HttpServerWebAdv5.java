package pn.eric.micservice.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * Created by eric on 16/10/2.
 * curl  127.0.0.1:8080/foo/bar
 */
public class HttpServerWebAdv5 {
    public static void main(String[] args) {
        Vertx vertx  =  Vertx.vertx();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

//        Route route = router.route().pathRegex(".*foo");
        Route route = router.route();

        route.pathRegex("\\/([^\\/]+)\\/([^\\/]+)").handler(routingContext -> {

            String productType = routingContext.request().getParam("param0");
            String productID = routingContext.request().getParam("param1");
            System.out.println(String.format("productType: %s",productType));
            System.out.println(String.format("productID: %s",productID));

            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");
            response.end("\nregex Hello world!\n");
            // Do something with them...
        });


        server.requestHandler(router::accept).listen(8080);
    }
}
