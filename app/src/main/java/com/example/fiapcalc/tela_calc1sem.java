package com.example.fiapcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class tela_calc1sem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_calc1sem);

        TextView tv_cp4=(TextView)findViewById(R.id.tv_cp4);
        TextView tv_cp5=(TextView)findViewById(R.id.tv_cp5);
        TextView tv_cp6=(TextView)findViewById(R.id.tv_cp6);
        TextView tv_sprint3=(TextView)findViewById(R.id.tv_sprint3);
        TextView tv_sprint4=(TextView)findViewById(R.id.tv_sprint4);
        TextView tv_gs2=(TextView)findViewById(R.id.tv_gs2);
        TextView tv_cpDesconsiderado=(TextView)findViewById(R.id.tv_cpDesconsiderado);
        TextView tv_media2Sem=(TextView)findViewById(R.id.tv_media2Sem);
        TextView tv_mediaFinal=(TextView)findViewById(R.id.tv_mediaFinal);
        TextView tv_resume=(TextView)findViewById(R.id.tv_resume);
        EditText et_cp4=(EditText)findViewById(R.id.et_cp4);
        EditText et_cp5=(EditText)findViewById(R.id.et_cp5);
        EditText et_cp6=(EditText)findViewById(R.id.et_cp6);
        EditText et_sprint3=(EditText)findViewById(R.id.et_sprint3);
        EditText et_sprint4=(EditText)findViewById(R.id.et_sprint4);
        EditText et_gs2=(EditText)findViewById(R.id.et_gs2);
        Button btn_calc2sem=(Button)findViewById(R.id.btn_calc2Sem);


        btn_calc2sem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pegando os parâmetros da activity 1
                Intent it_calc1Sem=getIntent();
                String materia = it_calc1Sem.getStringExtra("p_materia");
                double media1Sem = it_calc1Sem.getDoubleExtra("p_media1Sem", 0);


                double media2Sem, mediaFinal;
                String txtMediaFinal, txtMedia2Sem, txtCPDesconsiderado, txtResume;

                // Verificar se os campos estão vazios
                if (materia.equals("") || et_cp4.getText().toString().equals("") ||
                        et_cp5.getText().toString().equals("") || et_cp6.getText().toString().equals("") ||
                        et_sprint3.getText().toString().equals("") || et_sprint4.getText().toString().equals("") ||
                        et_gs2.getText().toString().equals("")) {
                    txtResume  = "Algum campo não foi preenchido";
                    tv_resume.setText(txtResume);
                    tv_cpDesconsiderado.setText("");
                    tv_media2Sem.setText("");
                    tv_mediaFinal.setText("");
                    return; // Sair do método para evitar exceção
                }else {

                    double cp4 = Double.parseDouble(et_cp4.getText().toString());
                    double cp5 = Double.parseDouble(et_cp5.getText().toString());
                    double cp6 = Double.parseDouble(et_cp6.getText().toString());
                    double sprint3 = Double.parseDouble(et_sprint3.getText().toString());
                    double sprint4 = Double.parseDouble(et_sprint4.getText().toString());
                    double gs2 = Double.parseDouble(et_gs2.getText().toString());

                    if (cp4 < cp5 && cp4 < cp6) {
                        txtCPDesconsiderado = "CP 4 desconsiderado.";
                        media2Sem = ((cp5 * 1) + (cp6 * 1) + (sprint3 * 1) + (sprint4 * 1) + (gs2 * 6)) / 10;
                    } else if (cp5 < cp4 && cp5 < cp6) {
                        txtCPDesconsiderado = "CP 5 desconsiderado.";
                        media2Sem = ((cp4 * 1) + (cp6 * 1) + (sprint3 * 1) + (sprint4 * 1) + (gs2 * 6)) / 10;
                    } else {
                        txtCPDesconsiderado = "CP 6 desconsiderado.";
                        media2Sem = ((cp4 * 1) + (cp5 * 1) + (sprint3 * 1) + (sprint4 * 1) + (gs2 * 6)) / 10;
                    }

                    //media2Sem = Math.round(media2Sem * 100.0) / 100.0;
                    mediaFinal = ((media1Sem * 4) + (media2Sem * 6)) / 10;
                    mediaFinal = Math.round(mediaFinal * 100.0) / 100.0;

                    if (mediaFinal >= 6) {
                        txtResume = "Aprovado(a) :)";
                    } else {
                        txtResume = "Reprovado(a) :(";
                    }
                    txtMedia2Sem = "Média 2º semestre em " + materia + ": " + media2Sem + ".";
                    txtMediaFinal = "Sua média final nessa matéria" + ": " + mediaFinal + ".";
                    tv_cpDesconsiderado.setText(txtCPDesconsiderado);
                    tv_media2Sem.setText(txtMedia2Sem);
                    tv_mediaFinal.setText(txtMediaFinal);
                    tv_resume.setText(txtResume);

                }
            }
        });







    }
}