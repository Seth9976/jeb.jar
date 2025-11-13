package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDIndex;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstantFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.math.MathUtil;
import java.util.Collection;
import java.util.Set;

public class bps extends bph implements IDImm {
   private final long A;
   private final IDIndex kS;

   private bps(IJavaType var1, long var2, IDIndex var4) {
      super(var1);
      this.A = var2;
      this.kS = var4;
   }

   bps(long var1, IJavaType var3) {
      super(var3);
      this.A = pC(var1, var3);
      this.kS = null;
      if (!var3.isLegal()) {
         throw new RuntimeException("Illegal type: " + var3);
      }
   }

   bps(IJavaType var1, IDIndex var2) {
      super(var1);
      this.A = var2.getValue() & 4294967295L;
      this.kS = var2;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (int)(this.A ^ this.A >>> 32);
      return 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
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
         bps var3 = (bps)var1;
         if (this.A != var3.A && this.getRawValue() != var3.getRawValue()) {
            return false;
         } else {
            if (this.kS == null) {
               if (var3.kS != null) {
                  return false;
               }
            } else if (!this.kS.equalsEx(var3.kS, var2)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public IDExpression copy(DCopyOptions var1) {
      if (var1 != null) {
         IDExpression var2 = var1.onDup(this);
         if (var2 != null) {
            return var2;
         }
      }

      bps var3 = new bps(this.pC, this.A, this.kS);
      super.A(var3);
      return var3;
   }

   @Override
   public IDImm duplicate() {
      return (bps)this.copy(null);
   }

   @Override
   public IDImm duplicateWithDifferentType(IJavaType var1) {
      if (this.kS != null) {
         return this.duplicate();
      } else {
         bps var2 = new bps(var1, this.A, null);
         super.pC(var2);
         return var2;
      }
   }

   private static long pC(long var0, IJavaType var2) {
      return A(var0, var2);
   }

   private static long A(long var0, IJavaType var2) {
      if (var2 == null) {
         return var0;
      } else {
         long var3;
         if (var2.isBoolean()) {
            var3 = var0 & 1L;
         } else if (var2.isByte()) {
            var3 = (byte)var0;
         } else if (var2.isShort()) {
            var3 = (short)var0;
         } else if (var2.isChar()) {
            var3 = var0 & 65535L;
         } else if (var2.isInt()) {
            var3 = (int)var0;
         } else if (var2.isLong()) {
            var3 = var0;
         } else if (var2.isFloat()) {
            var3 = var0 & 4294967295L;
         } else if (var2.isDouble()) {
            var3 = var0;
         } else if (var2.isSmallIntegerWildcard()) {
            var3 = (int)var0;
         } else {
            int var5 = var2.getSlotCount();
            if (var5 == 1) {
               var3 = var0 & 4294967295L;
            } else {
               var3 = var0;
            }
         }

         return var3;
      }
   }

   @Override
   public boolean isRef() {
      return this.kS == null && this.pC.isObject();
   }

   @Override
   public boolean isNullRef() {
      return this.isRef() && this.getObjectReferenceId() == 0L;
   }

   @Override
   public boolean isNonNullRef() {
      return this.isRef() && this.getObjectReferenceId() != 0L;
   }

   @Override
   public boolean maybeRef() {
      return this.kS == null && (this.pC.isObject() || this.pC.isSingleSlotWildcard() || this.pC.isSmallInt() || this.pC.isInt());
   }

   @Override
   public boolean maybeNullRef() {
      return this.maybeRef() && this.getObjectReferenceId() == 0L;
   }

   @Override
   public boolean maybeNonNullRef() {
      return this.maybeRef() && this.getObjectReferenceId() != 0L;
   }

   @Override
   public boolean isString() {
      return this.kS != null;
   }

   @Override
   public int getStringIndex() {
      return this.kS.getValue();
   }

   @Override
   public String getStringValue(IDGlobalContext var1) {
      int var2 = this.kS.getValue();
      return var1.getDex().getString(var2).getValue();
   }

   @Override
   public long getRawValue() {
      return pC(this.A, this.pC);
   }

   @Override
   public Object getImmediateAsJavaObject(IDGlobalContext var1) {
      if (this.isString()) {
         return this.getStringValue(var1);
      } else {
         if (this.pC.isPrimitive()) {
            String var2 = this.pC.getName();
            switch (var2) {
               case "Z":
                  return (this.A & 1L) != 0L;
               case "B":
                  return (byte)this.A;
               case "C":
                  return (char)this.A;
               case "S":
                  return (short)this.A;
               case "I":
                  return (int)this.A;
               case "J":
                  return this.A;
               case "F":
                  return Float.intBitsToFloat((int)this.A);
               case "D":
                  return Double.longBitsToDouble(this.A);
            }
         }

         return null;
      }
   }

   @Override
   public int getObjectReferenceId() {
      return (int)this.A;
   }

   @Override
   public boolean isZeroEquivalent() {
      return this.isZero();
   }

   @Override
   public boolean isZero() {
      long var1 = pC(this.A, this.pC);
      return var1 == 0L;
   }

   @Override
   public boolean isOnes() {
      long var1 = pC(this.A, this.pC);
      int var3 = this.pC.getEncodingBitsize();
      return MathUtil.signExtend(var1, var3) == -1L;
   }

   @Override
   public void collectVarIds(Set var1) {
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
   }

   @Override
   public final boolean setType(IJavaType var1, DTypeInfo var2, boolean var3) {
      if (this.isString()) {
         return var1 != null && var1.isJavaLangString();
      } else {
         if (var1 != null) {
            if (var1.isBoolean() && (this.A & -2L) != 0L) {
               return false;
            }

            if (var1.isByte() && (byte)this.A != this.A) {
               return false;
            }

            if (var1.isShort() && (short)this.A != this.A) {
               return false;
            }

            if (var1.isChar() && (this.A & -65536L) != 0L) {
               return false;
            }
         }

         return super.setType(var1, var2, var3);
      }
   }

   @Override
   public boolean pC() {
      return false;
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      return false;
   }

   @Override
   public boolean hasSideEffects(IDMethodContext var1, boolean var2) {
      return false;
   }

   @Override
   public int countVariable(IDVar var1) {
      return 0;
   }

   @Override
   public int replaceVariable(IDVar var1, IDExpression var2) {
      return 0;
   }

   @Override
   public void pC(IJavaType var1, IJavaType var2) {
      super.pC(var1, var2);
   }

   @Override
   public void collectSubExpressions(Collection var1) {
   }

   @Override
   public boolean replaceSubExpression(IDExpression var1, IDExpression var2) {
      return false;
   }

   @Override
   public void format(DFormattingContext var1) {
      if (this.kS != null) {
         int var5 = this.kS.getValue();
         String var3 = null;
         if (var1.getMethodContext() != null) {
            var3 = var1.getMethodContext().getDex().getString(var5).getValue();
         }

         if (var3 == null) {
            var1.appendFormat("#%d", var5);
         } else {
            var1.append('"');
            var1.append(Formatter.escapeString(var3.length() < 200 ? var3 : var3.substring(0, 200) + "...<trimmed>...", false));
            var1.append('"');
         }
      } else if (this.pC.isObject()) {
         int var2 = this.getObjectReferenceId();
         if (var2 == 0) {
            var1.appendFormat("null");
         } else {
            var1.appendFormat("$ref_%d", var2);
            var1.appendFormattedTypeIf(this.pC);
         }
      } else {
         try {
            if (this.pC.isBoolean()) {
               var1.append(this.isZero() ? "false" : "true");
               return;
            }

            if (this.pC.isByte()) {
               var1.appendFormat("%02X", (byte)this.toLong());
               return;
            }

            if (this.pC.isChar()) {
               var1.append("'").append(Formatter.escapeCharacter((char)this.toLong())).append("'");
               return;
            }

            if (this.pC.isShort()) {
               var1.appendFormat("%04X", (short)this.toLong());
               return;
            }

            if (this.pC.isInt()) {
               var1.appendFormat("%X", (int)this.toLong());
               return;
            }

            if (this.pC.isSmallIntegerWildcard()) {
               var1.appendFormat("%X", (int)this.toLong());
               var1.appendFormattedTypeIf(this.pC);
               return;
            }

            if (this.pC.isLong()) {
               var1.appendFormat("%XL", this.toLong());
               return;
            }

            if (this.pC.isFloat()) {
               var1.appendFormat("%ff", this.toFloat());
               return;
            }

            if (this.pC.isDouble()) {
               var1.appendFormat("%f", this.toDouble());
               return;
            }
         } catch (Exception var4) {
         }

         var1.appendFormat("%X", this.getRawValue());
         var1.appendFormattedTypeIf(this.pC);
      }
   }

   @Override
   public boolean canReadAsLong() {
      IJavaType var1 = this.getType();
      return var1 != null && !var1.isFloatingPointType() && !var1.isObject();
   }

   @Override
   public long getValueAsLong() {
      try {
         return this.toLong();
      } catch (DexDecEvaluationException var2) {
         throw new RuntimeException(var2);
      }
   }

   @Override
   public long toUnsignedLong() throws DexDecEvaluationException {
      IJavaType var1 = this.getType();
      if (var1 == null) {
         throw new DexDecEvaluationException("Missing type: " + this);
      } else {
         long var2;
         if (var1.isBoolean()) {
            var2 = this.A & 1L;
         } else if (var1.isByte()) {
            var2 = this.A & 255L;
         } else if (var1.isChar() || var1.isShort()) {
            var2 = this.A & 65535L;
         } else if (var1.isSmallInt()) {
            var2 = this.A & 4294967295L;
         } else if (var1.isLong()) {
            var2 = this.A;
         } else if (var1.isSingleSlotWildcard()) {
            var2 = this.A & 4294967295L;
         } else if (var1.isDoubleSlotWildcard()) {
            var2 = this.A;
         } else {
            if (!var1.isObject()) {
               throw new DexDecEvaluationException("Cannot evaluate as ulong: " + this);
            }

            var2 = this.A;
         }

         return var2;
      }
   }

   @Override
   public long toLong() throws DexDecEvaluationException {
      return this.toLong(false);
   }

   @Override
   public long toLong(boolean var1) throws DexDecEvaluationException {
      if (this.pC == null) {
         throw new DexDecEvaluationException("Missing type: " + this);
      } else {
         Long var2 = this.pC(var1);
         if (var2 == null) {
            throw new DexDecEvaluationException("Cannot evaluate as long: " + this);
         } else {
            return var2;
         }
      }
   }

   Long pC(boolean var1) {
      Long var2;
      if (this.pC.isBoolean()) {
         var2 = this.A & 1L;
      } else if (this.pC.isByte()) {
         var2 = this.A & 255L;
         if ((var2 & 128L) != 0L) {
            var2 = var2 | -256L;
         }
      } else if (this.pC.isChar()) {
         var2 = this.A & 65535L;
      } else if (this.pC.isShort()) {
         var2 = this.A & 65535L;
         if ((var2 & 32768L) != 0L) {
            var2 = var2 | -65536L;
         }
      } else if (this.pC.isSmallInt()) {
         var2 = this.A & 4294967295L;
         if ((var2 & 2147483648L) != 0L) {
            var2 = var2 | -4294967296L;
         }
      } else if (this.pC.isLong()) {
         var2 = this.A;
      } else if (this.pC.isSingleSlotWildcard()) {
         var2 = this.A & 4294967295L;
         if (var1 && (var2 & 2147483648L) != 0L) {
            var2 = var2 | -4294967296L;
         }
      } else if (this.pC.isDoubleSlotWildcard()) {
         var2 = this.A;
      } else if (this.pC.isObject()) {
         var2 = this.A;
      } else {
         var2 = null;
      }

      return var2;
   }

   @Override
   public float toFloat() throws DexDecEvaluationException {
      IJavaType var1 = this.getType();
      if (var1 == null) {
         throw new DexDecEvaluationException("Missing type");
      } else {
         float var2;
         if (var1.isFloat()) {
            var2 = Float.intBitsToFloat((int)this.A);
         } else if (var1.isDouble()) {
            var2 = (float)Double.longBitsToDouble(this.A);
         } else if (var1.isSingleSlotWildcard()) {
            var2 = Float.intBitsToFloat((int)this.A);
         } else {
            if (!var1.isDoubleSlotWildcard()) {
               if (var1.isByte()) {
                  return (byte)this.A;
               }

               if (var1.isChar()) {
                  return (char)this.A;
               }

               if (var1.isShort()) {
                  return (short)this.A;
               }

               if (var1.isInt()) {
                  return (int)this.A;
               }

               if (var1.isLong()) {
                  return (float)this.A;
               }

               throw new DexDecEvaluationException("Cannot evaluate");
            }

            var2 = (float)Double.longBitsToDouble(this.A);
         }

         return var2;
      }
   }

   @Override
   public double toDouble() throws DexDecEvaluationException {
      IJavaType var1 = this.getType();
      if (var1 == null) {
         throw new DexDecEvaluationException("Missing type");
      } else {
         double var2;
         if (var1.isFloat()) {
            var2 = Float.intBitsToFloat((int)this.A);
         } else if (var1.isDouble()) {
            var2 = Double.longBitsToDouble(this.A);
         } else if (var1.isSingleSlotWildcard()) {
            var2 = Float.intBitsToFloat((int)this.A);
         } else {
            if (!var1.isDoubleSlotWildcard()) {
               if (var1.isByte()) {
                  return (byte)this.A;
               }

               if (var1.isChar()) {
                  return (char)this.A;
               }

               if (var1.isShort()) {
                  return (short)this.A;
               }

               if (var1.isInt()) {
                  return (int)this.A;
               }

               if (var1.isLong()) {
                  return this.A;
               }

               throw new DexDecEvaluationException("Cannot evaluate");
            }

            var2 = Double.longBitsToDouble(this.A);
         }

         return var2;
      }
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      return (IDImm)(this.kS != null && var1 != null ? var1.registerObject(this.getStringValue(var1.getGlobalContext())) : this);
   }

   @Override
   public IJavaElement generateAST(IDMethodContext var1, IJavaMethod var2) {
      IJavaConstantFactory var3 = var2.getGlobalContext().getConstantFactory();
      IJavaConstant var4;
      if (this.kS != null) {
         int var5 = this.kS.getValue();
         String var6 = var1.getDex().getString(var5).getValue();
         var4 = var3.createString(var6, this.getOrigin());
      } else if (this.pC.isBoolean()) {
         boolean var7 = (this.A & 1L) != 0L;
         var4 = var3.createBoolean(var7, this.getOrigin());
      } else if (this.pC.isByte()) {
         byte var8 = (byte)this.A;
         var4 = var3.createByte(var8, this.getOrigin());
      } else if (this.pC.isChar()) {
         char var9 = (char)this.A;
         var4 = var3.createChar(var9, this.getOrigin());
      } else if (this.pC.isShort()) {
         short var10 = (short)this.A;
         var4 = var3.createShort(var10, this.getOrigin());
      } else if (!this.pC.isInt() && (!this.pC.isSmallIntegerWildcard() || !((bic)var2.getGlobalContext()).gp)) {
         if (this.pC.isLong()) {
            long var12 = this.A;
            var4 = var3.createLong(var12, this.getOrigin());
         } else if (this.pC.isFloat()) {
            float var13 = Float.intBitsToFloat((int)this.A);
            var4 = var3.createFloat(var13, this.getOrigin());
         } else if (this.pC.isDouble()) {
            double var14 = Double.longBitsToDouble(this.A);
            var4 = var3.createDouble(var14, this.getOrigin());
         } else {
            int var15 = this.getObjectReferenceId();
            if (var15 == 0) {
               var4 = var3.createNull();
            } else {
               var4 = var3.createInt(var15);
            }
         }
      } else {
         int var11 = (int)this.A;
         var4 = var3.createInt(var11, this.getOrigin());
      }

      return this.pC(var4);
   }

   bps pC(long var1) {
      IJavaType var3;
      if (!this.pC.isSmallInt() && !this.pC.isBoolean()) {
         if (!this.pC.isLong()) {
            throw new RuntimeException("Cannot fork the immediate value with a suitable type (this type= " + this.pC + ")");
         }

         var3 = this.pC.getFactory().getLong();
      } else {
         var3 = this.pC.getFactory().getInt();
      }

      return new bps(var1, var3);
   }

   @Override
   public IDImm _add(IDImm var1) {
      long var2 = this.getValueAsLong();
      return this.pC(var2 + var1.getRawValue());
   }

   @Override
   public IDImm _sub(IDImm var1) {
      long var2 = this.getValueAsLong();
      return this.pC(var2 - var1.getRawValue());
   }

   @Override
   public IDImm _mul(IDImm var1) {
      long var2 = this.getValueAsLong();
      return this.pC(var2 * var1.getRawValue());
   }

   @Override
   public IDImm _div(IDImm var1) {
      long var2 = this.getValueAsLong();
      return this.pC(var2 / var1.getRawValue());
   }

   @Override
   public IDImm _rem(IDImm var1) {
      long var2 = this.getValueAsLong();
      return this.pC(var2 % var1.getRawValue());
   }

   @Override
   public IDImm _neg() {
      long var1 = this.getValueAsLong();
      return this.pC(-var1);
   }

   @Override
   public IDImm _not() {
      long var1 = this.getValueAsLong();
      return this.pC(~var1);
   }

   @Override
   public IDImm _and(IDImm var1) {
      long var2 = this.getValueAsLong();
      return this.pC(var2 & var1.getRawValue());
   }

   @Override
   public IDImm _or(IDImm var1) {
      long var2 = this.getValueAsLong();
      return this.pC(var2 | var1.getRawValue());
   }

   @Override
   public IDImm _xor(IDImm var1) {
      long var2 = this.getValueAsLong();
      return this.pC(var2 ^ var1.getRawValue());
   }

   @Override
   public boolean _testbit(int var1) {
      int var2 = this.pC.getEncodingBitsize();
      if (var1 >= 0 && var1 < var2) {
         long var3 = this.getValueAsLong();
         return (var3 & 1L << var1) != 0L;
      } else {
         throw new ArithmeticException("Invalid bit position");
      }
   }

   private static int pC(int var0, int var1) {
      if (var1 == 64) {
         var0 &= 63;
      } else if (var1 == 32) {
         var0 &= 31;
      } else if (var1 == 8) {
         var0 &= 7;
      } else {
         var0 %= var1;
         if (var0 < 0) {
            var0 += var1;
         }
      }

      return var0;
   }

   @Override
   public IDImm _shl(int var1) {
      int var2 = bpl.A(this);
      var1 = pC(var1, var2);
      long var3 = this.getValueAsLong();
      return this.pC(var3 << var1);
   }

   @Override
   public IDImm _shr(int var1) {
      int var2 = bpl.A(this);
      var1 = pC(var1, var2);
      long var3 = MathUtil.makeMask(var2);
      long var5 = this.getValueAsLong();
      return this.pC((var5 & var3) >>> var1);
   }

   @Override
   public IDImm _sar(int var1) {
      int var2 = bpl.A(this);
      var1 = pC(var1, var2);
      long var3 = this.getValueAsLong();
      return this.pC(var3 >> var1);
   }

   @Override
   public IDImm _pow(int var1) {
      if (var1 < 0) {
         throw new ArithmeticException("Invalid exponent");
      } else {
         long var2 = this.getValueAsLong();

         long var4;
         for (var4 = 1L; var1 > 0; var1--) {
            var4 *= var2;
         }

         return this.pC(var4);
      }
   }

   @Override
   public int _cmp(IDImm var1) {
      long var2 = this.getValueAsLong();
      return Long.compare(var2, var1.getRawValue());
   }

   @Override
   public int _cmpU(IDImm var1) {
      long var2 = this.getValueAsLong();
      return Long.compareUnsigned(var2, var1.getRawValue());
   }

   @Override
   public boolean _isPowerOf2() {
      return !this.isZero() && this._and(this._sub(this.pC(1L))).isZero();
   }

   @Override
   public Integer _log2() {
      if (this._isPowerOf2()) {
         Object var1 = this;

         for (int var2 = 0; var2 < this.pC.getEncodingBitsize(); var2++) {
            boolean var3 = ((IDImm)var1)._testbit(0);
            var1 = ((IDImm)var1)._shr(1);
            if (var3) {
               if (((IDImm)var1).isZero()) {
                  return var2;
               }

               return null;
            }
         }
      }

      return null;
   }
}
