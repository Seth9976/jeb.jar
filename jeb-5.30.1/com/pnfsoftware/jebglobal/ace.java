package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class ace extends IL {
   public ace() {
      super(new aay.eo().q("wincoff", "winpe").RF(false).q());
   }

   @Override
   public List Dw() {
      ArrayList var1 = new ArrayList();
      var1.add(new com.pnfsoftware.jeb.corei.parsers.winpe.tw());
      return var1;
   }
}
