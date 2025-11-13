package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aae extends YA {
   public aae() {
      super(new VM.Av().pC(ICompiler.COMP_UNKNOWN_LINUX, ICompiler.COMP_ANDROID_ART, ICompiler.COMP_ANDROID_NDK).pC(1).pC());
   }
}
