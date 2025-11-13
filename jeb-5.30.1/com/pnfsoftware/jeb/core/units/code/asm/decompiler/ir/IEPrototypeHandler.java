package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import java.util.List;

public interface IEPrototypeHandler {
   boolean applyKnownPrototype(boolean var1);

   boolean retrieveFromPrototype(List var1, List var2);

   boolean refinePrototype();
}
