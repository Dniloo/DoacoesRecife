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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doacoes);
        ButterKnife.bind(this);

        setSupportActionBar(mToolBar);

        mViewPager.setAdapter(new DoacaoPager(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
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
