package noman.weekcalendar.decorator;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;

import noman.weekcalendar.R;
import noman.weekcalendar.fragment.WeekFragment;

/**
 * Created by gokhan on 7/27/16.
 */
public class DefaultDayDecorator implements DayDecorator {

    private Context context;
    private final int selectedDateColor;
    private final int todayDateColor;
    private int todayDateTextColor;
    private int textColor;
    private float textSize;

    public DefaultDayDecorator(Context context,
                               @ColorInt int selectedDateColor,
                               @ColorInt int todayDateColor,
                               @ColorInt int todayDateTextColor,
                               @ColorInt int textColor,
                               float textSize) {
        this.context = context;
        this.selectedDateColor = selectedDateColor;
        this.todayDateColor = todayDateColor;
        this.todayDateTextColor = todayDateTextColor;
        this.textColor = textColor;
        this.textSize = textSize;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void decorate(View view, TextView dayTextView,
                         DateTime dateTime, DateTime firstDayOfTheWeek, DateTime selectedDateTime) {
        //DateTime dt = new DateTime();

        Drawable holoCircle = ContextCompat.getDrawable(context, R.drawable.holo_circle);
        Drawable solidCircle = ContextCompat.getDrawable(context, R.drawable.solid_circle);

        holoCircle.setColorFilter(selectedDateColor, PorterDuff.Mode.SRC_ATOP);
        solidCircle.setColorFilter(todayDateColor, PorterDuff.Mode.SRC_ATOP);
        // solidCircle.mutate().setAlpha(200);
        //holoCircle.mutate().setAlpha(200);


        if (firstDayOfTheWeek.getMonthOfYear() < dateTime.getMonthOfYear()
                || firstDayOfTheWeek.getYear() < dateTime.getYear())
            dayTextView.setTextColor(Color.GRAY);

        DateTime calendarStartDate = WeekFragment.CalendarStartDate;

        if (selectedDateTime != null) {
            if (selectedDateTime.toLocalDate().equals(dateTime.toLocalDate())) {
                if (!selectedDateTime.toLocalDate().equals(calendarStartDate.toLocalDate()))
                    dayTextView.setBackground(holoCircle);
            } else {
                dayTextView.setBackground(null);
            }
        }

        if (dateTime.toLocalDate().equals(calendarStartDate.toLocalDate())) {
            dayTextView.setBackground(solidCircle);
            dayTextView.setTextColor(this.todayDateTextColor);
        } else {
            dayTextView.setTextColor(textColor);
        }
        float size = textSize;
        if (size == -1)
            size = dayTextView.getTextSize();
        dayTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }
}
