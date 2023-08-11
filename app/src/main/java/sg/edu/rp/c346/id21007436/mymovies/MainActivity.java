package sg.edu.rp.c346.id21007436.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShowList;
    EditText etTitle, etGenre, etYear;
    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnEdit);
        btnShowList = findViewById(R.id.btnDelete);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);

        dropdown = findViewById(R.id.spinnerRating);
        String[] items = new String[]{"G", "PG", "PG13", "NC16", "M18", "R21"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);

                String title = etTitle.getText().toString();
                String singers = etGenre.getText().toString();
                String stringYear = etYear.getText().toString();
                int year = Integer.parseInt(stringYear);
                String rating = dropdown.getSelectedItem().toString();

                db.insertSong(title, singers, year, rating);
                Toast.makeText(MainActivity.this,"Successfully insert movie information!",
                        Toast.LENGTH_SHORT).show();

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });
    }
}