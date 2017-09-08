package almekh.com.example.android7281.casadocodigo;

import android.app.Application;

import almekh.com.example.android7281.casadocodigo.CasaDoCodigoComponent;

/**
 * Created by android7281 on 08/09/17.
 */

public class CasaDoCodigoApplication extends Application {

    private CasaDoCodigoComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerCasaDoCodigoComponent.builder().build();
    }

    public CasaDoCodigoComponent getComponent(){
        return component;
    }
}
