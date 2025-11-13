package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class cqf {
   private int q = -1;
   private String RF;
   private List xK = new ArrayList();
   private cqf Dw;
   private cqe Uv;

   public cqf() {
   }

   public cqf(int var1, cqf var2) {
      if (var1 == -1) {
         throw new IllegalArgumentException("Invalid resource id");
      } else {
         this.q = var1;
         this.q(var2);
      }
   }

   public cqf(String var1, cqf var2) {
      if (var1 == null) {
         throw new NullPointerException("Invalid resource name");
      } else {
         this.RF = var1;
         this.q(var2);
      }
   }

   private void q(cqf var1) {
      if (var1 == null) {
         throw new NullPointerException("Invalid parent");
      } else {
         this.Dw = var1;
         var1.xK.add(this);
      }
   }

   public boolean q() {
      return this.RF == null && this.q == -1;
   }

   public int RF() {
      return this.q;
   }

   public String xK() {
      return this.RF;
   }

   public boolean Dw() {
      return this.Uv == null;
   }

   public cqf Uv() {
      return this.Dw;
   }

   public List oW() {
      return this.xK;
   }

   public boolean gO() {
      return this.Uv != null;
   }

   public cqe nf() {
      return this.Uv;
   }

   public void q(cqe var1) {
      this.Uv = var1;
   }

   public List gP() {
      ArrayList var1 = new ArrayList();
      this.q(this, var1);
      return var1;
   }

   private void q(cqf var1, List var2) {
      if (var1.Uv != null) {
         var2.add(var1.Uv);
         Assert.a(var1.xK.isEmpty(), "A resource data entry cannot contain resource nodes under it");
      }

      for (cqf var4 : var1.xK) {
         this.q(var4, var2);
      }
   }

   @Override
   public String toString() {
      return this.q != -1 ? Strings.ff("Resource:%d", this.q) : Strings.ff("Resource:\"%s\"", this.RF);
   }
}
