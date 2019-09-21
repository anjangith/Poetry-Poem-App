package digital.starein.com.project_june27;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemArrayAdapter extends BaseAdapter {

    Context context;
    ArrayList<CategoryFormat> list;

    public ItemArrayAdapter(Context context,ArrayList<CategoryFormat> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.listitem,viewGroup,false);
        }
        final CategoryFormat model=(CategoryFormat) this.getItem(i);
        TextView headertext= (TextView) view.findViewById(R.id.title);
        TextView descriptiontext= (TextView) view.findViewById(R.id.author);
        headertext.setText(model.getTitle());
        descriptiontext.setText(model.getAuthor());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,ReadContent.class);
                i.putExtra("title",model.getTitle());
                i.putExtra("author",model.getAuthor());
                i.putExtra("lines",model.getLines());
                context.startActivity(i);

            }
        });
        return view;
    }
}
