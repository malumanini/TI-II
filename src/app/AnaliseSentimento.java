package app;

import io.netty.util.Version;

public class TesteNetty {
    public static void main(String[] args) {
        System.out.println("Versão do Netty: " + Version.identify().entrySet());
    }
}
