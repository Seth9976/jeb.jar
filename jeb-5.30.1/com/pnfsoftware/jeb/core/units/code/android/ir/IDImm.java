package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.java.IJavaType;

public interface IDImm extends IDExpression {
   IDImm duplicateWithDifferentType(IJavaType var1);

   boolean isZeroEquivalent();

   boolean isZero();

   boolean isOnes();

   boolean isString();

   int getStringIndex();

   String getStringValue(IDGlobalContext var1);

   long getRawValue();

   Object getImmediateAsJavaObject(IDGlobalContext var1);

   long toUnsignedLong() throws DexDecEvaluationException;

   long toLong() throws DexDecEvaluationException;

   long toLong(boolean var1) throws DexDecEvaluationException;

   float toFloat() throws DexDecEvaluationException;

   double toDouble() throws DexDecEvaluationException;

   boolean isRef();

   boolean isNullRef();

   boolean isNonNullRef();

   boolean maybeRef();

   boolean maybeNullRef();

   boolean maybeNonNullRef();

   int getObjectReferenceId();

   IDImm duplicate();

   boolean canReadAsLong();

   long getValueAsLong();

   IDImm _add(IDImm var1);

   IDImm _sub(IDImm var1);

   IDImm _mul(IDImm var1);

   IDImm _div(IDImm var1);

   IDImm _rem(IDImm var1);

   IDImm _neg();

   IDImm _not();

   IDImm _and(IDImm var1);

   IDImm _or(IDImm var1);

   IDImm _xor(IDImm var1);

   boolean _testbit(int var1);

   IDImm _shl(int var1);

   IDImm _shr(int var1);

   IDImm _sar(int var1);

   IDImm _pow(int var1);

   int _cmp(IDImm var1);

   int _cmpU(IDImm var1);

   boolean _isPowerOf2();

   Integer _log2();
}
