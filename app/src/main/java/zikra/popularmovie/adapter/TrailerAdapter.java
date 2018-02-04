package zikra.popularmovie.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import zikra.popularmovie.R;
import zikra.popularmovie.data.TrailerData;

import com.squareup.picasso.Picasso;

import java.util.List;


public class TrailerAdapter  extends RecyclerView.Adapter<TrailerAdapter.NumberViewHolder> {

    private static final String TAG = TrailerAdapter.class.getSimpleName();


    private static int viewHolderCount;


    private Activity activity;
    private List<TrailerData> dirItems;

    TrailerData dir;

    public TrailerAdapter(Activity activity,  DirAdapterOnClickHandler mClickHandler) {

        this.activity = activity;
        this.mClickHandler = mClickHandler;
    }


    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new NumberViewHolder that holds the View for each list item
     */
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.trailer_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        viewHolderCount++;

        Log.d(TAG, "#####" + viewHolderCount);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(NumberViewHolder viewHolder, int position) {
        Log.d(TAG, "#" + position);


        dir = dirItems.get(position);






        String image = "http://i4.ytimg.com/vi/"+ dir.getKey() +"/hqdefault.jpg" ;
        // Log.e("image", image);
        Picasso.with(activity)
                .load(image)
                .into(viewHolder.imgTrailer);
        viewHolder.bind(position);
    }


    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {

        if (null == dirItems) return 0;

        //Log.d(TAG, "Besar" + dirItems.size());
        return dirItems.size();
    }

    public void clearData() {
        int size = this.dirItems.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                dirItems.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

    public void setDirData(List<TrailerData> weatherData) {
        dirItems = weatherData;
        notifyDataSetChanged();
    }




    /**
     * Cache of the children views for a list item.
     */
    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView imgTrailer;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         *
         * @param itemView The View that you inflated in
         *                 {@link MovieAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public NumberViewHolder(View itemView) {
            super(itemView);


            imgTrailer = (ImageView) itemView.findViewById(R.id.imgTrailer);

            itemView.setOnClickListener(this);
        }


        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         *
         * @param listIndex Position of the item in the list
         */
        void bind(int listIndex) {

            //listItemNumberView.setText(String.valueOf(listIndex));
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            // String weatherForDay = dir.getTitle();
            mClickHandler.onClick(dirItems.get(adapterPosition));
        }
    }


    private final DirAdapterOnClickHandler mClickHandler;

    public interface DirAdapterOnClickHandler {
        void onClick(TrailerData weatherForDay);
    }

    public TrailerAdapter(DirAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }
}
