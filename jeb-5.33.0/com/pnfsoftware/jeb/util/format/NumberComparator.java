package com.pnfsoftware.jeb.util.format;

import java.util.Comparator;

public class NumberComparator implements Comparator {
   private final Comparator charComparator;
   private final boolean scanHexadecimal;

   public NumberComparator() {
      this.charComparator = null;
      this.scanHexadecimal = true;
   }

   public NumberComparator(Comparator var1, boolean var2) {
      this.charComparator = var1;
      this.scanHexadecimal = var2;
   }

   private final boolean isDigit(char var1) {
      return var1 >= '0' && var1 <= '9';
   }

   private final boolean isHexaLetter(char var1) {
      return var1 >= 'A' && var1 <= 'F' || var1 >= 'a' && var1 <= 'f';
   }

   private final NumberComparator.Token getChunk(String var1, int var2, int var3, boolean var4) {
      StringBuilder var5 = new StringBuilder();
      char var7 = var1.charAt(var3);
      boolean var8 = false;
      var5.append(var7);
      int var6 = var3 + 1;
      if (this.isDigit(var7) || var4 && this.isHexaLetter(var7)) {
         while (var6 < var2) {
            var7 = var1.charAt(var6);
            if (!this.isDigit(var7) && (!var4 || !this.isHexaLetter(var7))) {
               if (var4 && Character.isAlphabetic(var7)) {
                  if (!var8 && var7 == 'h') {
                     var8 = true;
                     if (var6 + 1 < var2) {
                        char var9 = var1.charAt(var6 + 1);
                        if (Character.isAlphabetic(var9) || this.isDigit(var9)) {
                           return this.getChunk(var1, var2, var3, false);
                        }
                     }

                     var5.append(var7);
                     return new NumberComparator.Token(NumberComparator.Type.Number, var5.toString(), var8);
                  }

                  return this.getChunk(var1, var2, var3, false);
               }
               break;
            }

            var5.append(var7);
            var6++;
         }

         return new NumberComparator.Token(NumberComparator.Type.Number, var5.toString(), var8);
      } else {
         while (var6 < var2) {
            var7 = var1.charAt(var6);
            if (this.isDigit(var7) || !Character.isAlphabetic(var7)) {
               break;
            }

            var5.append(var7);
            var6++;
         }

         return new NumberComparator.Token(NumberComparator.Type.Word, var5.toString(), var8);
      }
   }

   public int compare(String var1, String var2) {
      int var3 = 0;
      int var4 = 0;
      int var5 = var1.length();
      int var6 = var2.length();

      while (var3 < var5 && var4 < var6) {
         while (var1.charAt(var3) == var2.charAt(var4) && !this.isDigit(var1.charAt(var3)) && !this.isHexaLetter(var1.charAt(var3))) {
            var3++;
            if (var3 >= var5 || ++var4 >= var6) {
               return var5 - var6;
            }
         }

         NumberComparator.Token var7 = this.getChunk(var1, var5, var3, this.scanHexadecimal);
         String var8 = var7.part;
         var3 += var8.length();
         NumberComparator.Token var9 = this.getChunk(var2, var6, var4, this.scanHexadecimal);
         String var10 = var9.part;
         var4 += var10.length();
         int var11 = 0;
         if (var7.type != NumberComparator.Type.Number && var9.type != NumberComparator.Type.Number) {
            if (this.charComparator == null) {
               var11 = var8.compareTo(var10);
            } else if (!var8.equals(var10)) {
               int var20 = Math.min(var8.length(), var10.length());

               for (int var21 = 0; var21 < var20; var21++) {
                  char var22 = var8.charAt(var21);
                  char var23 = var10.charAt(var21);
                  var11 = this.charComparator.compare(var22, var23);
                  if (var11 != 0) {
                     break;
                  }
               }

               if (var11 == 0) {
                  return var8.length() - var10.length();
               }
            }
         } else {
            if (var7.type != var9.type) {
               if (var7.type == NumberComparator.Type.Word) {
                  if (this.charComparator != null) {
                     return this.charComparator.compare(var8.charAt(0), Character.valueOf((char)48));
                  }

                  return Character.isAlphabetic(var8.charAt(0)) ? 1 : -1;
               }

               if (this.charComparator != null) {
                  return this.charComparator.compare(Character.valueOf((char)48), var10.charAt(0));
               }

               return Character.isAlphabetic(var10.charAt(0)) ? -1 : 1;
            }

            var8 = this.removeZeros(var8);
            int var12 = var8.length() - (var7.finalHexSymbol ? 1 : 0);
            var10 = this.removeZeros(var10);
            var11 = var12 - (var10.length() - (var9.finalHexSymbol ? 1 : 0));
            if (var11 == 0) {
               int var13 = 0;

               for (int var14 = 0; var14 < var12; var14++) {
                  char var15 = var8.charAt(var14);
                  char var16 = var10.charAt(var14);
                  var11 = (var15 | ' ') - (var16 | ' ');
                  if (var11 != 0) {
                     return var11;
                  }

                  if (var13 == 0) {
                     int var17 = var15 - var16;
                     if (var17 != 0 && this.isHexaLetter(var15) && this.isHexaLetter(var16) && (var8.charAt(var14) | ' ') == (var10.charAt(var14) | ' ')) {
                        var13 = var17;
                     }
                  }
               }

               if (var11 == 0 && var13 != 0) {
                  return var13;
               }
            }
         }

         if (var11 != 0) {
            return var11;
         }
      }

      return var5 - var6;
   }

   private String removeZeros(String var1) {
      if (var1.length() == 0) {
         return var1;
      } else if (var1.charAt(0) != '0') {
         return var1;
      } else {
         int var2 = 0;

         while (var2 < var1.length() && var1.charAt(var2) == '0') {
            var2++;
         }

         return var2 == var1.length() ? var1.substring(var2 - 1) : var1.substring(var2);
      }
   }

   private static class Token {
      NumberComparator.Type type;
      String part;
      boolean finalHexSymbol = false;

      public Token(NumberComparator.Type var1, String var2, boolean var3) {
         this.part = var2;
         this.type = var1;
         this.finalHexSymbol = var3;
      }
   }

   private static enum Type {
      Number,
      Word;
   }
}
