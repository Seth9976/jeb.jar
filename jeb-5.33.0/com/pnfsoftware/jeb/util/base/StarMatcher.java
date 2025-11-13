package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StarMatcher implements IStringMatcher {
   private boolean ci;
   private List bits;
   private int charSearchLength;

   public StarMatcher(String var1, boolean var2, boolean var3) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.ci = var2;
         if (var2) {
            var1 = var1.toLowerCase();
         }

         this.bits = new ArrayList();
         StringBuilder var4 = new StringBuilder();

         for (int var5 = 0; var5 < var1.length(); var5++) {
            char var6 = var1.charAt(var5);
            if (var3 && var6 == '\\') {
               if (var5 + 1 < var1.length()) {
                  var6 = var1.charAt(++var5);
                  if (var6 == '\\' || var6 == '\'' || var6 == '"' || var6 == '*' || var6 == '?') {
                     var4.append(var6);
                  } else if (var6 == 'n') {
                     var4.append('\n');
                  } else if (var6 == 'r') {
                     var4.append('\r');
                  } else if (var6 == 't') {
                     var4.append('\t');
                  } else {
                     var4.append('\\').append(var6);
                  }
               }
            } else if (var6 == '?') {
               if (var4.length() > 0) {
                  this.bits.add(new StarMatcher.E(var4.toString()));
                  var4.setLength(0);
               }

               if (!this.bits.isEmpty()) {
                  this.bits.add(StarMatcher.E.ANY);
               }
            } else if (var6 == '*') {
               if (var4.length() > 0) {
                  this.bits.add(new StarMatcher.E(var4.toString()));
                  var4.setLength(0);
               }

               if (!this.bits.isEmpty() && this.bits.get(this.bits.size() - 1) != StarMatcher.E.STAR) {
                  this.bits.add(StarMatcher.E.STAR);
               }
            } else {
               var4.append(var6);
            }
         }

         if (var4.length() > 0) {
            this.bits.add(new StarMatcher.E(var4.toString()));
         }

         for (StarMatcher.E var9 : this.bits) {
            this.charSearchLength = this.charSearchLength + var9.length();
         }
      }
   }

   public StarMatcher(String var1) {
      this(var1, true, true);
   }

   public int getCharSearchLength() {
      return this.charSearchLength;
   }

   @Override
   public boolean matches(String var1) {
      return this.find(var1, 0) != null;
   }

   public Collection collect(Collection var1) {
      ArrayList var2 = new ArrayList();

      for (String var4 : var1) {
         if (this.matches(var4)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public StarMatcher.Result find(String var1, int var2) {
      if (var1 == null) {
         return null;
      } else {
         ArrayDeque var3 = new ArrayDeque();
         int var4 = var2;
         boolean var5 = false;
         int var6 = var2;
         int var7 = 0;

         while (var7 < this.bits.size()) {
            StarMatcher.E var8 = (StarMatcher.E)this.bits.get(var7);
            if (var8 == StarMatcher.E.STAR) {
               var5 = false;
            } else if (var8 != StarMatcher.E.ANY) {
               if (var5) {
                  if (!var1.regionMatches(this.ci, var6, var8.str, 0, var8.str.length())) {
                     var6 = -1;
                  }
               } else {
                  var6 = this.indexOf(var1, var8.str, var6);
                  if (var6 >= 0) {
                     var3.push(new Couple(var7, var6));
                     var5 = true;
                  }
               }

               if (var6 < 0) {
                  if (var3.isEmpty()) {
                     return null;
                  }

                  Couple var9 = (Couple)var3.pop();
                  var7 = (Integer)var9.getFirst();
                  var6 = (Integer)var9.getSecond() + 1;
                  var5 = false;
                  continue;
               }
            }

            if (var7 == 0) {
               var4 = var6;
            }

            var6 += var8.length();
            var7++;
         }

         return new StarMatcher.Result(var4, var6);
      }
   }

   private int indexOf(String var1, String var2, int var3) {
      if (var2.length() == 0) {
         return var3;
      } else if (!this.ci) {
         return var1.indexOf(var2, var3);
      } else {
         label35:
         for (int var4 = var3; var4 <= var1.length() - var2.length(); var4++) {
            if (Character.toLowerCase(var1.charAt(var4)) == var2.charAt(0)) {
               for (int var5 = 1; var5 < var2.length(); var5++) {
                  if (Character.toLowerCase(var1.charAt(var4 + var5)) != var2.charAt(var5)) {
                     continue label35;
                  }
               }

               return var4;
            }
         }

         return -1;
      }
   }

   static class E {
      static final StarMatcher.E ANY = new StarMatcher.E(1);
      static final StarMatcher.E STAR = new StarMatcher.E(2);
      final int type;
      final String str;

      E(String var1) {
         Assert.a(var1 != null && var1.length() > 0);
         this.type = 0;
         this.str = var1;
      }

      E(int var1) {
         Assert.a(var1 != 0);
         this.type = var1;
         this.str = null;
      }

      boolean isString() {
         return this.type == 0;
      }

      int length() {
         if (this.type == 1) {
            return 1;
         } else {
            return this.type == 2 ? 0 : this.str.length();
         }
      }

      @Override
      public String toString() {
         if (this.type == 0) {
            return this.str;
         } else if (this.type == 1) {
            return "?";
         } else if (this.type == 2) {
            return "*";
         } else {
            throw new RuntimeException();
         }
      }
   }

   static class LineResult {
      public int charStartIndex;
      public int charEndIndex;
      public int lineStartIndex;
      public int lineEndIndex;

      public LineResult(int var1, int var2, int var3, int var4) {
         this.charStartIndex = var1;
         this.charEndIndex = var2;
         this.lineStartIndex = var3;
         this.lineEndIndex = var4;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.charEndIndex;
         var1 = 31 * var1 + this.charStartIndex;
         var1 = 31 * var1 + this.lineEndIndex;
         return 31 * var1 + this.lineStartIndex;
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
            StarMatcher.LineResult var2 = (StarMatcher.LineResult)var1;
            if (this.charEndIndex != var2.charEndIndex) {
               return false;
            } else if (this.charStartIndex != var2.charStartIndex) {
               return false;
            } else {
               return this.lineEndIndex != var2.lineEndIndex ? false : this.lineStartIndex == var2.lineStartIndex;
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Found: %d,%d (line: %d,%d)", this.charStartIndex, this.charEndIndex, this.lineStartIndex, this.lineEndIndex);
      }
   }

   public static class Result {
      public int charStartIndex;
      public int charEndIndex;

      public Result(int var1, int var2) {
         this.charStartIndex = var1;
         this.charEndIndex = var2;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.charEndIndex;
         return 31 * var1 + this.charStartIndex;
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
            StarMatcher.Result var2 = (StarMatcher.Result)var1;
            return this.charEndIndex != var2.charEndIndex ? false : this.charStartIndex == var2.charStartIndex;
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Found: %d,%d", this.charStartIndex, this.charEndIndex);
      }
   }
}
