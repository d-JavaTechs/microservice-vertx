package pn.eric.micservice.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * Created by eric on 16/10/2.
 */
public class HttpServerWebAdv {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.route().handler(routingContext->{
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type","text/plain");
            response.end("Hello world!");
        });

        server.requestHandler(router::accept).listen(8080);
    }
}
