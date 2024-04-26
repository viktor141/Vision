package com.cifrazia.vision.core.ui.util;

public class Color {
    public static final Color WHITE_100 = new Color(255, 255, 255, 255);
    public static final Color WHITE_20 = new Color(255, 255, 255, 51);
    public static final Color HALF = new Color(0.5f, 0.5f, 0.5f, 0.5f);
    public static final Color NINE_PRESENT = new Color(0, 0, 0, 204);
    public static final Color EIGHT_PRESENT = new Color(0, 0, 0, 204);
    public static final Color SIX_PRESENT = new Color(0, 0, 0, 153);
    public static final Color GRAY = new Color(92, 92, 92, 255);
    public static final Color GREEN = new Color(177, 229, 59, 255);
    public static final Color EMPTY = new Color(0, 0, 0, 0);


    private final float rf;
    private final float gf;
    private final float bf;
    private final float af;
    private final int r;
    private final int g;
    private final int b;
    private final int a;
    private final int fullColor;

    public Color(float rf, float gf, float bf, float af) {
        this(rf, gf, bf, af, (int) (rf * 255.0F), (int) (gf * 255.0F), (int) (bf * 255.0F), (int) (af * 255.0F));
    }

    public Color(int r, int g, int b, int a) {
        this(r / 255.0F, g / 255.0F, b / 255.0F, a / 255.0F, r, g, b, a);
    }

    private Color(float rf, float gf, float bf, float af, int r, int g, int b, int a) {
        this.rf = rf;
        this.gf = gf;
        this.bf = bf;
        this.af = af;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;

        fullColor = ((a & 0xFF) << 24) + ((r & 0xFF) << 16) + ((g & 0xFF) << 8) + (b & 0xFF);
    }

    public float getRf() {
        return rf;
    }

    public float getGf() {
        return gf;
    }

    public float getBf() {
        return bf;
    }

    public float getAf() {
        return af;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public int getA() {
        return a;
    }

    public int getFullColor() {
        return fullColor;
    }
}
