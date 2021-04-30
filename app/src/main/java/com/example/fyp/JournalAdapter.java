package com.example.fyp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class JournalAdapter  extends FirebaseRecyclerAdapter<Journal, JournalAdapter.ViewHolder> {

    private String TAG = "History";
    private List<Journal> journalList;
    private Context context;
    private Button goals;
    public JournalAdapter (FirebaseRecyclerOptions<Journal>  journalList) {
        super(journalList);
        //  this.goalList = goalList;
        //  goalList = new FirebaseRecyclerOptions<Goals>();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull JournalAdapter.ViewHolder holder, int position, @NonNull Journal model) {
        holder.grate.setText( "Grateful:" + " " + model.getGrateful());

    }

    @NonNull
    public JournalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieved_layout_journal, parent, false);
        return new JournalAdapter.ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView grate;
        TextView period;
        TextView type;
        TextView id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            grate = (TextView)itemView.findViewById(R.id.grateful);


        }
    }
}
