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
   private List<Payments> paymentsList;

   public PaymentsList(Activity context,List<Payments> paymentsList){
       super(context,R.layout.list_payments,paymentsList);
       this.context =context;
       this.paymentsList=paymentsList;
   }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();

        View pastPayView =inflater.inflate(R.layout.list_payments,null,true);
        TextView datePay = pastPayView.findViewById(R.id.ViewDatepay);
        TextView amountPay = pastPayView.findViewById(R.id.viewAmountPay);
        TextView roomtPay = pastPayView.findViewById(R.id.ViewRoomType);
        TextView payStatus = pastPayView.findViewById(R.id.viewPayStatus);
        TextView payName = pastPayView.findViewById(R.id.ViewPayName);

        Payments userViewPay =paymentsList.get(position);
        datePay.setText(userViewPay.getPayDate());
        amountPay.setText(Double.toString(userViewPay.getPayAmount()));
        roomtPay.setText(userViewPay.getRoomType());
        payStatus.setText(userViewPay.getStatus());
        payName.setText(userViewPay.getName());


        return  pastPayView;

    }
}



