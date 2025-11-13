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
public class ajw extends ajr {
   private static Map UT = new MapMaker().weakValues().makeMap();
   @SerId(1)
   protected long wS;

   public static ajw A(long var0, int var2) {
      if (var2 > 64) {
         throw new IllegalArgumentException("Illegal bitsize, greater than 64: " + var2);
      } else {
         long var3 = MathUtil.signExtend(var0, var2);
         Couple var5 = new Couple(var3, var2);
         ajw var6 = (ajw)UT.get(var5);
         if (var6 == null) {
            var6 = new ajw(var3, var2);
            UT.put(var5, var6);
         }

         return var6;
      }
   }

   private ajw(long var1, int var3) {
      super(var3);
      this.wS = var1;
   }

   @Override
   public IWildcardType.Group getGroup() {
      return IWildcardType.Group.INTEGER;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (int)(this.wS ^ this.wS >>> 32);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (var1 instanceof ajw var4) {
         return this.wS == var4.wS;
      } else {
         return var1 instanceof ajs var3 ? this.wS == var3.wS.longValue() : false;
      }
   }

   public ajr sY() {
      ajw var1 = new ajw(this.wS, this.pC);
      return super.pC(var1);
   }

   @Override
   public boolean canReadAsLong() {
      return true;
   }

   @Override
   public long getValueAsLong() {
      return this.wS;
   }

   @Override
   public boolean canReadAsUnsignedLong() {
      return this.pC <= 63 || this._signum() >= 0;
   }

   @Override
   public long getValueAsUnsignedLong() {
      if (!this.canReadAsUnsignedLong()) {
         throw new EvaluationException("Cannot return value as a 64-bit unsigned long");
      } else {
         return MathUtil.zeroExtend(this.wS, this.pC);
      }
   }

   @Override
   public BigInteger getValue() {
      return BigInteger.valueOf(this.wS);
   }

   @Override
   public BigInteger getUnsignedValue() {
      return new BigInteger(Long.toHexString(MathUtil.zeroExtend(this.wS, this.pC)), 16);
   }

   @Override
   public boolean isStringLiteral() {
      return this.kS instanceof ICConstantString;
   }

   @Override
   public String getStringLiteral() {
      return this.kS instanceof ICConstantString ? (String)((ICConstantString)this.kS).getValue() : null;
   }

   public ajr pC(int var1) {
      return (ajr)(var1 > 64 ? ajs.pC(this.wS, var1) : A(this.wS, var1));
   }

   public ajr A(int var1) {
      if (var1 > 64) {
         String var2 = Longs.toUnsignedString(MathUtil.zeroExtend(this.wS, this.pC));
         return ajs.A(var2, var1);
      } else {
         return A(MathUtil.zeroExtend(this.wS, this.pC), var1);
      }
   }

   @Override
   public void pC(akz var1) {
      if (this.kS != null && this.kS instanceof ICConstant) {
         ItemClassIdentifiers var4 = ItemClassIdentifiers.IMMEDIATE;
         if (this.kS instanceof ICConstantString) {
            var4 = ItemClassIdentifiers.STRING;
         }

         var1.append(this.kS.format(), var4);
      } else {
         String var2 = "";
         if (var1.pC()) {
            var2 = var2 + "i" + this.pC + ":";
         }
         var1.append(switch (this.pC) {
            case 1 -> var2 + Long.toString(this.wS & 1L);
            case 2 -> var2 + Long.toString(this.wS & 3L);
            case 8 -> var2 + Strings.ff("%02Xh", this.wS & 255L);
            case 16 -> var2 + Strings.ff("%04Xh", this.wS & 65535L);
            case 32 -> var2 + Strings.ff("%08Xh", this.wS & 4294967295L);
            case 64 -> var2 + Strings.ff("%016Xh", this.wS);
            default -> var2 + Strings.ff("%Xh", MathUtil.zeroExtend(this.wS, this.pC));
         }, ItemClassIdentifiers.IMMEDIATE);
         if (this.A) {
            var1.A(this);
         }
      }
   }

   @Override
   public int _signum() {
      return this.wS > 0L ? 1 : (this.wS < 0L ? -1 : 0);
   }

   public ajr ys() {
      return this.wS == 0L ? this : A(-this.wS, this.pC);
   }

