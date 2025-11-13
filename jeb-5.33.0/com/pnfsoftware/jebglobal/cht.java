package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class cht {
   @SerId(1)
   private Integer pC;
   @SerId(2)
   private boolean A;
   @SerId(3)
   private String UT;
   @SerId(4)
   private String E;
   @SerId(5)
   public long kS;
   @SerId(6)
   public int wS;

   public void pC(int var1) {
      if (this.pC != null) {
         throw new IllegalStateException();
      } else if (var1 < 0) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
      }
   }

   public int wS() {
      return this.pC == null ? -1 : this.pC;
   }

   protected void pC(com.pnfsoftware.jeb.corei.parsers.wasm.KD var1) {
      this.A = true;
      this.UT = var1.pC();
      this.E = var1.A();
   }

   public boolean UT() {
      return this.A;
   }

   public boolean E() {
      return !this.UT();
   }

   public void pC(String var1) {
      if (this.UT()) {
         throw new IllegalStateException();
      } else {
         this.E = var1;
      }
   }

   protected String A() {
      return "__internal_entry_" + this.pC;
   }

   public boolean sY() {
      return this.E != null;
   }

   public String ys() {
      return this.E == null ? this.A() : this.E;
   }

   public String ld() {
      if (this.E == null) {
         return this.ys();
      } else {
         return this.UT == null ? this.E : this.UT + "." + this.E;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("#%d=%s(%b)", this.wS(), this.ld(), this.E());
   }
}
