package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Ser
public class cuv {
   @SerId(1)
   List q = new ArrayList();

   public cuv(Collection var1) {
      this.q = new ArrayList(var1);
   }

   public List q() {
      return this.q;
   }
}
