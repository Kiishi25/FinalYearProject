package com.example.fyp;

/*import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;

import java.util.List;

import butterknife.ButterKnife;

public class RecyclerAdapter extends  RecyclerView.Adapter<BaseViewHolder>{

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Video> mYoutubeVideos;
    DisplayMetrics displayMetrics = new DisplayMetrics();

    public RecyclerAdapter(List<Video> youtubeVideos) {
        mYoutubeVideos = youtubeVideos;
    }

    @NonNull

    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_list, parent, false));
    }


    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }



    public int getItemViewType(int position) {
        return VIEW_TYPE_NORMAL;
    }


    public int getItemCount() {
        if (mYoutubeVideos != null && mYoutubeVideos.size() > 0) {
            return mYoutubeVideos.size();
        } else {
            return 1;
        }
    }

    public void setItems(List<Video> youtubeVideos) {
        mYoutubeVideos = youtubeVideos;
        notifyDataSetChanged();
    }


    public class ViewHolder extends BaseViewHolder {
        @SuppressLint("NonConstantResourceId")
       // @BindView(R.id.textViewTitle)
        TextView textWaveTitle;
      //  @BindView(R.id.btnPlay)
        ImageView playButton;

     //   @BindView(R.id.imageViewItem)
        ImageView imageViewItems;
      //  @BindView(R.id.youtube_view)
        YouTubePlayerView youTubePlayerView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);
            final Video mYoutubeVideo = mYoutubeVideos.get(position);
            ((Activity) itemView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            if (mYoutubeVideo.getTitle() != null)
                textWaveTitle.setText(mYoutubeVideo.getTitle());

            if (mYoutubeVideo.getImageUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(mYoutubeVideo.getImageUrl()).
                        apply(new RequestOptions().override(width - 36, 200))
                        .into(imageViewItems);
            }
            imageViewItems.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.VISIBLE);
            youTubePlayerView.setVisibility(View.GONE);

            playButton.setOnClickListener(view -> {
                imageViewItems.setVisibility(View.GONE);
                youTubePlayerView.setVisibility(View.VISIBLE);
                playButton.setVisibility(View.GONE);
                youTubePlayerView.initialize(initializedYouTubePlayer -> initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        initializedYouTubePlayer.loadVideo(mYoutubeVideo.getVideoId(), 0);
                    }
                }), true);
            });
        }
    }
}

 */

public class RecyclerAdapter{

}
