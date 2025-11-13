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
public class alw extends alu {
   private static Map Uv = new MapMaker().weakValues().makeMap();
   @SerId(1)
   protected float Dw;

   public static alw RF(float var0) {
      alw var1 = (alw)Uv.get(var0);
      if (var1 == null) {
         var1 = new alw(var0);
         Uv.put(var0, var1);
      }

      return var1;
   }

   protected alw(float var1) {
      super(32);
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
      alw var1 = new alw(this.Dw);
      return super.q(var1);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + Float.floatToIntBits(this.Dw);
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
         alw var3 = (alw)var1;
         return Float.floatToIntBits(this.Dw) == Float.floatToIntBits(var3.Dw);
      }
   }

   @Override
   public alu oW() {
      return this;
   }

   public alz za() {
      alz var1 = alz.RF(Float.floatToRawIntBits(this.Dw), 32);
      if (this.isMutable()) {
         var1 = (alz)var1.q(null);
      }

      return var1;
   }

   private alw lm(IEImm var1) {
      if (var1.getBitsize() != 32) {
         throw new RuntimeException("Mismatched bit sizes between " + this + " and " + var1);
      } else {
         if (!(var1 instanceof alw var2)) {
            if (!(var1 instanceof alz) && !(var1 instanceof alv)) {
               throw new RuntimeException("Cannot convert to float32: " + var1);
            }

            var2 = RF(Float.intBitsToFloat((int)var1.getValueAsLong()));
         }

         return var2;
      }
   }

   @Override
   public IEImm _fadd(IEImm var1) {
      alw var2 = this.lm(var1);
      return RF(this.Dw + var2.Dw);
   }

   @Override
   public IEImm _fsub(IEImm var1) {
      alw var2 = this.lm(var1);
      return RF(this.Dw - var2.Dw);
   }

   @Override
   public IEImm _fmul(IEImm var1) {
      alw var2 = this.lm(var1);
      return RF(this.Dw * var2.Dw);
   }

   @Override
   public IEImm _fdiv(IEImm var1) {
      alw var2 = this.lm(var1);
      return RF(this.Dw / var2.Dw);
   }

   @Override
   public Integer _fcmp(IEImm var1) {
      alw var2 = this.lm(var1);
      return !Float.isNaN(this.Dw) && !Float.isNaN(var2.Dw) ? Float.compare(this.Dw, var2.Dw) : null;
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
      return Float.floatToRawIntBits(this.Dw);
   }

   @Override
   public boolean canReadAsUnsignedLong() {
      return true;
   }

   @Override
   public long getValueAsUnsignedLong() {
      return Float.floatToRawIntBits(this.Dw) & 4294967295L;
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
      return true;
   }

   @Override
   public float getValueAsSingleFloat() {
      return this.Dw;
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
      return (ICElement)(this.xK != null ? this.xK : var2.getGlobalContext().getConstantFactory().createFloat32(this.Dw));
   }

   @Override
   public String toHexString() {
      return Float.toHexString(this.Dw);
   }

   @Override
   public void q(and var1) {
      String var2 = "f32:" + this.Dw;
      var1.append(var2, ItemClassIdentifiers.IMMEDIATE);
      if (this.RF) {
         var1.RF(this);
      }
   }
}
