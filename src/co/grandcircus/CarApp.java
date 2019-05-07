package co.grandcircus;

import java.util.Scanner;

public class CarApp {

	public static void main(String[] args) {

		// Alex Nouhan
		// Grand Circus 2019

		Scanner scan = new Scanner(System.in);

		Car car1 = new UsedCar("Honda", "Civic", 1991, 1800.69, 222000);
		Car car2 = new UsedCar("Datsun", "510", 1971, 6600.23, 87000);
		Car car3 = new Car("Chevrolet", "Corvette", 2020, 170000.00);

		CarLot.lot.add(car1);
		CarLot.lot.add(car2);
		CarLot.lot.add(car3);

		System.out.println("Welcome the Grand Circus Motors Admin console");

		while (true) {
			printMenu();

			int choice = chooser(scan, "What would you like to do?: ");

			if (choice == 1) {
				listCars();
			} else if (choice == 2) {
				addCar(scan);
			} else if (choice == 3) {
				buyCar(scan);
			} else if (choice == 4) {
				search(scan);
			} else if (choice == 5) {
				int go = Validator.getGo(scan, "Are you sure you want to quit? (y/n): ");
				if (go == 1) {
					break;
				} else if (go == 2) {
					continue;
				} else if (go == 3) {
					System.out.println("secret");
				} else {
					System.out.println("error!");
					continue;
				}

			}
		}
		System.out.println("Goodbye!");
		scan.close();
	}

	public static void printMenu() {
		System.out.println();
		System.out.println("\t   Menu");
		System.out.println("\t 1.View Lot");
		System.out.println("\t 2.Enter Car");
		System.out.println("\t 3.Buy Car");
		System.out.println("\t 4.Search Lot");
		System.out.println("\t 5.Quit");
		System.out.println();
	}

