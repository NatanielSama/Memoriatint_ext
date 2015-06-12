package nataniel.memoriatint_ext;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Memoint extends ActionBarActivity {
    private EditText text;
    private Button save, open;
    private static final int READ_BLOCK_SIZE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoint);
        text = (EditText) findViewById(R.id.archi2);
        save = (Button)findViewById(R.id.save2);
        open = (Button)findViewById(R.id.open2);
    }

    public void onClick(View v) {
        if (v.equals(save)){
            String str = text.getText().toString();
            //La clase FileOutputStream permite grabar texto en un archivo
            FileOutputStream fout=null;
            try {
                //Metodo que escribe y abre un archivo con un nombre especificado
                // La constante MODE_WORLD_READABLE indica que este arvhivo lo puede
                // leer cualquier apllicacion
                fout = openFileOutput("archivoTexto.txt", MODE_WORLD_READABLE);
                //Convierte un stream de caracteres en un stream de bytes
                OutputStreamWriter datos = new OutputStreamWriter(fout);
                datos.write(str);
                //Escribe en el buffer la cadena de texto
                datos.flush(); //Envia lo que hay en el buffer al archivo
                datos.close(); //Cierra el archivo de texto
                }
            catch (IOException e) {
            // TODO Auto-generated catch block e.printStackTrace();
            }
            Toast.makeText(getBaseContext(), "Archivo Guardado", Toast.LENGTH_SHORT).show();
            text.setText(""); }
        if (v.equals(open)) {
            try {
                //Se lee el archivo de texto indicado
                FileInputStream archivo = openFileInput("archivoTexto.txt");
                InputStreamReader lector = new InputStreamReader(archivo);
                char[] Bufferdatos = new char[READ_BLOCK_SIZE];
                String datos = "";
                //Se lee el archivo de texto mientras no se llegue al final de él
                int charlector;
                while ((charlector = lector.read(Bufferdatos)) > 0) {
                    //Se lee por bloques de 100 caracteres
                    // ya que se desconoce el tamaño del texto
                    // Y se va copiando a una cadena de texto
                    String strlector = String.copyValueOf(Bufferdatos, 0, charlector);
                    datos += strlector;
                    Bufferdatos = new char[READ_BLOCK_SIZE];
                }
                //Se muestra el texto leido en la caje de texto
                text.setText(datos);
                lector.close();
                Toast.makeText(getBaseContext(), R.string.archguar, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                // TODO: handle exception e.printStackTrace(); }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memoint, menu);
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
