package com.example.doarecife.doacoesrecife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

import com.example.doarecife.doacoesrecife.models.Itemdoacao;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoacoesActivity extends AppCompatActivity
        implements CliqueiNoItemListener{
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    LoginButton mLoginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_doacoes);
        ButterKnife.bind(this);

        iniciaLogin();
        loginFB();

        setSupportActionBar(mToolBar);

        mViewPager.setAdapter(new DoacaoPager(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void iniciaLogin() {
        callbackManager = CallbackManager.Factory.create();
        mLoginButton = (LoginButton) findViewById(R.id.login_button);
    }

    private void loginFB() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    class DoacaoPager extends FragmentPagerAdapter{

        public DoacaoPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        if (position == 0) return new ListaDoacoesFragment();
            return new ListaSalvosFragment();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0) return getString(R.string.aba_locais);
            return getString(R.string.aba_favoritos);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public void itemFoiClicado(Itemdoacao itemdoacao) {
       if (getResources().getBoolean(R.bool.tablet)) {
           DetalheDoacaoFragment ddf = DetalheDoacaoFragment.newInstance(itemdoacao);
           getSupportFragmentManager()
                   .beginTransaction()
                   .replace(R.id.detalhe, ddf, "detalhe")
                    .commit();
       }else {
           Intent it = new Intent(this, DetalheDoacaoActivity.class);
           Parcelable p = Parcels.wrap(itemdoacao);
           it.putExtra(DetalheDoacaoActivity.EXTRA_DOACAO, p);
           startActivity(it);
       }
    }
}
