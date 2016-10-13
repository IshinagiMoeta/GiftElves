package com.bystanders.moeta.giftelves.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.fragment.GameFragment;
import com.bystanders.moeta.giftelves.fragment.GiftFragment;
import com.bystanders.moeta.giftelves.fragment.HotFragment;
import com.bystanders.moeta.giftelves.fragment.SpecialFragment;
import com.bystanders.moeta.giftelves.view.PagerEnabledSlidingPaneLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.slidingPanelLayout)
    PagerEnabledSlidingPaneLayout slidingPaneLayout;
    @BindView(R.id.rl)
    LinearLayout linearLayout_content;
    Fragment giftFragment;
    Fragment gameFragment;
    Fragment hotFragment;
    Fragment specialFragment;
    Fragment nowFragment;
    @BindView(R.id.main_bar_title)
    TextView barTitle;
    @BindView(R.id.main_search_btn)
    TextView searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initSlidingPaneLayout();
        initFragment();
        switchFragment(giftFragment);
    }

    private void initFragment() {
        giftFragment = new GiftFragment();
        gameFragment = new GameFragment();
        hotFragment = new HotFragment();
        specialFragment = new SpecialFragment();
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()) {
            ft.hide(nowFragment).show(fragment).commit();
        } else {
            if (nowFragment == null) {
                ft.add(R.id.main_content, fragment).commit();
            } else {
                ft.hide(nowFragment).add(R.id.main_content, fragment).commit();
            }
        }
        nowFragment = fragment;
    }

    private void initSlidingPaneLayout() {

        slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                linearLayout_content.setScaleY(1 - slideOffset * 0.4f);
                linearLayout_content.setScaleX(1 - slideOffset * 0.4f);
                if (Build.VERSION.SDK_INT >= 21) {
                    linearLayout_content.setElevation(50);
                }
            }

            @Override
            public void onPanelOpened(View panel) {
            }

            @Override
            public void onPanelClosed(View panel) {
            }
        });
    }

    @OnClick(R.id.main_gift)
    void giftClick() {
        switchFragment(giftFragment);
        searchBtn.setVisibility(View.VISIBLE);
        barTitle.setText("礼包精灵");
    }

    @OnClick(R.id.main_game)
    void gameClick() {
        switchFragment(gameFragment);
        searchBtn.setVisibility(View.GONE);
        barTitle.setVisibility(View.VISIBLE);
        barTitle.setText("开服");
    }

    @OnClick(R.id.main_hot)
    void hotClick() {
        switchFragment(hotFragment);
        searchBtn.setVisibility(View.GONE);
        barTitle.setVisibility(View.VISIBLE);
        barTitle.setText("热门游戏");
    }

    @OnClick(R.id.main_special)
    void specialClick() {
        switchFragment(specialFragment);
        searchBtn.setVisibility(View.GONE);
        barTitle.setVisibility(View.VISIBLE);
        barTitle.setText("独家企划");
    }

    @OnClick(R.id.main_bar_menu)
    void menuClick() {
        slidingPaneLayout.openPane();
    }

    @OnClick(R.id.main_search_btn)
    void searchClick(){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }
}
