package edu.utsa.cs3443.tcs534_lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import android.text.InputType;
import edu.utsa.cs3443.tcs534_lab5.model.User;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passphraseEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passphraseEditText = findViewById(R.id.passphraseEditText);
        passphraseEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String passphrase = passphraseEditText.getText().toString();

                // Read user data from users.csv
                List<User> users = User.readUsersFromFile(getApplicationContext(), "users.csv");

                // Find the logged-in user
                User loggedInUser = null;
                for (User user : users) {
                    if (user.validate(username, passphrase)) {
                        loggedInUser = user;
                        break;
                    }
                }

                if (loggedInUser != null) {
                    // Pass realName and username data to RoleActivity
                    Intent intent = new Intent(MainActivity.this, RoleActivity.class);
                    intent.putExtra("realName", loggedInUser.getRealName());
                    intent.putExtra("username", username); // Add this line
                    intent.putExtra("passphrase", passphrase); // Add this line
                    startActivity(intent);
                } else {
                    // Display a toast message indicating incorrect credentials
                    Toast.makeText(MainActivity.this, "Incorrect username and/or passphrase", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
