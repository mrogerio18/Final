package com.example.afinal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button botaoRecuperar;
    private TextView textoResultado;
    private TextView uf, cidade, logradouro;
    private Retrofit retrofit;
    //private List<Foto> fotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uf = findViewById(R.id.etUF);
        cidade = findViewById(R.id.etCidade);
        logradouro = findViewById(R.id.etLogradouro);

        botaoRecuperar = findViewById(R.id.btnRecuperar);
        textoResultado = findViewById(R.id.texto);

        String url = "https://viacep.com.br/ws/";
        //String urlCep = "https://viacep.com.br/ws/";
//        String urlCep = "https://jsonplaceholder.typicode.com";
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarCep();
            }
        });
    }

    private void recuperarCep() {
        CEPService cepService = retrofit.create(CEPService.class);
        Call<List<CEP>> call = cepService.recuperarCEP((uf.getText().toString()), (cidade.getText().toString()), (logradouro.getText().toString()));
        //Call<List<CEP>> call = cepService.recuperarCEP("RS", "Porto Alegre", "Domingos");
        call.enqueue(new Callback<List<CEP>>() {
            @Override
            public void onResponse(Call<List<CEP>> call, Response<List<CEP>> response) {
                if (response.isSuccessful()) {
                    List<CEP> cep = response.body();
                    String enderecos = "";
                    for (int i=0; i< cep.size(); i++){
                        enderecos += cep.get(i).getLogradouro()+"\n";
                    }
                    textoResultado.setText(enderecos);
                }
            }

            @Override
            public void onFailure(Call<List<CEP>> call, Throwable t) {
                Log.d("Erro", t.getMessage());
            }
        });
    }
}
//    private void salvarFotoRetrofit(){
//        Foto foto = new Foto("Viagens", "2", "Porto Velho", "https://unir.com", "null");
//        DataService dataService = retrofit.create(DataService.class);
//        Call<Foto> call = dataService.salvarFoto(foto);
//        call.enqueue(new Callback<Foto>() {
//            @Override
//            public void onResponse(Call<Foto> call, Response<Foto> response) {
//                if(response.isSuccessful()){
//                    Foto fotoResposta = response.body();
//                    textoResultado.setText("Código: "+response.code()+ " ID: "
//                            + fotoResposta.getId()
//                            + "Título: " + fotoResposta.getTitle());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Foto> call, Throwable t) {
//
//            }
//        });
//    }
//    private void atualizarFoto(){
//        DataService dataService = retrofit.create(DataService.class);
//        Foto foto = new Foto("Viagens", "2", "Porto Velho", "https://unir", "null");
//        Call<Foto> call = dataService.atualizarFoto(2,foto);
//        call.enqueue(new Callback<Foto>() {
//            @Override
//            public void onResponse(Call<Foto> call, Response<Foto> response) {
//                if(response.isSuccessful()){
//                    Foto fotoResposta = response.body();
//                    textoResultado.setText("Código Status: "+response.code()+ "ID: "+ fotoResposta.getId()+ "Titulo: "
//                            + fotoResposta.getTitle()+" Album: " +fotoResposta.getAlbumID());
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Foto> call, Throwable t) {
//
//            }
//        });
//    }
//    private void atualizarFotoPatch(){
//        DataService dataService = retrofit.create(DataService.class);
//        Foto foto = new Foto("Viagens", "2", "Porto Velho", "https://unir", "null");
//        Call<Foto> call = dataService.atualizarFotoPath(2,foto);
//        call.enqueue(new Callback<Foto>() {
//            @Override
//            public void onResponse(Call<Foto> call, Response<Foto> response) {
//                if(response.isSuccessful()){
//                    Foto fotoResposta = response.body();
//                    textoResultado.setText("Código Status: "+response.code()+ "ID: "
//                            + fotoResposta.getId()
//                            + "Titulo: " + fotoResposta.getTitle()+" Album: " +fotoResposta.getAlbumID()
//                    +"ThumbnailURL: " + fotoResposta.getThumbnailUrl());
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Foto> call, Throwable t) {
//
//            }
//        });
//    }
//    private void removerFotoRetrofit(){
//        DataService dataService = retrofit.create(DataService.class);
//        Call<Void> call = dataService.removerFoto(2);
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if(response.isSuccessful()){
//                    textoResultado.setText("Status:" + response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//            }
//        });

