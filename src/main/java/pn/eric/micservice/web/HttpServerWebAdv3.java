package pn.eric.micservice.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * Created by eric on 16/10/2.
 * curl  127.0.0.1:8080/catalogue/products/tools/drill123/
 */
public class HttpServerWebAdv3 {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        Route route = router.route(HttpMethod.GET, "/catalogue/products/:productype/:productid/");

        route.handler(routingContext -> {

            String productType = routingContext.request().getParam("productype");
            String productID = routingContext.request().getParam("productid");
            System.out.println(String.format("productType: %s",productType));
            System.out.println(String.format("productID: %s",productID));

            // Do something with them...
            routingContext.response().end(String.format("productType->%s, productID->%s",productType,productID));
        });

        server.requestHandler(router::accept).listen(8080);
    }
}
