package edu.itstep.myapplic03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_GENDER_ACTIVITY = 101;
    public static final String KEY_MESSAGE = "message";
    private TextView tvGender;
    private Button btnGender;
    private EditText etMessage;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvGender = findViewById(R.id.tvGender);
        btnGender = findViewById(R.id.btnGender);
        etMessage = findViewById(R.id.etMessage);
        btnNext = findViewById(R.id.btnNext);

        btnGender.setOnClickListener(view -> {
            Intent intent = new Intent(this, GenderActivity.class);
            genderActivityResultLauncher.launch(intent);
        });

        btnNext.setOnClickListener(view -> {
            String message = etMessage.getText().toString();
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(KEY_MESSAGE, message);
            startActivity(intent);
        });
    }

    ActivityResultLauncher<Intent> genderActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    String gender = result.getData().getStringExtra(GenderActivity.KEY_GENDER);
                    tvGender.setText(String.format("%s%s", getString(R.string.selected_gender), gender));
                } else {
                    tvGender.setText(String.format("%s?", getString(R.string.selected_gender)));
                }
            }
    );
}