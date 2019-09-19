package com.ervicioweba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edusua,edcodigo,edangel;
    Button btnAgregar,btnMostrar,btnBuscar,btnEliminar,btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edusua=(EditText)findViewById(R.id.edusuario);
        edcodigo=(EditText)findViewById(R.id.edCodigo);
        edangel=(EditText)findViewById(R.id.edAngel);
        btnAgregar=(Button)findViewById(R.id.btnagregar);
        btnMostrar=(Button)findViewById(R.id.btnmostrar);
        btnBuscar=(Button)findViewById(R.id.btnBuscar);
        btnEliminar=(Button)findViewById(R.id.btneliminar);
        btnEditar=(Button)findViewById(R.id.btneditar);


      btnAgregar.setOnClickListener( new View.OnClickListener(){

          @Override
          public void onClick(View v) {
                  ejecutar_Sevicio("http://192.168.1.62:8080/ConexionServicioWeb/Insertar.php");
          }
      });


    }
    private void ejecutar_Sevicio(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("Idcodigo",edcodigo.getText().toString());
                parametros.put("nombre",edusua.getText().toString());
                parametros.put("usuario",edangel.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

