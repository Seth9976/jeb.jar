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
public class ajt extends ajr {
   private static Map UT = new MapMaker().weakValues().makeMap();
   @SerId(1)
   protected float wS;

   public static ajt A(float var0) {
      ajt var1 = (ajt)UT.get(var0);
      if (var1 == null) {
         var1 = new ajt(var0);
         UT.put(var0, var1);
      }

      return var1;
   }

   protected ajt(float var1) {
      super(32);
      this.wS = var1;
   }

   @Override
   public IWildcardType.Group getGroup() {
      return IWildcardType.Group.FLOAT;
   }

   public ajr sY() {
      ajt var1 = new ajt(this.wS);
      return super.pC(var1);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + Float.floatToIntBits(this.wS);
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
         ajt var3 = (ajt)var1;
         return Float.floatToIntBits(this.wS) == Float.floatToIntBits(var3.wS);
      }
   }

   @Override
   public ajr wS() {
      return this;
   }

   public ajw ys() {
      ajw var1 = ajw.A(Float.floatToRawIntBits(this.wS), 32);
      if (this.isMutable()) {
         var1 = (ajw)var1.pC(null);
      }

      return var1;
   }

   private ajt oT(IEImm var1) {
      if (var1.getBitsize() != 32) {
         throw new RuntimeException("Mismatched bit sizes between " + this + " and " + var1);
      } else {
         if (!(var1 instanceof ajt var2)) {
            if (!(var1 instanceof ajw) && !(var1 instanceof ajs)) {
               throw new RuntimeException("Cannot convert to float32: " + var1);
            }

            var2 = A(Float.intBitsToFloat((int)var1.getValueAsLong()));
         }

         return var2;
      }
   }

   @Override
   public IEImm _fadd(IEImm var1) {
      ajt var2 = this.oT(var1);
      return A(this.wS + var2.wS);
   }

   @Override
   public IEImm _fsub(IEImm var1) {
      ajt var2 = this.oT(var1);
      return A(this.wS - var2.wS);
   }

   @Override
   public IEImm _fmul(IEImm var1) {
      ajt var2 = this.oT(var1);
      return A(this.wS * var2.wS);
   }

   @Override
   public IEImm _fdiv(IEImm var1) {
      ajt var2 = this.oT(var1);
      return A(this.wS / var2.wS);
   }

   @Override
   public Integer _fcmp(IEImm var1) {
      ajt var2 = this.oT(var1);
      return !Float.isNaN(this.wS) && !Float.isNaN(var2.wS) ? Float.compare(this.wS, var2.wS) : null;
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
      return Float.floatToRawIntBits(this.wS);
   }

   @Override
   public boolean canReadAsUnsignedLong() {
      return true;
   }

   @Override
   public long getValueAsUnsignedLong() {
      return Float.floatToRawIntBits(this.wS) & 4294967295L;
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
      return this.wS;
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
      return (ICElement)(this.kS != null ? this.kS : var2.getGlobalContext().getConstantFactory().createFloat32(this.wS));
   }

   @Override
   public String toHexString() {
      return Float.toHexString(this.wS);
   }

   @Override
   public void pC(akz var1) {
      String var2 = "f32:" + this.wS;
      var1.append(var2, ItemClassIdentifiers.IMMEDIATE);
      if (this.A) {
         var1.A(this);
      }
   }
}
