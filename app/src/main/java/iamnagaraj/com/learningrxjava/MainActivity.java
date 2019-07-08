package iamnagaraj.com.learningrxjava;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ConstraintLayout constraintLayout;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText searchText = findViewById(R.id.searchText);
        constraintLayout = findViewById(R.id.mainScreen);
        textView = findViewById(R.id.textView);

        RxTextView.textChanges(searchText)
                .filter(text->text.length()>3)
                .debounce(150, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::searchResultUpdate);
    }

    private void searchResultUpdate(CharSequence charSequence) {
        Log.d(TAG, charSequence.toString());
        if (charSequence.length() > 9) {
            textView.setText(R.string.long_text);
        }else{
            textView.setText(R.string.result_text);
        }
    }
}
