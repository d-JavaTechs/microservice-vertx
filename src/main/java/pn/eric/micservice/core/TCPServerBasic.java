package pn.eric.micservice.core;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

/**
 * Created by eric on 16/10/2.
 */
public class TCPServerBasic {
    public static void main( String[] args )
    {
        Vertx vertx = Vertx.vertx();
        NetServerOptions options = new NetServerOptions().setLogActivity(true);

        NetServer server = vertx.createNetServer(options);

        server.connectHandler(socket->{
            socket.handler(buffer->{
                System.out.println("I received some bytes: " + buffer.getString(0, buffer.length()));

//                Buffer bufferAnother = Buffer.buffer().appendFloat(12.34f).appendInt(123);
//                socket.write(bufferAnother);
                // Write a string in UTF-8 encoding
//                socket.write("TCPServerBasic welcome data\n");
                socket.sendFile("myfile.dat");
            });

            socket.closeHandler(v -> {
                System.out.println("The socket has been closed");
            });



        });

        server.listen(1234,"localhost",res->{
            System.out.println(res);
            if (res.succeeded()) {
                System.out.println("Server is now listening!");
            } else {
                System.out.println("Failed to bind!");
            }
        });

//
//
//        server.close(res-> {
//                    if (res.succeeded()) {
//                        System.out.println("Server is now closed");
//                    } else {
//                        System.out.println("close failed");
//                    }
//                }
//        );
    }
}
