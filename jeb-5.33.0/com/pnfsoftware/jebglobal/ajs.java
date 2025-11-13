package com.pnfsoftware.jebglobal;

import com.google.common.collect.MapMaker;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.primitives.LargeIntHandler;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.math.BigInteger;
import java.util.Map;

@Ser
public class ajs extends ajr {
   private static final StructuredLogger UT = aco.pC(ajs.class);
   private static Map E = new MapMaker().weakValues().makeMap();
   @SerId(1)
   protected BigInteger wS;
   @SerTransient
   private LargeIntHandler sY;

   public static ajs A(BigInteger var0, int var1) {
      LargeIntHandler var2 = LargeIntHandler.create(var1);
      BigInteger var3 = var2.truncate(var0);
      Couple var4 = new Couple(var3, var1);
      ajs var5 = (ajs)E.get(var4);
      if (var5 == null) {
         var5 = new ajs(var2, var3, var1);
         E.put(var4, var5);
      }

      return var5;
   }

   public static ajs A(byte[] var0, int var1) {
      return A(new BigInteger(var0), var1);
   }

   public static ajs A(String var0, int var1) {
      byte var2 = 10;
      if (var0.length() >= 2) {
         if (var0.startsWith("0x")) {
            var2 = 16;
            var0 = var0.substring(2);
         } else if (var0.startsWith("0")) {
            var2 = 8;
            var0 = var0.substring(1);
         }
      }

      var0 = var0.replace("_", "");
      return A(new BigInteger(var0, var2), var1);
   }

   @SerCustomInit
   private void gp() {
      this.sY = LargeIntHandler.create(this.pC);
   }

   private ajs(LargeIntHandler var1, BigInteger var2, int var3) {
      super(var3);
      this.sY = var1;
      this.wS = var2;
   }

   @Override
   public IWildcardType.Group getGroup() {
      return IWildcardType.Group.INTEGER;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      if (this.pC <= 64) {
         long var2 = this.wS.longValue();
         var1 = 31 * var1 + (int)(var2 ^ var2 >>> 32);
      } else {
         var1 = 31 * var1 + this.wS.hashCode();
      }

      return var1;
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (var1 instanceof ajs var4) {
         return this.wS.equals(var4.wS);
      } else {
         return var1 instanceof ajw var3 ? this.wS.longValue() == var3.wS : false;
      }
   }

   public ajr sY() {
      ajs var1 = new ajs(this.sY, this.wS, this.pC);
      return super.pC(var1);
   }

   @Override
   public boolean canReadAsLong() {
      return this.pC <= 64 || this.wS.bitLength() <= 63;
   }

   @Override
   public long getValueAsLong() {
      if (!this.canReadAsLong()) {
         throw new EvaluationException("Cannot return value as a 64-bit long");
      } else {
         return this.wS.longValue();
      }
   }

   @Override
   public boolean canReadAsUnsignedLong() {
      return this.pC <= 63 || this._signum() >= 0 && (this.pC == 64 || this.wS.bitLength() <= 63);
   }

   @Override
   public long getValueAsUnsignedLong() {
      if (!this.canReadAsUnsignedLong()) {
         throw new EvaluationException(Strings.ff("Cannot return value as a 64-bit unsigned long: %s", this));
      } else {
         return this.pC >= 64 ? this.wS.longValue() : MathUtil.zeroExtend(this.wS.longValue(), this.pC);
      }
   }

   @Override
   public BigInteger getValue() {
      return this.wS;
   }

   @Override
   public BigInteger getUnsignedValue() {
      return this.sY.toUnsigned(this.wS);
   }

   public ajr pC(int var1) {
      return A(LargeIntHandler.create(var1).truncate(this.wS), var1);
   }

   public ajr A(int var1) {
      BigInteger var2 = this.sY.toUnsigned(this.wS);
      return A(LargeIntHandler.create(var1).truncate(var2), var1);
   }

   @Override
   public void pC(akz var1) {
      String var2 = "";
      if (var1.pC()) {
         var2 = var2 + "i" + this.pC + ":";
      }
      var1.append(switch (this.pC) {
         case 1 -> var2 + Long.toString(this.wS.longValue() & 1L);
         case 2 -> var2 + Long.toString(this.wS.longValue() & 3L);
         case 8 -> var2 + Strings.ff("%02Xh", this.wS.longValue() & 255L);
         case 16 -> var2 + Strings.ff("%04Xh", this.wS.longValue() & 65535L);
         case 32 -> var2 + Strings.ff("%08Xh", this.wS.longValue() & 4294967295L);
         case 64 -> var2 + Strings.ff("%016Xh", this.wS.longValue());
         default -> var2 + this.sY.toUnsigned(this.wS).toString(16).toUpperCase() + "h";
      }, ItemClassIdentifiers.IMMEDIATE);
      if (this.A) {
         var1.A(this);
      }
   }

