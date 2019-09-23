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

public class PaymentManagerList extends ArrayAdapter<Payments> {

    private Activity context1;
    private List<Payments> PaymentsManageList;


    public PaymentManagerList(Activity context1,List<Payments> PaymentsManageList){
        super(context1, R.layout.manage_payment_list,PaymentsManageList);
        this.context1=context1;
        this.PaymentsManageList=PaymentsManageList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater1 =context1.getLayoutInflater();

        View listViewItemMPay = inflater1.inflate(R.layout.manage_payment_list,null,true);
        TextView idPay = listViewItemMPay.findViewById(R.id.textViewIDMpay);
        TextView namePay = listViewItemMPay.findViewById(R.id.textViewNameMPay);
        TextView amountPay=listViewItemMPay.findViewById(R.id.textViewAmountPay);
        TextView statusPay=listViewItemMPay.findViewById(R.id.textViewStatusPay);

        Payments payManage = PaymentsManageList.get(position);
        idPay.setText(payManage.getUserID());
        namePay.setText(payManage.getName());
        amountPay.setText(Double.toString(payManage.getPayAmount()));
        statusPay.setText(payManage.getStatus());

        return listViewItemMPay;

    }
}
