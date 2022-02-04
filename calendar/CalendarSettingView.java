package calendar;

import java.util.Scanner;
import java.util.TimeZone;

public class CalendarSettingView {
	static Scanner input = new Scanner(System.in);
	private String currentTimeZone;
	private boolean darkTheme;
	
	private TimeZone tz = TimeZone.getDefault();

	
	public CalendarSettingView() {
		this.currentTimeZone = tz.getID();
		this.darkTheme = false;
	}
	
	public String getTimeZone() {
		return this.currentTimeZone;
	}
	public boolean getDarkTheme() {
		return this.darkTheme;
	}
	public void changeTimeZone() {
		System.out.println("Change timezone to: ");
		String zone = input.nextLine();
		
		this.tz = TimeZone.getTimeZone(zone);
		this.currentTimeZone = tz.getID();
	}
	public void changeTheme() {
		if (this.darkTheme == false) {
			this.darkTheme = true;
		}
		else {
			this.darkTheme = false;
		}
	}

}
