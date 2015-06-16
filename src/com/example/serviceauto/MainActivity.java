package com.example.serviceauto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	Button status;
	Button inreg;
	EditText nume, prenume, model, brand, serie;
	
	public ButtonInregClick binreg = new ButtonInregClick();
	public class ButtonInregClick implements Button.OnClickListener{

		@Override
		public void onClick(View arg0) {
			HttpPost post = new HttpPost("http://localhost:8080/ServiceAuto/regiterCar");
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
			nameValuePairs.add(new BasicNameValuePair("firstName", prenume.getEditableText().toString()));
			nameValuePairs.add(new BasicNameValuePair("lastName", nume.getEditableText().toString()));
			nameValuePairs.add(new BasicNameValuePair("carModel", model.getEditableText().toString()));
			nameValuePairs.add(new BasicNameValuePair("carBrand", brand.getEditableText().toString()));
			nameValuePairs.add(new BasicNameValuePair("carSerial", serie.getEditableText().toString()));
			try {
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			
			createExampleDialog(entity.toString());
			
			
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
		}
		
	}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        nume = (EditText)findViewById(R.id.nume);
        prenume = (EditText)findViewById(R.id.prenume);
        model = (EditText)findViewById(R.id.model);
        brand = (EditText)findViewById(R.id.brand);
        serie = (EditText)findViewById(R.id.serie);
        
        inreg = (Button)findViewById(R.id.butonInreg);
		inreg.setOnClickListener(binreg);
		
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
    
    private Dialog createExampleDialog(String idReg) {
    	 
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ID inregistrare");
        builder.setMessage("ID-ul dumneavoastra este: " + idReg);
 
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
 
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                return;
            }
        });
 
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
 
        return builder.create();
    }
}
