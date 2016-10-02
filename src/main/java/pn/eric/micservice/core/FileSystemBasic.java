package pn.eric.micservice.core;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;

/**
 * Created by eric on 16/10/2.
 */
public class FileSystemBasic {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        FileSystem fs = vertx.fileSystem();

        fs.copyBlocking("foo.txt", "barBlocking.txt");

//       Copy file from foo.txt to bar.txt
        fs.copy("foo.txt", "bar.txt", res -> {
            if (res.succeeded()) {
                // Copied ok!
                System.out.println("Copied ok!");
            } else {
                // Something went wrong
            }
        });
//       read file
        System.out.println(fs.readFileBlocking("foo.txt"));
        fs.readFile("foo.txt",result->{
            if (result.succeeded()) {
                System.out.println(result.result());
            } else {
                System.err.println("Oh oh ..." + result.cause());
            }
        });


//        Write a file
        vertx.fileSystem().writeFile("target/classes/hello.txt", Buffer.buffer("Hello"), result -> {
            if (result.succeeded()) {
                System.out.println("File written");
            } else {
                System.err.println("Oh oh ..." + result.cause());
            }
        });

//        Check existence and delete
        fs.exists("target/classes/hello.txt", result -> {
            if (result.succeeded() && result.result()) {
                vertx.fileSystem().delete("target/classes/hello.txt", r -> {
                    System.out.println("File deleted");
                });
            } else {
                System.err.println("Oh oh ... - cannot delete the file: " + result.cause());
            }
        });

    }
}
