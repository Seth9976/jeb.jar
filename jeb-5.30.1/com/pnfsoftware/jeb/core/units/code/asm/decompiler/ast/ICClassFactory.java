package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICClassFactory {
   ICClass get(String var1);

   ICClass create(String var1);

   ICClass create(INativeClassItem var1, boolean var2);
}
