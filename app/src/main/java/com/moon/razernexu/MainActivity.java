package com.moon.razernexu;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.leanback.app.BackgroundManager;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.FocusHighlight;
import androidx.leanback.widget.FocusHighlightHelper;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.OnChildViewHolderSelectedListener;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.moon.razernexu.bean.Content;
import com.moon.razernexu.bean.TypeSeven;
import com.moon.razernexu.content.ContentPresenterSelector;
import com.moon.razernexu.presenter.TypeFiveContentPresenter;
import com.moon.razernexu.presenter.TypeFourContentPresenter;
import com.moon.razernexu.presenter.TypeOneContentPresenter;
import com.moon.razernexu.presenter.TypeSixContentPresenter;
import com.moon.razernexu.presenter.TypeThreeContentPresenter;
import com.moon.razernexu.presenter.TypeTwoContentPresenter;
import com.moon.razernexu.presenter.TypeZeroContentPresenter;
import com.moon.razernexu.ui.BaseActivity;
import com.moon.razernexu.utils.Constants;
import com.moon.razernexu.utils.FontDisplayUtil;
import com.moon.razernexu.utils.LocalJsonResolutionUtil;
import com.moon.razernexu.view.ScaleTextView;
import com.moon.razernexu.view.TabVerticalGridView;

import java.util.List;

public class MainActivity extends BaseActivity implements ViewTreeObserver.OnGlobalFocusChangeListener, View.OnKeyListener, View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int MSG_NOTIFY_TITLE = 100;

    private TextView mOldTitle;
    private ImageView mIvNetwork;
    private ArrayObjectAdapter mArrayObjectAdapter;

    private int mCurrentPageIndex = 0;
    private boolean isSkipTabFromViewPager = false;
    private NetworkChangeReceiver networkChangeReceiver;

    private static final String MSG_BUNDLE_KEY_ADD_ITEM = "msgBundleKeyItem";

    private static final int MSG_ADD_ITEM = 100;
    private static final int MSG_REMOVE_LOADING = 101;

    private TabVerticalGridView mVerticalGridView;
    private ProgressBar        mPbLoading;
    private ArrayObjectAdapter mAdapter;

    private BackgroundManager mBackgroundManager;



    private String mCurrentTabCode = "7359d189a049468d9d4e280fd1ec15c5";
    private Handler mHandler = new MyHandler();

    @SuppressLint("HandlerLeak")
    private class MyHandler extends Handler {


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ADD_ITEM:

                    Content content = msg.getData().getParcelable(MSG_BUNDLE_KEY_ADD_ITEM);
                    if (content == null) {
                        break;
                    }
                    List<Content.DataBean> dataBeans = content.getData();
                    for (int i = 0; i < dataBeans.size(); i++) {
                        Content.DataBean dataBean = dataBeans.get(i);
                        addItem(dataBean);
                    }

                    mPbLoading.setVisibility(View.GONE);
                    mVerticalGridView.setVisibility(View.VISIBLE);
                    break;
                case MSG_REMOVE_LOADING:
                    mPbLoading.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        Log.e(TAG, "onGlobalFocusChanged newFocus: " + newFocus);
        Log.e(TAG, "onGlobalFocusChanged oldFocus: " + oldFocus);
        if (newFocus == null || oldFocus == null) {
            return;
        }
//        oldFocus.setAlpha(0.1f);
//        newFocus.setAlpha(1f);
//        if (newFocus.getId() == R.id.tv_main_title
//                && oldFocus.getId() == R.id.tv_main_title) {
//            ((TextView) newFocus).setTextColor(getResources().getColor(R.color.colorWhite));
//            ((TextView) newFocus).getPaint().setFakeBoldText(true);
//            ((TextView) oldFocus).setTextColor(getResources().getColor(R.color.colorWhite));
//            ((TextView) oldFocus).getPaint().setFakeBoldText(false);
//        } else if (newFocus.getId() == R.id.tv_main_title
//                && oldFocus.getId() != R.id.tv_main_title) {
//            ((TextView) newFocus).setTextColor(getResources().getColor(R.color.colorWhite));
//            ((TextView) newFocus).getPaint().setFakeBoldText(true);
//        } else if (newFocus.getId() != R.id.tv_main_title
//                && oldFocus.getId() == R.id.tv_main_title) {
//            ((TextView) oldFocus).setTextColor(getResources().getColor(R.color.colorBlue));
//            ((TextView) oldFocus).getPaint().setFakeBoldText(true);
//        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_BACK) {
//            isPressBack = true;
            switch (v.getId()) {
                case R.id.cl_search:
                case R.id.cl_history:
                case R.id.cl_login:
                case R.id.cl_open_vip:
                case R.id.tv_ad:
//                    if (mHorizontalGridView != null) {
//                        mHorizontalGridView.requestFocus();
//                    }
                    return true;
                default:
                    break;
            }

        }

        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cl_search:
