package xyz.kvantum.nanotube;

import junit.framework.TestCase;

public class NanoTubeTest extends TestCase {

    private static final boolean throwPrintException = true;

    /**
     * Used to test when jUnit decides to not work
     */
    public static void main(final String[] test) {
        new NanoTubeTest().test();
    }

    public void test() {
        NanoTube.construct(new ToLowerCase()).setExceptionHandler( throwable ->
                System.out.printf( "Exception :( -> %s\n", throwable.getMessage() ) )
                .setLast(new Print())
                .setLast(new Substring())
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
            if ( throwPrintException ) {
                throw new RuntimeException("Random error...");
            }
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
