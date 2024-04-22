package edu.utsa.cs3443.tcs534_lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import edu.utsa.cs3443.tcs534_lab5.model.User;

/**
 * The RoleActivity class represents the screen displaying user roles after successful login. It allows users to view their roles and navigate to different activities based on their permissions.
 *
 * @author Lisa Galvan tcs534
 *
 */
public class RoleActivity extends AppCompatActivity {

    /**
     * TextView displaying the real name of the logged-in user.
     */
    private TextView realNameTextView;

    /**
     * TextView displaying the list of roles assigned to the logged-in user.
     */
    private TextView rolesListTextView;

    /**
     * Act 1 Button for navigating to ActActivity.
     */
    private Button button1;

    /**
     * Act 2 Button for navigating to ActActivity.
     */
    private Button button2;

    /**
     * Button for closing the RoleActivity and navigating back to MainActivity.
     */
    private Button button3;

    /**
     * Initializes the RoleActivity when created. This method sets up the layout, retrieves user information,
     * displays user roles, and sets up button click listeners for navigation.
     *
     * @param savedInstanceState If the activity is being re-initialized, this Bundle contains the data from the previous state. Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        String realName = getIntent().getStringExtra("realName");
        String username = getIntent().getStringExtra("username");
        String passphrase = getIntent().getStringExtra("passphrase");

        realNameTextView = findViewById(R.id.realNameTextView);
        rolesListTextView = findViewById(R.id.rolesListTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        realNameTextView.setText(realName);

        List<User> users = User.readUsersFromFile(getApplicationContext(), "users.csv");

        User loggedInUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                loggedInUser = user;
                break;
            }
        }

        if (loggedInUser != null) {
            List<String> userRoles = loggedInUser.getRoles();
            StringBuilder rolesStringBuilder = new StringBuilder();
            for (String role : userRoles) {
                int parenthesesIndex = role.indexOf('(');
                if (parenthesesIndex != -1) {
                    role = role.substring(0, parenthesesIndex).trim();
                }
                rolesStringBuilder.append(role.trim()).append("\n");
            }
            if (rolesStringBuilder.length() > 0) {
                rolesStringBuilder.setLength(rolesStringBuilder.length() - 1);
                rolesListTextView.setText(rolesStringBuilder.toString());
            }
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoleActivity.this, ActActivity.class);
                intent.putExtra("act_number", 1);
                intent.putExtra("realName", realName);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoleActivity.this, ActActivity.class);
                intent.putExtra("act_number", 2);
                intent.putExtra("username", username);
                intent.putExtra("passphrase", passphrase);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
