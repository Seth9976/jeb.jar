package com.pnfsoftware.jeb.util.base;

import java.util.ArrayList;
import java.util.List;

public class CharSequenceList implements CharSequence {
   private List elts;
   private List linePosition;

   public CharSequenceList(List var1) {
      this.elts = var1;
      this.linePosition = new ArrayList();
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         this.linePosition.add(var2);
         var2 += ((CharSequence)var1.get(var3)).length() + 1;
      }
   }

   @Override
   public int length() {
      int var1 = (Integer)this.linePosition.get(this.linePosition.size() - 1);
      return var1 + ((CharSequence)this.elts.get(this.elts.size() - 1)).length() + 1;
   }

   @Override
   public char charAt(int var1) {
      if (var1 >= 0 && var1 <= this.length()) {
         int var2 = var1;

         for (int var3 = 0; var3 < this.elts.size(); var3++) {
            if (var2 < ((CharSequence)this.elts.get(var3)).length()) {
               return ((CharSequence)this.elts.get(var3)).charAt(var2);
            }

            if (var2 == ((CharSequence)this.elts.get(var3)).length()) {
               return '\n';
            }

            var2 -= ((CharSequence)this.elts.get(var3)).length() + 1;
         }

         throw new StringIndexOutOfBoundsException(var1);
      } else {
         throw new StringIndexOutOfBoundsException(var1);
      }
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      if (var1 < 0) {
         throw new StringIndexOutOfBoundsException(var1);
      } else if (var2 > this.length()) {
         throw new StringIndexOutOfBoundsException(var2);
      } else if (var1 > var2) {
         throw new StringIndexOutOfBoundsException(var2 - var1);
      } else {
         int var3 = var1;
         int var4 = var2;
         StringBuilder var5 = new StringBuilder();

         for (int var6 = 0; var6 < this.elts.size() && var4 >= 0; var6++) {
            if (var3 < 0) {
               if (var4 <= ((CharSequence)this.elts.get(var6)).length()) {
                  var5.append(((CharSequence)this.elts.get(var6)).subSequence(0, var4));
                  break;
               }

               var5.append(((CharSequence)this.elts.get(var6)).subSequence(0, ((CharSequence)this.elts.get(var6)).length())).append('\n');
            } else if (var3 < ((CharSequence)this.elts.get(var6)).length()) {
               if (var4 <= ((CharSequence)this.elts.get(var6)).length()) {
                  var5.append(((CharSequence)this.elts.get(var6)).subSequence(var3, var4));
                  break;
               }

               var5.append(((CharSequence)this.elts.get(var6)).subSequence(var3, ((CharSequence)this.elts.get(var6)).length())).append('\n');
            } else if (var3 == ((CharSequence)this.elts.get(var6)).length()) {
               var5.append('\n');
            }

            var3 -= ((CharSequence)this.elts.get(var6)).length() + 1;
            var4 -= ((CharSequence)this.elts.get(var6)).length() + 1;
         }

         return var5;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (CharSequence var3 : this.elts) {
         var1.append(var3).append('\n');
      }

      return var1.toString();
   }

   public String toString(int var1, int var2) {
      StringBuilder var3 = new StringBuilder();

      for (CharSequence var5 : this.elts) {
         var3.append(getLine(var5, var1, var2)).append('\n');
      }

      return var3.toString();
   }

   public static CharSequence getLine(CharSequence var0, int var1, int var2) {
      return (CharSequence)(var0.length() > var1
         ? new StringBuilder(var0.subSequence(0, var1 - var2 - 5)).append("[...]").append(var0.subSequence(var0.length() - var2, var0.length()))
         : var0);
   }
}
