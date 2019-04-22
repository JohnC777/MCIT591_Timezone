import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.InputEvent;
import javafx.util.StringConverter;

public class TimeSpinner extends Spinner<LocalTime> {
	private String time;
	

    public TimeSpinner(LocalTime time) {
        setEditable(true);

        // Create a StringConverter for converting between the text in the
        // editor and the actual value:

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        StringConverter<LocalTime> localTimeConverter = new StringConverter<LocalTime>() {

            @Override
            public String toString(LocalTime time) {
                return formatter.format(time);
            }

            @Override
            public LocalTime fromString(String string) {
                String[] tokens = string.split(":");
                int hours = getIntField(tokens, 0);
                int minutes = getIntField(tokens, 1) ;
                int seconds = getIntField(tokens, 2);
                int totalSeconds = (hours * 60 + minutes) * 60 + seconds ;
                return LocalTime.of((totalSeconds / 3600) % 24, (totalSeconds / 60) % 60, seconds % 60);
            }

            private int getIntField(String[] tokens, int index) {
                if (tokens.length <= index || tokens[index].isEmpty()) {
                    return 0 ;
                }
                return Integer.parseInt(tokens[index]);
            }

        };

        // The spinner value factory defines increment and decrement by
        // delegating to an editing mode:

        SpinnerValueFactory<LocalTime> value = new SpinnerValueFactory<LocalTime>() {

        // Update the mode when the user interacts with the editor.
     
        };

    }

    public String getTime() {
        return time;
    }
}