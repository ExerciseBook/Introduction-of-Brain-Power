package wiki.lhr.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.content.*;
import android.os.Bundle;
import android.view.View;

public class screen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);
    }

    public void button_back_clicked(View view){
        Intent intent = new Intent(screen1.this, MainActivity.class);
        startActivity(intent);
        screen1.this.finish();
    }

}
