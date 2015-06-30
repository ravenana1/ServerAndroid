package com.example.serviceauto;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class MainActivity extends Activity {
	
	Button status;
	Button inreg;
	EditText nume, model, brand, serie, idMasina, dataM;
	final Context context = this;
	String dataIng = "";
	GetRequest getRequest;
	public int ok = 0;
	public String registrationID;
	
	public int ok2 = 0;
	public String pretMesaj;
	public String stareMesaj;
	public String dataMesaj;
	
	
	public ButtonInregClick binreg = new ButtonInregClick();
	public class ButtonInregClick implements Button.OnClickListener{

		@Override
		public void onClick(View arg0) {
			/*Random r = new Random();
	        String registrationID = String.valueOf(r.nextInt(Integer.MAX_VALUE));
	        dataIng = dataM.getText().toString();
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
	 
				// set title
				alertDialogBuilder.setTitle("Numarul de inregistrare");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage(registrationID)
					.setCancelable(false)
					.setPositiveButton("OK",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// dialog
							dialog.cancel();
						}
					  });
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();*/
					
					String m = model.getText().toString();
					String s = serie.getText().toString();
					String b = brand.getText().toString();
					String d = dataM.getText().toString();
					String n = nume.getText().toString().split("")[0];
					String p = nume.getText().toString().split("")[1];
					
					getRequest = new GetRequest(MainActivity.this, n, p, m, b, s, d);
					getRequest.start();
					
					while(true){
						if(ok == 1)
							break;
					}
					createDialog();
		}
	}
	
	public ButtonStatusClick binStatus = new ButtonStatusClick();
	public class ButtonStatusClick implements Button.OnClickListener{

		@Override
		public void onClick(View arg0) {
			
			GetStatus getStatus = new GetStatus(MainActivity.this, idMasina.getText().toString());
			getStatus.start();
			
			while(true){
				if(ok2 == 1)
					break;
			}
			
			createDialog2();
		}
	}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        nume = (EditText)findViewById(R.id.nume);
        model = (EditText)findViewById(R.id.model);
        brand = (EditText)findViewById(R.id.brand);
        serie = (EditText)findViewById(R.id.serie);
        idMasina = (EditText)findViewById(R.id.idMasina);
        dataM = (EditText)findViewById(R.id.dataM);
        
        inreg = (Button)findViewById(R.id.butonInreg);
		inreg.setOnClickListener(binreg);
		
		status = (Button)findViewById(R.id.butonStatus);
		status.setOnClickListener(binStatus);	
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
    
    private void createDialog(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
 
			// set title
			alertDialogBuilder.setTitle("Numarul de inregistrare");
 
			// set dialog message
			alertDialogBuilder
				.setMessage(registrationID)
				.setCancelable(false)
				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// dialog
						dialog.cancel();
					}
				  });
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
	}
    
    private void createDialog2(){
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
		context);

		// set title
		alertDialogBuilder.setTitle("Status Masina");

		// set dialog message
		alertDialogBuilder
			.setMessage("Registration ID: " + idMasina.getText().toString() +
						"\nData Programare: " +  dataMesaj +
						"\nStare Masina: " + stareMesaj +
						"\nPret: " + pretMesaj)
			.setCancelable(false)
			.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// if this button is clicked, close
					// dialog
					dialog.cancel();
				}
			  });

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
    }
}
