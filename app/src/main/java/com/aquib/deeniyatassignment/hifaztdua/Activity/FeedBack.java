package com.aquib.deeniyatassignment.hifaztdua.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aquib.deeniyatassignment.hifaztdua.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

public class FeedBack extends AppCompatActivity {

    EditText email,subject,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        setContentView(R.layout.activity_feed_back);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        email =  findViewById(R.id.email);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        setSupportActionBar(toolbar);


        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Contact Us");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void feedback(View view) {

        if (subject.getText().toString().isEmpty()){
            Toast.makeText(this, "Subject field is required", Toast.LENGTH_SHORT).show();
            subject.setError("Subject field is required");
        } else if (message.getText().toString().isEmpty()){
            Toast.makeText(this, "Message field is required", Toast.LENGTH_SHORT).show();
            message.setError("Message field is required");
        }else {

            final Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType ("text/plain");
            List<ResolveInfo> resInfo = getPackageManager ().queryIntentActivities (intent, 0);

            if (!resInfo.isEmpty ()) {
                for (ResolveInfo info : resInfo) {
                    if (info.activityInfo.packageName.toLowerCase ().contains ("android.gm") || info.activityInfo.name.toLowerCase ().contains ("android.gm")) {


                        intent.putExtra(Intent.EXTRA_EMAIL,     new String[]{email.getText().toString().trim()});
                        intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString().trim());
                        intent.putExtra(Intent.EXTRA_TEXT,    message.getText().toString().trim());
                        intent.setPackage (info.activityInfo.packageName);

                        try {
                            startActivity (Intent.createChooser (intent,"Sending..."));
                            Toast.makeText(this, "Sending an email to your friend! ", Toast.LENGTH_LONG).show();
                        } catch (ActivityNotFoundException e) {


                            Toast.makeText(this, "Error! Try with other email address! ", Toast.LENGTH_LONG).show();
                        }


                    }
                }
        }}


    }
}
