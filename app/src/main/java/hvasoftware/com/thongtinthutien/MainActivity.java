package hvasoftware.com.thongtinthutien;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import hvasoftware.com.thongtinthutien.base.BaseActivity;
import hvasoftware.com.thongtinthutien.base.BaseFragment;
import hvasoftware.com.thongtinthutien.ui.fragment.LoginFragment;
import hvasoftware.com.thongtinthutien.utils.FragmentHelper;

import static hvasoftware.com.thongtinthutien.utils.Utils.hideSoftKeyboard;

public class MainActivity extends BaseActivity {
    View imvBack;
    TextView tvTitle;
    android.support.v7.widget.Toolbar MainToolbar;
    boolean isMenuVisible;

    @Override
    protected String GetScreenTitle() {
        return super.GetScreenTitle();
    }

    @Override
    protected void OnViewCreated() {
    }

    @Override
    protected void OnBindView() {
        ChangeStatusBar();
        MainToolbar = findViewById(R.id.mainToolbar);
        imvBack = findViewById(R.id.imvBack);
        tvTitle = findViewById(R.id.tvTitle);
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSupportActionBar(MainToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        setupUI(findViewById(R.id.root));
        SwitchFragment(new LoginFragment(), false);
    }

    @Override
    protected int GetLayoutId() {
        return R.layout.activity_main;
    }

    public void ChangeStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
    }

    @Override
    public void SwitchFragment(Fragment fragment, boolean IsAddToBackStack) {
        super.SwitchFragment(fragment, IsAddToBackStack);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void SetScreenTitle(String title) {
        if (title == null) {
            return;
        }
        tvTitle.setText(title);
    }

    @Override
    public void onBackPressed() {
        BaseFragment currentFragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.root);
        if (currentFragment == null || !currentFragment.OnBackPress()) {
            super.onBackPressed();
            FragmentHelper.RemoveLastFragment(getSupportFragmentManager());
        }
    }

    public void setMenuVisible(boolean visible) {
        isMenuVisible = visible;
        invalidateOptionsMenu();
    }

    public void setHeaderVisible(boolean isVisible) {
        if (isVisible) {
            MainToolbar.setVisibility(View.VISIBLE);
        } else {
            MainToolbar.setVisibility(View.GONE);
        }
    }

    public void setBackButtonVisible(boolean isVisible) {
        if (isVisible) {
            imvBack.setVisibility(View.VISIBLE);
        } else {
            imvBack.setVisibility(View.GONE);
        }
    }

    public void setScreenOrientation(boolean isPotraitMode) {
        setRequestedOrientation(isPotraitMode ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(MainActivity.this);
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }


}
