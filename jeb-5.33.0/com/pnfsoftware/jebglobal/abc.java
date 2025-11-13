package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class abc extends YA {
   public abc() {
      super(new VM.Av().pC(ProcessorType.MIPS, ProcessorType.MIPS64).kS(true).wS(0).pC());
   }

   @Override
   public List wS() {
      ArrayList var1 = new ArrayList();
      var1.add(new com.pnfsoftware.jeb.corei.parsers.mips.K());
      return var1;
   }
}
