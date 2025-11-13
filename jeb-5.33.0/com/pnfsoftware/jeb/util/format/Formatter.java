package com.pnfsoftware.jeb.util.format;

import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.collect.IntegerSegment;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.primitives.Characters;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.lang.Character.UnicodeBlock;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;

public class Formatter {
   private static SegmentMap rangesCharsPrintable;
   private static SegmentMap rangesCharsNonPrintable;

   public static CharSequence formatBinaryBlock(byte[] var0, int var1, int var2, long var3, boolean var5) {
      StringBuilder var6 = new StringBuilder();
      int var7 = var1;
      int var8 = var2;

      while (var8 > 0) {
         if (var5) {
            var6.append(toHexString(var7 - var3, true, 16)).append("  ");
         } else {
            var6.append(toHexString(var7 - (int)var3, true, 8)).append("  ");
         }

         int var9;
         for (var9 = 0; var9 < 16 && var9 < var8; var9++) {
            var6.append(toHexString(var0[var7 + var9], true, 2)).append(" ");
         }

         int var10;
         for (var10 = var9; var9 < 16; var9++) {
            var6.append("   ");
         }

         var6.append(" ");

         for (int var12 = 0; var12 < var10; var12++) {
            byte var11 = var0[var7 + var12];
            if (Characters.isAsciiChar(var11)) {
               var6.append((char)var11);
            } else {
               var6.append(".");
            }
         }

         var6.append("\n");
         var7 += var10;
         var8 -= var10;
      }

      return var6;
   }

   public static CharSequence formatBinaryBlock(byte[] var0, int var1, int var2, int var3) {
      return formatBinaryBlock(var0, var1, var2, var3, false);
   }

   public static CharSequence formatBinaryBlock(byte[] var0, int var1, int var2) {
      return formatBinaryBlock(var0, var1, var2, 0);
   }

   public static CharSequence formatBinaryBlock(byte[] var0) {
      return formatBinaryBlock(var0, 0, var0.length);
   }

   public static CharSequence formatBinaryLine(byte[] var0, int var1, int var2, int var3) {
      return formatBinaryLine(var0, var1, var2, var3, true);
   }

   public static CharSequence formatBinaryLine(byte[] var0, int var1, int var2, int var3, boolean var4) {
      StringBuilder var5 = new StringBuilder();

      int var6;
      for (var6 = 0; var6 < var2; var6++) {
         var5.append(toHexString(var0[var1 + var6], true, 2));
         if (var4) {
            var5.append(" ");
         }
      }

      while (var6 < var3) {
         var5.append("   ");
         var6++;
      }

      return var5;
   }

   public static CharSequence formatBinaryLine(byte[] var0, int var1, int var2) {
      return formatBinaryLine(var0, var1, var2, var2);
   }

   public static CharSequence formatBinaryLine(byte[] var0) {
      return formatBinaryLine(var0, 0, var0.length, 0);
   }

   public static CharSequence formatBinaryLineTruncate(byte[] var0, int var1, int var2, int var3, char var4) {
      StringBuilder var5 = new StringBuilder();
      int var7 = var2 <= var3 ? var2 : var3;

      int var6;
      for (var6 = 0; var6 < var7; var6++) {
         if (var6 >= 1) {
            var5.append(" ");
         }

         var5.append(toHexString(var0[var1 + var6], true, 2));
      }

      if (var2 <= var3) {
         var5.append(" ");

         while (var6 < var3) {
            var5.append("   ");
            var6++;
         }
      } else {
         var5.append(var4);
      }

      return var5;
   }

   public static CharSequence formatBinaryLineTruncate(byte[] var0, int var1, int var2, int var3) {
      return formatBinaryLineTruncate(var0, var1, var2, var3, '+');
   }

   public static CharSequence byteArrayToHex(byte[] var0) {
      return byteArrayToHex(var0, 0, var0.length);
   }

   public static String byteArrayToHexString(byte[] var0) {
      return byteArrayToHexString(var0, 0, var0.length);
   }

   public static String byteArrayToHexString(byte[] var0, int var1) {
      return byteArrayToHexString(var0, var1, var0.length);
   }

   public static String byteArrayToHexString(byte[] var0, int var1, int var2) {
      return byteArrayToHex(var0, var1, var2).toString();
   }

   public static CharSequence byteArrayToHex(byte[] var0, int var1, int var2) {
      return byteArrayToHex(var0, var1, var2, false, 0);
   }

