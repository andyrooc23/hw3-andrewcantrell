import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Andrew Cantrell testing file
 * Coverge:
 * Methods - 100%
 * Lines - 100%
 */

//Test constructor
public class AssassinManagerTest {

    /**
     * Test case 1 provided as an example
     * Test graveyardContains should not have the person who wasn't killed
     */
    @Test
    public void graveyardContainsNegtiveTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        Assert.assertFalse(manager.graveyardContains("ocean"));
    }

    /**
     * Comment
     */
    @Test
    public void graveyardContainsNullTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        Assert.assertEquals(manager.graveyardContains("ocean"), false);
    }

    /**
     * Test case 2 provided as an example
     * Test constructor with invalid argument
     * Should throw IllegalArgumentException
     */
    @Test
    public void constructorNegativeTest() {
        try {
            List<String> emptyList = new ArrayList<String>();
            AssassinManager manager = new AssassinManager(emptyList);
            Assert.fail("AssassinManager should throw IllegalArgumentExeption for empty list");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Testing if game is over. Game should not be over
     */
    @Test
    public void isGameOverFalseTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        Assert.assertFalse(manager.isGameOver());
    }

    /**
     * Testing if game is over. Game should be over
     */
    @Test
    public void isGameOverTrueTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        manager.kill("Chris");
        manager.kill("Ocean");
        Assert.assertTrue(manager.isGameOver());
    }

    /**
     * Testing the winner return method. Should return Dr.Han as the winner
     */
    @Test
    public void winnerTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        manager.kill("Chris");
        manager.kill("Ocean");
        Assert.assertEquals(manager.winner(), "Dr.Han");
    }

    /**
     * Testing winner method when game is not over. Should return null
     */
    @Test
    public void winnerNullTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        Assert.assertEquals(manager.winner(), null);
    }

    /**
     * Testing if kill ring contains a name. Should return true
     */
    @Test
    public void killRingContainsTrueTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        manager.kill("Ocean");
        Assert.assertTrue(manager.killRingContains("Dr.Han"));
    }

    /**
     * Testing if killRing contains name. Should return false
     */
    @Test
    public void killRingContainsFalseTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        Assert.assertFalse(manager.killRingContains("Dr van hoousan"));
    }

    /**
     * This test was a little more difficult. I instantiated a new output stream
     * to capture the console system print lines. Then tested those against what I
     * know should be the right answer. Should return true.
     */
    @Test
    public void printKillRingTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        manager.printKillRing();
        Assert.assertEquals("    Grayson is stalking Ocean" + System.lineSeparator()
                + "    Ocean is stalking Chris" + System.lineSeparator()
                + "    Chris is stalking Dr.Han" + System.lineSeparator()
                +"    Dr.Han is stalking Grayson" + System.lineSeparator(), out.toString());

    }

    /**
     * Testing printGraveyard method. I instantiated a new output stream
     * to capture the console system print lines. Then tested those against what I
     * know should be the right answer. Should return true.
     */
    @Test
    public void printGraveyardTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Dr.Han");
        manager.kill("Chris");
        manager.kill("Ocean");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        manager.printGraveyard();
        Assert.assertEquals("    Ocean was killed by Grayson" + System.lineSeparator()
                + "    Chris was killed by Ocean" + System.lineSeparator()
                + "    Dr.Han was killed by Chris" + System.lineSeparator(), out.toString());
    }
}