	public static int chooser(Scanner scan, String prompt) {
		char c;
		int choice = 0;
		try {
			System.out.print(prompt);
			c = scan.next().charAt(0);
			if (c == '1' | c == 'l' | c == 'L') {
				choice = 1;
			} else if (c == '2' | c == 'e' | c == 'E') {
				choice = 2;
			} else if (c == '3' | c == 'b' | c == 'B') {
				choice = 3;
			} else if (c == '4' | c == 's' | c == 'S') {
				choice = 4;
			} else if (c == '5' | c == 'q' | c == 'Q') {
				choice = 5;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Try again!");
		}
		scan.nextLine();
		return choice;
	}

	public static void listCars() {
		System.out.println();
		System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s %-10s\n", " ", "Make", "Model", "Year", "Price",
				"New/Used", "Milage");

		for (Car i : CarLot.lot) {
			if (i instanceof UsedCar) {
				System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s %-10s\n", CarLot.lot.indexOf(i) + 1, i.getMake(),
						i.getModel(), i.getYear(), i.getPrice(), "U", ((UsedCar) i).getMilage());
			} else if (i instanceof Car) {
				System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s\n", CarLot.lot.indexOf(i) + 1, i.getMake(),
						i.getModel(), i.getYear(), i.getPrice(), "N");
			}
		}
	}

	public static void addCar(Scanner scan) {
		System.out.println();
		System.out.println("Add New Car");
		System.out.println();
		System.out.println("New or Used?: ");

		while (true) {
			char usedChar = scan.next().charAt(0);
			scan.nextLine();

			if (usedChar == 'n' | usedChar == 'N') {
				String make = Validator.getLine(scan, "Make: ");
				String model = Validator.getLine(scan, "Model: ");
				int year = Validator.getInt(scan, "Year: ", 1911, 2020);
				double price = Validator.getDouble(scan, "Price: ", 0, 2000000);
				Car newCar = new Car(make, model, year, price);
				CarLot.lot.add(newCar);
				break;
			} else if (usedChar == 'u' | usedChar == 'U') {
				String make = Validator.getLine(scan, "Make: ");
				String model = Validator.getLine(scan, "Model: ");
				int year = Validator.getInt(scan, "Year: ", 1911, 2020);
				double price = Validator.getDouble(scan, "Price: ", 0, 2000000);
				double milage = Validator.getDouble(scan, "Milage: ", 1, 1000000);
				Car usedCar = new UsedCar(make, model, year, price, milage);
				CarLot.lot.add(usedCar);
				break;
			} else {
				System.out.println("Try again");
				System.out.println();
				continue;
			}
		}
		System.out.println("Car Added");
	}

	public static void buyCar(Scanner scan) {
		if (CarLot.lot.isEmpty()) {
			System.out.println();
			System.out.println("Lot is empty!");
			System.out.println();
		} else {
			listCars();
			int index = Validator.getInt(scan, "Which car would you like to buy? (0 to quit): ", 0, CarLot.lot.size());
			if (index == 0) {
				System.out.println("\nBuy Cancelled");
			} else {
				index = index - 1;

				if (CarLot.lot.get(index) instanceof UsedCar) {
					Car i = CarLot.lot.get(index);
					System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s %-10s\n", CarLot.lot.indexOf(i) + 1,
							i.getMake(), i.getModel(), i.getYear(), i.getPrice(), "U", ((UsedCar) i).getMilage());
				} else if (CarLot.lot.get(index) instanceof Car) {
					Car i = CarLot.lot.get(index);
					System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s\n", CarLot.lot.indexOf(i) + 1, i.getMake(),
							i.getModel(), i.getYear(), i.getPrice(), "N");
				}
				int safety = Validator.getGo(scan, "Are you sure? (y/n): ");
				if (safety == 2) {
					System.out.println("\nBuy Cancelled");
				} else if (safety == 1) {
					CarLot.lot.remove(index);
					System.out.println("\nCar Purchased");
				}
			}
		}
	}

	public static void search(Scanner scan) {
		System.out.println();
		System.out.println("Seach Lot");
		System.out.println();
		if (CarLot.lot.isEmpty()) {
			System.out.println();
			System.out.println("Lot is empty!");
			System.out.println();
		} else {
			try {
				int yeary = 0;
				System.out.println("You can search for make, model, year or type 'used' or new'");
				String query = Validator.getString(scan, "Enter query: ");
				try {
					yeary = Integer.parseInt(query);
				} catch (NumberFormatException e) {
					
				}
				System.out.println();
				
				for (Car i : CarLot.lot) {

					if (i.getMake().equalsIgnoreCase(query)) {
						if (i instanceof UsedCar) {
							System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s %-10s\n", CarLot.lot.indexOf(i) + 1,
									i.getMake(), i.getModel(), i.getYear(), i.getPrice(), "U",
									((UsedCar) i).getMilage());
						} else if (i instanceof Car) {
							System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s\n", CarLot.lot.indexOf(i) + 1,
									i.getMake(), i.getModel(), i.getYear(), i.getPrice(), "N");
						}
					} else if (i.getModel().equalsIgnoreCase(query)) {
						if (i instanceof UsedCar) {
							System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s %-10s\n", CarLot.lot.indexOf(i) + 1,
									i.getMake(), i.getModel(), i.getYear(), i.getPrice(), "U",
									((UsedCar) i).getMilage());
						} else if (i instanceof Car) {
							System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s\n", CarLot.lot.indexOf(i) + 1,
									i.getMake(), i.getModel(), i.getYear(), i.getPrice(), "N");
						}
					} else if (query.equalsIgnoreCase("used")) {
						if (i instanceof UsedCar) {
						System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s %-10s\n", CarLot.lot.indexOf(i) + 1,
								i.getMake(), i.getModel(), i.getYear(), i.getPrice(), "U", ((UsedCar) i).getMilage());
						} else if (i instanceof Car) {
							
						}
					} else if (query.equalsIgnoreCase("new")) {
						if (i instanceof UsedCar) {
							
						} else if (i instanceof Car) {
						System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s\n", CarLot.lot.indexOf(i) + 1, i.getMake(),
								i.getModel(), i.getYear(), i.getPrice(), "N");
						}
					} else if (i.getYear() == yeary) {
						if (i instanceof UsedCar) {
							System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s %-10s\n", CarLot.lot.indexOf(i) + 1,
									i.getMake(), i.getModel(), i.getYear(), i.getPrice(), "U",
									((UsedCar) i).getMilage());
						} else if (i instanceof Car) {
							System.out.printf("%-4s %-10s %-10s %-10s %-10s %-6s\n", CarLot.lot.indexOf(i) + 1,
									i.getMake(), i.getModel(), i.getYear(), i.getPrice(), "N");
						}
					}
				}
			} catch (Exception e) {
				System.out.println("error!");
			}
		}
	}

}
