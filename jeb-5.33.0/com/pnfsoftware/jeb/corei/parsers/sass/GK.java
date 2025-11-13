package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.base.LongLong;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.primitives.Floats;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Ser
public class GK {
   @SerId(1)
   int pC;
   @SerId(2)
   Map A;
   @SerId(3)
   Map kS;
   @SerId(4)
   Map wS;
   @SerId(5)
   GK.DH UT;
   @SerId(6)
   Set E;
   @SerId(7)
   List sY;
   @SerId(8)
   MultiMap ys;
   @SerId(9)
   MultiMap ld;
   @SerTransient
   private Set gp;
   @SerTransient
   private Set oT;
   @SerTransient
   private Set fI;
   @SerTransient
   private Set WR;
   @SerTransient
   private Set NS;

   public GK.KD pC(String var1) {
      for (GK.KD var3 : this.sY) {
         if (var3.pC.equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   private static Couple pC(String var0, int var1, Set var2) {
      int var3;
      for (var3 = var1; var1 < var0.length(); var1++) {
         char var4 = var0.charAt(var1);
         if (pC(var4) || var2 != null && var2.contains(var4)) {
            break;
         }
      }

      return new Couple(var0.substring(var3, var1), var1);
   }

   private static Couple pC(String var0, int var1) {
      return pC(var0, var1, Set.of('[', ']', '{', '}', '\'', '$'));
   }

   private static boolean pC(char var0) {
      return var0 == ' ' || var0 == '\t' || var0 == '\r' || var0 == '\n';
   }

   private static int A(String var0, int var1) {
      while (var1 < var0.length() && pC(var0.charAt(var1))) {
         var1++;
      }

      return var1;
   }

   private static Couple kS(String var0, int var1) {
      int var2;
      for (var2 = var1; var1 < var0.length(); var1++) {
         char var3 = var0.charAt(var1);
         if (var3 < '0' || var3 > '9') {
            break;
         }
      }

      return new Couple(var0.substring(var2, var1), var1);
   }

   private static long A(String var0) {
      return var0.startsWith("0x") ? Long.parseLong(var0.substring(2), 16) : Long.parseLong(var0);
   }

   public GK.sy pC(GK.KD var1, long var2, long var4) {
      return new GK.sy(this, var1, var2, var4);
   }

   public Long pC(GK.KD var1, String var2, long var3, long var5, String var7, String var8) {
      for (Entry var10 : var1.ld.pC.entrySet()) {
         GK.bO var11 = (GK.bO)var10.getValue();
         if (var11.pC()) {
            GK.bO.Av var12 = var11.A();
            if (var12.pC() && var12.A().equals(var2)) {
               String var13 = (String)var10.getKey();
               GK.K var14 = this.pC(var13, var3, var5);
               boolean var15 = var7 != null && var7.endsWith("SImm");
               long var16 = var14.pC(var15);
               String var18 = var11.kS();
               if (var18 != null) {
                  if (var18.equals("convertFloatType(mufuop == `MUFU_OP@RCP64H || mufuop == `MUFU_OP@RSQ64H, F64Imm, F32Imm)")) {
                     Assert.a(var8 != null);
                     if (pC(var8, "RCP64H", "RSQ64H")) {
                        var16 <<= 32;
                     } else {
                        var16 = MathUtil.signExtend(var16, 32);
                     }
                  } else if (var18.equals("convertFloatType(ofmt == `OFMT@E8M7_V2 || ofmt == `OFMT@BF16_V2, E8M7Imm, ofmt == `OFMT@E6M9_V2, E6M9Imm, F16Imm)")
                     || var18.equals("convertFloatType(dstfmt.srcfmt == `DSTFMT_SRCFMT_F32F16_F32E6M9@F32.E6M9, E6M9Imm, F16Imm)")) {
                     Assert.a(var8 != null);
                     if (pC(var8, "E8M7_V2", "BF16_V2")) {
                        double var48 = Float.intBitsToFloat((int)var16 << 16);
                        var16 = Double.doubleToRawLongBits(var48);
                     } else {
                        if (pC(var8, "E6M9_V2", "F32.E6M9")) {
                           throw new RuntimeException();
                        }

                        float var49 = Floats.fromFP16Bits((int)var16);
                        var16 = Double.doubleToRawLongBits(var49);
                     }
                  } else if (var18.equals("convertFloatType(ofmt == `OFMT_hfma2@E8M7_V2 || ofmt == `OFMT_hfma2@BF16_V2, E8M7Imm, F16Imm)")
                     || var18.equals("convertFloatType(ofmt == `OFMT_F16_V2_BF16_V2@BF16_V2, E8M7Imm, F16Imm)")
                     || var18.equals("convertFloatType(ofmt == `OFMT@BF16_V2, E8M7Imm, F16Imm)")
                     || var18.equals("convertFloatType(dstfmt.srcfmt == `DSTFMT_SRCFMT_F64F16_F64BF16@F64.BF16, E8M7Imm, F16Imm)")
                     || var18.equals("convertFloatType(ofmt == `OFMT_DIST@BF16_V2, E8M7Imm, F16Imm)")
                     || var18.equals("convertFloatType(ofmt == `OFMT_F16_V2_BF16_V2@BF16_V2, E8M7Imm, F16Imm)")
                     || var18.equals("convertFloatType(fmt == `FMT_F16_BF16@BF16, E8M7Imm, F16Imm)")) {
                     Assert.a(var8 != null);
                     if (pC(var8, "BF16_V2", "E8M7_V2", "F64.BF16", "BF16")) {
                        double var46 = Float.intBitsToFloat((int)var16 << 16);
                        var16 = Double.doubleToRawLongBits(var46);
                     } else {
                        float var47 = Floats.fromFP16Bits((int)var16);
                        var16 = Double.doubleToRawLongBits(var47);
                     }
                  } else if (var18.equals("convertFloatType(1==1, E8M7Imm, E8M7Imm)")) {
                     double var19 = Float.intBitsToFloat((int)var16 << 16);
                     var16 = Double.doubleToRawLongBits(var19);
                  } else {
                     if (var18.startsWith("convertFloatType(")) {
                        throw new RuntimeException("TODO: " + var18);
                     }

                     int var41 = 0;

                     while (var41 < var18.length()) {
                        Couple var20 = pC(var18, var41);
                        String var21 = (String)var20.getFirst();
                        var41 = (Integer)var20.getSecond();
                        if (var21.equals("SCALE")) {
                           var41 = A(var18, var41);
                           var20 = kS(var18, var41);
                           var16 *= A((String)var20.getFirst());
                           var41 = (Integer)var20.getSecond();
                        } else {
                           if (!var21.equals("MULTIPLY")) {
                              throw new RuntimeException();
                           }

                           var41 = A(var18, var41);
                           var20 = kS(var18, var41);
                           var16 /= A((String)var20.getFirst());
                           var41 = (Integer)var20.getSecond();
                        }

                        var41 = A(var18, var41);
                     }
                  }
               } else if (var7 != null && var7.equals("F64Imm") && var14.A == 32) {
                  var16 <<= 32;
               }

               return var16;
            }
         }
      }

      for (Entry var24 : var1.ld.pC.entrySet()) {
         GK.bO var26 = (GK.bO)var24.getValue();
         if (!var26.pC()) {
            String var28 = null;
            int var31 = 0;

            for (GK.bO.Av var36 : var26.UT()) {
               if (var36.pC() && var36.A().equals(var2)) {
                  var28 = (String)var24.getKey();
                  break;
               }

               var31++;
            }

            if (var28 != null) {
               GK.K var34 = this.pC(var28, var3, var5);
               GK.zp var37 = (GK.zp)this.wS.get(var26.wS());
               if (var37 != null) {
                  int var38 = (int)var34.pC;
                  List var17 = var37.pC(var38);
                  if (var17 != null && var17.size() == var26.E()) {
                     return (long)((Integer)var17.get(var31)).intValue();
                  }
               } else if ("IDENTICAL".equals(var26.wS())) {
                  boolean var39 = var7 != null && var7.endsWith("SImm");
                  long var40 = var34.pC(var39);
                  return var40;
               }
            }
         }
      }

      if (var2.equals("pseudo_opcode") && var1.gp != null) {
         GK.bO var23 = var1.gp;
         GK.zp var25 = (GK.zp)this.wS.get(var23.kS);
         if (var25 != null) {
            ArrayList var27 = new ArrayList();

            for (GK.bO.Av var32 : var23.UT()) {
               Long var35 = this.pC(var1, var32.A(), var3, var5, null, null);
               var27.add(var35 == null ? null : var35.intValue());
            }

            Integer var30 = var25.pC(var27);
            return var30 == null ? 0L : var30.longValue();
         }
      }

      return null;
   }

   private static boolean pC(String var0, String... var1) {
      for (String var5 : var1) {
         if (var0.endsWith("." + var5) || var0.contains("." + var5 + ".")) {
            return true;
         }
      }

      return false;
   }

   public GK.K pC(String var1, long var2, long var4) {
      LongLong var6 = (LongLong)this.kS.get(var1);
      if (var6 == null) {
         throw new RuntimeException("Invalid encoding key: " + var1);
      } else {
         long var7 = 0L;
         int var9 = 0;
         long var10 = var6.lo;

         for (long var12 = var10 & var2; var10 != 0L; var12 >>>= 1) {
            if ((var10 & 1L) != 0L) {
               if ((var12 & 1L) != 0L) {
                  var7 |= 1L << var9;
               }

               var9++;
            }

            var10 >>>= 1;
         }

         var10 = var6.hi;

         for (long var15 = var10 & var4; var10 != 0L; var15 >>>= 1) {
            if ((var10 & 1L) != 0L) {
               if ((var15 & 1L) != 0L) {
                  var7 |= 1L << var9;
               }

               var9++;
            }

            var10 >>>= 1;
         }

         return new GK.K(var7, var9);
      }
   }

   public GK.KD pC(long var1, long var3) {
      long var5 = var1 & 4095L;
      List var7 = this.ys.getSafe(var5);
      if (var7.isEmpty()) {
         Assert.a(this.pC >= 75);
         long var8 = var3 >> 27 & 1L;
         var5 = var8 << 12 | var5;
         var7 = this.ld.getSafe(var5);
         if (var7.isEmpty()) {
            throw new RuntimeException("Unknown opcode");
         }
      }

      GK.KD var26 = null;
      int var9 = Integer.MIN_VALUE;
      boolean var10 = false;
      ArrayList var11 = new ArrayList();

      for (GK.KD var13 : var7) {
         int var14 = 0;

         for (String var16 : var13.E) {
            GK.bO var17 = var13.ld.pC(var16);
            if (var17 != null && var17.pC()) {
               GK.K var18 = this.pC(var16, var1, var3);
               Assert.a(var18.A <= 32);
               int var19 = (int)var18.pC;
               GK.bO.Av var20 = var17.A();
               if (var20.pC()) {
                  GK.Ws var21 = var13.pC(var20.A());
                  if (var21 != null) {
                     String var22 = var21.pC.pC;
                     Map var23 = this.UT.pC(var22);
                     if (var23 != null) {
                        Collection var24 = var23.values();
                        if (!var24.contains(var19)) {
                           var14 = Integer.MIN_VALUE;
                           break;
                        }

                        var14++;
                     }
                  }
               } else if (var20.kS()) {
                  if (var20.wS() != var19) {
                     var14 = Integer.MIN_VALUE;
                     break;
                  }

                  var14++;
               }
            }
         }

         if (var14 > var9) {
            var26 = var13;
            var9 = var14;
            var10 = false;
            var11.clear();
         } else if (var14 == var9) {
            var10 = true;
            var11.add(var13);
         }
      }

      if (var26 == null) {
         throw new RuntimeException("Cannot find opcode (no top candidate)");
      } else if (var10) {
         throw new RuntimeException(
            Strings.ff(
               "Cannot find opcode (multiple candidates with top score %d): %s, %s",
               var9,
               var26.pC,
               var11.stream().map(var0 -> var0.pC).collect(Collectors.toList())
            )
         );
      } else {
         return var26;
      }
   }

   @Ser
   public static class Av {
      @SerId(1)
      GK.Ws pC;
      @SerId(2)
      GK.Ws A;

      @Override
      public String toString() {
         Object var1 = "";
         if (this.pC != null) {
            var1 = var1 + this.pC + " = ";
         }

         return var1 + this.A;
      }
   }

   @Ser
   public static class DH {
      @SerId(1)
      Map pC = new HashMap();

      Map pC(String var1) {
         return (Map)this.pC.get(var1);
      }

      LinkedHashSet pC(String var1, int var2) {
         LinkedHashSet var3 = new LinkedHashSet();
         LinkedHashMap var4 = (LinkedHashMap)this.pC.get(var1);
         if (var4 != null) {
            for (Entry var6 : var4.entrySet()) {
               if ((Integer)var6.getValue() == var2) {
                  var3.add((String)var6.getKey());
               }
            }
         }

         return var3;
      }

      boolean pC(String var1, int var2, String var3) {
         LinkedHashMap var4 = (LinkedHashMap)this.pC.get(var1);
         if (var4 != null) {
            for (Entry var6 : var4.entrySet()) {
               if ((Integer)var6.getValue() == var2) {
                  String var7 = (String)var6.getKey();
                  if (var7.equals(var3)) {
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   @Ser
   public static class HE {
      @SerId(1)
      String pC;
      @SerId(2)
      Integer A;
      @SerId(3)
      Long kS;
      @SerId(4)
      String wS;
      @SerId(5)
      boolean UT;
      @SerId(6)
      boolean E;
      @SerId(7)
      String sY;

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder(this.pC);
         if (this.A != null) {
            Strings.ff(var1, "(%d)", this.A);
         }

         if (this.kS != null) {
            Strings.ff(var1, "{%d}", this.kS);
         }

         if (this.wS != null) {
            Strings.ff(var1, "{%s}", this.wS);
         }

         return var1.toString();
      }
   }

   @Ser
   public static class K {
      @SerId(1)
      long pC;
      @SerId(2)
      int A;

      K(long var1, int var3) {
         Assert.a(var3 >= 1 && var3 <= 64 && (var3 == 64 || var1 >>> var3 == 0L));
         this.pC = var1;
         this.A = var3;
      }

      long pC(boolean var1) {
         if (var1 && this.A < 64 && this.pC >>> this.A - 1 != 0L) {
            long var2 = Long.MIN_VALUE >> 64 - this.A - 1;
            return this.pC | var2;
         } else {
            return this.pC;
         }
      }
   }

   @Ser
   public static class KD {
      @SerId(1)
      String pC;
      @SerId(2)
      boolean A;
      @SerId(3)
      String kS;
      @SerId(4)
      long wS;
      @SerId(5)
      boolean UT;
      @SerId(6)
      Set E;
      @SerId(7)
      Map sY;
      @SerId(8)
      Map ys;
      @SerId(9)
      GK.cq ld;
      @SerId(10)
      GK.bO gp;
      @SerId(11)
      GK.Ws oT;
      @SerId(12)
      List fI;
      @SerId(13)
      List WR;
      @SerId(14)
      List NS;
      @SerId(15)
      int vP;
      @SerId(16)
      int xC;
      @SerId(17)
      int ED;

      GK.Ws pC(String var1) {
         if (this.oT.pC(var1)) {
            return this.oT;
         } else {
            for (GK.Ws var3 : this.fI) {
               if (var3.pC(var1)) {
                  return var3;
               }
            }

            for (GK.yt var12 : this.WR) {
               label96:
               for (GK.RC var5 : var12.A) {
                  if (var5.pC()) {
                     GK.Sv var15 = var5.pC;
                     if (var15.pC.pC(var1)) {
                        return var15.pC;
                     }

                     for (GK.Ws var18 : var15.A) {
                        if (var18.pC(var1)) {
                           return var18;
                        }
                     }
                  } else {
                     for (GK.Sv var7 : var5.A) {
                        if (var7.pC.pC(var1)) {
                           return var7.pC;
                        }

                        for (GK.Ws var9 : var7.A) {
                           if (var9.pC(var1)) {
                              return var9;
                           }
                        }
                     }

                     Iterator var14 = var5.kS.iterator();

                     GK.Ws var16;
                     do {
                        if (!var14.hasNext()) {
                           continue label96;
                        }

                        var16 = (GK.Ws)var14.next();
                     } while (!var16.pC(var1));

                     return var16;
                  }
               }
            }

            for (GK.Av var13 : this.NS) {
               if (var13.A.pC(var1)) {
                  return var13.A;
               }

               if (var13.pC != null && var13.pC.pC(var1)) {
                  return var13.pC;
               }
            }

            return null;
         }
      }

      @Override
      public String toString() {
         return this.pC(false);
      }

      public String pC(boolean var1) {
         return this.pC(true, true, true, true);
      }

      public String pC(boolean var1, boolean var2, boolean var3, boolean var4) {
         StringBuilder var5 = new StringBuilder();
         if (this.A) {
            var5.append("^");
         }

         Strings.ff(var5, "0x%X:'%s':%s", this.wS, this.pC, this.kS);
         if (var2) {
            for (GK.Ws var7 : this.fI) {
               Strings.ff(var5, "/%s", var7.A);
            }
         }

         if (var3) {
            for (GK.yt var10 : this.WR) {
               if (var1) {
                  var5.append("\n");
               }

               Strings.ff(var5, " %s", var10);
            }
         }

         if (var4) {
            for (GK.Av var11 : this.NS) {
               if (var1) {
                  var5.append("\n");
               }

               Strings.ff(var5, " {%s}", var11);
            }
         }

         return var5.toString();
      }
   }

   @Ser
   public static class RC {
      @SerId(1)
      GK.Sv pC;
      @SerId(2)
      List A;
      @SerId(3)
      List kS;

      boolean pC() {
         return this.pC != null;
      }

      GK.Sv pC(int var1) {
         if (var1 < 0) {
            var1 += this.A.size();
         }

         return (GK.Sv)this.A.get(var1);
      }

      @Override
      public String toString() {
         return this.pC != null ? this.pC.toString() : "[" + Strings.join(" + ", this.A) + "]" + Strings.join("/", this.kS);
      }
   }

   @Ser
   public static class Sv {
      @SerId(1)
      GK.Ws pC;
      @SerId(2)
      List A;

      @Override
      public String toString() {
         String var1 = this.pC.toString();

         for (GK.Ws var3 : this.A) {
            var1 = var1 + Strings.ff("/%s", var3);
         }

         return var1;
      }
   }

   @Ser
   public static class Ws {
      @SerId(1)
      GK.HE pC;
      @SerId(2)
      String A;

      boolean pC(String var1) {
         return this.A.equals(var1);
      }

      @Override
      public String toString() {
         return this.pC + ":" + this.A;
      }
   }

   @Ser
   public static class bO {
      @SerId(1)
      private GK.bO.Av pC;
      @SerId(2)
      private String A;
      @SerId(3)
      private String kS;
      @SerId(4)
      private List wS;

      boolean pC() {
         return this.pC != null;
      }

      GK.bO.Av A() {
         return this.pC;
      }

      public String kS() {
         return this.A;
      }

      String wS() {
         return this.kS;
      }

      List UT() {
         return this.wS;
      }

      int E() {
         return this.wS.size();
      }

      @Override
      public String toString() {
         return this.pC != null ? this.pC.toString() : Strings.ff("%s(%s)", this.kS, Strings.join(", ", this.wS));
      }

      @Ser
      public static class Av {
         @SerId(1)
         private Object pC;

         boolean pC() {
            return this.pC instanceof String;
         }

         String A() {
            return (String)this.pC;
         }

         boolean kS() {
            return this.pC instanceof Integer;
         }

         int wS() {
            return (Integer)this.pC;
         }

         @Override
         public String toString() {
            return this.pC.toString();
         }
      }
   }

   @Ser
   public static class cq {
      @SerId(1)
      Map pC = new LinkedHashMap();

      GK.bO pC(String var1) {
         GK.bO var2 = (GK.bO)this.pC.get(var1);
         Assert.a(var2 != null);
         return var2;
      }

      @Override
      public String toString() {
         return this.pC.toString();
      }
   }

   @Ser
   public static class rQ {
      static GK.rQ pC = new GK.rQ();

      @Ser
      public static class Av extends GK.rQ {
         @SerId(1)
         int A;
      }

      @Ser
      public static class Sv extends GK.rQ {
         @SerId(1)
         String A;
      }
   }

   public static class sy {
      final GK pC;
      final GK.KD A;
      final long kS;
      final long wS;

      private sy(GK var1, GK.KD var2, long var3, long var5) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var5;
      }

      public Long pC(String var1, String var2, String var3) {
         return this.pC.pC(this.A, var1, this.kS, this.wS, var2, var3);
      }

      public Long pC(String var1) {
         return this.pC(var1, null, null);
      }
   }

   @Ser
   public static class yt {
      @SerId(1)
      int pC;
      @SerId(2)
      List A;

      static String pC(int var0) {
         if (var0 == 0) {
            return "";
         } else {
            String var1 = "";
            if ((var0 & 1) != 0) {
               var1 = var1 + "!";
            }

            if ((var0 & 2) != 0) {
               var1 = var1 + "-";
            }

            if ((var0 & 4) != 0) {
               var1 = var1 + "||";
            }

            if ((var0 & 8) != 0) {
               var1 = var1 + "~";
            }

            if (!var1.isEmpty()) {
               var1 = "[" + var1 + "]";
            }

            return var1;
         }
      }

      @Override
      public String toString() {
         return pC(this.pC) + Strings.join(" ", this.A);
      }
   }

   @Ser
   public static class zp {
      @SerId(1)
      String pC;
      @SerId(2)
      int A;
      @SerId(3)
      LinkedHashMap kS;

      Integer pC(List var1) {
         if (this.A == var1.size()) {
            label30:
            for (Entry var3 : this.kS.entrySet()) {
               List var4 = (List)var3.getKey();
               Assert.a(var4.size() == var1.size());
               int var5 = 0;

               for (Integer var7 : var4) {
                  if (var7 != null && !var7.equals(var1.get(var5))) {
                     continue label30;
                  }

                  var5++;
               }

               return ((GK.zp.Av)var3.getValue()).pC;
            }
         }

         return null;
      }

      List pC(int var1) {
         for (Entry var3 : this.kS.entrySet()) {
            if (((GK.zp.Av)var3.getValue()).pC(var1)) {
               return (List)var3.getKey();
            }
         }

         return null;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append(this.pC);

         for (Entry var3 : this.kS.entrySet()) {
            Strings.ff(var1, "\n%s -> %s", var3.getKey(), var3.getValue());
         }

         return var1.toString();
      }

      @Ser
      public static class Av {
         @SerId(1)
         private int pC;
         @SerId(2)
         private Set A;

         boolean pC(int var1) {
            return this.pC == var1 || this.A != null && this.A.contains(var1);
         }

         @Override
         public String toString() {
            return this.A == null ? this.pC + "" : this.pC + " " + Strings.join(" ", this.A);
         }
      }
   }
}
