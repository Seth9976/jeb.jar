package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface INativeAttribute {
   Object getValue();

   boolean importTo(INativeItem var1);

   boolean isPrintable();

   String getType();
}
