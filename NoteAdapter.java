package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class NoteAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<NoteBean> list;
    public NoteAdapter(Context context, List<NoteBean> list){
        this.layoutInflater=LayoutInflater.from(context);
        this.list=list;
    }
    @Override
    public  int getCount(){
        return list==null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position){
        return  position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  viewHolder;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.notepad_item_layout,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        NoteBean noteInfo = (NoteBean) getItem(position);
        viewHolder.tvNoteoadContent.setText(noteInfo.getNotepadContent());
        return convertView;
    }
    class ViewHolder{
        TextView tvNoteoadContent;
        TextView tvNotepadTime;
        public ViewHolder(View view){
            tvNoteoadContent=(TextView) view.findViewById(R.id.item_content);
            tvNotepadTime=(TextView) view.findViewById(R.id.item_time);
        }
    }
}
