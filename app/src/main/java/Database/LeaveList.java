package Database;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.stay.R;

import java.util.List;

public class LeaveList extends ArrayAdapter<LeaveReq> {


    private Activity context;
    private List<LeaveReq> leaveReqList;

    public LeaveList(Activity activity, List<LeaveReq>leaveReqList){
        super(activity, R.layout.leave_layout_list,leaveReqList);
        this.context = activity;
        this.leaveReqList = leaveReqList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.leave_layout_list,null,true);

        TextView nameTextView =(TextView) listViewItem.findViewById(R.id.ManageNameTextView22);
        TextView noTextView =(TextView) listViewItem.findViewById(R.id.ManageRoomTextView22);
        TextView leaveReasonTextView =(TextView) listViewItem.findViewById(R.id.ManageLeaveReasonTextView22);
        TextView dateTextView =(TextView) listViewItem.findViewById(R.id.ManageDateTextView22);

        LeaveReq leaveReq = leaveReqList.get(position);

        nameTextView.setText(leaveReq.getName());
        noTextView.setText(leaveReq.getRoomNo());
        leaveReasonTextView.setText(leaveReq.getReason());
        dateTextView.setText(leaveReq.getLeaveDate());

        return listViewItem;
    }
}
