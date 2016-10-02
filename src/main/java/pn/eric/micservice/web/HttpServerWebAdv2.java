package pn.eric.micservice.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * Created by eric on 16/10/2.
 */
public class HttpServerWebAdv2 {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
//        Route route = router.route("/some/some/*");
        Route rout = router.route().path("/some/path/");
//        Route rout = Route.route().path("/some/path/*");

        rout.handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");
            response.end("\nHello world!\n");
        });

        server.requestHandler(router::accept).listen(8080);
    }
}
