package com.example.afinal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

        //@GET("76801160/json/")
        //Call<CEP> recuperarCEP();
        @GET("{estado}/{municipio}/{localidade}/json/")
        Call<List<CEP>> recuperarCEP(@Path("estado") String estado, @Path("municipio") String municipio, @Path("localidade") String localidade);
    }

