package wiki.lhr.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.content.*;
import android.widget.*;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void button_1_clicked(View view){
        TextView MainTitle=findViewById(R.id.MainTitle);
        MainTitle.setText("LET THE BASS KICK");
        Toast.makeText(MainActivity.this, "O-oooooooooo AAAAE-A-A-I-A-U- JO-oooooooooooo AAE-O-A-A-U-U-A- E-eee-ee-eee AAAAE-A-E-I-E-A-JO-ooo-oo-oo-oo EEEEO-A-AAA-AAAA", Toast.LENGTH_SHORT).show();
    }

    public void button_2_clicked(View view){
        Intent intent = new Intent(MainActivity.this, screen1.class);
        startActivity(intent);
        MainActivity.this.finish();
    }

}
