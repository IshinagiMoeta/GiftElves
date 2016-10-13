package com.bystanders.moeta.giftelves.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bystanders.moeta.giftelves.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialFragment extends Fragment {

    List<Fragment> fragments;
    @BindView(R.id.special_viewPager)
    ViewPager viewPager;
    @BindView(R.id.special_tabLayout)
    TabLayout tabLayout;
    private static final String[] titles = new String[]{"暴打星期三", "新游周刊"};

    public SpecialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_special, container, false);
        ButterKnife.bind(this,view);
        WednesdayFragment wednesdayFragment = new WednesdayFragment();
        WeeklyFragment weeklyFragment = new WeeklyFragment();
        fragments = new ArrayList<>();
        fragments.add(wednesdayFragment);
        fragments.add(weeklyFragment);
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        viewPager.setAdapter(pagerAdapter);

        return view;
    }

}
