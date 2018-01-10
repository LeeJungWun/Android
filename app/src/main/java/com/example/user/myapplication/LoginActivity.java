package com.example.user.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class LoginActivity extends AppCompatActivity {

    private EditText id;
    private  EditText password;

    private Button login;
    private Button signin;
    private FirebaseRemoteConfig FirebaseRemoteConfig;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        firebaseAuth.signOut();

        String splash_backgroud = FirebaseRemoteConfig.getString(getString(R.string.rc_color));
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(Color.parseColor(splash_backgroud));
        }

        id = (EditText)findViewById(R.id.loginActivity_edittext_id);
        password = (EditText)findViewById(R.id.loginActivity_edittext_password);

        login = (Button)findViewById(R.id.loginActivity_button_login);
        signin = (Button)findViewById(R.id.loginActivity_button_signin);
        login.setBackgroundColor(Color.parseColor(splash_backgroud));
        signin.setBackgroundColor(Color.parseColor(splash_backgroud));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginEvent();
            }
        });

        signin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(LoginActivity.this, SigninActivity.class));
            }
        });

        //로그인 인터페이스 리스터
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //로그인
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    //로그아웃
                }
            }
        };
    }

    void loginEvent(){
        firebaseAuth.signInWithEmailAndPassword(id.getText().toString(), password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            //로그인 실패했을 때
                            Toast.makeText(LoginActivity.this, task.getException().getMessage() ,Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}
