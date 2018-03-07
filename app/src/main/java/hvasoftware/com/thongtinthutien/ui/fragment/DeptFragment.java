package hvasoftware.com.thongtinthutien.ui.fragment;

import hvasoftware.com.thongtinthutien.MainActivity;
import hvasoftware.com.thongtinthutien.R;
import hvasoftware.com.thongtinthutien.base.BaseFragment;

/**
 * Created by Thanh on 03/07/2018.
 */

public class DeptFragment extends BaseFragment {
    @Override
    protected void OnViewCreated() {
        GetMainAcitivity().setScreenOrientation(false);
    }

    @Override
    protected void OnBindView() {

    }

    @Override
    public int GetLayoutId() {
        return R.layout.dept_fragment;
    }

    @Override
    protected String GetScreenTitle() {
        return getResources().getString(R.string.app_name);
    }
}
