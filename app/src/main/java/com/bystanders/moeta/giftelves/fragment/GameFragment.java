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
 *
 */
public class GameFragment extends Fragment {
    List<Fragment> fragments;
    @BindView(R.id.game_viewPager)
    ViewPager viewPager;
    @BindView(R.id.game_tabLayout)
    TabLayout tabLayout;

    private static final String[] titles = new String[]{"开服", "开测"};
    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        ButterKnife.bind(this,view);
        OpenServiceFragment serviceFragment = new OpenServiceFragment();
        OpenTestFragment testFragment = new OpenTestFragment();
        fragments = new ArrayList<>();
        fragments.add(serviceFragment);
        fragments.add(testFragment);

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
