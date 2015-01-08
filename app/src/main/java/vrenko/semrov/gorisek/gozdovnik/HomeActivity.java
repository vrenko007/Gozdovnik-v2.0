package vrenko.semrov.gorisek.gozdovnik;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.artifex.mupdfdemo.MuPDFActivity;
import com.parse.ParseUser;

import java.io.File;


public class HomeActivity extends ActionBarActivity {

    // za testne namene
    Button btnPdf;
    private final static String path =   "/sdcard/Download/test.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                if(ParseUser.getCurrentUser() == null){
                    startActivity(new Intent(HomeActivity.this, LoginDispatchActivity.class));
                }
            }
        });

        // za testne namene
        btnPdf = (Button) findViewById(R.id.btnPdf);
        btnPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File(path);
                if(f.exists())
                {
                    Intent intent = new Intent(v.getContext(), MuPDFActivity.class);
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.fromFile(f));
                    intent.putExtra("FileName", path);
                    startActivity(intent);


                }
                else
                {
                    Toast.makeText(v.getContext(),"Ne najdem test.pdf",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
