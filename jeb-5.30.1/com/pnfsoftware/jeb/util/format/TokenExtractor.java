package com.pnfsoftware.jeb.util.format;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TokenExtractor {
   public static final TokenExtractor.IDelimiterFinder DF_WhiteSpace = var0 -> Character.isSpaceChar(var0);
   public static final TokenExtractor.IDelimiterFinder DF_CommonFullSymbolChars = var0 -> !Character.isJavaIdentifierPart(var0) && var0 != '/' && var0 != '+';
   public static final TokenExtractor.IDelimiterFinder DF_CommonSymbolChars = var0 -> !Character.isJavaIdentifierPart(var0);
   public static final TokenExtractor.IDelimiterFinder DF_NonAlphaNum = var0 -> !Character.isAlphabetic(var0) && !Character.isDigit(var0);
   public static final TokenExtractor.IDelimiterFinder DF_Assignment = var0 -> var0 == '=';
   private static final List tokenList = Collections.unmodifiableList(
      Arrays.asList(
         new TokenExtractor(DF_WhiteSpace),
         new TokenExtractor(DF_CommonFullSymbolChars),
         new TokenExtractor(DF_CommonSymbolChars),
         new TokenExtractor(DF_NonAlphaNum),
         new TokenExtractor(DF_Assignment)
      )
   );
   private final TokenExtractor.IDelimiterFinder delimiterFinder;

   public static List getGenericTokenList() {
      return tokenList;
   }

   public TokenExtractor(TokenExtractor.IDelimiterFinder var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.delimiterFinder = var1;
      }
   }

   public String extract(String var1, int var2) {
      int[] var3 = this.extractCoordinates(var1, var2);
      return var3 == null ? null : var1.substring(var3[0], var3[1]);
   }

   public int[] extractCoordinates(String var1, int var2) {
      int var3 = var1.length();
      int var4 = 0;

      for (int var5 = var2; var5 < var1.length(); var5++) {
         if (this.match(var1, var5)) {
            var3 = var5;
            break;
         }
      }

      if (var3 == var2) {
         return null;
      } else {
         for (int var6 = var2 - 1; var6 >= 0; var6--) {
            if (this.match(var1, var6)) {
               var4 = var6 + 1;
               break;
            }
         }

         return var4 >= var3 ? null : new int[]{var4, var3};
      }
   }

   protected boolean match(String var1, int var2) {
      char var3 = var1.charAt(var2);
      return this.delimiterFinder.isDelimiter(var3);
   }

   @FunctionalInterface
   public interface IDelimiterFinder {
      boolean isDelimiter(char var1);
   }
}
