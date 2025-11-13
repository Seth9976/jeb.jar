package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aci extends IL {
   public aci() {
      super(new aay.eo().q(ProcessorType.X86_64).q(ICompiler.COMP_VISUAL_STUDIO).q("winpe").Dw(4).q());
   }
}
