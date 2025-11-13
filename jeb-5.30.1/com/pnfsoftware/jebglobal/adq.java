package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class adq implements adn {
   @SerId(1)
   private final adi q;
   @SerId(2)
   private List RF = new ArrayList();

   public adq(adi var1) {
      this.q = var1;
   }

   public void q(adp var1) {
      this.RF.add(var1);
   }

   @Override
   public List q() {
      return this.RF;
   }

   @Override
   public boolean xK() {
      return this.RF.isEmpty();
   }

   @Override
   public adi RF() {
      return this.q;
   }
}
