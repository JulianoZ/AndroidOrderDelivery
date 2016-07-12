package codswork.ifspra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_load);
    }

    @Override
    protected void onResume(){
        super.onResume();

        boolean logged_in = true;

        ProgressDialog loading = ProgressDialog.show(this, "Carregando Dados", "Por favor, aguarde...", false, false);

        // Lógica de inserção de dados no programa

        loading.dismiss();
        if(logged_in) {
            Intent i = new Intent(LoadActivity.this, MainActivity.class);
            startActivity(i);

        }else{

        }
    }
}
