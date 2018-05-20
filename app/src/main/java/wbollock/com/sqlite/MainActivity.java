package wbollock.com.sqlite;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText buckysInput;
    TextView buckysText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buckysInput = (EditText) findViewById(R.id.buckysInput);
        buckysText = (TextView) findViewById(R.id.buckysText);
        dbHandler = new MyDBHandler(this, null, null, 1); // let DBHandler take care of factory/name
    }

    //Add a product to database
    public void addButtonClicked(View view){
        Products product = new Products(buckysInput.getText().toString());
        dbHandler.addProduct(product);
        printDatabase();
    }

    //Delete products
    public void deleteButtonClicked(View view){
        String inputText = buckysText.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();
    }

    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        buckysText.setText(dbString);
        buckysInput.setText(""); // resets the text to null that the user entered after the new query has gone through
    }

}
