package digital.starein.com.project_june27;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends FragmentPagerAdapter {



    private Context context;
    private String lines, title, author;
    List<HomeFormat> ls;

    public MyAdapter(FragmentManager fm, Context context, List<HomeFormat> l) {
        super(fm);
        this.context = context;
        this.ls=l;


    }


    @Override
    public Fragment getItem(int position) {


        if (position <= getCount()) {

            int drawPos=position%5;
            if(drawPos==0){
                return MyFragment.newInstance(ls.get(position).getAuthor(),ls.get(position).getTitle(),ls.get(position).getLines(),R.drawable.back);
            }
            else if(drawPos==1){
                return MyFragment.newInstance(ls.get(position).getAuthor(),ls.get(position).getTitle(),ls.get(position).getLines(),R.drawable.back1);
            }
            else if(drawPos==2){
                return MyFragment.newInstance(ls.get(position).getAuthor(),ls.get(position).getTitle(),ls.get(position).getLines(),R.drawable.back2);
            }
            else if(drawPos==3){
                return MyFragment.newInstance(ls.get(position).getAuthor(),ls.get(position).getTitle(),ls.get(position).getLines(),R.drawable.back3);
            }
            else if(drawPos==4){
                return MyFragment.newInstance(ls.get(position).getAuthor(),ls.get(position).getTitle(),ls.get(position).getLines(),R.drawable.back4);
            }



        }


        return null;
    }

    @Override
    public int getCount() {
        return ls.size();
    }
}
