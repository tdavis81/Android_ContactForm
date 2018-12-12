package com.example.contactform;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	static EditText etFName;
	static EditText etMName;
	static EditText etLName;
	static EditText etAddressMain;
	static EditText etAddressOptional;
	static EditText etCity;
	static EditText etState;
	static EditText etZip;
	Button btnClose;
	static TextView tvResult;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Contact Form");
        
        etFName = (EditText) findViewById(R.id.etFirstName);
        etMName = (EditText) findViewById(R.id.etMiddle);
        etLName = (EditText) findViewById(R.id.etLastName);
        etAddressMain = (EditText) findViewById(R.id.etAddressMain);
        etAddressOptional = (EditText) findViewById(R.id.etAddressOptional);
        etCity = (EditText) findViewById(R.id.etCity);
        etState = (EditText) findViewById(R.id.etState);
        etZip = (EditText) findViewById(R.id.etZip);
        tvResult = (TextView) findViewById(R.id.tvResults); 
        btnClose = (Button) findViewById(R.id.buttonClose);
        
        btnClose.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
				
			}
        });
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public void callToast(String input) {
    	Toast.makeText(MainActivity.this, input, 
				   Toast.LENGTH_LONG).show();
    }
    
    @SuppressLint({ "NewApi" }) public static boolean checkEmpty(String fName,String mName,String lName,String addressMain,String city,String state,String zip) {
		
    	if(fName.isEmpty() || mName.isEmpty() || lName.isEmpty() || addressMain.isEmpty() || city.isEmpty()
    			|| state.isEmpty() || zip.isEmpty()){
    		
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    @SuppressLint("NewApi") public static void printInformation(View v) {
    
    	
    	String fName;
    	String mName;
    	String lName;
    	String addressMain;
    	String addressOptional;
    	String city;
    	String state;
    	String zip;
    	String finalResult;
    	
    	fName = etFName.getText().toString();
    	mName = etMName.getText().toString();
    	lName = etLName.getText().toString();
    	addressMain = etAddressMain.getText().toString();
    	addressOptional = etAddressOptional.getText().toString();
    	city = etCity.getText().toString();
    	state = etState.getText().toString();
    	zip = etZip.getText().toString();
    	
    	if( checkEmpty(fName,mName,lName,addressMain,city,state,zip) ){
    		
    		   finalResult= ( "All fields excluding optional address are required." );
    		   
    	} else {
    		
    		if(addressOptional.isEmpty()){
    			finalResult =  fName + " | "  +  mName + " | " + lName
            			+ "\n" + addressMain +
            			"\n" + city + " | "  + state +  " | " + zip;
    		} else {
    			finalResult =  fName + " | "  + mName + " | " + lName
            			+ "\n" + addressMain + " | "  + addressOptional + 
            			"\n" + city + " | "  + state +  " | " + zip;
    		}
    		
    		
    		etFName.setText("");
        	etMName.setText("");
        	etLName.setText("");
        	etAddressMain.setText("");
        	etAddressOptional.setText("");
        	etCity.setText("");
        	etState.setText("");
        	etZip.setText("");
        	
    	}
    	
    	
        tvResult.setText(finalResult);
    	
    }
}
