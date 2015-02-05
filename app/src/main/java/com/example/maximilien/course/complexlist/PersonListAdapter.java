package com.example.maximilien.course.complexlist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maximilien.course.R;

import java.util.List;

import roboguice.inject.InjectView;

/**
 * Created by Maximilien on 22/01/2015.
 */
public class PersonListAdapter extends BaseAdapter {
    private Context context;
    private List<Person> dataSource;

    public PersonListAdapter(Context context, List<Person> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
//        ViewHolder holder;
//        if (view != null) {
//            holder = (ViewHolder) view.getTag();
//        } else {
//            view = LayoutInflater.from(context).inflate(R.layout.person_item, null);
//            holder = new ViewHolder(view);
//            view.setTag(holder);
//        }
//
//        holder.updateView(dataSource.get(position));
//
//        return view;
        View cellView = view;
        Person person = dataSource.get(position);
        PersonItemViewHolder viewHolder;
        if(cellView == null) {
            viewHolder = new PersonItemViewHolder(context);
            cellView = viewHolder.getView();
            cellView.setTag(viewHolder);
        }

        viewHolder = (PersonItemViewHolder) cellView.getTag();
        viewHolder.updateView(person);
        return cellView;
    }

    static class ViewHolder extends SuperViewHolder {

        @InjectView(R.id.firstname_textview)
        private TextView firstNameTextView;

        @InjectView(R.id.lastname_textview)
        private TextView lastNameTextView;

        @InjectView(R.id.color_view)
        private View colorView;

        public ViewHolder(View v) {
            super(v);
        }

        public void updateView(Person person) {
            firstNameTextView.setText(person.getFirstName());
            lastNameTextView.setText(person.getLastName());
            colorView.setBackgroundColor(person.getFavoriteColor());
        }

    }
}
