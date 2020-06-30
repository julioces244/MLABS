package com.tecsup.apaza.mts.Service;

import com.tecsup.apaza.mts.Models.Ambiente;
import com.tecsup.apaza.mts.Models.Colegio;
import com.tecsup.apaza.mts.Models.Instrumento;
import com.tecsup.apaza.mts.Models.Mantenimiento;
import com.tecsup.apaza.mts.Models.User;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface ApiService {

    String API_BASE_URL = "http://3.21.186.152/";

    @GET("api/get_colegios")
    Call<List<Colegio>> getColegios();

    @Streaming
    @GET("api/get_fechasmantenimiento/{idcolegio}")
    Call<List<Mantenimiento>> getFechas(@Path("idcolegio") Integer idcolegio);

    @Streaming
        @GET("api/get_colegios/{idcolegio}")
    Call<List<Ambiente>> getAmbientes(@Path("idcolegio") Integer idcolegio);

    @Streaming
    @GET("api/get_instrumentosname/{codigoambiente}")
    Call<List<Instrumento>> getInstrumentosname(@Path("codigoambiente") String codigoambiente);

    @FormUrlEncoded
    @POST("api/login")
    Call<User> login(@Field("email") String email,
                     @Field("password") String password);




}
