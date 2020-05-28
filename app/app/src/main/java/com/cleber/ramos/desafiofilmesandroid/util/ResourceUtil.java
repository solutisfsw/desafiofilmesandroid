package com.cleber.ramos.desafiofilmesandroid.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourceUtil {

    private static final String DRAWABLE = "drawable";

    public static Drawable devolveDrawable(Context context, String drawableEmTexto) {
        Resources resources = context.getResources();
        int idDoDrawable = resources.getIdentifier(drawableEmTexto,
                DRAWABLE,
                context.getPackageName());
        return resources.getDrawable(idDoDrawable);
    }

    public static Drawable devolveDrawable(Context context, Integer idDoDrawable) {
        Resources resources = context.getResources();
        return resources.getDrawable(idDoDrawable);
    }

}
