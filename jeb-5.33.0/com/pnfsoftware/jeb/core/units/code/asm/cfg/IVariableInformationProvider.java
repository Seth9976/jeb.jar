package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import com.pnfsoftware.jeb.core.units.code.ICFGOwnerContext;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IVariableInformationProvider extends ICFGOwnerContext {
   String getSliceName(int var1, int var2, int var3);
}
