package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aaq extends YA {
   public aaq() {
      super(new VM.Av().pC(ProcessorType.X86_64).pC(ICompiler.COMP_VISUAL_STUDIO).pC("winpe").wS(4).pC());
   }
}
