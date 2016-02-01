package noman.weekcalendar.eventbus;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;


public final class BusProvider extends Bus {

    private static final Handler handler = new Handler(Looper.getMainLooper());

    private static BusProvider mInstance = null;

    private BusProvider() {
        super(ThreadEnforcer.ANY);
    }

    public static BusProvider getInstance(){
        if(mInstance == null)
        {
            mInstance = new BusProvider();
        }
        return mInstance;
    }

    public static void disposeInstance() { mInstance = null; }

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