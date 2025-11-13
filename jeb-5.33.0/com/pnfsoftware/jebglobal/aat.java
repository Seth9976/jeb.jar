package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class aat extends YA {
   public aat() {
      super(new VM.Av().pC(ProcessorType.X86, ProcessorType.X86_64).pC(ICompiler.COMP_VISUAL_STUDIO).pC(com.pnfsoftware.jeb.corei.parsers.x86.wn.Av.kS).pC());
   }

   @Override
   public List wS() {
      ArrayList var1 = new ArrayList();
      var1.add(new cjk());
      return var1;
   }
}
