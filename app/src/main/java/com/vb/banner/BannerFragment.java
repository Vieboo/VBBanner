package com.vb.banner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Vieboo on 2016/6/29.
 */
public class BannerFragment extends Fragment implements OnItemClickListener, ViewPager.OnPageChangeListener {

    private ConvenientBanner convenient_banner;//顶部广告栏控件
    private List<String> networkImages;
    private String[] images = {"http://overwatch.nos.netease.com/2/media/screenshots/Dva01.jpg",
            "http://overwatch.nos.netease.com/2/media/screenshots/reaper-screenshot-005.1GcKT.jpg",
            "http://overwatch.nos.netease.com/2/media/screenshots/symmetra-screenshot-001.0OuV8.jpg",
            "http://overwatch.nos.netease.com/2/media/screenshots/tracer-screenshot-002.00UEN.jpg",
            "http://overwatch.nos.netease.com/2/media/screenshots/widowmaker-screenshot-004.069Eh.jpg",
            "http://overwatch.nos.netease.com/2/media/screenshots/zenyatta-screenshot-001.3n2TN.jpg",
            "http://overwatch.nos.netease.com/2/media/screenshots/Genji03.jpg",
            "http://overwatch.nos.netease.com/2/media/screenshots/mercy-screenshot-001.37iw4.jpg"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_banner, container, false);
        convenient_banner = (ConvenientBanner) contentView.findViewById(R.id.convenient_banner);

        initImageLoader();
        initBanner();

        return contentView;
    }

    private void initBanner() {
        //网络加载例子
        networkImages= Arrays.asList(images);
        convenient_banner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },networkImages);
        //设置点击事件
        convenient_banner.setOnItemClickListener(this);
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        convenient_banner.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
//        convenient_banner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);      //设置指示器的方向
//        convenient_banner.setOnPageChangeListener(this);      //监听翻页事件
//        convenient_banner.setCanLoop(false);        //控制是否循环(默认true)
//        convenient_banner.setManualPageable(false);    //设置不能手动影响
    }

    //初始化网络图片缓存库
    private void initImageLoader(){
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.mipmap.ic_default_adimage)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getActivity().getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onStart() {
        super.onStart();
        convenient_banner.startTurning(3000);
    }

    @Override
    public void onStop() {
        super.onStop();
        convenient_banner.stopTurning();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(), "Fragment点击了第"+position+"个", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
