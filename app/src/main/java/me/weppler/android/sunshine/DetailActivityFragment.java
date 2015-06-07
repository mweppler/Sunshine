package me.weppler.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private static final String TAG = DetailActivityFragment.class.getSimpleName();
    private static final String SHARE_HASHTAG = " #SunshineApp";
    private String mForecastStr;

    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_detail_fragment, menu);
        MenuItem item = menu.findItem(R.id.action_share).setVisible(true);
        ShareActionProvider shareActionProvider = new ShareActionProvider(getActivity());
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(createShareForecastIntent());
            MenuItemCompat.setActionProvider(item, shareActionProvider);
        } else {
            Log.d(TAG, "Share Action Provider is null?");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            mForecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
        }
        ((TextView) rootView.findViewById(R.id.forecast_details_textview)).setText(mForecastStr);
        return rootView;
    }

    private Intent createShareForecastIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, mForecastStr + SHARE_HASHTAG);
        return shareIntent;
    }
}
