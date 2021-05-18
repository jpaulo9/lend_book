package com.example.lendbook;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Opt_Fragment extends FragmentStatePagerAdapter {

    int Countab;
    @SuppressWarnings("deprecation")
    public Opt_Fragment(FragmentManager fm, int Contab) {
        super(fm);
        this.Countab = Contab;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position){

            case 0:
                frag = new Fragment_Mlivros();
                break;
            case 1:
                frag = new Fragment_Colecao();
                break;
            case 2:
                frag = new Fragment_AddLivro();
                break;
            case 3:
                frag = new Fragment_Mdados();
                break;
            case 4:
                frag = new Fragment_Solicitacao();
                break;

        }

        return frag;
    }

    @Override
    public int getCount() {
        return Countab;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
