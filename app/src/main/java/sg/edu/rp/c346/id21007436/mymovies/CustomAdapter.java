package sg.edu.rp.c346.id21007436.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movies> movieList;
    String rating;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        ImageView tvRate = rowView.findViewById(R.id.imageViewRating);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre);

        Movies currentMovie = movieList.get(position);

        tvTitle.setText(currentMovie.getTitle());
        tvYear.setText(Integer.toString(currentMovie.getYear()));
        rating = currentMovie.getRating();

        if (rating.equals("G")){
            tvRate.setImageResource(R.drawable.rating_g);
        }else if (rating.equals("PG")){
            tvRate.setImageResource(R.drawable.rating_pg);
        }else if (rating.equals("PG13")){
            tvRate.setImageResource(R.drawable.rating_pg13);
        }else if (rating.equals("NC16")){
            tvRate.setImageResource(R.drawable.rating_nc16);
        }else if (rating.equals("M18")){
            tvRate.setImageResource(R.drawable.rating_m18);
        }else{
            tvRate.setImageResource(R.drawable.rating_r21);
        }
        tvGenre.setText(currentMovie.getGenre());
        return rowView;
    }
}