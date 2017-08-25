package grupo001.dam.isi.frsf.utn.edu.ar.lab01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar skBar;
    TextView skBarVal,importePlazoFijo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skBar=(SeekBar)findViewById(R.id.seekBar1);
        //skBar.setMin(1);
        skBarVal=(TextView) findViewById(R.id.seekBarVal);
        importePlazoFijo=(TextView) findViewById(R.id.importePlazoFijo);
        skBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                skBarVal.setText("");
                skBarVal.setText("..."+skBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    float GetImportePlazoFijo(int capital,int interes,int taza, int plazo){
        float i=(float)capital;
        //i=i*(1f-(interes/100f))
        return i;
    }
}