   private ajs oT(IEImm var1) {
      if (var1.getBitsize() != this.pC) {
         throw new RuntimeException("Mismatched bit sizes");
      } else {
         if (var1 instanceof ajw) {
            var2 = A(BigInteger.valueOf(((ajw)var1).getValueAsLong()), ((ajw)var1).pC);
            if (this.isMutable()) {
               var2 = (ajs)var2.pC(var1.getType());
            }
         } else if (!(var1 instanceof ajs var2)) {
            if (var1 instanceof ajt) {
               var2 = A(((ajt)var1).getValue(), 32);
            } else if (var1 instanceof aju) {
               var2 = A(((aju)var1).getValue(), 64);
            } else {
               if (!(var1 instanceof ajv)) {
                  throw new RuntimeException();
               }

               var2 = A(((ajv)var1).getValue(), 64);
            }
         }

         return var2;
      }
   }

   @Override
   public int _signum() {
      return this.wS.signum();
   }

   public ajr ys() {
      return A(this.sY.neg(this.wS), this.pC);
   }

   public ajr pC(IEImm var1) {
      ajs var2 = this.oT(var1);
      return A(this.sY.add(this.wS, var2.wS), this.pC);
   }

   public ajr A(IEImm var1) {
      ajs var2 = this.oT(var1);
      return A(this.sY.sub(this.wS, var2.wS), this.pC);
   }

   public ajr kS(IEImm var1) {
      ajs var2 = this.oT(var1);
      return A(this.sY.mulS(this.wS, var2.wS), this.pC);
   }

   public ajr wS(IEImm var1) {
      ajs var2 = this.oT(var1);
      return A(this.sY.divS(this.wS, var2.wS), this.pC);
   }

   public ajr UT(IEImm var1) {
      ajs var2 = this.oT(var1);
      return A(this.sY.divU(this.wS, var2.wS), this.pC);
   }

   public ajr E(IEImm var1) {
      ajs var2 = this.oT(var1);
      return A(this.sY.remS(this.wS, var2.wS), this.pC);
   }

   public ajr sY(IEImm var1) {
      ajs var2 = this.oT(var1);
      return A(this.sY.remU(this.wS, var2.wS), this.pC);
   }

   public ajr ld() {
      return A(this.sY.not(this.wS), this.pC);
   }

   public ajr ys(IEImm var1) {
      ajs var2 = this.oT(var1);
      return A(this.sY.and(this.wS, var2.wS), this.pC);
   }

   public ajr ld(IEImm var1) {
      ajs var2 = this.oT(var1);
      return A(this.sY.or(this.wS, var2.wS), this.pC);
   }

   public ajr gp(IEImm var1) {
      ajs var2 = this.oT(var1);
      return A(this.sY.xor(this.wS, var2.wS), this.pC);
   }

   @Override
   public boolean _testbit(int var1) {
      return this.sY.testbit(this.wS, var1);
   }

   public ajr kS(int var1) {
      return A(this.sY.setbit(this.wS, var1), this.pC);
   }

   public ajr wS(int var1) {
      return A(this.sY.clearbit(this.wS, var1), this.pC);
   }

   public ajr UT(int var1) {
      return A(this.sY.shl(this.wS, var1), this.pC);
   }

   public ajr E(int var1) {
      return A(this.sY.shr(this.wS, var1), this.pC);
   }

   public ajr sY(int var1) {
      return A(this.sY.sar(this.wS, var1), this.pC);
   }

   public ajr ys(int var1) {
      return (ajr)(var1 < 0 ? this.ld(-var1) : A(this.sY.rol(this.wS, var1), this.pC));
   }

   public ajr ld(int var1) {
      return (ajr)(var1 < 0 ? this.ys(-var1) : A(this.sY.ror(this.wS, var1), this.pC));
   }

   @Override
   public int _cmp(IEImm var1) {
      ajs var2 = this.oT(var1);
      return this.sY.compare(this.wS, var2.wS);
   }

   @Override
   public int _cmpU(IEImm var1) {
      ajs var2 = this.oT(var1);
      return this.sY.compareU(this.wS, var2.wS);
   }

   public ajr gp(int var1) {
      if (var1 < 0) {
         throw new ArithmeticException("Invalid exponent");
      } else {
         return A(this.wS.pow(var1), this.pC);
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      if (this.kS != null) {
         return this.kS;
      } else if (this.pC <= 32) {
         return var2.getGlobalContext().getConstantFactory().createInt32(this.wS.intValue());
      } else if (this.pC <= 64) {
         return var2.getGlobalContext().getConstantFactory().createInt64(this.wS.longValue());
      } else {
         if (this.getType() != null && this.getType().isFloat() && this.getGroup() != IWildcardType.Group.FLOAT && this.wS.bitLength() <= 64) {
            int var3 = this.getType().getEffectiveBitsize();
            if (var3 == 32 || var3 == 64) {
               return ajw.A(this.wS.longValue(), var3).wS().generateC(var1, var2);
            }
         }

         return var2.getGlobalContext().getConstantFactory().createIntLarge(this.wS, this.pC);
      }
   }

   @Override
   public String toHexString() {
      return this.sY.toUnsigned(this.wS).toString(16);
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
         var1 = ajt.A(Float.intBitsToFloat(this.wS.intValue()));
      } else {
         if (var2 != 64) {
            throw new RuntimeException("Cannot generate a float immediate");
         }

         var1 = aju.kS(Double.longBitsToDouble(this.wS.longValue()));
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
         return Float.intBitsToFloat(this.wS.intValue());
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
         return Double.longBitsToDouble(this.wS.longValue());
      }
   }
}
