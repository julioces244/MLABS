package com.tecsup.apaza.mts;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.tecsup.apaza.mts.Adapters.MantenimientoAdapter;
import com.tecsup.apaza.mts.Models.Ambiente;
import com.tecsup.apaza.mts.Models.Colegio;
import com.tecsup.apaza.mts.Models.Instrumento;
import com.tecsup.apaza.mts.Models.Mantenimiento;
import com.tecsup.apaza.mts.Service.ApiService;
import com.tecsup.apaza.mts.Service.ApiServiceGenerator;

import java.text.ParseException;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener,OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private RelativeLayout card;
    private GoogleMap mMap;
    private static final String TAG = MapsActivity.class.getSimpleName();
    private UiSettings mUiSettings;
    private TextView nombre,fecha,dias;
    private Button boton;
    private LinearLayout box1,box2,box3, linear;
    private Spinner spinner;
    private String valuespinner;
    private RecyclerView reciclador;

    private View myView;
    private boolean isUp;
    private Integer integervalue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //card = findViewById(R.id.cardinformation);
        boton = findViewById(R.id.boton);
        reciclador = findViewById(R.id.recyclerfechas);
        linear = findViewById(R.id.linearbg);



        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        reciclador.setLayoutManager(layoutManager);
        reciclador.setAdapter(new MantenimientoAdapter());



        //box1 = findViewById(R.id.box1);
        //box2 = findViewById(R.id.box2);
        //box3 = findViewById(R.id.box3);

        spinner = findViewById(R.id.spinnerlabs);

        myView = findViewById(R.id.relative);
        // initialize as invisible (could also do in xml)
        myView.setVisibility(View.INVISIBLE);
        isUp = false;

        //Obteniendo las preferencias guardadas en el LoginActivity
        SharedPreferences sharedPreferences = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("key_name", "defaultValue");
        Integer id = sharedPreferences.getInt("key_id",0);





        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), InstrumentosActivity.class);
                intent.putExtra("valorspinner", valuespinner);
                startActivity(intent);
            }
        });



        nombre = findViewById(R.id.nombre);
        fecha = findViewById(R.id.fecha);
        //dias = findViewById(R.id.dias);

        ParsePosition a = new ParsePosition(5);


        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        //fecha.setText(date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaInicial= null;
        Date fechaFinal= null;
        Date fechaFinal2=null;
        Date fechaFinal3=null;
        try {
            fechaInicial = dateFormat.parse("10-04-2020");
            fechaFinal2 = dateFormat.parse("12-04-2020");
            fechaFinal3 = dateFormat.parse("14-04-2020");
            fechaFinal = dateFormat.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //fechaFinal.getTime();

        int diasdiferencia=(int)((fechaInicial.getTime()-fechaFinal.getTime())/86400000);
        String days = String.valueOf(diasdiferencia);
        //dias.setText("Quedan "+days+" días");

        //if (name.equals("Melissa")) {
         //   box1.setBackgroundColor(Color.parseColor("#33CB7A"));
         //   box2.setBackgroundColor(Color.parseColor("#33CB7A"));
          //  box3.setBackgroundColor(Color.parseColor("#33CB7A"));
        //}



        //String user = getIntent().getStringExtra("usuario");



    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mUiSettings = mMap.getUiSettings();


        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        try {
            mUiSettings.setZoomControlsEnabled(true);

            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.lightstyle_json));


            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( -13.683041, -76.154683),10));
            LatLng ubi = new LatLng(-13.683041, -76.154683);
            LatLng ubi2 = new LatLng( -13.693626, -76.026384);
            LatLng ubi3 = new LatLng(  -13.717681, -76.217158);
            LatLng ubi4 = new LatLng( -13.709627, -76.207343);
            LatLng ubi5 = new LatLng( -13.713474, -76.197473);
            LatLng ubi6 = new LatLng( -13.715412, -76.173966);
            LatLng ubi7 = new LatLng( -13.744443, -75.967713);
            LatLng ubi8 = new LatLng( -13.834388, -76.141539);

            //COLOCANDO IMAGEN EN INFOWINDOWS
            Bitmap.Config conf = Bitmap.Config.ARGB_8888;
            Bitmap bmp = Bitmap.createBitmap(80, 80, conf);
            Canvas canvas1 = new Canvas(bmp);

            // paint defines the text color, stroke width and size
            Paint color = new Paint();
            color.setTextSize(35);
            color.setColor(Color.BLACK);

            // modify canvas
            canvas1.drawBitmap(BitmapFactory.decodeResource(getResources(),
                    R.drawable.ic_school), 0,0, color);
            canvas1.drawText("User Name!", 30, 40, color);




            ApiService service = ApiServiceGenerator.createService(ApiService.class);

            Call<List<Colegio>> call = service.getColegios();

            call.enqueue(new Callback<List<Colegio>>() {
                @Override
                public void onResponse(Call<List<Colegio>> call, Response<List<Colegio>> response) {
                    try {

                        int statusCode = response.code();
                        Log.d(TAG, "HTTP status code: " + statusCode);

                        if (response.isSuccessful()) {

                            List<Colegio> colegios = response.body();
                            Log.d(TAG, "evaluateds: " + colegios);

                            for (Colegio colegio : colegios) {
                                System.out.println(colegio.getNombre());

                                Double latitud = Double.parseDouble(colegio.getLatitud());
                                Double longitud = Double.parseDouble(colegio.getLongitud());

                                LatLng ubi = new LatLng(latitud, longitud);

                                mMap.addMarker(new MarkerOptions()

                                        .position(ubi)
                                        .title(colegio.getNombre())
                                        .icon(BitmapDescriptorFactory
                                                .fromResource(R.drawable.ic_school2))).setTag(colegio.getIdcolegio());

                            }


                        } else {
                            Log.e(TAG, "onError: " + response.errorBody().string());
                            throw new Exception("Error en el servicio");
                        }

                    } catch (Throwable t) {
                        try {
                            Log.e(TAG, "onThrowable: " + t.toString(), t);
                            Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }catch (Throwable x){}
                    }
                }

                @Override
                public void onFailure(Call<List<Colegio>> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.toString());
                    Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }

            });





