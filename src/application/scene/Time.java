package application.scene;

public class Time {
	private static final int MINIMUM_SECONDS_COUNT = 0;
	private static final int MAXIMUM_SECONDS_COUNT = 59;
	
    private int minutes;
    private int seconds;
    

    public Time(){}

    public int getSeconds(){
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    /**
     * Sets minutes and seconds by the given current time
     * @param currentTime - current time given in mm:ss format
     */
    public void setCurrentTime(String currentTime){
        String[] time = currentTime.split(":");
        minutes = Integer.parseInt(time[0]);
        seconds = Integer.parseInt(time[1]);
    }

    /**
     * Gets minutes and seconds as mm:ss formatted string
     * @return current time in mm:ss format
     */
    public String getCurrentTime(){
        String minutes_string = String.format("%02d", minutes);
        String seconds_string = String.format("%02d", seconds);
        return minutes_string + ":" + seconds_string;
    }

    public void secondCountDown(){
        seconds--;
        if (seconds < MINIMUM_SECONDS_COUNT){
            minutes--;
            seconds = MAXIMUM_SECONDS_COUNT;
        }
    }

}
