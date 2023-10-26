package tests.src;

import billOfLading.FragileLoad;
import billOfLading.WayBill;
import core.Controller;
import org.junit.Assert;
import org.junit.Test;

public class Tests {

    @Test
    public void testCreateFragileLoad(){
        Controller controller = new Controller();
        WayBill wayBill = new FragileLoad("A123", 20, 30, "Dobrich", "Varna");
        controller.addWayBillToRepo(wayBill);
        Assert.assertEquals(1, controller.getStatistics());
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void testCreateEmptyBlank(){
        Controller controller = new Controller();
        String[] input = {""};
        WayBill wayBill = new FragileLoad(input[0], Double.parseDouble(input[1]), Double.parseDouble(input[2]), input[3], input[4]);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNegativeVolume(){
        Controller controller = new Controller();
        WayBill wayBill = new FragileLoad("A123", 20, -30, "Dobrich", "Varna");
        controller.addWayBillToRepo(wayBill);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNegativeKilograms(){
        Controller controller = new Controller();
        WayBill wayBill = new FragileLoad("A123", -20, 30, "Dobrich", "Varna");
        controller.addWayBillToRepo(wayBill);
    }

    @Test
    public void testRemoveBlank(){
        Controller controller = new Controller();
        WayBill wayBill = new FragileLoad("A123", 20, 30, "Dobrich", "Varna");
        controller.addWayBillToRepo(wayBill);
        controller.removeWayBillToRepo("A123");
        Assert.assertEquals(0, controller.getStatistics());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNotExistingBillOfLading(){
        Controller controller = new Controller();
        WayBill wayBill = new FragileLoad("A123", 20, 30, "Dobrich", "Varna");
        controller.addWayBillToRepo(wayBill);
        controller.getWayStatistic("DDD");
    }


}
