package digital.starein.com.project_june27;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private VerticalViewPager viewPager;
    private float mLastPositionOffset = 0f;
    private Context context;
    public static String url="http://poetrydb.org/author/all";
    private List<String> lineList = new ArrayList<>();
    private List<String> titleList=new ArrayList<>();
    private List<String> authorList=new ArrayList<>();
    private List<HomeFormat> list;
    private int limit=100;
    private int startPos,lastPos;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        viewPager = findViewById(R.id.viewPager);
        list=new ArrayList<>();
        loadData();



    }

    private void loadData() {


        progressBar.setVisibility(View.VISIBLE);


        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                progressBar.setVisibility(View.GONE);
                try {
                    list.clear();
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject o = jsonArray.getJSONObject(i);
                        String title = o.getString("title");
                        String author = o.getString("author");
                        String line = o.getString("lines");
                        String s1 = line.substring(1);
                        String s2 = s1.replaceAll("\"", "");
                        String s3 = s2.replaceAll(",", "\n");
                        String s5 = s3.replaceAll("]", " ");
                        String s6 = s5.replace("\\" , "");
                        HomeFormat f = new HomeFormat(title, author, s5);
                        list.add(f);

                    }
                    MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), context, list);
                    adapter.notifyDataSetChanged();
                    viewPager.setAdapter(adapter);
                    viewPager.setOnTouchListener((new OnSwipeTouchListener(MainActivity.this){

                        @Override
                        public void onSwipeTop() {
                            super.onSwipeTop();
                            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                        }

                        @Override
                        public void onSwipeBottom() {
                            super.onSwipeBottom();
                            viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                        }

                        @Override
                        public void onSwipeRight() {
                            super.onSwipeRight();
                            startActivity(new Intent(MainActivity.this, Likes.class));
                            overridePendingTransition(R.anim.enternew, R.anim.exitnew);

                        }

                        @Override
                        public void onSwipeLeft() {
                            super.onSwipeLeft();
                            startActivity(new Intent(MainActivity.this, Categories.class));
                            overridePendingTransition(R.anim.enter, R.anim.exit);
                        }
                    }));
                    try {
                        Field mScroller;
                        mScroller = ViewPager.class.getDeclaredField("mScroller");
                        mScroller.setAccessible(true);
                        Interpolator interpolator=new AccelerateDecelerateInterpolator();
                        FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext(), interpolator);
                        // scroller.setFixedDuration(5000);
                        mScroller.set(viewPager, scroller);
                    } catch (NoSuchFieldException e) {
                    } catch (IllegalArgumentException e) {
                    } catch (IllegalAccessException e) {
                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



   /* private void addData() {
        for (int i = 0; i < 8; i++) {
            stringList.add(String.valueOf(i));
        }
    }*/

}
