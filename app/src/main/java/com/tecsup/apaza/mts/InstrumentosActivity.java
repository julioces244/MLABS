package com.tecsup.apaza.mts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tecsup.apaza.mts.Adapters.InstrumentoAdapter;
import com.tecsup.apaza.mts.Models.Instrumento;
import com.tecsup.apaza.mts.Service.ApiService;
import com.tecsup.apaza.mts.Service.ApiServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstrumentosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    private Button btn_ins;
    private static final String TAG = InstrumentosActivity.class.getSimpleName();
    private String codigoambiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrumentos);



        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new InstrumentoAdapter());


        codigoambiente = getIntent().getStringExtra("valorspinner");


        initialize();
        btn_ins = findViewById(R.id.btn_instrumento);

        btn_ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });



    }

    private ArrayList<Instrumento> getitems(){
        ArrayList<Instrumento> ListItems = new ArrayList<>();
        ListItems.add(new Instrumento(1,"Computadora","SERIEX92-11",R.drawable.img_monitor));
        ListItems.add(new Instrumento(2,"Computadora","SERIEY32-12",R.drawable.img_monitor));
        ListItems.add(new Instrumento(3,"Monitor","SERIECE3-13",R.drawable.img_monitor));
        ListItems.add(new Instrumento(4,"Monitor","SERIECE3-13",R.drawable.img_monitor));
        ListItems.add(new Instrumento(5,"Monitor","SERIECE3-13",R.drawable.img_monitor));
        ListItems.add(new Instrumento(6,"Monitor","SERIECE3-13",R.drawable.img_monitor));
        ListItems.add(new Instrumento(7,"Monitor","SERIECE3-13",R.drawable.img_monitor));



        return ListItems;
    }


    private void initialize(){

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Instrumento>> call = service.getInstrumentosname(codigoambiente);

        call.enqueue(new Callback<List<Instrumento>>() {
            @Override
            public void onResponse(Call<List<Instrumento>> call, Response<List<Instrumento>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Instrumento> evaluateds = response.body();
                        Log.d(TAG, "evaluateds: " + evaluateds);


                        InstrumentoAdapter adapter = (InstrumentoAdapter) recyclerView.getAdapter();
                        adapter.setInstrumentos(evaluateds);
                        adapter.notifyDataSetChanged();





                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(InstrumentosActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Instrumento>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(InstrumentosActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }


}
