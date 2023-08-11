package sg.edu.rp.c346.id21007436.mymovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class editActivity extends AppCompatActivity {
    Button btnEdit, btnDelete, btnCancel;
    EditText etID, etTitle, etGenre, etYear;
    Spinner dropdown;
    Movies movieDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);

        dropdown = findViewById(R.id.spinnerRating);
        String[] items = new String[]{"G", "PG", "PG13", "NC16", "M18", "R21"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Intent i = getIntent();
        movieDetails = (Movies) i.getSerializableExtra("movie");

        etID.setText(movieDetails.getId() + "");
        etID.setEnabled(false);
        etTitle.setText(movieDetails.getTitle());
        etGenre.setText(movieDetails.getGenre());
        etYear.setText(movieDetails.getYear() + "");
        String rating = dropdown.getSelectedItem().toString();
        if (rating.equals("G")){
            dropdown.setSelection(1);
        }else if (rating.equals("PG")){
            dropdown.setSelection(2);
        }else if (rating.equals("PG13")){
            dropdown.setSelection(3);
        }else if (rating.equals("NC16")){
            dropdown.setSelection(4);
        }else if (rating.equals("M18")){
            dropdown.setSelection(5);
        }else{
            dropdown.setSelection(6);
        }

            btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(editActivity.this);
                movieDetails.setTitle(etTitle.getText().toString());
                movieDetails.setGenre(etGenre.getText().toString());
                String stringYear = etYear.getText().toString();
                int year = Integer.parseInt(stringYear);
                movieDetails.setYear(year);
                String rating = dropdown.getSelectedItem().toString();
                if (rating.equals("G")){
                    movieDetails.setRating("G");
                }else if (rating.equals("PG")){
                    movieDetails.setRating("PG");
                }else if (rating.equals("PG13")){
                    movieDetails.setRating("PG13");
                }else if (rating.equals("NC16")){
                    movieDetails.setRating("NC16");
                }else if (rating.equals("M18")){
                    movieDetails.setRating("M18");
                }else{
                    movieDetails.setRating("R21");
                }
                dbh.editMovies(movieDetails);
                dbh.close();
                Toast.makeText(editActivity.this,"Successfully update movie information!",
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(editActivity.this, ShowActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(editActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie "+ movieDetails.getTitle());
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(editActivity.this);
                        dbh.deleteMovies(movieDetails.getId());
                        Toast.makeText(editActivity.this,"Successfully delete movie!",
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(editActivity.this, ShowActivity.class);
                        startActivity(i);
                    }
                });
                myBuilder.setNegativeButton("CANCEL",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(editActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(editActivity.this, ShowActivity.class);
                        startActivity(i);
                    }
                });
                myBuilder.setNegativeButton("DO NO DISCARD",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }

}