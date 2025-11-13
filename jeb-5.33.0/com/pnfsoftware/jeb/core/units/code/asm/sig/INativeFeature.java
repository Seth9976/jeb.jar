package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface INativeFeature {
   Object getValue();

   String getType();

   boolean match(INativeFeature var1);

   INativeFeature deepCopy();
}
