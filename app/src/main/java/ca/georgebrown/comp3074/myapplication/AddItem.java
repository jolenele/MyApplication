package ca.georgebrown.comp3074.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddItem extends AppCompatActivity {

    public static final String EXTRA_ITEM = "ca.georgebrown.comp3074.myapplication.AddItem.EXTRA_ITEM";
    Button save;
    EditText newItem;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        save = findViewById(R.id.btnSave);
        newItem = findViewById(R.id.txtAdd);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddItem.this, MainActivity.class);
                if(TextUtils.isEmpty(newItem.getText())){
                    setResult(RESULT_CANCELED, i);
                }else{
                    String ni = newItem.getText().toString();
                    ItemViewModel itemViewModel = new ViewModelProvider(AddItem.this).get(ItemViewModel.class);
                    itemViewModel.insert(new Item(ni));
                    i.putExtra(EXTRA_ITEM, ni);
                    ((Activity)v.getContext()).setResult(Activity.RESULT_OK,i);
                }
                finish();
            }
        });
    }
}
