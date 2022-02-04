package calendar;

import java.util.ArrayList;
import java.util.Scanner;

public class Event {
	static Scanner input = new Scanner(System.in);
	
	private String desc;
	private int year;
	private int month;
	private int dayOfMonth;
	private int hourStartTime;
	private int minStartTime;
	private int hourEndTime;
	private int minEndTime;
	private ArrayList<Users> shareList = new ArrayList<Users>();
	
	public Event(String desc, int year, int month, int dayOfMonth, int hourStartTime, int minStartTime, int hourEndTime, int minEndTime) {
		this.desc = desc;
		this.year = year; 
		this.month = month;
		this.dayOfMonth = dayOfMonth;
		this.hourStartTime = hourStartTime;
		this.minStartTime = minStartTime;
		this.hourEndTime = hourEndTime;
		this.minEndTime = minEndTime;
	}
	
	// helper function to display the event details and all shared users
	public void eventDetails() {
		System.out.println("\nEvent Details");
		System.out.println("Event desc: " + this.desc);
		System.out.println("Event date: " + this.month + "/" + this.dayOfMonth + "/" + this.year);
		System.out.println("Event start time: " + this.hourStartTime + ":" + this.minStartTime);
		System.out.println("Event end time: " + this.hourEndTime + ":" + this.minEndTime);
		System.out.println("Event shared with: ");
		for(int i = 0; i < shareList.size(); i++) {
			System.out.println(shareList.get(i).getUserName());
		}
	}
	
	// function will add users to share with
	public void shareEvent() {
		System.out.println("Input user to share with: ");
		String userName = input.nextLine();
		
		if (shareList.contains(userName)) {
			System.out.println("Event is already shared with user.");
		}
		else {
			Users newUser = new Users(userName);
			shareList.add(newUser);
		}	
	}
	
	
}
