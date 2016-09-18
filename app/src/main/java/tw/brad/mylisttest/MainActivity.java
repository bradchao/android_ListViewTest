package tw.brad.mylisttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data;
    private String[] from = {"title"};
    private int[] to = {R.id.item_title};

    private String[] titles = {"Item 1","Item 2","Item 3","Item 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        initListViewV1();
    }

    private void initListViewV1(){
        data = new LinkedList<>();
        for (String itemTitle: titles) {
            HashMap<String, String> item = new HashMap<>();
            item.put(from[0], itemTitle);
            data.add(item);
        }
        adapter = new SimpleAdapter(this,data,R.layout.layout_item,from,to);
        listView.setAdapter(adapter);
    }

    private void initListViewV2(){
        listView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        MyAdapter(){
            inflater = LayoutInflater.from(MainActivity.this);
        }
        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public Object getItem(int i) {
            return titles[i];
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = inflater.inflate(R.layout.layout_item, viewGroup, false);
            }
            TextView title = (TextView)view.findViewById(R.id.item_title);
            title.setText(titles[i]);
            return view;
        }
    }
}
