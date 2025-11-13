package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class aam extends YA {
   public aam() {
      super(new VM.Av().pC("wincoff", "winpe").A(false).pC());
   }

   @Override
   public List wS() {
      ArrayList var1 = new ArrayList();
      var1.add(new com.pnfsoftware.jeb.corei.parsers.winpe.rQ());
      return var1;
   }
}
