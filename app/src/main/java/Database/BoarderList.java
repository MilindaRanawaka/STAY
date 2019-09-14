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

public class BoarderList extends ArrayAdapter<Boarder> {
    private Activity context;
    private List<Boarder> boarderList;

    public BoarderList(Activity context,List<Boarder> boarder) {
        super(context, R.layout.view_boarder_layout_list,boarder);
        this.context=context;
        this.boarderList = boarder;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.view_boarder_layout_list,null,true);

        TextView textno11 = (TextView) listViewItem.findViewById(R.id.viewBoarderName);
        TextView txtno22 =(TextView) listViewItem.findViewById(R.id.viewBoarderNIC);
        TextView txtno33 = (TextView) listViewItem.findViewById(R.id.viewBoarderPhno);
        TextView txtno44 = (TextView) listViewItem.findViewById(R.id.viewBoarderRoomNo);

        Boarder boarder = boarderList.get(position);

        textno11.setText(boarder.getName());
        txtno22.setText(boarder.getNic());
        txtno33.setText(String.valueOf(boarder.getPhNo()));
        txtno44.setText(boarder.getRoomNo());

        return listViewItem;
    }
}
