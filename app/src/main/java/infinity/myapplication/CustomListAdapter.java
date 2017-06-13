package infinity.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] itemname,decri,dat;
	private final Integer imgid;
	
	public CustomListAdapter(Activity context, String[] itemname,String[] descri,String[] date, Integer imgid) {
		super(context, R.layout.mylist, itemname);
		// TODO Auto-generated constructor stub
		
		this.context=context;
		this.itemname=itemname;
		this.imgid=imgid;
		this.decri=descri;
		this.dat=date;
	}
	
	public View getView(int position,View view,ViewGroup parent) {
		LayoutInflater inflater=context.getLayoutInflater();
		View rowView=inflater.inflate(R.layout.mylist, null, true);
		
		TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
		TextView date= (TextView) rowView.findViewById(R.id.date);
		
		txtTitle.setText(itemname[position]);
		imageView.setImageResource(imgid);
		extratxt.setText("On "+decri[position] +"Transaction");
		date.setText( dat[position]);
		return rowView;
		
	};
}
