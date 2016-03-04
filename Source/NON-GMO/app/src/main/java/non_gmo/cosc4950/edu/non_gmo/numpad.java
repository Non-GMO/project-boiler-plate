package non_gmo.cosc4950.edu.non_gmo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Bridger on 2/19/2016.
 */
public class numpad extends AppCompatActivity{
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b_backspace, b_enter;
    TextView num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numpad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // RETURN INTENT STUFF
        Intent intent = getIntent();
        final Intent output = new Intent();

        //------------------------------------------------------------------------
        // MAKE BUTTONS REFERENCEABLE
        b0 = (Button) findViewById(R.id.button_0);
        b1 = (Button) findViewById(R.id.button_1);
        b2 = (Button) findViewById(R.id.button_2);
        b3 = (Button) findViewById(R.id.button_3);
        b4 = (Button) findViewById(R.id.button_4);
        b5 = (Button) findViewById(R.id.button_5);
        b6 = (Button) findViewById(R.id.button_6);
        b7 = (Button) findViewById(R.id.button_7);
        b8 = (Button) findViewById(R.id.button_8);
        b9 = (Button) findViewById(R.id.button_9);
        b_backspace = (Button) findViewById(R.id.button_bckspc);
        b_enter = (Button) findViewById(R.id.button_search);
        num = (TextView) findViewById(R.id.entered_num);
        //------------------------------------------------------------------------
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.append(b1.getText());
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.append(b2.getText());
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.append(b3.getText());
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.append(b4.getText());
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.append(b5.getText());
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.append(b6.getText());
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.append(b7.getText());
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.append(b8.getText());
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.append(b9.getText());
            }
        });
        b_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = num.getText().toString();
                if (str.length() == 0) {
                    // USER IS A DUMBASS
                } else{
                    str.substring(0, str.length()-1);
                }
                num.setText(str);
            }
        });
        b_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: SEARCH DATABASE FOR NUMBER
            }
        });
    }
}
