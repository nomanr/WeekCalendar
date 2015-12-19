package noman.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.joda.time.DateTime;

import noman.weekcalendar.WeekCalendar;
import noman.weekcalendar.listener.OnDateClickListener;

public class MainActivity extends AppCompatActivity {
    private WeekCalendar weekCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button todaysDate = (Button) findViewById(R.id.today);
        todaysDate.setText(new DateTime().toLocalDate().toString());
        weekCalendar = (WeekCalendar) findViewById(R.id.weekCalendar);
        weekCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(DateTime dateTime) {
                Toast.makeText(MainActivity.this, "You Selected " + dateTime.toString(), Toast
                        .LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_github){
            openGithubRepo();
        }
        return true;
    }

    private void openGithubRepo() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://github.com/nomanr/WeekCalendar"));
        startActivity(intent);
    }

    public void onNextClick(View veiw) {
        weekCalendar.moveToNext();
    }


    public void onPreviousClick(View view) {
        weekCalendar.moveToPrevious();
    }

    public void onResetClick(View view) {
        weekCalendar.setSelectedDate(new DateTime().withDate(2016,12,30));

    }
}
