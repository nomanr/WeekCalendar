package noman.weekcalendar.eventbus;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;


public final class BusProvider extends Bus {
    private static final BusProvider BUS = new BusProvider();
    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static BusProvider getInstance() {
        return BUS;
    }

    private BusProvider() {
        super(ThreadEnforcer.ANY);
    }


    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    post(event);
                }
            });
        }
    }


}