package almekh.com.example.android7281.casadocodigo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import almekh.com.example.android7281.casadocodigo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android7281 on 08/09/17.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    boolean flagUsuarioLogado = false;


    @BindView(R.id.login_email)
    TextView email;

    @BindView(R.id.login_senha)
    TextView senha;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null && !flagUsuarioLogado){
                    flagUsuarioLogado = true;
                    Intent vaiParaMain = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(vaiParaMain);
                    finish();
                }
            }
        };

    }

    @OnClick(R.id.login_logar)
    public void logar(){

        String email = this.email.getText().toString().trim();
        String senha = this.senha.getText().toString().trim();

        if (!email.isEmpty() || !senha.isEmpty()) {

            mAuth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail", task.getException());
                                Snackbar.make(LoginActivity.this.email, "Acesso não autorizado, verifique suas " +
                                        "informações", Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Snackbar.make(this.senha, "Por favor complete todos os campos", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null){
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }
}
