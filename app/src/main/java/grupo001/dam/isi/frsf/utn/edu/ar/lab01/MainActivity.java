package grupo001.dam.isi.frsf.utn.edu.ar.lab01;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    SeekBar skBar;
    TextView skBarVal,importePlazoFijo,operationMsg;
    EditText correo,cuit,importeInicial;
    Button plazoFijoBtn;
    DecimalFormat df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        df=new DecimalFormat("#.##");
        correo=(EditText)findViewById(R.id.correoET);
        cuit=(EditText)findViewById(R.id.cuitET);

        importeInicial=(EditText)findViewById(R.id.importeInicial);
        skBar=(SeekBar)findViewById(R.id.seekBar1);
        skBarVal=(TextView) findViewById(R.id.seekBarVal);
        importePlazoFijo=(TextView) findViewById(R.id.importePlazoFijo);
        plazoFijoBtn=(Button) findViewById(R.id.plazoFijoBtn);

        operationMsg=(TextView)findViewById(R.id.operationMsg);

        skBarVal.setText("");
        skBarVal.setText("..."+skBar.getProgress());

        importeInicial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!importeInicial.getText().toString().trim().equals("") /*importeInicial.getText().toString()!=""*/) {
                    importePlazoFijo.setText("");
                    importePlazoFijo.setText("$" + df.format(GetInteres()));
                }else{
                    importePlazoFijo.setText("");
                }
            }
        });


        skBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                skBarVal.setText("");
                skBarVal.setText("..."+skBar.getProgress());

                if(!importeInicial.getText().toString().trim().equals("") /*importeInicial.getText().toString()!=""*/) {
                    importePlazoFijo.setText("");
                    importePlazoFijo.setText("$" + df.format(GetInteres()));
                }else{
                    importePlazoFijo.setText("");
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        plazoFijoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!CheckFields()){
                    operationMsg.setText("");
                    operationMsg.setText(R.string.errorMsg);
                    operationMsg.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.ERROR));
                }else{
                    operationMsg.setText("");
                    operationMsg.setText("Plazo fijo realizado. Recibirá "+ df.format(GetImporteTotal())+ " al vencimiento.");
                    operationMsg.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.CORRECTO));
                }
            }
        });
    }

    float GetImporteInteres(float m,float i,int n){

        float a=1+i;
        float b=n/360f;

        float aux= (float) Math.pow(a,b);

        return m*(aux-1);
    }

    boolean CheckFields(){
        String correoStr=correo.getText().toString();
        String cuitStr=cuit.getText().toString();
        String importeStr=importeInicial.getText().toString();
        if(TextUtils.isEmpty(correoStr)||TextUtils.isEmpty(cuitStr)||TextUtils.isEmpty(importeStr)){
            return false;
        }

        return true;
    }
    float GetInteres(){
        String importe=importeInicial.getText().toString();

        float inicial=0f;
        int n=skBar.getProgress();
        float i=0f;

        if(importe!=null || importe!="") {
            inicial = Float.parseFloat(importe);
        }

        if(n>29){
            if(inicial>99999){
                i=Float.parseFloat(getResources().getString(R.string.t10000030));
            }else{
                if(inicial>5000){
                    i=Float.parseFloat(getResources().getString(R.string.t9999930));
                }else{
                    i=Float.parseFloat(getResources().getString(R.string.t500030));
                }
            }
        }else{
            if(inicial>99999){
                i=Float.parseFloat(getResources().getString(R.string.t10000029));
            }else{
                if(inicial>5000){
                    i=Float.parseFloat(getResources().getString(R.string.t9999929));
                }else{
                    i=Float.parseFloat(getResources().getString(R.string.t500029));
                }
            }
        }


        return GetImporteInteres(inicial,i,n);
    }

    float GetImporteTotal(){
        String importeStr=importeInicial.getText().toString();

        float importe=0f;
        int n=skBar.getProgress();
        float i=0f;

        if(importeStr!=null || importeStr!="") {
            importe = Float.parseFloat(importeStr);
        }

        return importe+GetInteres();
    }
}
