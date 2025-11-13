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
public abstract class alu extends ane implements IEImm {
   private static final StructuredLogger Dw = aeg.q(alu.class);
   private static int Uv = 0;
   @SerId(1)
   protected int q;
   @SerId(2)
   protected boolean RF;
   @SerId(3)
   protected ICElement xK;
   private static final BigInteger oW = BigInteger.valueOf(-1L);

   public static synchronized int q() {
      return Uv;
   }

   public static synchronized int q(int var0) {
      if (var0 != 0 && var0 != 1) {
         throw new IllegalArgumentException("Invalid policy: " + var0);
      } else {
         Uv = var0;
         return var0;
      }
   }

   public static synchronized alu q(long var0, int var2) {
      return (alu)(Uv != 1 && var2 <= 64 ? alz.RF(var0, var2) : alv.RF(BigInteger.valueOf(var0), var2));
   }

   public static synchronized alu q(byte[] var0, int var1) {
      if (Uv != 1 && var1 <= 64) {
         long var2 = new BigInteger(var0).longValue();
         return alz.RF(var2, var1);
      } else {
         return alv.RF(var0, var1);
      }
   }

   public static synchronized alu q(BigInteger var0, int var1) {
      return (alu)(Uv != 1 && var1 <= 64 ? alz.RF(var0.longValue(), var1) : alv.RF(var0, var1));
   }

   public static synchronized alu q(String var0, int var1) {
      return (alu)(Uv != 1 && var1 <= 64 ? alz.RF(Conversion.stringToLong(var0), var1) : alv.RF(var0, var1));
   }

   public static synchronized alu q(float var0) {
      return alw.RF(var0);
   }

   public static synchronized alu q(double var0) {
      return alx.xK(var0);
   }

   public static synchronized alu RF(double var0) {
      return aly.xK(var0);
   }

   protected alu(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Illegal bitsize: " + var1);
      } else {
         this.q = var1;
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + this.q;
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
         return !(var1 instanceof alu var3) ? false : this.q == var3.q;
      }
   }

   @Override
   public int getBitsize() {
      return this.q;
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

   public alu q(EState var1) {
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
      return this.RF;
   }

   @Override
   public boolean setType(IWildcardType var1, ETypeInfo var2) {
      return !this.RF ? false : super.setType(var1, var2);
   }

   @Override
   public boolean setCustomAST(ICElement var1) {
      if (!this.RF) {
         Assert.debugFail("Cannot assign custom AST element to a non-mutable EImm: " + this);
         return false;
      } else {
         this.xK = var1;
         return true;
      }
   }

   @Override
   public ICElement getCustomAST() {
      return this.xK;
   }

   public alu xK() {
      return !this.RF ? this : (alu)this.duplicateToMutable();
   }

   protected alu q(alu var1) {
      var1.RF = true;
      var1.xK = this.xK;
      return (alu)super.q((ane)var1);
   }

   public final alu q(IWildcardType var1) {
      alu var2 = (alu)this.duplicateToMutable();
      var2.za = var1;
      return var2;
   }

   public abstract Object Dw();

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
      return this.getValue().equals(oW);
   }

   @Override
   public boolean _isPowerOf2() {
      return !this.isZero() && this._and(this._sub(q(1L, this.getBitsize()))).isZero();
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
      return (IEGeneric)(var1 == 0 ? this.slice(0, var2) : q(this.getValue().shiftLeft(var1).toByteArray(), var2));
   }

   @Override
   public IEGeneric rightShift(int var1, int var2) {
      return (IEGeneric)(var1 == 0 ? this.slice(0, var2) : this._shr(var1).truncate(var2 - var1).zeroExtend(var2));
   }

   public boolean Uv() {
      return true;
   }

   public abstract alu oW();

   public boolean gO() {
      return true;
   }

   public abstract alu nf();

   @Override
   public IEImm normalize() {
      if (this.getType() != null) {
         if (this.getType().isFloat() && this.getGroup() != IWildcardType.Group.FLOAT) {
            if (this.Uv()) {
               return this.oW();
            }
         } else if (!this.getType().isFloat() && this.getGroup() == IWildcardType.Group.FLOAT && this.gO()) {
            return this.nf();
         }
      }

      return null;
   }
}
