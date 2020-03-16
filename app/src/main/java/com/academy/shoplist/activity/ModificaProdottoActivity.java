package com.academy.shoplist.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.academy.shoplist.R;
import com.academy.shoplist.bean.Prodotto;
import com.academy.shoplist.constants.IntentConstant;
import com.academy.shoplist.database.ShoplistDatabaseManager;

public class ModificaProdottoActivity  extends AppCompatActivity {

    String nomeProdotto;
    String descrizioneProdotto;

    private void setContent(){
        final EditText editNomeProdotto = (EditText) findViewById(R.id.editText_nome_prodotto);
        final EditText editDescrizioneProdotto = (EditText) findViewById(R.id.editText_descrizione_prodotto);

        Button btnAggiungi = (Button) findViewById(R.id.modifica_prodotto);
        btnAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomeProdotto = editNomeProdotto.getText().toString();
                descrizioneProdotto = editDescrizioneProdotto.getText().toString();

                if (TextUtils.isEmpty(nomeProdotto) || TextUtils.isEmpty(descrizioneProdotto)){
                    Toast.makeText(ModificaProdottoActivity.this, R.string.campi_non_valorizzati,Toast.LENGTH_LONG).show();
                }else{
                    //right
                    ShoplistDatabaseManager.getInstance(ModificaProdottoActivity.this).addProdotto(new Prodotto(R.drawable.lavatrice,nomeProdotto,descrizioneProdotto));
                    setResult(IntentConstant.RISULTATO_AGGIUNTA_OK);
                    finish();
                }
            }
        });
    }
}
