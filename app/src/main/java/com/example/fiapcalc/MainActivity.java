package com.example.fiapcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String materia;
    double media1Sem = -1; // iniciando a variável com -1 para testar se os campos estão preenchidos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tv_materia=(TextView)findViewById(R.id.tv_materia);
        TextView tv_cp1=(TextView)findViewById(R.id.tv_cp1);
        TextView tv_cp2=(TextView)findViewById(R.id.tv_cp2);
        TextView tv_cp3=(TextView)findViewById(R.id.tv_cp3);
        TextView tv_sprint=(TextView)findViewById(R.id.tv_sprint1);
        TextView tv_sprint2=(TextView)findViewById(R.id.tv_sprint2);
        EditText et_materia=(EditText)findViewById(R.id.et_materia);
        EditText et_cp1=(EditText)findViewById(R.id.et_cp1);
        EditText et_cp2=(EditText)findViewById(R.id.et_cp2);
        EditText et_cp3=(EditText)findViewById(R.id.et_cp3);
        EditText et_sprint1=(EditText)findViewById(R.id.et_sprint1);
        EditText et_sprint2=(EditText)findViewById(R.id.et_sprint2);
        EditText et_gs1=(EditText)findViewById(R.id.et_gs1);
        Button btn_calc1sem=(Button)findViewById(R.id.btn_calc1sem);
        TextView tv_media =(TextView)findViewById(R.id.tv_media);
        TextView tv_cp =(TextView)findViewById(R.id.tv_cp);

        btn_calc1sem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txtMedia, txtCPDesconsiderado = "", txtEmpty;
                materia = et_materia.getText().toString();

                // Verificar se os campos estão vazios
                if (materia.equals("") || et_cp1.getText().toString().equals("") ||
                        et_cp2.getText().toString().equals("") || et_cp3.getText().toString().equals("") ||
                        et_sprint1.getText().toString().equals("") || et_sprint2.getText().toString().equals("") ||
                        et_gs1.getText().toString().equals("")) {
                    txtEmpty = "Algum campo não foi preenchido";
                    tv_media.setText(txtEmpty);
                    tv_cp.setText("");
                    media1Sem = -1;
                    return; // Sair do método para evitar exceção
                }else {

                    double cp1 = Double.parseDouble(et_cp1.getText().toString());
                    double cp2 = Double.parseDouble(et_cp2.getText().toString());
                    double cp3 = Double.parseDouble(et_cp3.getText().toString());
                    double sprint1 = Double.parseDouble(et_sprint1.getText().toString());
                    double sprint2 = Double.parseDouble(et_sprint2.getText().toString());
                    double gs = Double.parseDouble(et_gs1.getText().toString());


                    if (cp2 < cp1 && cp2 < cp3) {
                        txtCPDesconsiderado = "CP 2 desconsiderado.";
                        media1Sem = ((cp1 * 1) + (cp3 * 1) + (sprint1 * 1) + (sprint2 * 1) + (gs * 6)) / 10;
                    } else if (cp1 < cp2 && cp1 < cp3) {
                        txtCPDesconsiderado = "CP 1 desconsiderado.";
                        media1Sem = ((cp2 * 1) + (cp3 * 1) + (sprint1 * 1) + (sprint2 * 1) + (gs * 6)) / 10;
                    } else {
                        txtCPDesconsiderado = "CP 3 desconsiderado.";
                        media1Sem = ((cp1 * 1) + (cp2 * 1) + (sprint1 * 1) + (sprint2 * 1) + (gs * 6)) / 10;
                    }

                    // Arredonda o valor para duas casas decimais
                    media1Sem = Math.round(media1Sem * 100.0) / 100.0;

                    txtMedia = "Média em " + materia + " no 1 Sem: " + "\n" + media1Sem + ".";

                    tv_cp.setText(txtCPDesconsiderado);
                    tv_media.setText(txtMedia);


                }
            }
        });

    }

    // intent - ir para a activity 2 sem
    public void calcularMedia2Sem(View v){

        if(media1Sem == -1){
            Toast.makeText(this, "Calcule antes a média do 1º semestre", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent it_calc1Sem = new Intent(this, tela_calc1sem.class);

            // passando parâmetros para a outra activity
            it_calc1Sem.putExtra("p_materia", materia);
            it_calc1Sem.putExtra("p_media1Sem", media1Sem);
            startActivity(it_calc1Sem);
        }
    }


}