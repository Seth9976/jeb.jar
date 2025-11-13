package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class ann {
   @SerId(1)
   public int q;
   @SerId(2)
   public List RF = new ArrayList();

   public int q() {
      return this.q;
   }

   public List RF() {
      return this.RF;
   }

   @Override
   public String toString() {
      return Strings.ff("full=%d, partial=%s", this.q, this.RF);
   }
}
