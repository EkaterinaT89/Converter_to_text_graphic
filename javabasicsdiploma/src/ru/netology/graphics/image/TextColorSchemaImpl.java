package ru.netology.graphics.image;

public class TextColorSchemaImpl implements TextColorSchema {

    @Override
    public char convert(int color) {
        final char[] simbolsForPrint = {'#', '$', '@', '%', '*', '+', '-', '\''};
        return simbolsForPrint[color/32];
    }

}

