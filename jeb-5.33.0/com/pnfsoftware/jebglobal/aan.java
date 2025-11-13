package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class aan extends YA {
   public aan() {
      super(new VM.Av().pC(ProcessorType.ARM, ProcessorType.ARM64).kS(11).pC());
   }

   @Override
   public List wS() {
      ArrayList var1 = new ArrayList();
      var1.add(new com.pnfsoftware.jeb.corei.parsers.arm.Av());
      return var1;
   }
}
