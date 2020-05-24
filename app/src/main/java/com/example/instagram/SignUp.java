package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchpower, edtKickSpeed, edtkickPower;


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

        ParseInstallation.getCurrentInstallation().saveInBackground();

        btnSave.setOnClickListener(SignUp.this);

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
                        FancyToast.makeText(SignUp.this, KickBoxer.get("name") + " is saved to server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });

        } catch (Exception e) {

            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

        }

    }

 }


