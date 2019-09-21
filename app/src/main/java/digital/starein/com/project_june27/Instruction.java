package digital.starein.com.project_june27;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Instruction extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.poetrylogo, "WELCOME", "Enjoy thousands of free poems by some of the famous poets around the world"));
        models.add(new Model(R.drawable.imgone, "SWIPE UP", "Swipe the screen up or down to go to the next and previous poem respectively"));
        models.add(new Model(R.drawable.imgtwo, "SWIPE LEFT", "Swipe the screen left to choose Poets of your choice"));
        models.add(new Model(R.drawable.imgthree, "SWIPE RIGHT", "Swipe the screen right to view your liked poems"));

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.pager);
        proceed=(Button)findViewById(R.id.btnOrder);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Instruction.this,MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.enter,R.anim.exit);
                finish();
            }
        });

        Integer[] colors_temp = {
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.colorPrimary)
        };

        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(pref.getBoolean("activity_executed", false)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor ed = pref.edit();
            ed.putBoolean("activity_executed", true);
            ed.commit();
        }

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
