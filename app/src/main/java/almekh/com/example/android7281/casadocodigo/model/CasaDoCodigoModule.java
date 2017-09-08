package almekh.com.example.android7281.casadocodigo.model;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import javax.inject.Singleton;

import almekh.com.example.android7281.casadocodigo.R;
import dagger.Module;
import dagger.Provides;

/**
 * Created by android7281 on 08/09/17.
 */

@Module
public class CasaDoCodigoModule {

    @Provides
    @Singleton
    public Carrinho gCarrinho(){
        return new Carrinho();
    }

    @Singleton
    @Provides
    FirebaseRemoteConfig firebaseRemoteConfig(FirebaseRemoteConfigSettings settings) {
        FirebaseRemoteConfig config = FirebaseRemoteConfig.getInstance();
        config.setDefaults(R.xml.remote_config);
        config.setConfigSettings(settings);
        return  config;
    }

    @Provides
    FirebaseRemoteConfigSettings provideConfigSettings(){
        FirebaseRemoteConfigSettings settings = new FirebaseRemoteConfigSettings.Builder().
                setDeveloperModeEnabled(true).build();
        return settings;
    }
}
