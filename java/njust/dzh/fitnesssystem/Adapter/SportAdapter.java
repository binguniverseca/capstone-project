package njust.dzh.fitnesssystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

import njust.dzh.fitnesssystem.Activity.ExerciseActivity;
import njust.dzh.fitnesssystem.R;
import njust.dzh.fitnesssystem.Bean.Sport;
import njust.dzh.fitnesssystem.Activity.SportActivity;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder>{
    private Context mContext;
    private List<Sport> mSportList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.sport_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = holder.getAdapterPosition();

                Sport sport = mSportList.get(position);

                Intent intent = new Intent(mContext, ExerciseActivity.class);

                intent.putExtra(SportActivity.DATA, sport);

                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sport sport = mSportList.get(position);
        Glide.with(mContext).load(sport.getImageId()).into(holder.sportImage);
    }

    @Override
    public int getItemCount() {
        return mSportList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView sportImage;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            sportImage = itemView.findViewById(R.id.sport_image);
        }
    }

    public SportAdapter(List<Sport> sportList) {
        mSportList = sportList;
    }


}
