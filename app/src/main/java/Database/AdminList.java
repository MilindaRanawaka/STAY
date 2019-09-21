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

public class AdminList extends ArrayAdapter<Admin> {

    private Activity context;
    private List<Admin> adminList;

    public AdminList(Activity context, List<Admin> stuList) {
        super(context, R.layout.room_list_layout,stuList);
        this.context=context;
        this.adminList = stuList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.admin_list_layout,null,true);
        TextView textno11 = (TextView) listViewItem.findViewById(R.id.viewAdminName);
        TextView txtno22 =(TextView) listViewItem.findViewById(R.id.viewAdminNIC);
        TextView txtno3 =(TextView) listViewItem.findViewById(R.id.viewAdminPhno);
        TextView txtno4 =(TextView) listViewItem.findViewById(R.id.viewAdminRoomNo);

        Admin admin = adminList.get(position);

        textno11.setText(admin.getName());
        txtno22.setText(admin.getNic());
        txtno3.setText(String.valueOf(admin.getPhNo()));
        txtno4.setText(admin.getEmail());

        return  listViewItem;
    }
}
