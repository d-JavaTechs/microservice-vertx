package pn.eric.micservice.core;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.*;

/**
 * Created by eric on 16/10/2.
 */
public class TCPClientBasic {
    public static void main( String[] args )
    {
        Vertx vertx = Vertx.vertx();

        NetClientOptions options = new NetClientOptions().setLogActivity(true);

        NetClient client = vertx.createNetClient(options);

        client.connect(1234,"localhost",res->{
            if (res.succeeded()) {
                System.out.println("Connected!");
                NetSocket socket = res.result();
                socket.write("message from client\n");
                socket.sendFile("myfile.dat");


                //get message from Server
                socket.handler(buff->{
                    System.out.println("message from server");
//                    System.out.println(buff.getFloat(12));
//                    System.out.println(buff.getInt(4));
                    System.out.println(buff.getString(0,buff.length()));
                });

            } else {
                System.out.println("Failed to connect: " + res.cause().getMessage());
            }



        });
    }
}