//                startActivity(new Intent(this, AppInstalledActivity.class));
                Toast.makeText(this, "已安装应用", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cl_history:
                Toast.makeText(this, "历史", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cl_login:
                Toast.makeText(this, "登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cl_open_vip:
                Toast.makeText(this, "开通VIP", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_ad:
                Toast.makeText(this, "新人礼包", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
        initBroadCast();

    }

//    private boolean isPressUpDownLeftRightBack = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            switch (keyCode) {
//                case KeyEvent.KEYCODE_DPAD_UP:
//                case KeyEvent.KEYCODE_DPAD_DOWN:
//                case KeyEvent.KEYCODE_BACK:
//                case KeyEvent.KEYCODE_DPAD_LEFT:
//                case KeyEvent.KEYCODE_DPAD_RIGHT:
//                    isPressUpDownLeftRightBack = true;
//                    break;
//                default:
//                    isPressUpDownLeftRightBack = false;
//                    break;
//            }
//
//        }
//        if (mViewPagerAdapter != null) {
//            ContentFragment contentFragment = (ContentFragment)
//                    mViewPagerAdapter.getRegisteredFragment(mCurrentPageIndex);
//            if (contentFragment != null && contentFragment.onKeyEvent(event)) {
//                return true;
//            }
//        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {

        getWindow().getDecorView().getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
        super.onDestroy();
        if (mThread != null) {
            mThread.interrupt();
        }
        unregisterReceiver(networkChangeReceiver);
    }

    private Group mGroup;

    public Group getGroup() {
        return mGroup;
    }

    private ConstraintLayout mClSearch;
    private ConstraintLayout mClHistory;
    private ConstraintLayout mClLogin;
    private ConstraintLayout mClOpenVip;
    private ScaleTextView    mTvAd;

    private void initView() {
        mBackgroundManager = BackgroundManager.getInstance(this);
        mBackgroundManager.attach(this.getWindow());

        mGroup = findViewById(R.id.id_group);
        mIvNetwork = findViewById(R.id.iv_network);
        mClSearch = findViewById(R.id.cl_search);
        mClHistory = findViewById(R.id.cl_history);
        mClLogin = findViewById(R.id.cl_login);
        mClOpenVip = findViewById(R.id.cl_open_vip);
        mTvAd = findViewById(R.id.tv_ad);

        mPbLoading = findViewById(R.id.pb_loading);
        mVerticalGridView = findViewById(R.id.hg_content);
//        mVerticalGridView.setAlpha(0.1f);
        mVerticalGridView.setGroup(this.getGroup());
        mVerticalGridView.setVerticalSpacing(FontDisplayUtil.dip2px(this, 24));
        ContentPresenterSelector presenterSelector = new ContentPresenterSelector();
        mAdapter = new ArrayObjectAdapter(presenterSelector);
        ItemBridgeAdapter itemBridgeAdapter = new ItemBridgeAdapter(mAdapter);
        mVerticalGridView.setAdapter(itemBridgeAdapter);
        FocusHighlightHelper.setupBrowseItemFocusHighlight(itemBridgeAdapter,
        FocusHighlight.ZOOM_FACTOR_MEDIUM, false);

        FocusHighlightHelper.setupHeaderItemFocusHighlight(itemBridgeAdapter);
        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getWindow().setAttributes(params);
    }

    private void initData() {
        mPbLoading.setVisibility(View.VISIBLE);
        mVerticalGridView.setVisibility(View.VISIBLE);

        if (mThread != null) {
            mThread.start();
        }
    }

    private void initListener() {
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalFocusChangeListener(this);
        mClSearch.setOnClickListener(this);
        mClHistory.setOnClickListener(this);
        mClLogin.setOnClickListener(this);
        mClOpenVip.setOnClickListener(this);
        mTvAd.setOnClickListener(this);

        mClSearch.setOnKeyListener(this);
        mClHistory.setOnKeyListener(this);
        mClLogin.setOnKeyListener(this);
        mClOpenVip.setOnKeyListener(this);
        mTvAd.setOnKeyListener(this);


        mVerticalGridView.addOnScrollListener(onScrollListener);
        mVerticalGridView.addOnChildViewHolderSelectedListener(onSelectedListener);
//        mVerticalGridView.add

    }

    private void initBroadCast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                switch (networkInfo.getType()) {
                    case ConnectivityManager.TYPE_ETHERNET:
                        mIvNetwork.setImageResource(R.drawable.ethernet);
                        break;
                    case ConnectivityManager.TYPE_WIFI:
                        mIvNetwork.setImageResource(R.drawable.wifi);
                        break;
                    default:
                        break;
                }
            } else {
                mIvNetwork.setImageResource(R.drawable.no_net);
            }
        }
    }



    private void addItem(Content.DataBean dataBean) {
        switch (dataBean.getContentCode()) {
            case Constants.TYPE_ZERO:
                ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new TypeZeroContentPresenter());
                List<Content.DataBean.WidgetsBean> listZero = dataBean.getWidgets();
                if (listZero != null && listZero.size() > 2) {
                    listZero = listZero.subList(0, 2);
                }
                arrayObjectAdapter.addAll(0, listZero);
                ListRow listRow = new ListRow(arrayObjectAdapter);
                addWithTryCatch(listRow);

                break;
            case Constants.TYPE_ONE:
                ArrayObjectAdapter arrayObjectAdapterOne = new ArrayObjectAdapter(new TypeOneContentPresenter());
                List<Content.DataBean.WidgetsBean> listOne = dataBean.getWidgets();
                if (listOne == null) {
                    return;
                }
                if (listOne.size() > 8) {
                    listOne = listOne.subList(0, 8);
                }
                arrayObjectAdapterOne.addAll(0, listOne);
                HeaderItem headerItem = null;
                if (dataBean.getShowTitle()) {
                    headerItem = new HeaderItem(dataBean.getTitle());
                }
                ListRow listRowOne = new ListRow(headerItem, arrayObjectAdapterOne);
                addWithTryCatch(listRowOne);

                break;
            case Constants.TYPE_TWO:
                ArrayObjectAdapter arrayObjectAdapterTwo = new ArrayObjectAdapter(new TypeTwoContentPresenter());
                List<Content.DataBean.WidgetsBean> listTwo = dataBean.getWidgets();
                if (listTwo == null) {
                    return;
                }
                if (listTwo.size() > 6) {
                    listTwo = listTwo.subList(0, 6);
                }
                arrayObjectAdapterTwo.addAll(0, listTwo);
                HeaderItem headerItemTwo = null;
                if (dataBean.getShowTitle()) {
                    headerItemTwo = new HeaderItem(dataBean.getTitle());
                }
                ListRow listRowTwo = new ListRow(headerItemTwo, arrayObjectAdapterTwo);
                addWithTryCatch(listRowTwo);

                break;
            case Constants.TYPE_THREE:
                ArrayObjectAdapter arrayObjectAdapterThree = new ArrayObjectAdapter(new TypeThreeContentPresenter());
                List<Content.DataBean.WidgetsBean> listThree = dataBean.getWidgets();
                if (listThree == null) {
                    return;
                }
                if (listThree.size() > 6) {
                    listThree = listThree.subList(0, 6);
                }
                arrayObjectAdapterThree.addAll(0, listThree);
                HeaderItem headerItemThree = null;
                if (dataBean.getShowTitle()) {
                    headerItemThree = new HeaderItem(dataBean.getTitle());
                }
                ListRow listRowThree = new ListRow(headerItemThree, arrayObjectAdapterThree);
                addWithTryCatch(listRowThree);

                break;
            case Constants.TYPE_FOUR:
                ArrayObjectAdapter arrayObjectAdapterFour = new ArrayObjectAdapter(new TypeFourContentPresenter());
                List<Content.DataBean.WidgetsBean> listFour = dataBean.getWidgets();
                if (listFour == null) {
                    return;
                }
                if (listFour.size() > 6) {
                    listFour = listFour.subList(0, 6);
                }
                arrayObjectAdapterFour.addAll(0, listFour);
                HeaderItem headerItemFour = null;
                if (dataBean.getShowTitle()) {
                    headerItemFour = new HeaderItem(dataBean.getTitle());
                }
                ListRow listRowFour = new ListRow(headerItemFour, arrayObjectAdapterFour);
                addWithTryCatch(listRowFour);

                break;
            case Constants.TYPE_FIVE:
                ArrayObjectAdapter arrayObjectAdapterFive = new ArrayObjectAdapter(new TypeFiveContentPresenter());
                List<Content.DataBean.WidgetsBean> listFive = dataBean.getWidgets();
                if (listFive == null) {
                    return;
                }
                if (listFive.size() > 6) {
                    listFive = listFive.subList(0, 6);
                }
                arrayObjectAdapterFive.addAll(0, listFive);
                HeaderItem headerItemFive = null;
                if (dataBean.getShowTitle()) {
                    headerItemFive = new HeaderItem(dataBean.getTitle());
                }
                ListRow listRowFive = new ListRow(headerItemFive, arrayObjectAdapterFive);
                addWithTryCatch(listRowFive);
                break;
            case Constants.TYPE_SIX:
                ArrayObjectAdapter arrayObjectAdapterSix = new ArrayObjectAdapter(new TypeSixContentPresenter());
                List<Content.DataBean.WidgetsBean> listSix = dataBean.getWidgets();
                if (listSix == null) {
                    return;
                }
                if (listSix.size() > 6) {
                    listSix = listSix.subList(0, 6);
                }
                arrayObjectAdapterSix.addAll(0, listSix);
                HeaderItem headerItemSix = null;
                if (dataBean.getShowTitle()) {
                    headerItemSix = new HeaderItem(dataBean.getTitle());
                }
                ListRow listRowSix = new ListRow(headerItemSix, arrayObjectAdapterSix);
                addWithTryCatch(listRowSix);
                break;
            case Constants.TYPE_SEVEN:
                TypeSeven typeSeven = new TypeSeven();
                List<Content.DataBean.WidgetsBean> listSeven = dataBean.getWidgets();
                if (listSeven == null) {
                    return;
                }
                if (listSeven.size() > 5) {
                    listSeven = listSeven.subList(0, 5);
                }
                listSeven.get(0).setBigPic(true);
                typeSeven.setWidgetsBeanList(listSeven);
                addWithTryCatch(typeSeven);
                break;
            case Constants.TYPE_EIGHT:
                ArrayObjectAdapter arrayObjectAdapterEight = new ArrayObjectAdapter(new TypeSixContentPresenter());
                HeaderItem headerItemEight = new HeaderItem("大闹天宫");
                ListRow listRowEight = new ListRow(8, headerItemEight,
                        arrayObjectAdapterEight);
//                    headerItem.setContentDescription("大闹天宫");

                arrayObjectAdapterEight.addAll(0, dataBean.getWidgets());
                addWithTryCatch(listRowEight);
                break;
            case Constants.TYPE_NINE:
                ArrayObjectAdapter arrayObjectAdapterNine = new ArrayObjectAdapter(new TypeSixContentPresenter());
                HeaderItem headerItemNine = new HeaderItem("大闹天宫");
                ListRow listRowNine = new ListRow(8, headerItemNine,
                        arrayObjectAdapterNine);
//                    headerItem.setContentDescription("大闹天宫");

                arrayObjectAdapterNine.addAll(0, dataBean.getWidgets());
                addWithTryCatch(listRowNine);
                break;
            case Constants.TYPE_TEN:
                ArrayObjectAdapter arrayObjectAdapterTen = new ArrayObjectAdapter(new TypeSixContentPresenter());
                HeaderItem headerItemTen = new HeaderItem("大闹天宫");
                ListRow listRowTen = new ListRow(8, headerItemTen,
                        arrayObjectAdapterTen);
//                    headerItem.setContentDescription("大闹天宫");

                arrayObjectAdapterTen.addAll(0, dataBean.getWidgets());
                addWithTryCatch(listRowTen);
                break;
            case Constants.TYPE_ELEVEN:
                ArrayObjectAdapter arrayObjectAdapterEleven = new ArrayObjectAdapter(new TypeSixContentPresenter());
                HeaderItem headerItemEleven = new HeaderItem("大闹天宫");
                ListRow listRowEleven = new ListRow(8, headerItemEleven,
                        arrayObjectAdapterEleven);
//                    headerItem.setContentDescription("大闹天宫");

                arrayObjectAdapterEleven.addAll(0, dataBean.getWidgets());
                addWithTryCatch(listRowEleven);
                break;
        }
    }


    private final RecyclerView.OnScrollListener onScrollListener
            = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {
                //当屏幕滚动且用户使用的触碰或手指还在屏幕上，停止加载图片
                case RecyclerView.SCROLL_STATE_DRAGGING:
                    //由于用户的操作，屏幕产生惯性滑动，停止加载图片
                case RecyclerView.SCROLL_STATE_SETTLING:
//                    Glide.with(MainActivity.this).pauseRequests();
                    break;
                case RecyclerView.SCROLL_STATE_IDLE:
//                    Glide.with(MainActivity.this).resumeRequests();
            }
        }
    };

    private final OnChildViewHolderSelectedListener onSelectedListener
            = new OnChildViewHolderSelectedListener() {
        @Override
        public void onChildViewHolderSelected(RecyclerView parent,
                                              RecyclerView.ViewHolder child,
                                              int position, int subposition) {
            super.onChildViewHolderSelected(parent, child, position, subposition);
            Log.e(TAG, "onChildViewHolderSelected: " + parent +"subposition == "+ child  );

            if (mVerticalGridView == null || child == null) {
//                mVerticalGridView.setAlpha(0.1f);
                return;
            }

//            parent.setAlpha(1f);
            child.itemView.setAlpha(1f);
//            child.
//            Log.e(TAG, "onChildViewHolderSelected: " + "　isPressUp:" + mVerticalGridView.isPressUp()
//                    + " isPressDown:" + mVerticalGridView.isPressDown());
//            child.itemView.findViewById(R.id.iv_type_five_poster).setAlpha(1f);
//            Glide.with(MainActivity.this)
//                    .load(((Content.DataBean.WidgetsBean) item).getUrl())
//                    .apply(new RequestOptions()
//                            .centerCrop()
//                            .override(FontDisplayUtil.dip2px(mContext, 420),
//                                    FontDisplayUtil.dip2px(mContext, 246))
//                            .placeholder(R.drawable.bg_shape_default))
//                    .into(vh.mIvTypeSevenPoster);

//            if (mVerticalGridView.isPressUp() && position == 0) {
//                mListener.onFragmentInteraction(Uri.parse(Constants.URI_SHOW_TITLE));
//            } else if (mVerticalGridView.isPressDown() && position == 1) {
//                mListener.onFragmentInteraction(Uri.parse(Constants.URI_HIDE_TITLE));
//            }
        }
    };

    private void addWithTryCatch(Object item) {
        try {
            if (!mVerticalGridView.isComputingLayout()) {
                mAdapter.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        private final Thread mThread = new Thread(new Runnable() {
        @Override
        public void run() {
            String json = null;
            if (mCurrentTabCode == null) {
                mHandler.sendEmptyMessage(MSG_REMOVE_LOADING);
                return;
            }

            switch (mCurrentTabCode) {
                case "c40248cac1f44c278f8bd23a0bba8b4f":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "My.json");
                    break;
                case "7359d189a049468d9d4e280fd1ec15c5":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "WatchTv.json");
                    break;
                case "1b14cb1608d3449c83585b48d47b53c1":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Clear4k.json");
                    break;
                case "5f6874e8106e41a680e05fe49fe4a198":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Children.json");
                    break;
                case "50e4dfe685a84f929ba08952d6081877":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Featured.json");
                    break;
                case "dae28835ebac4f629cc610b4d5a8df25":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Years70.json");
                    break;
                case "5e1958d0cf9341589db884d83aca79e3":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Everything.json");
                    break;
                case "c4a72503d2374b188cf74767f2276220":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "VIP.json");
                    break;
                case "8146c5ff88a245b9af2ce7d2bf301b27":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "TVSeries.json");
                    break;
                case "7412804a6aa24ca9be25fd8cd26f1995":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Movie.json");
                    break;
                case "d179143bacc948d28748338562a94648":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Variety.json");
                    break;
                case "9c58bbdacc1449a4bb84ad6af16ba20d":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Classroom.json");
                    break;
                case "c33db6793aba48bea06b075c35c8be5a":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Anime.json");
                    break;
                case "65504aa451fb4b159bbfeb7161750411":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Basketball.json");
                    break;
                case "a4c28944cb0448579007c6c20c037127":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Physical.json");
                    break;
                case "d971d4585bd14e6fadab1aa2d27b71d6":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Game.json");
                    break;
                case "a868db298ef84dcbb22d919d02f473cb":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Documentary.json");
                    break;
                case "634e89b44aeb4b2a99e9a1bb449daf8b":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Life.json");
                    break;
                case "9a5fd09ddfa64c4b95b3dc02b27c7576":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "OrientalTheatre.json");
                    break;
                case "695ed6a510934a93a9593b034a99fc01":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Car.json");
                    break;
                case "b9c9229ef6534682919d7af67438e4d6":
                    json = LocalJsonResolutionUtil.getJson(MainActivity.this, "Funny.json");
                    break;
            }
            if (json == null) {
                return;
            }
            Content content = LocalJsonResolutionUtil.JsonToObject(json, Content.class);
            final Message msg = Message.obtain();
            msg.what = MSG_ADD_ITEM;
            Bundle b = new Bundle();
            b.putParcelable(MSG_BUNDLE_KEY_ADD_ITEM, content);
            msg.setData(b);
            //延迟1秒模拟加载数据过程
            mHandler.sendMessageDelayed(msg, 1000);

        }
    });

    public void setBackground(Drawable resource){
        if(resource != null) {
            mBackgroundManager.setDrawable(resource);
        }else{
            mBackgroundManager.setThemeDrawableResourceId(R.drawable.bg_gradient_home);
        }
    }
}


