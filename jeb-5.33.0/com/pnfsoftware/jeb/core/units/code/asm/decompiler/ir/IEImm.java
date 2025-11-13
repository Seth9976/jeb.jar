package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.math.BigInteger;

@Ser
public interface IEImm extends IEGeneric {
   boolean isMutable();

   IWildcardType.Group getGroup();

   IEImm duplicate();

   IEImm duplicateToMutable();

   IEImm duplicateWithType(IWildcardType var1);

   boolean setCustomAST(ICElement var1);

   ICElement getCustomAST();

   IEImm normalize();

   String toHexString();

   boolean canReadAsLong();

   long getValueAsLong();

   boolean canReadAsUnsignedLong();

   long getValueAsUnsignedLong();

   boolean canReadAsAddress();

   long getValueAsAddress();

   boolean isStringLiteral();

   String getStringLiteral();

   boolean isZero();

   boolean isOnes();

   IEImm zeroExtend(int var1);

   IEImm signExtend(int var1);

   BigInteger getValue();

   BigInteger getUnsignedValue();

   IEImm truncate(int var1);

   IEImm expand(int var1);

   int _bitlength();

   int _signum();

   IEImm _neg();

   IEImm _add(IEImm var1);

   IEImm _sub(IEImm var1);

   IEImm _mul(IEImm var1);

   IEImm _div(IEImm var1);

   IEImm _divU(IEImm var1);

   IEImm _rem(IEImm var1);

   IEImm _remU(IEImm var1);

   IEImm _not();

   IEImm _and(IEImm var1);

   IEImm _or(IEImm var1);

   IEImm _xor(IEImm var1);

   boolean _testbit(int var1);

   IEImm _setbit(int var1);

   IEImm _clearbit(int var1);

   IEImm _shl(int var1);

   IEImm _shr(int var1);

   IEImm _sar(int var1);

   IEImm _rol(int var1);

   IEImm _ror(int var1);

   IEImm _pow(int var1);

   int _cmp(IEImm var1);

   int _cmpU(IEImm var1);

   boolean _isPowerOf2();

   Integer _log2();

   boolean canReadAsSingleFloat();

   float getValueAsSingleFloat();

   boolean canReadAsDoubleFloat();

   double getValueAsDoubleFloat();

   IEImm _fadd(IEImm var1);

   IEImm _fsub(IEImm var1);

   IEImm _fmul(IEImm var1);

   IEImm _fdiv(IEImm var1);

   Integer _fcmp(IEImm var1);
}
