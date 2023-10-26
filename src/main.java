import billOfLading.FragileLoad;
import billOfLading.UnbreakableLoad;
import billOfLading.WayBill;
import repostory.Repo;
import repostory.Repostory;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Repostory repostory = new Repo();

        boolean exit = false;

        while (!exit) {

            System.out.println();
            System.out.println("Моля изберете една от следните опции:");
            System.out.println("1 - създаване на товарителница.");
            System.out.println("2 - преглед на товарителница.");
            System.out.println("3 - проследяване на товарителница.");
            System.out.println("4 - пренасочване на товарителница.");
            System.out.println("5 - изтриване на товарителница.");
            System.out.println("6 - изход.");
            System.out.println();

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Моля попълнете следните данни:");
                    System.out.println("1 за чуплив товар и 2 за нечуплив товар");
                    String fragleOrNot = scanner.nextLine();
                    System.out.println();
                    if (fragleOrNot.equals("1")){
                        System.out.println("Номер на товарителница, килограми, обем, изпратена от кой град, адресирана до кой град.");
                        System.out.println("(Пример: 5324 12.5 20 Русе Варна)");
                        String[] input = scanner.nextLine().split("\\s+");
                        WayBill wayBill = new FragileLoad(Integer.parseInt(input[0]), Double.parseDouble(input[1]),Double.parseDouble(input[2]), input[3], input[4]);
                        repostory.addLadingBill(wayBill);
                    } else if (fragleOrNot.equals("2")){
                        System.out.println("Номер на товарителница, килограми, обем, изпратена от кой град, адресирана до кой град.");
                        System.out.println("(Пример: 5324 12.5 20 Русе Варна)");
                        String[] input = scanner.nextLine().split("\\s+");
                        WayBill wayBill = new UnbreakableLoad(Integer.parseInt(input[0]), Double.parseDouble(input[1]),Double.parseDouble(input[2]), input[3], input[4]);
                        repostory.addLadingBill(wayBill);
                    } else {
                        System.out.println("Грешно въведен вид на товара.");
                    }
                    break;
                case "2":
                    System.out.println("Моля въведете номер на товарителницата:");
                    String numberInput2 = scanner.nextLine();
                    WayBill info = repostory.getWayBill(Integer.parseInt(numberInput2));
                    System.out.println(info.getStatistics());
                    break;
                case "3":
                    System.out.println("Моля въведете номер на товарителницата:");
                    String numberInput3 = scanner.nextLine();
                    String currentLocation = repostory.getWayBill(Integer.parseInt(numberInput3)).getSendFrom();
                    System.out.printf("Текущата локация на товарителница номер:%s е: %s%n",numberInput3,currentLocation);
                    break;
                case "4":
                    System.out.println("Моля въведете номер на товарителницата:");
                    String numberInput4 = scanner.nextLine();
                    String newLocation = scanner.nextLine();
                    repostory.getWayBill(Integer.parseInt(numberInput4)).setToLocation(newLocation);
                    break;
                case "5":
                    System.out.println("Моля въведете номер на товарителницата:");
                    String numberInput5 = scanner.nextLine();
                    boolean isDelete = repostory.removeLadingBill(Integer.parseInt(numberInput5));
                    if(isDelete){
                        System.out.printf("Вие успешно изтрихте товарителница номер: %s\n",numberInput5);
                    } else {
                        System.out.println("Итриването е неуспешно.");
                    }
                    break;
                case "6":
                    System.out.println("Изход.");
                    exit = true;
                    break;
                default:
                    System.out.println("Невалиден избор!");
                    System.out.println("________________");
                    break;
            }
        }
    }
}
