package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class aar extends YA {
   public aar() {
      super(new VM.Av().pC(ProcessorType.X86, ProcessorType.X86_64).pC());
   }

   @Override
   public List wS() {
      ArrayList var1 = new ArrayList();
      var1.add(new com.pnfsoftware.jeb.corei.parsers.x86.io());
      return var1;
   }
}
