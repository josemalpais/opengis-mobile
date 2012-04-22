package code.google.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ParcelaNuevo extends Activity {
	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearparcela);
        
        final Bundle extras = getIntent().getExtras();
        
        final EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
        final EditText txtProvincia = (EditText) findViewById(R.id.txtProvincia);
        final EditText txtPoblacion = (EditText) findViewById(R.id.txtPoblacion);
        final EditText txtPoligono = (EditText) findViewById(R.id.txtPoligono);
        final EditText txtNumero = (EditText) findViewById(R.id.txtNumero);
        final Button bGuardar = (Button) findViewById(R.id.cmdGuardarParcelaNueva);
       
     
        
        
        bGuardar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {

			

				String url = "http://"+getString(R.string.direccionServidor)+"/OpenGisMobile/CrearParcelaWebService.php?nom="+txtNombre.getText()+"&provincia="+txtProvincia.getText()+"&poblacion="+txtPoblacion.getText()+"&poligono="+txtPoligono.getText()+"&numero="+txtNumero.getText()+"&dni="+extras.getString("dni")+"";

				url = url.replaceAll(" ","%20");
				
				boolean finalizado = AccesoWebService.InsertarEnWebService(url);
				
				if(finalizado){
					
					Toast tt = Toast.makeText(getApplicationContext(),"Parcela a–adida correctamente",Toast.LENGTH_SHORT);
					tt.show();
					
					Intent vMisAperos = new Intent(ParcelaNuevo.this,AperosIconListView.class);
					vMisAperos.putExtra("dni",extras.getString("dni"));
					startActivity(vMisAperos);
					
					
				}else{
					
					// No se ha conseguido
					
				}
				
			}
        	
        	
        	
        });
	}

}
