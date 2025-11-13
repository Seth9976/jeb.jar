package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aaf extends YA {
   public aaf() {
      super(new VM.Av().pC(ICompiler.COMP_VISUAL_STUDIO).pC(1).pC());
   }
}
