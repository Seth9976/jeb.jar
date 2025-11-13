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
public class aly extends alu {
   private static Map Uv = new MapMaker().weakValues().makeMap();
   @SerId(1)
   protected double Dw;

   public static aly xK(double var0) {
      aly var2 = (aly)Uv.get(var0);
      if (var2 == null) {
         var2 = new aly(var0);
         Uv.put(var0, var2);
      }

      return var2;
   }

   protected aly(double var1) {
      super(80);
      this.Dw = var1;
   }

   @Override
   public IWildcardType.Group getGroup() {
      return IWildcardType.Group.FLOAT;
   }

   @Override
   public Object Dw() {
      return this.Dw;
   }

   public alu gP() {
      aly var1 = new aly(this.Dw);
      return super.q(var1);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      long var2 = Double.doubleToLongBits(this.Dw);
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
         aly var3 = (aly)var1;
         return Double.doubleToLongBits(this.Dw) == Double.doubleToLongBits(var3.Dw);
      }
   }

   @Override
   public alu oW() {
      return this;
   }

   public alv za() {
      long var1 = Double.doubleToRawLongBits(this.Dw);
      long var3 = Long.MIN_VALUE | (var1 & 72057594037927935L) << 11;
      long var5 = (var1 >>> 52 & 2047L) - 1023L;
      long var7 = var5 + 16383L << 64;
      if (var1 < 0L) {
         var7 |= 32768L;
      }

      String var9 = Strings.ff("0x%4X%16X", var7, var3);
      alv var10 = alv.RF(var9, 80);
      if (this.isMutable()) {
         var10 = (alv)var10.q(null);
      }

      return var10;
   }

   private aly lm(IEImm var1) {
      if (var1.getBitsize() != 80) {
         throw new RuntimeException("Mismatched bit sizes between " + this + " and " + var1);
      } else {
         if (!(var1 instanceof aly var2)) {
            if (!(var1 instanceof alz) && !(var1 instanceof alv)) {
               throw new RuntimeException("Cannot convert to float80: " + var1);
            }

            byte[] var3 = var1.getValue().toByteArray();
            boolean var4 = var3[0] < 0;
            byte[] var5 = Arrays.copyOfRange(var3, 2, 10);
            if ((var5[0] & -128) == 0) {
               var2 = xK(Double.NaN);
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

               var2 = xK(Double.longBitsToDouble(var15));
            }
         }

         return var2;
      }
   }

   @Override
   public IEImm _fadd(IEImm var1) {
      aly var2 = this.lm(var1);
      return xK(this.Dw + var2.Dw);
   }

   @Override
   public IEImm _fsub(IEImm var1) {
      aly var2 = this.lm(var1);
      return xK(this.Dw - var2.Dw);
   }

   @Override
   public IEImm _fmul(IEImm var1) {
      aly var2 = this.lm(var1);
      return xK(this.Dw * var2.Dw);
   }

   @Override
   public IEImm _fdiv(IEImm var1) {
      aly var2 = this.lm(var1);
      return xK(this.Dw / var2.Dw);
   }

   @Override
   public Integer _fcmp(IEImm var1) {
      aly var2 = this.lm(var1);
      return !Double.isNaN(this.Dw) && !Double.isNaN(var2.Dw) ? Double.compare(this.Dw, var2.Dw) : null;
   }

   @Override
   public IEImm truncate(int var1) {
      return this.za().RF(var1);
   }

   @Override
   public IEImm expand(int var1) {
      return this.za().xK(var1);
   }

   @Override
   public int _signum() {
      return this.za()._signum();
   }

   public alu lm() {
      return this.za().zz();
   }

   public alu q(IEImm var1) {
      return this.za().q(var1);
   }

   public alu RF(IEImm var1) {
      return this.za().RF(var1);
   }

   public alu xK(IEImm var1) {
      return this.za().xK(var1);
   }

   public alu Dw(IEImm var1) {
      return this.za().Dw(var1);
   }

   public alu Uv(IEImm var1) {
      return this.za().Uv(var1);
   }

   public alu oW(IEImm var1) {
      return this.za().oW(var1);
   }

   public alu gO(IEImm var1) {
      return this.za().gO(var1);
   }

   public alu zz() {
      return this.za().JY();
   }

   public alu nf(IEImm var1) {
      return this.za().nf(var1);
   }

   public alu gP(IEImm var1) {
      return this.za().gP(var1);
   }

   public alu za(IEImm var1) {
      return this.za().za(var1);
   }

   @Override
   public boolean _testbit(int var1) {
      return this.za()._testbit(var1);
   }

   public alu RF(int var1) {
      return this.za().Dw(var1);
   }

   public alu xK(int var1) {
      return this.za().Uv(var1);
   }

   public alu Dw(int var1) {
      return this.za().oW(var1);
   }

   public alu Uv(int var1) {
      return this.za().gO(var1);
   }

   public alu oW(int var1) {
      return this.za().nf(var1);
   }

   public alu gO(int var1) {
      return this.za().gP(var1);
   }

   public alu nf(int var1) {
      return this.za().za(var1);
   }

   @Override
   public int _cmp(IEImm var1) {
      return this.za()._cmp(var1);
   }

   @Override
   public int _cmpU(IEImm var1) {
      return this.za()._cmpU(var1);
   }

   public alu gP(int var1) {
      return this.za().lm(var1);
   }

   @Override
   public boolean canReadAsLong() {
      return true;
   }

   @Override
   public long getValueAsLong() {
      return Double.doubleToRawLongBits(this.Dw);
   }

   @Override
   public boolean canReadAsUnsignedLong() {
      return true;
   }

   @Override
   public long getValueAsUnsignedLong() {
      return Double.doubleToRawLongBits(this.Dw);
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
      return this.Dw;
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      return (ICElement)(this.xK != null ? this.xK : var2.getGlobalContext().getConstantFactory().createFloat64(this.Dw));
   }

   @Override
   public String toHexString() {
      return Double.toHexString(this.Dw);
   }

   @Override
   public void q(and var1) {
      String var2 = "f80:" + this.Dw;
      var1.append(var2, ItemClassIdentifiers.IMMEDIATE);
      if (this.RF) {
         var1.RF(this);
      }
   }
}
