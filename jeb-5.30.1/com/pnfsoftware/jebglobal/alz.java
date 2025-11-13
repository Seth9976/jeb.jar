package com.pnfsoftware.jebglobal;

import com.google.common.collect.MapMaker;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantString;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.primitives.LargeIntHandler;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;
import java.util.Map;

@Ser
public class alz extends alu {
   private static Map Uv = new MapMaker().weakValues().makeMap();
   @SerId(1)
   protected long Dw;

   public static alz RF(long var0, int var2) {
      if (var2 > 64) {
         throw new IllegalArgumentException("Illegal bitsize, greater than 64: " + var2);
      } else {
         long var3 = MathUtil.signExtend(var0, var2);
         Couple var5 = new Couple(var3, var2);
         alz var6 = (alz)Uv.get(var5);
         if (var6 == null) {
            var6 = new alz(var3, var2);
            Uv.put(var5, var6);
         }

         return var6;
      }
   }

   private alz(long var1, int var3) {
      super(var3);
      this.Dw = var1;
   }

   @Override
   public IWildcardType.Group getGroup() {
      return IWildcardType.Group.INTEGER;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (int)(this.Dw ^ this.Dw >>> 32);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (var1 instanceof alz var4) {
         return this.Dw == var4.Dw;
      } else {
         return var1 instanceof alv var3 ? this.Dw == var3.Dw.longValue() : false;
      }
   }

   public alu gP() {
      alz var1 = new alz(this.Dw, this.q);
      return super.q(var1);
   }

   public Long za() {
      return this.Dw;
   }

   @Override
   public boolean canReadAsLong() {
      return true;
   }

   @Override
   public long getValueAsLong() {
      return this.Dw;
   }

   @Override
   public boolean canReadAsUnsignedLong() {
      return this.q <= 63 || this._signum() >= 0;
   }

   @Override
   public long getValueAsUnsignedLong() {
      if (!this.canReadAsUnsignedLong()) {
         throw new EvaluationException("Cannot return value as a 64-bit unsigned long");
      } else {
         return MathUtil.zeroExtend(this.Dw, this.q);
      }
   }

   @Override
   public BigInteger getValue() {
      return BigInteger.valueOf(this.Dw);
   }

   @Override
   public BigInteger getUnsignedValue() {
      return new BigInteger(Long.toHexString(MathUtil.zeroExtend(this.Dw, this.q)), 16);
   }

   @Override
   public boolean isStringLiteral() {
      return this.xK instanceof ICConstantString;
   }

   @Override
   public String getStringLiteral() {
      return this.xK instanceof ICConstantString ? (String)((ICConstantString)this.xK).getValue() : null;
   }

   public alu RF(int var1) {
      return (alu)(var1 > 64 ? alv.q(this.Dw, var1) : RF(this.Dw, var1));
   }

   public alu xK(int var1) {
      if (var1 > 64) {
         String var2 = Longs.toUnsignedString(MathUtil.zeroExtend(this.Dw, this.q));
         return alv.RF(var2, var1);
      } else {
         return RF(MathUtil.zeroExtend(this.Dw, this.q), var1);
      }
   }

   @Override
   public void q(and var1) {
      if (this.xK != null && this.xK instanceof ICConstant) {
         ItemClassIdentifiers var4 = ItemClassIdentifiers.IMMEDIATE;
         if (this.xK instanceof ICConstantString) {
            var4 = ItemClassIdentifiers.STRING;
         }

         var1.append(this.xK.format(), var4);
      } else {
         String var2 = "";
         if (var1.q()) {
            var2 = var2 + "i" + this.q + ":";
         }
         var1.append(switch (this.q) {
            case 1 -> var2 + Long.toString(this.Dw & 1L);
            case 2 -> var2 + Long.toString(this.Dw & 3L);
            case 8 -> var2 + Strings.ff("%02Xh", this.Dw & 255L);
            case 16 -> var2 + Strings.ff("%04Xh", this.Dw & 65535L);
            case 32 -> var2 + Strings.ff("%08Xh", this.Dw & 4294967295L);
            case 64 -> var2 + Strings.ff("%016Xh", this.Dw);
            default -> var2 + Strings.ff("%Xh", MathUtil.zeroExtend(this.Dw, this.q));
         }, ItemClassIdentifiers.IMMEDIATE);
         if (this.RF) {
            var1.RF(this);
         }
      }
   }

   @Override
   public int _signum() {
      return this.Dw > 0L ? 1 : (this.Dw < 0L ? -1 : 0);
   }

   public alu lm() {
      return this.Dw == 0L ? this : RF(-this.Dw, this.q);
   }

