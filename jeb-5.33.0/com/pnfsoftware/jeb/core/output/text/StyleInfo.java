package com.pnfsoftware.jeb.core.output.text;

import com.pnfsoftware.jeb.util.format.Strings;

public class StyleInfo {
   public static final StyleInfo NO_STYLE = new StyleInfo();
   private Integer fgcolor;
   private Integer bgcolor;
   private Boolean bold;
   private Boolean italic;

   private StyleInfo() {
   }

   public StyleInfo(Integer var1, Integer var2, Boolean var3, Boolean var4) {
      this.fgcolor = var1;
      this.bgcolor = var2;
      this.bold = var3;
      this.italic = var4;
   }

   public Integer getForegroundColor() {
      return this.fgcolor;
   }

   public Integer getBackgroundColor() {
      return this.bgcolor;
   }

   public Boolean getBold() {
      return this.bold;
   }

   public Boolean getItalic() {
      return this.italic;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.bgcolor == null ? 0 : this.bgcolor.hashCode());
      var1 = 31 * var1 + (this.bold == null ? 0 : this.bold.hashCode());
      var1 = 31 * var1 + (this.fgcolor == null ? 0 : this.fgcolor.hashCode());
      return 31 * var1 + (this.italic == null ? 0 : this.italic.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         StyleInfo var2 = (StyleInfo)var1;
         if (this.bgcolor == null) {
            if (var2.bgcolor != null) {
               return false;
            }
         } else if (!this.bgcolor.equals(var2.bgcolor)) {
            return false;
         }

         if (this.bold == null) {
            if (var2.bold != null) {
               return false;
            }
         } else if (!this.bold.equals(var2.bold)) {
            return false;
         }

         if (this.fgcolor == null) {
            if (var2.fgcolor != null) {
               return false;
            }
         } else if (!this.fgcolor.equals(var2.fgcolor)) {
            return false;
         }

         if (this.italic == null) {
            if (var2.italic != null) {
               return false;
            }
         } else if (!this.italic.equals(var2.italic)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("fg=%s,bg=%s,b=%s,i=%s", this.getForegroundColor(), this.getBackgroundColor(), this.getBold(), this.getItalic());
   }
}
