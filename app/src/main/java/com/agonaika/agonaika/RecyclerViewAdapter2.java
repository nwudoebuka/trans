package com.agonaika.agonaika;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by Juned on 2/8/2017.
 */

import java.util.List;
import com.android.volley.toolbox.ImageLoader;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import api.DataAdapter2;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> {

    Context context;

    List<DataAdapter2> dataAdapters;

    ImageLoader imageLoader;

    public RecyclerViewAdapter2(List<DataAdapter2> getDataAdapter, Context context){

        super();
        this.dataAdapters = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_timeentry, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        DataAdapter2 dataAdapterOBJ =  dataAdapters.get(position);

        imageLoader = ImageAdapter.getInstance(context).getImageLoader();

        imageLoader.get(dataAdapterOBJ.getImageUrl(),
                ImageLoader.getImageListener(
                        Viewholder.VollyImageView,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        Viewholder.VollyImageView.setImageUrl(dataAdapterOBJ.getImageUrl(), imageLoader);

        Viewholder.ImageTitleTextView.setText(dataAdapterOBJ.getImageTitle());
        Viewholder.ImageTitleTextView2.setText(dataAdapterOBJ.getImageTitle2());
        Viewholder.ImageTitleTextView3.setText(dataAdapterOBJ.getImageTitle3());

    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleTextView;
        public TextView ImageTitleTextView2;
        public TextView ImageTitleTextView3;
        public NetworkImageView VollyImageView ;

        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleTextView = itemView.findViewById(R.id.ImageNameTextView) ;
            ImageTitleTextView2 = itemView.findViewById(R.id.ImageNameTextView2) ;
            ImageTitleTextView3 = itemView.findViewById(R.id.ImageNameTextView3) ;

            VollyImageView = (NetworkImageView) itemView.findViewById(R.id.VolleyImageView) ;

        }
    }
}
