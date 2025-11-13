package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class ciw {
   private int pC = -1;
   private String A;
   private List kS = new ArrayList();
   private ciw wS;
   private civ UT;

   public ciw() {
   }

   public ciw(int var1, ciw var2) {
      if (var1 == -1) {
         throw new IllegalArgumentException("Invalid resource id");
      } else {
         this.pC = var1;
         this.pC(var2);
      }
   }

   public ciw(String var1, ciw var2) {
      if (var1 == null) {
         throw new NullPointerException("Invalid resource name");
      } else {
         this.A = var1;
         this.pC(var2);
      }
   }

   private void pC(ciw var1) {
      if (var1 == null) {
         throw new NullPointerException("Invalid parent");
      } else {
         this.wS = var1;
         var1.kS.add(this);
      }
   }

   public boolean pC() {
      return this.A == null && this.pC == -1;
   }

   public int A() {
      return this.pC;
   }

   public String kS() {
      return this.A;
   }

   public ciw wS() {
      return this.wS;
   }

   public List UT() {
      return this.kS;
   }

   public boolean E() {
      return this.UT != null;
   }

   public void pC(civ var1) {
      this.UT = var1;
   }

   public List sY() {
      ArrayList var1 = new ArrayList();
      this.pC(this, var1);
      return var1;
   }

   private void pC(ciw var1, List var2) {
      if (var1.UT != null) {
         var2.add(var1.UT);
         Assert.a(var1.kS.isEmpty(), "A resource data entry cannot contain resource nodes under it");
      }

      for (ciw var4 : var1.kS) {
         this.pC(var4, var2);
      }
   }

   @Override
   public String toString() {
      return this.pC != -1 ? Strings.ff("Resource:%d", this.pC) : Strings.ff("Resource:\"%s\"", this.A);
   }
}
