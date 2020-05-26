package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchpower, edtKickSpeed, edtkickPower;
    private TextView getData;
    private Button btnGetAllData;
    private Button btnTransition;

    private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchpower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtkickPower = findViewById(R.id.edtKickPower);
        getData = findViewById(R.id.txtGetData);
        btnGetAllData = findViewById(R.id.btnGetAllKickBoxers);
        btnTransition = findViewById(R.id.btnNextActivity);

        ParseInstallation.getCurrentInstallation().saveInBackground();

        btnSave.setOnClickListener(SignUp.this);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("JguvkAccz9", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null)  {
                            getData.setText(object.get("name") + "-" + "Punch Power: " + object.get("PunchPower"));
                        }
                    }
                });
            }
        });

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";
                ParseQuery<ParseObject> queryall = ParseQuery.getQuery("KickBoxer");
                queryall.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                            if (e == null)  {
                                if (objects.size() > 0) {
                                    for (ParseObject kickBoxer : objects) {
                                        allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                                    }
                                     FancyToast.makeText(SignUp.this, allKickBoxers,
                                            FancyToast.LENGTH_LONG,
                                            FancyToast.SUCCESS, true).show();
                                }else {
                                    FancyToast.makeText(SignUp.this, e.getMessage(),
                                FancyToast.LENGTH_LONG, FancyToast.ERROR, true)
                                .show();
                                }
                            }
                    }
                });
            }
        });

        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignUpLoginActivity.class);
            }
        });

    }

//    public void helloWorldIsPressed(View view)  {

//        ParseObject Boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed", 200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//
//                    Toast.makeText(SignUp.this, "boxer object is saved successfully",
//                            Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });

    @Override
    public void onClick(View v) {

        try {

            final ParseObject KickBoxer = new ParseObject("KickBoxer");
            KickBoxer.put("name", edtName.getText().toString());
            KickBoxer.put("PunchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            KickBoxer.put("PunchPower", Integer.parseInt(edtPunchpower.getText().toString()));
            KickBoxer.put("KickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
            KickBoxer.put("KickPower", Integer.parseInt(edtkickPower.getText().toString()));
            KickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, KickBoxer.get("name")
                                + " is saved to server", FancyToast.LENGTH_LONG,
                                FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage(),
                                FancyToast.LENGTH_LONG, FancyToast.ERROR, true)
                                .show();
                    }
                }
            });

        } catch (Exception e) {

            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

        }

    }

 }


