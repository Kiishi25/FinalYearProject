package com.example.fyp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class JournalAdapter  extends FirebaseRecyclerAdapter<Journal, JournalAdapter.myviewholder> {

    private String TAG = "History";
    private List<Journal> journalList;
    private Context mcontext;
    FirebaseAuth fAuth;
    FirebaseUser rUser;
    DatabaseReference myRef;
    String userId;
    // String mGroupId;



    public JournalAdapter (Context context,FirebaseRecyclerOptions<Journal>  journalList) {

        super(journalList);
       this.mcontext = context;

    }

    


    @NonNull
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieved_layout_journal, parent, false);
        return new JournalAdapter.myviewholder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, final int position, @NonNull final Journal model) {
        holder.grate.setText( "Grateful:" + " " + model.getGrateful());
     //   holder.grate.setText( "Different:" + " " + model.getWillDo());
       // Log.i("Tracked Goal Acc", model.getToday());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(mcontext)
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();
              //  final EditText purl=myview.findViewById(R.id.uimgurl);
                final EditText grateful=myview.findViewById(R.id.ugrateful);
                final EditText reason=myview.findViewById(R.id.ureason);
                final EditText today=myview.findViewById(R.id.utoday);
                final EditText willdo=myview.findViewById(R.id.uwilldo);

                Button submit=myview.findViewById(R.id.usubmit);

               // purl.setText(model.getPurl());
                grateful.setText(model.getGrateful());
                today.setText(model.getToday());
                willdo.setText(model.getWillDo());
                reason.setText(model.getReason());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                     //   FirebaseAuth fAuth;
                        myRef = FirebaseDatabase.getInstance().getReference();
                        fAuth = FirebaseAuth.getInstance();
                        rUser = fAuth.getCurrentUser();
                        assert rUser != null;
                         userId = rUser.getUid();
                      //  String mGroupId = myRef.push().getKey();
                      //  myRef.child("Journal").child(userId).child(mGroupId);
                        //String mGroupId = myRef.push().getKey();
                        String journalId = myRef.child(fAuth.getUid()).push().getKey();
                        myRef = FirebaseDatabase.getInstance().getReference("Journal").child(userId).child(journalId);
                        HashMap<String, Object> hashMap = new HashMap<>();
                        // String journalId = myRef.child(fAuth.getUid()).push().getKey();
                        //hashMap.put("journal",journ);
                    //    hashMap.put("userId",userId);
                        hashMap.put("journal",journalId);
                        hashMap.put("grateful",grateful.getText().toString());
                        hashMap.put("reason", reason.getText().toString());
                        hashMap.put("today", today.getText().toString());
                        hashMap.put("different", willdo.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Journal").child(userId)
                                .child(getRef(position).getKey()).updateChildren(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });


            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(mcontext);
                builder.setTitle("Delete Journal Entry");
                builder.setMessage("Delete...?");
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
                fAuth = FirebaseAuth.getInstance();
                rUser = fAuth.getCurrentUser();
                assert rUser != null;
                userId = rUser.getUid();
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Journal").child(userId)
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    }




    class myviewholder extends RecyclerView.ViewHolder {
        TextView grate;
        TextView period;
        TextView type;
        TextView id;
        TextView date;
        ImageView edit,delete;
        CircleImageView img;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            grate = (TextView)itemView.findViewById(R.id.gg);
            date = (TextView)itemView.findViewById(R.id.dd);
          //  img=(CircleImageView) itemView.findViewById(R.id.img1);
            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);


        }
    }
}
