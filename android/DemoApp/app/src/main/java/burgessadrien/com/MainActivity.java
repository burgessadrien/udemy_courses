package burgessadrien.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickFunction(View view) {
        EditText myTextField = (EditText) findViewById(R.id.myTextField);
        Double dollarAmount = Double.parseDouble(myTextField.getText().toString());
        dollarAmount *= 0.75;
        Toast.makeText(MainActivity.this, "pounds: " + dollarAmount.toString(), Toast.LENGTH_SHORT).show();
    }

}
