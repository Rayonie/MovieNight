package sg.edu.rp.c346.id21045028.movienight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    Button BtnUpdate,BtnDelete;
    EditText etContentgenre, etContenttitle, etContentYear;
    Spinner ratingSpinner;
    ArrayList<Note> al;
    ArrayAdapter<Note> aa;
    Note data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        BtnUpdate = findViewById(R.id.BtnUpdate);
        BtnDelete = findViewById(R.id.BtnDelete);

        etContentYear = findViewById(R.id.etContentYear);
        etContenttitle = findViewById(R.id.etContentSongtitle);
        etContentgenre = findViewById(R.id.etContentSinger);
        ratingSpinner = findViewById(R.id.rating_spinner_edit);

        Intent i = getIntent();
        data = (Note) i.getSerializableExtra("data");
        String title = data.getTitle();
        String singer = data.getGenre();
        String year = data.getYear();
        String rating = data.getRating();
        etContenttitle.setText(title);
        etContentgenre.setText(singer);
        etContentYear.setText(year);

        if(rating.equals("G")){
            ratingSpinner.setSelection(0);
        }else if(rating.equals("PG")){
            ratingSpinner.setSelection(1);
        }else if(rating.equals("PG13")){
            ratingSpinner.setSelection(2);
        }else if(rating.equals("NC16")){
            ratingSpinner.setSelection(3);
        }else if(rating.equals("M18")){
            ratingSpinner.setSelection(4);
        }else if(rating.equals("R21")){
            ratingSpinner.setSelection(5);
        }else{
            ratingSpinner.setSelection(5);
        }

        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etContenttitle.getText().toString();
                String genre = etContentgenre.getText().toString();
                String Year = etContentYear.getText().toString();
                String agelimit = "";
                if(ratingSpinner.getSelectedItemPosition() == 0){
                    agelimit = "G";
                }else if(ratingSpinner.getSelectedItemPosition() == 1){
                    agelimit = "PG";
                }else if(ratingSpinner.getSelectedItemPosition() == 2){
                    agelimit = "PG13";
                }else if(ratingSpinner.getSelectedItemPosition() == 3){
                    agelimit = "NC16";
                }else if(ratingSpinner.getSelectedItemPosition() == 4){
                    agelimit = "M18";
                }else if(ratingSpinner.getSelectedItemPosition() == 5){
                    agelimit = "R21";
                }
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTitle(title);
                data.setGenre(genre);
                data.setYear(Year);
                data.setRating(agelimit);
                dbh.updateNote(data);
                dbh.close();
                finish();
            }
        });

        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteNote(data.getId());
                finish();
            }
        });

    }
}
