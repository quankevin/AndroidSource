package com.boyle.util.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boyle.musicplayer.R;

/***
 * 自定义适配器
 * 
 * @author zhangjia
 * 
 */
public class DragAdapter extends BaseAdapter {
	private ArrayList<String> arrayTitles;
	private ArrayList<Integer> arrayDrawables;
	private Context context;

	public DragAdapter(Context context, ArrayList<String> arrayTitles,
			ArrayList<Integer> arrayDrawables) {
		this.context = context;
		this.arrayTitles = arrayTitles;
		this.arrayDrawables = arrayDrawables;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		/***
		 * 在这里尽可能每次都进行实例化新的，这样在拖拽ListView的时候不会出现错乱.
		 * 具体原因不明，不过这样经过测试，目前没有发现错乱。虽说效率不高，但是做拖拽LisView足够了。
		 */
		view = LayoutInflater.from(context).inflate(R.layout.drag_row, null);
		TextView textView = (TextView) view.findViewById(R.id.title);
		ImageView imageView = (ImageView) view.findViewById(R.id.thumbnail);
		imageView.setImageResource(arrayDrawables.get(position));
		textView.setText(arrayTitles.get(position));
		return view;
	}

	/***
	 * 动态修改ListView的方位.
	 * 
	 * @param start
	 *            点击移动的position
	 * @param down
	 *            松开时候的position
	 */
	public void update(int start, int down) {
		// 获取删除的东东.
		String title = arrayTitles.get(start);
		int drawable_id = arrayDrawables.get(start);

		arrayTitles.remove(start);// 删除该项
		arrayDrawables.remove(start);// 删除该项

		arrayTitles.add(down, title);// 添加删除项
		arrayDrawables.add(down, drawable_id);// 添加删除项

		notifyDataSetChanged();// 刷新ListView
	}
	
	@Override
	public int getCount() {
		//return Title.length;
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
		//return Title[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
