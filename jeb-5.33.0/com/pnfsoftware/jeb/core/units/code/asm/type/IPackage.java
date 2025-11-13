package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.code.ICodePackage;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IPackage extends ICodePackage {
   ITypeManager getTypeManager();
}
