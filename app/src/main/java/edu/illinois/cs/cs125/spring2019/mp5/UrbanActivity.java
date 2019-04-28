package edu.illinois.cs.cs125.spring2019.mp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import edu.illinois.cs.cs125.spring2019.lab12.R;

public class UrbanActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urban);
        Button myButton = (Button)findViewById(R.id.search);
        EditText myInput = (EditText)findViewById(R.id.input);
        String input = myInput.getText().toString();

        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String toUrl =
                        "https://mashape-community-urban-dictionary.p.rapidapi.com/define?term="
                                + input;
                JsonGetter(toUrl);
                final TextView define = findViewById(R.id.result);
                JsonConveter newJson = new JsonConvetor();
                String toSet = newJson.converter(define.getText());
            }
        });
    }
    public void JsonGetter(String url) {
// ...
        final TextView textView = findViewById(R.id.result);
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
                @Override
                protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-RapidAPI-Host"
                        , "mashape-community-urban-dictionary.p.rapidapi.com");
                params.put("X-RapidAPI-Key"
                        , "d1aac54addmsh57dfeffbe602fdep1aefcfjsn6297c2f6f681");
                return params;
            };
        };
        queue.add(stringRequest);
    }
}