   private ajw oT(IEImm var1) {
      if (var1.getBitsize() != this.pC) {
         throw new RuntimeException("Mismatched bit sizes between " + this + " and " + var1);
      } else {
         if (!(var1 instanceof ajw var2)) {
            if (var1 instanceof ajs) {
               var2 = A(var1.getValueAsLong(), var1.getBitsize());
               if (this.isMutable()) {
                  var2 = (ajw)var2.pC(var1.getType());
               }
            } else if (var1 instanceof ajt) {
               var2 = ((ajt)var1).ys();
            } else {
               if (!(var1 instanceof aju)) {
                  throw new RuntimeException();
               }

               var2 = ((aju)var1).ys();
            }
         }

         return var2;
      }
   }

   public ajr pC(IEImm var1) {
      ajw var2 = this.oT(var1);
      return A(this.wS + var2.wS, this.pC);
   }

   public ajr A(IEImm var1) {
      ajw var2 = this.oT(var1);
      return A(this.wS - var2.wS, this.pC);
   }

   public ajr kS(IEImm var1) {
      ajw var2 = this.oT(var1);
      return A(this.wS * var2.wS, this.pC);
   }

   public ajr wS(IEImm var1) {
      ajw var2 = this.oT(var1);
      return A(this.wS / var2.wS, this.pC);
   }

   public ajr UT(IEImm var1) {
      ajw var2 = this.oT(var1);
      long var3;
      if (this.pC != 64 || this.wS >= 0L && var2.wS >= 0L) {
         long var9 = MathUtil.zeroExtend(this.wS, this.pC);
         long var7 = MathUtil.zeroExtend(var2.wS, this.pC);
         var3 = var9 / var7;
      } else {
         LargeIntHandler var5 = LargeIntHandler.create(this.pC);
         var3 = var5.divU(BigInteger.valueOf(this.wS), BigInteger.valueOf(var2.wS)).longValue();
      }

      return A(var3, this.pC);
   }

   public ajr E(IEImm var1) {
      ajw var2 = this.oT(var1);
      return A(this.wS % var2.wS, this.pC);
   }

   public ajr sY(IEImm var1) {
      ajw var2 = this.oT(var1);
      long var3 = this.pC == 64 ? this.wS : MathUtil.zeroExtend(this.wS, this.pC);
      long var5 = var2.pC == 64 ? var2.wS : MathUtil.zeroExtend(var2.wS, this.pC);
      return A(var3 % var5, this.pC);
   }

   public ajr ld() {
      return A(~this.wS, this.pC);
   }

   public ajr ys(IEImm var1) {
      ajw var2 = this.oT(var1);
      return A(this.wS & var2.wS, this.pC);
   }

   public ajr ld(IEImm var1) {
      ajw var2 = this.oT(var1);
      return A(this.wS | var2.wS, this.pC);
   }

   public ajr gp(IEImm var1) {
      ajw var2 = this.oT(var1);
      return A(this.wS ^ var2.wS, this.pC);
   }

   @Override
   public boolean _testbit(int var1) {
      if (var1 >= 0 && var1 < this.pC) {
         return (this.wS & 1L << var1) != 0L;
      } else {
         throw new ArithmeticException("Invalid bit position");
      }
   }

   public ajr kS(int var1) {
      if (var1 >= 0 && var1 < this.pC) {
         return A(this.wS | 1L << var1, this.pC);
      } else {
         throw new ArithmeticException("Invalid bit position");
      }
   }

   public ajr wS(int var1) {
      if (var1 >= 0 && var1 < this.pC) {
         return A(this.wS & ~(1L << var1), this.pC);
      } else {
         throw new ArithmeticException("Invalid bit position");
      }
   }

   private int oT(int var1) {
      if (this.pC == 64) {
         var1 &= 63;
      } else if (this.pC == 32) {
         var1 &= 31;
      } else if (this.pC == 8) {
         var1 &= 7;
      } else {
         var1 %= this.pC;
         if (var1 < 0) {
            var1 += this.pC;
         }
      }

      return var1;
   }

   public ajr UT(int var1) {
      var1 = this.oT(var1);
      return A(this.wS << var1, this.pC);
   }

