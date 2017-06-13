package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import adapter.TabPagerItem;
import infinity.myapplication.R;


public class ViewPagerFragment extends Fragment {
	private List<TabPagerItem> mTabs = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTabPagerItem();
    }

    private void createTabPagerItem(){
        mTabs.add(new TabPagerItem(getString(R.string.history), MainFragment.newInstance(getString(R.string.history))));
        mTabs.add(new TabPagerItem(getString(R.string.important), MainFragment.newInstance(getString(R.string.important))));
        mTabs.add(new TabPagerItem(getString(R.string.documents), MainFragment.newInstance(getString(R.string.documents))));
    }


}