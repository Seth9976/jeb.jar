package com.pnfsoftware.jeb.util.format;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Comparator;

public class AlphanumCharComparator implements Comparator {
   boolean caseSensitive;

   public AlphanumCharComparator() {
      this(true);
   }

   public AlphanumCharComparator(boolean var1) {
      this.caseSensitive = var1;
   }

   public int compare(Character var1, Character var2) {
      if (var1 == null) {
         return var2 == null ? 0 : -1;
      } else if (var2 == null) {
         return 1;
      } else {
         char var3 = var1;
         char var4 = var2;
         if (!this.caseSensitive) {
            var3 = Character.toLowerCase(var3);
            var4 = Character.toLowerCase(var4);
         }

         if (var3 == var4) {
            return 0;
         } else if (Character.isAlphabetic(var3)) {
            if (Character.isAlphabetic(var4)) {
               if (var3 >= 'A' && var3 <= 'z' && var4 >= 'A' && var4 <= 'z') {
                  return Character.compare(var3, var4);
               } else {
                  char var5 = Normalizer.normalize(String.valueOf(var3), Form.NFD).charAt(0);
                  char var6 = Normalizer.normalize(String.valueOf(var4), Form.NFD).charAt(0);
                  return var5 != var6 ? Character.compare(var5, var6) : Character.compare(var3, var4);
               }
            } else {
               return 1;
            }
         } else if (Character.isAlphabetic(var4)) {
            return -1;
         } else if (Character.isDigit(var3) && !Character.isDigit(var4)) {
            return 1;
         } else {
            return Character.isDigit(var4) && !Character.isDigit(var3) ? -1 : Character.compare(var3, var4);
         }
      }
   }
}
