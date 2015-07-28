package au.com.websutra.personaldictionary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import au.com.websutra.personaldictionary.DictionaryObject;
import au.com.websutra.personaldictionary.R;

/**
 * Created by nischal.manandhar on 7/28/2015.
 */
public class MyListViewAdapter extends BaseAdapter {
    private Context _context;
    private ArrayList<DictionaryObject> _list;
    public MyListViewAdapter(Context context,ArrayList<DictionaryObject> list) {
        this._context=context;
        _list=new ArrayList<DictionaryObject>();
        this._list=list;

    }

    @Override
    public int getCount() {
        return _list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.list_item,parent,false);
            }
        TextView tId= (TextView) convertView.findViewById(R.id.textView_list_item_id);
        TextView tWord= (TextView) convertView.findViewById(R.id.textView_list_item_word);
        TextView tFrequency= (TextView) convertView.findViewById(R.id.textView_list_item_frequency);

        tId.setText(_list.get(position).id+"");
        tWord.setText(_list.get(position).word);
        tFrequency.setText(_list.get(position).frequency+"");
        return convertView;
    }
}
