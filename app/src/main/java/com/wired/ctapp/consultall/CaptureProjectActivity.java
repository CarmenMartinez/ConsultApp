package com.wired.ctapp.consultall;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wired.ctapp.consultall.R;

public class CaptureProjectActivity extends AppCompatActivity {

    EditText projectName;
    EditText company;
    EditText description;
    EditText date;
    EditText customer;
    Button addProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_project);
        projectName = (EditText) findViewById(R.id.valueProjectName);
        company = (EditText) findViewById(R.id.valueCompany);
        description = (EditText) findViewById(R.id.valueDescription);
        customer = (EditText) findViewById(R.id.valueCustomer) ;
        date = (EditText) customer;
        addProject = (Button) findViewById(R.id.project_add_button);

        addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Name", projectName.getText().toString());
                intent.putExtra("Company", company.getText().toString());
                intent.putExtra("Description", description.getText().toString());
                intent.putExtra("Date", date.getText().toString());
                intent.putExtra("Customer", customer.getText().toString());

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

}
