package xyz.kvantum.nanotube;

import junit.framework.TestCase;

public class NanoTubeTest extends TestCase {

    /**
     * Used to test when jUnit decides to not work
     */
    public static void main(final String[] test) {
        new NanoTubeTest().test();
    }

    public void test() {
        NanoTube.construct(new ToLowerCase()).setLast(new Print()).setLast(new Substring())
                .setLast(new Print()).initiate("HELLO WORLD");
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