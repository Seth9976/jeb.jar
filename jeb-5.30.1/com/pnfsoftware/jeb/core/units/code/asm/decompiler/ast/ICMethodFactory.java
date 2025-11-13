package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICMethodFactory {
   ICMethod get(String var1);

   ICMethod create(String var1);

   ICMethod createSynthetic(String var1);
}
