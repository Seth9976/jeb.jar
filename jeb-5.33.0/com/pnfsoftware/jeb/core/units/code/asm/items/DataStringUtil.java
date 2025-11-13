package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.google.common.base.Strings;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeDataAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Characters;
import com.pnfsoftware.jebglobal.avb;
import com.pnfsoftware.jebglobal.yj;
import java.lang.Character.UnicodeBlock;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class DataStringUtil {
   private static final ILogger logger = GlobalLog.getLogger(DataStringUtil.class);
   public static final int maxStringItemNameLength = 64;
   public static final String NAME_PREFIX = "a";
   public static final int NAME_DEFAULT_LENGTH = 16;
   private static DataStringUtil.UnicodeRule bopomofo = DataStringUtil.UnicodeRule.starts("BOPOMOFO");
   private static DataStringUtil.UnicodeRule han = DataStringUtil.UnicodeRule.starts("CJK_");
   private static List languages = Arrays.asList(
      new DataStringUtil.UnicodeLanguage(han, bopomofo),
      new DataStringUtil.UnicodeLanguage(han, DataStringUtil.UnicodeRule.eq("HIRAGANA"), DataStringUtil.UnicodeRule.eq("KATAKANA")),
      new DataStringUtil.UnicodeLanguage(han, DataStringUtil.UnicodeRule.starts("HANGUL_"))
   );

   public static boolean isValidStringAt(INativeCodeModel var0, IVirtualMemory var1, long var2, int var4, int var5) {
      return getValidStringAt(var0, var1, var2, var4, var5) != null;
   }

   public static String getValidStringAt(INativeCodeModel var0, IVirtualMemory var1, long var2, int var4, int var5) {
      StringEntry var6 = getStringAt(var1, var2, -1L, var4, var5);
      if (var6 == null) {
         return null;
      } else {
         long var7 = var2 - 1L;
         if (var0 != null) {
            while (true) {
               INativeContinuousItem var9 = var0.getNextItem(var7);
               if (var9 == null || var9.getMemoryAddress() - var2 >= var6.size) {
                  break;
               }

               if (!yj.A(var9)) {
                  return null;
               }

               var7 = var9.getMemoryAddress();
            }
         }

         return var6.str;
      }
   }

   public static String getStringAt(IVirtualMemory var0, long var1, int var3, int var4) {
      return getStringAt(var0, var1, var3, var4, null);
   }

   public static String getStringAt(IVirtualMemory var0, long var1, int var3, int var4, StringEncoding[] var5) {
      StringEntry var6 = getStringItemFromMemory(null, var0, var1, -1L, null, var3, var4);
      if (var6 == null) {
         return null;
      } else {
         if (var5 != null && var5.length > 0) {
            var5[0] = var6.encoding;
         }

         return var6.str;
      }
   }

   public static StringEntry getStringAt(IVirtualMemory var0, long var1, long var3, int var5, int var6) {
      return getStringItemFromMemory(null, var0, var1, var3, null, var5, var6);
   }

   public static INativeStringItem createFromMemory(
      INativeDataAnalyzer var0, ILabelManager var1, IVirtualMemory var2, long var3, long var5, StringEncoding var7, int var8, int var9
   ) {
      StringEntry var10 = getStringItemFromMemory(var0, var2, var3, var5, var7, var8, var9);
      return var10 == null ? null : var10.item;
   }

   private static StringEntry getStringItemFromMemory(
      INativeDataAnalyzer var0, IVirtualMemory var1, long var2, long var4, StringEncoding var6, int var7, int var8
   ) {
      if (var7 < 0) {
         var7 = 3;
      }

      if (var8 <= 0 || var8 > 100000) {
         var8 = 100000;
      }

      if (var7 > var8) {
         return null;
      } else {
         Object var9 = null;
         if (var6 != null) {
            var9 = new ArrayList();
            var9.add(var6);
         }

         boolean var10 = var6 == null;
         if (var10) {
            var9 = heurDetermineStringType(var1, var2);
            if (var9.isEmpty()) {
               return null;
            }
         }

         ArrayList var11 = new ArrayList();

         for (StringEncoding var13 : var9) {
            int var14 = var13.getBasicCharSize();
            int var15 = var8 * var14;
            int var16 = var8;
            long var17 = var2 + var15;
            if (var4 >= 0L) {
               var17 = Math.min(var4, var17);
               var15 = (int)(var17 - var2);
               var16 = var15 / var14;
            }

            Couple var19 = determineSize(var1, var2, var17, var15, var14, var13);
            if (var19 != null) {
               int var20 = (Integer)var19.getFirst();
               var13 = (StringEncoding)var19.getSecond();
               String var21 = determineValue(var13, var1, var2, var20, var10);
               if (var21 != null && var21.length() >= var7 && var21.length() <= var16 && (!var10 || heurIsValidString(var21, var7))) {
                  StringEntry var22 = new StringEntry(var21, var2, var20, var13, null);
                  var11.add(var22);
               }
            }
         }

         if (var11.isEmpty()) {
            return null;
         } else {
            StringEntry var23 = chooseBestStringType(var11);
            if (var0 == null) {
               return var23;
            } else {
               int var25 = var23.encoding.getBasicCharSize();
               ITypeManager var26 = var0.getTypeManager();
               IPrimitiveType var27 = var26.getPrimitives().getIntegerBySize(var25, true);
               IArrayType var28 = var26.createArray(var27, var23.size / var25);
               avb var29 = (avb)var0.createString(var2, var23.size, var28, var23.encoding, null);
               var29.kS(var23.str);
               String var18 = createItemNameFromString(var23.str, 16);
               if (var18 != null) {
                  var29.setName(var18);
               }

               var23.item = var29;
               return var23;
            }
         }
      }
   }

   private static StringEntry chooseBestStringType(List var0) {
      if (var0.size() == 1) {
         return (StringEntry)var0.get(0);
      } else {
         if (((StringEntry)var0.get(0)).encoding == StringEncoding.UTF16_LE_ZERO && ((StringEntry)var0.get(1)).encoding == StringEncoding.ASCII_ZERO) {
            HashSet var1 = new HashSet();
            boolean var2 = false;

            for (int var3 = 0; var3 < ((StringEntry)var0.get(0)).str.length(); var3++) {
               char var4 = ((StringEntry)var0.get(0)).str.charAt(var3);
               if (var4 >= 256) {
                  var2 = true;
                  break;
               }

               if (var1.contains(var4)) {
                  return (StringEntry)var0.get(0);
               }
            }

            Object[] var10000 = new Object[]{((StringEntry)var0.get(0)).str, ((StringEntry)var0.get(1)).str};
            if (var2 && var1.size() <= 2) {
               return (StringEntry)var0.get(1);
            }
         }

         return (StringEntry)var0.get(0);
      }
   }

   private static List heurDetermineStringType(IVirtualMemory var0, long var1) {
      ArrayList var3 = new ArrayList();
      byte[] var4 = new byte[4];
      int var5 = VirtualMemoryUtil.readBytesSafe(var0, var1, 4, var4, 0, 1);
      if (var5 == 4) {
         if (var4[0] != 0) {
            if (var4[1] == 0 && var4[2] != 0 && var4[3] == 0) {
               var3.add(StringEncoding.UTF16_LE_ZERO);
            } else if (var4[0] == -1 && var4[1] == -2 && var4[2] != 0 && var4[3] == 0) {
               var3.add(StringEncoding.UTF16_ZERO);
            } else if (var4[0] == -2 && var4[1] == -1 && var4[2] == 0 && var4[3] != 0) {
               var3.add(StringEncoding.UTF16_ZERO);
            }

            if (Characters.isAsciiChar(var4[0]) && areAsciiCharOrEnd(var4[1], var4[2], var4[3])) {
               var3.add(StringEncoding.ASCII_ZERO);
            } else if (isValidUtf8Start(var4[0], var4[1], var4[2], var4[3])) {
               var3.add(StringEncoding.UTF8_ZERO);
            }
         } else if (var4[1] != 0 && var4[2] == 0 && var4[3] != 0) {
            var3.add(StringEncoding.UTF16_BE_ZERO);
         }
      }

      return var3;
   }

   private static boolean isValidUtf8Start(byte... var0) {
      int var1 = 0;
      int var2 = 0;

      for (byte var6 : var0) {
         int var7 = var6 & 255;
         if (var2 == 0) {
            if (var6 == 0) {
               return true;
            }

            var1 = var7;
            if (var7 >= 0 && var7 <= 127) {
               var2 = 0;
            } else {
               if (var7 < 194 || var7 > 244) {
                  return false;
               }

               var2 = 1;
            }
         } else if (var2 > 0) {
            if (var7 < 80 || var7 > 191) {
               return false;
            }

            if (var1 >= 194 && var1 <= 223) {
               var2 = 0;
            }

            if (var1 >= 224 && var1 <= 239) {
               if (var2 < 2) {
                  var2++;
               } else {
                  var2 = 0;
               }
            }

            if (var1 >= 240 && var1 <= 244) {
               if (var2 < 3) {
                  var2++;
               } else {
                  var2 = 0;
               }
            }
         }
      }

      return true;
   }

   private static boolean areAsciiCharOrEnd(byte... var0) {
      for (byte var4 : var0) {
         if (var4 == 0) {
            return true;
         }

         if (!Characters.isAsciiChar(var4)) {
            return false;
         }
      }

      return false;
   }

   private static Couple determineSize(IVirtualMemory var0, long var1, long var3, int var5, int var6, StringEncoding var7) {
      int var8 = Math.min(var5, 256);
      byte[] var9 = new byte[var8];
      int var10 = 0;
      boolean var11 = false;
      long var12 = var1;

      while (var12 < var3) {
         int var14 = Math.min(var8, (int)(var3 - var12));
         int var15 = VirtualMemoryUtil.readBytesSafe(var0, var12, var14, var9, 0, 1);
         if (var15 <= 0) {
            return null;
         }

         int var16;
         if (var6 == 1) {
            for (var16 = 0; var16 < var15; var16++) {
               if (var9[var16] == 0) {
                  var11 = true;
                  var16++;
                  break;
               }

               if (var7 == StringEncoding.ASCII_ZERO && !Characters.isAsciiCharOrCommonFormat(var9[var16])) {
                  var7 = StringEncoding.UTF8_ZERO;
               }
            }
         } else {
            if (var6 != 2) {
               throw new RuntimeException();
            }

            for (var16 = 0; var16 < var15 - 1; var16 += 2) {
               if (var9[var16] == 0 && var9[var16 + 1] == 0) {
                  var11 = true;
                  var16 += 2;
                  break;
               }
            }
         }

         var10 += var16;
         if (var16 < var15 || var11) {
            break;
         }

         var12 += var15;
      }

      if (!var11 && var7 != null && var7.isZeroTerminated()) {
         return null;
      } else {
         return var10 % var6 != 0 ? null : new Couple(var10, var7);
      }
   }

   public static String createItemNameFromString(String var0, int var1) {
      StringBuilder var2 = new StringBuilder("a");

      for (int var3 = 0; var3 < var0.length() && var3 < 64; var3++) {
         char var4 = var0.charAt(var3);
         if (Character.isLetterOrDigit(var4)) {
            if (var3 == 0) {
               var4 = Character.toUpperCase(var4);
            }

            var2.append(var4);
         } else if (!Character.isIdentifierIgnorable(var4)) {
            if (var3 + 1 < var0.length()) {
               char var5 = var0.charAt(var3 + 1);
               var3++;
               if (var4 == '!' && var5 == '=') {
                  var2.append("NE");
               } else if (var4 == '"' && var5 == '"') {
                  var2.append("STR");
               } else if (var4 == '%' && var5 == '=') {
                  var2.append("ACR");
               } else if (var4 == '&' && var5 == '&') {
                  var2.append("AND");
               } else if (var4 == '&' && var5 == '=') {
                  var2.append("ACA");
               } else if (var4 == '(' && var5 == ')') {
                  var2.append("FUN");
               } else if (var4 == '*' && var5 == '=') {
                  var2.append("ACM");
               } else if (var4 == '+' && var5 == '+') {
                  var2.append("INC");
               } else if (var4 == '+' && var5 == '=') {
                  var2.append("ACC");
               } else if (var4 == '-' && var5 == '-') {
                  var2.append("DEC");
               } else if (var4 == '-' && var5 == '=') {
                  var2.append("ACS");
               } else if (var4 == '-' && var5 == '>') {
                  var2.append("PTR");
               } else if (var4 == '/' && var5 == '=') {
                  var2.append("ACD");
               } else if (var4 == '<' && var5 == '<') {
                  var2.append("SL");
               } else if (var4 == '<' && var5 == '=') {
                  var2.append("LE");
               } else if (var4 == '=' && var5 == '=') {
                  var2.append("EQ");
               } else if (var4 == '>' && var5 == '>') {
                  var2.append("SR");
               } else if (var4 == '>' && var5 == '=') {
                  var2.append("GE");
               } else if (var4 == '[' && var5 == ']') {
                  var2.append("AR");
               } else if (var4 == '^' && var5 == '=') {
                  var2.append("ACX");
               } else if (var4 == '|' && var5 == '|') {
                  var2.append("OR");
               } else if (var4 == '|' && var5 == '=') {
                  var2.append("ACO");
               } else {
                  var2.append('_');
                  var3--;
               }
            } else {
               var2.append('_');
            }
         }

         if (var1 >= 0 && var2.length() >= var1) {
            while (var2.length() > var1) {
               var2.deleteCharAt(var2.length() - 1);
            }
            break;
         }
      }

      return var2.toString();
   }

   public static String determineValue(INativeStringItem var0, IVirtualMemory var1, boolean var2) {
      long var3 = var0.getMemoryAddress();
      int var5 = (int)var0.getMemorySize();
      byte[] var6 = new byte[var5];
      int var7 = VirtualMemoryUtil.readBytesSafe(var1, var3, var5, var6, 0, 1);
      return var7 < var5 ? null : determineValue(var0.getStringType(), var6, 0, var5, var2);
   }

   public static String determineValue(StringEncoding var0, IVirtualMemory var1, long var2, int var4, boolean var5) {
      byte[] var6 = new byte[var4];
      int var7 = VirtualMemoryUtil.readBytesSafe(var1, var2, var4, var6, 0, 1);
      return var7 < var4 ? null : determineValue(var0, var6, 0, var4, var5);
   }

   public static String determineValue(StringEncoding var0, byte[] var1, int var2, int var3, boolean var4) {
      int var5 = var3 - (var0.isZeroTerminated() ? var0.getBasicCharSize() : 0);
      Charset var6 = var0.getCharset();
      String var7 = new String(var1, var2, var5, var6);
      if (var4) {
         byte[] var8 = var7.getBytes(var6);
         if (var8.length != var5 || ArrayUtil.compareBytes(var8, 0, var1, var2, var5) != 0) {
            return null;
         }
      }

      return var7;
   }

   public static boolean heurIsValidString(String var0, int var1) {
      HashMap var2 = new HashMap();
      HashSet var3 = new HashSet();
      int var4 = 0;

      for (int var5 = 0; var5 < var0.length(); var5++) {
         char var6 = var0.charAt(var5);
         if ((var6 < 'a' || var6 > 'z') && (var6 < 'A' || var6 > 'Z')) {
            if ((var6 < ' ' || var6 > '~') && var6 != '\r' && var6 != '\n' && var6 != '\t') {
               int var10 = Character.getType(var6);
               switch (var10) {
                  case 1:
                  case 2:
                  case 3:
                  case 4:
                  case 5:
                  case 9:
                  case 12:
                  case 20:
                  case 21:
                  case 22:
                  case 23:
                  case 24:
                  case 25:
                  case 26:
                  case 27:
                     UnicodeBlock var11 = UnicodeBlock.of(var6);
                     Integer var9 = (Integer)var2.get(var11);
                     var2.put(var11, var9 == null ? 1 : var9 + 1);
                     break;
                  case 6:
                  case 7:
                  case 8:
                  case 10:
                  case 11:
                  case 13:
                  case 14:
                  case 15:
                  case 16:
                  case 17:
                  case 18:
                  case 19:
                  default:
                     return false;
                  case 28:
                     if (var6 != 169 && var6 != 174 && (var6 != 166 && var6 != 176 || var5 <= 2)) {
                        return false;
                     }
               }

               var3.add(var6);
            } else {
               var4++;
            }
         } else {
            UnicodeBlock var7 = UnicodeBlock.BASIC_LATIN;
            Integer var8 = (Integer)var2.get(var7);
            var2.put(var7, var8 == null ? 1 : var8 + 1);
            var4++;
         }
      }

      if (var3.size() + var4 < var1) {
         return false;
      } else {
         return var2.isEmpty() ? true : testCompatibleUnicodeBlocks(var2, var1);
      }
   }

   private static boolean testCompatibleUnicodeBlocks(Map var0, int var1) {
      boolean var2 = var0.remove(UnicodeBlock.BASIC_LATIN) != null;
      var0.remove(UnicodeBlock.CURRENCY_SYMBOLS);
      var0.remove(UnicodeBlock.SPACING_MODIFIER_LETTERS);
      var0.remove(UnicodeBlock.GENERAL_PUNCTUATION);
      var0.remove(UnicodeBlock.GENERAL_PUNCTUATION);
      var0.remove(UnicodeBlock.SUPPLEMENTAL_PUNCTUATION);
      if (var0.isEmpty()) {
         return true;
      } else if (!var2 && var0.containsKey(UnicodeBlock.LATIN_1_SUPPLEMENT)) {
         return false;
      } else {
         Optional var3 = var0.values().stream().reduce(Integer::sum);
         if (!var3.isPresent() || (Integer)var3.get() < var1) {
            return false;
         } else if (var0.size() <= 1) {
            return true;
         } else {
            String var4 = null;

            for (UnicodeBlock var6 : var0.keySet()) {
               if (var4 == null) {
                  var4 = var6.toString();
               } else {
                  var4 = Strings.commonPrefix(var4, var6.toString());
                  if (var4.isEmpty()) {
                     break;
                  }
               }
            }

            if (var4.length() < 5 && !var4.startsWith("YI") && !var4.startsWith("CJK_")) {
               for (DataStringUtil.UnicodeLanguage var8 : languages) {
                  if (var8.isValid(var0.keySet())) {
                     return true;
                  }
               }

               return false;
            } else {
               return true;
            }
         }
      }
   }

   public static boolean isSafeAsciiStringAt(IVirtualMemory var0, long var1, int var3, int var4) {
      return isSafeAsciiStringAt(var0, var1, StringEncoding.ASCII_ZERO, var3, var4);
   }

   public static boolean isSafeAsciiStringAt(IVirtualMemory var0, long var1, StringEncoding var3, int var4, int var5) {
      if (var5 > 0 && var3 != null) {
         int var6 = var3.getBasicCharSize();
         int var7 = var5 * var6;
         byte[] var8 = new byte[var7];
         var7 += var3.isZeroTerminated() ? var6 : 0;
         Couple var9 = determineSize(var0, var1, var1 + var7, var7, var6, var3);
         if (var9 != null && var9.getSecond() == var3 && (Integer)var9.getFirst() - (var3.isZeroTerminated() ? var6 : 0) >= var4 * var6) {
            int var10 = (Integer)var9.getFirst() - var6;
            int var11 = VirtualMemoryUtil.readBytesSafe(var0, var1, var10, var8, 0, 1);
            if (var11 < 1) {
               return false;
            } else {
               boolean var12 = true;
               if (var3 == StringEncoding.UTF16_ZERO) {
                  if (var10 < 2) {
                     return false;
                  }

                  if (var8[0] == -2 && var8[1] == -1) {
                     var12 = false;
                  } else {
                     if (var8[0] != -1 || var8[1] != -2) {
                        return false;
                     }

                     var12 = true;
                  }
               }

               for (int var13 = var3 == StringEncoding.UTF16_ZERO ? 2 : 0; var13 < var10; var13++) {
                  byte var14 = var8[var13];
                  if (var6 == 2 && var13 + 1 < var10) {
                     byte var15 = var8[var13 + 1];
                     if (var3 == StringEncoding.UTF16_BE_ZERO || !var12) {
                        var14 = var8[var13 + 1];
                        var15 = var8[var13];
                     }

                     if (var15 != 0) {
                        return false;
                     }

                     var13++;
                  }

                  if (!Characters.isAsciiCharOrCommonFormat(var14)) {
                     return false;
                  }
               }

               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean isSafeAsciiCharArrayAt(IVirtualMemory var0, long var1, int var3) {
      if (var3 <= 0) {
         return false;
      } else {
         byte[] var4 = new byte[var3];
         int var5 = VirtualMemoryUtil.readBytesSafe(var0, var1, var3, var4, 0, 1);
         if (var5 < 1) {
            return false;
         } else {
            for (int var6 = 0; var6 < var3; var6++) {
               byte var7 = var4[var6];
               if (!Characters.isAsciiCharOrCommonFormat(var7)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public static boolean isValidCharAt(IVirtualMemory var0, long var1, long var3, StringEncoding var5) {
      int var6 = var3 < var1 ? 4 : (int)Math.min(4L, var3 - var1);
      byte[] var7 = new byte[var6];
      int var8 = VirtualMemoryUtil.readBytesSafe(var0, var1, var6, var7, 0, 1);
      if (var8 < 1) {
         return false;
      } else {
         int var9 = 1;
         switch (var5) {
            case UTF16_ZERO:
               var9 = 2;
               break;
            case UTF16_LE_ZERO:
            case UTF16_BE_ZERO:
               byte var12 = var7[var5 == StringEncoding.UTF16_BE_ZERO ? 0 : 1];
               var9 = (var12 & 248) >>> 3 == 11 ? 4 : 2;
               break;
            case UTF8_ZERO:
            case UTF8_NONZERO:
            default:
               if ((var7[0] & 128) != 0) {
                  int var10 = ((var7[0] & 240) >>> 4) + 1;
                  if ((var10 & 14) == 12) {
                     var9 = 2;
                  } else if (var10 == 14) {
                     var9 = 3;
                  } else {
                     if (var10 != 15) {
                        return false;
                     }

                     var9 = 4;
                  }
               }
         }

         try {
            String var13 = new String(var7, 0, var9, var5.getCharset());
            return Character.isDefined(var13.charAt(0));
         } catch (Exception var11) {
            return false;
         }
      }
   }

   private static class UnicodeLanguage {
      List unicodeRules;

      UnicodeLanguage(DataStringUtil.UnicodeRule... var1) {
         this.unicodeRules = Arrays.asList(var1);
      }

      public boolean isValid(Set var1) {
         for (UnicodeBlock var3 : var1) {
            if (!this.isValid(var3.toString())) {
               return false;
            }
         }

         return true;
      }

      private boolean isValid(String var1) {
         for (DataStringUtil.UnicodeRule var3 : this.unicodeRules) {
            if (var3.match) {
               if (var1.equals(var3.name)) {
                  return true;
               }
            } else if (var3.matchStart && var1.startsWith(var3.name)) {
               return true;
            }
         }

         return false;
      }
   }

   private static class UnicodeRule {
      String name;
      boolean match = false;
      boolean matchStart = false;

      static DataStringUtil.UnicodeRule starts(String var0) {
         DataStringUtil.UnicodeRule var1 = new DataStringUtil.UnicodeRule();
         var1.name = var0;
         var1.matchStart = true;
         return var1;
      }

      static DataStringUtil.UnicodeRule eq(String var0) {
         DataStringUtil.UnicodeRule var1 = new DataStringUtil.UnicodeRule();
         var1.name = var0;
         var1.match = true;
         return var1;
      }
   }
}
