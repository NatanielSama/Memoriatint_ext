package nataniel.memoriatint_ext;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Menup extends ActionBarActivity {
TextView esca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menup);
        esca = (TextView) findViewById(R.id.esc);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.memin:
                Intent intent = new Intent(getBaseContext(),Memoint.class);
                startActivity(intent);
                return true;
            case R.id.memex:
                Intent intent2 = new Intent(getBaseContext(),Memoext.class);
                startActivity(intent2);
                return true;
            default:

                return super.onOptionsItemSelected(item);
    }
}
}

