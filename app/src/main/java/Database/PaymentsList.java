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

public class PaymentsList extends ArrayAdapter<Payments> {

    private Activity context;
    private List<Payments> payList;

    public PaymentsList(Activity context,List<Payments>payList){

        super(context, R.layout.list_payments);
        this.context=context;
        this.payList=payList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();
        View listViewItem =inflater.inflate(R.layout.list_payments,null,true);{
            TextView PayDateItem =listViewItem.findViewById(R.id.ViewDatepay);
            TextView PayAmountItem=listViewItem.findViewById(R.id.viewAmountPay);
            TextView PayBoarderIDItem=listViewItem.findViewById(R.id.viewBiD);
            TextView PayRoomTypeItem=listViewItem.findViewById(R.id.ViewRoomType);

            Payments payments= payList.get(position);

            PayDateItem.setText(payments.getPayDate());
            PayAmountItem.setText(Double.toString(payments.getPayAmount()));
            PayBoarderIDItem.setText(payments.getUserID());
            PayRoomTypeItem.setText(payments.getRoomType());

            return  listViewItem;
        }
    }
}



