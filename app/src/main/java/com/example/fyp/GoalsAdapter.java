package com.example.fyp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class GoalsAdapter extends FirebaseRecyclerAdapter<Goals, GoalsAdapter.ViewHolder> {
    private String TAG = "History";
    private List<Goals>goalList;
    private Context context;
    private Button goals;

    public GoalsAdapter(FirebaseRecyclerOptions<Goals>  goalList) {
        super(goalList);
      //  this.goalList = goalList;
      //  goalList = new FirebaseRecyclerOptions<Goals>();
    }


    @NonNull

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieved_layout, parent, false);
        return new GoalsAdapter.ViewHolder(view);
    }


   // public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //    Goals data = goalList.get(position);
     //   holder.name.setText(data.getNames());
        // myHolder.desc.setText(data.getDesc());
   // }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, final int position, @NonNull Goals model) {
        holder.name.setText( "Name:" + " " + model.getNames());
        holder.period.setText("Period:" + " " + model.getPeriod());
        holder.period.setText("type:" + " " + model.getType());
        holder.id.setText( model.getId());

holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
//        Goals goal = goalList.get(position);
     //   Intent intent = new Intent (context, TrackedGoalActivity.class);
      //  intent.putExtra("userid,", goal.getId());
    }
});
        //   Log.i(TAG, "Mood" + String.valueOf(model.getNames()));

    }


   // public int getItemCount() {
   //    return goalList.size();



  //}


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView period;
        TextView type;
        TextView id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
           name = (TextView)itemView.findViewById(R.id.name);
            period = (TextView)itemView.findViewById(R.id.period);
            type = (TextView)itemView.findViewById(R.id.type);
            goals = (Button) itemView.findViewById(R.id.button);
            id = (TextView) itemView.findViewById(R.id.id);
         //   desc = itemView.findViewById(R.id.desc);
            goals.setOnClickListener(new View.OnClickListener() {
                @Override
               public void onClick(View v) {
                    Context context = v.getContext();
                   Intent intent = new Intent(context, TrackedGoalActivity.class);
                    intent.putExtra("goalid",id.getText());
                  context.startActivity(intent);
               }
            });


        }

    }
}
