package calendar;

import java.util.HashMap;
import java.util.Scanner;


public class Users {
	
	static Scanner input = new Scanner(System.in);
	private String userName;
	// hash map to store calendars for each individual user. Key is Calendar description and value is CalendarView class.
	private HashMap<String, CalendarView> calendarList = new HashMap<String, CalendarView>();
	
	
	public Users(String userName) {
		this.userName = userName;
		CalendarView def = new CalendarView("default");
		calendarList.put("default", def);
	}
	
	public String getUserName() {
		return userName;
	}
	public void setName(String userName) {
		this.userName = userName;
	}

	
	public boolean loginUser(String userName) {		
		if (this.userName.equals(userName)) {			
			return true;
		}
		return false;
	}
	
	public void addCalendar(String desc) {		
		if (calendarList.containsKey(desc)) {
			System.out.println("Same description of existing calendar.");
			
		}
		else {
			CalendarView cal = new CalendarView(desc);
			calendarList.put(desc, cal);
		}		
	}
	
	public void updateCalendar(String select, String desc) {
		if (calendarList.containsKey(desc)) {
			System.out.println("Same description of existing calendar.");
			
		}
		else {
			CalendarView cal = calendarList.remove(select);
			calendarList.put(desc, cal);
		}	
			
	}
	public void removeCalendar(String desc) {
		if (calendarList.containsKey(desc)) {
			calendarList.remove(desc);
			
		}
		else {
			System.out.println("Calendar does not exist.");
		}	
	}
	
	// calendar menu that the user to enter event, setting, toggle private options
	public void selectCalendar(String desc) {
		if (calendarList.containsKey(desc)) {
			String[] calendarMenu = {"event", "setting", "toggleprivate", "exit"};
			
			while(true) {
				System.out.println("Viewing " + calendarList.get(desc).getPrivacy() + " Calendar: ");
				calendarList.get(desc).displayCalendar();
				
				System.out.println("\nMenu:");
				for (int i = 0; i < calendarMenu.length; i++) {
					System.out.println(calendarMenu[i]);
				}
				System.out.println("\nSelect menu options: ");
				String choice = input.nextLine();
				
				if(choice.toLowerCase().equals("event")) {
					calendarList.get(desc).eventMenu();
				}
				else if(choice.toLowerCase().equals("setting")) {
					calendarList.get(desc).editCalendarSetting();
				}
				else if(choice.toLowerCase().equals("toggleprivate")) {
					calendarList.get(desc).togglePrivate();
					
				}
				else if (choice.toLowerCase().equals("exit")) {
					break;
				}
				else {
					System.out.println("Choice was not valid.");
				}
			}
			
		}
		else {
			System.out.println("Calendar does not exist.");
		}	
	}
	
	// loop through calendarList and print all calendars
	public void viewCalendarList() {
		for(String i : calendarList.keySet()) {
			System.out.println(i);
		}
	}
	
}
