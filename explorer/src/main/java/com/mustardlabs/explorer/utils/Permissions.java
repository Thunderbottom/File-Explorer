package com.dnielfe.manager.utils;

import java.io.File;
import java.io.Serializable;

public final class Permissions implements Serializable {

    private static final long serialVersionUID = 2682238088276963741L;

    public final boolean ur;
    public final boolean uw;
    public final boolean ux;

    public final boolean gr;
    public final boolean gw;
    public final boolean gx;

    public final boolean or;
    public final boolean ow;
    public final boolean ox;

    public Permissions(String line) {
        if (line.length() != 10) {
            throw new IllegalArgumentException("Bad permission line");
        }

        this.ur = line.charAt(1) == 'r';
        this.uw = line.charAt(2) == 'w';
        this.ux = line.charAt(3) == 'x';

        this.gr = line.charAt(4) == 'r';
        this.gw = line.charAt(5) == 'w';
        this.gx = line.charAt(6) == 'x';

        this.or = line.charAt(7) == 'r';
        this.ow = line.charAt(8) == 'w';
        this.ox = line.charAt(9) == 'x';
    }

    public Permissions(boolean ur, boolean uw, boolean ux, boolean gr, boolean gw,
                       boolean gx, boolean or, boolean ow, boolean ox) {
        this.ur = ur;
        this.uw = uw;
        this.ux = ux;

        this.gr = gr;
        this.gw = gw;
        this.gx = gx;

        this.or = or;
        this.ow = ow;
        this.ox = ox;
    }

    public static String toOctalPermission(final Permissions p) {
        byte user = 0;
        byte group = 0;
        byte other = 0;

        if (p.ur) {
            user += 4;
        }
        if (p.uw) {
            user += 2;
        }
        if (p.ux) {
            user += 1;
        }

        if (p.gr) {
            group += 4;
        }
        if (p.gw) {
            group += 2;
        }
        if (p.gx) {
            group += 1;
        }

        if (p.or) {
            other += 4;
        }
        if (p.ow) {
            other += 2;
        }
        if (p.ox) {
            other += 1;
        }

        return String.valueOf(user) + group + other;
    }

    // use this as alternative if no root is available
    public static String getBasicPermission(File file) {
        String per = "";

        per += file.isDirectory() ? "d" : "-";
        per += file.canRead() ? "r" : "-";
        per += file.canWrite() ? "w" : "-";
        per += file.canExecute() ? "x" : "-";

        return per;
    }
}
