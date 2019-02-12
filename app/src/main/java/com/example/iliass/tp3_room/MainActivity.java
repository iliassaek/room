package com.example.iliass.tp3_room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST = 1;
    private SchoolViewModel schoolViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this ,AddSchoolActivity.class) ;
                startActivityForResult(intent , ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        final SchoolAdapter adapter = new SchoolAdapter() ;
        recyclerView.setAdapter(adapter);

        schoolViewModel = ViewModelProviders.of(this).get(SchoolViewModel.class) ;
        schoolViewModel.getAllSchools().observe(this, new Observer<List<School>>() {
            @Override
            public void onChanged(List<School> schools) {
                adapter.setSchools(schools);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 ,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                schoolViewModel.delete(adapter.getSchoolAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "School deleted" , Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddSchoolActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddSchoolActivity.EXTRA_DESCRIPTION);
            int priority  = data.getIntExtra(AddSchoolActivity.EXTRA_PRIORITY , 1) ;

            School school = new School(title, description , priority);
            schoolViewModel.insert(school);

            Toast.makeText(this,"school saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this , "school not saved" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater() ;
        menuInflater.inflate(R.menu.main_menu, menu);

        return true ;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.delete_all_notes:
                schoolViewModel.deleteAllSchools();
                Toast.makeText(this , "All schools deleted" , Toast.LENGTH_SHORT).show() ;
                return true ;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
