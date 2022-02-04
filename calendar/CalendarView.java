package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

public class CalendarView {
	static Scanner input = new Scanner(System.in);
	Calendar cal = new GregorianCalendar();
	
	private String desc;
	private int year = cal.get(Calendar.YEAR);
	private int month = cal.get(Calendar.MONTH);
//	private int dayOfMonth = cal.get(Calendar.DATE);
	private boolean privacy = true;
	
	private CalendarSettingView setting = new CalendarSettingView();
	// hashmap of events in the calendar, key is event description and value is Event class
	private HashMap<String, Event> eventList = new HashMap<String, Event>();

	
	public CalendarView(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}
	public String getPrivacy() {
		if (this.privacy == false) {
			return "PUBLIC";
		}
		return "PRIVATE";
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void togglePrivate() {
		if (this.privacy == false) {
			this.privacy = true;
		}
		else {
			this.privacy = false;
		}
	}
	
	
	// display the current months in a gregorian calendar
	public void displayCalendar() {
		String[] monthName = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", 
				"JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
		
		GregorianCalendar disCal = new GregorianCalendar(year, month, 1);
		int days = disCal.getActualMaximum(Calendar.DATE);
		int startInWeek = disCal.get(Calendar.DAY_OF_WEEK);
		
		disCal = new GregorianCalendar(this.year, this.month, days);
		int totalWeeks = disCal.getActualMaximum(Calendar.WEEK_OF_MONTH);
		
		System.out.println(monthName[this.month]);
		int count = 1;
		for (int i = 1; i <= totalWeeks; i++) {
			System.out.println();
			for (int j = 1; j <= 7; j++) {
				if (count < startInWeek || (count - startInWeek + 1) > 31) {
					System.out.print("--");
					System.out.print(" ");
				}
				else {
					String format = Integer.toString(count - startInWeek + 1);
					if (format.length() == 1) {
						format = "0" + format;
						
						System.out.print(format);
						System.out.print(" ");
					}
					else {
						System.out.print(count - startInWeek + 1);
						System.out.print(" ");
					}
				}
				count++;
			}
		}
		System.out.println();
	}
	
	public void editCalendarSetting() {
		while(true) {
			System.out.println("Timezone: " + setting.getTimeZone());
			System.out.println("Dark theme: " + setting.getDarkTheme());
			
			String[] calendarMenu = {"timezone", "theme", "exit"};
			
			System.out.println("\nSettings:");
			for (int i = 0; i < calendarMenu.length; i++) {
				System.out.println(calendarMenu[i]);
			}
		
			System.out.println("\nSelect settings options: ");
			String choice = input.nextLine();
			
			if(choice.toLowerCase().equals("timezone")) {
				setting.changeTimeZone();
			}
			else if(choice.toLowerCase().equals("theme")) {
				setting.changeTheme();
			}
			else if (choice.toLowerCase().equals("exit")) {
				break;
			}
			else {
				System.out.println("Choice was not valid.");
			}
		}
		
		
	}
	
	// display event menu options. Can view, share, add, remove, update events here. 
	public void eventMenu() {
		String[] calendarMenu = {"view", "share", "add", "remove", "update", "exit"};
		
		while(true) {
			System.out.println("Available Events: ");
			for(String i : eventList.keySet()) {
				System.out.println(i);
			}
			
			System.out.println("\nMenu:");
			for (int i = 0; i < calendarMenu.length; i++) {
				System.out.println(calendarMenu[i]);
			}
			System.out.println("\nSelect event options: ");
			String choice = input.nextLine();
			
			if(choice.toLowerCase().equals("view")) {
				System.out.println("Select event to view: ");
				String selection = input.nextLine();
				
				if (eventList.containsKey(selection)) {
					eventList.get(selection).eventDetails();
					
				}
				else {
					System.out.println("Event does not exist.");
				}				
			}
			else if(choice.toLowerCase().equals("share")) {
				System.out.println("Select event to share: ");
				String selection = input.nextLine();
				
				if (eventList.containsKey(selection)) {
					eventList.get(selection).shareEvent();
				}
				else {
					System.out.println("Event does not exist.");
				}				
			}
			else if(choice.toLowerCase().equals("add")) {
				addEvent();
			}
			else if(choice.toLowerCase().equals("remove")) {
				removeEvent();
			}
			else if(choice.toLowerCase().equals("update")) {
				updateEvent();
			}
			else if (choice.toLowerCase().equals("exit")) {
				break;
			}
			else {
				System.out.println("Choice was not valid.");
			}
		}
		
	}
	
	public void addEvent() {
		System.out.println("Event description: ");
		String desc = input.nextLine();
		System.out.println("Event year: ");
		int year = input.nextInt();
		System.out.println("Event month: ");
		int month = input.nextInt();
		System.out.println("Event day: ");
		int dayOfMonth = input.nextInt();
		System.out.println("Hour start time: ");
		int hourStartTime = input.nextInt();
		System.out.println("Minute start time: ");
		int minStartTime = input.nextInt();
		System.out.println("Hour end time: ");
		int hourEndTime = input.nextInt();
		System.out.println("Minute end time: ");
		int minEndTime = input.nextInt();
		
		if (eventList.containsKey(desc)) {
			System.out.println("Same description of existing event.");
		}
		else {
			Event event = new Event(desc, year, month, dayOfMonth, hourStartTime, minStartTime, hourEndTime, minEndTime);
			eventList.put(desc, event);
		}	
	}
	
	public void removeEvent() {
		System.out.println("Type Event to remove: ");
		String desc = input.nextLine();
		
		if (eventList.containsKey(desc)) {
			eventList.remove(desc);
			
		}
		else {
			System.out.println("Event does not exist.");
		}	
		
	}
	
	public void updateEvent() {
		System.out.println("Select Event to update: ");
		String selection = input.nextLine();
		
		if (eventList.containsKey(selection)) {
			System.out.println("Event description: ");
			String desc = input.nextLine();
			System.out.println("Event year: ");
			int year = input.nextInt();
			System.out.println("Event month: ");
			int month = input.nextInt();
			System.out.println("Event day: ");
			int dayOfMonth = input.nextInt();
			System.out.println("Hour start time: ");
			int hourStartTime = input.nextInt();
			System.out.println("Minute start time: ");
			int minStartTime = input.nextInt();
			System.out.println("Hour end time: ");
			int hourEndTime = input.nextInt();
			System.out.println("Minute end time: ");
			int minEndTime = input.nextInt();
			
			if (eventList.containsKey(desc)) {
				System.out.println("Same description of existing event.");
			}
			else {
				eventList.remove(selection);
				Event event = new Event(desc, year, month, dayOfMonth, hourStartTime, minStartTime, hourEndTime, minEndTime);
				eventList.put(desc, event);
			}	
		}
		else {		
			System.out.println("Event does not exist.");
		}
		
		
	}
	

}