   private alz lm(IEImm var1) {
      if (var1.getBitsize() != this.q) {
         throw new RuntimeException("Mismatched bit sizes between " + this + " and " + var1);
      } else {
         if (!(var1 instanceof alz var2)) {
            if (var1 instanceof alv) {
               var2 = RF(var1.getValueAsLong(), var1.getBitsize());
               if (this.isMutable()) {
                  var2 = (alz)var2.q(var1.getType());
               }
            } else if (var1 instanceof alw) {
               var2 = ((alw)var1).za();
            } else {
               if (!(var1 instanceof alx)) {
                  throw new RuntimeException();
               }

               var2 = ((alx)var1).za();
            }
         }

         return var2;
      }
   }

   public alu q(IEImm var1) {
      alz var2 = this.lm(var1);
      return RF(this.Dw + var2.Dw, this.q);
   }

   public alu RF(IEImm var1) {
      alz var2 = this.lm(var1);
      return RF(this.Dw - var2.Dw, this.q);
   }

   public alu xK(IEImm var1) {
      alz var2 = this.lm(var1);
      return RF(this.Dw * var2.Dw, this.q);
   }

   public alu Dw(IEImm var1) {
      alz var2 = this.lm(var1);
      return RF(this.Dw / var2.Dw, this.q);
   }

   public alu Uv(IEImm var1) {
      alz var2 = this.lm(var1);
      long var3;
      if (this.q != 64 || this.Dw >= 0L && var2.Dw >= 0L) {
         long var9 = MathUtil.zeroExtend(this.Dw, this.q);
         long var7 = MathUtil.zeroExtend(var2.Dw, this.q);
         var3 = var9 / var7;
      } else {
         LargeIntHandler var5 = LargeIntHandler.create(this.q);
         var3 = var5.divU(BigInteger.valueOf(this.Dw), BigInteger.valueOf(var2.Dw)).longValue();
      }

      return RF(var3, this.q);
   }

   public alu oW(IEImm var1) {
      alz var2 = this.lm(var1);
      return RF(this.Dw % var2.Dw, this.q);
   }

   public alu gO(IEImm var1) {
      alz var2 = this.lm(var1);
      long var3 = this.q == 64 ? this.Dw : MathUtil.zeroExtend(this.Dw, this.q);
      long var5 = var2.q == 64 ? var2.Dw : MathUtil.zeroExtend(var2.Dw, this.q);
      return RF(var3 % var5, this.q);
   }

   public alu zz() {
      return RF(~this.Dw, this.q);
   }

   public alu nf(IEImm var1) {
      alz var2 = this.lm(var1);
      return RF(this.Dw & var2.Dw, this.q);
   }

   public alu gP(IEImm var1) {
      alz var2 = this.lm(var1);
      return RF(this.Dw | var2.Dw, this.q);
   }

   public alu za(IEImm var1) {
      alz var2 = this.lm(var1);
      return RF(this.Dw ^ var2.Dw, this.q);
   }

   @Override
   public boolean _testbit(int var1) {
      if (var1 >= 0 && var1 < this.q) {
         return (this.Dw & 1L << var1) != 0L;
      } else {
         throw new ArithmeticException("Invalid bit position");
      }
   }

   public alu Dw(int var1) {
      if (var1 >= 0 && var1 < this.q) {
         return RF(this.Dw | 1L << var1, this.q);
      } else {
         throw new ArithmeticException("Invalid bit position");
      }
   }

   public alu Uv(int var1) {
      if (var1 >= 0 && var1 < this.q) {
         return RF(this.Dw & ~(1L << var1), this.q);
      } else {
         throw new ArithmeticException("Invalid bit position");
      }
   }

   private int zz(int var1) {
      if (this.q == 64) {
         var1 &= 63;
      } else if (this.q == 32) {
         var1 &= 31;
      } else if (this.q == 8) {
         var1 &= 7;
      } else {
         var1 %= this.q;
         if (var1 < 0) {
            var1 += this.q;
         }
      }

      return var1;
   }

   public alu oW(int var1) {
      var1 = this.zz(var1);
      return RF(this.Dw << var1, this.q);
   }

   public alu gO(int var1) {
      var1 = this.zz(var1);
      long var2 = MathUtil.makeMask(this.q);
      return RF((this.Dw & var2) >>> var1, this.q);
   }

   public alu nf(int var1) {
      var1 = this.zz(var1);
      return RF(this.Dw >> var1, this.q);
   }

   public alu gP(int var1) {
      if (var1 < 0) {
         return this.za(-var1);
      } else {
         var1 = this.zz(var1);
         long var2 = (1L << var1) - 1L;
         long var4 = this.Dw << var1 | this.Dw >>> this.q - var1 & var2;
         return RF(var4, this.q);
      }
   }

