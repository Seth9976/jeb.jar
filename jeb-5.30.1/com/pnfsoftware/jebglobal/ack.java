package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class ack extends IL {
   public ack() {
      super(new aay.eo().q(ProcessorType.X86, ProcessorType.X86_64).q(ICompiler.COMP_UNKNOWN_LINUX, ICompiler.COMP_ANDROID_ART, ICompiler.COMP_ANDROID_NDK).q());
   }

   @Override
   public List Dw() {
      ArrayList var1 = new ArrayList();
      var1.add(new csy());
      var1.add(new crm());
      return var1;
   }
}
