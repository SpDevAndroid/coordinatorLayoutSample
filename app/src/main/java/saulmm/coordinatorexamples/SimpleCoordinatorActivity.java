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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

import saulmm.coordinatorexamples.staggeredrecycler.PostItem;
import saulmm.coordinatorexamples.staggeredrecycler.PostsAdapter;
import saulmm.coordinatorexamples.staggeredrecycler.ViewType;

public class SimpleCoordinatorActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private AppBarLayout appbarLayout;
    private Toolbar toolbar, toolbarCollapsed;
    private ImageView cardCoupon;
    private TextView tvSearchBar;
    private LinearLayout llTabs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_coordinator);
        appbarLayout = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        toolbarCollapsed = findViewById(R.id.toolbar_collapsed);
        cardCoupon = findViewById(R.id.card_coupon);
        tvSearchBar = findViewById(R.id.tvSearchBar);
        llTabs = findViewById(R.id.llTabs);

        appbarLayout.addOnOffsetChangedListener(this);
        appbarLayout.setExpanded(true);

        setUpRecycler();
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


    private void setUpRecycler() {
        RecyclerView postsRecyclerView = findViewById(R.id.postsRecyclerView);
        StaggeredGridLayoutManager glm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        glm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        postsRecyclerView.setLayoutManager(glm);

        List<PostItem> postItems = new ArrayList<>();

        postItems.add(new PostItem(R.drawable.leeminho, ViewType.BIG.getId())); // 0
        postItems.add(new PostItem(R.drawable.leejongsuk, ViewType.SMALL.getId())); // 1
        postItems.add(new PostItem(R.drawable.chaeunwoo, ViewType.BIG.getId())); //2
        postItems.add(new PostItem(R.drawable.seokangjoon, ViewType.BIG.getId())); //3
        postItems.add(new PostItem(R.drawable.kimsoohyun, ViewType.EMPTY.getId()));//4
        postItems.add(new PostItem(R.drawable.parkseojoon, ViewType.SMALL.getId()));// 5


        postItems.add(new PostItem(R.drawable.parkseojoon, ViewType.SMALL.getId())); // 0
        postItems.add(new PostItem(R.drawable.seoinguk, ViewType.BIG.getId())); // 1
        postItems.add(new PostItem(R.drawable.jichangwook, ViewType.BIG.getId())); //2
        postItems.add(new PostItem(R.drawable.yooseungho, ViewType.BIG.getId())); //3
        postItems.add(new PostItem(R.drawable.kimsoohyun, ViewType.SMALL.getId()));//4
        postItems.add(new PostItem(R.drawable.leeseunggi, ViewType.EMPTY.getId()));// 5

        postItems.add(new PostItem(R.drawable.leeminho, ViewType.BIG.getId())); // 0
        postItems.add(new PostItem(R.drawable.leejongsuk, ViewType.SMALL.getId())); // 1
        postItems.add(new PostItem(R.drawable.chaeunwoo, ViewType.BIG.getId())); //2
        postItems.add(new PostItem(R.drawable.seokangjoon, ViewType.BIG.getId())); //3
        postItems.add(new PostItem(R.drawable.kimsoohyun, ViewType.EMPTY.getId()));//4
        postItems.add(new PostItem(R.drawable.parkseojoon, ViewType.SMALL.getId()));// 5


        postItems.add(new PostItem(R.drawable.parkseojoon, ViewType.SMALL.getId())); // 0
        postItems.add(new PostItem(R.drawable.seoinguk, ViewType.BIG.getId())); // 1
        postItems.add(new PostItem(R.drawable.jichangwook, ViewType.BIG.getId())); //2
        postItems.add(new PostItem(R.drawable.yooseungho, ViewType.BIG.getId())); //3
        postItems.add(new PostItem(R.drawable.kimsoohyun, ViewType.SMALL.getId()));//4
        postItems.add(new PostItem(R.drawable.leeseunggi, ViewType.EMPTY.getId()));// 5


        postsRecyclerView.setAdapter(new PostsAdapter(this, postItems));
    }
}
