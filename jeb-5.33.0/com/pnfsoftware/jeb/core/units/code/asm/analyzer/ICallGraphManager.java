package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICallGraphManager {
   ICallGraph getGlobalCallGraph();
}
