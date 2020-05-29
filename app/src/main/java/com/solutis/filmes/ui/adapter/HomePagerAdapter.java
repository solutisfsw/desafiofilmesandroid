package com.solutis.filmes.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> listFragments;

    public HomePagerAdapter(@NonNull FragmentManager fm, List<Fragment> listFragments) {
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.listFragments = listFragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }
}
