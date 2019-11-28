package ca.georgebrown.comp3074.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class Details extends AppCompatActivity {

    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final Intent intent = new Intent(Details.this, MainActivity.class);
        delete = findViewById(R.id.btnDelete);
        final TextView itemDelete = findViewById(R.id.txtDelete);
        final String itemString = getIntent().getStringExtra("Item");
        itemDelete.setText(itemString);
        final ItemViewModel itemViewModel = new ViewModelProvider(Details.this).get(ItemViewModel.class);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemViewModel.deleteItem(itemString);
                Toast.makeText(v.getContext(),"Deleted "+itemString, Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }
        });
    }
}