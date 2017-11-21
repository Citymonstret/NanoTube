package xyz.kvantum.nanotube;

import junit.framework.TestCase;

public class NanoTubeTest extends TestCase {

    public void test() {
        final NanoTube<String> nanoTube = NanoTube.construct(new ToLowerCase());
        nanoTube.getFirstItem().next(new Print()).next(new Substring()).next(new Print());
        nanoTube.initiate("HELLO WORLD");
    }

    public static class ToLowerCase extends Transformer<String> {
        @Override
        public String handle(String item) {
            return item.toLowerCase();
        }
    }

    public static class Print extends Receiver<String> {
        @Override
        public void receive(String item) {
            System.out.println(item);
        }
    }

    public static class Substring extends Transformer<String> {

        @Override
        public String handle(String item) {
            return item.substring(6);
        }
    }

}