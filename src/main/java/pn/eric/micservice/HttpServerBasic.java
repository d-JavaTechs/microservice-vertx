package pn.eric.micservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

/**
 * Hello world!
 *
 */
public class HttpServerBasic
{
    public static void main( String[] args )
    {
        HttpServer server = Vertx.vertx().createHttpServer();

        server.requestHandler(request -> {
            // This handler gets called for each request that arrives on the server
            HttpServerResponse response = request.response();
            response.putHeader("content-type", "text/plain");

            // Write to the response and end it
            response.end("Hello World!");
        });
        server.listen(8080);
    }
}
