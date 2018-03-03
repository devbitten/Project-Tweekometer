package com.example.user.project_tweekometer;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    // TODO : DELETE THIS, images should be 300x300
    // TODO : DO SOMETHING WITH JSON PARSER

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadProducts();
    }

    /**
     * This method loads the 'Product' content
     */
    public void loadProducts(/*View view*/){

        Resources res = getResources();

        InputStream is = res.openRawResource(R.raw.products);

        Scanner scanner = new Scanner(is);

        StringBuilder builder = new StringBuilder();

        while(scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }

        String j = builder.toString();
        try {
            JSONObject root = new JSONObject(j);
            System.out.println(root.getJSONObject("Products");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
