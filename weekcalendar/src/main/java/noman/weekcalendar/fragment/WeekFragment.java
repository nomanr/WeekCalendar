package noman.weekcalendar.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.ArrayList;

import noman.weekcalendar.R;
import noman.weekcalendar.eventbus.BusProvider;
import noman.weekcalendar.eventbus.Event;

/**
 * Created by nor on 12/4/2015.
 */
public class WeekFragment extends Fragment {
    public static String DATE_KEY = "date_key";
    private GridView gridView;
    private WeekAdapter weekAdapter;
    public static DateTime selectedDateTime = new DateTime();
    public static DateTime CalendarStartDate = new DateTime();
    private DateTime startDate;
    private DateTime endDate;
    private boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_week, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridView);
        init();
        return rootView;
    }

    private void init() {
        ArrayList<DateTime> days = new ArrayList<>();
        DateTime midDate = (DateTime) getArguments().getSerializable(DATE_KEY);
        if (midDate != null) {
            midDate = midDate.withDayOfWeek(DateTimeConstants.THURSDAY);
        }
        //Getting all seven days

        for (int i = -3; i <= 3; i++)
            days.add(midDate != null ? midDate.plusDays(i) : null);

        startDate = days.get(0);
        endDate = days.get(days.size() - 1);

        weekAdapter = new WeekAdapter(getActivity(), days);
        gridView.setAdapter(weekAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusProvider.getInstance().post(new Event.OnDateClickEvent(weekAdapter.getItem
                        (position)));
                selectedDateTime = weekAdapter.getItem(position);
                BusProvider.getInstance().post(new Event.InvalidateEvent());
            }
        });
    }

    @Subscribe
    public void updateSelectedDate(Event.UpdateSelectedDateEvent event) {
        if (isVisible) {
            selectedDateTime = selectedDateTime.plusDays(event.getDirection());
            if (selectedDateTime.toLocalDate().equals(endDate.plusDays(1).toLocalDate())
                    || selectedDateTime.toLocalDate().equals(startDate.plusDays(-1).toLocalDate())) {
                if (!(selectedDateTime.toLocalDate().equals(startDate.plusDays(-1).toLocalDate()) &&
                        event.getDirection() == 1)
                        && !(selectedDateTime.toLocalDate().equals(endDate.plusDays(1)
                        .toLocalDate()) && event.getDirection() == -1))
                    BusProvider.getInstance().post(new Event.SetCurrentPageEvent(event.getDirection()));
            }
            BusProvider.getInstance().post(new Event.InvalidateEvent());
        }
    }


    @Subscribe
    public void invalidate(Event.InvalidateEvent event) {
        gridView.invalidateViews();
    }

    @Subscribe
    public void updateUi(Event.OnUpdateUi event) {
        weekAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        BusProvider.getInstance().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        BusProvider.getInstance().unregister(this);
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isVisible = isVisibleToUser;
        super.setUserVisibleHint(isVisibleToUser);
    }

    public class WeekAdapter extends BaseAdapter {
        private ArrayList<DateTime> days;
        private Context context;
        private DateTime firstDay;

        WeekAdapter(Context context, ArrayList<DateTime> days) {
            this.days = days;
            this.context = context;
        }

        @Override
        public int getCount() {
            return days.size();
        }

        @Override
        public DateTime getItem(int position) {
            return days.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("InflateParams")
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.grid_item, null);
                firstDay = getItem(0);
            }

            DateTime dateTime = getItem(position).withMillisOfDay(0);

            TextView dayTextView = (TextView) convertView.findViewById(R.id.daytext);
            dayTextView.setText(String.valueOf(dateTime.getDayOfMonth()));

            BusProvider.getInstance().post(new Event.OnDayDecorateEvent(convertView, dayTextView,
                    dateTime, firstDay, WeekFragment.selectedDateTime));
            return convertView;
        }
    }


}
