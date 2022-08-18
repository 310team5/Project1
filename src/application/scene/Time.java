package application.scene;

public class Time {

    private int minutes;
    private int seconds;

    public Time(){}

    public int getSeconds(){
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setCurrentTime(String currentTime){
        String[] time = currentTime.split(":");
        minutes = Integer.parseInt(time[0]);
        seconds = Integer.parseInt(time[1]);
    }

    public String getCurrentTime(){
        String minutes_string = String.format("%02d", minutes);
        String seconds_string = String.format("%02d", seconds);
        return minutes_string + ":" + seconds_string;
    }

    public void secondCountDown(){
        seconds--;
        if (seconds < 0){
            minutes--;
            seconds = 59;
        }
    }

}
