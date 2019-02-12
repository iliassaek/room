package com.example.iliass.tp3_room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolHolder> {

    private List<School> schools = new ArrayList<>() ;
    @NonNull
    @Override
    public SchoolHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.school_item, parent , false) ;

        return new SchoolHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolHolder holder, int position) {
        School currentSchool =  schools.get(position) ;
        holder.textViewTitle.setText(currentSchool.getTitle());
        holder.textViewDescription.setText(currentSchool.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentSchool.getPriority()));
    }

    @Override
    public int getItemCount() {
        return schools.size();
    }

    public void setSchools(List<School> schools){
        this.schools = schools ;
        notifyDataSetChanged();
    }

    public School getSchoolAt(int position){
        return schools.get(position) ;

    }

    class SchoolHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle ;
        private TextView textViewDescription ;
        private TextView textViewPriority ;

        public SchoolHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title) ;
            textViewDescription = itemView.findViewById(R.id.text_view_description) ;
            textViewPriority = itemView.findViewById(R.id.text_view_priority) ;
        }
    }
}
