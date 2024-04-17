package edu.utsa.cs3443.tcs534_lab5;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import edu.utsa.cs3443.tcs534_lab5.model.Act;
import edu.utsa.cs3443.tcs534_lab5.model.Scene;

public class ActActivity extends AppCompatActivity {

    private static final String TAG = "ActActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);

        // Get the act number and real name from the intent extras
        int actNumber = getIntent().getIntExtra("act_number", 1);
        String realName = getIntent().getStringExtra("realName");

        // Find the TextViews
        TextView actView = findViewById(R.id.actView1);
        TextView sceneView = findViewById(R.id.sceneView);

        // Update the TextView based on the act number
        if (actNumber == 1) {
            actView.setText("Act I");
            // Load scenes from act1.txt
            Act act = new Act(1);
            List<Scene> scenes = act.getScenesForActor(realName);
            if (!scenes.isEmpty()) {
                StringBuilder scenesText = new StringBuilder();
                for (Scene scene : scenes) {
                    scenesText.append(scene.getTitle()).append("\n");
                }
                sceneView.setText(scenesText.toString());
            } else {
                sceneView.setText("No Scenes");
            }
        } else if (actNumber == 2) {
            actView.setText("Act II");
            // Load scenes from act2.txt
            Act act = new Act(2);
            List<Scene> scenes = act.getScenesForActor(realName);
            if (!scenes.isEmpty()) {
                StringBuilder scenesText = new StringBuilder();
                for (Scene scene : scenes) {
                    scenesText.append(scene.getTitle()).append("\n");
                }
                sceneView.setText(scenesText.toString());
            } else {
                sceneView.setText("No Scenes");
            }
        }
    }
}