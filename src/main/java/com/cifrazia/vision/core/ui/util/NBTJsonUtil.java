package com.cifrazia.vision.core.ui.util;

import net.minecraft.nbt.*;

public class NBTJsonUtil {

    public static NBTTagCompound Convert(String json) throws JsonException {
        json = json.trim();
        final JsonFile file = new JsonFile(json);
        if (!json.startsWith("{") || !json.endsWith("}")) {
            throw new JsonException("Not properly incapsulated between { }", file);
        }
        final NBTTagCompound compound = new NBTTagCompound();
        FillCompound(compound, file);
        return compound;
    }

    public static void FillCompound(final NBTTagCompound compound, final JsonFile json) throws JsonException {
        if (json.startsWith("{") || json.startsWith(",")) {
            json.cut(1);
        }
        if (json.startsWith("}")) {
            return;
        }
        final int index = json.keyIndex();
        if (index < 1) {
            throw new JsonException("Expected key after ,", json);
        }
        String key = json.substring(0, index);
        json.cut(index + 1);
        NBTBase base = readValue(json);
        if (base == null) {
            base = new NBTTagString();
        }
        if (key.startsWith("\"")) {
            key = key.substring(1);
        }
        if (key.endsWith("\"")) {
            key = key.substring(0, key.length() - 1);
        }
        compound.setTag(key, base);
        if (json.startsWith(",")) {
            FillCompound(compound, json);
        }
    }

    public static NBTBase readValue(final JsonFile json) throws JsonException {
        if (json.startsWith("{")) {
            final NBTTagCompound compound = new NBTTagCompound();
            FillCompound(compound, json);
            if (!json.startsWith("}")) {
                throw new JsonException("Expected }", json);
            }
            json.cut(1);
            return compound;
        } else if (json.startsWith("[")) {
            json.cut(1);
            final NBTTagList list = new NBTTagList();
            if (json.startsWith("B;") || json.startsWith("I;") || json.startsWith("L;")) {
                json.cut(2);
            }
            for (NBTBase value = readValue(json); value != null; value = readValue(json)) {
                list.appendTag(value);
                if (!json.startsWith(",")) {
                    break;
                }
                json.cut(1);
            }
            if (!json.startsWith("]")) {
                throw new JsonException("Expected ]", json);
            }
            json.cut(1);
            if (list.getTagType() == 3) {
                final int[] arr = new int[list.tagCount()];
                int i = 0;
                while (list.tagCount() > 0) {
                    arr[i] = ((NBTTagInt) list.removeTag(0)).getInt();
                    ++i;
                }
                return new NBTTagIntArray(arr);
            }
            if (list.getTagType() == 1) {
                final byte[] arr2 = new byte[list.tagCount()];
                int i = 0;
                while (list.tagCount() > 0) {
                    arr2[i] = ((NBTTagByte) list.removeTag(0)).getByte();
                    ++i;
                }
                return new NBTTagByteArray(arr2);
            }
            if (list.getTagType() == 4) {
                final long[] arr3 = new long[list.tagCount()];
                int i = 0;
                while (list.tagCount() > 0) {
                    arr3[i] = ((NBTTagLong) list.removeTag(0)).getByte();
                    ++i;
                }
                return new NBTTagLongArray(arr3);
            }
            return list;
        } else {
            if (json.startsWith("\"")) {
                String s = "";
                String cut = json.cut(1);
                for (boolean ignore = false; !json.startsWith("\"") || ignore; ignore = cut.equals("\\"), s += cut) {
                    cut = json.cutDirty(1);
                }
                json.cut(1);
                return new NBTTagString(s.replace("\\\\", "\\").replace("\\\"", "\""));
            }
            StringBuilder sBuilder = new StringBuilder();
            while (!json.startsWith(",", "]", "}")) {
                sBuilder.append(json.cut(1));
            }
            String s = sBuilder.toString();
            s = s.trim().toLowerCase();
            if (s.isEmpty()) {
                return null;
            }
            try {
                if (s.endsWith("d")) {
                    return new NBTTagDouble(Double.parseDouble(s.substring(0, s.length() - 1)));
                }
                if (s.endsWith("f")) {
                    return new NBTTagFloat(Float.parseFloat(s.substring(0, s.length() - 1)));
                }
                if (s.endsWith("b")) {
                    return new NBTTagByte(Byte.parseByte(s.substring(0, s.length() - 1)));
                }
                if (s.endsWith("s")) {
                    return new NBTTagShort(Short.parseShort(s.substring(0, s.length() - 1)));
                }
                if (s.endsWith("l")) {
                    return new NBTTagLong(Long.parseLong(s.substring(0, s.length() - 1)));
                }
                if (s.contains(".")) {
                    return new NBTTagDouble(Double.parseDouble(s));
                }
                return new NBTTagInt(Integer.parseInt(s));
            } catch (NumberFormatException ex) {
                throw new JsonException("Unable to convert: " + s + " to a number", json);
            }
        }
    }

    static class JsonFile {
        private final String original;
        private String text;

        public JsonFile(final String text) {
            this.text = text;
            this.original = text;
        }

        public int keyIndex() {
            boolean hasQuote = false;
            for (int i = 0; i < this.text.length(); ++i) {
                final char c = this.text.charAt(i);
                if (i == 0 && c == '\"') {
                    hasQuote = true;
                } else if (hasQuote && c == '\"') {
                    hasQuote = false;
                }
                if (!hasQuote && c == ':') {
                    return i;
                }
            }
            return -1;
        }

        public String cutDirty(final int i) {
            final String s = this.text.substring(0, i);
            this.text = this.text.substring(i);
            return s;
        }

        public String cut(final int i) {
            final String s = this.text.substring(0, i);
            this.text = this.text.substring(i).trim();
            return s;
        }

        public String substring(final int beginIndex, final int endIndex) {
            return this.text.substring(beginIndex, endIndex);
        }

        public int indexOf(final String s) {
            return this.text.indexOf(s);
        }

        public String getCurrentPos() {
            final int lengthOr = this.original.length();
            final int lengthCur = this.text.length();
            final int currentPos = lengthOr - lengthCur;
            final String done = this.original.substring(0, currentPos);
            final String[] lines = done.split("\r\n|\r|\n");
            int pos = 0;
            String line = "";
            if (lines.length > 0) {
                pos = lines[lines.length - 1].length();
                line = this.original.split("\r\n|\r|\n")[lines.length - 1].trim();
            }
            return "Line: " + lines.length + ", Pos: " + pos + ", Text: " + line;
        }

        public boolean startsWith(final String... ss) {
            for (final String s : ss) {
                if (this.text.startsWith(s)) {
                    return true;
                }
            }
            return false;
        }

        public boolean endsWith(final String s) {
            return this.text.endsWith(s);
        }
    }

    public static class JsonException extends Exception {
        public JsonException(final String message, final JsonFile json) {
            super(message + ": " + json.getCurrentPos());
        }
    }
}

