package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;
import java.util.Collection;

@Ser
public abstract class ajr extends ala implements IEImm {
   private static final StructuredLogger wS = aco.pC(ajr.class);
   private static int UT = 0;
   @SerId(1)
   protected int pC;
   @SerId(2)
   protected boolean A;
   @SerId(3)
   protected ICElement kS;
   private static final BigInteger E = BigInteger.valueOf(-1L);

   public static synchronized ajr pC(long var0, int var2) {
      return (ajr)(UT != 1 && var2 <= 64 ? ajw.A(var0, var2) : ajs.A(BigInteger.valueOf(var0), var2));
   }

   public static synchronized ajr pC(byte[] var0, int var1) {
      if (UT != 1 && var1 <= 64) {
         long var2 = new BigInteger(var0).longValue();
         return ajw.A(var2, var1);
      } else {
         return ajs.A(var0, var1);
      }
   }

   public static synchronized ajr pC(BigInteger var0, int var1) {
      return (ajr)(UT != 1 && var1 <= 64 ? ajw.A(var0.longValue(), var1) : ajs.A(var0, var1));
   }

   public static synchronized ajr pC(String var0, int var1) {
      return (ajr)(UT != 1 && var1 <= 64 ? ajw.A(Conversion.stringToLong(var0), var1) : ajs.A(var0, var1));
   }

   public static synchronized ajr pC(float var0) {
      return ajt.A(var0);
   }

   public static synchronized ajr pC(double var0) {
      return aju.kS(var0);
   }

   public static synchronized ajr A(double var0) {
      return ajv.kS(var0);
   }

   protected ajr(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Illegal bitsize: " + var1);
      } else {
         this.pC = var1;
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + this.pC;
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else {
         return !(var1 instanceof ajr var3) ? false : this.pC == var3.pC;
      }
   }

   @Override
   public int getBitsize() {
      return this.pC;
   }

   @Override
   public int getPriority() {
      return 100;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
   }

   @Override
   public boolean accessesMemory() {
      return false;
   }

   public ajr pC(EState var1) {
      return this;
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) {
      return 0;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) {
      return false;
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
   }

   @Override
   public boolean isMutable() {
      return this.A;
   }

   @Override
   public boolean setType(IWildcardType var1, ETypeInfo var2) {
      return !this.A ? false : super.setType(var1, var2);
   }

   @Override
   public boolean setCustomAST(ICElement var1) {
      if (!this.A) {
         Assert.debugFail("Cannot assign custom AST element to a non-mutable EImm: " + this);
         return false;
      } else {
         this.kS = var1;
         return true;
      }
   }

   @Override
   public ICElement getCustomAST() {
      return this.kS;
   }

   public ajr pC() {
      return !this.A ? this : (ajr)this.duplicateToMutable();
   }

   protected ajr pC(ajr var1) {
      var1.A = true;
      var1.kS = this.kS;
      return (ajr)super.pC(var1);
   }

   public final ajr pC(IWildcardType var1) {
      ajr var2 = (ajr)this.duplicateToMutable();
      var2.gp = var1;
      return var2;
   }

   @Override
   public boolean canReadAsAddress() {
      return this.canReadAsLong();
   }

   @Override
   public long getValueAsAddress() {
      long var1 = this.getValueAsLong();
      return this.getBitsize() >= 64 ? var1 : MathUtil.zeroExtend(var1, this.getBitsize());
   }

   @Override
   public boolean isStringLiteral() {
      return false;
   }

   @Override
   public String getStringLiteral() {
      return null;
   }

   @Override
   public int _bitlength() {
      for (int var1 = this.getBitsize() - 1; var1 >= 0; var1--) {
         if (this._testbit(var1)) {
            return var1 + 1;
         }
      }

      return 0;
   }

   @Override
   public boolean isZero() {
      return this._signum() == 0;
   }

   @Override
   public boolean isOnes() {
      return this.getValue().equals(E);
   }

   @Override
   public boolean _isPowerOf2() {
      return !this.isZero() && this._and(this._sub(pC(1L, this.getBitsize()))).isZero();
   }

   @Override
   public Integer _log2() {
      if (this._isPowerOf2()) {
         Object var1 = this;

         for (int var2 = 0; var2 < this.getBitsize(); var2++) {
            boolean var3 = ((IEImm)var1)._testbit(0);
            var1 = ((IEImm)var1)._shr(1);
            if (var3) {
               if (((IEImm)var1).isZero()) {
                  return var2;
               }

               return null;
            }
         }
      }

      return null;
   }

   @Override
   public IEImm zeroExtend(int var1) {
      int var2 = this.getBitsize();
      if (var1 < var2) {
         throw new RuntimeException();
      } else {
         return (IEImm)(var1 == var2 ? this : this.expand(var1));
      }
   }

   @Override
   public IEImm signExtend(int var1) {
      int var2 = this.getBitsize();
      if (var1 < var2) {
         throw new RuntimeException();
      } else {
         return (IEImm)(var1 == var2 ? this : this.truncate(var1));
      }
   }

   @Override
   public IEGeneric leftShift(int var1, int var2) {
      return (IEGeneric)(var1 == 0 ? this.slice(0, var2) : pC(this.getValue().shiftLeft(var1).toByteArray(), var2));
   }

   @Override
   public IEGeneric rightShift(int var1, int var2) {
      return (IEGeneric)(var1 == 0 ? this.slice(0, var2) : this._shr(var1).truncate(var2 - var1).zeroExtend(var2));
   }

   public boolean kS() {
      return true;
   }

   public abstract ajr wS();

   public boolean UT() {
      return true;
   }

   public abstract ajr E();

   @Override
   public IEImm normalize() {
      if (this.getType() != null) {
         if (this.getType().isFloat() && this.getGroup() != IWildcardType.Group.FLOAT) {
            if (this.kS()) {
               return this.wS();
            }
         } else if (!this.getType().isFloat() && this.getGroup() == IWildcardType.Group.FLOAT && this.UT()) {
            return this.E();
         }
      }

      return null;
   }
}
