/*
 * Copyright (C) 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package saulmm.coordinatorexamples;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import saulmm.coordinatorexamples.staggeredrecycler.BenefitsDataAdapter;
import saulmm.coordinatorexamples.staggeredrecycler.PostItem;
import saulmm.coordinatorexamples.staggeredrecycler.PostsAdapter;
import saulmm.coordinatorexamples.staggeredrecycler.ViewType;

public class SimpleCoordinatorActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private AppBarLayout appbarLayout;
    private Toolbar toolbar, toolbarCollapsed;
    private ConstraintLayout cardCoupon;
    private LinearLayout tvSearchBar;
    private com.google.android.material.tabs.TabLayout llTabs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_coordinator);
        appbarLayout = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        toolbarCollapsed = findViewById(R.id.toolbar_collapsed);
        cardCoupon = findViewById(R.id.card_coupon);
        tvSearchBar = findViewById(R.id.ll_searchbar);
        llTabs = findViewById(R.id.tl_categories);

        appbarLayout.addOnOffsetChangedListener(this);
        appbarLayout.setExpanded(true);

        setPager();

    }

    public static void start(Context c) {
        c.startActivity(new Intent(c, SimpleCoordinatorActivity.class));
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        double offsetAlpha = -(appbarLayout.getY() / appbarLayout.getTotalScrollRange());
        /**Here 0.7 signifies the 7 percent of the screen from where animation will start on offset scroll*/
        if (offsetAlpha > 0.7) {
//            cardCoupon.setVisibility(View.INVISIBLE);
            llTabs.setVisibility(View.INVISIBLE);
            tvSearchBar.setVisibility(View.INVISIBLE);
            showToolbarWithAnim();
            /** Hide Logo collapsable view when showing toolbar **/

        } else {
            hideToolbarWithAnim();
            /** Show Logo collapsable view when hiding toolbar **/

        }
    }

    private void hideToolbarWithAnim() {
        if (toolbarCollapsed.getVisibility() == View.VISIBLE) {
//            Animation animationDown = AnimationUtils.loadAnimation(this, R.anim.view_close_from_bottom_up);
//            toolbarCollapsed.startAnimation(animationDown);
//            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//                @Override
//                public void run() {
            toolbarCollapsed.setVisibility(View.INVISIBLE);
            toolbarCollapsed.setClickable(false);
            toolbarCollapsed.setFocusable(false);
            toolbar.setVisibility(View.VISIBLE);

//                    cardCoupon.setVisibility(View.VISIBLE);
            llTabs.setVisibility(View.VISIBLE);
            tvSearchBar.setVisibility(View.VISIBLE);

//                }
//            }, 50);
        }
    }

    private void showToolbarWithAnim() {
        if (toolbarCollapsed.getVisibility() == View.INVISIBLE) {
//            Animation animationDown = AnimationUtils.loadAnimation(this, R.anim.slide_from_up);
//            toolbarCollapsed.startAnimation(animationDown);
//            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//                @Override
//                public void run() {
            toolbarCollapsed.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.INVISIBLE);
            toolbarCollapsed.setClickable(true);
            toolbarCollapsed.setFocusable(true);
//                }
//            }, 50);
        }

    }


    private void setPager() {
        BenefitsDataAdapter benefitsDataAdapter = new BenefitsDataAdapter(this);
        ViewPager2 benefitsVp = findViewById(R.id.benefits_vp);
        benefitsVp.setAdapter(benefitsDataAdapter);

        new TabLayoutMediator(llTabs, benefitsVp, (tab, position) -> {
            tab.setText(benefitsDataAdapter.getItemTitle(position));
            //tab.setCustomView(react_with_any_emoji_tab)
            //      .setIcon(ThemeUtil.getThemedDrawable(requireContext(), viewModel.getCategoryIconAttr(position)));
        }).attach();
    }
}
