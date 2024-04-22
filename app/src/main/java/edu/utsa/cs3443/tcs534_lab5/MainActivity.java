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

/**
 * The MainActivity class represents the main login screen of the app. It allows users to input their username and passphrase to log in.
 *
 * @author Lisa Galvan tcs534
 *
 */
public class MainActivity extends AppCompatActivity {

    /**
     * EditText field for entering the username.
     */
    private EditText usernameEditText;

    /**
     * EditText field for entering the passphrase.
     */
    private EditText passphraseEditText;

    /**
     * Button for initiating the login process which leads to RoleActivity.
     */
    private Button loginButton;

    /**
     * Initializes the MainActivity when created. This method sets up the layout, initializes UI components,
     * sets up the login process, and sets up button click listeners to launch RoleActivity.
     *
     * @param savedInstanceState If the activity is being re-initialized, this Bundle contains the data from the previous state. Otherwise, it is null.
     */
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
                List<User> users = User.readUsersFromFile(getApplicationContext(), "users.csv");

                User loggedInUser = null;
                for (User user : users) {
                    if (user.validate(username, passphrase)) {
                        loggedInUser = user;
                        break;
                    }
                }

                if (loggedInUser != null) {
                    Intent intent = new Intent(MainActivity.this, RoleActivity.class);
                    intent.putExtra("realName", loggedInUser.getRealName());
                    intent.putExtra("username", username);
                    intent.putExtra("passphrase", passphrase);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Incorrect username and/or passphrase", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
