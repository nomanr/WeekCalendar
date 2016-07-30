package noman.weekcalendar.decorator;

import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;

/**
 * Created by gokhan on 7/27/16.
 */
public interface DayDecorator {
    void decorate(View view, TextView dayTextView, DateTime dateTime, DateTime firstDayOfTheWeek, DateTime selectedDateTime);
}
