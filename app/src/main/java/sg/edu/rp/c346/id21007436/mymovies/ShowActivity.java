package sg.edu.rp.c346.id21007436.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    ListView lv;
    Button btnPG13;
    Boolean bPG13;
    ArrayList<Movies> alMovies;
    ArrayAdapter<Movies> aaMovies;
    ArrayAdapter<Movies> aaMoviesPG13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        lv = findViewById(R.id.lvSong);
        btnPG13 = findViewById(R.id.btn5star);
        bPG13 = false;

        DBHelper db = new DBHelper(ShowActivity.this);

        alMovies = db.getMovies();
        db.close();
        ArrayList<Movies> alPG13 = new ArrayList<Movies>();
        for ( int i = 0; i < alMovies.size(); i++ ){
            if (alMovies.get(i).getRating().equals("PG13")) {
                alPG13.add(alMovies.get(i));
            }
        }

        aaMovies = new CustomAdapter(this, R.layout.row, alMovies);
        aaMoviesPG13 = new CustomAdapter(this, R.layout.row, alPG13);
        lv.setAdapter(aaMovies);

        btnPG13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bPG13 = true;
                lv.setAdapter(aaMoviesPG13);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                if (bPG13 == false){
                    Movies movieDetail = alMovies.get(position);
                    Intent i = new Intent(ShowActivity.this, editActivity.class);
                    i.putExtra("movie", movieDetail);
                    startActivity(i);
                }else{
                    Movies movieDetail = alPG13.get(position);
                    Intent i = new Intent(ShowActivity.this, editActivity.class);
                    i.putExtra("movie", movieDetail);
                    startActivity(i);
                }
            }
        });
    }
}