   public alu za(int var1) {
      if (var1 < 0) {
         return this.gP(-var1);
      } else {
         var1 = this.zz(var1);
         long var2 = (1L << var1) - 1L;
         long var4 = MathUtil.makeMask(this.q);
         long var6 = (this.Dw & var4) >>> var1 | (this.Dw & var2) << this.q - var1;
         return RF(var6, this.q);
      }
   }

   @Override
   public int _cmp(IEImm var1) {
      alz var2 = this.lm(var1);
      return Long.compare(this.Dw, var2.Dw);
   }

   @Override
   public int _cmpU(IEImm var1) {
      alz var2 = this.lm(var1);
      return Longs.compareUnsigned(this.Dw, var2.Dw);
   }

   public alu lm(int var1) {
      if (var1 < 0) {
         throw new ArithmeticException("Invalid exponent");
      } else {
         long var2;
         for (var2 = 1L; var1 > 0; var1--) {
            var2 *= this.Dw;
         }

         return alu.q(var2, this.q);
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      if (this.xK != null) {
         return this.xK;
      } else {
         ICConstantFactory var3 = var2.getConstantFactory();
         ICType var4 = null;
         if (this.getType() != null && this.getType().isPointer()) {
            if (this._signum() == 0) {
               return var3.getNull();
            }

            var4 = var2.getTypeFactory().create(this.getType());
         }

         long var5 = MathUtil.zeroExtend(this.Dw, this.q);
         int var7 = this.q;
         boolean var8 = false;
         if (this.getType() != null) {
            var7 = this.getType().getBitsize();
            if (var7 > 64) {
               var7 = 64;
            }

            if (this.getType().isSigned()) {
               var8 = true;
            }
         }

         if (var8) {
            var5 = MathUtil.signExtend(var5, var7);
         } else {
            var5 = MathUtil.zeroExtend(var5, var7);
         }

         Object var11;
         if (var4 != null) {
            var11 = var3.createPointer(var5);
            var11 = var2.getElementFactory().createCast(var4, var11);
         } else if (var7 <= 32) {
            if (this.getType() != null && this.getType().toString().equals("char")) {
               var11 = var3.createChar((char)var5);
            } else if (this.getType() != null && this.getType().isUnsigned()) {
               var11 = var3.createUnsignedInt32((int)var5);
            } else {
               var11 = var3.createInt32((int)var5);
            }
         } else if (this.getType() != null && this.getType().isUnsigned()) {
            var11 = var3.createUnsignedInt64(var5);
         } else {
            var11 = var3.createInt64(var5);
         }

         return var11;
      }
   }

   @Override
   public String toHexString() {
      return Long.toHexString(MathUtil.zeroExtend(this.Dw, this.q));
   }

   @Override
   public boolean Uv() {
      return this.q == 32 || this.q == 64;
   }

   @Override
   public alu oW() {
      int var2 = this.q;
      if (this.getType() != null && this.getType().isFloat()) {
         var2 = this.getType().getBitsize();
      }

      Object var1;
      if (var2 == 32) {
         var1 = alw.RF(Float.intBitsToFloat((int)this.Dw));
      } else {
         if (var2 != 64) {
            throw new RuntimeException("Cannot generate a float immediate");
         }

         var1 = alx.xK(Double.longBitsToDouble(this.Dw));
      }

      if (this.isMutable()) {
         var1 = ((alu)var1).q(null);
      }

      return (alu)var1;
   }

   @Override
   public alu nf() {
      return this;
   }

   @Override
   public IEImm _fadd(IEImm var1) {
      return this.oW()._fadd(var1);
   }

   @Override
   public IEImm _fsub(IEImm var1) {
      return this.oW()._fsub(var1);
   }

   @Override
   public IEImm _fmul(IEImm var1) {
      return this.oW()._fmul(var1);
   }

   @Override
   public IEImm _fdiv(IEImm var1) {
      return this.oW()._fdiv(var1);
   }

   @Override
   public Integer _fcmp(IEImm var1) {
      return this.oW()._fcmp(var1);
   }

   @Override
   public boolean canReadAsSingleFloat() {
      return this.q == 32;
   }

   @Override
   public float getValueAsSingleFloat() {
      if (!this.canReadAsSingleFloat()) {
         throw new IllegalStateException("Cannot return value as a 32-bit single-precision float");
      } else {
         return Float.intBitsToFloat((int)this.Dw);
      }
   }

   @Override
   public boolean canReadAsDoubleFloat() {
      return this.q == 64;
   }

   @Override
   public double getValueAsDoubleFloat() {
      if (!this.canReadAsDoubleFloat()) {
         throw new IllegalStateException("Cannot return value as a 64-bit double-precision float");
      } else {
         return Double.longBitsToDouble(this.Dw);
      }
   }
}
