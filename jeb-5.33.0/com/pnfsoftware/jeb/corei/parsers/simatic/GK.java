package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ByteArray;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class GK {
   private static final ILogger pC = GlobalLog.getLogger(GK.class);
   private ByteArray A;
   private ByteArray kS;
   private int wS;
   private int UT;
   private int E;
   private int sY;
   private Map ys = new TreeMap();

   public static GK pC(byte[] var0) {
      return new GK(var0);
   }

   private GK(byte[] var1) {
      this.A = new ByteArray(var1);
      this.wS = this.A.u8();
      this.UT = this.A.u16();
      this.E = this.A.u16();
      this.sY = this.A.u16();
      Assert.a(this.A.position() == 7);
      int var2 = 7 + this.E;
      Assert.a(var2 <= var1.length);
      this.kS = new ByteArray(var1, var2);

      while (this.A.position() < var2) {
         this.pC(null);
      }

      for (GK.Av var4 : this.ys.values()) {
         var4.kS();
      }
   }

   public GK.Av pC(S7.SectionType var1) {
      return (GK.Av)this.ys.get(var1.getId());
   }

   private GK.Av A() {
      GK.Av var1 = new GK.Av(0);
      int var2 = this.A.u8();
      if (var2 == 255) {
         var2 = this.A.u16();
      }

      for (int var3 = 0; var3 < var2; var3++) {
         this.pC(var1);
      }

      var1.kS();
      return var1;
   }

   private void pC(GK.Av var1) {
      int var2 = this.A.u8();
      int var3 = this.A.u8();
      if ((var3 & 16) != 0) {
         throw new RuntimeException("TBI: handle 'extended' flag for categories (?)");
      } else {
         int var4 = var3 & 7;
         if (var1 == null) {
            var1 = (GK.Av)this.ys.get(var4);
            if (var1 == null) {
               var1 = new GK.Av(var4);
               this.ys.put(var4, var1);
            }
         }

         Object var5 = null;
         S7.DataType var9 = S7.DataType.fromId(var2);
         GK.K var6;
         int var7;
         int var8;
         switch (var9) {
            case MULTI_INST_FB:
            case MULTI_INST_SFB:
               throw new RuntimeException("TBI");
            case ARRAY:
               var6 = new GK.K(var2);
               int var22 = this.A.u8();
               Assert.a(var22 >= 1);
               var6.UT = new int[2 * var22];
               int var11 = 0;
               int var12 = 1;
               int var13 = 1;
               int var14 = 0;

               for (; var14 < var22; var14++) {
                  int var15 = this.A.i16();
                  int var16 = this.A.i16();
                  Assert.a(var15 <= var16);
                  var6.UT[var11++] = var15;
                  var6.UT[var11++] = var16;
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
               var6.E = this.A.u8();
               var6.sY = this.A.u8();
               S7.DataType var25 = S7.DataType.fromId(var6.E);
               if (var25 == S7.DataType.STRUCT) {
                  var6.ys = this.A();
                  var7 = var14 * var6.ys.kS;
               } else if (var25 == S7.DataType.STRING) {
                  int var26 = this.A.u8();
                  var6.wS = var26;
                  int var29 = this.A(16 + var26 * 8);
                  var7 = var14 * var29;
               } else {
                  Assert.a(var25 != S7.DataType.ARRAY);
                  if (var25 == S7.DataType.MULTI_INST_FB || var25 == S7.DataType.MULTI_INST_SFB) {
                     throw new RuntimeException("TBI");
                  }

                  int var27 = this.pC(var25.getBitsize() * var13);
                  var7 = this.A(var12 * var27);
               }

               var8 = 16;
               if ((var3 & 8) != 0) {
                  int var28 = this.kS.u16();
                  this.kS.u8();
                  this.kS.u8();
                  ArrayList var18 = new ArrayList();

                  for (int var19 = 0; var19 < var28; var19++) {
                     int var20 = this.kS.u16();
                     Object var21 = this.pC(S7.DataType.fromId(var6.E));
                     var18.add(new Couple(var20, var21));
                  }

                  var6.kS = var18;
               }
               break;
            case STRUCT:
               var6 = new GK.K(var2);
               var6.ys = this.A();
               var7 = var6.ys.kS;
               var8 = 16;
               break;
            case STRING:
               int var10 = this.A.u8();
               if ((var3 & 8) != 0) {
                  var5 = this.pC(var9);
               }

               var6 = new GK.K(var2, var5, var10);
               var7 = 16 + var10 * 8;
               var8 = 16;
               break;
            default:
               if ((var3 & 8) != 0) {
                  var5 = this.pC(var9);
               }

               var6 = new GK.K(var2, var5);
               var7 = var9.getBitsize();
               var8 = Math.min(16, var7);
         }

         var6.A = this.pC(var1.kS, var8);
         var1.kS = var6.A + var7;
         Assert.a(var6 != null);
         var1.pC(var6);
      }
   }

   private int pC(int var1, int var2) {
      return (var1 + var2 - 1) / var2 * var2;
   }

   private int pC(int var1) {
      return (var1 + 7) / 8 * 8;
   }

   private int A(int var1) {
      return (var1 + 15) / 16 * 16;
   }

   private Object pC(S7.DataType var1) {
      return pC(var1, this.kS, 0, false);
   }

   private static Object pC(S7.DataType var0, ByteArray var1, int var2, boolean var3) {
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
      return this.pC();
   }

   public String pC() {
      return this.A(null);
   }

   public String A(byte[] var1) {
      return this.pC(var1, true);
   }

   public String pC(byte[] var1, boolean var2) {
      StringBuilder var3 = new StringBuilder();
      ByteArray var4 = var1 == null ? null : new ByteArray(var1);
      int var5 = 0;
      int var6 = 0;

      for (GK.Av var8 : this.ys.values()) {
         if (var6 > 0) {
            var3.append('\n');
         }

         Strings.ff(var3, "%s:\n", var8.pC());
         var8.pC(var3, var2, 0, var5, var4);
         var5 += var8.kS;
         var6++;
      }

      if (var2) {
         Strings.ff(var3, "\n%5d.%d:  -", var5 >>> 3, var5 & 7);
      }

      return var3.toString();
   }

   public static class Av {
      private int pC;
      private List A = new ArrayList();
      private int kS;

      Av(int var1) {
         this.pC = var1;
      }

      public S7.SectionType pC() {
         return S7.SectionType.fromId(this.pC);
      }

      public List A() {
         return Collections.unmodifiableList(this.A);
      }

      void pC(GK.K var1) {
         this.A.add(var1);
      }

      void kS() {
         this.kS = (this.kS + 15) / 16 * 16;
      }

      public int wS() {
         return this.kS;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         this.pC(var1, true, 0, 0, null);
         return var1.toString();
      }

      void pC(StringBuilder var1, boolean var2, int var3, int var4, ByteArray var5) {
         int var6 = 0;

         for (GK.K var8 : this.A) {
            if (var6 > 0) {
               var1.append('\n');
            }

            if (var2) {
               Strings.ff(var1, "%5d.%d:  ", var4 + var8.A >>> 3, var4 + var8.A & 7);
            }

            if (var3 > 0) {
               var1.append(Strings.spaces(var3 * 2));
            }

            var8.pC(var1, var2, var4, var3, var5);
            var6++;
         }
      }
   }

   public static class K {
      private int pC;
      private int A;
      private Object kS;
      private int wS;
      private int[] UT;
      private int E;
      private int sY;
      private GK.Av ys;

      K(int var1) {
         this(var1, null);
      }

      K(int var1, Object var2) {
         this(var1, var2, 0);
      }

      K(int var1, Object var2, int var3) {
         this.pC = var1;
         this.kS = var2;
         this.wS = var3;
      }

      public S7.DataType pC() {
         return S7.DataType.fromId(this.pC);
      }

      public int A() {
         return this.A;
      }

      public Object kS() {
         return this.kS;
      }

      public int wS() {
         return this.wS;
      }

      public GK.Sv UT() {
         List var1;
         if (this.kS == null) {
            var1 = Collections.emptyList();
         } else {
            var1 = (List)this.kS;
         }

         return new GK.Sv(var1);
      }

      public int E() {
         return this.sY();
      }

      public int sY() {
         int var1 = this.UT[this.UT.length - 2];
         int var2 = this.UT[this.UT.length - 1];
         return var2 - var1 + 1;
      }

      public int[] ys() {
         int[] var1 = new int[this.UT.length / 2];
         int var2 = 0;

         for (int var3 = this.UT.length - 2; var3 >= 0; var3 -= 2) {
            int var4 = this.UT[var3];
            int var5 = this.UT[var3 + 1];
            int var6 = var5 - var4 + 1;
            var1[var2++] = var6;
         }

         return var1;
      }

      public int ld() {
         int var1 = 1;

         for (int var5 : this.ys()) {
            var1 *= var5;
         }

         return var1;
      }

      public int gp() {
         int[] var1 = this.ys();
         int var2 = 1;

         for (int var3 = 1; var3 < var1.length; var3++) {
            var2 *= var1[var3];
         }

         return var2;
      }

      public S7.DataType oT() {
         return S7.DataType.fromId(this.E);
      }

      public int[] pC(int var1) {
         int[] var2 = new int[this.UT.length / 2];
         int var3 = var2.length;

         for (int var4 = this.UT.length - 2; var4 >= 0; var4 -= 2) {
            int var5 = this.UT[var4];
            int var6 = this.UT[var4 + 1];
            int var7 = var6 - var5 + 1;
            int var8 = var1 % var7;
            var1 /= var7;
            var2[var3 - 1] = var5 + var8;
            var3--;
         }

         return var2;
      }

      public GK.Av fI() {
         return this.ys;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         this.pC(var1, true, 0, 0, null);
         return var1.toString();
      }

      void pC(StringBuilder var1, boolean var2, int var3, int var4, ByteArray var5) {
         int var6 = var3 + this.A;
         S7.DataType var7 = S7.DataType.fromId(this.pC);
         switch (var7) {
            case MULTI_INST_FB:
            case MULTI_INST_SFB:
               throw new RuntimeException("TBI: rendering for multi-instance data blocks");
            case ARRAY:
               var1.append("ARRAY[");

               for (byte var15 = 0; var15 < this.UT.length; var15 += 2) {
                  if (var15 > 0) {
                     var1.append(", ");
                  }

                  Strings.ff(var1, "%d..%d", this.UT[var15], this.UT[var15 + 1]);
               }

               var1.append(']');
               S7.DataType var16 = this.oT();
               Strings.ff(var1, " of %s", var16);
               if (var16 == S7.DataType.STRING && this.wS != 254) {
                  Strings.ff(var1, "[%d]", this.wS);
               }

               if (var5 != null) {
                  var1.append(": ");
                  var5.position(var6 / 8);
                  if (var16 == S7.DataType.STRUCT) {
                     int var9 = 0;

                     for (int var10 = 0; var10 < this.gp(); var10++) {
                        for (int var11 = 0; var11 < this.E(); var11++) {
                           var1.append("\n");
                           this.ys.pC(var1, var2, var4 + 1, var6, var5);
                           var6 += this.ys.wS();
                           Strings.ff(var1, "  // end of struct item %s", Arrays.toString(this.pC(var9)));
                           var9++;
                        }
                     }
                  } else if (var16 == S7.DataType.BOOL) {
                     int var17 = this.E();
                     int var20 = 0;

                     for (int var23 = 0; var23 < this.gp(); var23++) {
                        for (int var12 = 0; var12 < var17; var12++) {
                           if (var20 > 0) {
                              var1.append(", ");
                           }

                           Object var13 = GK.pC(var16, var5, var12 & 7, true);
                           Strings.ff(var1, Formatter.toString(var13));
                           var20++;
                        }

                        if (var17 % 8 != 0) {
                           var5.position(var5.position() + 1);
                        }
                     }
                  } else {
                     int var18 = 0;

                     for (int var21 = 0; var21 < this.gp(); var21++) {
                        for (int var24 = 0; var24 < this.E(); var24++) {
                           if (var18 > 0) {
                              var1.append(", ");
                           }

                           Object var26 = GK.pC(var16, var5, 0, true);
                           Strings.ff(var1, Formatter.toString(var26));
                           var18++;
                        }
                     }
                  }
               } else if (var16 == S7.DataType.STRUCT) {
                  var1.append('\n');
                  this.ys.pC(var1, var2, var4 + 1, var6, var5);
               } else if (this.kS != null) {
                  var1.append(": ");
                  List var19 = (List)this.kS;
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
               this.ys.pC(var1, var2, var4 + 1, var6, var5);
               break;
            case STRING:
            default:
               Strings.ff(var1, "%s", var7);
               if (var7 == S7.DataType.STRING && this.wS != 254) {
                  Strings.ff(var1, "[%d]", this.wS);
               }

               Object var8;
               if (var5 != null) {
                  var5.position(var6 / 8);
                  var8 = GK.pC(var7, var5, var6 & 7, true);
               } else {
                  var8 = this.kS;
               }

               if (var8 != null) {
                  Strings.ff(var1, ": %s", Formatter.toString(var8));
               }
         }
      }
   }

   public static class Sv implements Iterator {
      private List pC;
      private int A;
      private Object kS;
      private int wS;

      Sv(List var1) {
         this.pC = var1;
         this.pC();
      }

      @Override
      public boolean hasNext() {
         return this.A > 0;
      }

      @Override
      public Object next() {
         if (this.A <= 0) {
            throw new NoSuchElementException();
         } else {
            this.A--;
            Object var1 = this.kS;
            if (this.A == 0) {
               this.wS++;
               this.pC();
            }

            return var1;
         }
      }

      private void pC() {
         if (this.wS < this.pC.size()) {
            Couple var1 = (Couple)this.pC.get(this.wS);
            this.A = (Integer)var1.getFirst();
            this.kS = var1.getSecond();
         }
      }
   }
}
