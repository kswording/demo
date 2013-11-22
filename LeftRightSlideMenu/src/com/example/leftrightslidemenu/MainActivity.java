package com.example.leftrightslidemenu;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends FragmentActivity {
	private SlideView mContent;
	private LinearLayout mLeft_layout;
	private LinearLayout mRight_layout;
	private ListView mLeftList;
	private ListView mRightList;
	private ListView mMainList;
	private int MaxRightWidth,MaxLeftWidth;
	private DisplayMetrics mDm = new DisplayMetrics();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}
	
	protected void initView() {
		mContent = (SlideView) findViewById(R.id.main_content);
		mLeftList = (ListView) findViewById(R.id.title_list);
		mRightList = (ListView) findViewById(R.id.infor_list);
		mLeft_layout = (LinearLayout) findViewById(R.id.left_layout);
		mRight_layout = (LinearLayout) findViewById(R.id.right_layout);
		mMainList = (ListView) findViewById(R.id.main_list);
		initLayout();
		testData();
		mContent.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			
			@Override
			public boolean onPreDraw() {
				MaxRightWidth = mRight_layout.getWidth();
				MaxLeftWidth = mLeft_layout.getWidth();
				mContent.getViewTreeObserver().removeOnPreDrawListener(this);
				mContent.setMaxWidth(MaxLeftWidth, MaxRightWidth);
				return true;
			}
		});
		Fragment fragment = new OutlineFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.info_content, fragment).commit();
	}
	
	public void initLayout() {
		getWindowManager().getDefaultDisplay().getMetrics(mDm);
		LayoutParams leftparam = (LayoutParams) mLeft_layout.getLayoutParams();
		LayoutParams rightparam = (LayoutParams) mRight_layout.getLayoutParams();
		leftparam.width = mDm.widthPixels/2;
		leftparam.rightMargin = mDm.widthPixels/2;
		rightparam.width = mDm.widthPixels/2;
		rightparam.leftMargin = mDm.widthPixels/2;
		mLeft_layout.setLayoutParams(leftparam);
		mRight_layout.setLayoutParams(rightparam);
	}
	
	public void testData() {
		String[] leftdata = new String[]{"选择主题","招商银行","工商银行","广发银行"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, leftdata);
		mLeftList.setAdapter(adapter);
		mRightList.setAdapter(adapter);
		//mMainList.setAdapter(adapter);
	}
}
