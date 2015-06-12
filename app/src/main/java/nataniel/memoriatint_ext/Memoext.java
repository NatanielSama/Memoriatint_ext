package nataniel.memoriatint_ext;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.File;


public class Memoext extends ActionBarActivity {
    private EditText text;
    private Button save, open;
    private static final int READ_BLOCK_SIZE=100;
    String estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoext);
        text = (EditText) findViewById(R.id.archi);
        save = (Button)findViewById(R.id.save);
        open = (Button)findViewById(R.id.open);
        estado = Environment.getExternalStorageState();
    }


    public void onClick(View v) {
        File memoriaext, directorio, archivo = null;
        try { // comprobamos si se encuentra montada nuestra memoria externa

            if (Environment.MEDIA_MOUNTED.equals(estado));
            {
                // Obtenemos la direccion de la memoria externa
                memoriaext = Environment.getExternalStorageDirectory();
                if (v.equals(save)) {
                    String texto = text.getText().toString();
                    // Clase que permite grabar texto en un archivo
                    FileOutputStream datos = null;
                    try {
                        // instanciamos un objeto File para crear un nuevo directorio en la memoria externa
                        directorio = new File(memoriaext.getAbsolutePath() + "/ArchivosExternos");
                        // se crea el nuevo directorio donde se creara el archivo
                        directorio.mkdirs();
                        // creamos el archivo en el nuevo directorio creado
                        archivo = new File(directorio, "MiArchivo.txt");
                        datos = new FileOutputStream(archivo);
                        // Convierte un stream de caracteres en un stream de bytes
                        OutputStreamWriter escribe = new OutputStreamWriter(datos);
                        escribe.write(texto);
                        // Escribe en el buffer la cadena de texto
                        escribe.flush(); // Envia lo que hay en el buffer al archivo
                        escribe.close(); // Cierra el archivo de texto
                        Toast.makeText(getBaseContext(), R.string.archguar, Toast.LENGTH_SHORT).show();
                        text.setText("");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block e.printStackTrace(); }
                    }
                }

                    if (v.equals(open)) {
                        try {
                            //Obtenemos el direcorio donde se encuentra el archivo que se va a leer
                            directorio = new File(memoriaext.getAbsolutePath() + "/ArchivosExternos");
                            //Creamos un objeto File de nuestro archivo a leer
                            archivo = new File(directorio, "MiArchivo.txt");
                            //Creamos un objeto de la clase FileInputStream
                            // el cual representa un stream del archivo que vamos a leer
                            FileInputStream streamarchi = new FileInputStream(archivo);
                            //Creamos un objeto InputStreamReader que nos permitira
                            // leer el stream del archivo abierto
                            InputStreamReader lector = new InputStreamReader(streamarchi);
                            char[] Bufferdatos = new char[READ_BLOCK_SIZE];
                            String texto1 = "";
                            // Se lee el archivo de texto mientras no se llegue al
                            // final de él
                            int charRead;
                            while ((charRead = lector.read(Bufferdatos)) > 0) {
                                // Se lee por bloques de 100 caracteres
                                // ya que se desconoce el tamaño del texto
                                // Y se va copiando a una cadena de texto
                                String textoxopia = String.copyValueOf(Bufferdatos, 0, charRead);
                                texto1 += textoxopia;
                                Bufferdatos = new char[READ_BLOCK_SIZE];
                            }
                            // Se muestra el texto leido en la caje de texto
                            text.setText(texto1);
                            lector.close();
                            Toast.makeText(getBaseContext(), R.string.archcarg, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                             Toast.makeText(this, "Error: "+ e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                    else {

                        Toast.makeText(getBaseContext(), R.string.almacno, Toast.LENGTH_LONG).show();
                    }

            }

        }
        catch(Exception e){
            Toast.makeText(this, "Error: "+ e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memoext, menu);
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
