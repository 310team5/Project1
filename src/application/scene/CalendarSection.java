package application.scene;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


import java.time.LocalDate;
import java.time.LocalTime;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;


public class CalendarSection{
	
	@FXML
	private VBox calendarBox;

	
	@FXML
	public void initialize(){
		
		CalendarView calendarView = new CalendarView();
		Calendar holidays = new Calendar("Holidays");
		CalendarSource source = new CalendarSource("My Calendars");
		source.getCalendars().add(holidays);
		calendarView.getCalendarSources().add(source);
		calendarView.setRequestedTime(LocalTime.now());
		
		Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
			@Override
			public void run() {
				while(true) {
					Platform.runLater(() -> {
						calendarView.setToday(LocalDate.now());
						calendarView.setTime(LocalTime.now());
					});
					try {
						sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		updateTimeThread.setPriority(Thread.MIN_PRIORITY);
		updateTimeThread.setDaemon(true);
		updateTimeThread.start();
		
		
		calendarBox.getChildren().add(calendarView);
		
		 
		
		
		
	}

	/** Click handler for the dashboard button. */
	@FXML
	private void dashboard() {
		SceneManager.switchToDashboardScene();
	}

	/** Click handler for the notes button. */
	@FXML
	private void notes() {
		SceneManager.switchToNotesScene();
	}

	/** Click handler for the todo button. */
	@FXML
	private void todo() {
		SceneManager.switchToTodoScene();
	}

	/** Click handler for the drawboard button. */
	@FXML
	private void drawboard() {
		SceneManager.switchToDrawboardScene();
	}
	
	@FXML
	private void calendar() {
		SceneManager.switchToCalendarScene();
	}

	


}

