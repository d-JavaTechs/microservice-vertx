package pn.eric.micservice.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * Created by eric on 16/10/2.
 * curl  127.0.0.1:8080/all.getfoo
 */
public class HttpServerWebAdv4 {
    public static void main(String[] args) {
        Vertx vertx  =  Vertx.vertx();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

//        Route route = router.route().pathRegex(".*foo");
        Route route = router.routeWithRegex(".*foo");

        route.handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");
            response.end("\nregex Hello world!\n");
        });


        server.requestHandler(router::accept).listen(8080);
    }
}
