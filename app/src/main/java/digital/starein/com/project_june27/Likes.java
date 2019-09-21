package digital.starein.com.project_june27;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Likes extends AppCompatActivity {

    private ListView listView;
    private ArrayList<CategoryFormat> list;
    private Toolbar mToolbar;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.new_left_to_right, R.anim.new_right_to_left);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);
        mToolbar = (Toolbar) findViewById(R.id.likeToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("LIKES");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listView=(ListView)findViewById(R.id.mylistview);
        list=getFromPreference();
        if(list==null){
            list=new ArrayList<>();
        }
        ItemArrayAdapter adapter=new ItemArrayAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.emptyview));
    }

    ArrayList<CategoryFormat> getFromPreference(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(Likes.this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("TAG", "");
        Type type = new TypeToken<ArrayList<CategoryFormat>>() {}.getType();
        ArrayList<CategoryFormat> arrayList = gson.fromJson(json, type);
        return arrayList;
    }
}
