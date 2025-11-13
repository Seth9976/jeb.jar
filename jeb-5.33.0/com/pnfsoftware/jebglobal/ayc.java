package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataHints;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.math.MathUtil;
import java.util.Deque;
import java.util.stream.Collectors;

public class ayc implements axy {
   byte[] pC;
   int A;
   INativeDataItem kS;
   axv wS;
   GenericCodeFormatter UT;
   axz E;
   Deque sY;
   boolean ys;
   boolean ld;

   public ayc(byte[] var1, int var2, INativeDataItem var3, axv var4, GenericCodeFormatter var5, axz var6, Deque var7) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
      this.E = var6;
      this.sY = var7;
      this.ys = false;
      this.ld = true;
   }

   public void pC(int var1) {
      this.A += var1;
      this.ys = false;
   }

   @Override
   public boolean pC() {
      return !this.ys;
   }

   @Override
   public boolean A() {
      return true;
   }

   @Override
   public int kS() {
      return (int)this.kS.getMemorySize();
   }

   @Override
   public int wS() {
      return this.A;
   }

   @Override
   public int UT() {
      INativeType var1 = this.kS.getType();
      Assert.a(var1 != null);
      int var2 = (int)this.kS.getMemorySize();
      long var3;
      long var5;
      switch (var2) {
         case 1:
            var3 = this.pC[this.A];
            var5 = var3 & 255L;
            break;
         case 2:
            var3 = EndianUtil.bytesToShort(this.pC, this.A, this.UT.getEndianness().toByteOrder());
            var5 = var3 & 65535L;
            break;
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            this.wS.append(var2 + " bytes: " + Formatter.byteArrayToHex(this.pC, this.A, this.A + var2));
            this.ys = true;
            return var2;
         case 4:
            var3 = EndianUtil.bytesToInt(this.pC, this.A, this.UT.getEndianness().toByteOrder());
            var5 = var3 & 4294967295L;
            break;
         case 8:
            var3 = EndianUtil.bytesToLong(this.pC, this.A, this.UT.getEndianness().toByteOrder());
            var5 = var3;
      }

      if (this.ld) {
         this.UT.formatDataDeclarator(var2, this.wS);
         this.ld = false;
      }

      DataHints var7 = this.kS.getHints(false);
      if ((var5 == 0L || !TypeUtil.isPointer(var1)) && (var7 == null || var7.getAddressCalculationHint() != 1)) {
         if (var3 != 0L && var7 != null && var7.getAddressCalculationHint() == 2) {
            this.wS.append("rel offset ");
            this.UT.formatAddress(this.E.getUnit().getVirtualImageBase() + var3, this.wS);
         } else {
            String var8 = null;
            if (this.kS instanceof ava && ((ava)this.kS).UO()) {
               int var9 = ((ava)this.kS).E();
               int var10 = ((ava)this.kS).Ab();
               var3 = MathUtil.zeroExtend(var3 >>> var9, var10);
               var5 = MathUtil.zeroExtend(var5 >>> var9, var10);
            }

            NumberFormatter var16 = this.UT.getNumberFormatter(this.kS);
            NumberFormatter.NumberBase var17 = var16.getBase();
            boolean var11 = var16.isSignedNumber();
            aye var12 = (aye)TypeUtil.getNonAlias(var1);
            if (var12 instanceof ayq var13) {
               ayr var14 = var13.kS().pC();
               if (var14.isCharacter(var13)) {
                  var8 = Strings.ff("'%s'", Formatter.escapeCharacter((char)var3));
               } else if (var14.isFloat(var13)) {
                  if (var2 == 4) {
                     var8 = Strings.ff("%f", Float.intBitsToFloat((int)var3));
                  } else if (var2 == 8) {
                     var8 = Strings.ff("%f", Double.longBitsToDouble(var3));
                  }
               } else if (var14.isSignedInteger(var13)) {
                  var11 = true;
               }
            }

            if (var8 == null) {
               var8 = var16.format(var2 * 8, var5, var17, var11);
            }

            this.wS.appendAndRecord(var8, ItemClassIdentifiers.IMMEDIATE, this.kS.getItemId());
         }
      } else {
         this.wS.append("offset ");
         this.UT.formatAddress(var5, this.wS);
      }

      String var15 = this.E();
      if (var15 != null && !var15.isEmpty()) {
         this.wS.pC(var15);
      }

      this.ys = true;
      return (int)this.kS.getMemorySize();
   }

   String E() {
      if (this.sY != null && !this.sY.isEmpty()) {
         StringBuilder var1 = new StringBuilder();

         for (axx var3 : this.sY) {
            String var4 = null;
            if (var3.pC instanceof auw) {
               INativeType var5 = var3.pC.getType();
               if (var5 != null && TypeUtil.getNonAlias(var5) instanceof ayv var6) {
                  if (var6.isUnion()) {
                     ayu var7 = var6.pC(0);
                     var4 = var7.getName();
                     var4 = var4 + "(";
                     var4 = var4
                        + Strings.join(
                           "/", (Iterable)var6.getFields().subList(1, var6.getFieldsCount()).stream().map(var0 -> var0.getName()).collect(Collectors.toList())
                        );
                     var4 = var4 + ")";
                  } else {
                     ayu var15 = var6.A(var3.A, var3.kS);
                     if (var15 != null) {
                        var4 = var15.getName(true);
                     }
                  }
               }
            } else if (var3.pC instanceof auv) {
               INativeType var12 = var3.pC.getType();
               if (var12 != null) {
                  var12 = TypeUtil.getNonAlias(var12);
                  if (var12 instanceof ayg) {
                     int var14 = var3.A / ((ayg)var12).E().getSize();
                     var4 = "[" + var14 + "]";
                  }
               }
            }

            if (var4 == null) {
               return null;
            }

            if (var1.length() > 0 && var1.charAt(0) != '[') {
               var1.insert(0, ".");
            }

            var1.insert(0, var4);
         }

         return var1.toString();
      } else {
         return null;
      }
   }
}
