package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class ali {
   @SerId(1)
   public int pC;
   @SerId(2)
   public List A = new ArrayList();

   @Override
   public String toString() {
      return Strings.ff("full=%d, partial=%s", this.pC, this.A);
   }
}
