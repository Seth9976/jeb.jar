package com.pnfsoftware.jebglobal;

import com.google.common.collect.MapMaker;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;

@Ser
public class ajv extends ajr {
   private static Map UT = new MapMaker().weakValues().makeMap();
   @SerId(1)
   protected double wS;

   public static ajv kS(double var0) {
      ajv var2 = (ajv)UT.get(var0);
      if (var2 == null) {
         var2 = new ajv(var0);
         UT.put(var0, var2);
      }

      return var2;
   }

   protected ajv(double var1) {
      super(80);
      this.wS = var1;
   }

   @Override
   public IWildcardType.Group getGroup() {
      return IWildcardType.Group.FLOAT;
   }

   public ajr sY() {
      ajv var1 = new ajv(this.wS);
      return super.pC(var1);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      long var2 = Double.doubleToLongBits(this.wS);
      return 31 * var1 + (int)(var2 ^ var2 >>> 32);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         ajv var3 = (ajv)var1;
         return Double.doubleToLongBits(this.wS) == Double.doubleToLongBits(var3.wS);
      }
   }

   @Override
   public ajr wS() {
      return this;
   }

   public ajs ys() {
      long var1 = Double.doubleToRawLongBits(this.wS);
      long var3 = Long.MIN_VALUE | (var1 & 72057594037927935L) << 11;
      long var5 = (var1 >>> 52 & 2047L) - 1023L;
      long var7 = var5 + 16383L << 64;
      if (var1 < 0L) {
         var7 |= 32768L;
      }

      String var9 = Strings.ff("0x%4X%16X", var7, var3);
      ajs var10 = ajs.A(var9, 80);
      if (this.isMutable()) {
         var10 = (ajs)var10.pC(null);
      }

      return var10;
   }

   private ajv oT(IEImm var1) {
      if (var1.getBitsize() != 80) {
         throw new RuntimeException("Mismatched bit sizes between " + this + " and " + var1);
      } else {
         if (!(var1 instanceof ajv var2)) {
            if (!(var1 instanceof ajw) && !(var1 instanceof ajs)) {
               throw new RuntimeException("Cannot convert to float80: " + var1);
            }

            byte[] var3 = var1.getValue().toByteArray();
            boolean var4 = var3[0] < 0;
            byte[] var5 = Arrays.copyOfRange(var3, 2, 10);
            if ((var5[0] & -128) == 0) {
               var2 = kS(Double.NaN);
            } else {
               var5[0] = (byte)(var5[0] & 127);
               long var6 = new BigInteger(var5).longValue();
               long var8 = var6 >>> 11;
               byte[] var10 = Arrays.copyOfRange(var3, 0, 2);
               var10[0] = (byte)(var10[0] & 127);
               long var11 = (var10[0] << 8) + var10[1] - 16383;
               long var13 = var11 + 1023L & 2047L;
               long var15 = var13 << 52 | var8;
               if (var4) {
                  var15 |= Long.MIN_VALUE;
               }

               var2 = kS(Double.longBitsToDouble(var15));
            }
         }

         return var2;
      }
   }

   @Override
   public IEImm _fadd(IEImm var1) {
      ajv var2 = this.oT(var1);
      return kS(this.wS + var2.wS);
   }

   @Override
   public IEImm _fsub(IEImm var1) {
      ajv var2 = this.oT(var1);
      return kS(this.wS - var2.wS);
   }

   @Override
   public IEImm _fmul(IEImm var1) {
      ajv var2 = this.oT(var1);
      return kS(this.wS * var2.wS);
   }

   @Override
   public IEImm _fdiv(IEImm var1) {
      ajv var2 = this.oT(var1);
      return kS(this.wS / var2.wS);
   }

   @Override
   public Integer _fcmp(IEImm var1) {
      ajv var2 = this.oT(var1);
      return !Double.isNaN(this.wS) && !Double.isNaN(var2.wS) ? Double.compare(this.wS, var2.wS) : null;
   }

   @Override
   public IEImm truncate(int var1) {
      return this.ys().pC(var1);
   }

   @Override
   public IEImm expand(int var1) {
      return this.ys().A(var1);
   }

   @Override
   public int _signum() {
      return this.ys()._signum();
   }

   public ajr ld() {
      return this.ys().ys();
   }

   public ajr pC(IEImm var1) {
      return this.ys().pC(var1);
   }

   public ajr A(IEImm var1) {
      return this.ys().A(var1);
   }

   public ajr kS(IEImm var1) {
      return this.ys().kS(var1);
   }

   public ajr wS(IEImm var1) {
      return this.ys().wS(var1);
   }

   public ajr UT(IEImm var1) {
      return this.ys().UT(var1);
   }

   public ajr E(IEImm var1) {
      return this.ys().E(var1);
   }

   public ajr sY(IEImm var1) {
      return this.ys().sY(var1);
   }

   public ajr gp() {
      return this.ys().ld();
   }

   public ajr ys(IEImm var1) {
      return this.ys().ys(var1);
   }

   public ajr ld(IEImm var1) {
      return this.ys().ld(var1);
   }

   public ajr gp(IEImm var1) {
      return this.ys().gp(var1);
   }

   @Override
   public boolean _testbit(int var1) {
      return this.ys()._testbit(var1);
   }

   public ajr pC(int var1) {
      return this.ys().kS(var1);
   }

   public ajr A(int var1) {
      return this.ys().wS(var1);
   }

   public ajr kS(int var1) {
      return this.ys().UT(var1);
   }

   public ajr wS(int var1) {
      return this.ys().E(var1);
   }

   public ajr UT(int var1) {
      return this.ys().sY(var1);
   }

   public ajr E(int var1) {
      return this.ys().ys(var1);
   }

   public ajr sY(int var1) {
      return this.ys().ld(var1);
   }

   @Override
   public int _cmp(IEImm var1) {
      return this.ys()._cmp(var1);
   }

   @Override
   public int _cmpU(IEImm var1) {
      return this.ys()._cmpU(var1);
   }

   public ajr ys(int var1) {
      return this.ys().gp(var1);
   }

   @Override
   public boolean canReadAsLong() {
      return true;
   }

   @Override
   public long getValueAsLong() {
      return Double.doubleToRawLongBits(this.wS);
   }

   @Override
   public boolean canReadAsUnsignedLong() {
      return true;
   }

   @Override
   public long getValueAsUnsignedLong() {
      return Double.doubleToRawLongBits(this.wS);
   }

   @Override
   public BigInteger getValue() {
      return BigInteger.valueOf(this.getValueAsLong());
   }

   @Override
   public BigInteger getUnsignedValue() {
      return BigInteger.valueOf(this.getValueAsUnsignedLong());
   }

   @Override
   public boolean canReadAsSingleFloat() {
      return false;
   }

   @Override
   public float getValueAsSingleFloat() {
      throw new RuntimeException();
   }

   @Override
   public boolean canReadAsDoubleFloat() {
      return true;
   }

   @Override
   public double getValueAsDoubleFloat() {
      return this.wS;
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      return (ICElement)(this.kS != null ? this.kS : var2.getGlobalContext().getConstantFactory().createFloat64(this.wS));
   }

   @Override
   public String toHexString() {
      return Double.toHexString(this.wS);
   }

   @Override
   public void pC(akz var1) {
      String var2 = "f80:" + this.wS;
      var1.append(var2, ItemClassIdentifiers.IMMEDIATE);
      if (this.A) {
         var1.A(this);
      }
   }
}
