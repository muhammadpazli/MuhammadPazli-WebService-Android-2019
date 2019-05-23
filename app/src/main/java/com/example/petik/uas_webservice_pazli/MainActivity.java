package com.example.petik.uas_webservice_pazli;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.btn_cuaca);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }
    private void jsonParse(){
        String url = "https://api.myjson.com/bins/11mqfw";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("desktop");
                            for ( int i = 0; i<jsonArray.length(); i++){
                                JSONObject desktops = jsonArray.getJSONObject(i);

                                String Kota = desktops.getString("Kota");
                                String siang = desktops.getString("siang");
                                String malam = desktops.getString("malam");
                                String dini_hari = desktops.getString("dini_hari");
                                String suhu = desktops.getString("suhu");
                                String Kelembapan = desktops.getString("Kelembapan");

                                mTextViewResult.append(Kota+", "+siang+", "+malam+", "+dini_hari+", "+
                                suhu+", "+Kelembapan+"\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}
