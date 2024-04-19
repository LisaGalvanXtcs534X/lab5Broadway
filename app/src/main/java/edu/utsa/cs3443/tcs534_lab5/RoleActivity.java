package edu.utsa.cs3443.tcs534_lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import edu.utsa.cs3443.tcs534_lab5.model.User;

public class RoleActivity extends AppCompatActivity {
    private TextView realNameTextView;
    private TextView rolesListTextView;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        // Get the realName and username passed from MainActivity
        String realName = getIntent().getStringExtra("realName");
        String username = getIntent().getStringExtra("username");
        String passphrase = getIntent().getStringExtra("passphrase");

        // Find the views
        realNameTextView = findViewById(R.id.realNameTextView);
        rolesListTextView = findViewById(R.id.rolesListTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        // Set the real name to realNameTextView
        realNameTextView.setText(realName);

        // Read user data from users.csv
        List<User> users = User.readUsersFromFile(getApplicationContext(), "users.csv");

        // Find the corresponding user
        User loggedInUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                loggedInUser = user;
                break;
            }
        }

        // Display user roles in rolesListTextView
        if (loggedInUser != null) {
            List<String> userRoles = loggedInUser.getRoles();
            StringBuilder rolesStringBuilder = new StringBuilder();
            for (String role : userRoles) {
                int parenthesesIndex = role.indexOf('(');
                if (parenthesesIndex != -1) {
                    role = role.substring(0, parenthesesIndex).trim();
                }
                rolesStringBuilder.append(role.trim()).append("\n"); // Change " " to "\n"
            }
            // Remove the trailing newline character
            if (rolesStringBuilder.length() > 0) {
                rolesStringBuilder.setLength(rolesStringBuilder.length() - 1);
                rolesListTextView.setText(rolesStringBuilder.toString());
            }
        }

        // Set onClickListeners for the buttons
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ActActivity with act number 1
                Intent intent = new Intent(RoleActivity.this, ActActivity.class);
                intent.putExtra("act_number", 1);
                intent.putExtra("realName", realName); // Pass the realName to ActActivity
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ActActivity with act number 2
                Intent intent = new Intent(RoleActivity.this, ActActivity.class);
                intent.putExtra("act_number", 2);
                intent.putExtra("username", username); // Add this line
                intent.putExtra("passphrase", passphrase); // Add this line
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to MainActivity
                finish();
            }
        });
    }
}
