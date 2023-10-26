import billOfLading.FragileLoad;
import billOfLading.UnbreakableLoad;
import billOfLading.WayBill;
import core.Controller;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Controller controller = new Controller();

        boolean exit = false;

        while (!exit) {

            String choice = getString(scanner);

            switch (choice) {
                case "1" -> {
                    System.out.println("Изберете: 1 за чуплив товар");
                    System.out.println("Изберете: 2 за нечуплив товар");
                    System.out.println("__________________________________________");
                    String fragileOrNot = scanner.nextLine();
                    if (fragileOrNot.equals("1")) {
                        System.out.println("Номер на товарителница, килограми, обем, изпратена от кой град, адресирана до кой град.");
                        System.out.println("(Пример: A1234h 12.5 20 Русе Варна)");
                        System.out.println("__________________________________________");
                        String[] input = scanner.nextLine().split("\\s+");
                        String firstNum = input[1];
                        String secondNum = input[2];
                        if (extracted(firstNum) && extracted(secondNum)) {
                            WayBill wayBill = new FragileLoad(input[0], Double.parseDouble(input[1]), Double.parseDouble(input[2]), input[3], input[4]);
                            controller.addWayBillToRepo(wayBill);
                        } else {
                            System.out.println("Моля въведете на 2-ра и 3-та секция число!");
                            System.out.println("__________________________________________");
                        }
                    } else if (fragileOrNot.equals("2")) {
                        System.out.println("Номер на товарителница, килограми, обем, изпратена от кой град, адресирана до кой град.");
                        System.out.println("(Пример: A5324h 12.5 20 Русе Варна)");
                        String[] input = scanner.nextLine().split("\\s+");
                        String firstNum1 = input[1];
                        String secondNum1 = input[2];
                        if (extracted(firstNum1) && extracted(secondNum1)) {
                            WayBill wayBill = new UnbreakableLoad(input[0], Double.parseDouble(input[1]), Double.parseDouble(input[2]), input[3], input[4]);
                            controller.addWayBillToRepo(wayBill);
                        } else {
                            System.out.println("Моля въведете на 2-ра и 3-та секция число!");
                            System.out.println("__________________________________________");
                        }
                    } else {
                        System.out.println("Грешно въведен избор! Моля опитайте отново.");
                        System.out.println("__________________________________________");
                    }
                }
                case "2" -> {
                    System.out.println("Моля въведете номер на товарителницата:");
                    String numberInput2 = scanner.nextLine();
                    System.out.println(controller.getWayStatistic(numberInput2));
                }
                case "3" -> {
                    System.out.println("Моля въведете номер на товарителницата:");
                    String numberInput3 = scanner.nextLine();
                    String currentLocation = controller.getWayBill(numberInput3).getSendFrom();
                    System.out.printf("Текущата локация на товарителница номер:%s е %s%n", numberInput3, currentLocation);
                    System.out.println("__________________________________________");
                }
                case "4" -> {
                    System.out.println("Моля въведете номер на товарителницата:");
                    String numberInput4 = scanner.nextLine();
                    System.out.println("Пренасочване на товарителница към: ");
                    String newLocation = scanner.nextLine();
                    controller.getWayBill(numberInput4).setToLocation(newLocation);
                    System.out.printf("Вие успешно пренасочихте товарителница номер:%s към %s%n", numberInput4, newLocation);
                    System.out.println("__________________________________________");
                }
                case "5" -> {
                    double totalPrice = controller.sumOfAll();
                    System.out.printf("Сума: %.2fлв.\n", totalPrice);
                    System.out.println("__________________________________________");
                }
                case "6" -> {
                    int totalWayBills = controller.getStatistics();
                    System.out.printf("Брой създадени товарителници: %d.\n", totalWayBills);
                    System.out.println("__________________________________________");
                }
                case "7" -> {
                    System.out.println("Моля въведете номер на товарителницата:");
                    String numberInput5 = scanner.nextLine();
                    boolean isDelete = controller.removeWayBillFromRepo(numberInput5);
                    if (isDelete) {
                        System.out.printf("Вие успешно изтрихте товарителница номер: %s\n", numberInput5);
                        System.out.println("__________________________________________");
                    } else {
                        System.out.println("Итриването е неуспешно.");
                        System.out.println("__________________________________________");
                    }
                }
                case "8" -> {
                    System.out.println("Изход.");
                    System.out.println("__________________________________________");
                    exit = true;
                }
                default -> {
                    System.out.println("Невалиден избор!");
                    System.out.println("________________");
                }
            }
        }
    }

    private static boolean extracted(String firstNum) {

            try {
                Double.parseDouble(firstNum);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

    private static String getString(Scanner scanner) {
        String sb = "Моля изберете една от следните опции:" + System.lineSeparator() +
                "1 - създаване на товарителница." + System.lineSeparator() +
                "2 - преглед на товарителница." + System.lineSeparator() +
                "3 - проследяване на товарителница." + System.lineSeparator() +
                "4 - пренасочване на товарителница." + System.lineSeparator() +
                "5 - Обща цена на всички товарителници." + System.lineSeparator() +
                "6 - Брой създадени товарителници." + System.lineSeparator() +
                "7 - изтриване на товарителница." + System.lineSeparator() +
                "8 - изход." + System.lineSeparator() +
                "__________________________________________" + System.lineSeparator();
        System.out.print(sb);
        return scanner.nextLine();
    }
}