   public static CharSequence byteArrayToHex(byte[] var0, int var1, int var2, boolean var3, int var4) {
      if (var0 != null && var1 >= 0 && var2 <= var0.length && var1 <= var2) {
         StringBuilder var5 = new StringBuilder();
         int var6 = var1;

         for (int var7 = var1; var7 < var2; var7++) {
            var5.append(toHexString(var0[var7], true, 2));
            if (var3 && ((var7 - var1) % var4 == var4 - 1 || var7 == var2 - 1)) {
               if (var7 == var2 - 1) {
                  var5.append(Strings.pad(' ', 2 * (var4 - (var7 - var1) % var4 - 1)));
               }

               var5.append("  ");

               for (int var8 = 0; var8 < var4 && var6 + var8 < var2; var8++) {
                  if (Characters.isAsciiChar(var0[var6 + var8])) {
                     var5.append((char)var0[var6 + var8]);
                  } else {
                     var5.append(".");
                  }
               }

               var6 = var7 + 1;
            }

            if (var4 > 0 && (var7 - var1) % var4 == var4 - 1 && var7 + 1 != var2) {
               var5.append("\n");
            }
         }

         return var5;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static byte[] hexStringToByteArray(String var0, int var1, int var2) {
      if (var1 >= 0 && var1 <= var2 && (var2 - var1) % 2 == 0) {
         int var3 = (var2 - var1) / 2;
         byte[] var4 = new byte[var3];

         for (int var5 = 0; var5 < var3; var5++) {
            try {
               var4[var5] = (byte)Integer.parseInt(var0.substring(var1, var1 + 2), 16);
               var1 += 2;
            } catch (NumberFormatException var6) {
               return null;
            }
         }

         return var4;
      } else {
         return null;
      }
   }

   public static byte[] hexStringToByteArray(String var0) {
      if (var0 == null) {
         return null;
      } else {
         byte var1 = 0;
         int var2 = var0.length();
         if (var0.endsWith("h")) {
            var2--;
         }

         if (var0.startsWith("0x") || var0.startsWith("0X")) {
            var1 += 2;
         }

         return hexStringToByteArray(var0, var1, var2);
      }
   }

   public static void addCustomPrintableCharRange(int var0, int var1, boolean var2) {
      int var3 = var1 - var0;
      if (var3 > 0) {
         if (var2) {
            if (rangesCharsPrintable == null) {
               rangesCharsPrintable = new SegmentMap();
            }

            rangesCharsPrintable.add(new IntegerSegment(var0, var3));
         } else {
            if (rangesCharsNonPrintable == null) {
               rangesCharsNonPrintable = new SegmentMap();
            }

            rangesCharsNonPrintable.add(new IntegerSegment(var0, var3));
         }
      }
   }

   public static void resetCustomPrintableCharRanges() {
      rangesCharsPrintable = null;
      rangesCharsNonPrintable = null;
   }

   public static boolean isPrintableChar(char var0) {
      if (Character.isISOControl(var0)) {
         return false;
      } else if (Character.isSurrogate(var0)) {
         return false;
      } else if (rangesCharsNonPrintable != null && rangesCharsNonPrintable.getSegmentContaining(Integer.valueOf(var0)) != null) {
         return false;
      } else if (rangesCharsPrintable != null && rangesCharsPrintable.getSegmentContaining(Integer.valueOf(var0)) != null) {
         return true;
      } else {
         UnicodeBlock var1 = UnicodeBlock.of(var0);
         return var1 == null ? false : var1 != UnicodeBlock.SPECIALS;
      }
   }

   public static String escapeCharacter(char var0, boolean var1) {
      if (var0 == '\'') {
         return "\\'";
      } else if (var0 == '"') {
         return "\\\"";
      } else if (var0 == '\\') {
         return "\\\\";
      } else if (var1 && isPrintableChar(var0)) {
         return Character.toString(var0);
      } else if (var0 >= 127) {
         return "\\u" + toHexString(var0, true, 4);
      } else if (var0 >= ' ') {
         return Character.toString(var0);
      } else if (var0 == '\n') {
         return "\\n";
      } else if (var0 == '\r') {
         return "\\r";
      } else if (var0 == '\t') {
         return "\\t";
      } else if (var0 == '\b') {
         return "\\b";
      } else {
         return var0 == '\f' ? "\\f" : "\\u" + toHexString(var0, true, 4);
      }
   }

   public static String escapeCharacter(char var0) {
      return escapeCharacter(var0, true);
   }

   public static String escapeString(CharSequence var0) {
      return escapeString(var0, true);
   }

   public static String escapeString(CharSequence var0, boolean var1) {
      return escapeString(var0, 0, var1, null);
   }

   public static String escapeString(CharSequence var0, int var1, boolean var2, Set var3) {
      StringBuilder var4 = new StringBuilder();
      boolean var5 = false;
      if (var1 != -1) {
         if (var1 == 0) {
            if (Strings.hasRtl(var0)) {
               var2 = false;
            }
         } else if (var2 && Strings.hasRtl(var0)) {
            if (var1 == 1) {
               var4.append('\u202a');
            } else {
               if (var1 != 2) {
                  throw new IllegalArgumentException("Illegal directionality argument: " + var1);
               }

               var4.append('\u202b');
            }

            var5 = true;
         }
      }

      for (int var6 = 0; var6 < var0.length(); var6++) {
         char var7 = var0.charAt(var6);
         if (Characters.isAsciiChar(var7) && var7 != '\'' && var7 != '\\' && var7 != '"') {
            var4.append(var7);
         } else if (var3 != null && var3.contains(var7)) {
            var4.append(var7);
         } else {
            var4.append(escapeCharacter(var7, var2));
         }
      }

      if (var5) {
         var4.append('\u202c');
      }

      return var4.toString();
   }

   public static String unescapeString(String var0) throws ParseException {
      Object var1 = "";
      int var2 = 0;

      while (var2 < var0.length()) {
         char var3 = var0.charAt(var2++);
         if (var3 == '\\') {
            if (var2 >= var0.length()) {
               throw new ParseException("String too short, need 1 char for escape", var2);
            }

            var3 = var0.charAt(var2++);
            if (var3 == '\\') {
               var1 = var1 + "\\";
            } else if (var3 == '"') {
               var1 = var1 + "\"";
            } else if (var3 == '\'') {
               var1 = var1 + "'";
            } else if (var3 == 'n') {
               var1 = var1 + "\n";
            } else if (var3 == 'r') {
               var1 = var1 + "\r";
            } else if (var3 == 't') {
               var1 = var1 + "\t";
            } else if (var3 == 'b') {
               var1 = var1 + "\b";
            } else if (var3 == 'f') {
               var1 = var1 + "\f";
            } else if (var3 == 'u') {
               if (var2 + 4 > var0.length()) {
                  throw new ParseException("String too short, need 4 chars for escape", var2);
               }

               int var4 = Integer.parseInt(var0.substring(var2, var2 + 4), 16);
               var2 += 4;
               var1 = var1 + (char)var4;
            } else {
               if (var3 != 'x') {
                  throw new ParseException("Unknown escape: " + var3, var2);
               }

               if (var2 + 2 > var0.length()) {
                  throw new ParseException("String too short, need 2 chars for escape", var2);
               }

               int var6 = Integer.parseInt(var0.substring(var2, var2 + 2), 16);
               var2 += 2;
               var1 = var1 + (char)var6;
            }
         } else {
            var1 = var1 + var3;
         }
      }

      return (String)var1;
   }

   public static String escapeAllCharacters(CharSequence var0) {
      StringBuilder var1 = new StringBuilder();

      for (int var2 = 0; var2 < var0.length(); var2++) {
         char var3 = var0.charAt(var2);
         var1.append("\\u").append(toHexString(var3, true, 4));
      }

      return var1.toString();
   }

   public static String escapeToJavaStringArray(Collection var0) {
      StringBuilder var1 = new StringBuilder();
      var1.append("new String[]{\n");

      for (Object var3 : var0) {
         if (var3 == null) {
            var1.append("    null,\n");
         } else {
            var1.append("    \"").append(escapeString(var3.toString())).append("\",\n");
         }
      }

      var1.append("}");
      return var1.toString();
   }

   public static String escapeBytes(byte[] var0, int var1, int var2) {
      StringBuilder var3 = new StringBuilder();

      for (int var4 = var1; var4 < var1 + var2; var4++) {
         var3.append(escapeCharacter((char)(var0[var4] & 255), false));
      }

      return var3.toString();
   }

   public static String escapeBytes(byte[] var0) {
      return escapeBytes(var0, 0, var0.length);
   }

   public static String escapeByte(int var0) {
      return escapeBytes(new byte[]{(byte)var0});
   }

   public static String formatHexNumbers(Collection var0) {
      return formatNumbers(var0, 16, null, null);
   }

   public static String formatNumbers(Collection var0, int var1, String var2, String var3) {
      StringBuilder var4 = new StringBuilder("[");
      int var5 = 0;
      var2 = Strings.safe(var2);
      var3 = Strings.safe(var3);
      if (var1 == 8) {
         for (Number var7 : var0) {
            if (var5 >= 1) {
               var4.append(",");
            }

            var4.append(var2).append(Long.toOctalString(var7.longValue())).append(var3);
            var5++;
         }
      } else if (var1 == 10) {
         for (Number var12 : var0) {
            if (var5 >= 1) {
               var4.append(",");
            }

            var4.append(var2).append(var12.longValue()).append(var3);
            var5++;
         }
      } else {
         if (var1 != 16) {
            throw new IllegalArgumentException("Invalid base: " + var1);
         }

         for (Number var13 : var0) {
            if (var5 >= 1) {
               var4.append(",");
            }

            var4.append(var2).append(Long.toHexString(var13.longValue()).toUpperCase()).append(var3);
            var5++;
         }
      }

      var4.append("]");
      return var4.toString();
   }

   public static String integerToAlphaString(int var0) {
      String var1 = "";
      boolean var2 = false;
      if (var0 < 0) {
         var0 = -var0;
         var2 = true;
      }

      int var3 = 1;

      for (byte var4 = 26; var0 >= var4; var3++) {
         var0 -= var4;
         var4 *= 26;
      }

      for (int var5 = 0; var5 < var3; var5++) {
         int var6 = var0 % 26;
         var0 /= 26;
         var1 = (char)(97 + var6) + var1;
      }

      return var2 ? "-" + var1 : var1;
   }

   public static String toHexString(long var0, boolean var2, int var3) {
      Object var4 = toHexString(var0, var2);
      if (var4.length() < var3) {
         var4 = Strings.pad('0', var3 - var4.length()) + var4;
      }

      return (String)var4;
   }

   public static String toHexString(long var0, boolean var2) {
      String var3 = Long.toHexString(var0);
      if (var2) {
         var3 = var3.toUpperCase();
      }

      return var3;
   }

   public static String toHexString(int var0, boolean var1, int var2) {
      Object var3 = toHexString(var0, var1);
      if (var3.length() < var2) {
         var3 = Strings.pad('0', var2 - var3.length()) + var3;
      }

      return (String)var3;
   }

   public static String toHexString(int var0, boolean var1) {
      String var2 = Integer.toHexString(var0);
      if (var1) {
         var2 = var2.toUpperCase();
      }

      return var2;
   }

   public static String toHexString(short var0, boolean var1, int var2) {
      return toHexString(var0 & '\uffff', var1, var2);
   }

   public static String toHexString(short var0, boolean var1) {
      return toHexString(var0 & '\uffff', var1);
   }

   public static String toHexString(byte var0, boolean var1, int var2) {
      return toHexString(var0 & 255, var1, var2);
   }

   public static String toHexString(byte var0, boolean var1) {
      return toHexString(var0 & 255, var1);
   }

   public static String htmlEscape(String var0) {
      return htmlEscape(var0, false);
   }

   public static String htmlEscape(String var0, boolean var1) {
      var0 = StringEscapeUtils.escapeHtml4(var0);
      if (var1) {
         var0 = var0.replaceAll("\r|\r\n|\n", "<br>\n");
      }

      return var0;
   }

   public static List wordWrap(String var0, int var1, int var2) {
      if (var0 == null) {
         throw new IllegalArgumentException("Null string");
      } else if (var1 <= 0) {
         throw new IllegalArgumentException("Invalid wrapping length: " + var1);
      } else {
         ArrayList var3 = new ArrayList();
         boolean var4 = false;
         StringBuilder var5 = new StringBuilder();

         for (String var9 : Strings.splitLines(var0)) {
            int var10 = 0;
            int var11 = Integer.MAX_VALUE;

            for (int var12 = 0; var12 < var9.length(); var12++) {
               char var13 = var9.charAt(var12);
               boolean var14 = Character.getType(var13) == 12;
               if (var14) {
                  var11 = var12;
               }

               var5.append(var13);
               if (var5.length() >= var1) {
                  if (var2 == 0) {
                     var4 = true;
                  } else if (var2 > 0) {
                     var4 = var14;
                  } else {
                     if (!var14 && var11 - var10 < var1) {
                        var5.setLength(var11 - var10 + 1);
                        var12 = var11;
                     }

                     var4 = true;
                  }
               }

               if (var4) {
                  var3.add(var5.toString());
                  var5.setLength(0);
                  var4 = false;
                  var10 = var12 + 1;
               }
            }

            if (var5.length() > 0) {
               var3.add(var5.toString());
               var5.setLength(0);
               var4 = false;
            }
         }

         return var3;
      }
   }

   public static String toString(Object var0) {
      return new PrettyPrinter(true, 2).format(var0);
   }

   @SerDisabled
   private static class CharSegment implements ISegment {
      private Character begin;
      private Character end;

      public CharSegment(Character var1, Character var2) {
         this.begin = var1;
         this.end = var2;
      }

      public Character getBegin() {
         return this.begin;
      }

      public Character getEnd() {
         return this.end;
      }
   }
}
