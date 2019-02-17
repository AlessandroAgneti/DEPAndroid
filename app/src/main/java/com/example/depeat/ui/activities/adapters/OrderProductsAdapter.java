package com.example.depeat.ui.activities.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.depeat.R;
import com.example.depeat.datamodels.Food;

import java.util.ArrayList;

public class OrderProductsAdapter extends RecyclerView.Adapter<OrderProductsAdapter.OrderProductViewHolder> {

    private ArrayList<Food> dataSet;
    private Context context;
    private LayoutInflater inflater;
    private float miniumOrder;

    public OrderProductsAdapter(Context context, ArrayList<Food> dataSet, float miniumOrder){
        this.dataSet = dataSet;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.miniumOrder = miniumOrder;
    }

    public interface onItemRemovedListener{
        void onItemRemoved(float subtotal);

    }


    private onItemRemovedListener onItemRemovedListener;


    public OrderProductsAdapter.onItemRemovedListener getOnItemRemovedListener() {
        return onItemRemovedListener;
    }

    public void setOnItemRemovedListener(OrderProductsAdapter.onItemRemovedListener onItemRemovedListener) {
        this.onItemRemovedListener = onItemRemovedListener;
    }

    @NonNull
    @Override
    public OrderProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new OrderProductViewHolder(inflater.inflate(R.layout.item_order_product, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProductViewHolder orderProductViewHolder, int i) {
        Food food = dataSet.get(i);
        orderProductViewHolder.productNameTv.setText(food.getNomeFood());
        orderProductViewHolder.quantityTv.setText(String.valueOf(food.getQuantity()));
        orderProductViewHolder.subTotalTv.setText(String.valueOf(food.getSubtotal()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    private void removeItem(int index){
        dataSet.remove(index);
        notifyItemRemoved(index);

    }
    public class OrderProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView quantityTv, productNameTv, subTotalTv;
        public ImageButton removeBtn;

        public OrderProductViewHolder(@NonNull View itemView) {
            super(itemView);
            quantityTv = itemView.findViewById(R.id.quantity_tv);
            productNameTv = itemView.findViewById(R.id.product_name_tv);
            subTotalTv = itemView.findViewById(R.id.subtotal_tv);
            removeBtn = itemView.findViewById(R.id.remove_btn);
            removeBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder myAlert = new AlertDialog.Builder(context);
            myAlert.setTitle("Remove");
            myAlert.setMessage("Are you sure to remove this?");
            myAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onItemRemovedListener.onItemRemoved(dataSet.get(getAdapterPosition()).getSubtotal());
                    removeItem(getAdapterPosition());
                }
            });

            myAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            myAlert.setCancelable(false);

            myAlert.show();

        }
    }
}
