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
import java.util.Collection;
import java.util.Map;

@Ser
public class alv extends alu {
   private static final StructuredLogger Uv = aeg.q(alv.class);
   private static Map oW = new MapMaker().weakValues().makeMap();
   @SerId(1)
   protected BigInteger Dw;
   @SerTransient
   private LargeIntHandler gO;

   public static alv RF(BigInteger var0, int var1) {
      LargeIntHandler var2 = LargeIntHandler.create(var1);
      BigInteger var3 = var2.truncate(var0);
      Couple var4 = new Couple(var3, var1);
      alv var5 = (alv)oW.get(var4);
      if (var5 == null) {
         var5 = new alv(var2, var3, var1);
         oW.put(var4, var5);
      }

      return var5;
   }

   public static int gP() {
      return oW.size();
   }

   public static Collection za() {
      return oW.values();
   }

   public static alv RF(byte[] var0, int var1) {
      return RF(new BigInteger(var0), var1);
   }

   public static alv RF(String var0, int var1) {
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
      return RF(new BigInteger(var0, var2), var1);
   }

   @SerCustomInit
   private void io() {
      this.gO = LargeIntHandler.create(this.q);
   }

   private alv(LargeIntHandler var1, BigInteger var2, int var3) {
      super(var3);
      this.gO = var1;
      this.Dw = var2;
   }

   @Override
   public IWildcardType.Group getGroup() {
      return IWildcardType.Group.INTEGER;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      if (this.q <= 64) {
         long var2 = this.Dw.longValue();
         var1 = 31 * var1 + (int)(var2 ^ var2 >>> 32);
      } else {
         var1 = 31 * var1 + this.Dw.hashCode();
      }

      return var1;
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (var1 instanceof alv var4) {
         return this.Dw.equals(var4.Dw);
      } else {
         return var1 instanceof alz var3 ? this.Dw.longValue() == var3.Dw : false;
      }
   }

   public alu lm() {
      alv var1 = new alv(this.gO, this.Dw, this.q);
      return super.q(var1);
   }

   @Override
   public Object Dw() {
      return this.Dw;
   }

   @Override
   public boolean canReadAsLong() {
      return this.q <= 64 || this.Dw.bitLength() <= 63;
   }

   @Override
   public long getValueAsLong() {
      if (!this.canReadAsLong()) {
         throw new EvaluationException("Cannot return value as a 64-bit long");
      } else {
         return this.Dw.longValue();
      }
   }

   @Override
   public boolean canReadAsUnsignedLong() {
      return this.q <= 63 || this._signum() >= 0 && (this.q == 64 || this.Dw.bitLength() <= 63);
   }

   @Override
   public long getValueAsUnsignedLong() {
      if (!this.canReadAsUnsignedLong()) {
         throw new EvaluationException(Strings.ff("Cannot return value as a 64-bit unsigned long: %s", this));
      } else {
         return this.q >= 64 ? this.Dw.longValue() : MathUtil.zeroExtend(this.Dw.longValue(), this.q);
      }
   }

   @Override
   public BigInteger getValue() {
      return this.Dw;
   }

   @Override
   public BigInteger getUnsignedValue() {
      return this.gO.toUnsigned(this.Dw);
   }

   public alu RF(int var1) {
      return RF(LargeIntHandler.create(var1).truncate(this.Dw), var1);
   }

   public alu xK(int var1) {
      BigInteger var2 = this.gO.toUnsigned(this.Dw);
      return RF(LargeIntHandler.create(var1).truncate(var2), var1);
   }

   @Override
   public void q(and var1) {
      String var2 = "";
      if (var1.q()) {
         var2 = var2 + "i" + this.q + ":";
      }
      var1.append(switch (this.q) {
         case 1 -> var2 + Long.toString(this.Dw.longValue() & 1L);
         case 2 -> var2 + Long.toString(this.Dw.longValue() & 3L);
         case 8 -> var2 + Strings.ff("%02Xh", this.Dw.longValue() & 255L);
         case 16 -> var2 + Strings.ff("%04Xh", this.Dw.longValue() & 65535L);
         case 32 -> var2 + Strings.ff("%08Xh", this.Dw.longValue() & 4294967295L);
         case 64 -> var2 + Strings.ff("%016Xh", this.Dw.longValue());
         default -> var2 + this.gO.toUnsigned(this.Dw).toString(16).toUpperCase() + "h";
      }, ItemClassIdentifiers.IMMEDIATE);
      if (this.RF) {
         var1.RF(this);
      }
   }

   private alv lm(IEImm var1) {
      if (var1.getBitsize() != this.q) {
         throw new RuntimeException("Mismatched bit sizes");
      } else {
         if (var1 instanceof alz) {
            var2 = RF(BigInteger.valueOf(((alz)var1).getValueAsLong()), ((alz)var1).q);
            if (this.isMutable()) {
               var2 = (alv)var2.q(var1.getType());
            }
         } else if (!(var1 instanceof alv var2)) {
            if (var1 instanceof alw) {
               var2 = RF(((alw)var1).getValue(), 32);
            } else if (var1 instanceof alx) {
               var2 = RF(((alx)var1).getValue(), 64);
            } else {
               if (!(var1 instanceof aly)) {
                  throw new RuntimeException();
               }

               var2 = RF(((aly)var1).getValue(), 64);
            }
         }

         return var2;
      }
   }

