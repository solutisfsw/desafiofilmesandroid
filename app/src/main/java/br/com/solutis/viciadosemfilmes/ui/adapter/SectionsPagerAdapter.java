package br.com.solutis.viciadosemfilmes.ui.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import br.com.solutis.viciadosemfilmes.R;
import br.com.solutis.viciadosemfilmes.model.Filme;
import br.com.solutis.viciadosemfilmes.ui.fragment.PopularesFragment;
import br.com.solutis.viciadosemfilmes.ui.fragment.RecentesFragment;
import br.com.solutis.viciadosemfilmes.ui.fragment.TopRated;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context context;
    private final List<Filme> listaFilmesRecentes;
    private final List<Filme> listaFilmesPopulares;
    private final List<Filme> listaFilmesTopRated;

    public SectionsPagerAdapter(Context context, FragmentManager fm, List<Filme> listaFilmesRecentes,
                                List<Filme> listaFilmesPopulares, List<Filme> listaFilmesTopRated) {
        super(fm);
        this.context = context;
        this.listaFilmesRecentes = listaFilmesRecentes;
        this.listaFilmesPopulares = listaFilmesPopulares;
        this.listaFilmesTopRated = listaFilmesTopRated;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0: fragment = new RecentesFragment(context, listaFilmesRecentes); break;
            case 1: fragment = new PopularesFragment(context, listaFilmesPopulares); break;
            case 2: fragment = new TopRated(context, listaFilmesTopRated); break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }

}