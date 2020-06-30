package com.tecsup.apaza.mts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tecsup.apaza.mts.Models.User;
import com.tecsup.apaza.mts.Service.ApiService;
import com.tecsup.apaza.mts.Service.ApiServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;

    private static final String TAG = LoginActivity.class.getSimpleName();
    public static final String PREFS_NAME = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.tvemail);
        password = findViewById(R.id.tvpassword);
        login = findViewById(R.id.btlogin);


        final String usuario = email.getText().toString();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gomainview();
            }
        });



    }

    public void gomainview(){

        String user  = email.getText().toString();
        String pass = password.getText().toString();

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<User> call = null;

        call = service.login(user, pass);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        User responseMessage = response.body();
                        String userName =  responseMessage.getName();
                        Integer id = responseMessage.getIdUser();



                        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("key_id",id);
                        editor.putString("key_name",userName);
                        editor.apply();

                        startActivity(new Intent(LoginActivity.this, MapsActivity.class));
                        finish();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Correo y/o contraseña incorrectos");

                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(LoginActivity.this, "Oops! Parece que la conexión fallo.", Toast.LENGTH_LONG).show();
            }

        });


    }
}