/*
            mMap.addMarker(new MarkerOptions()
                    .position(ubi)
                    .title("José Carlos Mariátegui")
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.ic_school2))).setTag(0);

*/

            mMap.setOnMarkerClickListener(this);
            mMap.setOnInfoWindowClickListener(this);

            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    //Toast.makeText(MapsActivity.this, "Click cualquier lugar", Toast.LENGTH_LONG).show();

                    if (isUp == true) {
                        slideDown(myView);
                        isUp = !isUp;
                    }else{

                    }
                }
            });


      /*      mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    String dataModel = (String)(marker.getTitle());
                    //String title = (String)dataModel.get("title");

                    //Log.i("Map", "Click en marker : " + title);
                    //card.setVisibility(View.VISIBLE);
                    return false;
                }
            });
*/


            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
        // Position the map's camera near Sydney, Australia.




    }



    public static void animateViewFromBottomToTop(final View view){
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        { @Override public void onGlobalLayout()
        { view.getViewTreeObserver().removeOnGlobalLayoutListener(
                this);
                final int TRANSLATION_Y = view.getHeight();
                view.setTranslationY(TRANSLATION_Y);
                view.setVisibility(View.GONE);
                view.animate()
                        .translationYBy(-TRANSLATION_Y)
                        .setDuration(500)
                        .setStartDelay(200)
                        .setListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationStart(final Animator animation) {
                view.setVisibility(View.VISIBLE);
            } })
                        .start();
        } }); }

    public void slideUp(View view){

        mMap.setPadding(0,0,0, view.getHeight()+15);
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);


    }

    // slide the view from its current position to below itself
    public void slideDown(View view){
        mMap.setPadding(0,0,0, 0);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()+30); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }





    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        if (isUp == false) {
            slideUp(myView);
            isUp = !isUp;
            //myButton.setText("Slide up");
        }else{
            slideUp(myView);
        }






        //isUp = !isUp;


        // Retrieve the data from the marker.
        //Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        /*
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }*/
        String value = String.valueOf(marker.getTag());
        integervalue = Integer.parseInt(value);

        nombre.setText(marker.getTitle());
/*
        if(integervalue==1){
            box1.setBackgroundColor(Color.parseColor("#4F4DA2"));
            box2.setBackgroundColor(Color.parseColor("#4F4DA2"));
            box3.setBackgroundColor(Color.parseColor("#4F4DA2"));
        } else if(integervalue == 2){
            box1.setBackgroundColor(Color.parseColor("#CF9F38"));
            box2.setBackgroundColor(Color.parseColor("#CF9F38"));
            box3.setBackgroundColor(Color.parseColor("#CF9F38"));
        }*/

        marker.getTitle();

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Ambiente>> call = service.getAmbientes(integervalue);

        call.enqueue(new Callback<List<Ambiente>>() {
            @Override
            public void onResponse(Call<List<Ambiente>> call, Response<List<Ambiente>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Ambiente> ambientes = response.body();
                        Log.d(TAG, "evaluateds: " + ambientes);

                        List <String> list = new ArrayList<>();
                        for (Ambiente ambiente : ambientes) {
                            System.out.println(ambiente.getNombre());
                            list.add(ambiente.getCodigo());
                        }

                        ArrayAdapter<String> comboAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, list);
                        comboAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(comboAdapter);



                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                valuespinner= adapterView.getItemAtPosition(i).toString();

                                Toast.makeText(getBaseContext(),
                                        "Selected item" + valuespinner,
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });






                        //FECHAS MANTENIMIENTO
                        initialize();






                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }




                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Ambiente>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }

    private void initialize(){

        ApiService service2 = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Mantenimiento>> call2 = service2.getFechas(integervalue);

        call2.enqueue(new Callback<List<Mantenimiento>>() {
            @Override
            public void onResponse(Call<List<Mantenimiento>> llamada, Response<List<Mantenimiento>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Mantenimiento> mantenimientos = response.body();
                        Log.d(TAG, "mantenimientos: " + mantenimientos);


                        MantenimientoAdapter adapter = (MantenimientoAdapter) reciclador.getAdapter();
                        adapter.setMantenimientos(mantenimientos);
                        adapter.notifyDataSetChanged();

                        /*
                        if(mantenimientos.get(0).getIdmantenimiento()==1){
                            boton.setBackgroundColor(Color.parseColor("#000000"));
                        }else {

                        }*/






                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Mantenimiento>> llamada, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }


}
