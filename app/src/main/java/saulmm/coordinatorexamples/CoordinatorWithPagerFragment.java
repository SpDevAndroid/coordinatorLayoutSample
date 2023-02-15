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
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import saulmm.coordinatorexamples.staggeredrecycler.BenefitsDataAdapter;

public class CoordinatorWithPagerFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    private AppBarLayout appbarLayout;
    private Toolbar toolbar, toolbarCollapsed;
    private ConstraintLayout cardCoupon;
    private LinearLayout tvSearchBar;
    private com.google.android.material.tabs.TabLayout llTabs;
    private ViewPager2 benefitsVp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coordinator_with_pager, container, false);
        appbarLayout = view.findViewById(R.id.appbar);
        toolbar = view.findViewById(R.id.toolbar);
        toolbarCollapsed = view.findViewById(R.id.toolbar_collapsed);
        cardCoupon = view.findViewById(R.id.card_coupon);
        tvSearchBar = view.findViewById(R.id.ll_searchbar);
        llTabs = view.findViewById(R.id.tl_categories);
        benefitsVp = view.findViewById(R.id.benefits_vp);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appbarLayout.addOnOffsetChangedListener(this);
        appbarLayout.setExpanded(true);

        setPager();
    }

    public static void start(Context c) {
        c.startActivity(new Intent(c, CoordinatorWithPagerFragment.class));
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        double offsetAlpha = -(appbarLayout.getY() / appbarLayout.getTotalScrollRange());
        Log.d("TEST_2", "onOffsetChanged totalScrollRange :" + appBarLayout.getTotalScrollRange() + " offsetAlpha : " + offsetAlpha);
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
        BenefitsDataAdapter benefitsDataAdapter = new BenefitsDataAdapter(requireActivity());
        benefitsVp.setAdapter(benefitsDataAdapter);

        new TabLayoutMediator(llTabs, benefitsVp, (tab, position) -> {
            tab.setText(benefitsDataAdapter.getItemTitle(position));
            //tab.setCustomView(react_with_any_emoji_tab)
            //      .setIcon(ThemeUtil.getThemedDrawable(requireContext(), viewModel.getCategoryIconAttr(position)));
        }).attach();
    }
}
