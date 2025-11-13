package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.math.BigInteger;

@Ser
public interface ICConstantInteger extends ICConstant {
   int getBitsize();

   boolean isUnsigned();

   BigInteger getIntegerValue();

   long getValueAsLong();

   boolean isPositive();

   boolean isNegative();

   boolean needsCustomFormatting();

   NumberFormatter getFormatter();

   ICConstantInteger duplicate2();
}