   public ajr E(int var1) {
      var1 = this.oT(var1);
      long var2 = MathUtil.makeMask(this.pC);
      return A((this.wS & var2) >>> var1, this.pC);
   }

   public ajr sY(int var1) {
      var1 = this.oT(var1);
      return A(this.wS >> var1, this.pC);
   }

   public ajr ys(int var1) {
      if (var1 < 0) {
         return this.ld(-var1);
      } else {
         var1 = this.oT(var1);
         long var2 = (1L << var1) - 1L;
         long var4 = this.wS << var1 | this.wS >>> this.pC - var1 & var2;
         return A(var4, this.pC);
      }
   }

   public ajr ld(int var1) {
      if (var1 < 0) {
         return this.ys(-var1);
      } else {
         var1 = this.oT(var1);
         long var2 = (1L << var1) - 1L;
         long var4 = MathUtil.makeMask(this.pC);
         long var6 = (this.wS & var4) >>> var1 | (this.wS & var2) << this.pC - var1;
         return A(var6, this.pC);
      }
   }

   @Override
   public int _cmp(IEImm var1) {
      ajw var2 = this.oT(var1);
      return Long.compare(this.wS, var2.wS);
   }

   @Override
   public int _cmpU(IEImm var1) {
      ajw var2 = this.oT(var1);
      return Longs.compareUnsigned(this.wS, var2.wS);
   }

   public ajr gp(int var1) {
      if (var1 < 0) {
         throw new ArithmeticException("Invalid exponent");
      } else {
         long var2;
         for (var2 = 1L; var1 > 0; var1--) {
            var2 *= this.wS;
         }

         return ajr.pC(var2, this.pC);
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      if (this.kS != null) {
         return this.kS;
      } else {
         ICConstantFactory var3 = var2.getConstantFactory();
         ICType var4 = null;
         if (this.getType() != null && this.getType().isPointer()) {
            if (this._signum() == 0) {
               return var3.getNull();
            }

            var4 = var2.getTypeFactory().create(this.getType());
         }

         long var5 = MathUtil.zeroExtend(this.wS, this.pC);
         int var7 = this.pC;
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
      return Long.toHexString(MathUtil.zeroExtend(this.wS, this.pC));
   }

   @Override
   public boolean kS() {
      return this.pC == 32 || this.pC == 64;
   }

   @Override
   public ajr wS() {
      int var2 = this.pC;
      if (this.getType() != null && this.getType().isFloat()) {
         var2 = this.getType().getBitsize();
      }

      Object var1;
      if (var2 == 32) {
         var1 = ajt.A(Float.intBitsToFloat((int)this.wS));
      } else {
         if (var2 != 64) {
            throw new RuntimeException("Cannot generate a float immediate");
         }

         var1 = aju.kS(Double.longBitsToDouble(this.wS));
      }

      if (this.isMutable()) {
         var1 = ((ajr)var1).pC(null);
      }

      return (ajr)var1;
   }

   @Override
   public ajr E() {
      return this;
   }

   @Override
   public IEImm _fadd(IEImm var1) {
      return this.wS()._fadd(var1);
   }

   @Override
   public IEImm _fsub(IEImm var1) {
      return this.wS()._fsub(var1);
   }

   @Override
   public IEImm _fmul(IEImm var1) {
      return this.wS()._fmul(var1);
   }

   @Override
   public IEImm _fdiv(IEImm var1) {
      return this.wS()._fdiv(var1);
   }

   @Override
   public Integer _fcmp(IEImm var1) {
      return this.wS()._fcmp(var1);
   }

   @Override
   public boolean canReadAsSingleFloat() {
      return this.pC == 32;
   }

   @Override
   public float getValueAsSingleFloat() {
      if (!this.canReadAsSingleFloat()) {
         throw new IllegalStateException("Cannot return value as a 32-bit single-precision float");
      } else {
         return Float.intBitsToFloat((int)this.wS);
      }
   }

   @Override
   public boolean canReadAsDoubleFloat() {
      return this.pC == 64;
   }

   @Override
   public double getValueAsDoubleFloat() {
      if (!this.canReadAsDoubleFloat()) {
         throw new IllegalStateException("Cannot return value as a 64-bit double-precision float");
      } else {
         return Double.longBitsToDouble(this.wS);
      }
   }
}
