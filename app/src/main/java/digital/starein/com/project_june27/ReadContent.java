package digital.starein.com.project_june27;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import es.dmoral.toasty.Toasty;

public class ReadContent extends AppCompatActivity {


    private TextView lineText;
    private Button likeBtn;
    private ArrayList<CategoryFormat> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_content);
        lineText=(TextView)findViewById(R.id.rLine);
        likeBtn=(Button)findViewById(R.id.likeBtn);
        AssetManager am = ReadContent.this.getApplicationContext().getAssets();
        Typeface custom_font3 = Typeface.createFromAsset(am,"fonts/OpenSans-Regular.ttf");
        lineText.setTypeface(custom_font3);
        final String title=getIntent().getStringExtra("title");
        final String author=getIntent().getStringExtra("author");
        final String lines=getIntent().getStringExtra("lines");
        lineText.setText(lines);
        list=getFromPreference();
        Typeface custom_font1 = Typeface.createFromAsset(am,"fonts/OpenSans-Semibold.ttf");
        Toasty.Config.getInstance()
                .tintIcon(true).setToastTypeface(custom_font1).setTextSize(12).allowQueue(true).apply();
        CategoryFormat f=new CategoryFormat(title,author,lines);
        if(list==null){
            list=new ArrayList<>();
        }

        if(list.contains(f)){
            likeBtn.setBackgroundResource(R.drawable.outline_thumb_down_black_24dp);
        }else{
            likeBtn.setBackgroundResource(R.drawable.outline_thumb_up_black_24dp);
        }


        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryFormat f=new CategoryFormat(title,author,lines);
                if(list.contains(f)){

                    //Toast.makeText(ReadContent.this,"Removed",Toast.LENGTH_SHORT).show();
                    list.remove(f);
                    saveToPreference();
                    Toasty.error(ReadContent.this, "Item removed from Favourites", Toast.LENGTH_SHORT, true).show();
                    likeBtn.setBackgroundResource(R.drawable.outline_thumb_up_black_24dp);

                }else{

                   // Toast.makeText(ReadContent.this,"Item name"+f.getAuthor(),Toast.LENGTH_SHORT).show();
                    list.add(f);
                    Toasty.info(ReadContent.this, "Item added to favourites", Toast.LENGTH_SHORT, true).show();
                    saveToPreference();
                    likeBtn.setBackgroundResource(R.drawable.outline_thumb_down_black_24dp);
                }

            }
        });
        //lineText.setMovementMethod(new ScrollingMovementMethod());
       // Toast.makeText(this,"Size="+list.size(),Toast.LENGTH_SHORT).show();



    }

    void saveToPreference(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ReadContent.this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("TAG", json);
        editor.commit();
    }


    ArrayList<CategoryFormat> getFromPreference(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ReadContent.this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("TAG", "");
        Type type = new TypeToken<ArrayList<CategoryFormat>>() {}.getType();
        ArrayList<CategoryFormat> arrayList = gson.fromJson(json, type);
        return arrayList;
    }
}