   @Override
   public int _signum() {
      return this.Dw.signum();
   }

   public alu zz() {
      return RF(this.gO.neg(this.Dw), this.q);
   }

   public alu q(IEImm var1) {
      alv var2 = this.lm(var1);
      return RF(this.gO.add(this.Dw, var2.Dw), this.q);
   }

   public alu RF(IEImm var1) {
      alv var2 = this.lm(var1);
      return RF(this.gO.sub(this.Dw, var2.Dw), this.q);
   }

   public alu xK(IEImm var1) {
      alv var2 = this.lm(var1);
      return RF(this.gO.mulS(this.Dw, var2.Dw), this.q);
   }

   public alu Dw(IEImm var1) {
      alv var2 = this.lm(var1);
      return RF(this.gO.divS(this.Dw, var2.Dw), this.q);
   }

   public alu Uv(IEImm var1) {
      alv var2 = this.lm(var1);
      return RF(this.gO.divU(this.Dw, var2.Dw), this.q);
   }

   public alu oW(IEImm var1) {
      alv var2 = this.lm(var1);
      return RF(this.gO.remS(this.Dw, var2.Dw), this.q);
   }

   public alu gO(IEImm var1) {
      alv var2 = this.lm(var1);
      return RF(this.gO.remU(this.Dw, var2.Dw), this.q);
   }

   public alu JY() {
      return RF(this.gO.not(this.Dw), this.q);
   }

   public alu nf(IEImm var1) {
      alv var2 = this.lm(var1);
      return RF(this.gO.and(this.Dw, var2.Dw), this.q);
   }

   public alu gP(IEImm var1) {
      alv var2 = this.lm(var1);
      return RF(this.gO.or(this.Dw, var2.Dw), this.q);
   }

   public alu za(IEImm var1) {
      alv var2 = this.lm(var1);
      return RF(this.gO.xor(this.Dw, var2.Dw), this.q);
   }

   @Override
   public boolean _testbit(int var1) {
      return this.gO.testbit(this.Dw, var1);
   }

   public alu Dw(int var1) {
      return RF(this.gO.setbit(this.Dw, var1), this.q);
   }

   public alu Uv(int var1) {
      return RF(this.gO.clearbit(this.Dw, var1), this.q);
   }

   public alu oW(int var1) {
      return RF(this.gO.shl(this.Dw, var1), this.q);
   }

   public alu gO(int var1) {
      return RF(this.gO.shr(this.Dw, var1), this.q);
   }

   public alu nf(int var1) {
      return RF(this.gO.sar(this.Dw, var1), this.q);
   }

   public alu gP(int var1) {
      return (alu)(var1 < 0 ? this.za(-var1) : RF(this.gO.rol(this.Dw, var1), this.q));
   }

   public alu za(int var1) {
      return (alu)(var1 < 0 ? this.gP(-var1) : RF(this.gO.ror(this.Dw, var1), this.q));
   }

   @Override
   public int _cmp(IEImm var1) {
      alv var2 = this.lm(var1);
      return this.gO.compare(this.Dw, var2.Dw);
   }

   @Override
   public int _cmpU(IEImm var1) {
      alv var2 = this.lm(var1);
      return this.gO.compareU(this.Dw, var2.Dw);
   }

   public alu lm(int var1) {
      if (var1 < 0) {
         throw new ArithmeticException("Invalid exponent");
      } else {
         return RF(this.Dw.pow(var1), this.q);
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      if (this.xK != null) {
         return this.xK;
      } else if (this.q <= 32) {
         return var2.getGlobalContext().getConstantFactory().createInt32(this.Dw.intValue());
      } else if (this.q <= 64) {
         return var2.getGlobalContext().getConstantFactory().createInt64(this.Dw.longValue());
      } else {
         if (this.getType() != null && this.getType().isFloat() && this.getGroup() != IWildcardType.Group.FLOAT && this.Dw.bitLength() <= 64) {
            int var3 = this.getType().getEffectiveBitsize();
            if (var3 == 32 || var3 == 64) {
               return alz.RF(this.Dw.longValue(), var3).oW().generateC(var1, var2);
            }
         }

         return var2.getGlobalContext().getConstantFactory().createIntLarge(this.Dw, this.q);
      }
   }

   @Override
   public String toHexString() {
      return this.gO.toUnsigned(this.Dw).toString(16);
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
         var1 = alw.RF(Float.intBitsToFloat(this.Dw.intValue()));
      } else {
         if (var2 != 64) {
            throw new RuntimeException("Cannot generate a float immediate");
         }

         var1 = alx.xK(Double.longBitsToDouble(this.Dw.longValue()));
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
         return Float.intBitsToFloat(this.Dw.intValue());
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
         return Double.longBitsToDouble(this.Dw.longValue());
      }
   }
}
