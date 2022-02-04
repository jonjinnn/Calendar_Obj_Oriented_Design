package calendar;

import java.util.Scanner;
import java.util.HashMap;


public class Main {
	static Scanner input = new Scanner(System.in);
	
	// hashmap to store user in users class. Uses username as key, value is Users class.
	static HashMap<String, Users> userList = new HashMap<String, Users>();	
	
	// helper function to find selected user in userList hash map
	public static String findUser() {
//		test user database
		Users jon = new Users("jon");
		Users jess = new Users("jess");
		userList.put("jon", jon);
		userList.put("jess", jess);
		////////////////////////////////
				
		System.out.println("Enter username to login to existing user or create new user.");
		System.out.println("Username: ");
		String userName = input.nextLine();
		
		if(userList.containsKey(userName)) {
			System.out.println("Logging in, " + userList.get(userName).getUserName() + ".");
		}
		else {
			Users newUser = new Users(userName);
			userList.put(userName, newUser);
			System.out.println("New user " + userList.get(userName).getUserName() + " created!");
		}
		return userName;
	}
	
	// provide user menu options to select, add, update, or remove calendars
	public static void userOptions(String userName) {
		String[] userMenu = {"select", "add", "update", "remove", "exit"};
		
		
		while(true) {
			System.out.println("\nAvailable Calendar(s): ");
			userList.get(userName).viewCalendarList();
			
			System.out.println("\nCalendar options:");
			for (int i = 0; i < userMenu.length; i++) {
				System.out.println(userMenu[i]);
			}
			System.out.println("\nSelect Calendar options: ");
			String choice = input.nextLine();
			
			if(choice.toLowerCase().equals("select")) {
				System.out.println("Select Calendar to view: ");
				String desc = input.nextLine();
				userList.get(userName).selectCalendar(desc);
			}
			else if(choice.toLowerCase().equals("add")) {
				System.out.println("Calendar description: ");
				String desc = input.nextLine();
				userList.get(userName).addCalendar(desc);
			}
			else if (choice.toLowerCase().equals("update")) {
				System.out.println("Select Calendar to update: ");
				String desc = input.nextLine();
				System.out.println("Update Calendar description: ");
				String newDesc = input.nextLine();
				userList.get(userName).updateCalendar(desc, newDesc);
				
			}
			else if (choice.toLowerCase().equals("remove")) {
				System.out.println("Type Calendar to remove: ");
				String desc = input.nextLine();
				userList.get(userName).removeCalendar(desc);			
			}
			else if (choice.toLowerCase().equals("exit")) {
				break;
			}
			else {
				System.out.println("Choice was not valid.");
			}
		}
	}
			
	
	public static void main(String[]args) {
		
		while(true) {
			String userName;
			userName = findUser();
			
			userOptions(userName);
		}
		
		

		
		
		
		
	}
}
