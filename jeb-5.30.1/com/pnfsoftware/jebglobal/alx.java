package com.pnfsoftware.jebglobal;

import com.google.common.collect.MapMaker;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;
import java.util.Map;

@Ser
public class alx extends alu {
   private static Map Uv = new MapMaker().weakValues().makeMap();
   @SerId(1)
   protected double Dw;

   public static alx xK(double var0) {
      alx var2 = (alx)Uv.get(var0);
      if (var2 == null) {
         var2 = new alx(var0);
         Uv.put(var0, var2);
      }

      return var2;
   }

   protected alx(double var1) {
      super(64);
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
      alx var1 = new alx(this.Dw);
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
         alx var3 = (alx)var1;
         return Double.doubleToLongBits(this.Dw) == Double.doubleToLongBits(var3.Dw);
      }
   }

   @Override
   public alu oW() {
      return this;
   }

   public alz za() {
      alz var1 = alz.RF(Double.doubleToRawLongBits(this.Dw), 64);
      if (this.isMutable()) {
         var1 = (alz)var1.q(null);
      }

      return var1;
   }

   private alx lm(IEImm var1) {
      if (var1.getBitsize() != 64) {
         throw new RuntimeException("Mismatched bit sizes between " + this + " and " + var1);
      } else {
         if (!(var1 instanceof alx var2)) {
            if (!(var1 instanceof alz) && !(var1 instanceof alv)) {
               throw new RuntimeException("Cannot convert to float64: " + var1);
            }

            var2 = xK(Double.longBitsToDouble(var1.getValueAsLong()));
         }

         return var2;
      }
   }

   @Override
   public IEImm _fadd(IEImm var1) {
      alx var2 = this.lm(var1);
      return xK(this.Dw + var2.Dw);
   }

   @Override
   public IEImm _fsub(IEImm var1) {
      alx var2 = this.lm(var1);
      return xK(this.Dw - var2.Dw);
   }

   @Override
   public IEImm _fmul(IEImm var1) {
      alx var2 = this.lm(var1);
      return xK(this.Dw * var2.Dw);
   }

   @Override
   public IEImm _fdiv(IEImm var1) {
      alx var2 = this.lm(var1);
      return xK(this.Dw / var2.Dw);
   }

   @Override
   public Integer _fcmp(IEImm var1) {
      alx var2 = this.lm(var1);
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
      return this.za().lm();
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
      return this.za().zz();
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
      String var2 = "f64:" + this.Dw;
      var1.append(var2, ItemClassIdentifiers.IMMEDIATE);
      if (this.RF) {
         var1.RF(this);
      }
   }
}
