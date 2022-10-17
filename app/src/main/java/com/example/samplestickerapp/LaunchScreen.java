package com.example.samplestickerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LaunchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch_screen);

        //AGREGAR ANIMACIONES
        Animation animacion1 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);

        TextView fromTextView = findViewById(R.id.fromtextView);
        ImageView AzTechLogo = findViewById(R.id.AzTechLogo);
        ImageView logoImageView = findViewById(R.id.imageView);

        fromTextView.setAnimation(animacion2);
        AzTechLogo.setAnimation(animacion2);
        logoImageView.setAnimation(animacion1);

        new Handler().postDelayed(new Runnable() {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(LaunchScreen.this);
            @Override
            public void run() {
                if (user != null && account != null){
                    Intent intent = new Intent(LaunchScreen.this,UserActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(LaunchScreen.this,LoginActivity.class);

                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View, String>(logoImageView, "logoImageTrans");
                    pairs[1] = new Pair<View, String>(AzTechLogo, "textTrans");

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LaunchScreen.this, pairs);
                        startActivity(intent, options.toBundle());
                    }else{
                        startActivity(intent);
                        finish();
                    }

                }

            }
        }, 4000);
    }
}