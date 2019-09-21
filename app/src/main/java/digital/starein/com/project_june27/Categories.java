package digital.starein.com.project_june27;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

public class Categories extends AppCompatActivity {

    Toolbar mToolbar;
    ListView mListView;
   // TextView mEmptyView = (TextView) findViewById(R.id.emptyView);
    ArrayAdapter mAdapter;
    List<String> arrayList= new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        progressBar=(ProgressBar)findViewById(R.id.categorybar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("AUTHORS");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mListView=(ListView)findViewById(R.id.list);
        mListView = (ListView) findViewById(R.id.list);
        loadData();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.url= "http://poetrydb.org/author/"+adapterView.getItemAtPosition(i).toString();
                Intent intent= new Intent(Categories.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                finish();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Type Author Name");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }

    private void loadData() {


        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://poetrydb.org/author", new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject obj=new JSONObject(response);
                    String arr[]=obj.getString("authors").split(",");
                    String firstName=arr[0];
                    String mod=firstName.substring(1,firstName.length());
                    String s1 = mod.replaceAll("\"", "");
                    arrayList.add(s1);
                    for (int i=1; i<arr.length; i++){

                        String str=arr[i];
                        String s2 = str.replaceAll("\"", "");

                        if(s2.charAt(s2.length()-1)==']'){

                            arrayList.add(s2.substring(0,s2.length()-1));
                        }
                        else{

                            arrayList.add(s2);
                        }
                    }

                    mAdapter = new ArrayAdapter(Categories.this, android.R.layout.simple_list_item_1,arrayList);
                    mListView.setAdapter(mAdapter);




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Categories.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
