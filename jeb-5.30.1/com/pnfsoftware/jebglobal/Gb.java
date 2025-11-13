package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.util.encoding.IntegerLEB128;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Gb {
   @SerId(1)
   private List q = new ArrayList();
   @SerId(2)
   private List RF = new ArrayList();
   private static final String[] xK = new String[]{
      "Pre-v4",
      "v4",
      "v4T",
      "v5T",
      "v5TE",
      "v5TEJ",
      "v6",
      "v6KZ",
      "v6T2",
      "v6K",
      "v7",
      "v6-M",
      "v6S-M",
      "v7E-M",
      "v8",
      "v8-R",
      "v8-M.baseline",
      "v8-M.mainline"
   };
   private static final String[] Dw = new String[]{"Not permitted", "Permitted"};
   private static final String[] Uv = new String[]{"Not permitted", "Thumb", "Thumb-2"};
   private static final String[] oW = new String[]{
      "Not Permitted", "VFPv1", "VFPv2", "VFPv3", "VFPv3-D16", "VFPv4", "VFPv4-D16", "ARMv8-a FP", "ARMv8-a FP-D16"
   };
   private static final String[] gO = new String[]{"Not Permitted", "WMMXv1", "WMMXv2"};
   private static final String[] nf = new String[]{"Not Permitted", "NEONv1", "NEONv2+FMA", "ARMv8-a NEON", "ARMv8.1-a NEON"};
   private static final String[] gP = new String[]{
      "None", "Bare Platform", "Linux Application", "Linux DSO", "Palm OS 2004", "Reserved (Palm OS)", "Symbian OS 2004", "Reserved (Symbian OS)"
   };
   private static final String[] za = new String[]{"v6", "Static Base", "TLS", "Unused"};
   private static final String[] lm = new String[]{"Absolute", "PC-relative", "SB-relative", "Not Permitted"};
   private static final String[] zz = new String[]{"Absolute", "PC-relative", "Not Permitted"};
   private static final String[] JY = new String[]{"Not Permitted", "Direct", "GOT-Indirect"};
   private static final String[] HF = new String[]{"Not Permitted", "Unknown", "2-byte", "Unknown", "4-byte"};
   private static final String[] LK = new String[]{"IEEE-754", "Runtime"};
   private static final String[] io = new String[]{"Unsupported", "IEEE-754", "Sign Only"};
   private static final String[] qa = new String[]{"Not Permitted", "IEEE-754"};
   private static final String[] Hk = new String[]{"Not Permitted", "IEEE-754"};
   private static final String[] Me = new String[]{"Not Permitted", "Finite Only", "RTABI", "IEEE-754"};
   private static final String[] PV = new String[]{"Not Permitted", "8-byte alignment", "4-byte alignment", "Reserved"};
   private static final String[] oQ = new String[]{"Not Required", "8-byte data alignment", "8-byte data and code alignment", "Reserved"};
   private static final String[] xW = new String[]{"Not Permitted", "Packed", "Int32", "External Int32"};
   private static final String[] KT = new String[]{"Tag_FP_arch", "Single-Precision", "Reserved", "Tag_FP_arch (deprecated)"};
   private static final String[] Gf = new String[]{"AAPCS", "AAPCS VFP", "Custom", "Not Permitted"};
   private static final String[] Ef = new String[]{"AAPCS", "iWMMX", "Custom"};
   private static final String[] cC = new String[]{"None", "Speed", "Aggressive Speed", "Size", "Aggressive Size", "Debugging", "Best Debugging"};
   private static final String[] sH = new String[]{"None", "Speed", "Aggressive Speed", "Size", "Aggressive Size", "Accuracy", "Best Accuracy"};
   private static final String[] CE = new String[]{"Not Permitted", "v6-style"};
   private static final String[] wF = new String[]{"If Available", "Permitted"};
   private static final String[] If = new String[]{"Not Permitted", "IEEE-754", "VFPv3"};
   private static final String[] Dz = new String[]{"Not Permitted", "Permitted"};
   private static final String[] mI = new String[]{"If Available", "Not Permitted", "Permitted"};
   private static final String[] jq = new String[]{"Not Permitted", "Permitted"};
   private static final String[] ui = new String[]{"Not Permitted", "Permitted"};
   private static final String[] TX = new String[]{"Not Permitted", "TrustZone", "Virtualization Extensions", "TrustZone + Virtualization Extensions"};

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   public static Gb q(ByteBuffer var0, boolean var1, long var2) {
      Gb var4 = new Gb();

      try {
         byte var5 = var0.get();
         if (var5 != 65) {
            var4.RF.add(Strings.ff("uncommon version number (%x) => parsing stopped", var5));
            return var4;
         }
      } catch (BufferUnderflowException var14) {
         return null;
      }

      long var6;
      try {
         var6 = 1L;
      } catch (BufferUnderflowException var12) {
         return null;
      }

      while (true) {
         try {
            if (var6 >= var2) {
               return var4;
            }

            int var8 = var0.getInt();
            String var9 = q(var0);
            if (var9.equals("aeabi")) {
               byte var10 = var0.get();
               if (var10 == 1) {
                  int var11 = var0.getInt() - 1 - 4;
                  if (!q(var4, var0, var11)) {
                     var4.RF.add("public file attributes parsing failed");
                  }
               } else if (var10 == 2) {
                  var4.RF.add("public attributes targeting a section => not parsed");
               } else if (var10 == 3) {
                  var4.RF.add("public attributes targeting a symbol => not parsed");
               } else {
                  var4.RF.add("public attributes with unknown granularity => not parsed");
               }
            } else {
               var4.RF.add(Strings.ff("private vendor attributes for %s => not parsed", var9));
            }

            var6 += var8;
            if (var6 > 10000L) {
               return null;
            }
         } catch (BufferUnderflowException var13) {
            return null;
         }
      }
   }

   private static boolean q(Gb var0, ByteBuffer var1, int var2) {
      byte[] var3 = new byte[var2];
      var1.get(var3);

      try {
         try (ByteArrayInputStream var4 = new ByteArrayInputStream(var3)) {
            int var5 = 0;

            while (var5 < var2) {
               IntegerLEB128.DecodedInt var6 = IntegerLEB128.decodeULEB128(var4);
               String var7 = ELF.getArmAttributeTagString(var6.value);
               if (var7 == null) {
                  var0.RF.add("unknown attribute => parsing stopped");
                  return false;
               }

               var5 += var6.encodedSize;
               String var8 = null;
               int var9 = 0;
               switch (var6.value) {
                  case 4:
                  case 5:
                     var8 = q(var4);
                     var9 = var8.length() + 1;
                     break;
                  case 6:
                     IntegerLEB128.DecodedInt var48 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(xK, var48.value);
                     var9 = var48.encodedSize;
                     break;
                  case 7:
                     IntegerLEB128.DecodedInt var47 = IntegerLEB128.decodeULEB128(var4);

                     var8 = switch (var47.value) {
                        case 0 -> "None";
                        case 65 -> "Application";
                        case 77 -> "Microcontroller";
                        case 82 -> "Real-time";
                        case 83 -> "Classic";
                        default -> "Unknown";
                     };
                     var9 = var47.encodedSize;
                     break;
                  case 8:
                     IntegerLEB128.DecodedInt var46 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(Dw, var46.value);
                     var9 = var46.encodedSize;
                     break;
                  case 9:
                     IntegerLEB128.DecodedInt var45 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(Uv, var45.value);
                     var9 = var45.encodedSize;
                     break;
                  case 10:
                     IntegerLEB128.DecodedInt var44 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(oW, var44.value);
                     var9 = var44.encodedSize;
                     break;
                  case 11:
                     IntegerLEB128.DecodedInt var43 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(gO, var43.value);
                     var9 = var43.encodedSize;
                     break;
                  case 12:
                     IntegerLEB128.DecodedInt var42 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(nf, var42.value);
                     var9 = var42.encodedSize;
                     break;
                  case 13:
                     IntegerLEB128.DecodedInt var41 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(gP, var41.value);
                     var9 = var41.encodedSize;
                     break;
                  case 14:
                     IntegerLEB128.DecodedInt var40 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(za, var40.value);
                     var9 = var40.encodedSize;
                     break;
                  case 15:
                     IntegerLEB128.DecodedInt var39 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(lm, var39.value);
                     var9 = var39.encodedSize;
                     break;
                  case 16:
                     IntegerLEB128.DecodedInt var38 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(zz, var38.value);
                     var9 = var38.encodedSize;
                     break;
                  case 17:
                     IntegerLEB128.DecodedInt var37 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(JY, var37.value);
                     var9 = var37.encodedSize;
                     break;
                  case 18:
                     IntegerLEB128.DecodedInt var36 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(HF, var36.value);
                     var9 = var36.encodedSize;
                     break;
                  case 19:
                     IntegerLEB128.DecodedInt var35 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(LK, var35.value);
                     var9 = var35.encodedSize;
                     break;
                  case 20:
                     IntegerLEB128.DecodedInt var34 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(io, var34.value);
                     var9 = var34.encodedSize;
                     break;
                  case 21:
                     IntegerLEB128.DecodedInt var33 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(qa, var33.value);
                     var9 = var33.encodedSize;
                     break;
                  case 22:
                     IntegerLEB128.DecodedInt var32 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(Hk, var32.value);
                     var9 = var32.encodedSize;
                     break;
                  case 23:
                     IntegerLEB128.DecodedInt var31 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(Me, var31.value);
                     var9 = var31.encodedSize;
                     break;
                  case 24:
                     IntegerLEB128.DecodedInt var30 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(PV, var30.value);
                     if (var8 == null) {
                        if (var30.value <= 12) {
                           var8 = Strings.ff("8-byte alignment, %d-byte extended alignment", var30.value);
                        } else {
                           var8 = "invalid";
                        }
                     }

                     var9 = var30.encodedSize;
                     break;
                  case 25:
                     IntegerLEB128.DecodedInt var29 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(oQ, var29.value);
                     if (var8 == null) {
                        if (var29.value <= 12) {
                           var8 = Strings.ff("8-byte alignment, %d-byte data alignment", var29.value);
                        } else {
                           var8 = "invalid";
                        }
                     }

                     var9 = var29.encodedSize;
                     break;
                  case 26:
                     IntegerLEB128.DecodedInt var28 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(xW, var28.value);
                     var9 = var28.encodedSize;
                     break;
                  case 27:
                     IntegerLEB128.DecodedInt var27 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(KT, var27.value);
                     var9 = var27.encodedSize;
                     break;
                  case 28:
                     IntegerLEB128.DecodedInt var26 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(Gf, var26.value);
                     var9 = var26.encodedSize;
                     break;
                  case 29:
                     IntegerLEB128.DecodedInt var25 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(Ef, var25.value);
                     var9 = var25.encodedSize;
                     break;
                  case 30:
                     IntegerLEB128.DecodedInt var24 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(cC, var24.value);
                     var9 = var24.encodedSize;
                     break;
                  case 31:
                     IntegerLEB128.DecodedInt var23 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(sH, var23.value);
                     var9 = var23.encodedSize;
                  case 32:
                  case 33:
                  case 35:
                  case 37:
                  case 39:
                  case 40:
                  case 41:
                  case 43:
                  case 45:
                  case 47:
                  case 48:
                  case 49:
                  case 50:
                  case 51:
                  case 52:
                  case 53:
                  case 54:
                  case 55:
                  case 56:
                  case 57:
                  case 58:
                  case 59:
                  case 60:
                  case 61:
                  case 62:
                  case 63:
                  case 64:
                  case 65:
                  case 67:
                  case 69:
                  case 70:
                  default:
                     break;
                  case 34:
                     IntegerLEB128.DecodedInt var22 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(CE, var22.value);
                     var9 = var22.encodedSize;
                     break;
                  case 36:
                     IntegerLEB128.DecodedInt var21 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(wF, var21.value);
                     var9 = var21.encodedSize;
                     break;
                  case 38:
                     IntegerLEB128.DecodedInt var20 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(If, var20.value);
                     var9 = var20.encodedSize;
                     break;
                  case 42:
                     IntegerLEB128.DecodedInt var19 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(Dz, var19.value);
                     var9 = var19.encodedSize;
                     break;
                  case 44:
                     IntegerLEB128.DecodedInt var18 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(mI, var18.value);
                     var9 = var18.encodedSize;
                     break;
                  case 46:
                     IntegerLEB128.DecodedInt var17 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(jq, var17.value);
                     var9 = var17.encodedSize;
                     break;
                  case 66:
                     IntegerLEB128.DecodedInt var16 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(ui, var16.value);
                     var9 = var16.encodedSize;
                     break;
                  case 68:
                     IntegerLEB128.DecodedInt var10 = IntegerLEB128.decodeULEB128(var4);
                     var8 = q(TX, var10.value);
                     var9 = var10.encodedSize;
               }

               if (var9 == 0) {
                  var0.RF.add(Strings.ff("cannot parse tag %s value => parsing stopped", var7));
                  return true;
               }

               var0.q.add(Strings.ff("%s: %s", var7, var8));
               var5 += var9;
            }
         }

         return true;
      } catch (IOException var13) {
         return false;
      }
   }

   private static String q(String[] var0, int var1) {
      return var1 >= 0 && var1 < var0.length ? var0[var1] : null;
   }

   private static String q(ByteBuffer var0) {
      StringBuilder var1 = new StringBuilder();

      for (byte var2 = var0.get(); var2 != 0; var2 = var0.get()) {
         char var3 = (char)(var2 & 255);
         var1.append(var3);
      }

      return var1.toString();
   }

   private static String q(InputStream var0) throws IOException {
      StringBuilder var1 = new StringBuilder();

      for (int var2 = var0.read(); var2 != 0 && var2 != -1; var2 = var0.read()) {
         var1.append((char)var2);
      }

      return var1.toString();
   }

   public List q() {
      return this.q;
   }

   public List RF() {
      return this.RF;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.q != null && this.q.size() != 0) {
         var1.append("+ Vendor 'aeabi' (public attributes):\n");

         for (String var3 : this.q) {
            var1.append("    ");
            var1.append(var3);
            var1.append("\n");
         }
      }

      if (this.RF != null && this.RF.size() != 0) {
         var1.append("\n  + Notes on ARM attributes parsing:\n");

         for (String var5 : this.RF) {
            var1.append("    ");
            var1.append(var5);
            var1.append("\n");
         }
      }

      return var1.toString();
   }
}
