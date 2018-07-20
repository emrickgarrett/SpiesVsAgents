package garyapps.spiesvsagents.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import garyapps.spiesvsagents.Models.TextCard;
import garyapps.spiesvsagents.R;

public class TextCardAdapter extends ArrayAdapter<TextCard> {

    private static class ViewHolder {
        int position;
        TextView cardName;
    }

    public TextCardAdapter(Context context, ArrayList<TextCard> cards) {
        super(context, 0, cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextCard card = getItem(position);

        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_text_card_small, parent, false);
            holder.cardName = (TextView) convertView.findViewById(R.id.cardName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.cardName.setText(card.getValue());
        holder.cardName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) ((ViewHolder)view.getTag()).position;

                TextCard card = getItem(position);

                //Create Dialog and display value on big screen
                Toast.makeText(getContext(), card.getValue(), Toast.LENGTH_SHORT);
            }
        });

        return convertView;
    }
}
