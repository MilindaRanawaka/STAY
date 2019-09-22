package Database;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.stay.R;

import java.util.List;

public class OrderList extends ArrayAdapter<Order> {

    private Activity context;
    private List<Order> orderList;


    public OrderList(Activity context, List<Order> orderList){
        super(context, R.layout.view_food_list,orderList);
        this.context=context;
        this.orderList=orderList;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listViewItem=inflater.inflate(R.layout.view_food_list,null,true);

        TextView tvName=(TextView) listViewItem.findViewById(R.id.tvName);
        TextView tvRNo=(TextView) listViewItem.findViewById(R.id.tvRNo);
        TextView tvgenre=(TextView) listViewItem.findViewById(R.id.tvGenre);


        Order order=orderList.get(position);

        tvName.setText(order.getName());
        tvRNo.setText(order.getRNo());
        tvgenre.setText(order.getBgenre());


        return listViewItem;
    }

}

