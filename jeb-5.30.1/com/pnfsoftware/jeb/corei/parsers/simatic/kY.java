package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ByteArray;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class kY {
   private static final ILogger q = GlobalLog.getLogger(kY.class);
   private ByteArray RF;
   private ByteArray xK;
   private int Dw;
   private int Uv;
   private int oW;
   private int gO;
   private Map nf = new TreeMap();

   public static kY q(byte[] var0) {
      return new kY(var0);
   }

   private kY(byte[] var1) {
      this.RF = new ByteArray(var1);
      this.Dw = this.RF.u8();
      this.Uv = this.RF.u16();
      this.oW = this.RF.u16();
      this.gO = this.RF.u16();
      Assert.a(this.RF.position() == 7);
      int var2 = 7 + this.oW;
      Assert.a(var2 <= var1.length);
      this.xK = new ByteArray(var1, var2);

      while (this.RF.position() < var2) {
         this.q(null);
      }

      for (kY.eo var4 : this.nf.values()) {
         var4.Dw();
      }
   }

   public int q() {
      return this.Dw;
   }

   public int RF() {
      return this.Uv;
   }

   public int xK() {
      return this.oW;
   }

   public int Dw() {
      return this.gO;
   }

   public kY.eo q(S7.SectionType var1) {
      return (kY.eo)this.nf.get(var1.getId());
   }

   public Collection Uv() {
      return Collections.unmodifiableCollection(this.nf.values());
   }

   private kY.eo gO() {
      kY.eo var1 = new kY.eo(0);
      int var2 = this.RF.u8();
      if (var2 == 255) {
         var2 = this.RF.u16();
      }

      for (int var3 = 0; var3 < var2; var3++) {
         this.q(var1);
      }

      var1.Dw();
      return var1;
   }

   private void q(kY.eo var1) {
      int var2 = this.RF.u8();
      int var3 = this.RF.u8();
      if ((var3 & 16) != 0) {
         throw new RuntimeException("TBI: handle 'extended' flag for categories (?)");
      } else {
         int var4 = var3 & 7;
         if (var1 == null) {
            var1 = (kY.eo)this.nf.get(var4);
            if (var1 == null) {
               var1 = new kY.eo(var4);
               this.nf.put(var4, var1);
            }
         }

         Object var5 = null;
         S7.DataType var9 = S7.DataType.fromId(var2);
         kY.nI var6;
         int var7;
         int var8;
         switch (var9) {
            case MULTI_INST_FB:
            case MULTI_INST_SFB:
               throw new RuntimeException("TBI");
            case ARRAY:
               var6 = new kY.nI(var2);
               int var22 = this.RF.u8();
               Assert.a(var22 >= 1);
               var6.Uv = new int[2 * var22];
               int var11 = 0;
               int var12 = 1;
               int var13 = 1;
               int var14 = 0;

               for (; var14 < var22; var14++) {
                  int var15 = this.RF.i16();
                  int var16 = this.RF.i16();
                  Assert.a(var15 <= var16);
                  var6.Uv[var11++] = var15;
                  var6.Uv[var11++] = var16;
                  int var17 = var16 - var15 + 1;
                  if (var14 < var22 - 1) {
                     var12 *= var17;
                  } else {
                     var13 = var17;
                  }
               }

               Assert.a(var12 >= 1);
               Assert.a(var13 >= 1);
               var14 = var12 * var13;
               var6.oW = this.RF.u8();
               var6.gO = this.RF.u8();
               S7.DataType var25 = S7.DataType.fromId(var6.oW);
               if (var25 == S7.DataType.STRUCT) {
                  var6.nf = this.gO();
                  var7 = var14 * var6.nf.Dw;
               } else if (var25 == S7.DataType.STRING) {
                  int var26 = this.RF.u8();
                  var6.Dw = var26;
                  int var29 = this.RF(16 + var26 * 8);
                  var7 = var14 * var29;
               } else {
                  Assert.a(var25 != S7.DataType.ARRAY);
                  if (var25 == S7.DataType.MULTI_INST_FB || var25 == S7.DataType.MULTI_INST_SFB) {
                     throw new RuntimeException("TBI");
                  }

                  int var27 = this.q(var25.getBitsize() * var13);
                  var7 = this.RF(var12 * var27);
               }

               var8 = 16;
               if ((var3 & 8) != 0) {
                  int var28 = this.xK.u16();
                  this.xK.u8();
                  this.xK.u8();
                  ArrayList var18 = new ArrayList();

                  for (int var19 = 0; var19 < var28; var19++) {
                     int var20 = this.xK.u16();
                     Object var21 = this.q(S7.DataType.fromId(var6.oW));
                     var18.add(new Couple(var20, var21));
                  }

                  var6.xK = var18;
               }
               break;
            case STRUCT:
               var6 = new kY.nI(var2);
               var6.nf = this.gO();
               var7 = var6.nf.Dw;
               var8 = 16;
               break;
            case STRING:
               int var10 = this.RF.u8();
               if ((var3 & 8) != 0) {
                  var5 = this.q(var9);
               }

               var6 = new kY.nI(var2, var5, var10);
               var7 = 16 + var10 * 8;
               var8 = 16;
               break;
            default:
               if ((var3 & 8) != 0) {
                  var5 = this.q(var9);
               }

               var6 = new kY.nI(var2, var5);
               var7 = var9.getBitsize();
               var8 = Math.min(16, var7);
         }

         var6.RF = this.q(var1.Dw, var8);
         var1.Dw = var6.RF + var7;
         Assert.a(var6 != null);
         var1.q(var6);
      }
   }

   private int q(int var1, int var2) {
      return (var1 + var2 - 1) / var2 * var2;
   }

   private int q(int var1) {
      return (var1 + 7) / 8 * 8;
   }

   private int RF(int var1) {
      return (var1 + 15) / 16 * 16;
   }

   private Object q(S7.DataType var1) {
      return q(var1, this.xK, 0, false);
   }

   private static Object q(S7.DataType var0, ByteArray var1, int var2, boolean var3) {
      Assert.a(var0 == S7.DataType.BOOL || var2 == 0);
      switch (var0) {
         case STRING:
            int var16 = var1.u8();
            int var5 = var1.u8();
            return new S7.StringA(var16, var1.get(var5));
         case BOOL:
            int var15 = var1.u8();
            if (var3) {
               Assert.a(var2 >= 0 && var2 <= 7);
               if (var2 < 7) {
                  var1.position(var1.position() - 1);
               }
            } else {
               Assert.a(var2 == 0);
            }

            return (var15 >> var2 & 1) == 1;
         case BYTE:
            return (byte)var1.u8();
         case CHAR:
            return (char)var1.u8();
         case WORD:
            short var14 = (short)var1.u16();
            return var3 ? EndianUtil.swapShort(var14) : var14;
         case INT:
            short var13 = (short)var1.u16();
            return var3 ? EndianUtil.swapShort(var13) : var13;
         case DWORD:
            int var12 = (int)var1.u32();
            return var3 ? EndianUtil.swapInt(var12) : var12;
         case DINT:
            int var11 = (int)var1.u32();
            return var3 ? EndianUtil.swapInt(var11) : var11;
         case REAL:
            int var10 = var1.i32();
            return Float.intBitsToFloat(var3 ? EndianUtil.swapInt(var10) : var10);
         case S5TIME:
            short var9 = (short)var1.u16();
            return S7.S5Time.create(var3 ? EndianUtil.swapShort(var9) : var9);
         case TIME:
            int var8 = var1.i32();
            return S7.Time.create(var3 ? EndianUtil.swapInt(var8) : var8);
         case DATE:
            short var7 = (short)var1.u16();
            return S7.Date.create(var3 ? EndianUtil.swapShort(var7) : var7);
         case TIME_OF_DAY:
            int var6 = (int)var1.u32();
            return S7.TimeOfDay.create(var3 ? EndianUtil.swapInt(var6) : var6);
         case DATE_AND_TIME:
            long var4 = EndianUtil.swapLong(var1.i64());
            return S7.DateAndTime.create(var4);
         default:
            throw new RuntimeException("Cannot decode initial value for type: " + var0);
      }
   }

   @Override
   public String toString() {
      return this.oW();
   }

   public String oW() {
      return this.RF(null);
   }

   public String RF(byte[] var1) {
      return this.q(var1, true);
   }

   public String q(byte[] var1, boolean var2) {
      StringBuilder var3 = new StringBuilder();
      ByteArray var4 = var1 == null ? null : new ByteArray(var1);
      int var5 = 0;
      int var6 = 0;

      for (kY.eo var8 : this.nf.values()) {
         if (var6 > 0) {
            var3.append('\n');
         }

         Strings.ff(var3, "%s:\n", var8.q());
         var8.q(var3, var2, 0, var5, var4);
         var5 += var8.Dw;
         var6++;
      }

      if (var2) {
         Strings.ff(var3, "\n%5d.%d:  -", var5 >>> 3, var5 & 7);
      }

      return var3.toString();
   }

   public static class CU implements Iterator {
      private List q;
      private int RF;
      private Object xK;
      private int Dw;

      CU(List var1) {
         this.q = var1;
         this.q();
      }

      @Override
      public boolean hasNext() {
         return this.RF > 0;
      }

      @Override
      public Object next() {
         if (this.RF <= 0) {
            throw new NoSuchElementException();
         } else {
            this.RF--;
            Object var1 = this.xK;
            if (this.RF == 0) {
               this.Dw++;
               this.q();
            }

            return var1;
         }
      }

      private void q() {
         if (this.Dw < this.q.size()) {
            Couple var1 = (Couple)this.q.get(this.Dw);
            this.RF = (Integer)var1.getFirst();
            this.xK = var1.getSecond();
         }
      }
   }

   public static class eo {
      private int q;
      private List RF = new ArrayList();
      private int xK;
      private int Dw;

      eo(int var1) {
         this.q = var1;
      }

      public S7.SectionType q() {
         return S7.SectionType.fromId(this.q);
      }

      public List RF() {
         return Collections.unmodifiableList(this.RF);
      }

      public kY.nI q(int var1) {
         return (kY.nI)this.RF.get(var1);
      }

      public int xK() {
         return this.RF.size();
      }

      void q(kY.nI var1) {
         this.RF.add(var1);
      }

      void Dw() {
         this.Dw = (this.Dw + 15) / 16 * 16;
      }

      public int Uv() {
         return this.xK;
      }

      public int oW() {
         return this.Dw;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         this.q(var1, true, 0, 0, null);
         return var1.toString();
      }

      void q(StringBuilder var1, boolean var2, int var3, int var4, ByteArray var5) {
         int var6 = 0;

         for (kY.nI var8 : this.RF) {
            if (var6 > 0) {
               var1.append('\n');
            }

            if (var2) {
               Strings.ff(var1, "%5d.%d:  ", var4 + var8.RF >>> 3, var4 + var8.RF & 7);
            }

            if (var3 > 0) {
               var1.append(Strings.spaces(var3 * 2));
            }

            var8.q(var1, var2, var4, var3, var5);
            var6++;
         }
      }
   }

   public static class nI {
      private int q;
      private int RF;
      private Object xK;
      private int Dw;
      private int[] Uv;
      private int oW;
      private int gO;
      private kY.eo nf;

      nI(int var1) {
         this(var1, null);
      }

      nI(int var1, Object var2) {
         this(var1, var2, 0);
      }

      nI(int var1, Object var2, int var3) {
         this.q = var1;
         this.xK = var2;
         this.Dw = var3;
      }

      public S7.DataType q() {
         return S7.DataType.fromId(this.q);
      }

      public int RF() {
         return this.RF;
      }

      public Object xK() {
         return this.xK;
      }

      public int Dw() {
         return this.Dw;
      }

      public List Uv() {
         return this.xK == null ? Collections.emptyList() : (List)this.xK;
      }

      public kY.CU oW() {
         List var1;
         if (this.xK == null) {
            var1 = Collections.emptyList();
         } else {
            var1 = (List)this.xK;
         }

         return new kY.CU(var1);
      }

      public List gO() {
         return Collections.unmodifiableList(ArrayUtil.asList(this.Uv));
      }

      public int nf() {
         return this.gP();
      }

      public int gP() {
         int var1 = this.Uv[this.Uv.length - 2];
         int var2 = this.Uv[this.Uv.length - 1];
         return var2 - var1 + 1;
      }

      public int[] za() {
         int[] var1 = this.lm();
         return Arrays.copyOf(var1, var1.length - 1);
      }

      public int[] lm() {
         int[] var1 = new int[this.Uv.length / 2];
         int var2 = 0;

         for (int var3 = this.Uv.length - 2; var3 >= 0; var3 -= 2) {
            int var4 = this.Uv[var3];
            int var5 = this.Uv[var3 + 1];
            int var6 = var5 - var4 + 1;
            var1[var2++] = var6;
         }

         return var1;
      }

      public int zz() {
         int var1 = 1;

         for (int var5 : this.lm()) {
            var1 *= var5;
         }

         return var1;
      }

      public int JY() {
         int[] var1 = this.lm();
         int var2 = 1;

         for (int var3 = 1; var3 < var1.length; var3++) {
            var2 *= var1[var3];
         }

         return var2;
      }

      public S7.DataType HF() {
         return S7.DataType.fromId(this.oW);
      }

      public int[] q(int var1) {
         int[] var2 = new int[this.Uv.length / 2];
         int var3 = var2.length;

         for (int var4 = this.Uv.length - 2; var4 >= 0; var4 -= 2) {
            int var5 = this.Uv[var4];
            int var6 = this.Uv[var4 + 1];
            int var7 = var6 - var5 + 1;
            int var8 = var1 % var7;
            var1 /= var7;
            var2[var3 - 1] = var5 + var8;
            var3--;
         }

         return var2;
      }

      public kY.eo LK() {
         return this.nf;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         this.q(var1, true, 0, 0, null);
         return var1.toString();
      }

      void q(StringBuilder var1, boolean var2, int var3, int var4, ByteArray var5) {
         int var6 = var3 + this.RF;
         S7.DataType var7 = S7.DataType.fromId(this.q);
         switch (var7) {
            case MULTI_INST_FB:
            case MULTI_INST_SFB:
               throw new RuntimeException("TBI: rendering for multi-instance data blocks");
            case ARRAY:
               var1.append("ARRAY[");

               for (byte var15 = 0; var15 < this.Uv.length; var15 += 2) {
                  if (var15 > 0) {
                     var1.append(", ");
                  }

                  Strings.ff(var1, "%d..%d", this.Uv[var15], this.Uv[var15 + 1]);
               }

               var1.append(']');
               S7.DataType var16 = this.HF();
               Strings.ff(var1, " of %s", var16);
               if (var16 == S7.DataType.STRING && this.Dw != 254) {
                  Strings.ff(var1, "[%d]", this.Dw);
               }

               if (var5 != null) {
                  var1.append(": ");
                  var5.position(var6 / 8);
                  if (var16 == S7.DataType.STRUCT) {
                     int var9 = 0;

                     for (int var10 = 0; var10 < this.JY(); var10++) {
                        for (int var11 = 0; var11 < this.nf(); var11++) {
                           var1.append("\n");
                           this.nf.q(var1, var2, var4 + 1, var6, var5);
                           var6 += this.nf.oW();
                           Strings.ff(var1, "  // end of struct item %s", Arrays.toString(this.q(var9)));
                           var9++;
                        }
                     }
                  } else if (var16 == S7.DataType.BOOL) {
                     int var17 = this.nf();
                     int var20 = 0;

                     for (int var23 = 0; var23 < this.JY(); var23++) {
                        for (int var12 = 0; var12 < var17; var12++) {
                           if (var20 > 0) {
                              var1.append(", ");
                           }

                           Object var13 = kY.q(var16, var5, var12 & 7, true);
                           Strings.ff(var1, Formatter.toString(var13));
                           var20++;
                        }

                        if (var17 % 8 != 0) {
                           var5.position(var5.position() + 1);
                        }
                     }
                  } else {
                     int var18 = 0;

                     for (int var21 = 0; var21 < this.JY(); var21++) {
                        for (int var24 = 0; var24 < this.nf(); var24++) {
                           if (var18 > 0) {
                              var1.append(", ");
                           }

                           Object var26 = kY.q(var16, var5, 0, true);
                           Strings.ff(var1, Formatter.toString(var26));
                           var18++;
                        }
                     }
                  }
               } else if (var16 == S7.DataType.STRUCT) {
                  var1.append('\n');
                  this.nf.q(var1, var2, var4 + 1, var6, var5);
               } else if (this.xK != null) {
                  var1.append(": ");
                  List var19 = (List)this.xK;
                  int var22 = 0;

                  for (Couple var27 : var19) {
                     if (var22 > 0) {
                        var1.append(", ");
                     }

                     int var28 = (Integer)var27.getFirst();
                     Object var14 = var27.getSecond();
                     if (var28 == 1) {
                        Strings.ff(var1, "%s", Formatter.toString(var14));
                     } else {
                        Strings.ff(var1, "%d (%s)", var28, Formatter.toString(var14));
                     }

                     var22++;
                  }
               }
               break;
            case STRUCT:
               Strings.ff(var1, "STRUCT:\n");
               this.nf.q(var1, var2, var4 + 1, var6, var5);
               break;
            case STRING:
            default:
               Strings.ff(var1, "%s", var7);
               if (var7 == S7.DataType.STRING && this.Dw != 254) {
                  Strings.ff(var1, "[%d]", this.Dw);
               }

               Object var8;
               if (var5 != null) {
                  var5.position(var6 / 8);
                  var8 = kY.q(var7, var5, var6 & 7, true);
               } else {
                  var8 = this.xK;
               }

               if (var8 != null) {
                  Strings.ff(var1, ": %s", Formatter.toString(var8));
               }
         }
      }
   }
}
