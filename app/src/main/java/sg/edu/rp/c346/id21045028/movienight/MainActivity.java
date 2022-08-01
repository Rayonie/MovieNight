package sg.edu.rp.c346.id21045028.movienight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnShow, btnRetrieve;
    TextView tvDBContent;
    EditText etContentGenre, etContentMovieTitle, etContentYear;
    Spinner rating;
    ArrayList<Note> al;
    ArrayAdapter<Note> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.BtnInsert);
        btnShow = findViewById(R.id.BtnShow);

        etContentYear = findViewById(R.id.etContentYear);
        etContentMovieTitle = findViewById(R.id.etContentSongtitle);
        etContentGenre = findViewById(R.id.etContentSinger);
        rating = (Spinner)findViewById(R.id.rating_spinner);
        //initialize the variables with UI here







        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etContentMovieTitle.getText().toString();
                String genre = etContentGenre.getText().toString();
                String Year = etContentYear.getText().toString();
                String agelimit = "";
                if(rating.getSelectedItemPosition() == 0){
                    agelimit = "G";
                }else if(rating.getSelectedItemPosition() == 1){
                    agelimit = "PG";
                }else if(rating.getSelectedItemPosition() == 2){
                    agelimit = "PG13";
                }else if(rating.getSelectedItemPosition() == 3){
                    agelimit = "NC16";
                }else if(rating.getSelectedItemPosition() == 4){
                    agelimit = "M18";
                }else if(rating.getSelectedItemPosition() == 5){
                    agelimit = "R21";
                }
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertNote(title,genre,Year,agelimit);

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        ShowMovieActivity.class);
                startActivity(i);
            }
        });


    }


}