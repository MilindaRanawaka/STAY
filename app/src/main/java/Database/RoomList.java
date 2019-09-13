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

public class RoomList extends ArrayAdapter<Room> {

    private Activity context;
    private List<Room> roomList;

    public RoomList(Activity context, List<Room> stuList) {
        super(context, R.layout.room_list_layout,stuList);
        this.context=context;
        this.roomList = stuList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.room_list_layout,null,true);
        TextView textno11 = (TextView) listViewItem.findViewById(R.id.capacityTxtView);
        TextView txtno22 =(TextView) listViewItem.findViewById(R.id.roomNoTxtView);
        TextView txtno3 =(TextView) listViewItem.findViewById(R.id.priceTxtView);
        TextView txtno4 =(TextView) listViewItem.findViewById(R.id.acTxtView);

        Room room = roomList.get(position);

        txtno22.setText(room.getRoomID());
        textno11.setText(Integer.toString(room.getCapacity()));
        txtno3.setText(Double.toString(room.getPrice()));
        txtno4.setText(room.getAcType());

        return  listViewItem;
    }

}
