package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class aby implements abv {
   @SerId(1)
   private final abq pC;
   @SerId(2)
   private List A = new ArrayList();

   public aby(abq var1) {
      this.pC = var1;
   }

   public void pC(abx var1) {
      this.A.add(var1);
   }

   @Override
   public List pC() {
      return this.A;
   }

   @Override
   public boolean kS() {
      return this.A.isEmpty();
   }

   @Override
   public abq A() {
      return this.pC;
   }
}
