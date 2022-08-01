package sg.edu.rp.c346.id21045028.movienight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class ShowMovieActivity extends AppCompatActivity {

    Button btnFilter;
    Button btnback;
    Spinner yearFilter;
    ListView SongList;
    ArrayList<Note> al;
    ArrayAdapter<CustomAdapter> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);

        SongList = findViewById(R.id.SongList);
        btnFilter = findViewById(R.id.BtnFilter);
        yearFilter =(Spinner)findViewById(R.id.year_spinner);
        btnback = findViewById(R.id.BtnBack);

        al = new ArrayList<Note>();
        aa = new CustomAdapter(this,
                R.layout.row, al);

        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1980; i <= thisYear ; i++) {
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        yearFilter.setAdapter(adapter);
        yearFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int i, long id) {
                DBHelper dbh = new DBHelper(ShowMovieActivity.this);
                al.clear();
                String filterText = years.get(i);
                if(filterText.length() == 0) {
                    al.addAll(dbh.getAllNotes());
                }
                else{
                    al.addAll(dbh.getAllNotesYear(filterText));
                }
                aa.notifyDataSetChanged();
            }


            public void onNothingSelected(AdapterView<?> parent) {
                DBHelper dbh = new DBHelper(ShowMovieActivity.this);
                al.clear();
                al.addAll(dbh.getAllNotes());
                aa.notifyDataSetChanged();
            }
        });


        SongList.setAdapter(aa);
        SongList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Note data = al.get(position);
                Intent i = new Intent(ShowMovieActivity.this,
                        EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        DBHelper dbh = new DBHelper(ShowMovieActivity.this);
        al.clear();
        al.addAll(dbh.getAllNotes());
        aa.notifyDataSetChanged();



        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowMovieActivity.this);
                al.clear();
                String filterText = "PG13";
                if(filterText.length() == 0) {
                    al.addAll(dbh.getAllNotes());
                }
                else{
                    al.addAll(dbh.getAllNotesRating(filterText));
                }
                aa.notifyDataSetChanged();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ShowMovieActivity.this);
        al.clear();
        al.addAll(dbh.getAllNotes());
        aa.notifyDataSetChanged();
    }
}
