package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICNamingEngine {
   boolean beautifyIdentifierNames(ICClass var1, IDynamicContentManager var2);

   boolean beautifyIdentifierNames(ICField var1, IDynamicContentManager var2);

   boolean beautifyIdentifierNames(ICMethod var1, IDynamicContentManager var2);
}
