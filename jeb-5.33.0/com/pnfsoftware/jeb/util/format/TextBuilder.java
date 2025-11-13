package com.pnfsoftware.jeb.util.format;

public class TextBuilder implements Appendable {
   private StringBuilder sb;
   private int indentationLevel;
   private int indentationSize;

   public TextBuilder() {
      this(4);
   }

   public TextBuilder(int var1) {
      this(var1, 16);
   }

   public TextBuilder(int var1, int var2) {
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else {
         this.indentationSize = var1;
         this.sb = new StringBuilder(var2);
      }
   }

   public int length() {
      return this.sb.length();
   }

   public void clear() {
      this.sb.setLength(0);
   }

   public void updateIndentation(boolean var1) {
      if (var1) {
         this.indent();
      } else {
         this.unindent();
      }
   }

   public TextBuilder indent(boolean var1) {
      this.indentationLevel++;
      if (var1) {
         this.eol();
      }

      return this;
   }

   public TextBuilder indent() {
      return this.indent(false);
   }

   public TextBuilder unindent() {
      if (this.indentationLevel == 0) {
         throw new IllegalStateException("Negative indentation level");
      } else {
         this.indentationLevel--;
         return this;
      }
   }

   public TextBuilder append(CharSequence var1) {
      return this.append((Object)var1);
   }

   public TextBuilder append(CharSequence var1, int var2, int var3) {
      return this.append(var1.subSequence(var2, var3));
   }

   public TextBuilder append(char var1) {
      return this.appendInternal(false, false, Character.toString(var1));
   }

   public TextBuilder append(byte var1) {
      return this.appendInternal(false, false, Byte.toString(var1));
   }

   public TextBuilder append(short var1) {
      return this.appendInternal(false, false, Short.toString(var1));
   }

   public TextBuilder append(int var1) {
      return this.appendInternal(false, false, Integer.toString(var1));
   }

   public TextBuilder append(long var1) {
      return this.appendInternal(false, false, Long.toString(var1));
   }

   public TextBuilder append(float var1) {
      return this.appendInternal(false, false, Float.toString(var1));
   }

   public TextBuilder append(double var1) {
      return this.appendInternal(false, false, Double.toString(var1));
   }

   public TextBuilder append(Object var1) {
      return this.appendInternal(false, false, var1 == null ? "null" : var1.toString());
   }

   public TextBuilder append(String var1, Object... var2) {
      return this.appendInternal(false, false, var1, var2);
   }

   public TextBuilder appendLine(String var1, Object... var2) {
      return this.appendInternal(false, true, var1, var2);
   }

   public TextBuilder appendLine(boolean var1) {
      return this.appendInternal(var1, true, "");
   }

   public TextBuilder appendLine() {
      return this.appendLine(false);
   }

   public TextBuilder eol() {
      return this.appendLine();
   }

   public TextBuilder space() {
      return this.append(' ');
   }

   private TextBuilder appendInternal(boolean var1, boolean var2, String var3, Object... var4) {
      if (!var1 && (this.sb.length() == 0 || this.sb.charAt(this.sb.length() - 1) == '\n')) {
         this.sb.append(Strings.generate(' ', this.indentationSize * this.indentationLevel));
      }

      this.sb.append(var4.length == 0 ? var3 : Strings.ff((Appendable)null, var3, var4));
      if (var2) {
         this.sb.append(Strings.LINESEP);
      }

      return this;
   }

   public void removeChar(int var1) {
      this.sb.deleteCharAt(var1);
   }

   public void removeChars(int var1, int var2) {
      this.sb.delete(var1, var2);
   }

   public void removeLastChar() {
      this.sb.deleteCharAt(this.sb.length() - 1);
   }

   public void removeLastLine() {
      int var1 = this.sb.lastIndexOf(Strings.LINESEP);
      if (var1 == -1) {
         var1 = 0;
      }

      this.sb.setLength(var1);
   }

   String formatLastLine() {
      int var1 = this.sb.lastIndexOf(Strings.LINESEP);
      return var1 == -1 ? this.sb.toString() : this.sb.substring(var1 + Strings.LINESEP.length());
   }

   @Override
   public String toString() {
      return this.sb.toString();
   }
}
