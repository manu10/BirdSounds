package com.example.manuel.birdsounds;

import android.support.annotation.RawRes;

import com.example.manuel.multidex.mulbirdsounds.R;

/**
 * Created by manuel on 30/04/16.
 */
public class Ave {
    String aveName;
    int image; // drawable reference id
    enum avesEnum {bigua,cauquen,choiquePatagonico,condor,flamengo,garcitaBlanca,macaComun,martineta,ostreroAustral}
    static String[] avesStr = {"Bigua",
            "Cauquen",
            "Choique Patagonico",
            "Condor",
            "Flamengo",
            "Garcita Blanca",
            "Maca Comun",
            "Martineta",
            "Ostrero Austral"
    };
    static int[] avesSounds= {R.raw.bigua, R.raw.cauquen,R.raw.cauquen,R.raw.condor_short,R.raw.cauquen,R.raw.cauquen,R.raw.cauquen,R.raw.cauquen,R.raw.cauquen};
    static int[] avesImg= {R.drawable.bigua_main,R.drawable.cauquen_main,R.drawable.cauquen_main,R.drawable.condor3,R.drawable.cauquen_main,R.drawable.cauquen_main,R.drawable.cauquen_main,R.drawable.cauquen_main,R.drawable.cauquen_main};
    public Ave(String aName, int image)
    {
        this.aveName = aName;

        this.image = image;
    }



}
