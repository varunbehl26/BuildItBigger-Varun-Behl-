package varunbehl.joke_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class JokesMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_main);
        TextView tx_joke = (TextView) findViewById(R.id.tx_joke);
        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(), intent.getStringExtra("joke"), Toast.LENGTH_LONG).show();
        tx_joke.setText(intent.getStringExtra("joke"));
    }
}
