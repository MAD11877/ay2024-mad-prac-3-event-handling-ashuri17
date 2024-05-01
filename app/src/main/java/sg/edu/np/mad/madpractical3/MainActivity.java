package sg.edu.np.mad.madpractical3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        User user = new User("John Doe","MAD Developer",1,false); //creating new user

        //finding widgets' IDs
        TextView tvName = findViewById(R.id.textView2);     //'userTitle'
        TextView tvDescription = findViewById(R.id.textView3);  // 'user.description'
        Button btnFollow = findViewById(R.id.button1);  //toggle 'followed' boolean

        //retrieving random integer generated from ListActivity.java
        Intent receivingId = getIntent();
        int userID = receivingId.getIntExtra("id",-1);
        //concatenating user.name and userID
        String userTitle = String.format("%1$s %2$06d",user.name,userID);

        tvName.setText(userTitle);
        tvDescription.setText(user.description);
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.followed = !user.followed;
                if (!user.followed){
                    btnFollow.setText("Follow");
                    Toast.makeText(getApplicationContext(),"Unfollowed",Toast.LENGTH_SHORT).show();
                }
                else{
                    btnFollow.setText("Unfollow");
                    Toast.makeText(getApplicationContext(),"Followed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}