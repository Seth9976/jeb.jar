package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class acu extends IL {
   public acu() {
      super(new aay.eo().q(ProcessorType.MIPS, ProcessorType.MIPS64).xK(true).Dw(0).q());
   }

   @Override
   public List Dw() {
      ArrayList var1 = new ArrayList();
      var1.add(new ckl());
      return var1;
   }
}
