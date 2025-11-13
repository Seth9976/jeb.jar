package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.math.BigInteger;

@Ser
public interface ICConstantFactory {
   ICConstantString createString(String var1, long var2);

   ICConstantString createString(String var1, long var2, Integer var4);

   ICConstantPointer getNull();

   ICConstantPointer createPointer(long var1);

   ICConstantInteger createInt(BigInteger var1, int var2);

   ICConstantInteger createChar(int var1);

   ICConstantInteger32 createInt32(int var1);

   ICConstantInteger64 createInt64(long var1);

   ICConstantInteger32 createUnsignedInt32(int var1);

   ICConstantInteger64 createUnsignedInt64(long var1);

   ICConstantIntegerLarge createIntLarge(BigInteger var1, int var2);

   ICConstantFloat32 createFloat32(float var1);

   ICConstantFloat64 createFloat64(double var1);
}
