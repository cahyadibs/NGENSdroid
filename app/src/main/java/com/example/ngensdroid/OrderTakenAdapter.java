package com.example.ngensdroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */

public class OrderTakenAdapter extends RecyclerView.Adapter<OrderTakenAdapter.OrderViewHolder> {


    private Context mCtx;
    private List<OrderTaken> orderList;
    private  static OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public static void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public OrderTakenAdapter(Context mCtx, List<OrderTaken> orderList) {
        this.mCtx = mCtx;
        this.orderList = orderList;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.order_list, null);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        OrderTaken product = orderList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);

        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getDescription());
        holder.textViewRating.setText(String.valueOf(product.getTags()));
        holder.textViewPrice.setText("Rp"+String.valueOf(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        public OrderViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}