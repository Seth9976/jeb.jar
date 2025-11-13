package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class aas extends YA {
   public aas() {
      super(
         new VM.Av().pC(ProcessorType.X86, ProcessorType.X86_64).pC(ICompiler.COMP_UNKNOWN_LINUX, ICompiler.COMP_ANDROID_ART, ICompiler.COMP_ANDROID_NDK).pC()
      );
   }

   @Override
   public List wS() {
      ArrayList var1 = new ArrayList();
      var1.add(new com.pnfsoftware.jeb.corei.parsers.x86.OX());
      var1.add(new com.pnfsoftware.jeb.corei.parsers.x86.Kr());
      return var1;
   }
}
