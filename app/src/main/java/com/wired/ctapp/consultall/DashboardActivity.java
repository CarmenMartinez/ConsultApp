package com.wired.ctapp.consultall;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wired.ctapp.consultall.com.wired.ctapp.consultall.utils.Project;

public class DashboardActivity extends AppCompatActivity {

    public Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.project_add_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addProject = new Intent(getApplicationContext(), CaptureProjectActivity.class);
                startActivityForResult(addProject, 0);
            }
        });

        /* Use this setting to improve performance if you know that changes
         in content do not change the layout size of the RecyclerView */

        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);


        Project project = new Project();
        project.setName("ConsulApp");
        project.setCustomer("Iteso");
        project.setDescription("Proyecto de desarrollo de aplicación móvil para" +
                                " consultoría");

        mAdapter = new Adapter(this);
        recyclerView.setAdapter(mAdapter);

            mAdapter.addProject(project);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                if(resultCode == RESULT_OK){
                    String nameProject = data.getStringExtra("Name");
                    String company = data.getStringExtra("Company");
                    String description = data.getStringExtra("Description");
                    String date = data.getStringExtra("Date");
                    String customer = data.getStringExtra("Customer");

                    Project project = new Project();
                    project.setName(nameProject);
                    project.setCompany(company);
                    project.setDescription(description);
                    project.setCustomer(customer);

                    mAdapter.addProject(project);
                }
                break;

        }
    }
}
