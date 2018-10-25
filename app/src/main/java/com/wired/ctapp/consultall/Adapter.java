package com.wired.ctapp.consultall;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wired.ctapp.consultall.com.wired.ctapp.consultall.utils.Project;
import com.wired.ctapp.consultall.projects.ResponderActivity;

import java.util.ArrayList;

/**
 * Created by Isaac on 10/23/2018.
 */

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Project> data;
    private Context context;

    private static class Holder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView address;
        public TextView description;
        public ImageButton add;
        public ImageButton delete;

        public Holder(View v){
            super(v);
            name = (TextView) v.findViewById(R.id.valueName);
            address = (TextView) v.findViewById(R.id.valueAddress);
            description = (TextView) v.findViewById(R.id.description);
            add = (ImageButton) v.findViewById(R.id.view_project);
            delete = (ImageButton) v.findViewById(R.id.delete_project);
        }
    }

    public Adapter(Context context){
        this.context = context;
        data = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.project, parent, false);
        final Holder h = new Holder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra(ResponderActivity.PROJECT_NAME, h.name.getText().toString());
                context.startActivity(i);
            }
        });
        return h;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder h = (Holder)holder;
        h.name.setText(data.get(position).getName());
        h.address.setText(data.get(position).getAddress());
        h.description.setText(data.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addProject(Project project){
        data.add(project);
        notifyDataSetChanged();
    }
}
