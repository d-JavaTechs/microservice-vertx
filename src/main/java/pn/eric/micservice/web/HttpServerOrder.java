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
public class HttpServerOrder {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        Route route1 = router.route("/some/path/").handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            response.write("route1\n");

            // Now call the next matching route
            routingContext.next();
        });

        Route route2 = router.route("/some/path/").order(-1).handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            // enable chunked responses because we will be adding data as
            // we execute over other handlers. This is only required once and
            // only if several handlers do output.
            response.setChunked(true);

            response.write("route2\n");

            // Now call the next matching route
            routingContext.next();
        });

        Route route3 = router.route("/some/path/").handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            response.write("route3");

            // Now end the response
            routingContext.response().end();
        });

       // Change the order of route2 so it runs before route1

        server.requestHandler(router::accept).listen(8080);
    }
}
