package noman.weekcalendar.listener;

import org.joda.time.DateTime;

/**
 * Created by gokhan on 7/28/16.
 */
public interface OnWeekChangeListener {

    void onWeekChange(DateTime firstDayOfTheWeek, boolean forward);
}
