package com.example.ygodeckbuilder;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CardList extends AppCompatActivity {

    Button buscar;
    EditText cardName;

    ImageView cardImageView;

    TextView urlCard;
    TextView idCard;
    String apiUrl = "https://db.ygoprodeck.com/api/v7/";

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_card_list);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            cardImageView = findViewById(R.id.imageView_card);
            cardName = findViewById(R.id.editText_cardName);
            buscar = findViewById(R.id.button_search);

            urlCard = findViewById(R.id.urlCard);
            idCard = findViewById(R.id.idCard);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = cardName.getText().toString().trim().toLowerCase();

                ApiService apiService = retrofit.create(ApiService.class);
                Call<CardInfoResponse> call = apiService.getCardInfo(name);

                call.enqueue(new Callback<CardInfoResponse>()  {
                    @Override
                    public void onResponse(Call<CardInfoResponse> call, Response<CardInfoResponse> response) {
                        if (response.isSuccessful()) {
                            CardInfoResponse cardInfoResponse = response.body();
                            if (cardInfoResponse != null && cardInfoResponse.getData().size() > 0) {
                                CardData cardData = cardInfoResponse.getData().get(0);
                                int cardId = cardData.getId();
                                idCard.setText("ID de la tarjeta: " + cardId);
                            }
                        } else {
                            // Manejo de errores
                            Toast.makeText(CardList.this, "Error en el servidor: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CardInfoResponse> call, Throwable t) {
                        if (t instanceof IOException) {
                            // Error de red
                            Toast.makeText(CardList.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            // Otro tipo de error
                            Toast.makeText(CardList.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        // Registra el error en Logcat para depuración
                        Log.e("Retrofit", "Error en la solicitud: " + t.getMessage(), t);
                    }

                });

            }
        });

    }
}