package pn.eric.micservice.core;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.streams.Pump;

/**
 * Created by eric on 16/10/2.
 */
public class FileSystemAdvance {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.fileSystem().open("target/classes/hello.txt", new OpenOptions(), result -> {
            if (result.succeeded()) {
                AsyncFile file = result.result();
                Buffer buff = Buffer.buffer("fooHello");
                for (int i = 0; i < 5; i++) {
                    file.write(buff, buff.length() * i, ar -> {
                        if (ar.succeeded()) {
                            System.out.println("Written ok!");
                            // etc
                        } else {
                            System.err.println("Failed to write: " + ar.cause());
                        }
                    });
                }
            } else {
                System.err.println("Cannot open file " + result.cause());
            }
        });

        vertx.fileSystem().open("target/classes/hello.txt", new OpenOptions(), result -> {
            if (result.succeeded()) {
                AsyncFile file = result.result();
                Buffer buff = Buffer.buffer(1000);
                for (int i = 0; i < 10; i++) {
                    file.read(buff, i * 100, i * 100, 100, ar -> {
                        if (ar.succeeded()) {

                            System.out.println(buff);
                        } else {
                            System.err.println("Failed to write: " + ar.cause());
                        }
                    });
                }
            } else {
                System.err.println("Cannot open file " + result.cause());
            }
        });

        final AsyncFile output = vertx.fileSystem().openBlocking("target/classes/hello.txt", new OpenOptions());
        vertx.fileSystem().open("target/classes/plagiary.txt", new OpenOptions(), result -> {
            if (result.succeeded()) {
                AsyncFile file = result.result();
                Pump.pump(file, output).start();
                file.endHandler((r) -> {
                    System.out.println("Copy done");
                });
            } else {
                System.err.println("Cannot open file " + result.cause());
            }
        });
    }
}
