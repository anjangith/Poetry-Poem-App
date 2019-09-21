package digital.starein.com.project_june27;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyFragment extends Fragment {
    String line;
    String author;
    String title;
    int resource;
    private static final String LINE = "line";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String RESOURCE = "resource" ;

    private TextView titleText;
    private TextView authorText;
    private TextView lineText;
    private Button readBtn;
    private RelativeLayout rl;
    private CardView cardView;
    private FloatingActionButton floatingActionButton;

    public MyFragment() {
        // Required empty public constructor
    }


    public static MyFragment newInstance(String author,String title,String lines,int resource) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle(3);
        bundle.putString(LINE, lines);
        bundle.putString(AUTHOR,author);
        bundle.putString(TITLE,title);
        bundle.putInt(RESOURCE,resource);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.line = getArguments().getString(LINE);
        this.author=getArguments().getString(AUTHOR);
        this.title=getArguments().getString(TITLE);
        this.resource=getArguments().getInt(RESOURCE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        titleText = view.findViewById(R.id.title);
        authorText=view.findViewById(R.id.author);
        lineText=view.findViewById(R.id.textView);
        floatingActionButton=(FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        cardView=(CardView)view.findViewById(R.id.myCard);
        rl=(RelativeLayout)view.findViewById(R.id.rl);
        AssetManager am = getActivity().getApplicationContext().getAssets();
        Typeface custom_font1 = Typeface.createFromAsset(am,"fonts/Sansation-Regular.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(am,"fonts/SEASRN__.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(am,"fonts/OpenSans-Light.ttf");
        titleText.setTypeface(custom_font2);
        authorText.setTypeface(custom_font1);
        lineText.setTypeface(custom_font3);
        rl.setBackground(getResources().getDrawable(resource));
        readBtn=(Button)view.findViewById(R.id.readBTN);
        titleText.setText(title);
        authorText.setText(author);
        lineText.setText(line);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Download this app from Google Play to read thousands poems by famous poets worldwide.\n";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Download the app now!");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,shareBody+"Read the poem "+title+" by "+author);
                startActivity(Intent.createChooser(sharingIntent, shareBody+"Share via"));
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ReadContent.class);
                intent.putExtra("title",title);
                intent.putExtra("author",author);
                intent.putExtra("lines",line);
                startActivity(intent);
            }
        });

       /* cardView.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                startActivity(new Intent(getActivity(), Categories.class));
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
            }

            @Override
            public void onSwipeRight() {
                startActivity(new Intent(getActivity(), Likes.class));
                getActivity().overridePendingTransition(R.anim.enternew, R.anim.exitnew);


            }
        });*/

        return view;    }

}
