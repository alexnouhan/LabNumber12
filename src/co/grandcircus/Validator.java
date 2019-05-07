package co.grandcircus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

// this is a concrete class that can be used over and over again
// you can also add your own validation methods here
public class Validator {

	public static DateTimeFormatter f = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	// dont use this if you need to consume a whole sentence
	public static String getString(Scanner sc, String prompt) {
		System.out.print(prompt);
		String s = sc.next(); // read user entry
		sc.nextLine(); // discard any other data entered on the line
		return s;
	}

	public static String getLine(Scanner sc, String prompt) {
		String s = "";
		try {
			System.out.println(prompt);
			s = sc.nextLine();
		} catch (Exception e) {
			System.out.println("error!");
			sc.nextLine();
		}
		return s;
	}
	

	public static int getInt(Scanner sc, String prompt) {
		int i = 0;
		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("Error! Invalid integer value. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return i;
	}

	public static int getInt(Scanner sc, String prompt, int min, int max) {
		int i = 0;
		boolean isValid = false;
		while (isValid == false) {
			i = getInt(sc, prompt);
			if (i < min)
				System.out.println("Error! Number must be " + min + " or greater.");
			else if (i > max)
				System.out.println("Error! Number must be " + max + " or less.");
			else
				isValid = true;
		}
		return i;
	}

	public static double getDouble(Scanner sc, String prompt) {
		double d = 0;
		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextDouble()) {
				d = sc.nextDouble();
				isValid = true;
			} else {
				System.out.println("Error! Invalid decimal value. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return d;
	}

	public static double getDouble(Scanner sc, String prompt, double min, double max) {
		double d = 0;
		boolean isValid = false;
		while (isValid == false) {
			d = getDouble(sc, prompt);
			if (d < min)
				System.out.println("Error! Number must be " + min + " or greater.");
			else if (d > max)
				System.out.println("Error! Number must be " + max + " or less.");
			else
				isValid = true;
		}
		return d;
	}

	public static String getStringMatchingRegex(Scanner sc, String prompt, String regex) {
		boolean isValid = false;
		String input;
		do {
			input = getString(sc, prompt);
			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.out.println("Input must match the right format: ");
			}

		} while (!isValid);

		return input;
	}

	public static LocalDate getDate(Scanner sc, String prompt) {
		String s = "";
		LocalDate ld = null;
		try {
			s = dateValidator(sc, prompt);
			ld = LocalDate.parse(s, f);
		} catch (DateTimeParseException e) {
			System.out.println("enter valid date");
			sc.nextLine();
		} catch (Exception e) {
			System.out.println("error!");
			sc.nextLine();
		}
		return ld;
	}

	public static String dateValidator(Scanner scan, String prompt) {
		String date = "";
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			date = scan.next();
			if (Pattern.matches(
					"(([0][13578]|[1][02])\\/([0][1-9]|[1-2][0-9]|[3][0-1]))\\/\\d{4}|(([0][469]|[1][1])\\/([0][1-9]|[1-2][0-9]|[3][0]))\\/\\d{4}|(([0][2])\\/([0][1-9]|[1-2][0-9]))\\/\\d{4}",
					date)) {
				isValid = true;
			} else {
				System.out.println("Not a valid date! Try again: ");
			}
			scan.nextLine();
		}
		return date;
	}

	public static int getGo(Scanner scan, String prompt) {
		String go = "";
		int num = 0;
		while (num == 0) {
			try {
				go = getString(scan, prompt);
			} catch (Exception e) {
				System.out.println("error!");
			}
			if (go.equalsIgnoreCase("y")) {
				num = 1;
			} else if (go.equalsIgnoreCase("n")) {
				num = 2;
			} else if (go.equalsIgnoreCase("detroit")) {
				num = 3;
			} else {
				System.out.println();
				System.out.println("Invalid entry.");
				continue;
			}
		}
		return num;
	}


}