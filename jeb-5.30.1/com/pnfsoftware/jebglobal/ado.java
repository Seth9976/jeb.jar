package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class ado implements adm {
   @SerId(1)
   private final long q;
   @SerId(2)
   private final List RF;

   private ado(long var1, List var3) {
      this.q = var1;
      this.RF = var3;
   }

   public static ado q(long var0, adl var2) {
      ArrayList var3 = new ArrayList();
      var3.add(var2);
      return new ado(var0, var3);
   }

   public static ado q(long var0, List var2) {
      return new ado(var0, var2);
   }

   public static ado q(long var0) {
      return new ado(var0, null);
   }

   @Override
   public boolean RF() {
      return this.RF == null;
   }

   @Override
   public long q() {
      return this.q;
   }

   @Override
   public List xK() {
      return this.RF;
   }
}
