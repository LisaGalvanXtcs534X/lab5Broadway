package edu.utsa.cs3443.tcs534_lab5;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import edu.utsa.cs3443.tcs534_lab5.model.Act;
import edu.utsa.cs3443.tcs534_lab5.model.Scene;
import edu.utsa.cs3443.tcs534_lab5.model.User;

/**
 * The ActActivity class represents the screen displaying scenes for a specific act. It retrieves act information and scenes associated with the logged-in user's roles.
 *
 * @author Lisa Galvan tcs534
 *
 */
public class ActActivity extends AppCompatActivity {

    /**
     * Initializes the ActActivity when created. This method sets up the layout, retrieves act information,
     * and displays scenes associated with the logged-in user's roles.
     *
     * @param savedInstanceState If the activity is being re-initialized, this Bundle contains the data from the previous state. Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);

        int actNumber = getIntent().getIntExtra("act_number", 1);
        String username = getIntent().getStringExtra("username");
        String passphrase = getIntent().getStringExtra("passphrase");

        TextView actView = findViewById(R.id.actView1);
        TextView sceneView = findViewById(R.id.sceneView);

        if (actNumber == 1) {
            actView.setText("Act I");
            Act act = new Act(1);
            List<User> users = User.readUsersFromFile(getApplicationContext(), "users.csv");
            List<Scene> scenes = act.getScenesForActor(users, username, passphrase);
            if (!scenes.isEmpty()) {
                StringBuilder scenesText = new StringBuilder();
                for (Scene scene : scenes) {
                    scenesText.append(scene.getTitle()).append("\n");
                }
                sceneView.setText(scenesText.toString());
            }
            else {
                sceneView.setText("No scenes");
            }
        }
        else if (actNumber == 2) {
            actView.setText("Act II");
            Act act = new Act(2);
            List<User> users = User.readUsersFromFile(getApplicationContext(), "users.csv");
            List<Scene> scenes = act.getScenesForActor(users, username, passphrase);
            if (!scenes.isEmpty()) {
                StringBuilder scenesText = new StringBuilder();
                for (Scene scene : scenes) {
                    scenesText.append(scene.getTitle()).append("\n");
                }
                sceneView.setText(scenesText.toString());
            }
            else {
                sceneView.setText("No scenes");
            }
        }
    }
}
