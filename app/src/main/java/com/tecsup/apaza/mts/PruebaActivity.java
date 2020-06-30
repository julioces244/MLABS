package com.tecsup.apaza.mts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tecsup.apaza.mts.Adapters.InstrumentoAdapter;
import com.tecsup.apaza.mts.Adapters.MantenimientoAdapter;
import com.tecsup.apaza.mts.Models.Instrumento;
import com.tecsup.apaza.mts.Models.Mantenimiento;
import com.tecsup.apaza.mts.Service.ApiService;
import com.tecsup.apaza.mts.Service.ApiServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PruebaActivity extends AppCompatActivity {

    private RecyclerView recy;
    private static final String TAG = PruebaActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        recy = findViewById(R.id.recyclerview);
        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setAdapter(new MantenimientoAdapter());

        initialize();
    }


    private void initialize(){

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Mantenimiento>> call2 = service.getFechas(1);

        call2.enqueue(new Callback<List<Mantenimiento>>() {
            @Override
            public void onResponse(Call<List<Mantenimiento>> llamada, Response<List<Mantenimiento>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Mantenimiento> mantenimientos = response.body();
                        Log.d(TAG, "evaluateds: " + mantenimientos);


                        MantenimientoAdapter adapter = (MantenimientoAdapter) recy.getAdapter();
                        adapter.setMantenimientos(mantenimientos);
                        adapter.notifyDataSetChanged();






                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(PruebaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Mantenimiento>> llamada, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(PruebaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }



}
