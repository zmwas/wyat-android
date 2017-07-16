package com.wyat.wyat.events.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.wyat.entities.Event;
import com.wyat.wyat.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zack on 16/01/17.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    private Context context;
    private List<Event> events;
    private RecyclerClickListener recyclerClickListener;

    public EventListAdapter(List<Event> events, Context context,RecyclerClickListener recyclerClickListener) {

        this.context = context;
        this.events = events;
        this.recyclerClickListener = recyclerClickListener;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.bindEvent(events.get(position));
        final Event event= events.get(position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerClickListener.OnItemClicked(position,event,v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.event_datetime)
        TextView datetime;
        @Bind(R.id.event_description)
        TextView description;
        @Bind(R.id.event_name)
        TextView event_name;
        @Bind(R.id.event_picture)
        ImageView event_picture;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);


        }
        public void bindEvent(Event event){
            datetime.setText(event.getTime());
            description.setText(event.getDescription());
            event_name.setText(event.getName());




        Picasso.with(context).load(event.getEventPicUrl()).fit().into(event_picture);




        }



    }
}
