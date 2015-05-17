package perchello.hangman.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import perchello.hangman.Model.Highscore;
import perchello.hangman.Model.UserInfo;
import perchello.hangman.R;
import perchello.hangman.UI.HighscoreActivity;

/**
 * Created by Perchello on 22/03/2015.
 */
public class HighscoreAdapter extends RecyclerView.Adapter<HighscoreAdapter.HighscoreViewHolder> {
      private Context mContext;
      private String[] mName;
      private int [] mScore;

    public HighscoreAdapter (Context context, String [] usernames, int [] scores){
        mContext = context;
        mName = usernames;
        mScore = scores;

    }

    @Override
    public HighscoreViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.highscore_list_item, parent, false);
        HighscoreViewHolder viewHolder = new HighscoreViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HighscoreViewHolder holder, int position) {
            holder.bindHighscore((position + 1) + "", mName[position]+" ", mScore[position]+" ");

    }

    @Override
    public int getItemCount() {
        return mName.length;
    }


    public class HighscoreViewHolder extends RecyclerView.ViewHolder {
        private TextView mPlaceLabel;
        private TextView mNameLabel;
        private TextView mScoreLabel;

        public HighscoreViewHolder(View itemView) {
            super(itemView);

            mPlaceLabel = (TextView) itemView.findViewById(R.id.highscorePlaceLabel);
            mNameLabel = (TextView) itemView.findViewById(R.id.highscoreNameLabel);
            mScoreLabel = (TextView) itemView.findViewById(R.id.highscoreScoreLabel);
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/Fedora.ttf");
            mPlaceLabel.setTypeface(typeface);
            mNameLabel.setTypeface(typeface);
            mScoreLabel.setTypeface(typeface);
        }

        public void bindHighscore (String place, String name, String score) {
            mPlaceLabel.setText(place+ ". ");
            mNameLabel.setText(name);
            mScoreLabel.setText(score);
        }
    }
}
