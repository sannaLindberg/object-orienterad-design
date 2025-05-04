package startup;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.posi.startup.Main;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
 @Test
    public void testMain() {
        PrintStream originalSysOut = null;
        try {
            originalSysOut = System.out;
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            String[] args = null;
            Main.main(args);
            assertTrue(outContent.toString().contains("Tack för ditt köp!"),
                       "Wrong output when main is executed");
                       
            } finally {
                System.setOut(originalSysOut);
            }
    }
}
