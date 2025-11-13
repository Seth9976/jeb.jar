package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.units.IBinaryUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotation;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationElement;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugInfo;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexFieldData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.corei.parsers.dex.HE;
import com.pnfsoftware.jeb.corei.parsers.dex.Sv;
import com.pnfsoftware.jeb.corei.parsers.dex.vi;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DexUtil {
   private static final ILogger logger = GlobalLog.getLogger(DexUtil.class);
   private static int[] flconv = new int[]{
      1,
      1,
      2,
      2,
      4,
      4,
      8,
      8,
      16,
      16,
      32,
      32,
      64,
      64,
      64,
      64,
      128,
      128,
      128,
      128,
      256,
      256,
      512,
      512,
      1024,
      1024,
      2048,
      2048,
      4096,
      4096,
      8192,
      8192,
      16384,
      16384,
      65536,
      65536,
      131072,
      131072
   };

   private DexUtil() {
   }

   public static final int getVersion(IBinaryUnit var0) throws ParseException {
      ByteBuffer var1 = var0.getInput().getHeader();
      byte[] var2 = new byte[3];
      var1.position(4);
      var1.get(var2);
      if (Character.isDigit(var2[0]) && Character.isDigit(var2[1]) && Character.isDigit(var2[2])) {
         String var3 = Strings.decodeASCII(var2);
         var3 = Strings.ltrim(var3, '0');
         return Conversion.stringToInt(var3);
      } else {
         throw new ParseException("Illegal version number", 0);
      }
   }

   private static void illegalEncoding(String var0, byte[] var1, int var2) {
      if (var0 == null) {
         var0 = "Illegal encoding";
      }

      int var3 = var1.length - var2;
      if (var3 < 0) {
         var0 = var0 + ": nothing to decode";
      }

      throw new DexParsingException(var0);
   }

   public static int bytearrayULEInt16ToInt(byte[] var0, int var1) {
      if (var1 + 2 > var0.length) {
         illegalEncoding("INT16: not enough data", var0, var1);
      }

      int var2 = var0[var1] & 255;
      return var2 | (var0[var1 + 1] & 0xFF) << 8;
   }

   public static int bytearrayULEInt32ToInt(byte[] var0, int var1) {
      if (var1 + 4 > var0.length) {
         illegalEncoding("INT32: not enough data", var0, var1);
      }

      int var2 = var0[var1] & 255;
      var2 |= (var0[var1 + 1] & 255) << 8;
      var2 |= (var0[var1 + 2] & 255) << 16;
      var2 |= (var0[var1 + 3] & 255) << 24;
      if (var2 < -1) {
         illegalEncoding("INT32: resulting in -1", var0, var1);
      }

      return var2;
   }

   public static int bytearraySLEB128ToInt(byte[] var0, int var1, int[] var2) {
      int var3 = 0;
      byte var4 = -1;

      int var5;
      for (var5 = 0; var4 < 0; var5++) {
         if (var1 + var5 >= var0.length) {
            illegalEncoding("LEB128: not enough data", var0, var1);
         }

         var4 = var0[var1 + var5];
         var3 |= (var4 & 127) << var5 * 7;
      }

      if (var5 > 5) {
         illegalEncoding("LEB128: too long", var0, var1);
      }

      if ((var4 & 64) != 0) {
         if (var5 == 1) {
            var3 |= -128;
         } else if (var5 == 2) {
            var3 |= -16384;
         } else if (var5 == 3) {
            var3 |= -2097152;
         } else if (var5 == 4) {
            var3 |= -268435456;
         }
      }

      var2[0] = var5;
      return var3;
   }

   public static int bytearrayULEB128ToInt(byte[] var0, int var1, int[] var2) {
      int var3 = 0;
      byte var4 = -1;

      int var5;
      for (var5 = 0; var4 < 0; var5++) {
         if (var1 + var5 >= var0.length) {
            illegalEncoding("ULEB128: not enough data", var0, var1);
         }

         var4 = var0[var1 + var5];
         var3 |= (var4 & 127) << var5 * 7;
      }

      if (var5 > 5) {
         illegalEncoding("ULEB128: too long", var0, var1);
      }

      var2[0] = var5;
      return var3;
   }

   public static int bytearrayULEB128P1ToInt(byte[] var0, int var1, int[] var2) {
      int var3 = bytearrayULEB128ToInt(var0, var1, var2);
      return var3 - 1;
   }

   public static String bytearrayMUTF8ToStringFast(byte[] var0, int var1, int[] var2, int var3) {
      char[] var4 = new char[var3];
      int var5 = 0;
      int var6 = var1;

      while (true) {
         if (var6 >= var0.length) {
            illegalEncoding("MUTF8: not enough data at offset " + var6, var0, var1);
         }

         int var7 = var0[var6] & 255;
         var6++;
         if (var7 == 0) {
            if (var2 != null) {
               var2[0] = var6 - var1;
            }

            return new String(var4);
         }

         if (var5 >= var3) {
            illegalEncoding("MUTF8: string is longer than expected: " + var3, var0, var1);
         }

         if ((var7 & 128) != 0) {
            if ((var7 & 224) == 192) {
               if (var6 >= var0.length) {
                  illegalEncoding("MUTF8: 2be: not enough data at offset " + var6, var0, var1);
               }

               int var8 = var0[var6] & 255;
               var6++;
               if ((var8 & 192) == 128) {
                  var7 = (var7 & 31) << 6 | var8 & 63;
               } else {
                  illegalEncoding("MUTF8: Invalid 2-byte encoding at offset " + var6, var0, var1);
               }
            } else if ((var7 & 240) == 224) {
               if (var6 + 2 > var0.length) {
                  illegalEncoding("MUTF8: 3be: not enough data at offset " + var6, var0, var1);
               }

               int var11 = var0[var6] & 255;
               int var9 = var0[++var6] & 255;
               var6++;
               if ((var11 & 192) == 128 && (var9 & 192) == 128) {
                  var7 = (var7 & 15) << 12 | (var11 & 63) << 6 | var9 & 63;
               } else {
                  illegalEncoding("MUTF8: Invalid 3-byte encoding at offset " + var6, var0, var1);
               }
            } else {
               illegalEncoding("MUTF8: Invalid encoding at offset " + var6, var0, var1);
            }
         }

         var4[var5++] = (char)var7;
      }
   }

   public static String bytearrayMUTF8ToString(byte[] var0, int var1, int[] var2) {
      StringBuilder var3 = new StringBuilder();
      int var4 = var1;
      int var5 = 0;

      while (true) {
         if (var4 >= var0.length) {
            illegalEncoding("MUTF8: not enough data at offset " + var4, var0, var1);
         }

         int var6 = var0[var4] & 255;
         var4++;
         if (var6 == 0) {
            if (var5 != 0) {
               var6 = (var5 - 65536 >> 10) + 55296;
               var3.appendCodePoint(var6);
            }

            if (var2 != null) {
               var2[0] = var4 - var1;
            }

            return var3.toString();
         }

         if ((var6 & 128) != 0) {
            if ((var6 & 224) == 192) {
               if (var4 >= var0.length) {
                  illegalEncoding("MUTF8: 2be: not enough data at offset " + var4, var0, var1);
               }

               int var7 = var0[var4] & 255;
               var4++;
               if ((var7 & 192) == 128) {
                  var6 = (var6 & 31) << 6 | var7 & 63;
               } else {
                  illegalEncoding("MUTF8: Invalid 2-byte encoding at offset " + var4, var0, var1);
               }
            } else if ((var6 & 240) == 224) {
               if (var4 + 2 > var0.length) {
                  illegalEncoding("MUTF8: 3be: not enough data at offset " + var4, var0, var1);
               }

               int var12 = var0[var4] & 255;
               int var8 = var0[++var4] & 255;
               var4++;
               if ((var12 & 192) == 128 && (var8 & 192) == 128) {
                  var6 = (var6 & 15) << 12 | (var12 & 63) << 6 | var8 & 63;
                  if (var6 >= 55296 && var6 <= 56319) {
                     if (var5 != 0) {
                        Object[] var14 = new Object[0];
                        int var9 = (var5 - 65536 >> 10) + 55296;
                        var3.appendCodePoint(var9);
                     }

                     var5 = 65536 + (var6 - 55296 << 10);
                     continue;
                  }

                  if (var6 >= 56320 && var6 <= 57343) {
                     if (var5 == 0) {
                        Object[] var10000 = new Object[]{var6};
                     } else {
                        var6 = var5 + (var6 - 56320);
                        var5 = 0;
                     }
                  }
               } else {
                  illegalEncoding("MUTF8: Invalid 3-byte encoding at offset " + var4, var0, var1);
               }
            } else {
               illegalEncoding("MUTF8: Invalid encoding at offset " + var4, var0, var1);
            }
         }

         if (var5 != 0) {
            Object[] var15 = new Object[0];
            int var13 = (var5 - 65536 >> 10) + 55296;
            var5 = 0;
            var3.appendCodePoint(var13);
         }

         var3.appendCodePoint(var6);
      }
   }

   public static String stringFromMUTF8(byte[] var0) {
      return bytearrayMUTF8ToString(var0, 0, null);
   }

   public static byte[] stringToMUTF8(String var0) {
      return stringToMUTF8(var0, true);
   }

   public static byte[] stringToMUTF8(String var0, boolean var1) {
      BytePipe var2 = new BytePipe();

      for (char var6 : var0.toCharArray()) {
         if (var6 == 0) {
            var2.append(192);
            var2.append(128);
         } else if (var6 <= 127) {
            var2.append((int)var6);
         } else if (var6 <= 2047) {
            var2.append(192 | var6 >>> 6);
            var2.append(128 | var6 & '?');
         } else {
            var2.append(224 | var6 >>> '\f');
            var2.append(128 | var6 >>> 6 & 63);
            var2.append(128 | var6 & '?');
         }
      }

      if (var1) {
         var2.append(0);
      }

      return var2.getAll();
   }

   public static int convertDexFlagsToCodeFlags(int var0) {
      int var1 = 0;

      for (byte var2 = 0; var0 != 0 && var2 < flconv.length; var2 += 2) {
         if ((var0 & flconv[var2]) != 0) {
            var0 &= ~flconv[var2];
            var1 |= flconv[var2 + 1];
         }
      }

      return var1;
   }

   public static void validateFlags(int var0) {
      if ((var0 & -229376) != 0) {
         throw new DexParsingException("Invalid access flags: " + var0);
      }
   }

   public static void validateClassFlags(int var0) {
      if ((var0 & -30240) != 0) {
         throw new DexParsingException("Invalid class access flags: " + var0);
      }
   }

   public static void validateFieldFlags(int var0) {
      if ((var0 & -20704) != 0) {
         throw new DexParsingException("Invalid field access flags: " + var0);
      }
   }

   public static void validateMethodFlags(int var0) {
      if ((var0 & -204288) != 0) {
         throw new DexParsingException("Invalid method access flags: " + var0);
      }
   }

   public static String formatAccessFlags(int var0, int var1) {
      StringBuilder var2 = new StringBuilder();
      appendAccessFlagString(var2, var0, 1, "public");
      appendAccessFlagString(var2, var0, 2, "private");
      appendAccessFlagString(var2, var0, 4, "protected");
      appendAccessFlagString(var2, var0, 8, "static");
      appendAccessFlagString(var2, var0, 16, "final");
      appendAccessFlagString(var2, var0, 1024, "abstract");
      appendAccessFlagString(var2, var0, 512, "interface");
      appendAccessFlagString(var2, var0, 16384, "enum");
      appendAccessFlagString(var2, var0, 32, "synchronized");
      appendAccessFlagString(var2, var0, 64, "volatile");
      appendAccessFlagString(var2, var0, 64, "bridge");
      appendAccessFlagString(var2, var0, 128, "transient");
      appendAccessFlagString(var2, var0, 128, "varargs");
      appendAccessFlagString(var2, var0, 256, "native");
      appendAccessFlagString(var2, var0, 2048, "strictfp");
      appendAccessFlagString(var2, var0, 4096, "synthetic");
      appendAccessFlagString(var2, var0, 8192, "annotation");
      appendAccessFlagString(var2, var0, 65536, "constructor");
      appendAccessFlagString(var2, var0, 131072, "declared-synchronized");
      if (var1 == 1 || var1 == -1 && var2.length() > 0) {
         var2.append(" ");
      }

      return var2.toString();
   }

   private static void appendAccessFlagString(StringBuilder var0, int var1, int var2, String var3) {
      if (var3 != null && var3.length() > 0 && (var1 & var2) != 0) {
         if (var0.length() > 0) {
            var0.append(" ");
         }

         var0.append(var3);
      }
   }

   public static int determineBestBase(long var0) {
      if (-20L <= var0 && var0 <= 20L) {
         return 10;
      } else {
         if (var0 < 0L) {
            var0 = -var0;
         }

         long var2 = var0;
         int var4 = 0;

         int var5;
         for (var5 = 0; var2 != 0L && var2 != -1L; var4++) {
            long var6 = var2 % 10L;
            if (var6 == 0L) {
               var5++;
            }

            var2 /= 10L;
         }

         var2 = var0;
         int var13 = 0;
         int var7 = 0;

         int var8;
         for (var8 = 0; var2 != 0L && var2 != -1L; var13++) {
            long var9 = var2 & 15L;
            if (var9 == 0L) {
               var7++;
            } else if (var9 == 15L) {
               var8++;
            }

            var2 >>= 4;
         }

         int var14 = var5 - (var4 - var5);
         int var10 = var7 + var8 - (var13 - var7 - var8);
         return var14 >= var10 ? 10 : 16;
      }
   }

   public static final String formatTypenames(IDexUnit var0, Collection var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (int var5 : var1) {
         if (var3 >= 1) {
            var2.append("\n");
         }

         IDexType var6 = var0.getType(var5);
         Strings.ff(var2, "%d: %s", var5, var6 == null ? "???" : var6.getName(false));
         var3++;
      }

      return var2.toString();
   }

   public static final String formatFieldsigs(IDexUnit var0, Collection var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (int var5 : var1) {
         if (var3 >= 1) {
            var2.append("\n");
         }

         IDexField var6 = var0.getField(var5);
         Strings.ff(var2, "%d: %s", var5, var6 == null ? "???" : var6.getSignature(false));
         var3++;
      }

      return var2.toString();
   }

   public static final String formatMethodsigs(IDexUnit var0, Collection var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (int var5 : var1) {
         if (var3 >= 1) {
            var2.append("\n");
         }

         IDexMethod var6 = var0.getMethod(var5);
         Strings.ff(var2, "%d: %s", var5, var6 == null ? "???" : var6.getSignature(false));
         var3++;
      }

      return var2.toString();
   }

   public static IDexValue getStaticFieldInitializer(IDexClass var0, IDexFieldData var1) {
      int var2 = var0.getData().getStaticFields().indexOf(var1);
      return var2 >= 0 && var2 < var0.getStaticInitializers().size() ? (IDexValue)var0.getStaticInitializers().get(var2) : null;
   }

   public static String[] generateDefaultJniNames(String var0) {
      String[] var1 = var0.split("->");
      if (var1.length != 2) {
         return null;
      } else {
         String var2 = var1[0];
         if (var2.charAt(0) == 'L' && var2.charAt(var2.length() - 1) == ';') {
            var2 = var2.substring(1, var2.length() - 1).replace("_", "_1").replace('/', '_');
            int var3 = var1[1].indexOf(40);
            if (var3 < 0) {
               String var8 = var1[1].replace("_", "_1");
               return new String[]{"Java_" + var2 + "_" + var8};
            } else {
               String var4 = var1[1].substring(0, var3).replace("_", "_1");
               String var5 = var1[1].substring(var3);
               var5 = var5.substring(1, var5.indexOf(41)).replace("_", "_1").replace(";", "_2").replace('/', '_');
               String var6 = "Java_" + var2 + "_" + var4;
               return new String[]{var6, var6 + "__" + var5};
            }
         } else {
            return null;
         }
      }
   }

   public static List getDebugParameterNames(IDexUnit var0, IDexMethodData var1) {
      if (var1.getCodeItem() != null && var1.getCodeItem().getDebugInfo() != null) {
         IDexDebugInfo var2 = var1.getCodeItem().getDebugInfo();
         ArrayList var3 = new ArrayList();

         for (int var7 : var2.getParameterNameIndexes()) {
            String var8 = null;
            if (var7 >= 0 && var7 < var0.getStringCount()) {
               var8 = var0.getString(var7).getValue();
            }

            var3.add(var8);
         }

         return var3;
      } else {
         return null;
      }
   }

   public static List getAnnotatedParameterNames(IDexUnit var0, IDexMethodData var1) {
      IDexType var2 = var0.getType("Ldalvik/annotation/MethodParameters;");
      if (var2 == null) {
         return null;
      } else {
         int var3 = var1.getMethodIndex();
         IDexMethod var4 = var0.getMethod(var3);
         IDexClass var5 = var4.getClassType().getImplementingClass();
         List var6 = var5.getAnnotationsDirectory().getMethodAnnotations(var3);
         if (var6.isEmpty()) {
            return null;
         } else {
            Optional var7 = var6.stream().filter(var1x -> var1x.getAnnotation().getTypeIndex() == var2.getIndex()).findFirst();
            if (!var7.isPresent()) {
               return Collections.emptyList();
            } else {
               IDexAnnotation var8 = ((IDexAnnotationItem)var7.get()).getAnnotation();
               ArrayList var9 = new ArrayList();

               for (IDexAnnotationElement var11 : var8.getElements()) {
                  if ("names".equals(var11.getName(var0))) {
                     IDexValue var12 = var11.getValue();
                     if (var12.getType() != 28) {
                        return null;
                     }

                     for (IDexValue var14 : var12.getArray()) {
                        if (var14.getType() != 23) {
                           return null;
                        }

                        String var15 = var0.getString(var14.getStringIndex()).getValue(false);
                        var9.add(var15);
                     }
                  }
               }

               return var9;
            }
         }
      }
   }

   public static ParametersInfo getMethodParametersInfo(IDexUnit var0, IDexMethodData var1) {
      boolean var2 = (var1.getAccessFlags() & 8) != 0;
      int var3 = var1.getMethodIndex();
      int var4 = var0.getMethod(var3).getPrototypeIndex();
      String var5 = var0.getPrototype(var4).getShorty();
      return getMethodParametersInfo(var5, var2);
   }

   public static ParametersInfo getMethodParametersInfo(String var0, boolean var1) {
      int var2 = var1 ? 0 : 1;
      ArrayList var3 = new ArrayList();

      for (int var4 = 1; var4 < var0.length(); var4++) {
         var3.add(var2);
         char var5 = var0.charAt(var4);
         switch (var5) {
            case 'B':
            case 'C':
            case 'F':
            case 'I':
            case 'L':
            case 'S':
            case 'Z':
               var2++;
               break;
            case 'D':
            case 'J':
               var2 += 2;
               break;
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            default:
               throw new RuntimeException("Unknown shorty character type: " + var5);
         }
      }

      return new ParametersInfo(var2, var3);
   }

   public static int getMethodSlotCount(String var0, boolean var1) {
      int var2 = var1 ? 0 : 1;

      for (int var3 = 1; var3 < var0.length(); var3++) {
         switch (var0.charAt(var3)) {
            case 'B':
            case 'C':
            case 'F':
            case 'I':
            case 'L':
            case 'S':
            case 'Z':
               var2++;
               break;
            case 'D':
            case 'J':
               var2 += 2;
               break;
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            default:
               throw new RuntimeException();
         }
      }

      return var2;
   }

   public static int[] getMethodParameterIndices(IDexMethod var0) {
      IDexMethodData var1 = var0.getData();
      int var2 = var1.getCodeItem().getRegisterCount();
      String var3 = var0.getPrototype().getShorty();
      int var4 = var3.length() - 1;
      if (!var1.isStatic()) {
         var4++;
      }

      int[] var5 = new int[var4];
      int var6 = var4 - 1;

      for (int var7 = var3.length() - 1; var7 >= 1; var7--) {
         char var8 = var3.charAt(var7);
         switch (var8) {
            case 'B':
            case 'C':
            case 'F':
            case 'I':
            case 'L':
            case 'S':
            case 'Z':
               var5[var6] = --var2;
               break;
            case 'D':
            case 'J':
               var2 -= 2;
               var5[var6] = var2;
               break;
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            default:
               throw new RuntimeException();
         }

         var6--;
      }

      if (!var1.isStatic()) {
         var5[var6] = --var2;
         var6--;
      }

      Assert.a(var6 == -1);
      return var5;
   }

   public static boolean isSubtypeOf(IDexUnit var0, int var1, int var2) {
      return ((vi)var0).eP().pC(var1, var2);
   }

   public static List getMemberClasses(IDexUnit var0, IDexClass var1) {
      return getMemberClasses(var0, (IDexItem)var1);
   }

   public static List getMemberClasses(IDexUnit var0, IDexMethod var1) {
      return getMemberClasses(var0, (IDexItem)var1);
   }

   private static List getMemberClasses(IDexUnit var0, IDexItem var1) {
      ArrayList var2 = new ArrayList();
      HE var3 = ((vi)var0).ld().pC((Sv)var1);

      for (HE var5 : var3.getChildren()) {
         if (var5.kS() instanceof IDexClass) {
            var2.add((IDexClass)var5.kS());
         }
      }

      return var2;
   }

   public static IDexClass getParentClass(IDexUnit var0, IDexClass var1) {
      return getParentClass(var0, (IDexItem)var1);
   }

   private static IDexClass getParentClass(IDexUnit var0, IDexItem var1) {
      HE var2 = ((vi)var0).ld().pC((Sv)var1);
      HE var3 = var2.A();
      if (var3 == null) {
         return null;
      } else {
         Sv var4 = var3.kS();
         if (var4 instanceof IDexClass) {
            return (IDexClass)var4;
         } else {
            return var4 instanceof IDexMethod ? getParentClass(var0, var4) : null;
         }
      }
   }

   public static IDexClass getSuperClass(IDexUnit var0, IDexClass var1) {
      int var2 = var1.getSuperTypeIndex();
      return var2 < 0 ? null : var0.getType(var2).getImplementingClass();
   }

   public static boolean isSimpleName(String var0, int var1) {
      int var2 = parseSimpleName(var0, 0, var1);
      return var2 == var0.length();
   }

   public static int parseSimpleName(String var0, int var1, int var2) {
      if (var1 >= var0.length()) {
         return -1;
      } else {
         boolean var3 = false;
         int var4 = var1;

         while (true) {
            label106: {
               if (var4 < var0.length()) {
                  char var5 = var0.charAt(var4);
                  if (var3) {
                     if (var5 < '\ud800' || var5 > '\udfff') {
                        return -1;
                     }

                     var3 = false;
                     break label106;
                  }

                  if (var5 >= 'A' && var5 <= 'Z'
                     || var5 >= 'a' && var5 <= 'z'
                     || var5 >= '0' && var5 <= '9'
                     || var5 == '$'
                     || var5 == '-'
                     || var5 == '_'
                     || var5 >= 161 && var5 <= 8191
                     || var5 >= 8208 && var5 <= 8231
                     || var5 >= 8240 && var5 <= '\ud7ff'
                     || var5 >= '\ue000' && var5 <= '\uffef') {
                     break label106;
                  }

                  if (var5 >= '\ud800' && var5 <= '\udfff') {
                     var3 = true;
                     break label106;
                  }

                  if ((var2 == 0 || var2 >= 40) && (var5 == ' ' || var5 == 160 || var5 >= 8192 && var5 <= 8202 || var5 == 8239)) {
                     break label106;
                  }
               }

               return var4;
            }

            var4++;
         }
      }
   }

   public static boolean isInternalClassname(String var0, boolean var1, List var2, int var3) {
      return isInternalClassname(var0, var1, var2, var3, true);
   }

   public static boolean isInternalPackageName(String var0, boolean var1, List var2, int var3) {
      return isInternalClassname(var0, var1, var2, var3, false);
   }

   private static boolean isInternalClassname(String var0, boolean var1, List var2, int var3, boolean var4) {
      if (var0.length() >= 3 && var0.startsWith("L") && (!var4 || var0.endsWith(";")) && (var4 || var0.endsWith("/"))) {
         String[] var5 = var0.substring(1, var0.length() - 1).split("/", -1);
         if (var5.length == 0) {
            return false;
         } else {
            if (var1) {
               for (String var9 : var5) {
                  if (!isSimpleName(var9, var3)) {
                     return false;
                  }
               }
            }

            if (var2 != null) {
               var2.clear();

               for (String var13 : var5) {
                  var2.add(var13);
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean isClassname(String var0, boolean var1, List var2, int var3) {
      String[] var4 = var0.split("\\.", -1);
      if (var4.length == 0) {
         return false;
      } else {
         if (var1) {
            for (String var8 : var4) {
               if (!isSimpleName(var8, var3)) {
                  return false;
               }
            }
         }

         if (var2 != null) {
            var2.clear();

            for (String var12 : var4) {
               var2.add(var12);
            }
         }

         return true;
      }
   }

   public static String getStringSafe(IDexUnit var0, int var1, String var2) {
      return ((vi)var0).pC(var1, var2, false);
   }

   public static IDexAnnotation findAnnotation(IDexUnit var0, Collection var1, String var2, Integer var3) {
      IDexType var4 = var0.getType(var2);
      if (var4 == null) {
         return null;
      } else {
         for (IDexAnnotationItem var6 : var1) {
            if ((var3 == null || var6.getVisibility() == var3) && var6.getAnnotation().getTypeIndex() == var4.getIndex()) {
               return var6.getAnnotation();
            }
         }

         return null;
      }
   }

   public static IDexValue findAnnotationElement(IDexUnit var0, IDexAnnotation var1, String var2) {
      for (IDexAnnotationElement var4 : var1.getElements()) {
         if (var2.equals(var4.getName(var0))) {
            return var4.getValue();
         }
      }

      return null;
   }

   public static IApkUnit findParentApk(IDexUnit var0) {
      IUnitCreator var1 = var0.getParent();
      return !(var1 instanceof IApkUnit) ? null : (IApkUnit)var1;
   }

   public static String findPackageName(IDexUnit var0) {
      IUnitCreator var1 = var0.getParent();
      return !(var1 instanceof IApkUnit) ? null : ((IApkUnit)var1).getPackageName();
   }

   public static IDexMethod findInternalVirtualMethodTarget(IDexUnit var0, IDexClass var1, String var2, String... var3) {
      while (true) {
         IDexMethod var4 = var1.getMethod(false, var2, var3);
         if (var4 != null) {
            return var4;
         }

         int var5 = var1.getSuperTypeIndex();
         if (var5 >= 0) {
            var1 = var0.getType(var5).getImplementingClass();
            if (var1 != null) {
               continue;
            }
         }

         return null;
      }
   }

   static {
      Assert.a(flconv.length % 2 == 0);
   }
}
