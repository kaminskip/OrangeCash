package eu.netforms.orangecash.view;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagesAdapter extends FragmentPagerAdapter {
	
	List<PageFragment> fragments = new ArrayList<PageFragment>();

	public PagesAdapter(FragmentManager fm) {
		super(fm);
		fragments.add(new BalanceFragment());
		fragments.add(new TransacionFragment());
		fragments.add(new InfoFragment());
	}

	@Override
	public Fragment getItem(int item) {
		return (Fragment) fragments.get(item);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

}
