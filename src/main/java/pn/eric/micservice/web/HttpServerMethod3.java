package pn.eric.micservice.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * Created by eric on 16/10/2.
 */
public class HttpServerMethod3 {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        // curl  127.0.0.1:8080
//        router.get().handler(routingContext -> {
//
//            // Will be called for any GET request
//            HttpServerResponse response = routingContext.response();
//            response.putHeader("content-type","text/plain");
//            response.end("get !");
//        });
       // curl  127.0.0.1:8080/some/path/
        router.get("/some/path/").handler(routingContext -> {

            // Will be called for any GET request to a path
            // starting with /some/path
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type","text/plain");
            response.end("some path!");
        });
        // curl  127.0.0.1:8080/all.getfoo
        router.getWithRegex(".*foo").handler(routingContext -> {

            // Will be called for any GET request to a path
            // ending with `foo`
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type","text/plain");
            response.end("foo!");
        });

        server.requestHandler(router::accept).listen(8080);
    }
}
