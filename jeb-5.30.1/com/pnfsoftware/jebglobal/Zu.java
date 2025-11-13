package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Zu {
   @SerId(1)
   int q;

   public Zu(int var1) {
      this.q = var1;
   }

   public int q() {
      return this.q;
   }
}
