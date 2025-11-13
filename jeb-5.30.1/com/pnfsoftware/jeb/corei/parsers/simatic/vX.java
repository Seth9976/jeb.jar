package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.io.EndianUtil;

public class vX {
   private byte[] q;

   public byte[] q(kY var1) {
      if (var1.q(S7.SectionType.IN) == null
         && var1.q(S7.SectionType.OUT) == null
         && var1.q(S7.SectionType.IN_OUT) == null
         && var1.q(S7.SectionType.STAT) == null) {
         return new byte[0];
      } else {
         BytePipe var2 = new BytePipe();
         kY.eo var3 = var1.q(S7.SectionType.IN);
         if (var3 != null) {
            var2.append(this.q(var3));
         }

         var3 = var1.q(S7.SectionType.OUT);
         if (var3 != null) {
            var2.append(this.q(var3));
         }

         var3 = var1.q(S7.SectionType.IN_OUT);
         if (var3 != null) {
            var2.append(this.q(var3));
         }

         var3 = var1.q(S7.SectionType.STAT);
         if (var3 != null) {
            var2.append(this.q(var3));
         }

         this.q = var2.getAll();
         return this.q;
      }
   }

   public byte[] q(kY.eo var1) {
      int var2 = var1.oW() / 8;
      this.q = new byte[var2];
      this.q(0, var1);
      return this.q;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private void q(int var1, kY.eo var2) {
      for (kY.nI var4 : var2.RF()) {
         S7.DataType var5 = var4.q();
         switch (var5) {
            case MULTI_INST_FB:
            case MULTI_INST_SFB:
               throw new RuntimeException("TBI: Bytes generation for multi-instance data blocks");
            case ARRAY:
               S7.DataType var6 = var4.HF();
               int var7 = var4.zz();
               int var8 = var4.gP();
               int var9;
               int var10;
               if (var6 == S7.DataType.STRUCT) {
                  var9 = var4.LK().oW();
                  var10 = var8 * var9;
               } else if (var6 == S7.DataType.STRING) {
                  int var16 = 16 + var4.Dw() * 8;
                  var9 = (var16 + 15) / 16 * 16;
                  var10 = var8 * var9;
               } else if (var6 == S7.DataType.BOOL) {
                  var9 = 1;
                  var10 = (var8 + 7) / 8 * 8;
               } else {
                  var9 = var6.getBitsize();
                  var10 = var8 * var9;
               }

               int var11 = var1 + var4.RF();
               kY.CU var12 = var4.oW();
               int var13 = 0;

               for (int var14 = 0; var13 < var7; var13++) {
                  if (var6 == S7.DataType.STRUCT) {
                     this.q(var11 + var14 * var9, var4.LK());
                  } else {
                     Object var15 = var12.hasNext() ? var12.next() : null;
                     this.q(var11 + var14 * var9, var6, var15, var4.Dw());
                  }

                  if (++var14 == var8) {
                     var11 += var10;
                     var14 = 0;
                  }
               }
               break;
            case STRUCT:
               this.q(var1 + var4.RF(), var4.LK());
               break;
            case STRING:
            default:
               this.q(var1 + var4.RF(), var4.q(), var4.xK(), var4.Dw());
         }
      }
   }

   private void q(int var1, S7.DataType var2, Object var3, int var4) {
      int var5 = var1 >>> 3;
      int var6 = var1 & 7;
      Assert.a(var6 == 0 || var2 == S7.DataType.BOOL);
      switch (var2) {
         case STRING:
            if (var3 != null) {
               S7.StringA var18 = (S7.StringA)var3;
               Assert.a(var4 == var18.getMaximumLength());
               this.q[var5] = (byte)var18.getMaximumLength();
               this.q[var5 + 1] = (byte)var18.getCurrentLength();
               ArrayUtil.copyBytes(this.q, var5 + 2, var18.getStringBytes(), 0, var18.getCurrentLength());
            } else {
               this.q[var5] = (byte)var4;
            }
            break;
         case BOOL:
            if (Boolean.TRUE.equals(var3)) {
               this.q[var5] = (byte)(this.q[var5] | 1 << var6);
            }
            break;
         case BYTE:
            if (var3 != null) {
               byte var17 = (Byte)var3;
               this.q[var5] = var17;
            }
            break;
         case CHAR:
            if (var3 != null) {
               char var16 = (Character)var3;
               this.q[var5] = (byte)var16;
            } else {
               this.q[var5] = 32;
            }
            break;
         case WORD:
         case INT:
            if (var3 != null) {
               short var15 = (Short)var3;
               EndianUtil.shortToBEBytes(var15, this.q, var5);
            }
            break;
         case DWORD:
         case DINT:
            if (var3 != null) {
               int var14 = (Integer)var3;
               EndianUtil.intToBEBytes(var14, this.q, var5);
            }
            break;
         case REAL:
            if (var3 != null) {
               float var13 = (Float)var3;
               int var8 = Float.floatToIntBits(var13);
               EndianUtil.intToBEBytes(var8, this.q, var5);
            }
            break;
         case S5TIME:
            if (var3 != null) {
               short var12 = ((S7.S5Time)var3).getRawValue();
               EndianUtil.shortToBEBytes(var12, this.q, var5);
            }
            break;
         case TIME:
            if (var3 != null) {
               int var11 = ((S7.Time)var3).getRawValue();
               EndianUtil.intToBEBytes(var11, this.q, var5);
            }
            break;
         case DATE:
            if (var3 != null) {
               short var10 = ((S7.Date)var3).getRawValue();
               EndianUtil.shortToBEBytes(var10, this.q, var5);
            }
            break;
         case TIME_OF_DAY:
            if (var3 != null) {
               int var9 = ((S7.TimeOfDay)var3).getRawValue();
               EndianUtil.intToBEBytes(var9, this.q, var5);
            }
            break;
         case DATE_AND_TIME:
            long var7;
            if (var3 != null) {
               var7 = ((S7.DateAndTime)var3).getRawValue();
            } else {
               var7 = S7.DateAndTime.DEFAULT.getRawValue();
            }

            EndianUtil.longToBEBytes(var7, this.q, var5);
      }
   }
}
