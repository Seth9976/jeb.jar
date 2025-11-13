package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICTypeFactory {
   ICType create(IWildcardType var1);

   ICType getVoid();

   ICType getInt();

   ICType getUnsignedInt();

   ICType getChar();
}
