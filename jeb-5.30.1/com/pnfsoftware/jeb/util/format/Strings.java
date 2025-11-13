package com.pnfsoftware.jeb.util.format;

import com.pnfsoftware.jeb.util.base.StarMatcher;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.primitives.Characters;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.MalformedInputException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strings {
   public static final String LINESEP;
   static Charset csASCII;
   static Charset csUTF8;
   static Charset csUTF16;
   static Charset csUTF16LE;
   static Charset csUTF16BE;
   static Charset csUTF32LE;
   static Charset csUTF32BE;
   private static Set unicodeWhiteSpaces;
   private static final Random prng;

   public static boolean hasLength(CharSequence var0) {
      return var0 != null && var0.length() > 0;
   }

   public static String replaceNewLines(String var0, String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Replacement string cannot be null");
      } else if (var1.indexOf(13) >= 0 || var1.indexOf(10) >= 0) {
         throw new IllegalArgumentException("The replacement string cannot contain new-line characters");
      } else if (var0 == null) {
         return null;
      } else {
         return var0.indexOf(13) < 0 && var0.indexOf(10) < 0 ? var0 : var0.replace("\r\n", var1).replace("\r", var1).replace("\n", var1);
      }
   }

   public static String normalizeNewLines(String var0) {
      return var0.replace("\r\n", "\n").replace("\r", "\n");
   }

   public static String safe(Object var0) {
      return var0 != null ? var0.toString() : "";
   }

   public static String safe(Object var0, String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         return var0 != null ? var0.toString() : var1;
      }
   }

   public static String safe2(Object var0, String var1) {
      if (var1 == null || var1.isEmpty()) {
         throw new IllegalArgumentException();
      } else if (var0 == null) {
         return var1;
      } else {
         String var2 = var0.toString();
         return var2.isEmpty() ? var1 : var2;
      }
   }

   public static String joinList(Iterable var0) {
      return "[" + join(", ", var0) + "]";
   }

   public static String joinv(String var0, Object... var1) {
      return joinv(var0, "null", var1);
   }

   public static String joinv(String var0, String var1, Object... var2) {
      if (var0 == null) {
         throw new IllegalArgumentException();
      } else {
         StringBuilder var3 = new StringBuilder();
         int var4 = 0;

         for (Object var8 : var2) {
            if (var4 >= 1) {
               var3.append(var0);
            }

            var3.append(var8 == null ? var1 : var8.toString());
            var4++;
         }

         return var3.toString();
      }
   }

   public static String join(String var0, Iterable var1) {
      if (var0 == null) {
         throw new IllegalArgumentException();
      } else {
         StringBuilder var2 = new StringBuilder();
         int var3 = 0;

         for (Object var5 : var1) {
            if (var3 >= 1) {
               var2.append(var0);
            }

            var2.append(var5 == null ? "null" : var5.toString());
            var3++;
         }

         return var2.toString();
      }
   }

   public static String join(String var0, Iterable var1, Function var2) {
      if (var0 == null) {
         throw new IllegalArgumentException();
      } else {
         StringBuilder var3 = new StringBuilder();
         int var4 = 0;

         for (Object var6 : var1) {
            if (var4 >= 1) {
               var3.append(var0);
            }

            var3.append((CharSequence)var2.apply(var6));
            var4++;
         }

         return var3.toString();
      }
   }

   public static String join(String var0, String[] var1, int var2, int var3) {
      if (var0 == null) {
         throw new IllegalArgumentException();
      } else {
         StringBuilder var4 = new StringBuilder();

         for (int var5 = var2; var5 < var3; var5++) {
            if (var5 > var2) {
               var4.append(var0);
            }

            var4.append(var1[var5]);
         }

         return var4.toString();
      }
   }

   public static String[] splitLines(String var0, boolean var1) {
      return var0.length() < 300 && var0.indexOf(10) < 0 && var0.indexOf(13) < 0 ? new String[]{var0} : var0.split("(\r\n|\r|\n)", var1 ? 0 : -1);
   }

   public static String[] splitLines(String var0) {
      return splitLines(var0, false);
   }

   public static String[] splitall(String var0, String var1) {
      return var0.split(var1, -1);
   }

   public static String firstLine(String var0) {
      int var1 = indexOf2(var0, '\r', '\n');
      return var1 < 0 ? var0 : var0.substring(0, var1);
   }

   public static int search(CharSequence var0, int var1, String var2, boolean var3, boolean var4, boolean var5) {
      if (var0 == null || var2 == null) {
         throw new IllegalArgumentException();
      } else if (var1 < 0 || var1 > var0.length()) {
         return -1;
      } else if (var2.isEmpty()) {
         return var1;
      } else {
         Pattern var6 = null;
         if (var3) {
            try {
               var6 = Pattern.compile(var2, var4 ? 0 : 2);
            } catch (IllegalArgumentException var11) {
            }
         }

         if (var6 == null) {
            if (var2.length() > var0.length()) {
               return -1;
            } else if (!var5 && var1 + var2.length() > var0.length()) {
               return -1;
            } else if (var5 && var1 - var2.length() < 0) {
               return -1;
            } else if (var4 && !var5) {
               return var0.toString().indexOf(var2, var1);
            } else {
               if (!var4) {
                  var2 = var2.toLowerCase();
               }

               char var12 = var2.charAt(0);
               String var13 = null;
               if (!var5) {
                  for (int var16 = var1; var16 <= var0.length() - var2.length(); var16++) {
                     char var18 = toLowerCase(var0.charAt(var16));
                     if (var18 == var12) {
                        if (var13 == null) {
                           var13 = var0.toString();
                        }

                        if (var2.regionMatches(true, 0, var13, var16, var2.length())) {
                           return var16;
                        }
                     }
                  }

                  return -1;
               } else if (var4) {
                  for (int var15 = var1 - var2.length(); var15 >= 0; var15--) {
                     char var17 = var0.charAt(var15);
                     if (var17 == var12) {
                        if (var13 == null) {
                           var13 = var0.toString();
                        }

                        if (var2.regionMatches(false, 0, var13, var15, var2.length())) {
                           return var15;
                        }
                     }
                  }

                  return -1;
               } else {
                  for (int var14 = var1 - var2.length(); var14 >= 0; var14--) {
                     char var10 = toLowerCase(var0.charAt(var14));
                     if (var10 == var12) {
                        if (var13 == null) {
                           var13 = var0.toString();
                        }

                        if (var2.regionMatches(true, 0, var13, var14, var2.length())) {
                           return var14;
                        }
                     }
                  }

                  return -1;
               }
            }
         } else {
            Matcher var7 = var6.matcher(var0);
            if (var5) {
               int var8 = -1;

               while (var7.find()) {
                  int var9 = var7.start();
                  if (var1 > 0 && var9 >= var1) {
                     break;
                  }

                  var8 = var9;
               }

               return var8;
            } else {
               return var7.find(var1) ? var7.start() : -1;
            }
         }
      }
   }

   private static char toLowerCase(int var0) {
      if (var0 < 127) {
         if (var0 > 90) {
            return (char)var0;
         }

         if (var0 > 64) {
            return (char)(var0 | 32);
         }

         if (var0 >= 32) {
            return (char)var0;
         }
      }

      return (char)Character.toLowerCase(var0);
   }

   public static boolean isContainedIn(String var0, String... var1) {
      if (var0 != null) {
         for (String var5 : var1) {
            if (var0.equals(var5)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean contains(String var0, String... var1) {
      if (var0 != null) {
         for (String var5 : var1) {
            if (var0.contains(var5)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean startsWith(String var0, String... var1) {
      if (var0 != null) {
         for (String var5 : var1) {
            if (var0.startsWith(var5)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean containsAt(String var0, int var1, String var2) {
      if (var1 >= 0 && var1 + var2.length() <= var0.length()) {
         for (int var3 = 0; var3 < var2.length(); var3++) {
            if (var0.charAt(var1 + var3) != var2.charAt(var3)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean endsWith(String var0, String... var1) {
      if (var0 != null) {
         for (String var5 : var1) {
            if (var0.endsWith(var5)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean equals(String var0, String var1) {
      return var0 == null && var1 == null || var0 != null && var0.equals(var1);
   }

   public static boolean equalsIgnoreCase(String var0, String var1) {
      return var0 != null && var0.equalsIgnoreCase(var1);
   }

   public static String toString(Object var0) {
      return toString(var0, "null");
   }

   public static String toString(Object var0, String var1) {
      return var0 == null ? var1 : var0.toString();
   }

   public static String generate(char var0, int var1) {
      if (var1 <= 0) {
         return "";
      } else {
         StringBuilder var2 = new StringBuilder(var1);

         for (int var3 = 0; var3 < var1; var3++) {
            var2.append(var0);
         }

         return var2.toString();
      }
   }

   public static String generate(CharSequence var0, int var1) {
      if (var1 <= 0) {
         return "";
      } else {
         StringBuilder var2 = new StringBuilder(var0.length() * var1);

         for (int var3 = 0; var3 < var1; var3++) {
            var2.append(var0);
         }

         return var2.toString();
      }
   }

   public static String spaces(int var0) {
      if (var0 <= 0) {
         return "";
      } else {
         switch (var0) {
            case 1:
               return " ";
            case 2:
               return "  ";
            case 3:
               return "   ";
            case 4:
               return "    ";
            case 5:
               return "     ";
            case 6:
               return "      ";
            case 7:
               return "       ";
            case 8:
               return "        ";
            case 9:
               return "         ";
            case 10:
               return "          ";
            default:
               StringBuilder var1 = new StringBuilder("           ");

               for (int var2 = 0; var2 < var0 - 11; var2++) {
                  var1.append(' ');
               }

               return var1.toString();
         }
      }
   }

   public static boolean isBlank(CharSequence var0) {
      if (var0 != null) {
         for (int var1 = 0; var1 < var0.length(); var1++) {
            if (!Character.isWhitespace(var0.charAt(var1))) {
               return false;
            }
         }
      }

      return true;
   }

   public static int countNonBlankCharacters(CharSequence var0) {
      int var1 = 0;
      if (var0 != null) {
         for (int var2 = 0; var2 < var0.length(); var2++) {
            if (!Character.isWhitespace(var0.charAt(var2))) {
               var1++;
            }
         }
      }

      return var1;
   }

   public static int indexOf(CharSequence var0, char var1) {
      for (int var2 = 0; var2 < var0.length(); var2++) {
         if (var0.charAt(var2) == var1) {
            return var2;
         }
      }

      return -1;
   }

   public static int indexOf2(CharSequence var0, char var1, char var2) {
      return indexOf2(var0, 0, var1, var2);
   }

   public static int indexOf2(CharSequence var0, int var1, char var2, char var3) {
      for (int var4 = var1; var4 < var0.length(); var4++) {
         char var5 = var0.charAt(var4);
         if (var5 == var2 || var5 == var3) {
            return var4;
         }
      }

      return -1;
   }

   public static int indexOfAny(CharSequence var0, Set var1) {
      for (int var2 = 0; var2 < var0.length(); var2++) {
         char var3 = var0.charAt(var2);
         if (var1.contains(var3)) {
            return var2;
         }
      }

      return -1;
   }

   public static int indexOfNotInGroup(CharSequence var0, char var1, int var2, char[]... var3) {
      int[] var4 = new int[var3.length];

      for (int var5 = var2; var5 < var0.length(); var5++) {
         char var6 = var0.charAt(var5);
         if (var6 == var1) {
            boolean var7 = true;

            for (int var8 = 0; var8 < var3.length; var8++) {
               if (var4[var8] != 0) {
                  var7 = false;
                  break;
               }
            }

            if (var7) {
               return var5;
            }
         }

         for (int var9 = 0; var9 < var3.length; var9++) {
            if (var6 == var3[var9][0]) {
               var4[var9]++;
               break;
            }

            if (var6 == var3[var9][1]) {
               if (var4[var9] == 0) {
                  return -2;
               }

               var4[var9]--;
            }
         }
      }

      return -1;
   }

   public static int lastIndexOf2(CharSequence var0, char var1, char var2) {
      return lastIndexOf2(var0, var0.length() - 1, var1, var2);
   }

   public static int lastIndexOf2(CharSequence var0, int var1, char var2, char var3) {
      for (int var4 = var1; var4 >= 0; var4--) {
         char var5 = var0.charAt(var4);
         if (var5 == var2 || var5 == var3) {
            return var4;
         }
      }

      return -1;
   }

   public static int lastIndexOfAny(CharSequence var0, Set var1) {
      for (int var2 = var0.length() - 1; var2 >= 0; var2--) {
         char var3 = var0.charAt(var2);
         if (var1.contains(var3)) {
            return var2;
         }
      }

      return -1;
   }

   public static boolean hasBlank(CharSequence var0) {
      if (var0 != null) {
         for (int var1 = 0; var1 < var0.length(); var1++) {
            if (Character.isWhitespace(var0.charAt(var1))) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean isWhitespace(char var0) {
      return unicodeWhiteSpaces.contains(var0);
   }

   public static boolean isAsciiWhitespace(int var0, char... var1) {
      if (var0 != 32 && var0 != 9 && var0 != 13 && var0 != 10) {
         if (var1 != null && var1.length > 0) {
            for (char var5 : var1) {
               if (var0 == var5) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public static String replaceWhitespaces(String var0, char var1) {
      boolean var2 = false;

      int var3;
      for (var3 = 0; var3 < var0.length(); var3++) {
         char var4 = var0.charAt(var3);
         if (isWhitespace(var4)) {
            var2 = true;
            break;
         }
      }

      if (!var2) {
         return var0;
      } else {
         StringBuilder var6;
         for (var6 = new StringBuilder(); var3 < var0.length(); var3++) {
            char var5 = var0.charAt(var3);
            if (isWhitespace(var5)) {
               var6.append(var1);
            } else {
               var6.append(var5);
            }
         }

         return var6.toString();
      }
   }

   public static String trimWhitespaces(String var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.length() && unicodeWhiteSpaces.contains(var0.charAt(var2)); var2++) {
         var1++;
      }

      int var4 = var0.length();

      for (int var3 = var4 - 1; var3 >= var1 && unicodeWhiteSpaces.contains(var0.charAt(var3)); var3--) {
         var4--;
      }

      return var0.substring(var1, var4);
   }

   public static String trim(String var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.length() && var0.charAt(var2) <= ' '; var2++) {
         var1++;
      }

      int var4 = var0.length();

      for (int var3 = var4 - 1; var3 >= var1 && var0.charAt(var3) <= ' '; var3--) {
         var4--;
      }

      return var0.substring(var1, var4);
   }

   public static String trim(String var0, char var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var0.length() && var0.charAt(var3) == var1; var3++) {
         var2++;
      }

      int var5 = var0.length();

      for (int var4 = var5 - 1; var4 >= var2 && var0.charAt(var4) == var1; var4--) {
         var5--;
      }

      return var0.substring(var2, var5);
   }

   public static String ltrim(String var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.length() && var0.charAt(var2) <= ' '; var2++) {
         var1++;
      }

      return var0.substring(var1);
   }

   public static String rtrim(String var0) {
      int var1 = var0.length();

      for (int var2 = var1 - 1; var2 >= 0 && var0.charAt(var2) <= ' '; var2--) {
         var1--;
      }

      return var0.substring(0, var1);
   }

   public static String ltrim(String var0, char var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var0.length() && var0.charAt(var3) == var1; var3++) {
         var2++;
      }

      return var0.substring(var2);
   }

   public static String rtrim(String var0, char var1) {
      int var2 = var0.length();

      for (int var3 = var2 - 1; var3 >= 0 && var0.charAt(var3) == var1; var3--) {
         var2--;
      }

      return var0.substring(0, var2);
   }

   public static int getAsciiLength(byte[] var0, int var1) {
      int var2;
      for (var2 = 0; var2 < var1; var2++) {
         byte var3 = var0[var2];
         if (!Characters.isAsciiCharOrCommonFormat(var3)) {
            break;
         }
      }

      return var2;
   }

   public static int getAsciiLength(byte[] var0) {
      return getAsciiLength(var0, var0.length);
   }

   public static Charset determinePotentialEncoding(byte[] var0, int var1, int var2) {
      Charset var3 = null;
      ByteBuffer var4 = ByteBuffer.wrap(var0, var1, var2);
      byte[] var5 = new byte[4];

      while (var4.remaining() >= 4) {
         var5[0] = var4.get();
         var5[1] = var4.get();
         var5[2] = var4.get();
         var5[3] = var4.get();
         if (var5[0] != 0) {
            if (var5[1] == 0 && var5[2] != 0 && var5[3] == 0) {
               var3 = setCs(csUTF16LE, var3);
               if (var3 == null) {
                  break;
               }
            } else if (var5[1] == 0 && var5[2] == 0 && var5[3] == 0) {
               var3 = setCs(csUTF32LE, var3);
               if (var3 == null) {
                  break;
               }
            } else if (var5[1] == 0 || var5[2] == 0 || var5[3] == 0) {
               if (var5[0] == -1 && var5[1] == -2 && var5[2] != 0 && var5[3] == 0) {
                  var3 = setCs(csUTF16, var3);
                  if (var3 == null) {
                     break;
                  }
               } else if (var5[0] == -2 && var5[1] == -1 && var5[2] == 0 && var5[3] != 0) {
                  var3 = setCs(csUTF16, var3);
                  if (var3 == null) {
                     break;
                  }
               }
            } else if (Characters.isAsciiChar(var5[0]) && Characters.isAsciiChar(var5[1]) && Characters.isAsciiChar(var5[2]) && Characters.isAsciiChar(var5[3])
               )
             {
               var3 = setCs(csASCII, var3);
               if (var3 == null) {
                  break;
               }
            } else {
               int var6 = EndianUtil.littleEndianBytesToInt(var5);
               if (var6 != -1) {
                  var3 = setCs(csUTF8, var3);
                  if (var3 == null) {
                     break;
                  }
               }
            }
         } else if (var5[1] != 0 && var5[2] == 0 && var5[3] != 0) {
            var3 = setCs(csUTF16BE, var3);
            if (var3 == null) {
               break;
            }
         } else if (var5[1] == 0 && var5[2] == 0 && var5[3] != 0) {
            var3 = setCs(csUTF32BE, var3);
            if (var3 == null) {
               break;
            }
         }
      }

      return var3;
   }

   private static Charset setCs(Charset var0, Charset var1) {
      return var1 != null && var1 != var0 ? null : var0;
   }

   public static boolean isNumber(String var0) {
      for (int var1 = 0; var1 < var0.length(); var1++) {
         if (!Character.isDigit(var0.charAt(var1))) {
            return false;
         }
      }

      return true;
   }

   public static boolean isHexNumber(String var0) {
      Boolean var1 = null;

      for (int var2 = 0; var2 < var0.length(); var2++) {
         char var3 = var0.charAt(var2);
         if (!Character.isDigit(var3)) {
            if (var3 >= 'A' && var3 <= 'F') {
               if (Boolean.FALSE.equals(var1)) {
                  return false;
               }

               var1 = Boolean.TRUE;
            } else if (var3 >= 'a' && var3 <= 'f') {
               if (Boolean.TRUE.equals(var1)) {
                  return false;
               }

               var1 = Boolean.FALSE;
            }
         }
      }

      return true;
   }

   public static String f(String var0, Object... var1) {
      return ff(Locale.US, var0, var1);
   }

   public static Appendable ff(Locale var0, Appendable var1, String var2, Object... var3) {
      boolean var4 = false;

      try {
         StringBuilder var5 = new StringBuilder(var2.length() + var3.length * 16);
         int var6 = 0;

         int var7;
         label240:
         for (var7 = 0; var6 < var2.length(); var6++) {
            char var8 = var2.charAt(var6);
            if (var8 != '%') {
               var5.append(var8);
            } else {
               if (var6 + 1 == var2.length()) {
                  var4 = true;
                  break;
               }

               char var9 = var2.charAt(var6 + 1);
               var6++;
               switch (var9) {
                  case '%':
                     var5.append('%');
                     break;
                  case 'X':
                     Object var27 = var3[var7++];
                     if (var27 instanceof Byte) {
                        var5.append(Integer.toHexString((Byte)var27 & 255).toUpperCase());
                     } else if (var27 instanceof Short) {
                        var5.append(Integer.toHexString((Short)var27 & '\uffff').toUpperCase());
                     } else if (var27 instanceof Integer) {
                        var5.append(Integer.toHexString((Integer)var27).toUpperCase());
                     } else {
                        if (!(var27 instanceof Long)) {
                           var4 = true;
                           break label240;
                        }

                        var5.append(Long.toHexString((Long)var27).toUpperCase());
                     }
                     break;
                  case 'b':
                     Object var26 = var3[var7++];
                     var5.append(((Boolean)var26).booleanValue());
                     break;
                  case 'c':
                     Object var29 = var3[var7++];
                     char var25;
                     if (var29 instanceof Character) {
                        var25 = (Character)var29;
                     } else if (var29 instanceof Byte) {
                        var25 = (char)((Byte)var29).intValue();
                     } else if (var29 instanceof Short) {
                        var25 = (char)((Short)var29).intValue();
                     } else {
                        if (!(var29 instanceof Integer)) {
                           var4 = true;
                           break label240;
                        }

                        var25 = (char)((Integer)var29).intValue();
                     }

                     var5.append(var25);
                     break;
                  case 'd':
                     Object var24 = var3[var7++];
                     if (var24 instanceof Byte) {
                        var5.append(((Byte)var24).byteValue());
                     } else if (var24 instanceof Short) {
                        var5.append(((Short)var24).shortValue());
                     } else if (var24 instanceof Integer) {
                        var5.append(((Integer)var24).intValue());
                     } else {
                        if (!(var24 instanceof Long)) {
                           var4 = true;
                           break label240;
                        }

                        var5.append(((Long)var24).longValue());
                     }
                     break;
                  case 'n':
                     var5.append(LINESEP);
                     break;
                  case 's':
                     Object var23 = var3[var7++];
                     if (var23 instanceof String) {
                        var5.append((String)var23);
                     } else {
                        var5.append(var23);
                     }
                     break;
                  case 'x':
                     Object var22 = var3[var7++];
                     if (var22 instanceof Byte) {
                        var5.append(Integer.toHexString((Byte)var22 & 255));
                     } else if (var22 instanceof Short) {
                        var5.append(Integer.toHexString((Short)var22 & '\uffff'));
                     } else if (var22 instanceof Integer) {
                        var5.append(Integer.toHexString((Integer)var22));
                     } else {
                        if (!(var22 instanceof Long)) {
                           var4 = true;
                           break label240;
                        }

                        var5.append(Long.toHexString((Long)var22));
                     }
                     break;
                  default:
                     if (var9 == '0') {
                        if (var6 + 1 == var2.length()) {
                           var4 = true;
                           break label240;
                        }

                        char var10 = var2.charAt(var6 + 1);
                        var6++;
                        if (var10 != '2' && var10 != '4' && var10 != '8' || var6 + 1 >= var2.length()) {
                           var4 = true;
                           break label240;
                        }

                        char var11 = var2.charAt(var6 + 1);
                        var6++;
                        Object var12 = var3[var7++];
                        String var13 = null;
                        if (var11 == 'x') {
                           if (var12 instanceof Byte) {
                              var13 = Integer.toHexString((Byte)var12 & 255);
                           } else if (var12 instanceof Short) {
                              var13 = Integer.toHexString((Short)var12 & '\uffff');
                           } else if (var12 instanceof Integer) {
                              var13 = Integer.toHexString((Integer)var12);
                           } else if (var12 instanceof Long) {
                              var13 = Long.toHexString((Long)var12);
                           }
                        } else if (var11 == 'X') {
                           if (var12 instanceof Byte) {
                              var13 = Integer.toHexString((Byte)var12 & 255).toUpperCase();
                           } else if (var12 instanceof Short) {
                              var13 = Integer.toHexString((Short)var12 & '\uffff').toUpperCase();
                           } else if (var12 instanceof Integer) {
                              var13 = Integer.toHexString((Integer)var12).toUpperCase();
                           } else if (var12 instanceof Long) {
                              var13 = Long.toHexString((Long)var12).toUpperCase();
                           }
                        }

                        if (var13 == null) {
                           var4 = true;
                           break label240;
                        }

                        int var14 = var10 - '0';
                        if (var13.length() < var14) {
                           for (int var15 = var13.length(); var15 < var14; var15++) {
                              var5.append('0');
                           }
                        }

                        var5.append(var13);
                     } else {
                        boolean var21 = false;
                        if (var9 == '-') {
                           var21 = true;
                           if (var6 + 1 == var2.length()) {
                              var4 = true;
                              break label240;
                           }

                           var9 = var2.charAt(var6 + 1);
                           var6++;
                        }

                        int var28;
                        for (var28 = 0; var9 >= '0' && var9 <= '9' && var6 + 1 < var2.length(); var6++) {
                           var28 = var28 * 10 + (var9 - '0');
                           var9 = var2.charAt(var6 + 1);
                        }

                        if (var9 != 's') {
                           var4 = true;
                           break label240;
                        }

                        Object var30 = var3[var7++];
                        String var31 = var30 == null ? "null" : var30.toString();
                        if (var31.length() >= var28) {
                           var5.append(var31);
                        } else if (!var21) {
                           for (int var32 = var31.length(); var32 < var28; var32++) {
                              var5.append(' ');
                           }

                           var5.append(var31);
                        } else {
                           var5.append(var31);

                           for (int var33 = var31.length(); var33 < var28; var33++) {
                              var5.append(' ');
                           }
                        }
                     }
               }
            }
         }

         if (!var4) {
            if (var3.length != var7) {
               throw new RuntimeException("Not all format parameters were used! format=" + var2 + ", params=" + Arrays.toString(var3));
            }

            if (var1 == null) {
               return var5;
            }

            var1.append(var5);
            return var1;
         }
      } catch (IOException var17) {
         throw new RuntimeException(var17);
      } catch (Exception var18) {
      }

      String var19 = String.format(var0, var2, var3);
      if (var1 == null) {
         return new StringBuilder(var19);
      } else {
         try {
            var1.append(var19);
            return var1;
         } catch (IOException var16) {
            throw new RuntimeException(var16);
         }
      }
   }

   public static Appendable ff(Appendable var0, String var1, Object... var2) {
      return ff((Locale)null, var0, var1, var2);
   }

   public static String ff(Locale var0, String var1, Object... var2) {
      return ff(var0, (Appendable)null, var1, var2).toString();
   }

   public static String ff(String var0, Object... var1) {
      return ff((Locale)null, (Appendable)null, var0, var1).toString();
   }

   public static String replaceLast(String var0, String var1, String var2) {
      if (!isBlank(var0) && !isBlank(var1)) {
         int var3 = var0.lastIndexOf(var1);
         return var3 >= 0 ? var0.substring(0, var3) + var2 + var0.substring(var3 + var1.length()) : var0;
      } else {
         return var0;
      }
   }

   public static String substring(String var0, int var1, int var2) {
      if (var1 < 0) {
         var1 += var0.length();
      }

      if (var2 < 0) {
         var2 += var0.length();
      }

      return var0.substring(var1, var2);
   }

   public static String truncate(String var0, int var1) {
      return var0.length() <= var1 ? var0 : var0.substring(0, var1);
   }

   public static String truncateWithSuffix(String var0, int var1, String var2) {
      if (var2 == null) {
         var2 = "";
      } else if (var2.length() > var1) {
         throw new IllegalArgumentException();
      }

      return var0.length() <= var1 ? var0 : var0.substring(0, var1 - var2.length()) + var2;
   }

   public static String indentBlock(String var0, String var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (String var7 : splitLines(var0, true)) {
         if (var3 >= 1) {
            var2.append('\n');
         }

         var2.append(var1 + var7);
         var3++;
      }

      return var2.toString();
   }

   public static String indentBlock(String var0) {
      return indentBlock(var0, "    ");
   }

   public static String urlencodeUTF8(String var0) {
      try {
         return URLEncoder.encode(var0, "UTF-8");
      } catch (UnsupportedEncodingException var2) {
         throw new RuntimeException(var2);
      }
   }

   public static String urldecodeUTF8(String var0) {
      try {
         return URLDecoder.decode(var0, "UTF-8");
      } catch (UnsupportedEncodingException var2) {
         throw new RuntimeException(var2);
      }
   }

   public static String[] parseUrlParameters(String var0, String... var1) {
      String[] var2 = var0.split("&");
      if (var2.length != var1.length) {
         return null;
      } else {
         String[] var3 = new String[var2.length];
         int var4 = 0;

         for (String var8 : var1) {
            String[] var9 = var2[var4].split("=");
            if (var9.length != 2 || !var8.equals(var9[0])) {
               return null;
            }

            var3[var4] = var9[1];
            var4++;
         }

         return var3;
      }
   }

   public static String parseUrlParameter(String var0, String var1) {
      return parseUrlParameters(var0, var1)[0];
   }

   public static String encodeArray(Object... var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (Object var6 : var0) {
         if (var2 > 0) {
            var1.append(",");
         }

         var1.append(urlencodeUTF8(var6.toString()));
         var2++;
      }

      return var1.toString();
   }

   public static String[] decodeArray(String var0) {
      String[] var1 = var0.split(",");
      String[] var2 = new String[var1.length];
      int var3 = 0;

      for (String var7 : var1) {
         var2[var3] = urldecodeUTF8(var7);
         var3++;
      }

      return var2;
   }

   public static String encodeList(List var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (Object var4 : var0) {
         if (var2 > 0) {
            var1.append(",");
         }

         var1.append(urlencodeUTF8(var4.toString()));
         var2++;
      }

      return var1.toString();
   }

   public static List decodeList(String var0) {
      ArrayList var1 = new ArrayList();
      if (var0 != null) {
         for (String var5 : var0.split(",")) {
            var1.add(urldecodeUTF8(var5));
         }
      }

      return var1;
   }

   public static String encodeMap(Map var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (Object var4 : var0.keySet()) {
         if (var2 > 0) {
            var1.append('&');
         }

         var1.append(urlencodeUTF8(var4.toString()) + "=" + urlencodeUTF8(var0.get(var4).toString()));
         var2++;
      }

      return var1.toString();
   }

   public static Map decodeMap(String var0) {
      LinkedHashMap var1 = new LinkedHashMap();
      if (var0 != null) {
         for (String var5 : var0.split("&")) {
            String[] var6 = var5.split("=");
            if (var6.length == 2) {
               var1.put(urldecodeUTF8(var6[0]), urldecodeUTF8(var6[1]));
            }
         }
      }

      return var1;
   }

   public static byte[] encodeUTF8(String var0) {
      try {
         return var0.getBytes("UTF-8");
      } catch (UnsupportedEncodingException var1) {
         return var0.getBytes(Charset.defaultCharset());
      }
   }

   public static String decodeUTF8(byte[] var0, int var1, int var2) {
      try {
         return new String(var0, var1, var2, "UTF-8");
      } catch (UnsupportedEncodingException var3) {
         return new String(var0, var1, var2, Charset.defaultCharset());
      }
   }

   public static String decodeUTF8(byte[] var0) {
      return decodeUTF8(var0, 0, var0.length);
   }

   public static byte[] encodeASCII(String var0) {
      try {
         return var0.getBytes("US-ASCII");
      } catch (UnsupportedEncodingException var1) {
         return var0.getBytes(Charset.defaultCharset());
      }
   }

   public static String decodeASCII(byte[] var0, int var1, int var2) {
      try {
         return new String(var0, var1, var2, "US-ASCII");
      } catch (UnsupportedEncodingException var3) {
         return new String(var0, var1, var2, Charset.defaultCharset());
      }
   }

   public static String decodeASCII(byte[] var0) {
      return decodeASCII(var0, 0, var0.length);
   }

   public static byte[] encodeLocal(String var0) {
      return var0.getBytes(Charset.defaultCharset());
   }

   public static String decodeLocal(byte[] var0, int var1, int var2) {
      return new String(var0, var1, var2, Charset.defaultCharset());
   }

   public static String decodeLocal(byte[] var0) {
      return decodeLocal(var0, 0, var0.length);
   }

   public static byte[] encodeBinary(String var0) {
      char[] var1 = var0.toCharArray();
      byte[] var2 = new byte[var1.length];
      int var3 = 0;

      for (char var7 : var1) {
         var2[var3++] = (byte)(var7 & 255);
      }

      return var2;
   }

   public static Comparator getComparator() {
      return getComparator(true, true);
   }

   public static Comparator getComparator(boolean var0, boolean var1) {
      return new NumberComparator(new AlphanumCharComparator(var0), var1);
   }

   public static void makeNewLine(StringBuilder var0) {
      int var1 = var0.length();
      if (var1 != 0) {
         char var2 = var0.charAt(var1 - 1);
         if (var2 != '\r' && var2 != '\n') {
            var0.append(LINESEP);
         }
      }
   }

   public static String randomUniqueId() {
      byte[] var0 = (prng.nextLong() + "-thxeg73z5tdhgs9c920klaph-" + System.nanoTime()).getBytes(csASCII);
      return Formatter.byteArrayToHexString(Hash.calculateSHA256(var0), 0, 16).toLowerCase();
   }

   public static CharSequence pad(char var0, int var1) {
      if (var1 <= 0) {
         return "";
      } else if (var1 == 1) {
         return String.valueOf(var0);
      } else {
         StringBuilder var2 = new StringBuilder();

         for (int var3 = 0; var3 < var1; var3++) {
            var2.append(var0);
         }

         return var2;
      }
   }

   public static String capitalizeFirst(String var0) {
      if (var0.isEmpty()) {
         return var0;
      } else {
         char var1 = var0.charAt(0);
         return Character.isUpperCase(var1) ? var0 : Character.toUpperCase(var1) + var0.substring(1);
      }
   }

   public static String camelCaseToString(String var0, boolean var1, boolean var2) throws ParseException {
      StringBuilder var3 = new StringBuilder();
      boolean var4 = false;
      boolean var5 = false;

      for (int var6 = 0; var6 < var0.length(); var6++) {
         char var7 = var0.charAt(var6);
         if (var6 == 0 && !Character.isUpperCase(var7) || Character.isWhitespace(var7)) {
            throw new ParseException("Not a legal camel-case string", var6);
         }

         if (Character.isUpperCase(var7)) {
            if (var6 > 0 && !var5) {
               var3.append(' ');
            }

            boolean var8 = false;
            if (var2) {
               if (var5) {
                  if (var6 + 1 < var0.length()
                     && Character.isUpperCase(var0.charAt(var6 + 1))
                     && (var6 + 2 == var0.length() || Character.isUpperCase(var0.charAt(var6 + 2)))) {
                     var8 = true;
                  }
               } else if (var6 + 1 < var0.length()
                  && Character.isUpperCase(var0.charAt(var6 + 1))
                  && (var6 + 2 == var0.length() || Character.isUpperCase(var0.charAt(var6 + 2)))) {
                  var5 = true;
                  var8 = true;
               }
            }

            var3.append(var6 != 0 && !var5 ? Character.toLowerCase(var7) : var7);
            var4 = false;
            var5 = var8;
         } else if (var6 > 0 && var1 && Character.isDigit(var7) && !var4) {
            var3.append(' ');
            var3.append(var7);
            var4 = true;
            var5 = false;
         } else {
            var3.append(var7);
            var5 = false;
         }
      }

      return var3.toString();
   }

   public static String camelCaseToString(String var0) throws ParseException {
      return camelCaseToString(var0, false, false);
   }

   public static boolean hasRtl(CharSequence var0) {
      for (int var1 = 0; var1 < var0.length(); var1++) {
         char var2 = var0.charAt(var1);
         byte var3 = Character.getDirectionality(var2);
         if (var3 == 1 || var3 == 2) {
            return true;
         }
      }

      return false;
   }

   public static String[] parseCommandline(String var0) {
      if (var0 != null && var0.length() != 0) {
         byte var1 = 0;
         StringTokenizer var2 = new StringTokenizer(var0, "\"' ", true);
         ArrayList var3 = new ArrayList();
         StringBuilder var4 = new StringBuilder();
         boolean var5 = false;

         while (var2.hasMoreTokens()) {
            String var6 = var2.nextToken();
            switch (var1) {
               case 1:
                  if ("'".equals(var6)) {
                     var5 = true;
                     var1 = 0;
                  } else {
                     var4.append(var6);
                  }
                  continue;
               case 2:
                  if ("\"".equals(var6)) {
                     var5 = true;
                     var1 = 0;
                  } else {
                     var4.append(var6);
                  }
                  continue;
            }

            if ("'".equals(var6)) {
               var1 = 1;
            } else if ("\"".equals(var6)) {
               var1 = 2;
            } else if (" ".equals(var6)) {
               if (var5 || var4.length() != 0) {
                  var3.add(var4.toString());
                  var4.setLength(0);
               }
            } else {
               var4.append(var6);
            }

            var5 = false;
         }

         if (var5 || var4.length() != 0) {
            var3.add(var4.toString());
         }

         return var1 != 1 && var1 != 2 ? (String[])var3.toArray(new String[var3.size()]) : null;
      } else {
         return new String[0];
      }
   }

   public static boolean isWellFormedUTF8(byte[] var0) {
      return isWellFormedUTF8(var0, 0, var0.length);
   }

   public static boolean isWellFormedUTF8(byte[] var0, int var1, int var2) {
      String var3 = new String(var0, var1, var2, csUTF8);
      return ArrayUtil.equalsBytes(var3.getBytes(csUTF8), 0, var0, var1, var2);
   }

   public static boolean isPrintableUTF8Header(byte[] var0) {
      return isPrintableCharsetHeader(var0, csUTF8);
   }

   public static boolean isPrintableCharsetHeader(byte[] var0, Charset var1) {
      CharsetDecoder var2 = var1.newDecoder();

      try {
         CharBuffer var3 = var2.decode(ByteBuffer.wrap(var0));
         return validateCharBuffer(var3);
      } catch (MalformedInputException var8) {
         int var4 = var8.getInputLength();
         byte[] var5 = new byte[var0.length - var4];
         System.arraycopy(var0, 0, var5, 0, var5.length);

         try {
            CharBuffer var6 = var2.decode(ByteBuffer.wrap(var5));
            return validateCharBuffer(var6);
         } catch (CharacterCodingException var7) {
         }
      } catch (IOException var9) {
      }

      return false;
   }

   private static boolean validateCharBuffer(CharBuffer var0) {
      while (var0.hasRemaining()) {
         char var1 = var0.get();
         if (!Characters.isAsciiChar(var1) && !Character.isAlphabetic(var1) && !Character.isWhitespace(var1)) {
            switch (Character.getType(var1)) {
               case 9:
               case 10:
               case 11:
               case 12:
               case 13:
               case 14:
               case 16:
               case 20:
               case 21:
               case 22:
               case 23:
               case 24:
               case 25:
               case 26:
               case 27:
               case 28:
               case 29:
               case 30:
                  break;
               case 15:
               case 17:
               case 18:
               case 19:
               default:
                  return false;
            }
         }
      }

      return true;
   }

   public static String decodeUTF8Ex(byte[] var0, boolean var1) {
      return decodeUTF8Ex(var0, 0, var0.length, var1);
   }

   public static String decodeUTF8Ex(byte[] var0, int var1, int var2, boolean var3) {
      String var4 = null;
      if (var3) {
         var4 = new String(var0, var1, var2, csUTF8);
         if (ArrayUtil.equalsBytes(var4.getBytes(csUTF8), 0, var0, var1, var2)) {
            return var4;
         }
      }

      try {
         StringBuilder var5 = new StringBuilder(var2);
         int var6 = var1;

         while (var6 < var1 + var2) {
            int var7 = var0[var6++] & 255;
            int var8;
            if ((var7 & 128) == 0) {
               var8 = var7 & 127;
            } else if ((var7 & 224) == 192) {
               int var9 = var0[var6++] & 255;
               if ((var9 & 192) != 128) {
                  throw new IllegalArgumentException();
               }

               var8 = var9 & 63;
               var8 |= (var7 & 31) << 6;
            } else if ((var7 & 240) == 224) {
               int var22 = var0[var6++] & 255;
               if ((var22 & 192) != 128) {
                  throw new IllegalArgumentException();
               }

               int var10 = var0[var6++] & 255;
               if ((var10 & 192) != 128) {
                  throw new IllegalArgumentException();
               }

               var8 = var10 & 63;
               var8 |= (var22 & 63) << 6;
               var8 |= (var7 & 15) << 12;
            } else {
               if ((var7 & 248) != 240) {
                  throw new IllegalArgumentException();
               }

               int var23 = var0[var6++] & 255;
               if ((var23 & 192) != 128) {
                  throw new IllegalArgumentException();
               }

               int var24 = var0[var6++] & 255;
               if ((var24 & 192) != 128) {
                  throw new IllegalArgumentException();
               }

               int var11 = var0[var6++] & 255;
               if ((var11 & 192) != 128) {
                  throw new IllegalArgumentException();
               }

               var8 = var11 & 63;
               var8 |= (var24 & 63) << 6;
               var8 |= (var23 & 63) << 12;
               var8 |= (var7 & 8) << 18;
            }

            if (!Character.isValidCodePoint(var8)) {
               throw new IllegalArgumentException();
            }

            var5.append(Character.toChars(var8));
         }

         return var5.toString();
      } catch (Exception var12) {
         if (var4 == null) {
            var4 = new String(var0, var1, var2, csUTF8);
         }

         return var4;
      }
   }

   public static int getBOMSize(byte[] var0) {
      if (var0.length < 2) {
         return 0;
      } else if (var0[0] == -2 && var0[1] == -1) {
         return 2;
      } else if (var0[0] == -1 && var0[1] == -2) {
         return var0.length >= 4 && var0[2] == 0 && var0[3] == 0 ? 4 : 2;
      } else if (var0.length < 3) {
         return 0;
      } else if (var0[0] == -17 && var0[1] == -69 && var0[2] == -65) {
         return 3;
      } else if (var0.length < 4) {
         return 0;
      } else {
         return var0[0] == 0 && var0[1] == 0 && var0[2] == -2 && var0[3] == -1 ? 4 : 0;
      }
   }

   public static String readBOM(byte[] var0) {
      if (var0.length < 2) {
         return null;
      } else if (var0[0] == -2 && var0[1] == -1) {
         return "UTF-16BE";
      } else if (var0[0] == -1 && var0[1] == -2) {
         return var0.length >= 4 && var0[2] == 0 && var0[3] == 0 ? "UTF-32LE" : "UTF-16LE";
      } else if (var0.length < 3) {
         return null;
      } else if (var0[0] == -17 && var0[1] == -69 && var0[2] == -65) {
         return "UTF-8";
      } else if (var0.length < 4) {
         return null;
      } else if (var0[0] == 0 && var0[1] == 0 && var0[2] == -2 && var0[3] == -1) {
         return "UTF-32BE";
      } else {
         return var0[0] == -1 && var0[1] == -2 && var0[2] == 0 && var0[3] == 0 ? "UTF-32LE" : null;
      }
   }

   public static int getInitialBlankSize(InputStream var0, boolean var1, char... var2) throws IOException {
      int var3 = 0;
      if (var1) {
         byte[] var4 = new byte[Math.min(4, var0.available())];
         var0.read(var4);
         int var5 = getBOMSize(var4);
         var3 += var5;

         for (int var6 = var5; var6 < var4.length; var6++) {
            if (!isAsciiWhitespace(var4[var6], var2)) {
               return var3;
            }

            var3++;
         }
      }

      while (isAsciiWhitespace(var0.read(), var2)) {
         var3++;
      }

      return var3;
   }

   public static int count(String var0, char var1) {
      int var2 = 0;
      int var4 = 0;

      while (true) {
         var4 = var0.indexOf(var1, var4);
         if (var4 < 0) {
            return var2;
         }

         var4++;
         var2++;
      }
   }

   public static int count(String var0, String var1, boolean var2) {
      int var3 = 0;
      int var5 = 0;

      while (true) {
         var5 = var0.indexOf(var1, var5);
         if (var5 < 0) {
            return var3;
         }

         if (var2) {
            var5++;
         } else {
            var5 += var1.length();
         }

         var3++;
      }
   }

   public static boolean like(String var0, String var1) {
      return Pattern.compile(var1).matcher(var0).matches();
   }

   public static boolean likei(String var0, String var1) {
      return Pattern.compile(var1, 2).matcher(var0).matches();
   }

   public static boolean starMatches(String var0, String var1) {
      return new StarMatcher(var1).matches(var0);
   }

   public static int[] findWordBoundaries(String var0, int var1) {
      return findWordBoundaries(var0, var1, null);
   }

   public static int[] findWordBoundaries(String var0, int var1, Predicate var2) {
      if (var1 >= 0 && var1 < var0.length()) {
         if (var2 == null) {
            var2 = Strings::isBound;
         }

         char var3 = var0.charAt(var1);
         boolean var4 = Character.isWhitespace(var3);
         int var5;
         if (var4) {
            var5 = var1;
         } else {
            int var6;
            for (var6 = var1; var6 > 0; var6--) {
               char var7 = var0.charAt(var6 - 1);
               if (var2.test(var7)) {
                  break;
               }
            }

            var5 = var6;
         }

         int var8 = var1;
         if (var4) {
            while (var8 < var0.length()) {
               char var9 = var0.charAt(var8);
               if (!Character.isWhitespace(var9)) {
                  break;
               }

               var8++;
            }
         }

         while (var8 < var0.length()) {
            char var10 = var0.charAt(var8);
            if (var2.test(var10)) {
               break;
            }

            var8++;
         }

         return new int[]{var5, var8};
      } else {
         return new int[]{var1, var1};
      }
   }

   private static boolean isBound(char var0) {
      if (Character.isWhitespace(var0)) {
         return true;
      } else {
         int var1 = Character.getType(var0);
         return var1 == 21 || var1 == 22 || var1 == 24;
      }
   }

   static {
      String var0 = System.getProperty("line.separator");
      if (var0 == null) {
         var0 = ff("%n");
         if (var0 == null) {
            throw new RuntimeException("Cannot determine line separator for this platform");
         }
      }

      LINESEP = var0;
      csASCII = Charset.forName("ASCII");
      csUTF8 = Charset.forName("UTF-8");
      csUTF16 = Charset.forName("UTF-16");
      csUTF16LE = Charset.forName("UTF-16LE");
      csUTF16BE = Charset.forName("UTF-16BE");
      csUTF32LE = Charset.forName("UTF-32LE");
      csUTF32BE = Charset.forName("UTF-32BE");
      unicodeWhiteSpaces = new HashSet();
      unicodeWhiteSpaces.add('\t');
      unicodeWhiteSpaces.add('\n');
      unicodeWhiteSpaces.add('\u000b');
      unicodeWhiteSpaces.add('\f');
      unicodeWhiteSpaces.add('\r');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add('\u0085');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add('\u2028');
      unicodeWhiteSpaces.add('\u2029');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add(' ');
      unicodeWhiteSpaces.add('　');
      prng = new Random();
   }
}
