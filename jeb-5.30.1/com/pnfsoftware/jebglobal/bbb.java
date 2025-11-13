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

public class bbb implements bax {
   byte[] q;
   int RF;
   INativeDataItem xK;
   bat Dw;
   GenericCodeFormatter Uv;
   bay oW;
   Deque gO;
   boolean nf;
   boolean gP;

   public bbb(byte[] var1, int var2, INativeDataItem var3, bat var4, GenericCodeFormatter var5, bay var6, Deque var7) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
      this.oW = var6;
      this.gO = var7;
      this.nf = false;
      this.gP = true;
   }

   public void q(int var1) {
      this.RF += var1;
      this.nf = false;
   }

   @Override
   public boolean q() {
      return !this.nf;
   }

   @Override
   public boolean RF() {
      return true;
   }

   @Override
   public int xK() {
      return (int)this.xK.getMemorySize();
   }

   @Override
   public int Dw() {
      return this.RF;
   }

   @Override
   public int Uv() {
      INativeType var1 = this.xK.getType();
      Assert.a(var1 != null);
      int var2 = (int)this.xK.getMemorySize();
      long var3;
      long var5;
      switch (var2) {
         case 1:
            var3 = this.q[this.RF];
            var5 = var3 & 255L;
            break;
         case 2:
            var3 = EndianUtil.bytesToShort(this.q, this.RF, this.Uv.getEndianness().toByteOrder());
            var5 = var3 & 65535L;
            break;
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            this.Dw.append(var2 + " bytes: " + Formatter.byteArrayToHex(this.q, this.RF, this.RF + var2));
            this.nf = true;
            return var2;
         case 4:
            var3 = EndianUtil.bytesToInt(this.q, this.RF, this.Uv.getEndianness().toByteOrder());
            var5 = var3 & 4294967295L;
            break;
         case 8:
            var3 = EndianUtil.bytesToLong(this.q, this.RF, this.Uv.getEndianness().toByteOrder());
            var5 = var3;
      }

      if (this.gP) {
         this.Uv.formatDataDeclarator(var2, this.Dw);
         this.gP = false;
      }

      DataHints var7 = this.xK.getHints(false);
      if ((var5 == 0L || !TypeUtil.isPointer(var1)) && (var7 == null || var7.getAddressCalculationHint() != 1)) {
         if (var3 != 0L && var7 != null && var7.getAddressCalculationHint() == 2) {
            this.Dw.append("rel offset ");
            this.Uv.formatAddress(this.oW.getUnit().getVirtualImageBase() + var3, this.Dw);
         } else {
            String var8 = null;
            if (this.xK instanceof axv && ((axv)this.xK).cC()) {
               int var9 = ((axv)this.xK).oW();
               int var10 = ((axv)this.xK).sH();
               var3 = MathUtil.zeroExtend(var3 >>> var9, var10);
               var5 = MathUtil.zeroExtend(var5 >>> var9, var10);
            }

            NumberFormatter var16 = this.Uv.getNumberFormatter(this.xK);
            NumberFormatter.NumberBase var17 = var16.getBase();
            boolean var11 = var16.isSignedNumber();
            bbd var12 = (bbd)TypeUtil.getNonAlias(var1);
            if (var12 instanceof bbq var13) {
               bbr var14 = var13.xK().xK();
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

            this.Dw.appendAndRecord(var8, ItemClassIdentifiers.IMMEDIATE, this.xK.getItemId());
         }
      } else {
         this.Dw.append("offset ");
         this.Uv.formatAddress(var5, this.Dw);
      }

      String var15 = this.oW();
      if (var15 != null && !var15.isEmpty()) {
         this.Dw.q(var15);
      }

      this.nf = true;
      return (int)this.xK.getMemorySize();
   }

   String oW() {
      if (this.gO != null && !this.gO.isEmpty()) {
         StringBuilder var1 = new StringBuilder();

         for (bav var3 : this.gO) {
            String var4 = null;
            if (var3.q instanceof axr) {
               INativeType var5 = var3.q.getType();
               if (var5 != null && TypeUtil.getNonAlias(var5) instanceof bbv var6) {
                  if (var6.isUnion()) {
                     bbu var7 = var6.xK(0);
                     var4 = var7.getName();
                     var4 = var4 + "(";
                     var4 = var4
                        + Strings.join(
                           "/", (Iterable)var6.getFields().subList(1, var6.getFieldsCount()).stream().map(var0 -> var0.getName()).collect(Collectors.toList())
                        );
                     var4 = var4 + ")";
                  } else {
                     bbu var15 = var6.RF(var3.RF, var3.xK);
                     if (var15 != null) {
                        var4 = var15.getName(true);
                     }
                  }
               }
            } else if (var3.q instanceof axq) {
               INativeType var12 = var3.q.getType();
               if (var12 != null) {
                  var12 = TypeUtil.getNonAlias(var12);
                  if (var12 instanceof bbf) {
                     int var14 = var3.RF / ((bbf)var12).oW().getSize();
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
